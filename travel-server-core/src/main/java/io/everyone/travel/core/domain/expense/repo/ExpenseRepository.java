package io.everyone.travel.core.domain.expense.repo;


import io.everyone.travel.core.domain.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
