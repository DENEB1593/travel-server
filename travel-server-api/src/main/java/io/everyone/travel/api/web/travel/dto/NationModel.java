package io.everyone.travel.api.web.travel.dto;

import io.everyone.travel.core.domain.travel.enums.Nation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableSet;


@Schema(name = "국가 모델", description = "국가에 대한 정보를 나타낸다")
public record NationModel(

    @Schema(description = "코드", example = "KR")
    String code,

    @Schema(description = "한국어명", example = "대한민국")
    String kr,

    @Schema(description = "영어명", example = "KOREA, REPUBLIC OF")
    String eng
) {

    public static NationModel of(Nation from) {
        if (from == null) {
            return null;
        }

        return new NationModel(
            from.getCode(),
            from.getKr(),
            from.getEng()
        );
    }

    public static Set<NationModel> toModel() {
        return Arrays.stream(Nation.values())
            .map(NationModel::of)
            .collect(toUnmodifiableSet());
    }

}
