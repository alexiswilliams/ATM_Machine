package com.cs225.finalproject.ui;

import com.cs225.finalproject.utils.Constants;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.java.swing.plaf.windows.resources.windows;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
/*
 * Need:
 *  - Title
 *  
 */
public class MainStage extends Application {

	private BorderPane borderPane;
	private static final String TITLE = "Eagle Bank";
	private MenuBar menuBar;
	private Menu fileMenu, optionsMenu, helpMenu;
	private MenuItem 
	depositMenuItem, withdrawalMenuItem, transferFundsMenuItem,
	getBalanceMenuItem, exitMenuItem, logoutMenuItem, changePinMenuItem,
	createNewAccountMenuItem, viewTransactionHistoryMenuItem, 
	contactUsMenuItem, faqMenuItem;

	private Button loginButton, createAccountButton;
	private GridPane grid;
	private Label accountNumberLabel, accountPinLabel;
	private TextField accountNumberInput, accountPinInput;

	public MainStage() {
		//		buildScene(Constants.LOGIN_LABEL);
	}

	private Scene getLoginScene() {
		loginButton = new Button(Constants.LOGIN_LABEL);
		createAccountButton = new Button(Constants.CREATE_NEW_ACCOUNT_LABEL);
		grid = new GridPane();
		// Create labels for login scene
		accountNumberLabel = new Label(Constants.ACCOUNT_NUMBER_LABEL);
		accountPinLabel = new Label(Constants.ACCOUNT_PIN_LABEL);

		// Create inputs for login scene
		accountNumberInput = new TextField();
		accountPinInput = new TextField();
		accountNumberInput.setPromptText(Constants.ACCOUNT_NUMBER_LABEL);
		accountPinInput.setPromptText(Constants.ACCOUNT_PIN_LABEL);

		grid.setPadding(new Insets(10,10,10,10));
		// set spacing between nodes
		grid.setVgap(8);
		grid.setHgap(10);

		// assign nodes to GridPane
		GridPane.setConstraints(accountNumberLabel, 0, 0);
		GridPane.setConstraints(accountNumberInput, 1, 0);
		GridPane.setConstraints(accountPinLabel, 0, 1);
		GridPane.setConstraints(accountPinInput, 1, 1);
		GridPane.setConstraints(loginButton, 0, 2);
		GridPane.setConstraints(createAccountButton, 1, 2);
		grid.getChildren().addAll(
				accountNumberLabel, accountNumberInput, accountPinLabel, accountPinInput, loginButton, createAccountButton);

		// event handler
		loginButton.setOnAction(e -> {
			buildScene(Constants.DEPOSIT_LABEL);
			updateTopBorder(menuBar);
		});
		BorderPane loginBorderPane = new BorderPane();
		loginBorderPane.setTop(getMenuBar(Constants.LOGIN_LABEL));
		loginBorderPane.setCenter(grid);
		Scene loginScene = new Scene(loginBorderPane);
		return loginScene;
	}

	private Scene getDepositScene() {

		return null;
	}

	private Scene getWithdrawalScene() {

		return null;
	}

	private Scene getTransferFundsScene() {

		return null;
	}

	private Scene getMainScene() {

		return null;
	}

	private Scene getAdditionalOptionsScene() {

		return null;
	}

	private Scene getCurrentBalanceScene() {

		return null;
	}

	private Scene getFaqScene() {

		return null;
	}

	private Scene getChangePinScene() {

		return null;
	}

	private MenuBar getMenuBarLoginScreen() {
		// Create MenuBar
		MenuBar menuBarLoginScreen = new MenuBar();
		// Create Menus
		fileMenu = new Menu(Constants.FILE_LABEL);
		optionsMenu = new Menu(Constants.OPTIONS_LABEL);
		helpMenu = new Menu(Constants.HELP_LABEL);
		// create Menu Items
		exitMenuItem = new MenuItem(Constants.EXIT_LABEL);
		contactUsMenuItem = new MenuItem(Constants.CONTACT_US_LABEL);
		faqMenuItem = new MenuItem(Constants.FAQ_LABEL);

		fileMenu.getItems().add(exitMenuItem);
		helpMenu.getItems().addAll(contactUsMenuItem, faqMenuItem);

		// Add menus to menuBar
		menuBar.getMenus().addAll(fileMenu, optionsMenu, helpMenu);

		// disable close button
		exitMenuItem.setOnAction(e -> {
			Platform.exit();
			// TODO add call to generate receipt
		});

		return menuBarLoginScreen;
	}

	private MenuBar getMenuBar(String sceneType) {
		// Create MenuBar
		menuBar = new MenuBar();
		// Create Menus
		fileMenu = new Menu(Constants.FILE_LABEL);
		optionsMenu = new Menu(Constants.OPTIONS_LABEL);
		helpMenu = new Menu(Constants.HELP_LABEL);
		// create Menu Items
		exitMenuItem = new MenuItem(Constants.EXIT_LABEL);
		contactUsMenuItem = new MenuItem(Constants.CONTACT_US_LABEL);
		faqMenuItem = new MenuItem(Constants.FAQ_LABEL);
		System.out.println(sceneType + " " + sceneType.equals(Constants.LOGIN_LABEL));
		//		if(!sceneType.equals(Constants.LOGIN_LABEL)) {
		depositMenuItem = new MenuItem(Constants.DEPOSIT_LABEL);
		withdrawalMenuItem = new MenuItem(Constants.WITHDRAWAL_LABEL);
		transferFundsMenuItem = new MenuItem(Constants.TRANSFER_FUNDS_LABEL);
		getBalanceMenuItem = new MenuItem(Constants.GET_BALANCE_LABEL);
		logoutMenuItem = new MenuItem(Constants.LOGOUT_LABEL);
		changePinMenuItem = new MenuItem(Constants.CHANGE_PIN_LABEL);
		createNewAccountMenuItem = new MenuItem(Constants.CREATE_NEW_ACCOUNT_LABEL);
		viewTransactionHistoryMenuItem = new MenuItem(Constants.VIEW_TRANSACTION_HISTORY_LABEL);

		// Add menu items to respective menus
		fileMenu.getItems().addAll(depositMenuItem, withdrawalMenuItem, transferFundsMenuItem, getBalanceMenuItem, exitMenuItem);
		optionsMenu.getItems().addAll(logoutMenuItem, changePinMenuItem, createNewAccountMenuItem, viewTransactionHistoryMenuItem);
		helpMenu.getItems().addAll(contactUsMenuItem, faqMenuItem);
		//		} 
		//		else {
		//			fileMenu.getItems().add(exitMenuItem);
		//			helpMenu.getItems().addAll(contactUsMenuItem, faqMenuItem);
		//		}

		// Add menus to menuBar
		menuBar.getMenus().addAll(fileMenu, optionsMenu, helpMenu);

		// disable close button
		exitMenuItem.setOnAction(e -> {
			Platform.exit();
			// TODO add call to generate receipt
		});

		return menuBar;
	}

	private void updateMenuBar(String sceneType) {
		// Create MenuBar
		menuBar = new MenuBar();
		// Create Menus
		fileMenu = new Menu(Constants.FILE_LABEL);
		optionsMenu = new Menu(Constants.OPTIONS_LABEL);
		helpMenu = new Menu(Constants.HELP_LABEL);
		// create Menu Items
		exitMenuItem = new MenuItem(Constants.EXIT_LABEL);
		contactUsMenuItem = new MenuItem(Constants.CONTACT_US_LABEL);
		faqMenuItem = new MenuItem(Constants.FAQ_LABEL);
		if(!sceneType.equals(Constants.LOGIN_LABEL)) {
			depositMenuItem = new MenuItem(Constants.DEPOSIT_LABEL);
			withdrawalMenuItem = new MenuItem(Constants.WITHDRAWAL_LABEL);
			transferFundsMenuItem = new MenuItem(Constants.TRANSFER_FUNDS_LABEL);
			getBalanceMenuItem = new MenuItem(Constants.GET_BALANCE_LABEL);
			logoutMenuItem = new MenuItem(Constants.LOGOUT_LABEL);
			changePinMenuItem = new MenuItem(Constants.CHANGE_PIN_LABEL);
			createNewAccountMenuItem = new MenuItem(Constants.CREATE_NEW_ACCOUNT_LABEL);
			viewTransactionHistoryMenuItem = new MenuItem(Constants.VIEW_TRANSACTION_HISTORY_LABEL);

			// Add menu items to respective menus
			fileMenu.getItems().addAll(depositMenuItem, withdrawalMenuItem, transferFundsMenuItem, getBalanceMenuItem, exitMenuItem);
			optionsMenu.getItems().addAll(logoutMenuItem, changePinMenuItem, createNewAccountMenuItem, viewTransactionHistoryMenuItem);
			helpMenu.getItems().addAll(contactUsMenuItem, faqMenuItem);
		} else {
			fileMenu.getItems().add(exitMenuItem);
			helpMenu.getItems().addAll(contactUsMenuItem, faqMenuItem);
		}

		// Add menus to menuBar
		menuBar.getMenus().addAll(fileMenu, optionsMenu, helpMenu);

		// disable close button
		exitMenuItem.setOnAction(e -> {
			Platform.exit();
			// TODO add call to generate receipt
		});
	}

	private void buildScene(String sceneType) { 
		updateMenuBar(sceneType);

		if(sceneType.equals(Constants.DEPOSIT_LABEL)) {

		} else if(sceneType.equals(Constants.WITHDRAWAL_LABEL)) {

		} else if (sceneType.equals(Constants.LOGIN_LABEL)){
			loginButton = new Button(Constants.LOGIN_LABEL);
			createAccountButton = new Button(Constants.CREATE_NEW_ACCOUNT_LABEL);
			grid = new GridPane();
			// Create labels for login scene
			accountNumberLabel = new Label(Constants.ACCOUNT_NUMBER_LABEL);
			accountPinLabel = new Label(Constants.ACCOUNT_PIN_LABEL);

			// Create inputs for login scene
			accountNumberInput = new TextField();
			accountPinInput = new TextField();
			accountNumberInput.setPromptText(Constants.ACCOUNT_NUMBER_LABEL);
			accountPinInput.setPromptText(Constants.ACCOUNT_PIN_LABEL);

			grid.setPadding(new Insets(10,10,10,10));
			// set spacing between nodes
			grid.setVgap(8);
			grid.setHgap(10);

			// assign nodes to GridPane
			GridPane.setConstraints(accountNumberLabel, 0, 0);
			GridPane.setConstraints(accountNumberInput, 1, 0);
			GridPane.setConstraints(accountPinLabel, 0, 1);
			GridPane.setConstraints(accountPinInput, 1, 1);
			GridPane.setConstraints(loginButton, 0, 2);
			GridPane.setConstraints(createAccountButton, 1, 2);
			grid.getChildren().addAll(
					accountNumberLabel, accountNumberInput, accountPinLabel, accountPinInput, loginButton, createAccountButton);

			// event handler
			loginButton.setOnAction(e -> {
				buildScene(Constants.DEPOSIT_LABEL);
				updateTopBorder(menuBar);
			});
		}
	}

	@Override
	public void start(Stage mainStage) {
		// TODO Auto-generated method stub
		mainStage.setTitle(TITLE);
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		mainStage.setResizable(false);
		mainStage.setOnCloseRequest(e -> e.consume());

		Scene scene = getLoginScene();
		// Add the menubar and shapes 
		//		borderPane.setTop(menuBar);
		mainStage.setWidth(primaryScreenBounds.getWidth() * (3.0/4.0));
		mainStage.setHeight(primaryScreenBounds.getHeight() * (3.0/4.0));
		mainStage.setScene(scene);
		mainStage.show();
	}

	private void updateTopBorder(MenuBar menuBar) {
		borderPane.setTop(menuBar);
	}
}
