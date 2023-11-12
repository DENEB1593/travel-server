package io.everyone.travel.repository;

import io.everyone.travel.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    Optional<List<Plan>> findByTravelId(Long travelId);

}
