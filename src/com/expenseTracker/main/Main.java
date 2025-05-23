package com.expenseTracker.main;

import java.util.Arrays;

import com.expenseTracker.Impl.TransactionPersistor;
import com.expenseTracker.Impl.TransactionRepositoryImpl;
import com.expenseTracker.Impl.UserInterfaceImpl;
import com.expenseTracker.Repository.TransactionRepository;
import com.expenseTracker.Repository.UserInterface;

public class Main {

	public static void main(String[] args) {
		   TransactionRepository repository = new TransactionRepositoryImpl(new TransactionPersistor("transactions.txt"));
	        UserInterface ui = new UserInterfaceImpl(repository, Arrays.asList("Salary", "Business", "Other"), Arrays.asList("Food", "Rent", "Travel", "Other"));
	        ui.run();
	}
}
