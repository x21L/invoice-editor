package com.codersbay.invoiceeditor.model;

import java.util.ArrayList;
import java.util.List;

public class DummyProductList {
	private static final List<Product> products;
	
	static {
		products = new ArrayList<>();
		
		products.add(new Product("1001", "Gartentisch", 159.0));
		products.add(new Product("2004", "Sonnenschirm", 79.99));
		products.add(new Product("2200", "Sitzbank", 129.99));
	}
	
	public static List<Product> getDummyProducts() {
		return products;
	}
}
