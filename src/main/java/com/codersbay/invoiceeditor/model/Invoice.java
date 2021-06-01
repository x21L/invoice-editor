package com.codersbay.invoiceeditor.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Invoice {
	private Invoice() {}
	
	private static final ObservableList<InvoiceItem> invoiceItems = FXCollections.observableArrayList();
	
	public static ObservableList<InvoiceItem> getInvoiceitems() {
		return invoiceItems;
	}
}
