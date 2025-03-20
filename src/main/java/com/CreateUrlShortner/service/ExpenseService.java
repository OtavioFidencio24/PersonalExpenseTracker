package com.CreateUrlShortner.service;

import com.CreateUrlShortner.model.Expense;
import java.util.List;
import java.util.Optional;

// Defines the contract for expense-related operations
public interface ExpenseService {

    // Retrieve all expenses
    List<Expense> getExpenses();

    // Retrieve expenses by a specific day
    List<Expense> getExpenseByDay(String date);

    // Retrieve expenses filtered by category and month
    List<Expense> getExpenseByCategoryAndMonth(String category, String month);

    // Retrieve all unique expense categories
    List<String> getAllExpenseCategories();

    // Retrieve a specific expense by its ID
    Optional<Expense> getExpenseById(Long id);

    // Add a new expense record
    Expense addExpense(Expense expense);

    // Update an existing expense; returns true if successful, false if the expense doesn't exist
    boolean updateExpense(Expense expense);

    // Delete an expense by ID; returns true if deleted, false if not found
    boolean deleteExpense(Long id);
}
