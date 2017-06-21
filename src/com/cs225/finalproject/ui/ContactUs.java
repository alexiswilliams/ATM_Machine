package com.cs225.finalproject.ui;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ContactUs {
	public static void display() {
		Stage popUp = new Stage();
		StackPane pane = new StackPane();
		final String contactUsString = "This program was written by Paris Williams."
				+ " If you have any questions about the program contact Paris Williams"
				+ " at: willip27@my.erau.edu";
		Label contactUsLabel = new Label(contactUsString);
		contactUsLabel.setWrapText(true);
		contactUsLabel.setTextAlignment(TextAlignment.CENTER);
		pane.getChildren().add(contactUsLabel);
		
		
		// block UI of other windows
		popUp.initModality(Modality.APPLICATION_MODAL);
		// disable resizing
		popUp.setResizable(false);
		// set title
		popUp.setTitle("Contact Us");
		// set size of window
		Rectangle2D primaryScreeBounds = Screen.getPrimary().getVisualBounds();
		popUp.setWidth(primaryScreeBounds.getWidth()*(1.0/2.0));
		popUp.setHeight(primaryScreeBounds.getHeight()*(1.0/2.0));
		Scene contactUsScreen = new Scene(pane);
		popUp.setScene(contactUsScreen);
		popUp.showAndWait();
	}
}