package io.everyone.travel.core.domain.expense.service;

import io.everyone.travel.core.domain.expense.dto.UpdateExpense;
import io.everyone.travel.core.domain.expense.dto.WriteExpense;
import io.everyone.travel.core.domain.expense.entity.Expense;
import io.everyone.travel.core.domain.travel.Travel;
import io.everyone.travel.core.exception.NotFoundException;
import io.everyone.travel.core.domain.expense.repo.ExpenseRepository;
import io.everyone.travel.core.domain.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final TravelService travelService;
    private final ExpenseRepository expenseRepository;


    @Transactional
    public Expense save(WriteExpense writeExpense) {
        Travel travel = travelService
            .findById(writeExpense.travelId())
            .orElseThrow(NotFoundException::forTravel);

        Expense expense = Expense.builder()
            .amt(writeExpense.amt())
            .spendAt(writeExpense.spendAt())
            .build();

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
    public Expense updateExpense(
        Long expenseId, UpdateExpense updateExpense
    ) {
        Expense expense = expenseRepository
            .findById(expenseId)
            .orElseThrow(NotFoundException::forExpense);

        expense.updateFromRequest(updateExpense);
        expenseRepository.save(expense);

        return expense;
    }

    @Transactional
    public void deleteById(Long expenseId) {
        expenseRepository.findById(expenseId)
            .ifPresentOrElse(
                (expense) -> expenseRepository.deleteById(expense.getId()),
                () -> {throw NotFoundException.forExpense();}
            );
    }



}
