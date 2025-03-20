package com.CreateUrlShortner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity // Marks this class as a JPA entity, meaning it will be mapped to a database table.
@Data // Lombok annotation to automatically generate getters, setters, toString, equals, and hashCode.
public class Expense {

    @Id // Specifies the primary key of the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Automatically generates the ID using the Identity strategy,
    // commonly used for auto-incremented fields in databases.

    private Long id;

    private int expenseType; // expense == 0 (expense), income == 1 (income).

    private String date;

    private double amount;

    private String category;

    private String account;

    private String note;
}
