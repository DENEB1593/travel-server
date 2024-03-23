package io.everyone.travel.core.domain.travel.service;

import io.everyone.travel.core.domain.travel.entity.TravelAlarm;
import io.everyone.travel.core.domain.travel.repo.TravelAlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class TravelAlarmService {

	private final TravelAlarmRepository travelAlarmRepository;
	private final static Sort DEFAULT_SORT = Sort.by(Sort.Direction.ASC, "nation");

	public List<TravelAlarm> findAll() {
		return travelAlarmRepository.findAll(DEFAULT_SORT);
	}


}
