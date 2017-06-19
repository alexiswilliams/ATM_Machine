package com.cs225.finalproject.ui;

import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.stage.Stage;
/*
 * Need:
 *  - Title
 *  
 */
public class MainStage extends Application {
	
	private static final String TITLE = "Eagle Bank";
	private Menu menu;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle(TITLE);
	}

}
