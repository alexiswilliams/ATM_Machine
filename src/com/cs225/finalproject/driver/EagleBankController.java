package com.cs225.finalproject.driver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.cs225.finalproject.database.Account;
import com.cs225.finalproject.database.AccountHistory;
import com.cs225.finalproject.database.AccountHistoryData;
import com.cs225.finalproject.database.DatabaseException;
import com.cs225.finalproject.ui.getBalance;
import com.cs225.finalproject.utils.Constants;

public class EagleBankController {
	private ArrayList<AccountHistoryData> currentTransactions;
	private Account vault;
	private int accountNumber;
	private int accountPin;

	public EagleBankController() throws IOException {
		vault = new Account();
		currentTransactions = new ArrayList<>();
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public int getAccountBalanace() {
		AccountHistory accountVault = new AccountHistory(accountNumber);
		int currBalance = 0;
		
		for(AccountHistoryData record :accountVault.getAccountHistoryRecords()) {
			currBalance = record.getBalance();
		}
		
		for(AccountHistoryData record :currentTransactions) {
			currBalance = record.getBalance();
		}
		
		return currBalance;
	}
	
	public int deposit(int amount) {
		int currentBalance = getAccountBalanace();
		
		currentBalance = currentBalance + amount;
		
		AccountHistoryData transaction = new AccountHistoryData(accountNumber, accountPin, currentBalance, Constants.DEPOSIT_LABEL);
		
		currentTransactions.add(transaction);
		
		return currentBalance + amount;
	}
	
	public int withdraw(int amount) throws DatabaseException {
		int currentBalance = getAccountBalanace();
		
		if(amount > 1000) {
			throw new DatabaseException("Withdrawal must be no more than $1000.");
		}
		
		if((double)amount / (double)currentBalance > .7) {
			throw new DatabaseException("Must withdraw no more than 70% of current balance.");
		}
		
		currentBalance = currentBalance - amount;
		
		AccountHistoryData transaction = new AccountHistoryData(accountNumber, accountPin, currentBalance, Constants.WITHDRAWAL_LABEL);
		
		currentTransactions.add(transaction);
		
		return currentBalance;
		
	}
	
	public void createNewAccount(int acctNumber, int acctPin) throws DatabaseException, IOException {
		vault.createNewAccount(acctNumber, acctPin);
	}
	
	public void login(int acctNumber, int acctPin) throws DatabaseException, IOException {
		this.accountNumber = acctNumber;
		this.accountPin = acctPin;
		vault = new Account();
		
		Account acct = vault.getAccount(acctNumber, acctPin);
		
		if(acct == null) {
			throw new DatabaseException("Please check the " + Constants.ACCOUNT_NUMBER_LABEL + " and " + Constants.ACCOUNT_PIN_LABEL + " entered.");
		}
	}
	
	public void logout() throws IOException {
		AccountHistory accountVault = new AccountHistory(accountNumber);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate localDate = LocalDate.now();
		String currDate = dtf.format(localDate);
		PrintStream fileStream = new PrintStream(new File("receipt" + currDate + ".txt"));
		
		fileStream.println("Date:\t" + currDate);
		fileStream.println(getCurrentTransactions());
		fileStream.println(Constants.ACCOUNT_PIN_LABEL + ":\tXXXXX" + String.valueOf(getAccountNumber()).substring(5, 8));
		fileStream.println(Constants.CURRENT_BALANCE + "\t" + getAccountBalanace());
		fileStream.flush();
		fileStream.close();
		
		accountVault.updateAccountHistoryRecords(currentTransactions);
		this.accountNumber = 0;
		this.accountPin = 0;
		vault = new Account();
	}
	
	public String getCurrentTransactions() {
		String currTransactions = "";
		System.out.println(currentTransactions.size());
		
		for(AccountHistoryData record :currentTransactions) {
			currTransactions = currTransactions + record.getTransaction() + "\t" + String.valueOf(record.getBalance()) + "\n";
		}
		
		return currTransactions;
	}
	
	public void transfer(int amount, int acctNumberFrom, int acctPinFrom, int acctNumberTo, int acctPinTo) throws DatabaseException, IOException {
		Account accountFrom = vault.getAccount(acctNumberFrom, acctPinFrom);
		Account accountTo = vault.getAccount(acctNumberTo, acctPinTo);
		
		if(accountFrom == null || accountTo == null) {
			throw new DatabaseException("Please verify information entered for transfer: " + Constants.ACCOUNT_PIN_LABEL + " " + Constants.ACCOUNT_NUMBER_LABEL);
		}	
	}

}
