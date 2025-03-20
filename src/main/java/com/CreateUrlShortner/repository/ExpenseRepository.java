package com.CreateUrlShortner.repository;

import com.CreateUrlShortner.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository provides built-in CRUD operations, so no need to implement them manually.
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
