package io.everyone.travel.controller.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class TravelView {

    Long id;

    LocalDateTime startAt;

    LocalDateTime endAt;

    String title;

    NationModel nation;

    List<PlanView> plans;

    List<ExpenseView> expenses;


}
