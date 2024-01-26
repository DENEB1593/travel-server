package io.everyone.travel.api.web.travel;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.everyone.travel.api.config.additional.PageModel;
import io.everyone.travel.api.web.CommonResponse;
import io.everyone.travel.api.exception.model.ProblemResponseModel;
import io.everyone.travel.api.web.expense.mapper.ExpenseApiMapper;
import io.everyone.travel.api.web.plan.mapper.PlanApiMapper;
import io.everyone.travel.api.web.travel.mapper.TravelApiMapper;
import io.everyone.travel.api.web.expense.dto.ExpenseView;
import io.everyone.travel.api.web.plan.dto.PlanView;
import io.everyone.travel.api.web.travel.dto.*;
import io.everyone.travel.core.domain.travel.dto.UpdateTravel;
import io.everyone.travel.core.domain.travel.dto.WriteTravel;
import io.everyone.travel.core.domain.travel.entity.Travel;
import io.everyone.travel.core.exception.NotFoundException;
import io.everyone.travel.core.domain.expense.service.ExpenseService;
import io.everyone.travel.core.domain.plan.service.PlanService;
import io.everyone.travel.core.domain.travel.service.TravelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static io.everyone.travel.api.web.CommonResponse.OK;
import static org.springframework.http.HttpStatus.CREATED;

@Tag(name = "여행 API")
@Slf4j
@RestController
@RequestMapping("api/travels")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;
    private final PlanService planService;
    private final ExpenseService expenseService;
    private final AmazonS3 s3;
    private static final String BUCKET_NAME = "travel";

    @Operation(
        summary = "여행 정보 저장",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "저장 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @ResponseStatus(CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<TravelWriteResponse> save(
        @RequestPart("payload") TravelWriteRequest request,
        @RequestPart("thumbnail") MultipartFile thumbnail
    ) throws IOException {
        WriteTravel writeTravel = TravelApiMapper.toWriteTravel(request, thumbnail);

        Travel travel = travelService.save(writeTravel);

        // 썸네일 업로드 진행
        String key = String.format("thumbnails/%s_%s",
            UUID.randomUUID(),
            thumbnail.getOriginalFilename()
        );

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(thumbnail.getContentType());
        metadata.setContentLength(thumbnail.getSize());
        PutObjectRequest putObjectRequest =
            new PutObjectRequest(BUCKET_NAME, key, thumbnail.getInputStream(), metadata);

        s3.putObject(putObjectRequest);

        return OK(
            TravelApiMapper.toWriteResponse(travel)
        );
    }

    @Operation(
        summary = "여행 정보 단건 조회",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "조회 성공",
                useReturnTypeSchema = true
            ),
            @ApiResponse(
                responseCode = "404",
                description = "조회 불가",
                content = @Content(
                    schema = @Schema(implementation = ProblemResponseModel.class)
                )
            ),
        }
    )
    @GetMapping("/{travelId}")
    public CommonResponse<TravelResponse.TravelView> find(
        @PathVariable Long travelId
    ) {
        return OK(
            travelService.findById(travelId)
                .map(TravelApiMapper::toView)
                .orElseThrow(NotFoundException::forTravel)
        );
    }

    @Operation(
        summary = "여행 목록 조회",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "조회 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @Parameters({
        @Parameter(name = "page", description = "페이지 번호", example = "1"),
        @Parameter(name = "size", description = "페이지 크기", example = "20")
    })
    @GetMapping
    public CommonResponse<TravelResponse> findPaginated(
        @Parameter(hidden = true) PageModel pageModel
    ) {
        return OK(
            TravelResponse.of(
                travelService.findPaginated(
                    pageModel.getPage(),
                    pageModel.getSize()
                )
            )
        );
    }

    @Operation(
        summary = "여행 내 계획 목록 조회",
        description = "여행ID로 등록된 계획 목록을 조회한다",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "조회 성공",
                useReturnTypeSchema = true
            ),
            @ApiResponse(
                responseCode = "404",
                description = "조회 불가",
                content = @Content(
                    schema = @Schema(implementation = ProblemResponseModel.class)
                )
            ),
        }
    )
    @GetMapping("/{travelId}/plans")
    public CommonResponse<List<PlanView>> findPlans(
        @PathVariable Long travelId
    ) {
        return OK(
            planService.findByTravelId(travelId)
                .stream()
                .map(PlanApiMapper::toView)
                .toList()
        );
    }

    @Operation(
        summary = "여행 내 지출 목록 조회",
        description = "여행ID로 등록된 지출 목록을 조회한다",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "조회 성공",
                useReturnTypeSchema = true
            ),
            @ApiResponse(
                responseCode = "404",
                description = "조회 불가",
                content = @Content(
                    schema = @Schema(implementation = ProblemResponseModel.class)
                )
            ),
        }
    )
    @GetMapping("/{travelId}/expenses")
    public CommonResponse<List<ExpenseView>> findExpenses(
        @PathVariable Long travelId
    ) {
        return OK(
            expenseService.findByTravelId(travelId)
                .stream()
                .map(ExpenseApiMapper::toView)
                .toList()
        );
    }


    @Operation(
        summary = "여행 정보 수정",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "수정 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @PutMapping(path = "/{travelId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResponse<TravelUpdateResponse> update(
        @PathVariable Long travelId,
        @ModelAttribute @Valid TravelUpdateRequest request
    ) {
        UpdateTravel updateTravel = TravelApiMapper.toUpdateTravel(travelId, request);
        return OK(
            TravelApiMapper.toUpdateResponse(
                travelService.updateTravel(updateTravel)
            )
        );
    }

    @Operation(
        summary = "여행 정보 삭제",
        description = "여행 정보를 삭제한다",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "삭제 성공",
                useReturnTypeSchema = true
            )
        }
    )
    @DeleteMapping("/{travelId}")
    public CommonResponse<?> delete(
        @PathVariable Long travelId
    ) {
        travelService.deleteById(travelId);
        return OK("삭제 완료");
    }


}
