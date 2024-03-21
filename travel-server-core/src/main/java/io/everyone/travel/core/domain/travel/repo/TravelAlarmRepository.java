package io.everyone.travel.core.domain.travel.repo;

import io.everyone.travel.core.domain.travel.entity.TravelAlarm;
import io.everyone.travel.core.domain.travel.enums.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelAlarmRepository extends JpaRepository<TravelAlarm, Nation> {
}
