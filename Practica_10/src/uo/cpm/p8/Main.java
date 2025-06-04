package uo.cpm.p8;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import uo.cpm.p8.player.MusicPlayer;
import uo.cpm.p8.ui.VentanaPrincipal;


public class Main {
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		      try {
		    	  Properties props = new Properties();
		    	  props.put("logoString", "");
		    	  HiFiLookAndFeel.setCurrentTheme(props);
		    	  UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		    	  MusicPlayer mp = new MusicPlayer();
		    	  VentanaPrincipal v = new VentanaPrincipal(mp);
		    	  v.setVisible(true);
		      } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error: "+e);
		      }
		    }
		  });

	}

}