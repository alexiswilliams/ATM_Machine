package com.cs225.finalproject.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class AccountHistory {
	private AccountHistoryData data;
	private ArrayList<AccountHistoryData> accountHistoryRecords;
	private CSVWriter writer;
	private static final String ACCOUNT_HISTORY_FILE = "acct{0}.txt";
	private static CSVReader reader;
	private static String fileName;

	private String getFileName(int acctNumber) {
		return ACCOUNT_HISTORY_FILE.replace("{0}", String.valueOf(acctNumber));
	}

	public void createAccountHistoryFile(int acctNumber) throws IOException {
		writer = new CSVWriter(new FileWriter(getFileName(acctNumber)));

		writer.writeNext(null);

		writer.close();	
	}

	public AccountHistory() {}

	public AccountHistory(int acctNumber) {
		accountHistoryRecords = new ArrayList<>();

		fileName = getFileName(acctNumber);

		System.out.println("fileName: " + fileName);

		try {
			reader = new CSVReader(new FileReader(fileName));

			//Read all rows at once
			List<String[]> allRows = reader.readAll();

			//Read CSV line by line and use the string array as you want
			if(allRows != null && !allRows.isEmpty()) {
				for(String[] row : allRows){
					AccountHistoryData accountHistoryRecord = new AccountHistoryData(
							Integer.parseInt(row[0]), Integer.parseInt(row[1]), 
							Integer.parseInt(row[2]), row[3]);
					accountHistoryRecords.add(accountHistoryRecord);
				}	
			}

			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateAccountHistoryRecords(ArrayList<AccountHistoryData> newAccountHistoryRecords) throws IOException {
		writer = new CSVWriter(new FileWriter(fileName, true));

		for(AccountHistoryData newAccountHistoryRecord :newAccountHistoryRecords) {
			String [] record = String.valueOf(newAccountHistoryRecord.getAccountNumber()).
					concat(",").
					concat(String.valueOf(newAccountHistoryRecord.getAccountPin())).
					concat(",").
					concat(String.valueOf(newAccountHistoryRecord.getBalance())).
					concat(",").concat(newAccountHistoryRecord.getTransaction()).
					split(",");

			writer.writeNext(record);
		}

		writer.close();
	}

	public ArrayList<AccountHistoryData> getAccountHistoryRecords() {
		return accountHistoryRecords;
	}

	/**
	 * @return the accountNumber
	 */
	public int getAccountNumber() {
		return data.getAccountNumber();
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(int accountNumber) {
		this.data.setAccountNumber(accountNumber);
	}

	/**
	 * @return the accountPin
	 */
	public int getAccountPin() {
		return data.getAccountPin();
	}

	/**
	 * @param accountPin the accountPin to set
	 */
	public void setAccountPin(int accountPin) {
		this.data.setAccountPin(accountPin);
	}

	/**
	 * @return the balance
	 */
	public int getBalance() {
		return data.getBalance();
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(int balance) {
		this.data.setBalance(balance);
	}

	/**
	 * @return the transaction
	 */
	public String getTransaction() {
		return data.getTransaction();
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(String transaction) {
		this.data.setTransaction(transaction);
	}
}
