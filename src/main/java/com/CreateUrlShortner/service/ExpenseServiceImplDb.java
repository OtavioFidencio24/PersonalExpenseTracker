package com.CreateUrlShortner.service;

import com.CreateUrlShortner.model.Expense;
import com.CreateUrlShortner.repository.ExpenseRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marks this class as a service component for Spring's dependency injection.
@Profile("db") // This implementation is only active when the "db" profile is enabled.
public class ExpenseServiceImplDb implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImplDb(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> getExpenses() {
        return expenseRepository.findAll(); // Retrieves all expenses from the database.
    }

    @Override
    public List<Expense> getExpenseByDay(String date) {
        return expenseRepository.findAll().stream()
                .filter(expense -> expense.getDate().equalsIgnoreCase(date)) // Filters by exact date match.
                .toList();
    }

    @Override
    public List<Expense> getExpenseByCategoryAndMonth(String category, String month) {
        return expenseRepository.findAll().stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category)
                        && expense.getDate().startsWith(month)) // Assumes the date starts with the month string (e.g., "2024-03").
                .toList();
    }

    @Override
    public List<String> getAllExpenseCategories() {
        return expenseRepository.findAll().stream()
                .map(Expense::getCategory) // Extracts category names.
                .distinct() // Removes duplicates.
                .toList();
    }

    @Override
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id); // Retrieves an expense by ID.
    }

    @Override
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense); // Saves a new expense in the database.
    }

    @Override
    public boolean updateExpense(Expense updatedExpense) {
        if (expenseRepository.existsById(updatedExpense.getId())) {
            expenseRepository.save(updatedExpense); // Updates the existing expense.
            return true;
        } else {
            return false; // Returns false if the expense ID does not exist.
        }
    }

    @Override
    public boolean deleteExpense(Long id) {
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id); // Deletes the expense if it exists.
            return true;
        } else {
            return false; // Returns false if the expense does not exist.
        }
    }
}
