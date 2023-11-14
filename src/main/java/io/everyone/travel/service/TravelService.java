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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    @Transactional
    public Travel save(TravelWriteRequest request) {
        Travel travel = TravelMapper.toEntity(request);
        return travelRepository.save(travel);
    }

    @Transactional(readOnly = true)
    public Optional<Travel> findById(Long id) {
        return travelRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Travel> findPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return travelRepository.findAll();
    }

}
