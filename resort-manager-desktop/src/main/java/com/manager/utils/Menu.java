package com.manager.utils;

public enum  Menu {
	Home("Dashboard"),
	Customer("Customer Management"),
	Employee("Employee Management"),
	Contract("Contract Management");

	private String title;

	Menu(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFxml() {
		return String.format("%s.fxml", name());
	}
}
