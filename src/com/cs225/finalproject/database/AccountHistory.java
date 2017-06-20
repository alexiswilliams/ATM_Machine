package com.cs225.finalproject.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class AccountHistory {
	/*
	 * acct###.txt
	 * The individual account files should be formatted in the following way: 
	ATM Card#<<comma>>ATM Pin#<<comma>> Balance<<comma>>Transaction 

	 */
	private int accountNumber;
	private int accountPin;
	private int balance;
	private String transaction;
	private ArrayList<AccountHistory> accountHistoryRecords;
	private static final String ACCOUNT_HISTORY_FILE = "acct{0}.txt";
	private static CSVReader reader;

	public AccountHistory(int acctNumber) {

		accountHistoryRecords = new ArrayList<>();
		String fileName = ACCOUNT_HISTORY_FILE.replace("{0}", String.valueOf(acctNumber));
		System.out.println("fileName: " + fileName);
		try {
			reader = new CSVReader(new FileReader(fileName));

			//Read all rows at once
			List<String[]> allRows = reader.readAll();

			//Read CSV line by line and use the string array as you want
			if(allRows != null && !allRows.isEmpty()) {
				for(String[] row : allRows){
					AccountHistory accountHistoryRecord = new AccountHistory(
							Integer.parseInt(row[0]), Integer.parseInt(row[1]), 
							Integer.parseInt(row[2]), row[3]);
					accountHistoryRecords.add(accountHistoryRecord);
				}	
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public AccountHistory(int acctNumber, int acctPin, int bal, String transactionType) {
		this.accountNumber = acctNumber;
		this.accountPin = acctPin;
		this.balance = bal;
		this.transaction = transactionType;
	}
	
	public ArrayList<AccountHistory> getAccountHistoryRecords() {
		return accountHistoryRecords;
	}

	/**
	 * @return the accountNumber
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the accountPin
	 */
	public int getAccountPin() {
		return accountPin;
	}

	/**
	 * @param accountPin the accountPin to set
	 */
	public void setAccountPin(int accountPin) {
		this.accountPin = accountPin;
	}

	/**
	 * @return the balance
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * @return the transaction
	 */
	public String getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
}
