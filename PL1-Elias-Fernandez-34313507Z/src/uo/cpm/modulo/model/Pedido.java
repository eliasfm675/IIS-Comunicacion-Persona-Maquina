package uo.cpm.modulo.model;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.modulo.util.FileUtil;

public class Pedido {
	private List<Producto> orderList = null;
	private String observations = "Observaciones: ";
	private Cliente customer;
	private String code = "";
	public Pedido() {
		orderList = new ArrayList<Producto>();
	}
	public List<Producto> getOrderList() {
		return orderList;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getObservations() {
		return observations;
	}
	private void setObservations(String observations) {
		this.observations = observations;
	}
	public void appendObservations(String observations) {
		this.observations += observations +"\n";
	}
	public void appendObservationsGluten(Producto p) {
		this.observations += p.getName() +" - " +" Sin gluten/Intolerancias"+"\n";
	}
	public Cliente getCustomer() {
		return customer;
	}
	public void setCustomer(Cliente customer) {
		this.customer = customer;
	}
	public void add(Producto p, int units ) {
		Producto itemInOrder = null;
		for(Producto a: orderList) {
			if(a.getCode().equals(p.getCode())) {
				itemInOrder = a;
				itemInOrder.editUnits(itemInOrder.getUnits()+units);
			}
		
			
		}
		if (itemInOrder == null){
			Producto itemToOrder = new Producto(p);
			itemToOrder.editUnits(units);
			orderList.add(itemToOrder);
		}
		
	}
	public int searchUnits(Producto p) {
		for(Producto a: orderList) {
			if(a.getCode().equals(p.getCode())) {
				return a.getUnits();
				}
		}
		return 0;
	}
	public boolean isInOrder(Producto p) {
		for(Producto a: orderList) {
			if(a.getCode().equals(p.getCode())) {
				return true;
			}
		}
		return false;
	}
	public void changeUnits(Producto p, int units) {
		for(Producto a: orderList) {
			if(a.getCode().equals(p.getCode())) {
				a.editUnits(a.getUnits()-units);
				if(a.getUnits()<0) {
					a.editUnits(0);
				}
				break;
			}
		}
		
	}
	public void remove(Producto p) {
		for(Producto a: orderList) {
			if(a.getCode().equals(p.getCode())) {
				a.editUnits(0);
				orderList.remove(a);
				break;
			}
		}
	}
	public float getPrice(){
		float total = 0.0f;
		for (Producto a : orderList){
			total += a.getPrice()* a.getUnits();
		}
		return total;
	}
	public void saveOrder(){
		FileUtil.saveToFile(this);
	  }
	public void initializeDifferentCustomer(){
		orderList.clear();
		setCode("");
		setCustomer(null);
		setObservations("Observaciones: ");
	}
	public void initialize(){
		orderList.clear();
		//getCustomer().editTimesOrdered(getCustomer().getTimesOrdered()+1);
		setCode("");
		setObservations("Observaciones: ");
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Producto a: orderList) {
			sb.append(a.toString() + "\n");
		}
		return sb.toString();
	}
	public String toStringShoppingCart() {
		StringBuilder sb = new StringBuilder();
		for(Producto a: orderList) {
			sb.append(a.toStringShoppingCart() + "\n");
		}
		sb.append(String.format("Total: %.2f €", getPrice()));
		return sb.toString();
	}

	


	
	
}
