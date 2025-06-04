package uo.cpm.modulo.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.modulo.model.Casilla;
import uo.cpm.modulo.model.Juego;
import uo.cpm.modulo.model.Tablero;
import uo.cpm.modulo.service.Pizzeria;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

public class VentanaJuego extends JDialog {
	private VentanaPrincipal vr;
	private Pizzeria pz;
	private JPanel panelJuego;
	private JLabel lblTurnos;
	private JLabel lblNumeroTurnos;
	private JLabel lblPremiosGanados;
	private JButton btnFinalizar;
	private JMenuBar menuJuego;
	private JMenu mnAyuda;
	private JMenuItem mntmAbrirAyuda;
	private Juego jg;
	private DestapaCasilla pc = new DestapaCasilla();
	private JPanel panelRewards;
	private ProcessExit pE = new ProcessExit();
	class ProcessExit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {	
			pz.getClient().editTimesOrdered(pz.getClient().getTimesOrdered()+1);
			pz.saveOrder();
			pz.initializeOrder();
			vr.restart();
			dispose();
			
		}
		
	}
	class DestapaCasilla implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {
		    int pos = Integer.parseInt(e.getActionCommand());
		    Casilla[] tablero = jg.getTablero().getTablero();
		    
		    JButton btn = (JButton) getPanelJuego().getComponent(pos);
		    btn.setEnabled(false);
		    btn.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		    setImagenAdaptada(btn, tablero[pos].getImage());
		    
		    jg.choose(pos);
		    if (jg.getCasillaSeleccionada_2() != null) {
		    	int pos1= jg.searchCasilla( jg.getCasillaSeleccionada_1());
	        	int pos2=jg.searchCasilla( jg.getCasillaSeleccionada_2());
	        	JButton btn1 = (JButton) getPanelJuego().getComponent(pos1);
	        	JButton btn2 = (JButton) getPanelJuego().getComponent(pos2);
	        	btn2.setEnabled(false);

		        if (jg.choose_final()) {
		        	JOptionPane.showMessageDialog(null, "¡Has conseguido pareja!","Resultado", JOptionPane.INFORMATION_MESSAGE);
		            String reward = jg.getReward();
		            JLabel lblReward = new JLabel();
		            String parts = reward.split("-")[1];
		            lblReward.setToolTipText("Premio - " + parts.substring(0, parts.length()-4));
		            setImagenAdaptadaEtiqueta(lblReward, reward);
		            getPanelRewards().add(lblReward);
		            System.out.println(getPanelRewards().getComponentCount());
		        } else {
		            // Rehabilito ls botones si no hay pareja
		        	JOptionPane.showMessageDialog(null, "¡Vaya no hubo suerte!","Resultado", JOptionPane.INFORMATION_MESSAGE);
		        	btn1.setEnabled(true);
		        	btn2.setEnabled(true);
		        	btn1.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		        	btn2.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		            setImagenAdaptada(btn1, "/img/interrogacion.png");
		            setImagenAdaptada(btn2, "/img/interrogacion.png");
		        }
		        jg.setCasillaSeleccionada_1(null);
			    jg.setCasillaSeleccionada_2(null);
		    }

	
		    repaint();
		    validate();

		    getLblNumeroTurnos().setText(String.valueOf(jg.getTries()));

		    if (jg.isGameFinished()) {
		        deshabilitarBotones();
		        getPanelRewards().setVisible(true);
				getLblPremiosGanados().setVisible(true);
				getBtnFinalizar().setEnabled(true);
		    }
		}

		
	}
	public void deshabilitarBotones() {
		for(int i=0; i<Tablero.SIZE; i++) {
			getPanelJuego().getComponent(i).setEnabled(false);
		}
	}
	private void cargaAyuda(){

		   URL hsURL;
		   HelpSet hs;

		    try {
			    	File fichero = new File("help/ayuda.hs");
			    	hsURL = fichero.toURI().toURL();
			        hs = new HelpSet(null, hsURL);
			      }

		    catch (Exception e){
		      System.out.println("Ayuda no encontrada");
		      return;
		   }

		   HelpBroker hb = hs.createHelpBroker();
		   hb.initPresentation();

		   hb.enableHelpKey(getRootPane(),"juego", hs);
		   hb.enableHelpOnButton(getMntmAbrirAyuda(), "juego", hs);
		 }
	/**
	 * Create the dialog.
	 */
	public VentanaJuego(VentanaPrincipal vr, Pizzeria pz, Juego jg) {
		setTitle("Uniovi's: Juego");
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaJuego.class.getResource("/img/logo.png")));
		this.vr = vr;
		this.pz  = pz;
		this.jg = jg;
		setBounds(100, 100, 694, 580);
		getContentPane().setLayout(null);
		getContentPane().add(getPanelJuego());
		getContentPane().add(getLblTurnos());
		getContentPane().add(getLblNumeroTurnos());
		getContentPane().add(getLblPremiosGanados());
		getContentPane().add(getBtnFinalizar());
		getContentPane().add(getPanelRewards());
		setJMenuBar(getMenuJuego());
		cargaAyuda();
	}
	private JPanel getPanelJuego() {
		if (panelJuego == null) {
			panelJuego = new JPanel();
			panelJuego.setBounds(10, 10, 660, 370);
			panelJuego.setLayout(new GridLayout(4, 4, 5, 5));
			generarBotones();
		}
		return panelJuego;
	}
	private void generarBotones() {
		for(int i=0; i<Tablero.SIZE; i++) {
			JButton btn = new JButton();
			btn.setText("");
			btn.setActionCommand(String.valueOf(i));
			setImagenAdaptada(btn, "/img/interrogacion.png");
			btn.setActionCommand(String.valueOf(i));
			btn.addActionListener(pc);
			setImagenDeshabilitadaAdaptada(btn, jg.getImage(i));
			getPanelJuego().add(btn);
		}
	}
	private JLabel getLblTurnos() {
		if (lblTurnos == null) {
			lblTurnos = new JLabel("Turnos restantes");
			lblTurnos.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTurnos.setBounds(20, 390, 154, 25);
		}
		return lblTurnos;
	}
	private JLabel getLblNumeroTurnos() {
		if (lblNumeroTurnos == null) {
			lblNumeroTurnos = new JLabel("");
			lblNumeroTurnos.setText(String.valueOf(jg.getTries()));
			lblNumeroTurnos.setFont(new Font("Tahoma", Font.PLAIN, 22));
			lblNumeroTurnos.setHorizontalAlignment(SwingConstants.CENTER);
			lblNumeroTurnos.setBounds(-11, 411, 170, 30);
		}
		return lblNumeroTurnos;
	}
	private JLabel getLblPremiosGanados() {
		if (lblPremiosGanados == null) {
			lblPremiosGanados = new JLabel("Premios ganados");
			lblPremiosGanados.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblPremiosGanados.setBounds(20, 450, 139, 25);
			lblPremiosGanados.setVisible(false);
		}
		return lblPremiosGanados;
	}
	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setEnabled(false);
			btnFinalizar.setMnemonic('F');
			btnFinalizar.setBorder(new LineBorder(new Color(0, 0, 0), 4));
			btnFinalizar.setBackground(new Color(50, 205, 50));
			btnFinalizar.setForeground(new Color(255, 255, 255));
			btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 17));
			btnFinalizar.setBounds(503, 436, 154, 59);
			btnFinalizar.addActionListener(pE);
		}
		return btnFinalizar;
	}
	private JMenuBar getMenuJuego() {
		if (menuJuego == null) {
			menuJuego = new JMenuBar();
			menuJuego.add(getMnAyuda());
		}
		return menuJuego;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('A');
			mnAyuda.add(getMntmAbrirAyuda());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmAbrirAyuda() {
		if (mntmAbrirAyuda == null) {
			mntmAbrirAyuda = new JMenuItem("Abrir Ayuda");
			mntmAbrirAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmAbrirAyuda;
	}
	public void setImagenDeshabilitadaAdaptada(JButton btn, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance((int) (70), (int) (70),Image.SCALE_SMOOTH);
		 //si se quiere reescalar la ventana, este 100 tendria que cambiarse por el width y el height  respectivamente de la imagen para que así se reescale cada vez que cambias el tamaño
		 //además este método debería llamarse en el constructor de la ventana mientras se usa un evento componentResized
		 btn.setDisabledIcon(new ImageIcon(imgEscalada));
	}
	public void setImagenAdaptada(JButton btn, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance((int) (70), (int) (70),Image.SCALE_SMOOTH);
		 //si se quiere reescalar la ventana, este 100 tendria que cambiarse por el width y el height  respectivamente de la imagen para que así se reescale cada vez que cambias el tamaño
		 //además este método debería llamarse en el constructor de la ventana mientras se usa un evento componentResized
		 btn.setIcon(new ImageIcon(imgEscalada));
	}
	private JPanel getPanelRewards() {
		if (panelRewards == null) {
			panelRewards = new JPanel();
			panelRewards.setBackground(new Color(0, 0, 0));
			panelRewards.setBounds(171, 411, 299, 84);
			panelRewards.setVisible(false);
		}
		return panelRewards;
	}
	public void setImagenAdaptadaEtiqueta(JLabel etiqueta, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance((int) (50), (int) (50),Image.SCALE_SMOOTH);
		 //si se quiere reescalar la ventana, este 100 tendria que cambiarse por el width y el height  respectivamente de la imagen para que así se reescale cada vez que cambias el tamaño
		 //además este método debería llamarse en el constructor de la ventana mientras se usa un evento componentResized
		 etiqueta.setIcon(new ImageIcon(imgEscalada));
	}
}
