package uo.cpm.p6;

import uo.cpm.p6.service.SpaceInvaders;
import uo.cpm.p6.ui.VentanaPrincipal;

import java.awt.EventQueue;
import java.util.Properties;
import javax.swing.UIManager;


public class Main {

	public static void main(String[] args) {
        SpaceInvaders spaceInvaders = new SpaceInvaders();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 /* Properties props = new Properties();
			    	  props.put("logoString", "");
			    	  HiFiLookAndFeel.setCurrentTheme(props);*/
			    	  UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");		
			    	  VentanaPrincipal frame = new VentanaPrincipal(spaceInvaders);
					    frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
