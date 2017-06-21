package com.cs225.finalproject.driver;

import java.io.IOException;
import java.util.ArrayList;

import com.cs225.finalproject.database.Account;
import com.cs225.finalproject.database.AccountHistory;
import com.cs225.finalproject.database.AccountHistoryData;
import com.cs225.finalproject.database.DatabaseException;
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
		
		if(amount >= 1000) {
			throw new DatabaseException("Withdrawal must be less than $1000.");
		}
		
		if((double)amount / (double)currentBalance >= .7) {
			throw new DatabaseException("Must withdraw less than 70% of current balance.");
		}
		
		currentBalance = currentBalance - amount;
		
		AccountHistoryData transaction = new AccountHistoryData(accountNumber, accountPin, currentBalance, Constants.WITHDRAWAL_LABEL);
		
		currentTransactions.add(transaction);
		
		return currentBalance;
		
	}
	
	public void createNewAccount(int acctNumber, int acctPin) throws DatabaseException, IOException {
		vault.createNewAccount(acctNumber, acctPin);
	}
	
	public void login(int acctNumber, int acctPin) throws DatabaseException {
		this.accountNumber = acctNumber;
		this.accountPin = acctPin;
		
		Account acct = vault.getAccount(acctNumber, acctPin);
		
		if(acct == null) {
			throw new DatabaseException("Please check the " + Constants.ACCOUNT_NUMBER_LABEL + " and " + Constants.ACCOUNT_PIN_LABEL + " entered.");
		}
	}
	
	public void logout() throws IOException {
		AccountHistory accountVault = new AccountHistory(accountNumber);
		accountVault.updateAccountHistoryRecords(currentTransactions);
		this.accountNumber = 0;
		this.accountPin = 0;
	}
	
	public void transfer(int amount, int acctNumberFrom, int acctPinFrom, int acctNumberTo, int acctPinTo) throws DatabaseException, IOException {
		Account accountFrom = vault.getAccount(acctNumberFrom, acctPinFrom);
		Account accountTo = vault.getAccount(acctNumberTo, acctPinTo);
		
		if(accountFrom == null || accountTo == null) {
			throw new DatabaseException("Please verify information entered for transfer: " + Constants.ACCOUNT_PIN_LABEL + " " + Constants.ACCOUNT_NUMBER_LABEL);
		}	
	}

}
