package com.cs225.finalproject.ui;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class getBalance {
	public static void display() {
		Stage balance = new Stage();
		VBox pane = new VBox();
		final String balanceTitleString = "Balance";
		final String balanceString = "$ ";
		Label balanceLabel = new Label(balanceString);
		balanceLabel.setWrapText(true);
		balanceLabel.setTextAlignment(TextAlignment.CENTER);
		pane.getChildren().add(balanceLabel);
		pane.setAlignment(Pos.CENTER);

		/*
		 * MAKE A METHOD THAT ADDS BALANCE TO "balanceString"
		 */
		
		// block UI of other windows
		balance.initModality(Modality.APPLICATION_MODAL);
		// disable resizing
		balance.setResizable(false);
		// set title
		balance.setTitle(balanceTitleString);
		// set size of window
		Rectangle2D primaryScreeBounds = Screen.getPrimary().getVisualBounds();
		balance.setWidth(primaryScreeBounds.getWidth()*(1.0/2.0));
		balance.setHeight(primaryScreeBounds.getHeight()*(1.0/2.0));
		Scene contactUsScreen = new Scene(pane);
		balance.setScene(contactUsScreen);
		balance.showAndWait();
	}
}
