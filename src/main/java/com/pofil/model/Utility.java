package com.pofil.model;

public class Utility {

	protected String expenseType;
	protected double amount;
	protected String description;
	
	protected Utility() {
		
	}

	public Utility(String expenseType, double amount, String description) {
		super();
		this.expenseType = expenseType;
		this.amount = amount;
		this.description = description;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
