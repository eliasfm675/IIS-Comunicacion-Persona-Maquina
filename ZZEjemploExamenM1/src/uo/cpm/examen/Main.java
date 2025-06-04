package uo.cpm.examen;

import java.awt.EventQueue;

import uo.cpm.examen.service.Ruleta;
import uo.cpm.examen.ui.*;

public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ruleta rul = new Ruleta();
					VentanaPrincipal frame = new VentanaPrincipal(rul);
					 frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
