package io.everyone.travel.controller.dto;

import io.everyone.travel.domain.enums.Nation;
import lombok.Builder;
import lombok.Value;

/**
 * 국가 코드 모델
 */
@Value
@Builder
public class NationModel {

    String code;

    String kr;

    String eng;

    public static NationModel of(Nation nation) {
        if (nation == null) {
            return null;
        }

        return NationModel.builder()
                .code(nation.getCode())
                .kr(nation.getKr())
                .eng(nation.getEng())
                .build();
    }

}
