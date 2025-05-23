package com.expenseTracker.Repository;

import java.util.List;
import java.util.Map;

import com.expenseTracker.model.Transaction;

public interface TransactionRepository {

	   void addTransaction(Transaction transaction);
	    List<Transaction> getAllTransactions();
	    Map<String, Double> getIncomeByCategory(String monthYear);
	    Map<String, Double> getExpensesByCategory(String monthYear);
	    double getTotalIncome(String monthYear);
	    double getTotalExpenses(String monthYear);
}
