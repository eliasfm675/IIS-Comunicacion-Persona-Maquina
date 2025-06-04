package uo.cpm.p3.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p3.model.Product;
import uo.cpm.p3.service.McDonalds;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.DropMode;
import java.awt.Component;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private McDonalds mc;
	private JButton btnAñadir;
	private JLabel lblEtiquetaUnidades;
	private JSpinner spinnerCantidad;
	private JComboBox<Product> comboBox;
	private JLabel lblArticulos;
	private JLabel lblEtiqueta;
	private JLabel lblEtiquetaPedido;
	private JTextField textoPrecio;
	private JButton btnSiguiente;
	private JButton btnCancelar;
	private JLabel lblTotalItems;
	private JButton btnEliminar;
	private JToggleButton tglbtnCarrito;
	private JScrollPane scrollPanePedido;
	private JTextArea textAreaPedido;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaPrincipal frame = new VentanaPrincipal();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(McDonalds mc) {
		this.mc = mc;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.png")));
		setTitle("McDonald's Espa\u00F1a");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 487);
		setLocationRelativeTo(null); //hay que ponerlo debajo del setBounds
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 666, 1);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		contentPane.add(getBtnAñadir());
		contentPane.add(getLblEtiquetaUnidades());
		contentPane.add(getSpinnerCantidad());
		contentPane.add(getComboBox());
		contentPane.add(getLblArticulos());
		contentPane.add(getLblEtiqueta());
		contentPane.add(getLblEtiquetaPedido());
		contentPane.add(getTextoPrecio());
		contentPane.add(getBtnSiguiente());
		contentPane.add(getBtnCancelar());
		contentPane.add(getLblTotalItems());
		contentPane.add(getBtnEliminar());
		contentPane.add(getTglbtnCarrito());
		contentPane.add(getScrollPanePedido());
		
	}
	private JButton getBtnAñadir() {
		if (btnAñadir == null) {
			btnAñadir = new JButton("A\u00F1adir");
			btnAñadir.setBounds(462, 260, 89, 23);
			btnAñadir.setMnemonic('A');
			btnAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirAPedido();
				}
			});
			btnAñadir.setForeground(Color.WHITE);
			btnAñadir.setBackground(new Color(0, 128, 0));
			
		}
		return btnAñadir;
	}
	private void añadirAPedido() {
		Product articuloSeleccionado = (Product)getComboBox().getSelectedItem();
		int unidadesSolicitadas = (int)getSpinnerCantidad().getValue();
		mc.addToOrder(articuloSeleccionado, unidadesSolicitadas);
		getLblTotalItems().setText("Hay " + mc.searchUnits(articuloSeleccionado) + " unidades en el pedido");
		getLblTotalItems().setVisible(true);
		String precio = String.format("%.2f",  mc.getOrderTotal());
		getTextoPrecio().setText(precio + " €");
		if(!getBtnSiguiente().isEnabled())
			getBtnSiguiente().setEnabled(true);
		if(!getBtnEliminar().isEnabled()) {
			getBtnEliminar().setEnabled(true);
		}
		getTextAreaPedido().setText(getShoppingCart());
	}
	private JLabel getLblEtiquetaUnidades() {
		if (lblEtiquetaUnidades == null) {
			lblEtiquetaUnidades = new JLabel("Unidades:");
			lblEtiquetaUnidades.setBounds(400, 233, 64, 16);
			lblEtiquetaUnidades.setLabelFor(getSpinnerCantidad());
			lblEtiquetaUnidades.setDisplayedMnemonic('U');
		}
		return lblEtiquetaUnidades;
	}
	private JSpinner getSpinnerCantidad() {
		if (spinnerCantidad == null) {
			spinnerCantidad = new JSpinner();
			spinnerCantidad.setBounds(400, 261, 50, 33);
			spinnerCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		}
		return spinnerCantidad;
	}
	private JComboBox<Product> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<Product>();
			comboBox.setBounds(66, 259, 284, 25);
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getSpinnerCantidad().setValue(1);
					getLblTotalItems().setVisible(true);
					getLblTotalItems().setText("Hay " + mc.searchUnits((Product) getComboBox().getSelectedItem()) + " unidades en el pedido");
				}
			});
			comboBox.setModel(new DefaultComboBoxModel(mc.getMenuProducts()));
		}
		return comboBox;
	}
	private JLabel getLblArticulos() {
		if (lblArticulos == null) {
			lblArticulos = new JLabel("Art\u00EDculos:");
			lblArticulos.setBounds(66, 231, 55, 16);
			lblArticulos.setLabelFor(getComboBox());
			lblArticulos.setDisplayedMnemonic('r');
		}
		return lblArticulos;
	}
	private JLabel getLblEtiqueta() {
		if (lblEtiqueta == null) {
			lblEtiqueta = new JLabel("   McDonald's");
			lblEtiqueta.setBounds(25, 47, 446, 171);
			lblEtiqueta.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
			lblEtiqueta.setFont(new Font("Arial Black", Font.BOLD, 39));
			lblEtiqueta.setBackground(Color.WHITE);
		}
		return lblEtiqueta;
	}
	private JLabel getLblEtiquetaPedido() {
		if (lblEtiquetaPedido == null) {
			lblEtiquetaPedido = new JLabel("Precio Pedido");
			lblEtiquetaPedido.setBounds(460, 297, 86, 14);
		}
		return lblEtiquetaPedido;
	}
	private JTextField getTextoPrecio() {
		if (textoPrecio == null) {
			textoPrecio = new JTextField();
			textoPrecio.setBounds(460, 326, 86, 27);
			textoPrecio.setEditable(false);
			textoPrecio.setColumns(10);
		}
		return textoPrecio;
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setBounds(460, 375, 89, 23);
			btnSiguiente.setMnemonic('S');
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					crearVentanaRegistro();
				}
			});
			btnSiguiente.setForeground(Color.WHITE);
			btnSiguiente.setEnabled(false);
			btnSiguiente.setBackground(new Color(0, 128, 0));
		}
		return btnSiguiente;
	}
	private void crearVentanaRegistro() {
		VentanaRegistro vr = new VentanaRegistro(this);
		vr.setVisible(true);
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(561, 375, 89, 23);
			btnCancelar.setMnemonic('C');
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initialize();
				}
			});
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setBackground(Color.RED);
		}
		return btnCancelar;
	}
	protected void initialize() {
		mc.initOrder();
		initializeWindow();
	}
	private void initializeWindow() {
		getComboBox().setSelectedIndex(0);
		getTextoPrecio().setText("");
		getBtnSiguiente().setEnabled(false);
		getSpinnerCantidad().setValue(1);
		getLblTotalItems().setVisible(false);
		getBtnEliminar().setEnabled(false);
		getTextAreaPedido().setText(getShoppingCart());
		getScrollPanePedido().setVisible(false);
		getTextAreaPedido().setVisible(false);
	}
	public McDonalds getMc() {
		return mc;
	}
	private JLabel getLblTotalItems() {
		if (lblTotalItems == null) {
			lblTotalItems = new JLabel();
			lblTotalItems.setBounds(66, 346, 284, 33);
			lblTotalItems.setForeground(new Color(255, 0, 0));
			lblTotalItems.setVisible(false);
		}
		return lblTotalItems;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setBounds(563, 260, 89, 23);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteProduct();
				}
				}
			);
			btnEliminar.setEnabled(false);
			btnEliminar.setMnemonic('E');
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setBackground(new Color(0, 128, 0));
		}
		return btnEliminar;
	}
private void deleteProduct() {
	Product selectedProduct = (Product)getComboBox().getSelectedItem();
	int unitsToRemove = (int) getSpinnerCantidad().getValue() ;
	if(mc.searchUnits(selectedProduct)>0) {
		mc.changeUnits(selectedProduct, unitsToRemove);
	}
	if(mc.searchUnits(selectedProduct)==0) {
		mc.remove(selectedProduct);
		getBtnSiguiente().setEnabled(false);
		getBtnEliminar().setEnabled(false);
	}
	if(mc.searchUnits(selectedProduct)<0) {
		mc.changeUnits(selectedProduct, 0);
	}
	getTextoPrecio().setText(String.format("%.2f €", mc.getOrderTotal()));
	getLblTotalItems().setText("Hay " + mc.searchUnits(selectedProduct) + " unidades en el pedido");
	if((float)mc.getOrderTotal()==0) {
		getTextoPrecio().setText("");
	}
	getTextAreaPedido().setText(getShoppingCart());
	}
	private JToggleButton getTglbtnCarrito() {
		if (tglbtnCarrito == null) {
			tglbtnCarrito = new JToggleButton("");
			tglbtnCarrito.setBounds(602, 19, 64, 55);
			tglbtnCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showShoppingCart();
				}
			});
			tglbtnCarrito.setToolTipText("Carrito\r\n");
			tglbtnCarrito.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/pedido.png")));
		}
		return tglbtnCarrito;
	}
	private void showShoppingCart() {
		if(!getScrollPanePedido().isVisible()) {
			getScrollPanePedido().setVisible(true);
			getTextAreaPedido().setVisible(true);
		}else {
			getScrollPanePedido().setVisible(false);
			getTextAreaPedido().setVisible(false);
		}
		
		
	}
	private JScrollPane getScrollPanePedido() {
		if (scrollPanePedido == null) {
			scrollPanePedido = new JScrollPane(getTextAreaPedido());
			scrollPanePedido.setBounds(473, 86, 177, 133);
			scrollPanePedido.setVisible(false);
			scrollPanePedido.setBackground(new Color(255, 255, 255));
		}
		return scrollPanePedido;
	}
	private JTextArea getTextAreaPedido() {
		if (textAreaPedido == null) {
			textAreaPedido = new JTextArea();
			textAreaPedido.setEditable(false);
			textAreaPedido.setBounds(473, 86, 177, 133);
			textAreaPedido.setWrapStyleWord(true);
			textAreaPedido.setLineWrap(true);
			textAreaPedido.setVisible(false);
			textAreaPedido.setBackground(new Color(255, 255, 255));
		}
		return textAreaPedido;
	}
	private String getShoppingCart() {
		return mc.getOrderToStringPedido();
	}
}
