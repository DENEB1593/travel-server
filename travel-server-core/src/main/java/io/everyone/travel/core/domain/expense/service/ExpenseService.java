package io.everyone.travel.core.domain.expense.service;

import io.everyone.travel.core.domain.expense.dto.UpdateExpense;
import io.everyone.travel.core.domain.expense.dto.WriteExpense;
import io.everyone.travel.core.domain.expense.entity.Expense;
import io.everyone.travel.core.domain.expense.mapper.ExpenseMapper;
import io.everyone.travel.core.domain.travel.entity.Travel;
import io.everyone.travel.core.exception.NotFoundException;
import io.everyone.travel.core.domain.expense.repo.ExpenseRepository;
import io.everyone.travel.core.domain.travel.service.TravelService;
import io.everyone.travel.core.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static org.springframework.util.Assert.isTrue;

@Transactional
@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final TravelService travelService;
    private final ExpenseRepository expenseRepository;


    public Expense save(WriteExpense writeExpense) {
        Travel travel = travelService
            .findById(writeExpense.travelId())
            .orElseThrow(NotFoundException::forTravel);

        this.validateWriteExpense(writeExpense, travel);

        Expense expense = ExpenseMapper.writeRequestToExpense(writeExpense);
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

    public void deleteById(Long expenseId) {
        expenseRepository.findById(expenseId)
            .ifPresentOrElse(
                it -> expenseRepository.deleteById(it.getId()),
                () -> {throw NotFoundException.forExpense();}
            );
    }

    private void validateWriteExpense(WriteExpense writeExpense, Travel travel) {
        isTrue(
            DateUtils.isBetween(writeExpense.spendAt(), travel.getStartAt(), travel.getEndAt()),
            "지출일자는 여행 기간 내 포함되어야 합니다"
        );
    }



}
