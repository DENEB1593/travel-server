package io.everyone.travel.core.domain.plan.repo;


import io.everyone.travel.core.domain.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    Optional<List<Plan>> findByTravelId(Long travelId);

}
