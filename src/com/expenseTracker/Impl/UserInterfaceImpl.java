package com.expenseTracker.Impl;

import java.util.List;
import java.util.Scanner;

import com.expenseTracker.Repository.TransactionRepository;
import com.expenseTracker.Repository.UserInterface;
import com.expenseTracker.model.Transaction;

public class UserInterfaceImpl implements UserInterface {
	 private final TransactionRepository repository;
	    private final List<String> incomeCategories;
	    private final List<String> expenseCategories;
	    private static  Scanner scanner = new Scanner(System.in);
	    
	    
	public UserInterfaceImpl(TransactionRepository repository, List<String> incomeCategories,
				List<String> expenseCategories) {
			this.repository = repository;
			this.incomeCategories = incomeCategories;
			this.expenseCategories = expenseCategories;
		}


	  @Override
	    public void run() {
	        while (true) {
	            System.out.println("\n1. Add Transaction\n2. View Monthly Summary\n3. Save and Exit");
	            System.out.print("Choose an option: ");
	            String choice = scanner.nextLine().trim();
	            switch (choice) {
	                case "1":
	                    addTransaction();
	                    break;
	                case "2":
	                    viewMonthlySummary();
	                    break;
	                case "3":
	                    System.out.println("Exiting...");
	                    return;
	                default:
	                    System.out.println("Invalid option.");
	            }
	        }
	    }

	    private void addTransaction() {
	        System.out.print("Enter type (Income/Expense): ");
	        String type = scanner.nextLine().trim().toLowerCase();
	        if (!type.equals("income") && !type.equals("expense")) {
	            System.out.println("Invalid type. Use 'Income' or 'Expense'.");
	            return;
	        }

	        List<String> categories = type.equals("income") ? incomeCategories : expenseCategories;
	        System.out.println("Available categories: " + categories);
	        System.out.print("Enter category: ");
	        String category = scanner.nextLine().trim();
	        if (!categories.contains(category)) {
	            System.out.println("Invalid category. Using 'Other'.");
	            category = "Other";
	        }

	        System.out.print("Enter amount: ");
	        double amount;
	        try {
	            amount = Double.parseDouble(scanner.nextLine().trim());
	            if (amount < 0) throw new NumberFormatException();
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid amount. Transaction cancelled.");
	            return;
	        }

	        System.out.print("Enter description: ");
	        String description = scanner.nextLine().trim();

	        repository.addTransaction(new Transaction(type, category, amount, description));
	        System.out.println("Transaction added.");
	    }

	    private void viewMonthlySummary() {
	        System.out.print("Enter year (YYYY): ");
	        int year;
	        try {
	            year = Integer.parseInt(scanner.nextLine().trim());
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid year.");
	            return;
	        }
	        System.out.print("Enter month (MM): ");
	        int month;
	        try {
	            month = Integer.parseInt(scanner.nextLine().trim());
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid month.");
	            return;
	        }

	        String monthYear = String.format("%d-%02d", year, month);
	        System.out.println("\nMonthly Summary for " + monthYear);
	        System.out.println("Income:");
	        repository.getIncomeByCategory(monthYear)
	                .forEach((cat, amt) -> System.out.printf("  %s: Rs.%.2f\n", cat, amt));
	        System.out.printf("Total Income: Rs.%.2f\n", repository.getTotalIncome(monthYear));
	        System.out.println("Expenses:");
	        repository.getExpensesByCategory(monthYear)
	                .forEach((cat, amt) -> System.out.printf("  %s: $%.2f\n", cat, amt));
	        System.out.printf("Total Expenses: Rs.%.2f\n", repository.getTotalExpenses(monthYear));
	        System.out.printf("Net Balance: Rs.%.2f\n", repository.getTotalIncome(monthYear) - repository.getTotalExpenses(monthYear));
	    }

}
