package uo.cpm.modulo.service;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.modulo.model.Cliente;
import uo.cpm.modulo.model.Menu;
import uo.cpm.modulo.model.Pedido;
import uo.cpm.modulo.model.Producto;
import uo.cpm.modulo.util.FileUtil;

public class Pizzeria {
	private Pedido order = new Pedido();
	private Menu menu = new Menu();
	private List<Cliente> clients = new ArrayList<Cliente>();
	public static final String CLIENTS_FILE = "files/reservas.dat";
	public static final int TODOS = 0;
	public static final int PIZZAS = 1;
	public static final int ENSALADAS = 2;
	public static final int ENTRANTES = 3;
	public static final int PASTAS = 4;
	public static final int BEBIDAS = 5;
	public static final int POSTRES = 6;
	private int unitsChanged = 0;
	private Cliente client;
	private boolean hasClientPlayed;
	public void setClientPlayed(boolean playState) {
		this.hasClientPlayed = playState;
	}
	public boolean hasClientPlayed() {
		return hasClientPlayed;
	}
	
	public int getUnitsChanged() {
		return unitsChanged;
	}
	public void setUnitsChanged(int unitsChanged) {
		this.unitsChanged = unitsChanged;
	}
	public Pizzeria() {
		FileUtil.loadClients(CLIENTS_FILE, clients);
		initializeOrder();
		this.hasClientPlayed = false;
	}
	public void initializeOrder() {
		order.initialize();	
		
	}
	public void initializeOrderDifferentClient() {
		order.initializeDifferentCustomer();
		client = new Cliente("","");
		this.hasClientPlayed = false;
		
	}
	
	public void remove(Producto p) {
		order.remove(p);
	}
	public void changeUnits(Producto p, int units) {
		order.changeUnits(p, units);
		if(order.searchUnits(p)<=0) {
			remove(p);
		}
	}
	public void saveOrder()
	{
		order.saveOrder();
	}
	public String getOrderToString() {
		return order.toString();
	}
	public String getOrderToStringShoppingCart() {
		return order.toStringShoppingCart();
	}
	public void saveCustomerData (String id, String code) {
		order.setCustomer ( new Cliente (id, code));
	}
	public String getOrderCode()
	{
		return order.getCode();
	}
	
	public void addToOrder ( Producto p, int units )
	{
		order.add(p, units);
	}

	public Object getOrderTotal() {
		return order.getPrice();
	}
	public Producto[] getMenuTotalProducts()
	{
		return menu.getTotalProducts();
	}
	public Producto[] getMenuPizzas() {
		return menu.getPizzas();
	}
	public Producto[] getMenuBebidas() {
		return menu.getBebidas();
	}
	public Producto[] getMenuPostres() {
		return menu.getPostres();
	}
	public Producto[] getMenuEnsaladas() {
		return menu.getEnsaladas();
	}
	public Producto[] getMenuEntrantes() {
		return menu.getEntrantes();
	}
	public Producto[] getMenuPastas() {
		return menu.getPastas();
	}
	public Producto[] typeOfFilter(int filter) {
		switch(filter) {
		case TODOS:
			return getMenuTotalProducts();
		case PIZZAS:
			return getMenuPizzas();
		case BEBIDAS:
			return getMenuBebidas();
		case POSTRES:
			return getMenuPostres();
		case PASTAS:
			return getMenuPastas();
		case ENTRANTES:
			return getMenuEntrantes();
		case ENSALADAS:
			return getMenuEnsaladas();
		default:
			return null;
		
			
			
		}
	}
	public boolean isUserAClient(String id, String code) {
		for(Cliente c: clients) {
			if(c.getId().equals(id) && c.getCode().equals(code)) {
//				client = new Cliente(id, code);
//				order.setCustomer(client);
				saveCustomerData(id, code);
//				order.setCode(code);
				return true;
			}
		}
		return false;
		
	}
	public boolean hasPlayed() {
		return client.isHasPlayed();
	}
	public String getObservations() {
		return order.getObservations();
	}
	public void appendObservations(String observations) {
		order.appendObservations(observations);
	}
	public void appendObservationsGluten(Producto p) {
		order.appendObservationsGluten(p);
	}
	public Cliente getClient() {
		return order.getCustomer();
	}
	public Pedido getOrder() {
		return order;
	}
	
}
