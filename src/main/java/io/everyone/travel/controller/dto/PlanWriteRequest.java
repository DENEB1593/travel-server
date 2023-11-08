package io.everyone.travel.controller.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlanWriteRequest {

    String content;

}
