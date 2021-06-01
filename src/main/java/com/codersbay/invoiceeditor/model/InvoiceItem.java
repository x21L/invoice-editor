package com.codersbay.invoiceeditor.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InvoiceItem {
	private final StringProperty name;
	private final DoubleProperty priceUnit;
	private final DoubleProperty price = new SimpleDoubleProperty();
	private final IntegerProperty amount;
	
	public InvoiceItem(String name, double priceUnit, int amount) {
		this.name = new SimpleStringProperty(name);
		this.priceUnit = new SimpleDoubleProperty(priceUnit);
		this.amount = new SimpleIntegerProperty(amount);
		
		// bind price to unit
		price.bind(this.priceUnit.multiply(this.amount));
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public DoubleProperty priceUnitProperty() {
		return priceUnit;
	}
	
	public DoubleProperty priceProperty() {
		return price;
	}
	
	public IntegerProperty amountProperty() {
		return amount;
	}

	public String getName() {
		return name.get();
	}

	public double getPriceUnit() {
		return priceUnit.get();
	}

	public double getPrice() {
		return price.get();
	}

	public int getAmount() {
		return amount.get();
	}
	
	public void setAmount(int amount) {
		this.amount.set(amount);
	}

	@Override
	public String toString() {
		return "InvoiceItem [name=" + name + ", priceUnit=" + priceUnit + ", price=" + price + ", amount=" + amount
				+ "]";
	}
}
