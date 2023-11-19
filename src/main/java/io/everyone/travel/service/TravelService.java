package io.everyone.travel.service;

import io.everyone.travel.controller.dto.TravelView;
import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.domain.Travel;
import io.everyone.travel.mapper.TravelMapper;
import io.everyone.travel.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    @Transactional
    public Travel save(TravelWriteRequest request) {
        Travel travel = TravelMapper.toEntity(request);

        // 이미지 확인 우선은 기본이미지 주소로..
        String thumbnail = "http://localhost:8080/images/default_travel_image.png";
        travel.updateThumbnail(thumbnail);

        return travelRepository.save(travel);
    }

    @Transactional(readOnly = true)
    public Optional<Travel> findById(Long id) {
        return travelRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Travel> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return travelRepository.findAllFetchJoin(pageable);
    }


}
