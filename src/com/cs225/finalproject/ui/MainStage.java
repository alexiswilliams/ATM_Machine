package com.cs225.finalproject.ui;

import java.io.IOException;

import com.cs225.finalproject.database.DatabaseException;
import com.cs225.finalproject.driver.EagleBankController;
import com.cs225.finalproject.utils.Constants;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
/*
 * Need:
 *  - Title
 *  
 */
//@SuppressWarnings("unused")
public class MainStage extends Application {

	//	private BorderPane borderPane;
	private Scene
	loginScene, mainMenuScene, depositScene, transferFundsScene,
	changePinScene, createNewAccount, withdrawalScene, faqScene, mainScene;
	
	private EagleBankController controller;

	public MainStage() throws IOException {
		//		buildScene(Constants.LOGIN_LABEL);
		controller = new EagleBankController();
	}

	private GridPane getGridPane() {
		GridPane grid = new GridPane();

		grid.setPadding(new Insets(10,10,10,10));
		// set spacing between nodes
		grid.setVgap(8);
		grid.setHgap(10);

		return grid;
	}

	private Button createButton(String label, String help) {
		Button button = new Button(label);
		button.setTooltip(new Tooltip(help));
		return button;
	}

	private TextField createTextField(String prompt) {
		TextField field = new TextField();
		if(prompt != null && !prompt.isEmpty()) {			
			field.setPromptText(prompt);
		}

		return field;
	}

	private Scene getLoginScene(Stage mainStage) {
		Label accountNumberLabel, accountPinLabel;

		Button loginButton = createButton(Constants.LOGIN_LABEL, Constants.LOGIN_HELP);
		Button createAccountButton = createButton(Constants.CREATE_NEW_ACCOUNT_LABEL, Constants.CREATE_ACCOUNT_HELP);

		// Create labels for login scene
		accountNumberLabel = new Label(Constants.ACCOUNT_NUMBER_LABEL);
		accountPinLabel = new Label(Constants.ACCOUNT_PIN_LABEL);

		// Create inputs for login scene
		TextField accountNumberInput = createTextField(Constants.ACCOUNT_NUMBER_LABEL);
		TextField accountPinInput = createTextField(Constants.ACCOUNT_PIN_LABEL);

		GridPane grid = getGridPane();

		// assign nodes to GridPane
		GridPane.setConstraints(accountNumberLabel, 0, 0);
		GridPane.setConstraints(accountNumberInput, 1, 0);
		GridPane.setConstraints(accountPinLabel, 0, 1);
		GridPane.setConstraints(accountPinInput, 1, 1);
		GridPane.setConstraints(loginButton, 0, 2);
		GridPane.setConstraints(createAccountButton, 1, 2);

		grid.getChildren().addAll(
				accountNumberLabel, accountNumberInput, accountPinLabel,
				accountPinInput, loginButton, createAccountButton);

		// event handler
		loginButton.setOnAction(e -> {
			try {
				controller.login(Integer.valueOf(accountNumberInput.getText()), Integer.valueOf(accountPinInput.getText()));
				//			TODO buildScene(Constants.DEPOSIT_LABEL);
				//			TODO updateTopBorder(menuBar);
				mainStage.setScene(getMainMenuScene(mainStage));
			} catch (NumberFormatException | DatabaseException e1) {
				ErrorMessage.display(e1.getMessage());
				// TODO 1. open error alert box with message from e1.getMessage();
				e1.printStackTrace();
				System.out.println("errormessage: " + e1.getMessage());
				System.out.println(Integer.valueOf(accountNumberInput.getText()));
				System.out.println(Integer.valueOf(accountPinInput.getText()));
			}
		});

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(getMenuBarLoginScreen(mainStage));
		borderPane.setCenter(grid);

		Scene scene = new Scene(borderPane);

		return scene;
	}
	/**
	 * depositScene
		description: Used to get the user input for deposit for their account
	 * @param mainStage 
	 * @return
	 */

	private Scene getMainMenuScene(Stage mainStage) {
		Label mainMenuLabel, accountNumberLabel,
		currentBalanceLabel, currentTransactionHistory;
		Button
		changePinButton, createNewAccount, logOutButton,
		depositButton, witdrawalButton, transferFundsButton;
		VBox optionButtons = new VBox(10);
		VBox menuButtons = new VBox(20);
		VBox statusBar = new VBox();
		BorderPane mainMenuLayout = new BorderPane();
		
		// initializing Labels
		mainMenuLabel = new Label(Constants.MAIN_MENU_LABEL);
		// TODO insert stub method to add user accountNumber
		accountNumberLabel = new Label("Account Number: " + controller.getAccountNumber());
		// TODO insert stub method to add currentBalance
		currentBalanceLabel = new Label("$ " + controller.getAccountBalanace());
		// TODO insert stub method to add getTransactionHistory(history);
		currentTransactionHistory = new Label();
		
		// initializing Buttons
		changePinButton = createButton(Constants.CHANGE_PIN_LABEL, Constants.CHANGE_PIN_HELP);
		createNewAccount = createButton(Constants.CREATE_NEW_ACCOUNT_LABEL, Constants.CREATE_ACCOUNT_HELP);
		logOutButton = new Button(Constants.LOGOUT_LABEL);
		depositButton = createButton(Constants.DEPOSIT_LABEL, Constants.DEPOSIT_HELP);
		witdrawalButton = createButton(Constants.WITHDRAWAL_LABEL, Constants.WITHDRAWAL_HELP);
		transferFundsButton = createButton(Constants.TRANSFER_FUNDS_LABEL, Constants.TRANSFER_FUNDS_HELP);
		
		// create event handlers
		changePinButton.setOnAction(changePin -> mainStage.setScene(changePinScene));
		depositButton.setOnAction(deposit -> mainStage.setScene(getDepositScene(mainStage)));
		logOutButton.setOnAction(e -> {
			try {
				controller.logout();

				mainStage.setScene(getLoginScene(mainStage));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				ErrorMessage.display(e1.getMessage());
			}
		});
//		createNewAccount.setOnAction(createNewAccount -> mainStage.setScene());
		
		// assigning buttons to VBox panes
		optionButtons.getChildren().addAll(changePinButton, createNewAccount, logOutButton);
		menuButtons.getChildren().addAll(mainMenuLabel, depositButton, witdrawalButton, transferFundsButton);
		statusBar.getChildren().addAll(accountNumberLabel, currentBalanceLabel, currentTransactionHistory);
		
		// align VBox in the center of their locations
		optionButtons.setAlignment(Pos.CENTER);
		menuButtons.setAlignment(Pos.CENTER);
		statusBar.setAlignment(Pos.TOP_LEFT);
		
		// embedding VBox panes into BorderPane
		mainMenuLayout.setTop(getMenuBarLoggedIn(mainStage));
		mainMenuLayout.setLeft(optionButtons);
		mainMenuLayout.setCenter(menuButtons);
		mainMenuLayout.setRight(statusBar);
		
		Scene scene = new Scene(mainMenuLayout);
		
		return scene;
	}
	
	private Scene getDepositScene(Stage mainStage) {
		Button confirmButton, cancelButton;
		Label depositAmountLabel;
		TextField depositAmountInput;

		confirmButton = new Button(Constants.CONFIRM_LABEL);
		cancelButton = new Button(Constants.CANCEL_LABEL);

		// Create labels for login scene
		depositAmountLabel = new Label(Constants.DEPOSIT_AMOUNT_LABEL);

		// Create inputs for login scene
		depositAmountInput = new TextField();

		GridPane grid = getGridPane();

		// assign nodes to GridPane
		GridPane.setConstraints(depositAmountLabel, 0, 0);
		GridPane.setConstraints(depositAmountInput, 1, 0);
		GridPane.setConstraints(cancelButton, 0, 1);
		GridPane.setConstraints(confirmButton, 1, 1);

		grid.getChildren().addAll(
				depositAmountLabel, depositAmountInput, confirmButton, cancelButton);

		// event handler
		confirmButton.setOnAction(e -> {
			//			buildScene(Constants.DEPOSIT_LABEL);
			//			updateTopBorder(menuBar);
			controller.deposit(Integer.valueOf(depositAmountInput.getText()));
			mainStage.setScene(getMainMenuScene(mainStage));
		});
		
		cancelButton.setOnAction(cancel -> mainStage.setScene(getMainMenuScene(mainStage)));

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(getMenuBarLoggedIn(mainStage));
		borderPane.setCenter(grid);

		Scene scene = new Scene(borderPane);

		return scene;
	}
	/*
	 * withdrawalScene
description:
Used to get the user input for withdrawal for their account
nodes:
buttons:


	 */
	private Scene getWithdrawalScene(Stage mainStage) {

		Button confirmButton, cancelButton, twentyButton, fourtyButton, 
		sixtyButton, eightyButton, oneHundredButton, twoHundredButton;
		Label withdrawalAmountLabel;
		TextField withdrawalAmountInput;

		confirmButton = new Button(Constants.CONFIRM_LABEL);
		cancelButton = new Button(Constants.CANCEL_LABEL);
		twentyButton = new Button(Constants.TWENTY_DOLLARS_LABEL);
		fourtyButton = new Button(Constants.FOURTY_DOLLARS_LABEL);
		sixtyButton = new Button(Constants.SIXTY_DOLLARS_LABEL);
		eightyButton = new Button(Constants.EIGHTY_DOLLARS_LABEL);
		oneHundredButton = new Button(Constants.ONE_HUNDRED_DOLLARS_LABEL);
		twoHundredButton = new Button(Constants.TWO_HUNDRED_DOLLARS_LABEL);

		// Create labels for login scene
		withdrawalAmountLabel = new Label(Constants.WITHDRAWAL_AMOUNT_LABEL);

		// Create inputs for login scene
		withdrawalAmountInput = new TextField();

		// text prompt
		withdrawalAmountInput.setPromptText(Constants.WITHDRAWAL_AMOUNT_LABEL);

		GridPane grid = getGridPane();

		// assign nodes to GridPane
		GridPane.setConstraints(withdrawalAmountLabel, 0, 0);
		GridPane.setConstraints(withdrawalAmountInput, 1, 0);
		GridPane.setConstraints(twentyButton, 0, 1);
		GridPane.setConstraints(fourtyButton, 1, 1);
		GridPane.setConstraints(sixtyButton, 0, 2);
		GridPane.setConstraints(eightyButton, 1, 2);
		GridPane.setConstraints(oneHundredButton, 0, 3);
		GridPane.setConstraints(twoHundredButton, 1, 3);
		GridPane.setConstraints(confirmButton, 0, 4);
		GridPane.setConstraints(cancelButton, 1, 4);

		grid.getChildren().addAll(
				withdrawalAmountLabel, withdrawalAmountInput, confirmButton, cancelButton, twentyButton, fourtyButton, 
				sixtyButton, eightyButton, oneHundredButton, twoHundredButton);

		// event handler
		confirmButton.setOnAction(e -> {
			//			buildScene(Constants.DEPOSIT_LABEL);
			//			updateTopBorder(menuBar);
		});

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(getMenuBarLoggedIn(mainStage));
		borderPane.setCenter(grid);

		Scene scene = new Scene(borderPane);

		return scene;
	}

	private Scene getTransferFundsScene(Stage mainStage) {
		// Create nodes for scene: buttons, labels, text fields
		Button confirmButton, cancelButton;
		Label accountNumberLabel, accountPinLabel, transferAmountLabel;
		TextField accountNumberInput, accountPinInput, transferAmountInput;

		// Create buttons for scene
		confirmButton = new Button(Constants.CONFIRM_LABEL);
		cancelButton = new Button(Constants.CANCEL_LABEL);

		// Create labels for scene
		accountNumberLabel = new Label(Constants.ACCOUNT_NUMBER_LABEL);
		accountPinLabel = new Label(Constants.ACCOUNT_PIN_LABEL);
		transferAmountLabel = new Label(Constants.TRANSFER_AMOUNT_LABEL);

		// Create inputs for scene
		accountNumberInput = new TextField();
		accountPinInput = new TextField();
		transferAmountInput = new TextField();

		// Optionally to set prompt text for fields in scene
		accountNumberInput.setPromptText(Constants.ACCOUNT_NUMBER_LABEL);
		accountPinInput.setPromptText(Constants.ACCOUNT_PIN_LABEL);
		transferAmountInput.setPromptText(Constants.TRANSFER_AMOUNT_LABEL);

		GridPane grid = getGridPane();

		// assign nodes to GridPane
		GridPane.setConstraints(accountNumberLabel, 0, 0);
		GridPane.setConstraints(accountNumberInput, 1, 0);
		GridPane.setConstraints(accountPinLabel, 0, 1);
		GridPane.setConstraints(accountPinInput, 1, 1);
		GridPane.setConstraints(transferAmountLabel, 0, 2);
		GridPane.setConstraints(transferAmountInput, 1, 2);
		GridPane.setConstraints(cancelButton, 0, 3);
		GridPane.setConstraints(confirmButton, 1, 3);

		grid.getChildren().addAll(
				accountNumberLabel, accountNumberInput, accountPinLabel, accountPinInput, transferAmountInput, transferAmountLabel, cancelButton, confirmButton);

		// event handler
		//		loginButton.setOnAction(e -> {
		//			//			buildScene(Constants.DEPOSIT_LABEL);
		//			//			updateTopBorder(menuBar);
		//		});

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(getMenuBarLoggedIn(mainStage));
		borderPane.setCenter(grid);

		Scene scene = new Scene(borderPane);

		return scene;
	}

	private Scene getAdditionalOptionsScene() {

		return null;
	}

	private Scene getViewTransactionHistoryScene() {

		return null;
	}

//	private Scene getCurrentBalanceScene() {
//
//		return null;
//	}

	private Scene getFaqScene(Stage mainStage) {
		// Nodes needed for scene: buttons, labels, text fields
		Label loginLabel, loginHelp, depositLabel, depositHelp, 
		withdrawalLabel, withdrawalHelp, 
		transferFundsLabel, transferFundsHelp,
		changePinLabel, changePinHelp;

		Button okButton = new Button(Constants.OK_LABEL);

		loginLabel = new Label(Constants.LOGIN_LABEL);
		loginHelp = new Label(Constants.LOGIN_HELP);
		depositLabel = new Label(Constants.DEPOSIT_LABEL);
		depositHelp = new Label(Constants.DEPOSIT_HELP);
		withdrawalLabel = new Label(Constants.WITHDRAWAL_LABEL);
		withdrawalHelp = new Label(Constants.WITHDRAWAL_HELP);
		transferFundsLabel = new Label(Constants.TRANSFER_FUNDS_LABEL);
		transferFundsHelp = new Label(Constants.TRANSFER_FUNDS_HELP); 
		changePinLabel = new Label(Constants.CHANGE_PIN_LABEL);
		changePinHelp = new Label(Constants.CHANGE_PIN_HELP);

		GridPane grid = getGridPane();

		// assign nodes to GridPane
		GridPane.setConstraints(loginLabel, 0, 0);
		GridPane.setConstraints(loginHelp, 1, 0);
		GridPane.setConstraints(depositLabel, 0, 1);
		GridPane.setConstraints(depositHelp, 1, 1);
		GridPane.setConstraints(withdrawalLabel, 0, 2);
		GridPane.setConstraints(withdrawalHelp, 1, 2);
		GridPane.setConstraints(transferFundsLabel, 0, 3);
		GridPane.setConstraints(transferFundsHelp, 1, 3);
		GridPane.setConstraints(changePinLabel, 0, 4);
		GridPane.setConstraints(changePinHelp, 1, 4);
		GridPane.setConstraints(okButton, 0, 5);

		grid.getChildren().addAll(
				loginLabel, loginHelp, depositLabel, depositHelp, 
				withdrawalLabel, withdrawalHelp, 
				transferFundsLabel, transferFundsHelp,
				changePinLabel, changePinHelp,
				okButton);

		// event handler
		okButton.setOnAction(e -> {
			//			buildScene(Constants.DEPOSIT_LABEL);
			//			updateTopBorder(menuBar);
		});

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(getMenuBarLoggedIn(mainStage));
		borderPane.setCenter(grid);

		Scene scene = new Scene(borderPane);

		return scene;
	}

	private Scene getChangePinScene(Stage mainStage) {
		// Nodes needed for scene: buttons, labels, text fields
		Button confirmButton, cancelButton;
		Label accountPinLabel, accountPinLabelNew;
		TextField accountPinInput, accountPinInputNew;

		confirmButton = new Button(Constants.CONFIRM_LABEL);
		cancelButton = new Button(Constants.CANCEL_LABEL);

		// Create labels for scene
		accountPinLabel = new Label(Constants.ACCOUNT_PIN_LABEL);
		accountPinLabelNew = new Label(Constants.ACCOUNT_PIN_LABEL_NEW);

		// Create inputs for scene
		accountPinInput = new TextField();
		accountPinInput.setPromptText(Constants.ACCOUNT_PIN_LABEL);
		accountPinInputNew = new TextField();
		accountPinInputNew.setPromptText(Constants.ACCOUNT_PIN_LABEL_NEW);

		GridPane grid = getGridPane();

		// assign nodes to GridPane
		GridPane.setConstraints(accountPinLabel, 0, 0);
		GridPane.setConstraints(accountPinInput, 1, 0);
		GridPane.setConstraints(accountPinLabelNew, 0, 1);
		GridPane.setConstraints(accountPinInputNew, 1, 1);
		GridPane.setConstraints(confirmButton, 0, 2);
		GridPane.setConstraints(cancelButton, 1, 2);

		grid.getChildren().addAll(
				accountPinLabel, accountPinLabelNew, accountPinInput, accountPinInputNew, confirmButton, cancelButton);

		// event handler
		confirmButton.setOnAction(e -> {
			//			buildScene(Constants.DEPOSIT_LABEL);
			//			updateTopBorder(menuBar);
		});

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(getMenuBarLoggedIn(mainStage));
		borderPane.setCenter(grid);

		Scene scene = new Scene(borderPane);

		return scene;
	}

	private Scene getCreateNewAccount(Stage mainStage) {
		Label accountNumberLabel, accountPinLabel;

		Button cancelButton = createButton(Constants.CANCEL_LABEL, Constants.CANCEL_HELP);
		Button createAccountButton = createButton(Constants.CREATE_NEW_ACCOUNT_LABEL, Constants.CREATE_ACCOUNT_HELP);

		// Create labels for login scene
		accountNumberLabel = new Label(Constants.ACCOUNT_NUMBER_LABEL);
		accountPinLabel = new Label(Constants.ACCOUNT_PIN_LABEL);

		// Create inputs for login scene
		TextField accountNumberInput = createTextField(Constants.ACCOUNT_NUMBER_LABEL);
		TextField accountPinInput = createTextField(Constants.ACCOUNT_PIN_LABEL);

		GridPane grid = getGridPane();

		// assign nodes to GridPane
		GridPane.setConstraints(accountNumberLabel, 0, 0);
		GridPane.setConstraints(accountNumberInput, 1, 0);
		GridPane.setConstraints(accountPinLabel, 0, 1);
		GridPane.setConstraints(accountPinInput, 1, 1);
		GridPane.setConstraints(cancelButton, 0, 2);
		GridPane.setConstraints(createAccountButton, 1, 2);

		grid.getChildren().addAll(
				accountNumberLabel, accountNumberInput, accountPinLabel, accountPinInput, cancelButton, createAccountButton);

		// event handler
		cancelButton.setOnAction(e -> {
			//			buildScene(Constants.DEPOSIT_LABEL);
			//			updateTopBorder(menuBar);
			returnToMainScene(mainStage);
		});

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(getMenuBarLoggedIn(mainStage));
		borderPane.setCenter(grid);

		Scene scene = new Scene(borderPane);

		return scene;
	}

	private MenuBar getMenuBarLoggedIn(Stage mainStage) {
		// Create MenuBar
		MenuBar menuBar = new MenuBar();
		// Create Menus
		Menu fileMenu = new Menu(Constants.FILE_LABEL);
		Menu optionsMenu = new Menu(Constants.OPTIONS_LABEL);
		Menu helpMenu = new Menu(Constants.HELP_LABEL);

		MenuItem 
		depositMenuItem, withdrawalMenuItem, transferFundsMenuItem,
		getBalanceMenuItem, exitMenuItem, logoutMenuItem, changePinMenuItem,
		createNewAccountMenuItem, viewTransactionHistoryMenuItem, 
		contactUsMenuItem, faqMenuItem;

		// create Menu Items
		exitMenuItem = new MenuItem(Constants.EXIT_LABEL);
		contactUsMenuItem = new MenuItem(Constants.CONTACT_US_LABEL);
		faqMenuItem = new MenuItem(Constants.FAQ_LABEL);
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

		// Add menus to menuBar
		menuBar.getMenus().addAll(fileMenu, optionsMenu, helpMenu);

		// change to deposit scene
		depositMenuItem.setOnAction(e -> mainStage.setScene(depositScene));
		// change to witdrawal scene
		withdrawalMenuItem.setOnAction(e -> mainStage.setScene(withdrawalScene));
		// change to transfer funds scene
		transferFundsMenuItem.setOnAction(e -> mainStage.setScene(transferFundsScene));
		/*
		 * NEED GET BALANCE BUTTON AND POPUP SCENE
		 */
		// disable close button
		exitMenuItem.setOnAction(exit -> Platform.exit());
		// logout sequence
		logoutMenuItem.setOnAction(logout -> returnToLoginScene(mainStage));
		// change scene to change pin scene
		changePinMenuItem.setOnAction(changePin -> mainStage.setScene(changePinScene));
		// change scene to create new account scene
		createNewAccountMenuItem.setOnAction(e -> mainStage.setScene(createNewAccount));
		// display contact scene
		contactUsMenuItem.setOnAction(contactUs -> ContactUs.display());
		// display faq scene
		faqMenuItem.setOnAction(FAQ -> Faq.display());

		return menuBar;

	}

	private MenuBar getMenuBarLoginScreen(Stage mainStage) {
		MenuBar menuBarLoginScreen = new MenuBar();
		Menu fileMenu, optionsMenu, helpMenu;
		MenuItem 
		exitMenuItem, contactUsMenuItem, faqMenuItem;

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
		menuBarLoginScreen.getMenus().addAll(fileMenu, optionsMenu, helpMenu);

		// terminate program
		exitMenuItem.setOnAction(exit -> Platform.exit());
		// display contact scene
		contactUsMenuItem.setOnAction(contactUs -> ContactUs.display());
		// display faq scene
		faqMenuItem.setOnAction(FAQ -> Faq.display());

		return menuBarLoginScreen;
	}

	public void returnToMainScene(Stage mainStage) {
		mainStage.setScene(mainMenuScene);
	}

	public void returnToLoginScene(Stage mainStage) {
		mainStage.setScene(loginScene);
	}

	@Override
	public void start(Stage mainStage) {
		// TODO Auto-generated method stub
		mainStage.setTitle(Constants.TITLE);
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		mainStage.setResizable(false);
		mainStage.setOnCloseRequest(e -> e.consume());

		loginScene = getLoginScene(mainStage);
		depositScene = getDepositScene(mainStage);
		transferFundsScene = getTransferFundsScene(mainStage);
		changePinScene = getChangePinScene(mainStage);
		createNewAccount = getCreateNewAccount(mainStage);
		withdrawalScene = getWithdrawalScene(mainStage);
		faqScene = getFaqScene(mainStage);
		mainMenuScene = getMainMenuScene(mainStage);

		// Add the menubar and shapes 
		//		borderPane.setTop(menuBar);
		mainStage.setWidth(primaryScreenBounds.getWidth() * (3.0/4.0));
		mainStage.setHeight(primaryScreenBounds.getHeight() * (3.0/4.0));
		mainStage.setScene(loginScene);
		mainStage.show();
	}

	//	private void updateTopBorder(MenuBar menuBar) {
	//		borderPane.setTop(menuBar);
	//	}
}

