package uo.cpm.p3;

import java.awt.EventQueue;

import javax.swing.UIManager;

import uo.cpm.p3.service.McDonalds;
import uo.cpm.p3.ui.VentanaPrincipal;

public class Main {
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			McDonalds mc = new McDonalds();
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
