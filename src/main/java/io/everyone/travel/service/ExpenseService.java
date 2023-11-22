package io.everyone.travel.service;

import io.everyone.travel.controller.dto.ExpenseUpdateRequest;
import io.everyone.travel.controller.dto.ExpenseWriteRequest;
import io.everyone.travel.domain.Expense;
import io.everyone.travel.domain.Travel;
import io.everyone.travel.exception.NotFoundException;
import io.everyone.travel.mapper.ExpenseMapper;
import io.everyone.travel.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
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
            .orElseThrow(NotFoundException::forTravel);

        Expense expense = ExpenseMapper.toEntity(request);
        expense.setTravel(travel);

        expenseRepository.save(expense);

        return expense;
    }

    @Transactional(readOnly = true)
    public Optional<Expense> findByExpenseId(Long expenseId) {
        return expenseRepository.findById(expenseId);
    }

    @Transactional(readOnly = true)
    public Set<Expense> findByTravelId(Long travelId) {
        return travelService
            .findById(travelId)
            .map(Travel::getExpenses)
            .orElseThrow(NotFoundException::forTravel);

    }

    @Transactional
    public Expense updateExpense(Long expenseId, ExpenseUpdateRequest request) {
        Expense expense = expenseRepository
            .findById(expenseId)
            .orElseThrow(NotFoundException::forExpense);

        expense.updateFromRequest(request);
        expenseRepository.save(expense);

        return expense;
    }

    @Transactional
    public void deleteById(Long expenseId) {
        expenseRepository.findById(expenseId)
            .ifPresentOrElse(
                (expense) -> expenseRepository.deleteById(expense.getId()),
                NotFoundException::forExpense
            );
    }



}
