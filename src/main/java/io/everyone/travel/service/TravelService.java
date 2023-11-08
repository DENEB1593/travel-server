package io.everyone.travel.service;

import io.everyone.travel.controller.dto.TravelWriteRequest;
import io.everyone.travel.domain.Travel;
import io.everyone.travel.mapper.TravelMapper;
import io.everyone.travel.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    @Transactional
    public Travel save(TravelWriteRequest request) {
        Travel travel = TravelMapper.of(request);
        return travelRepository.save(travel);
    }
}
