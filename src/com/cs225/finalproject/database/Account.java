/**
 * 
 */
package com.cs225.finalproject.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;

/**
 * @author pariswilliams
 *
 */
public class Account {
	private static final String EAGLE_BANK_ACCOUNTS_FILE = "EagleBank.txt";
	private static CSVReader reader;
	private double accountNumber;
	private int accountPin;
	private int numberOfTransactions;
	private ArrayList<Account> accounts;

	public Account() throws IOException {
		reader = new CSVReader(new FileReader(EAGLE_BANK_ACCOUNTS_FILE));

		//Read all rows at once
		List<String[]> allRows = reader.readAll();

		//Read CSV line by line and use the string array as you want
		if(allRows != null && !allRows.isEmpty()) {
			accounts = new ArrayList<>();
			
			for(String[] row : allRows){
				Account account = new Account(Double.parseDouble(row[0]), Integer.parseInt(row[1]), Integer.parseInt(row[2]));
				accounts.add(account);
			}	
		}
	}
	
	public void createNewAccount(double acctNumber, int acctPin) {
		if(Double.toString(acctNumber).length() != 16) {
			
		}
		if(Integer.toString(acctPin).length() != 4) {
			
		}
		for(Account acct :accounts) {
			if(acct.getAccountNumber() == acctNumber) {
				break;
				//TODO create an exception here for matching account numbers
			}
		}
		
		//TODO create method to add account to accounts and write to file
	}
	
	public Account getAccount(double acctNumber, int acctPin) {
		for(Account acct :accounts) {
			if(acct.getAccountNumber() == acctNumber && acct.getAccountPin() == acctPin) {
				return acct;
			}
		}
		
		return null;
	}
	
	public Account(double acctNumber, int acctPin, int numTransactions) {
		this.accountNumber = acctNumber;
		this.accountPin = acctPin;
		this.numberOfTransactions = numTransactions;
	}

	/**
	 * @return the accounts
	 */
	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return the accountNumber
	 */
	public double getAccountNumber() {
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
	 * @return the numberOfTransactions
	 */
	public int getNumberOfTransactions() {
		return numberOfTransactions;
	}

	/**
	 * @param numberOfTransactions the numberOfTransactions to set
	 */
	public void setNumberOfTransactions(int numberOfTransactions) {
		this.numberOfTransactions = numberOfTransactions;
	}
}
