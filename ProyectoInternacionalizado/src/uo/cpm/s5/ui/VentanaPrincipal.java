package uo.cpm.s5.ui;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.text.DateFormat;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnBotones;
	private JPanel pnFechaHora;
	private JPanel pnBandera;
	private JButton btEspanol;
	private JButton btIngles;
	private JButton btFrances;
	private JLabel lbFecha;
	private JLabel lbHora;
	private JLabel lbBandera;
	private JTextArea taTexto;
	private JScrollPane scrollPane;
	private ProcessAction pa = new ProcessAction();
	private JButton btItaliano;
	class ProcessAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			localizar(new Locale(btn.getActionCommand()));
			
		}
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setTitle("Ejemplo de Internacionalizacion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 320);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnBotones(), BorderLayout.NORTH);
		contentPane.add(getPnFechaHora(), BorderLayout.SOUTH);
		contentPane.add(getPnBandera(), BorderLayout.WEST);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		localizar(new Locale("es"));//por defecto
		
	}
	
	private void localizar(Locale localizacion) {	
		ResourceBundle textos = ResourceBundle.getBundle("rcs/textos",localizacion);
		setTitle(textos.getString("title"));
		String imagenBandera = "/img/" + localizacion.getLanguage() + ".png";
		getLbBandera().setIcon(new ImageIcon(VentanaPrincipal.class.getResource(imagenBandera)));
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource(imagenBandera)));
		getTaTexto().setText(textos.getString("text"));
		getBtEspanol().setToolTipText(textos.getString("tooltip.spanish"));
		getBtIngles().setToolTipText(textos.getString("tooltip.english"));
		getBtFrances().setToolTipText(textos.getString("tooltip.french"));
		getBtItaliano().setToolTipText(textos.getString("tooltip.italian"));
		Date fechaHora = new Date();
		DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.LONG, localizacion);
		//Falta que la fecha tenga el formato correspondiente a la localizacion
		lbFecha.setText(formatoFecha.format(fechaHora));
		
		DateFormat formatoHora = DateFormat.getTimeInstance(DateFormat.LONG,localizacion);
		lbHora.setText(formatoHora.format(fechaHora));
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pnBotones.setBackground(Color.WHITE);
			FlowLayout flowLayout = (FlowLayout) pnBotones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBotones.add(getBtEspanol());
			pnBotones.add(getBtIngles());
			pnBotones.add(getBtFrances());
			pnBotones.add(getBtItaliano());
		}
		return pnBotones;
	}
	private JPanel getPnFechaHora() {
		if (pnFechaHora == null) {
			pnFechaHora = new JPanel();
			pnFechaHora.setLayout(new GridLayout(1, 2, 0, 0));
			pnFechaHora.add(getLbFecha());
			pnFechaHora.add(getLbHora());
		}
		return pnFechaHora;
	}
	private JPanel getPnBandera() {
		if (pnBandera == null) {
			pnBandera = new JPanel();
			pnBandera.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pnBandera.setBackground(Color.WHITE);
			pnBandera.add(getLbBandera());
		}
		return pnBandera;
	}
	private JButton getBtEspanol() {
		if (btEspanol == null) {
			btEspanol = new JButton("es");
			btEspanol.setMnemonic('e');
			btEspanol.setActionCommand("es");
//			btEspanol.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent arg0) {
//					//localizar(Locale.of("es"));	
//					localizar(new Locale("es"));
//					//(Nota: Sentencia equivalente a: localizar(new Locale("es")), que en la versión actual de java está deprecated) 
//				}
//			});
			btEspanol.addActionListener(pa);
		}
		return btEspanol;
	}
	private JButton getBtIngles() {
		if (btIngles == null) {
			btIngles = new JButton("en");
			btIngles.setMnemonic('n');
			btIngles.setActionCommand("en");
			btIngles.addActionListener(pa);
		}
		return btIngles;
	}
	private JButton getBtFrances() {
		if (btFrances == null) {
			btFrances = new JButton("fr");
			btFrances.setMnemonic('f');
			btFrances.setActionCommand("fr");
			btFrances.addActionListener(pa);
		}
		return btFrances;
	}
	private JLabel getLbFecha() {
		if (lbFecha == null) {
			lbFecha = new JLabel("");
		}
		return lbFecha;
	}
	private JLabel getLbHora() {
		if (lbHora == null) {
			lbHora = new JLabel("");
		}
		return lbHora;
	}
	private JLabel getLbBandera() {
		if (lbBandera == null) {
			lbBandera = new JLabel("");
			lbBandera.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/es.PNG")));
		}
		return lbBandera;
	}
	private JTextArea getTaTexto() {
		if (taTexto == null) {
			taTexto = new JTextArea();
			taTexto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			taTexto.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));
			taTexto.setWrapStyleWord(true);
			taTexto.setLineWrap(true);
		}
		return taTexto;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTaTexto());
		}
		return scrollPane;
	}
	private JButton getBtItaliano() {
		if (btItaliano == null) {
			btItaliano = new JButton("it");
			btItaliano.setToolTipText((String) null);
			btItaliano.setMnemonic('i');
			btItaliano.setActionCommand("it");
			btItaliano.addActionListener(pa);
		}
		return btItaliano;
	}
}
