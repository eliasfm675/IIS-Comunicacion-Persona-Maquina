package uo.cpm.modulo;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.UIManager;

import uo.cpm.modulo.service.Pizzeria;
import uo.cpm.modulo.ui.VentanaPrincipal;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pizzeria pz = new Pizzeria();
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					VentanaPrincipal frame = new VentanaPrincipal(pz);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
