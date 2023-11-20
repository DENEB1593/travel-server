package io.everyone.travel.service;

import io.everyone.travel.exception.NotFoundException;
import io.everyone.travel.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

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
