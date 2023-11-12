package io.everyone.travel.service;

import io.everyone.travel.domain.Plan;
import io.everyone.travel.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    @Transactional(readOnly = true)
    public Optional<List<Plan>> findByTravelId(Long travelId) {
        return planRepository.findByTravelId(travelId); // 엔티티 연관관계니까 Travel로 조회하는게 맞는듯..?

    }

}
