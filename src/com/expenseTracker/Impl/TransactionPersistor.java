package com.expenseTracker.Impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.expenseTracker.model.Transaction;

public class TransactionPersistor implements com.expenseTracker.Repository.TransactionPersistor {

	private final String fileName ;
	
	public TransactionPersistor(String fileName) {
	this.fileName = fileName;
	}
	
	@Override
	public List<Transaction> load() {
		List<Transaction> transactions = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(",");
	            if (parts.length == 5) {
	                String type = parts[0].trim();
	                String category = parts[1].trim();
	                double amount = Double.parseDouble(parts[2].trim());
	                String date = parts[3].trim();
	                String description = parts[4].trim();
	                transactions.add(new Transaction(type, category, amount, date, description));
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("No existing transaction file found. Starting fresh.");
	    }
//	    System.out.println("Loaded " + transactions.size() + " transactions.");
	    return transactions;
	}

	@Override
	public void save(List<Transaction> transactions) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Transaction t : transactions) {
                writer.write(t.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }
}
