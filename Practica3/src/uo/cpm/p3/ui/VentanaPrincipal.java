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
		
	}
	private JButton getBtnAñadir() {
		if (btnAñadir == null) {
			btnAñadir = new JButton("A\u00F1adir");
			btnAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirAPedido();
				}
			});
			btnAñadir.setForeground(Color.WHITE);
			btnAñadir.setBackground(new Color(0, 128, 0));
			btnAñadir.setBounds(462, 260, 89, 23);
			
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
	}
	private JLabel getLblEtiquetaUnidades() {
		if (lblEtiquetaUnidades == null) {
			lblEtiquetaUnidades = new JLabel("Unidades:");
			lblEtiquetaUnidades.setBounds(400, 233, 64, 16);
		}
		return lblEtiquetaUnidades;
	}
	private JSpinner getSpinnerCantidad() {
		if (spinnerCantidad == null) {
			spinnerCantidad = new JSpinner();
			spinnerCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spinnerCantidad.setBounds(400, 261, 50, 20);
		}
		return spinnerCantidad;
	}
	private JComboBox<Product> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<Product>();
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getSpinnerCantidad().setValue(1);
					getLblTotalItems().setVisible(true);
					getLblTotalItems().setText("Hay " + mc.searchUnits((Product) getComboBox().getSelectedItem()) + " unidades en el pedido");
				}
			});
			comboBox.setModel(new DefaultComboBoxModel(mc.getMenuProducts()));
			comboBox.setBounds(66, 259, 284, 25);
		}
		return comboBox;
	}
	private JLabel getLblArticulos() {
		if (lblArticulos == null) {
			lblArticulos = new JLabel("Art\u00EDculos:");
			lblArticulos.setBounds(66, 231, 55, 16);
		}
		return lblArticulos;
	}
	private JLabel getLblEtiqueta() {
		if (lblEtiqueta == null) {
			lblEtiqueta = new JLabel("   McDonald's");
			lblEtiqueta.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
			lblEtiqueta.setFont(new Font("Arial Black", Font.BOLD, 44));
			lblEtiqueta.setBackground(Color.WHITE);
			lblEtiqueta.setBounds(75, 34, 525, 150);
		}
		return lblEtiqueta;
	}
	private JLabel getLblEtiquetaPedido() {
		if (lblEtiquetaPedido == null) {
			lblEtiquetaPedido = new JLabel("Precio Pedido:");
			lblEtiquetaPedido.setBounds(460, 297, 86, 14);
		}
		return lblEtiquetaPedido;
	}
	private JTextField getTextoPrecio() {
		if (textoPrecio == null) {
			textoPrecio = new JTextField();
			textoPrecio.setEditable(false);
			textoPrecio.setColumns(10);
			textoPrecio.setBounds(460, 326, 86, 20);
		}
		return textoPrecio;
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					crearVentanaRegistro();
				}
			});
			btnSiguiente.setForeground(Color.WHITE);
			btnSiguiente.setEnabled(false);
			btnSiguiente.setBackground(new Color(0, 128, 0));
			btnSiguiente.setBounds(460, 375, 89, 23);
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
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initialize();
				}
			});
			btnCancelar.setForeground(Color.WHITE);
			btnCancelar.setBackground(Color.RED);
			btnCancelar.setBounds(561, 375, 89, 23);
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
	}
	public McDonalds getMc() {
		return mc;
	}
	private JLabel getLblTotalItems() {
		if (lblTotalItems == null) {
			lblTotalItems = new JLabel();
			lblTotalItems.setForeground(new Color(255, 0, 0));
			lblTotalItems.setBounds(66, 346, 284, 33);
			lblTotalItems.setVisible(false);
		}
		return lblTotalItems;
	}
}
