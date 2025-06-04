package uo.cpm.p11;

import java.awt.EventQueue;

import javax.swing.UIManager;

import uo.cpm.p11.service.*;
import uo.cpm.p11.ui.VentanaPrincipal;

public class Main {

	public static void main(String[] args) {
		final McDonalds mc = new McDonalds();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					VentanaPrincipal frame = new VentanaPrincipal(mc);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}