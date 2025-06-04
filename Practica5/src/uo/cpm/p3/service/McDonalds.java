package uo.cpm.p3.service;

import java.util.HashMap;
import java.util.List;

import uo.cpm.p3.model.Customer;
import uo.cpm.p3.model.Menu;
import uo.cpm.p3.model.Order;
import uo.cpm.p3.model.Product;

public class McDonalds {

	Menu menu = new Menu();
	Order order = new Order();
	public static final int EDAD_MIN=16;
	public static final int EDAD_MAX=100;
	
	
	public McDonalds() {
		initOrder();		
	}
	
	public String getOrderCode()
	{
		return order.getCode();
	}
	
	public void addToOrder ( Product p, Integer units )
	{
		order.add(p, units);
	}
	
	public Object getOrderTotal() {
		// TODO Auto-generated method stub
		return order.getPrice();
	}
	
	public void saveCustomerData (String name, Integer year, String password)
	{
		order.setCustomer ( new Customer ( name, password, year ));
	}
	
	public void setOrderType( boolean takeAway )
	{
		order.setTakeAway( takeAway);
	}
	public Product[] getMenuProducts()
	{
		return menu.getProducts();
	}
	public Product[] getMenuProductsBurgers() {
		return menu.getBurgers();
	}
	public Product[] getMenuProductsDrinks() {
		return menu.getDrinks();
	}
	public Product[] getMenuProductsDesserts() {
		return menu.getDesserts();
	}
	public Product[] getMenuProductsSide() {
		return menu.getSide();
	}
	
	public void initOrder()
	{
		order.initialize();
	}
	public int searchUnits(Product p) {
		return order.searchUnits(p);
	}
	public void remove(Product p) {
		 order.remove(p);
	}
	public void changeUnits(Product p, int units) {
		order.changeUnits(p, units);
	}
	public void saveOrder()
	{
		order.saveOrder();
	}
	public String getOrderToString() {
		return order.toString();
	}
	public String getOrderToStringPedido() {
		return order.toStringPedido();
	}
}
