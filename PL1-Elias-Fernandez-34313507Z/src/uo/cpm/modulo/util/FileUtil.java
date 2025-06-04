package uo.cpm.modulo.util;

import java.io.*;

import java.util.*;

import uo.cpm.modulo.model.Cliente;
import uo.cpm.modulo.model.Pedido;
import uo.cpm.modulo.model.Producto;

public abstract class FileUtil {
	
public static void loadFile (String fileName, List<Producto> productsList) {
		
	    String line;
	    String[] productData= null;	   
	    
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader(fileName));
	    		while (file.ready()) {
	    			line = file.readLine();
	    			productData = line.split("@");
	    			productsList.add(new Producto(productData[0],productData[1],productData[2], productData[3], productData[4], Float.parseFloat(productData[5]) ));
	    		}
	    		file.close();
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("File not found.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("I/O Error.");
	    } 
	  }
public static void loadClients (String fileName, List<Cliente> clientList) {
	
    String line;
    String[] clientData= null;	   
    
    try {
    	   BufferedReader file = new BufferedReader(new FileReader(fileName));
    		while (file.ready()) {
    			line = file.readLine();
    			clientData = line.split("@");
    			clientList.add(new Cliente(clientData[0], clientData[1]));
    		}
    		file.close();
    }
    catch (FileNotFoundException fnfe) {
      System.out.println("File not found.");
    }
    catch (IOException ioe) {
      new RuntimeException("I/O Error.");
    } 
  }


	
	
	
	public static String setFileName(){
		String code = "";
		String base = "0123456789abcdefghijklmnopqrstuvwxyz";
		int length = 8;
		for(int i=0; i<length;i++){ 
			int numero = (int)(Math.random()*(base.length())); 
			code += base.charAt(numero);
		}
		return code;
	}

	public static void saveToFile(Pedido order) {
	try {
		        BufferedWriter file = new BufferedWriter(new FileWriter("files/" + order.getCustomer().getCode() +order.getCustomer().getTimesOrdered()+ ".dat"));
		        StringBuilder sb = new StringBuilder();
		        for(Producto a: order.getOrderList()) {
		        	sb.append(a.toString()+"\n");
		        }
		        sb.append(order.getObservations());
		        //String line = order.getOrderList().toString();
		        file.write(sb.toString());
		        file.close();
			}

		catch (FileNotFoundException fnfe) {
		      System.out.println("The file could not be saved.");
		    }
		catch (IOException ioe) {
		      new RuntimeException("I/O Error.");
		}
	  }
}
