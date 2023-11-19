package io.everyone.travel.repository;

import io.everyone.travel.domain.Travel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {

    @Query(
        "select t from Travel t " +
            "join fetch t.plans " +
            "join fetch t.expenses "
    )
    List<Travel> findAllFetchJoin(Pageable pageable);
}
