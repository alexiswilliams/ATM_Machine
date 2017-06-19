/**
 * 
 */
package com.cs225.finalproject.ui;

import com.cs225.finalproject.utils.Constants;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author pariswilliams
 *
 */

public class BaseScene extends Application {
	private BorderPane borderPane;
	private MenuBar menuBar;
	private Menu fileMenu, optionsMenu, helpMenu;
	private MenuItem 
	depositMenuItem, withdrawalMenuItem, transferFundsMenuItem,
	getBalanceMenuItem, exitMenuItem, logoutMenuItem, changePinMenuItem,
	createNewAccountMenuItem, viewTransactionHistoryMenuItem, 
	contactUsMenuItem, faqMenuItem;
	private Scene scene;

	public BaseScene() {
		setupMenuBar();
	}
	
	public Scene getScene() {
		return scene;
	}
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// disable close button
		exitMenuItem.setOnAction(e -> {
			Platform.exit();
			// TODO add call to generate receipt
		});
		borderPane = new BorderPane();
		borderPane.setTop(menuBar);
		scene = new Scene(borderPane);
	}

	private void setupMenuBar() {
		// Create MenuBar
		menuBar = new MenuBar();
		// Create Menus
		fileMenu = new Menu(Constants.FILE_LABEL);
		optionsMenu = new Menu(Constants.OPTIONS_LABEL);
		helpMenu = new Menu(Constants.HELP_LABEL);
		// create Menu Items
		depositMenuItem = new MenuItem(Constants.DEPOSIT_LABEL);
		withdrawalMenuItem = new MenuItem(Constants.WITHDRAWAL_LABEL);
		transferFundsMenuItem = new MenuItem(Constants.TRANSFER_FUNDS_LABEL);
		getBalanceMenuItem = new MenuItem(Constants.GET_BALANCE_LABEL);
		exitMenuItem = new MenuItem(Constants.EXIT_LABEL);
		logoutMenuItem = new MenuItem(Constants.LOGOUT_LABEL);
		changePinMenuItem = new MenuItem(Constants.CHANGE_PIN_LABEL);
		createNewAccountMenuItem = new MenuItem(Constants.CREATE_NEW_ACCOUNT_LABEL);
		viewTransactionHistoryMenuItem = new MenuItem(Constants.VIEW_TRANSACTION_HISTORY_LABEL);
		contactUsMenuItem = new MenuItem(Constants.CONTACT_US_LABEL);
		faqMenuItem = new MenuItem(Constants.FAQ_LABEL);
		// Add menu items to respective menus
		fileMenu.getItems().addAll(depositMenuItem, withdrawalMenuItem, transferFundsMenuItem, getBalanceMenuItem, exitMenuItem);
		optionsMenu.getItems().addAll(logoutMenuItem, changePinMenuItem, createNewAccountMenuItem, viewTransactionHistoryMenuItem);
		helpMenu.getItems().addAll(contactUsMenuItem, faqMenuItem);
		// Add menus to menuBar
		menuBar.getMenus().addAll(fileMenu, optionsMenu, helpMenu);
	}


}
