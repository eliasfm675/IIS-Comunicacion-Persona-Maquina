package uo.cpm.modulo.model;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.modulo.util.FileUtil;

public class Menu {
	private List<Producto> productsList = null;
	public static final String PRODUCTS_FILE = "files/carta.dat";
	public Menu() {
		productsList = new ArrayList<Producto>();
		loadFile();
	}
	private void loadFile() {
		FileUtil.loadFile(PRODUCTS_FILE, productsList);
	}
	public Producto[] getTotalProducts() {
		Producto[] total  =  productsList.toArray(new Producto[productsList.size()]);
		Producto pizzaDelDia = new Producto("", "", "", "", "", 0);
		int randomPos = 0;
		while(!pizzaDelDia.getType().equals("Pizza")) {
			randomPos = (int) (Math.random()*total.length);
			pizzaDelDia = total[randomPos];
		}
		Producto aux = total[0];
		total[0] = pizzaDelDia;
		total[randomPos] = aux;
		total[0].setPrice(total[0].getPrice()-(total[0].getPrice()*0.2));
		return total;
	}
	public Producto[] getPizzas() {
		List<Producto> pizzas = new ArrayList<Producto>();
		for(Producto a: productsList) {
			if(a.getType().equals("Pizza")) {
				pizzas.add(a);
			}
		}
		return pizzas.toArray(new Producto[pizzas.size()]);		
	}
	public Producto[] getEntrantes() {
		List<Producto> entrantes = new ArrayList<Producto>();
		for(Producto a: productsList) {
			if(a.getType().equals("Entrante")) {
				entrantes.add(a);
			}
		}
		return entrantes.toArray(new Producto[entrantes.size()]);		
	}
	public Producto[] getPastas() {
		List<Producto> pastas = new ArrayList<Producto>();
		for(Producto a: productsList) {
			if(a.getType().equals("Pasta")) {
				pastas.add(a);
			}
		}
		return pastas.toArray(new Producto[pastas.size()]);		
	}
	public Producto[] getBebidas() {
		List<Producto> bebidas = new ArrayList<Producto>();
		for(Producto a: productsList) {
			if(a.getType().equals("Bebida")) {
				bebidas.add(a);
			}
		}
		return bebidas.toArray(new Producto[bebidas.size()]);		
	}
	public Producto[] getEnsaladas() {
		List<Producto> ensaladas = new ArrayList<Producto>();
		for(Producto a: productsList) {
			if(a.getType().equals("Ensalada")) {
				ensaladas.add(a);
			}
		}
		return ensaladas.toArray(new Producto[ensaladas.size()]);		
	}
	public Producto[] getPostres() {
		List<Producto> postres = new ArrayList<Producto>();
		for(Producto a: productsList) {
			if(a.getType().equals("Postre")) {
				postres.add(a);
			}
		}
		return postres.toArray(new Producto[postres.size()]);		
	}
}
