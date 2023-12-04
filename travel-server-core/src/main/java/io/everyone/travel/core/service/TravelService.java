package io.everyone.travel.core.service;

import io.everyone.travel.core.domain.Travel;
import io.everyone.travel.core.domain.enums.Nation;
import io.everyone.travel.core.exception.NotFoundException;
import io.everyone.travel.core.repository.TravelRepository;
import io.everyone.travel.core.util.EnumSupports;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    private static final Sort TRAVEL_DEFAULT_SORT = Sort.by("startAt").descending();

    @Transactional
    public Travel save(LocalDateTime starAt, LocalDateTime endAt, String title, String nation) {
        Travel travel = Travel.builder()
            .startAt(starAt)
            .endAt(endAt)
            .title(title)
            .nation(EnumSupports.byEnumName(Nation.class, nation))
            .build();

        // 이미지 확인 우선은 기본이미지 주소로..
        String thumbnail = "http://localhost:8080/images/default_travel_image.png";
        travel.updateThumbnail(thumbnail);

        return travelRepository.save(travel);
    }

    @Transactional(readOnly = true)
    public Optional<Travel> findById(Long travelId) {
        return travelRepository.findById(travelId);
    }

    @Transactional(readOnly = true)
    public Page<Travel> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, TRAVEL_DEFAULT_SORT);
        return travelRepository.findAll(pageable);
    }

    @Transactional
    public Travel updateTravel(
        Long travelId, LocalDateTime starAt, LocalDateTime endAt, String title, String nation
    ) {
        Travel travel = this.findById(travelId)
            .orElseThrow(NotFoundException::forTravel);

        travel.updateFromRequest(
            title,
            EnumSupports.byEnumName(Nation.class, nation),
            starAt,
            endAt
        );

        travelRepository.save(travel);

        return travel;
    }

    @Transactional
    public void deleteById(Long travelId) {
        travelRepository
            .findById(travelId)
            .ifPresentOrElse(
                (travel) -> travelRepository.deleteById(travel.getId()),
                () -> { throw NotFoundException.forTravel(); }
            );
        ;
    }
}
