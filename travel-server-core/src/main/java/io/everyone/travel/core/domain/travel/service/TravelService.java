package io.everyone.travel.core.domain.travel.service;

import io.everyone.travel.core.domain.travel.dto.UpdateTravel;
import io.everyone.travel.core.domain.travel.dto.WriteTravel;
import io.everyone.travel.core.domain.travel.entity.Travel;
import io.everyone.travel.core.domain.travel.enums.Nation;
import io.everyone.travel.core.domain.travel.mapper.TravelMapper;
import io.everyone.travel.core.domain.travel.repo.TravelRepository;
import io.everyone.travel.core.exception.NotFoundException;
import io.everyone.travel.core.util.DateUtils;
import io.everyone.travel.core.util.EnumSupports;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.util.Assert.isTrue;


@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    private static final Sort TRAVEL_DEFAULT_SORT = Sort.by("startAt").descending();

    @Transactional
    public Travel save(WriteTravel writeTravel) {
        this.validateWriteRequest(writeTravel);

        return travelRepository.save(
            TravelMapper.writeRequestToTravel(writeTravel)
        );
    }

    @Transactional(readOnly = true)
    public Optional<Travel> findById(Long travelId) {
        return travelRepository.findById(travelId);
    }

    @Transactional(readOnly = true)
    public Page<Travel> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, TRAVEL_DEFAULT_SORT);
        return travelRepository.findAll(pageable);
    }

    @Transactional
    public Travel updateTravel(UpdateTravel updateTravel) {
        this.validateUpdateRequest(updateTravel);

        Travel travel = this
            .findById(updateTravel.travelId())
            .orElseThrow(NotFoundException::forTravel);

        travel.updateFromRequest(
            updateTravel.title(),
            EnumSupports.byEnumName(Nation.class, updateTravel.nation()),
            updateTravel.startAt(),
            updateTravel.endAt()
        );

        travelRepository.save(travel);

        return travel;
    }

    @Transactional
    public void deleteById(Long travelId) {
        travelRepository
            .findById(travelId)
            .ifPresentOrElse(
                it -> travelRepository.deleteById(it.getId()),
                () -> { throw NotFoundException.forTravel(); }
            );
    }

    @Transactional
    public void updateThumbnail(Long travelId, String thumbnailUrl) {
        travelRepository.findById(travelId).ifPresent(it -> it.updateThumbnail(thumbnailUrl));
    }

    private void validateWriteRequest(WriteTravel writeTravel) {
        isTrue(writeTravel.title().length() <= 200, "여행명은 200자 이하여야 합니다");
        isTrue(
            DateUtils.isOnOrAfter(writeTravel.startAt(), writeTravel.endAt()),
            "여행종료일자는 시작일자 이후여야 합니다"
        );
    }

    private void validateUpdateRequest(UpdateTravel updateTravel) {
        isTrue(updateTravel.title().length() <= 200, "여행명은 200자 이하여야 합니다");
        isTrue(
            DateUtils.isOnOrAfter(updateTravel.startAt(), updateTravel.endAt()) ,
            "여행종료일자는 시작일자 이후여야 합니다"
        );
    }
}
