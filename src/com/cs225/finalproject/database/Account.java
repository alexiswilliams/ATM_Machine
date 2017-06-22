/**
 * 
 */
package com.cs225.finalproject.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cs225.finalproject.utils.Constants;
import com.cs225.finalproject.utils.Validation;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * @author pariswilliams
 *
 */
public class Account {
	private static final String EAGLE_BANK_ACCOUNTS_FILE = "EagleBank.txt";
	private static CSVReader reader;
	private static CSVWriter writer;
	private int accountNumber;
	private int accountPin;
	private int numberOfTransactions;
	private ArrayList<Account> accounts;

	public Account() throws IOException {
		reader = new CSVReader(new FileReader(EAGLE_BANK_ACCOUNTS_FILE));
		accounts = new ArrayList<>();
		
		//Read all rows at once
		List<String[]> allRows = reader.readAll();

		//Read CSV line by line and use the string array as you want
		if(allRows != null && !allRows.isEmpty()) {

			for(String[] row : allRows){
				Account account = new Account(Integer.parseInt(row[0]), Integer.parseInt(row[1]), Integer.parseInt(row[2]));
				accounts.add(account);
			}	
		}
		
		reader.close();
	}
	
	public void updateAccountPin(int acctNumberCurr, int acctPinCurr, int acctPinNew) throws DatabaseException, IOException {
		for(Account acct :accounts) {
			if(acct.getAccountNumber() == acctNumberCurr && acct.getAccountPin() != acctPinCurr) {
				throw new DatabaseException(Constants.ACCOUNT_PIN_LABEL + " does not match the current PIN for your account.");
			}
		}
		
		if(acctPinCurr == acctPinNew) {
			throw new DatabaseException(Constants.ACCOUNT_PIN_LABEL + " values match.");
		}

		if(!Validation.hasValidPinNumber(acctPinNew)) {
			throw new DatabaseException(Constants.ACCOUNT_PIN_LABEL + " must be 4 digits long");
		}
		
		writer = new CSVWriter(new FileWriter(EAGLE_BANK_ACCOUNTS_FILE));
		int acctNumber, acctPin, numberTransactions;
		
		for(Account acct :accounts) {
			acctNumber = acct.getAccountNumber();
			acctPin = acct.getAccountPin();
			numberTransactions = acct.getNumberOfTransactions();
			
			if(acct.getAccountNumber() == acctNumberCurr) {
				acctPin = acctPinNew;
			}
			
			String [] record = String.valueOf(acctNumber).concat(",").concat(String.valueOf(acctPin)).concat(",").concat(String.valueOf(numberTransactions)).split(",");

			writer.writeNext(record);
		}
		
		writer.close();
	}

	public void createNewAccount(int acctNumber, int acctPin) throws DatabaseException, IOException {
		if(!Validation.hasValidAccountNumber(acctNumber)) {
			throw new DatabaseException(Constants.ACCOUNT_NUMBER_LABEL + " must be 8 digits long.");
		}
		if(!Validation.hasValidPinNumber(acctPin)) {
			throw new DatabaseException(Constants.ACCOUNT_PIN_LABEL + " must be 4 digits long");
		}
		for(Account acct :accounts) {
			if(acct.getAccountNumber() == acctNumber) {
				throw new DatabaseException(Constants.ACCOUNT_NUMBER_LABEL + " must be unique");
			}
		}
		System.out.println("Creating new account: " + acctNumber + " : " + acctPin);
		writer = new CSVWriter(new FileWriter(EAGLE_BANK_ACCOUNTS_FILE, true));

		String [] record = String.valueOf(acctNumber).concat(",").concat(String.valueOf(acctPin)).concat(",0").split(",");

		writer.writeNext(record);

		writer.close();
		
		AccountHistory accountHistoryFile = new AccountHistory();
		accountHistoryFile.createAccountHistoryFile(acctNumber);
	}

	public Account getAccount(int acctNumber, int acctPin) {
		for(Account acct :accounts) {
			System.out.println(acct.getAccountNumber() + ":" + acct.getAccountPin());
			if(acct.getAccountNumber() == acctNumber && acct.getAccountPin() == acctPin) {
				return acct;
			}
		}

		return null;
	}

	public Account(int acctNumber, int acctPin, int numTransactions) {
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
