package com.cs225.finalproject.utils;

import javafx.beans.property.SimpleStringProperty;

public class HelpItem {
	private final SimpleStringProperty action;
	private final SimpleStringProperty help;
	
	private HelpItem(String a, String h) {
		this.action = new SimpleStringProperty(a);
		this.help = new SimpleStringProperty(h);
	}
	
	public String getAction() {
		return action.get();
	}
	
	public void setAction(String a) {
		action.set(a);
	}
	
	public String getHelp() {
		return help.get();
	}
	
	public void setHelp(String h) {
		help.set(h);
	}
}
