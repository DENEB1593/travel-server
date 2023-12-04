package io.everyone.travel.core.repository;


import io.everyone.travel.core.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
