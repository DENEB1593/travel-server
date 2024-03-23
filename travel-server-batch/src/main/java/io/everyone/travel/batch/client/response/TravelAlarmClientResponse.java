package io.everyone.travel.batch.client.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class TravelAlarmClientResponse {

    Long currentCount;

    List<TravelAlarmData> data;

    Long totalCount;

    Long numOfRows;

    Long pageNo;

    Long resultCode;

    String resultMsg;

    @JsonNaming( PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Value
    public static class TravelAlarmData {

        String alarmLvl;

        String continentCd;

        String continentEngNm;

        String continentNm;

        String countryEngNm;

        String countryIsoAlp2;

        String countryNm;

        String dangMapDownloadUrl;

        String flagDownloadUrl;

        String mapDownloadUrl;

        String regionTy;

        String remark;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate writtenDt;

    }
}
