package com.CreateUrlShortner.controller;

import com.CreateUrlShortner.model.Expense;
import com.CreateUrlShortner.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Marks this class as a REST controller, making it capable of handling HTTP requests.
@RequestMapping("/expenses") // Base path for all endpoints in this controller.
public class ExpenseController {

    private final ExpenseService expenseService;

    // Constructor-based dependency injection of the ExpenseService
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Retrieve all expenses
    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses() {
        return ResponseEntity.ok(expenseService.getExpenses());
    }

    // Retrieve a specific expense by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Expense>> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    // Retrieve all distinct expense categories
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllExpenseCategories() {
        List<String> categories = expenseService.getAllExpenseCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); // 204 No Content if no categories are found.
        }
        return ResponseEntity.ok(categories);
    }

    // Retrieve expenses filtered by a specific day
    @GetMapping("/day/{day}")
    public ResponseEntity<List<Expense>> getExpensesByDay(@PathVariable("day") String day) {
        List<Expense> typeExpenses = expenseService.getExpenseByDay(day);
        return ResponseEntity.ok(typeExpenses);
    }

    // Retrieve expenses filtered by category and month
    @GetMapping("/category/{category}/month")
    public ResponseEntity<List<Expense>> getExpensesByCategoryAndMonth(
            @PathVariable String category, @RequestParam String month) {
        List<Expense> filteredExpenses = expenseService.getExpenseByCategoryAndMonth(category, month);
        return ResponseEntity.ok(filteredExpenses);
    }

    // Create a new expense
    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        Expense newExpense = expenseService.addExpense(expense);
        return new ResponseEntity<>(newExpense, HttpStatus.CREATED); // 201 Created response.
    }

    // Update an existing expense
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id,
                                                 @RequestBody Expense expense) {
        expense.setId(id); // Ensure the expense object has the correct ID before updating.
        boolean isUpdated = expenseService.updateExpense(expense);
        if (isUpdated) {
            return new ResponseEntity<>(expense, HttpStatus.OK); // 200 OK if update is successful.
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found if the expense doesn't exist.
        }
    }

    // Delete an expense by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        boolean isDeleted = expenseService.deleteExpense(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content if deletion is successful.
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found if the expense doesn't exist.
        }
    }
}
