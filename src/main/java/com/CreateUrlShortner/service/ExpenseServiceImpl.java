package com.CreateUrlShortner.service;

import com.CreateUrlShortner.model.Expense;
import com.CreateUrlShortner.utils.ExpenseDataLoader;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service // Marks this class as a Spring-managed service component.
@Profile("json") // This service implementation is only active when the "json" profile is enabled.
public class ExpenseServiceImpl implements ExpenseService {

    private final AtomicLong idCounter = new AtomicLong();
    // Generates unique IDs for new expenses (since there's no database handling this in this implementation).

    @Override
    public List<Expense> getExpenses() {
        return ExpenseDataLoader.getExpenses(); // Retrieves all expenses from the data loader.
    }

    @Override
    public List<Expense> getExpenseByDay(String date) {
        return ExpenseDataLoader.getExpenses().stream()
                .filter(expense -> expense.getDate().equalsIgnoreCase(date)) // Filters expenses by exact date match.
                .toList();
    }

    @Override
    public List<Expense> getExpenseByCategoryAndMonth(String category, String month) {
        return ExpenseDataLoader.getExpenses().stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category)
                        && expense.getDate().startsWith(month)) // Assumes the date starts with the month string (e.g., "2024-03").
                .toList();
    }

    @Override
    public List<String> getAllExpenseCategories() {
        return ExpenseDataLoader.getExpenses().stream() // Converts the list of expenses into a stream.
                .map(Expense::getCategory) // Extracts only the category field from each Expense object.
                .distinct() // Ensures each category appears only once (removes duplicates).
                .toList(); // Converts the result back into a List<String>.
    }

    @Override
    public Optional<Expense> getExpenseById(Long id) {
        return ExpenseDataLoader.getExpenses().stream()
                .filter(expense -> expense.getId().equals(id)) // Finds the expense by ID.
                .findFirst();
    }

    @Override
    public Expense addExpense(Expense expense) {
        expense.setId(idCounter.incrementAndGet()); // Assigns a unique ID to the new expense.
        ExpenseDataLoader.getExpenses().add(expense); // Adds the expense to the in-memory list.
        return expense;
    }

    @Override
    public boolean updateExpense(Expense updatedExpense) {
        Optional<Expense> existingExpense = getExpenseById(updatedExpense.getId());
        if (existingExpense.isPresent()) {
            ExpenseDataLoader.getExpenses().remove(existingExpense.get()); // Removes the old expense.
            ExpenseDataLoader.getExpenses().add(updatedExpense); // Adds the updated expense.
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteExpense(Long id) {
        Optional<Expense> existingExpense = getExpenseById(id);
        if (existingExpense.isPresent()) {
            ExpenseDataLoader.getExpenses().remove(existingExpense.get()); // Removes the expense if found.
            return true;
        } else {
            return false;
        }
    }
}
