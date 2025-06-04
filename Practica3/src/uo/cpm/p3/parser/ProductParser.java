package uo.cpm.p3.parser;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.p3.model.Product;

public class ProductParser {
	public List<Product> parse(List<String> lines) {
		List<Product> products = new ArrayList<>();
		for(String theLine: lines) {
			products.add(parseLine(theLine));
		}
		return products;
	}

	private Product parseLine(String theLine) {
		Product product;
		String[] parts = theLine.split("@");
		String code = parts[0];
		String type = parts[1]; 
		String name = parts[2];
		float price = parseFloat(parts[3]);
		product = new Product(code, type, name, price);
		return product;
	}

	private float parseFloat(String string) {
		try {
			return Float.parseFloat(string);
		}catch(NumberFormatException e) {
			throw new NumberFormatException();
		}
	}
}
