package com.codersbay.invoiceeditor.model;

public class Product {
	private final String ID;
	private final String name;
	private final double price;
	
	public Product(String ID, String name, double price) {
		super();
		this.ID = ID;
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getID() {
		return ID;
	}
	
	public double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return name + " [ID:" + ID + "]";
	}
}
