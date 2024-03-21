package io.everyone.travel.core.domain.travel.mapper;

import io.everyone.travel.core.domain.travel.dto.WriteTravel;
import io.everyone.travel.core.domain.travel.entity.Travel;
import io.everyone.travel.core.domain.travel.enums.Nation;
import io.everyone.travel.core.util.EnumUtils;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TravelMapper {

    public Travel writeRequestToTravel(WriteTravel request) {
        return Travel.builder()
            .startAt(request.startAt())
            .endAt(request.endAt())
            .title(request.title())
            .nation(
                EnumUtils.byEnumName(Nation.class, request.nation())
            )
            .build();
    }

}
