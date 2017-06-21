package com.cs225.finalproject.database;

public class AccountHistoryData {
	private int accountNumber;
	private int accountPin;
	private int balance;
	private String transaction;

	public AccountHistoryData(int acctNumber, int acctPin, int bal, String transactionType) {
		this.setAccountNumber(acctNumber);
		this.setAccountPin(acctPin);
		this.setBalance(bal);
		this.setTransaction(transactionType);
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAccountPin() {
		return accountPin;
	}

	public void setAccountPin(int accountPin) {
		this.accountPin = accountPin;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
}