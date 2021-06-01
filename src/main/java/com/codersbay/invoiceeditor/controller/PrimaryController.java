package com.codersbay.invoiceeditor.controller;

import com.codersbay.invoiceeditor.model.DummyProductList;
import com.codersbay.invoiceeditor.model.Invoice;
import com.codersbay.invoiceeditor.model.InvoiceItem;
import com.codersbay.invoiceeditor.model.Product;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

public class PrimaryController {

	private final ObservableList<InvoiceItem> invoiceItems = Invoice.getInvoiceitems();

	@FXML
	private TableView<InvoiceItem> invoiceCells;

	@FXML
	private TableColumn<InvoiceItem, String> nameColumn;

	@FXML
	private TableColumn<InvoiceItem, Integer> amountColumn;

	@FXML
	private TableColumn<InvoiceItem, Double> priceUnitColumn;

	@FXML
	private TableColumn<InvoiceItem, Double> priceColumn;

	@FXML
	private ChoiceBox<Product> productChoice;

	@FXML
	private TextField addAmount;

	@FXML
	private Button addButton;

	@FXML
	public void initialize() {
		/*
		 * table
		 */

		// insert invoice items into table
		invoiceCells.itemsProperty().setValue(invoiceItems);
		invoiceCells.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		// name
		nameColumn.setCellValueFactory((TableColumn.CellDataFeatures<InvoiceItem, String> row) -> {
			InvoiceItem invoiceItem = row.getValue();
			return invoiceItem == null ? new ReadOnlyStringWrapper("no name set") : invoiceItem.nameProperty();
		});

		// amount
		amountColumn.setCellValueFactory((TableColumn.CellDataFeatures<InvoiceItem, Integer> row) -> {
			InvoiceItem invoiceItem = row.getValue();
			return invoiceItem == null ? new SimpleIntegerProperty(0).asObject()
					: invoiceItem.amountProperty().asObject();
		});

		// price per unit
		priceUnitColumn.setCellValueFactory((TableColumn.CellDataFeatures<InvoiceItem, Double> row) -> {
			InvoiceItem invoiceItem = row.getValue();
			return invoiceItem == null ? new SimpleDoubleProperty(0.0).asObject()
					: invoiceItem.priceUnitProperty().asObject();
		});

		// price
		priceColumn.setCellValueFactory((TableColumn.CellDataFeatures<InvoiceItem, Double> row) -> {
			InvoiceItem invoiceItem = row.getValue();
			return invoiceItem == null ? new SimpleDoubleProperty(0.0).asObject()
					: invoiceItem.priceProperty().asObject();
		});

		// for editing the amount
		amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {

			@Override
			public String toString(Integer object) {
				return object == null ? "" : object.toString();
			}

			@Override
			public Integer fromString(String newValue) {
				int index = amountColumn.getTableView().getSelectionModel().getSelectedIndex();

				try {
					int newAmount = Integer.parseInt(newValue);
					invoiceItems.get(index).setAmount(newAmount);
					return newAmount;
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				}

				return invoiceItems.get(index).getAmount();
			}
		}));

		/*
		 * choice box
		 */
		productChoice.show();
		// add the items, we need strings for displaying -> toString() in Product
		productChoice.getItems().addAll(DummyProductList.getDummyProducts());
		// select first item per default
		productChoice.getSelectionModel().selectFirst();
	}

	/*
	 * button
	 */

	@FXML
	void add() {
		int index = productChoice.getSelectionModel().getSelectedIndex();
		if (!addAmount.getText().isEmpty()) {
			try {
				int amount = Integer.parseInt(addAmount.getText());
				addItem(DummyProductList.getDummyProducts().get(index), amount);
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void addItem(Product product, int amount) {
		invoiceItems.add(new InvoiceItem(product.getName(), product.getPrice(), amount));
		invoiceCells.refresh();
	}
}
