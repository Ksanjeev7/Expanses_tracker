package com.expenseTracker.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expenseTracker.Repository.TransactionRepository;
import com.expenseTracker.model.Transaction;

public class TransactionRepositoryImpl implements TransactionRepository {
	
	private final List<Transaction> transactions = new java.util.ArrayList<>();
	private final TransactionPersistor persistor;

	public TransactionRepositoryImpl(TransactionPersistor persistor) {
        this.persistor = persistor;
        loadTransactions();
    }

    private void loadTransactions() {
        transactions.addAll(persistor.load());
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        persistor.save(transactions);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }

    @Override
    public Map<String, Double> getIncomeByCategory(String monthYear) {
        Map<String, Double> incomeByCategory = new HashMap<>();
        for (Transaction t : transactions) {
            if (t.getDate().startsWith(monthYear) && t.getType().equals("income")) {
                incomeByCategory.merge(t.getCategory(), t.getAmount(), Double::sum);
            }
        }
        return incomeByCategory;
    }

    @Override
    public Map<String, Double> getExpensesByCategory(String monthYear) {
        Map<String, Double> expenseByCategory = new HashMap<>();
        for (Transaction t : transactions) {
            if (t.getDate().startsWith(monthYear) && t.getType().equals("expense")) {
                expenseByCategory.merge(t.getCategory(), t.getAmount(), Double::sum);
            }
        }
        return expenseByCategory;
    }

    @Override
    public double getTotalIncome(String monthYear) {
        return transactions.stream()
                .filter(t -> t.getDate().startsWith(monthYear) && t.getType().equals("income"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    @Override
    public double getTotalExpenses(String monthYear) {
        return transactions.stream()
                .filter(t -> t.getDate().startsWith(monthYear) && t.getType().equals("expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

}
