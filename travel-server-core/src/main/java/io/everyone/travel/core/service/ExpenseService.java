package io.everyone.travel.core.service;

import io.everyone.travel.core.domain.Expense;
import io.everyone.travel.core.domain.Travel;
import io.everyone.travel.core.exception.NotFoundException;
import io.everyone.travel.core.repository.ExpenseRepository;
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
    public Expense save(BigDecimal amt, LocalDateTime spendAt, Long travelId) {
        Travel travel = travelService
            .findById(travelId)
            .orElseThrow(NotFoundException::forTravel);

        Expense expense = Expense.builder()
            .amt(amt)
            .spendAt(spendAt)
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
        Long expenseId, BigDecimal amt, LocalDateTime spendAt, Long travelId
    ) {
        Expense expense = expenseRepository
            .findById(expenseId)
            .orElseThrow(NotFoundException::forExpense);

        expense.updateFromRequest(amt, spendAt);
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
