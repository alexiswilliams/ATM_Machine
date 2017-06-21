package com.cs225.finalproject.tests;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.cs225.finalproject.database.Account;
import com.cs225.finalproject.database.AccountHistory;
import com.cs225.finalproject.database.DatabaseException;

public class Tests {

	private static final int ACCOUNT_NUMBER_BAD = 81111111;
	private static final int ACCOUNT_NUMBER_GOOD = 11111111;
	private static final int PIN_GOOD = 1234;
	private static final int PIN_BAD = 123;

	private static int getRandomAccountNumber() {
		return ThreadLocalRandom.current().nextInt(11111111, 99999999 + 1);
	}
	
	public static void main(String[] args) throws IOException {
		
		AccountHistory accountHistory = new AccountHistory(getRandomAccountNumber());
		ArrayList<AccountHistory> records = accountHistory.getAccountHistoryRecords();
		System.out.println("number of records for account size: " + records.size());
		
		accountHistory = new AccountHistory(ACCOUNT_NUMBER_GOOD);
		records = accountHistory.getAccountHistoryRecords();
		System.out.println("number of records for account: " + ACCOUNT_NUMBER_GOOD + " size: " + records.size());
		
		Account account = new Account();
		try {
			account.createNewAccount(getRandomAccountNumber(), PIN_BAD);
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			account.createNewAccount(getRandomAccountNumber(), PIN_GOOD);
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}
}
