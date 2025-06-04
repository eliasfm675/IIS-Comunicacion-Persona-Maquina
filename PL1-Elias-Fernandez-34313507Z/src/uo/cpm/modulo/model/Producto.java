package uo.cpm.modulo.model;

import java.util.zip.DataFormatException;


public class Producto {
	private String code;
	private String name;
	private String ingredients;
	private String intolerance;
	private String type;
	private double price;
	private int units;
	public Producto(String code, String name, String ingredients, String intolerance, String type, double price) {
		setCode(code);
		setName(name);
		setIngredients(ingredients);
		setIntolerance(intolerance);
		setType(type);
		setPrice(price);
		setUnits(0);
	}
	public Producto(String code, String name, String ingredients, String intolerance, String type, double price, int units) {
		setCode(code);
		setName(name);
		setIngredients(ingredients);
		setIntolerance(intolerance);		
		setType(type);
		setPrice(price);
		setUnits(units);
	}
	public Producto (Producto anotherItem) {
        this(anotherItem.getCode(), anotherItem.getName(), anotherItem.getIngredients(), anotherItem.getIntolerance(), anotherItem.getType(), anotherItem.getPrice(), anotherItem.getUnits());
    }
	public int getUnits() {
		return units;
	}
	private void setUnits(int units) {
		this.units = units;
	}
	public String getCode() {
		return code;
	}
	private void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public String getIngredients() {
		return ingredients;
	}
	private void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	private String getIntolerance() {
		return intolerance;
	}
	private void setIntolerance(String intolerance) {
		this.intolerance = intolerance;
	}
	public String getType() {
		return type;
	}
	private void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	void setPrice(double price) {
		this.price = price;
	}
	public boolean hasIntolerance() {
		if(intolerance.equals("SI")) {
			return true;
		}else {
			return false;
		}
	}
	public void editUnits(int units) {
		setUnits(units);
	}
	public String toStringMenu(){
		return String.format("%s - %s - %.2f €", getName(), getType(), getPrice());
	}
	public String toString(){
		return code + "@" + name + "@" + units;
	}
	public String toStringShoppingCart(){
		return String.format("%s - %s - %.2f € - %d uds", getName(), getType(), getPrice(), getUnits());
	}
	public String toStringBuyScreen() {
		String[] parts = getIngredients().split(",");
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<parts.length; i++) {
			parts[i].strip();
			if(i!=0) {
				sb.append(parts[i].toUpperCase().charAt(1) + parts[i].substring(2)+"\n");
			}else {
				sb.append(parts[i].toUpperCase().charAt(0) + parts[i].substring(1)+"\n");
			}

			
		}
		return sb.toString();
	}
}
