package uo.cpm.p3.model;

import java.util.*;

import uo.cpm.p3.util.FileUtil;

public class Order {
	
	private List<Product> orderList = null;
	private String code="";
	private Customer customer;
	private boolean takeAway;
	
	public boolean isTakeAway() {
		return takeAway;
	}

	public void setTakeAway(boolean takeAway) {
		this.takeAway = takeAway;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public Order(){
		orderList = new ArrayList<Product>();
		//We generate the new code for the order:
		generateCode();
	}

	public void add(Product item, int units){
		Product itemInOrder = null;
	
		for (Product a : orderList){
			if (a.getCode().equals(item.getCode()))
			{
				itemInOrder = a;
				itemInOrder.setUnits(itemInOrder.getUnits()+units);
				break;
			}
		}
		
		if (itemInOrder == null){
			Product itemToOrder = new Product(item);
			itemToOrder.setUnits(units);
			orderList.add(itemToOrder);
		}
	}
	
	public String getCode() {
		return code;
	}

	public float getPrice(){
		float total = 0.0f;
		for (Product a : orderList){
			total += a.getPrice()* a.getUnits();
		}
		if(takeAway) {
			total+=0.15;
		}
		return total;
	}
	
	public void saveOrder(){
		FileUtil.saveToFile(this);
	  }

	public void initialize(){
		orderList.clear();
		setCode("");
		generateCode();
		setTakeAway(false);
		setCustomer(null);
	}
	
	private void generateCode() {
		String base = "0123456789abcdefghijklmnopqrstuvwxyz";
		int longitudCodigo = 8;
		for(int i=0; i<longitudCodigo;i++){ 
			int numero = (int)(Math.random()*(base.length())); 
			code += base.charAt(numero);
		}
	}
public int searchUnits(Product p) {
		for(Product a: orderList) {
			if(a.getCode().equals(p.getCode())) {
				return a.getUnits();
				}
		}
		return 0;
	}

	public List<Product> getOrderList() {
		return orderList;
	}

	public void remove(Product p) {
		for(Product a: orderList) {
			if(a.getCode().equals(p.getCode())) {
				a.setUnits(0);
				orderList.remove(a);
				break;
			}
		}
	}

	public void changeUnits(Product p, int units) {
		for(Product a: orderList) {
			if(a.getCode().equals(p.getCode())) {
				a.setUnits(a.getUnits()-units);
				if(a.getUnits()<0) {
					a.setUnits(0);
				}
				break;
			}
		}
		
	}

	public boolean isInOrder(Product p) {
		for(Product a: orderList) {
			if(a.getCode().equals(p.getCode())) {
				return true;
			}
		}
		return false;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Product a: orderList) {
			sb.append(a.toString() + "\n");
		}
		return sb.toString();
	}
	public String toStringPedido() {
		StringBuilder sb = new StringBuilder();
		for(Product a: orderList) {
			sb.append(a.toStringPedido() + "\n");
		}
		sb.append(String.format("Total: %.2f €", getPrice()));
		return sb.toString();
	}
}

