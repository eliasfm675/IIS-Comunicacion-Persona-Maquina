package uo.cpm.s5;

import javax.swing.*;
import uo.cpm.s5.ui.VentanaPrincipal;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		      try {
		    	  UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		    	  VentanaPrincipal v = new VentanaPrincipal();
		    	  v.setVisible(true);
		      } catch (Exception e) {
		          JOptionPane.showMessageDialog(null, "Error: "+e);
		      }
		    }
		  });

	}

}
