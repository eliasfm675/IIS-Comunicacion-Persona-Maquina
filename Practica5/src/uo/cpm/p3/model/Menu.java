package uo.cpm.p3.model;

import java.util.*;

import uo.cpm.p3.util.FileUtil;

public class Menu {
	
	private static final String PRODUCTS_FILE = "files/products.dat";
	private List<Product> productsList = null;
	
	public Menu(){
		productsList = new ArrayList<Product>();
		loadProducts();
		
	}

	private void loadProducts(){
		FileUtil.loadFile (PRODUCTS_FILE, productsList);
	  }

	public Product[] getProducts(){
		Product[] products = productsList.toArray(new Product[productsList.size()]);
		return products;	
	}

	public Product[] getBurgers() {
		List<Product> burgers = new ArrayList<>();
		for(Product a: productsList) {
			if(a.getType().equals("Burger")) {
				burgers.add(a);
			}
		}
		return burgers.toArray(new Product[productsList.size()]);
	}

	public Product[] getDrinks() {
		List<Product> drinks = new ArrayList<>();
		for(Product a: productsList) {
			if(a.getType().equals("Drink")) {
				drinks.add(a);
			}
		}
		return drinks.toArray(new Product[productsList.size()]);
	}

	public Product[] getDesserts() {
		List<Product> desserts = new ArrayList<>();
		for(Product a: productsList) {
			if(a.getType().equals("Dessert")) {
				desserts.add(a);
			}
		}
		return desserts.toArray(new Product[productsList.size()]);
	}

	public Product[] getSide() {
		List<Product> sides = new ArrayList<>();
		for(Product a: productsList) {
			if(a.getType().equals("Side")) {
				sides.add(a);
			}
		}
		return sides.toArray(new Product[productsList.size()]);
	}
	
}
