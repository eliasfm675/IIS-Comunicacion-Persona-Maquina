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

import uo.cpm.modulo.model.Producto;
import uo.cpm.modulo.service.Pizzeria;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.JScrollPane;

public class VentanaProducto extends JDialog {
	private JPanel panelButtons;
	private JButton btnEliminar;
	private JButton btnAnadir;
	private JButton btnCancelar;
	private JButton btnSiguiente;
	private JLabel lblObservaciones;
	private JTextField textFieldObservaciones;
	private JLabel lblCantidad;
	private JSpinner spinnerCantidad;
	private JLabel lblProducto;
	private JCheckBox chckbxIntolerancias;
	private JLabel lblIngredientes;
	private JTextArea textAreaIngredientes;
	private Producto producto;
	private VentanaPrincipal vr;
	private Pizzeria pz;
	private ProcessExit pe = new ProcessExit();
	private JScrollPane scrollPaneIngredientes;
	private ProcessUnits pu = new ProcessUnits();
	private ProcessAccept pa = new ProcessAccept();
	
	class ProcessAccept implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			pz.setUnitsChanged(0);
			vr.getTxtAreaCarrito().setText(pz.getOrderToStringShoppingCart());
			if(getChckbxIntolerancias().isSelected()) {
				pz.appendObservationsGluten(producto);
			}
			if(!getTextFieldObservaciones().getText().isBlank() || !getTextFieldObservaciones().getText().isEmpty()) {
				pz.appendObservations(producto.getName() + " - " + getTextFieldObservaciones().getText());
			}	
			//vr.getTextAreaPedidos().setText(pz.getObservations());
			vr.getTextAreaObservaciones().setText(pz.getObservations());
			dispose();
			
		}
		
	}
	class ProcessUnits implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int units = (int) getSpinnerCantidad().getValue();
			if(e.getActionCommand().equals("Anadir")) {
				pz.addToOrder(producto, units);
				getTextAreaIngredientes().setText(producto.toStringBuyScreen());
			}else {
				pz.changeUnits(producto, units);
				getTextAreaIngredientes().setText(producto.toStringBuyScreen());
			}
			vr.getPanelPedidoPapelera().setLayout(new GridLayout(pz.getOrder().getOrderList().size(), 1, 0, 0));
			vr.getPanelPedidoPapelera().removeAll();
			vr.generarPedido();
			validate();
			
			
		}
		
	}
	class ProcessExit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			pz.addToOrder(producto, pz.getUnitsChanged());
			dispose();
			
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

		   hb.enableHelpKey(getRootPane(),"producto", hs);
		 }

	/**
	 * Create the dialog.
	 * @param pz 
	 */
	public VentanaProducto(VentanaPrincipal vr, Pizzeria pz, Producto producto) {
		getContentPane().setBackground(new Color(0, 0, 139));
		this.producto = producto;
		pz.setUnitsChanged(producto.getUnits());
		this.vr = vr;
		this.pz = pz;
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaProducto.class.getResource("/img/logo.png")));
		setBounds(100, 100, 462, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle(producto.getName());
		getContentPane().setLayout(null);
		getContentPane().add(getPanelButtons());
		getContentPane().add(getLblObservaciones());
		getContentPane().add(getTextFieldObservaciones());
		getContentPane().add(getLblCantidad());
		getContentPane().add(getSpinnerCantidad());
		getContentPane().add(getLblProducto());
		getContentPane().add(getChckbxIntolerancias());
		getContentPane().add(getLblIngredientes());
		getContentPane().add(getScrollPaneIngredientes());
		cargaAyuda();
	}
	private JPanel getPanelButtons() {
		if (panelButtons == null) {
			panelButtons = new JPanel();
			panelButtons.setBackground(new Color(0, 0, 139));
			panelButtons.setBounds(249, 187, 187, 64);
			panelButtons.setLayout(new GridLayout(2, 2, 5, 5));
			panelButtons.add(getBtnEliminar());
			panelButtons.add(getBtnAnadir());
			panelButtons.add(getBtnCancelar());
			panelButtons.add(getBtnSiguiente());
		}
		return panelButtons;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setMnemonic('E');
			btnEliminar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setBackground(new Color(50, 205, 50));
			btnEliminar.setActionCommand("Eliminar");
			btnEliminar.addActionListener(pu);
		}
		return btnEliminar;
	}
	private JButton getBtnAnadir() {
		if (btnAnadir == null) {
			btnAnadir = new JButton("A\u00F1adir");
			btnAnadir.setMnemonic('A');
			btnAnadir.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			btnAnadir.setBackground(new Color(50, 205, 50));
			btnAnadir.setForeground(new Color(255, 255, 255));
			btnAnadir.setActionCommand("Anadir");
			btnAnadir.addActionListener(pu);
		}
		return btnAnadir;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setMnemonic('C');
			btnCancelar.setBackground(new Color(255, 0, 0));
			btnCancelar.setForeground(new Color(255, 255, 255));
			btnCancelar.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			btnCancelar.addActionListener(pe);
		}
		return btnCancelar;
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setMnemonic('S');
			btnSiguiente.setBorder(new LineBorder(new Color(0, 0, 0), 2));
			btnSiguiente.setForeground(new Color(255, 255, 255));
			btnSiguiente.setBackground(new Color(50, 205, 50));
			btnSiguiente.addActionListener(pa);
		}
		return btnSiguiente;
	}
	private JLabel getLblObservaciones() {
		if (lblObservaciones == null) {
			lblObservaciones = new JLabel("Observaciones: ");
			lblObservaciones.setForeground(new Color(255, 255, 255));
			lblObservaciones.setLabelFor(getTextFieldObservaciones());
			lblObservaciones.setDisplayedMnemonic('O');
			lblObservaciones.setBounds(12, 200, 93, 16);
		}
		return lblObservaciones;
	}
	private JTextField getTextFieldObservaciones() {
		if (textFieldObservaciones == null) {
			textFieldObservaciones = new JTextField();
			textFieldObservaciones.setBounds(133, 198, 104, 33);
			textFieldObservaciones.setColumns(10);
		}
		return textFieldObservaciones;
	}
	private JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Cantidad:");
			lblCantidad.setForeground(new Color(255, 255, 255));
			lblCantidad.setLabelFor(getSpinnerCantidad());
			lblCantidad.setDisplayedMnemonic('C');
			lblCantidad.setBounds(12, 228, 55, 16);
		}
		return lblCantidad;
	}
	private JSpinner getSpinnerCantidad() {
		if (spinnerCantidad == null) {
			spinnerCantidad = new JSpinner();
			spinnerCantidad.setBackground(new Color(0, 0, 0));
			spinnerCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spinnerCantidad.setBounds(78, 228, 41, 23);
		}
		return spinnerCantidad;
	}
	private JLabel getLblProducto() {
		if (lblProducto == null) {
			lblProducto = new JLabel("");
			lblProducto.setBackground(new Color(0, 0, 205));
			lblProducto.setBounds(12, 12, 177, 155);
			setImagenAdaptada(lblProducto, "/img/" + producto.getCode() + ".png");
			
		}
		
		return lblProducto;
	}
	public void setImagenAdaptada(JLabel etiqueta, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance((int) (100), (int) (100),Image.SCALE_SMOOTH);
		 //si se quiere reescalar la ventana, este 100 tendria que cambiarse por el width y el height  respectivamente de la imagen para que as� se reescale cada vez que cambias el tama�o
		 //adem�s este m�todo deber�a llamarse en el constructor de la ventana mientras se usa un evento componentResized
		 etiqueta.setIcon(new ImageIcon(imgEscalada));
	}
	private JCheckBox getChckbxIntolerancias() {
		if (chckbxIntolerancias == null) {
			chckbxIntolerancias = new JCheckBox("Sin gluten / Intolerancias");
			chckbxIntolerancias.setForeground(new Color(255, 255, 255));
			chckbxIntolerancias.setBounds(8, 168, 199, 24);
			if(!producto.hasIntolerance()) {
				chckbxIntolerancias.setVisible(false);
				chckbxIntolerancias.setEnabled(false);
			}else {
				chckbxIntolerancias.setVisible(true);
				chckbxIntolerancias.setEnabled(true);
			}
		}
		return chckbxIntolerancias;
	}
	private JLabel getLblIngredientes() {
		if (lblIngredientes == null) {
			lblIngredientes = new JLabel("Ingredientes");
			lblIngredientes.setForeground(new Color(255, 255, 255));
			lblIngredientes.setFont(new Font("Dialog", Font.BOLD, 14));
			lblIngredientes.setBounds(261, 12, 135, 33);
		}
		return lblIngredientes;
	}
	private JTextArea getTextAreaIngredientes() {
		if (textAreaIngredientes == null) {
			textAreaIngredientes = new JTextArea();
			textAreaIngredientes.setEditable(false);
			textAreaIngredientes.setText(producto.toStringBuyScreen());
		}
		return textAreaIngredientes;
	}
	private JScrollPane getScrollPaneIngredientes() {
		if (scrollPaneIngredientes == null) {
			scrollPaneIngredientes = new JScrollPane();
			scrollPaneIngredientes.setBounds(261, 57, 150, 110);
			scrollPaneIngredientes.setViewportView(getTextAreaIngredientes());
		}
		return scrollPaneIngredientes;
	}
}
