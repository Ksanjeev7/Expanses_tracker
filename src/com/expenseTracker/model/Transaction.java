package com.expenseTracker.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {

	  private final String type;
	    private final String category;
	    private final double amount;
	    private final String date;
	    private final String description;
	    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    public Transaction(String type, String category, double amount, String description) {
	        this.type = type;
	        this.category = category;
	        this.amount = amount;
	        this.date = LocalDate.now().format(DATE_FORMATTER);
	        this.description = description;
	    }

	    public Transaction(String type, String category, double amount, String date, String description) {
	        this.type = type;
	        this.category = category;
	        this.amount = amount;
	        this.date = date;
	        this.description = description;
	    }

	    public String getType() {
	        return type;
	    }

	    public String getCategory() {
	        return category;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public String getDate() {
	        return date;
	    }

	    public String getDescription() {
	        return description;
	    }

	    @Override
	    public String toString() {
	        return String.format("%s,%s,%.2f,%s,%s", type, category, amount, date, description);
	    }
}
