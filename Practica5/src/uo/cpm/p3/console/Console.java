package uo.cpm.p3.console;

import java.util.List;
import java.util.Scanner;

import uo.cpm.p3.model.Product;
import uo.cpm.p3.service.McDonalds;

public class Console {
	private McDonalds mc = new McDonalds();
	private Scanner sc = new Scanner(System.in);
	public void setUp() {
		mc.initOrder();
		signUp();
		int choice=1;
		int units = 0;
		System.out.println("Escoge una opción");
		while(choice>0) {
			System.out.println("Llevas esta cantidad en productos: " + mc.getOrderTotal());
			printMenu(mc.getMenuProducts());
			choice = sc.nextInt();
			System.out.println("¿Cuántas unidades?");
			units = sc.nextInt();
			mc.addToOrder(mc.getMenuProducts()[choice-1], units);
			System.out.println("¿Algo más? s/n");
			String exit = sc.next();
			if(exit.equals("n")) {
				choice=0;
			}
		}
		System.out.println("Llevas esta cantidad en productos: " + mc.getOrderTotal());
		System.out.println("¿Lo quiere para llevar? s/n");
		String takeAway=sc.next();
		if(takeAway.equals("s")) {
			mc.setOrderType(true);
		}else {
			mc.setOrderType(false);
		}
		mc.saveOrder();
		mc.getOrderCode();
		System.out.println("¡Adios!");
		System.exit(0);
	}
	private void printMenu(Product[] products) {
		System.out.println("McMenús parrilla y demás ");
		System.out.println("-----------------------------");
		for(int i=0; i<products.length; i++) {
			System.out.println((i+1) + ": " + products[i]);
		}
		System.out.println(0 + ": Salir");
	}
	private void signUp() {
		System.out.println("Bienvenido a McDonald's");
		System.out.println("-----------------------------");
		System.out.println("Escribe tu nombre y apellidos");
		String name = sc.next();
		System.out.println("Escribe tu edad");
		int age = sc.nextInt();
		System.out.println("Escribe tu contraseña");
		String password = sc.next();
		System.out.println("Confirma tu contraseña");
		String confirm = sc.next();
		while(!password.equals(confirm)) {
			System.out.println("Por favor introduce la misma contraseña");
			confirm = sc.next();
		}
		mc.saveCustomerData(name, age, confirm);
	}

}

