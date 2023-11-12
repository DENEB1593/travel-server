package io.everyone.travel.service;

import io.everyone.travel.domain.Plan;
import io.everyone.travel.domain.Travel;
import io.everyone.travel.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final TravelRepository travelRepository;

    @Transactional(readOnly = true)
    public List<Plan> findByTravelId(Long travelId) {
        Travel travel = travelRepository
                .findById(travelId)
                .orElseThrow(() -> new RuntimeException("travel not found :" + travelId));

        return travel.getPlans();

    }

}
