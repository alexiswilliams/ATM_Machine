package com.cs225.finalproject.ui;

import com.cs225.finalproject.utils.Constants;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Faq {
	public static void display() {
		Label loginLabel, loginHelp, depositLabel, depositHelp, 
		withdrawalLabel, withdrawalHelp, 
		transferFundsLabel, transferFundsHelp,
		changePinLabel, changePinHelp;
		Stage faq = new Stage();
		final String faqTitleString = "FAQ: Tool Tips";

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

		GridPane grid = new GridPane();
		//		grid.setAlignment(Pos.CENTER);

		grid.setPadding(new Insets(10,10,10,10));
		// set spacing between nodes
		grid.setVgap(8);
		grid.setHgap(10);

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

		grid.getChildren().addAll(
				loginLabel, loginHelp, depositLabel, depositHelp, 
				withdrawalLabel, withdrawalHelp, 
				transferFundsLabel, transferFundsHelp,
				changePinLabel, changePinHelp);

		// block UI of other windows
		faq.initModality(Modality.APPLICATION_MODAL);
		// disable resizing
		faq.setResizable(false);
		// set title
		faq.setTitle(faqTitleString);
		// set size of window
		Rectangle2D primaryScreeBounds = Screen.getPrimary().getVisualBounds();
		faq.setWidth(primaryScreeBounds.getWidth()*(1.0/2.0));
		faq.setHeight(primaryScreeBounds.getHeight()*(1.0/2.0));
		Scene faqScene = new Scene(grid);
		faq.setScene(faqScene);
		faq.showAndWait();
	}
}
