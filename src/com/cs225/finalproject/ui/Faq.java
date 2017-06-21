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

public class Faq {
	public static void display() {
		Stage popUp = new Stage();
		VBox pane = new VBox();
		final String faqTitleString = "FAQ: Tool Tips";
		final String faqString = "test";
		Label faqLabel = new Label(faqString);
		faqLabel.setWrapText(true);
		faqLabel.setTextAlignment(TextAlignment.CENTER);
		pane.getChildren().add(faqLabel);
		pane.setAlignment(Pos.CENTER);

		// block UI of other windows
		popUp.initModality(Modality.APPLICATION_MODAL);
		// disable resizing
		popUp.setResizable(false);
		// set title
		popUp.setTitle(faqTitleString);
		// set size of window
		Rectangle2D primaryScreeBounds = Screen.getPrimary().getVisualBounds();
		popUp.setWidth(primaryScreeBounds.getWidth()*(1.0/2.0));
		popUp.setHeight(primaryScreeBounds.getHeight()*(1.0/2.0));
		Scene contactUsScreen = new Scene(pane);
		popUp.setScene(contactUsScreen);
		popUp.showAndWait();
	}
}
