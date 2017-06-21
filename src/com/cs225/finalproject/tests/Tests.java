package com.cs225.finalproject.tests;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.cs225.finalproject.database.Account;
import com.cs225.finalproject.database.AccountHistory;
import com.cs225.finalproject.database.AccountHistoryData;
import com.cs225.finalproject.database.DatabaseException;
import com.cs225.finalproject.driver.EagleBankController;

public class Tests {

	private static final int ACCOUNT_NUMBER_BAD = 81111111;
	private static final int ACCOUNT_NUMBER_GOOD = 11111111;
	private static final int PIN_GOOD = 1234;
	private static final int PIN_BAD = 123;

	private static int getRandomAccountNumber() {
		return ThreadLocalRandom.current().nextInt(11111111, 99999999 + 1);
	}
	
	public static void main(String[] args) throws IOException, DatabaseException {
		// Test with random account number
		int accountNumber = getRandomAccountNumber();
		EagleBankController controller = new EagleBankController();
		try {
			controller.login(accountNumber, PIN_GOOD);
		} catch (Exception e) {
			// TODO: handle exception
			controller.createNewAccount(accountNumber, PIN_GOOD);
		}
		controller.deposit(100);
		controller.withdraw(10);
		controller.logout();
		
//		controller = new EagleBankController(acctNumber, acctPin)
		
//		AccountHistory accountHistory = new AccountHistory(getRandomAccountNumber());
//		ArrayList<AccountHistoryData> records = accountHistory.getAccountHistoryRecords();
//		System.out.println("number of records for account size: " + records.size());
//		
//		accountHistory = new AccountHistory(ACCOUNT_NUMBER_GOOD);
//		records = accountHistory.getAccountHistoryRecords();
//		System.out.println("number of records for account: " + ACCOUNT_NUMBER_GOOD + " size: " + records.size());
//		
//		Account account = new Account();
//		try {
//			account.createNewAccount(getRandomAccountNumber(), PIN_BAD);
//		} catch (DatabaseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			account.createNewAccount(getRandomAccountNumber(), PIN_GOOD);
//		} catch (DatabaseException e) {
//			e.printStackTrace();
//		}
	}
}
