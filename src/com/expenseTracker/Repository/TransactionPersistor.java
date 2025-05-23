package com.expenseTracker.Repository;

import java.util.List;

import com.expenseTracker.model.Transaction;

public interface TransactionPersistor {

	List<Transaction> load();
	void save(List<Transaction> transactions);
}
