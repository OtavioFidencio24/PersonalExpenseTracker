package com.CreateUrlShortner.utils;

import com.CreateUrlShortner.model.Expense;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component // Marks this as a Spring-managed component.
public class ExpenseDataLoader {
    private static List<Expense> expenses; // Stores the loaded expenses.

    @PostConstruct // Runs this method after the bean is initialized.
    public void init() {
        ObjectMapper objectMapper = new ObjectMapper(); // Jackson's object mapper for JSON parsing.

        try {
            InputStream inputStream = getClass().getClassLoader()
                    .getResourceAsStream("expenses.json"); // Loads JSON file from resources folder.

            if (inputStream != null) {
                expenses = objectMapper.readValue(inputStream,
                        new TypeReference<List<Expense>>() {}); // Deserializes JSON into a List<Expense>.
            }

        } catch (IOException e) {
            throw new RuntimeException("Error loading JSON file: " + e);
        }
    }

    public static List<Expense> getExpenses() {
        return expenses; // Returns the loaded list of expenses.
    }
}
