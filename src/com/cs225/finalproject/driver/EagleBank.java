package com.cs225.finalproject.driver;

import java.io.IOException;

import com.cs225.finalproject.database.Account;
import com.cs225.finalproject.ui.MainStage;

public class EagleBank extends MainStage{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MainStage.launch(args);
		Account bankVault = new Account();
		
		Account acct = bankVault.getAccount(1111222233336666L, 1234);
		
		System.out.println(acct.toString());
	}

}
