package com.cs225.finalproject.utils;

public class Validation {

	public Validation() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean hasValidAccountNumber(int accountNumber) {
		return Integer.toString(accountNumber).length() == 8;
	}

	public static boolean hasValidPinNumber(int acctPin) {
		return Integer.toString(acctPin).length() == 4;
	}

}
