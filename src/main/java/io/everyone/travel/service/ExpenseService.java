package io.everyone.travel.service;

import io.everyone.travel.controller.dto.ExpenseView;
import io.everyone.travel.controller.dto.ExpenseWriteRequest;
import io.everyone.travel.domain.Expense;
import io.everyone.travel.domain.Plan;
import io.everyone.travel.domain.Travel;
import io.everyone.travel.exception.NotFoundException;
import io.everyone.travel.mapper.ExpenseMapper;
import io.everyone.travel.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final TravelService travelService;
    private final ExpenseRepository expenseRepository;


    @Transactional
    public Expense save(ExpenseWriteRequest request) {
        Travel travel = travelService
            .findById(request.travelId())
            .orElseThrow(() -> new NotFoundException("여행 정보가 조회되지 않습니다"));

        Expense expense = ExpenseMapper.toEntity(request);
        expense.setTravel(travel);

        expenseRepository.save(expense);

        return expense;
    }

    @Transactional(readOnly = true)
    public Set<Expense> findByTravelId(Long travelId) {
        return travelService
            .findById(travelId)
            .map(Travel::getExpenses)
            .orElseThrow(() -> new NotFoundException(String.format("travel not found with id [%d]", travelId)));

    }

    @Transactional
    public void deleteById(Long expenseId) {
        expenseRepository.findById(expenseId)
            .ifPresentOrElse(
                (expense) ->
                    expenseRepository.deleteById(expense.getId()),
                () -> {
                    throw new NotFoundException("지출 정보가 조회되지 않습니다");
                }
            );
    }



}
