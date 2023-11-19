package io.everyone.travel.service;

import io.everyone.travel.domain.Plan;
import io.everyone.travel.domain.Travel;
import io.everyone.travel.exception.NotFoundException;
import io.everyone.travel.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final TravelRepository travelRepository;

    @Transactional(readOnly = true)
    public Set<Plan> findByTravelId(Long travelId) {
        return travelRepository
            .findById(travelId)
            .map(Travel::getPlans)
            .orElseThrow(() -> new NotFoundException(String.format("travel not found with id [%d]", travelId)));

    }

}
