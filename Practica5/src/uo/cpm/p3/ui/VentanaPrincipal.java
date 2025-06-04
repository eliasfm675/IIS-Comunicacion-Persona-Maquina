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
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import java.awt.GridLayout;

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
	private JLabel lblArticulo;
	private JMenuBar menuBar;
	private JMenu mnPedido;
	private JMenu mnAyuda;
	private JMenuItem mntmNuevo;
	private JMenuItem mntmSalir;
	private JSeparator separator;
	private JMenuItem mntmContenidos;
	private JMenuItem mntmAcercaDe;
	private JSeparator separator_1;
	private JPanel panel_1;
	private JButton btnHamburguesas;
	private JButton btnTodos;
	private JButton btnPostres;
	private JButton btnBebidas;
	private JButton btnComplementos;

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
		setBounds(100, 100, 886, 550);
		setLocationRelativeTo(null); //hay que ponerlo debajo del setBounds
		setJMenuBar(getMenuBar_1());
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
		contentPane.add(getLblArticulo());
		contentPane.add(getPanel_1());
		
	}
	private JButton getBtnAñadir() {
		if (btnAñadir == null) {
			btnAñadir = new JButton("A\u00F1adir");
			btnAñadir.setBounds(644, 282, 89, 23);
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
			lblEtiquetaUnidades.setBounds(568, 248, 64, 16);
			lblEtiquetaUnidades.setLabelFor(getSpinnerCantidad());
			lblEtiquetaUnidades.setDisplayedMnemonic('U');
		}
		return lblEtiquetaUnidades;
	}
	private JSpinner getSpinnerCantidad() {
		if (spinnerCantidad == null) {
			spinnerCantidad = new JSpinner();
			spinnerCantidad.setBounds(568, 277, 50, 33);
			spinnerCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		}
		return spinnerCantidad;
	}
	private JComboBox<Product> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<Product>();
			comboBox.setBounds(236, 281, 284, 25);
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarComboBox();
				}
			});
			comboBox.setModel(new DefaultComboBoxModel(mc.getMenuProducts()));
		}
		return comboBox;
	}
	private void actualizarComboBox() {
		getSpinnerCantidad().setValue(1);
		getLblTotalItems().setVisible(true);
		Product selectedItem = (Product) getComboBox().getSelectedItem();
		cambiarImagen();
		if(mc.searchUnits(selectedItem)==0) {
			getBtnEliminar().setEnabled(false);
		}
		getLblTotalItems().setText("Hay " + mc.searchUnits((Product) getComboBox().getSelectedItem()) + " unidades en el pedido");
	}
	private void cambiarImagen() {
		Product selectedItem = (Product)getComboBox().getSelectedItem();
		getLblArticulo().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/"+selectedItem.getCode()+".png")));
	}
	private JLabel getLblArticulos() {
		if (lblArticulos == null) {
			lblArticulos = new JLabel("Art\u00EDculos:");
			lblArticulos.setBounds(244, 248, 55, 16);
			lblArticulos.setLabelFor(getComboBox());
			lblArticulos.setDisplayedMnemonic('r');
		}
		return lblArticulos;
	}
	private JLabel getLblEtiqueta() {
		if (lblEtiqueta == null) {
			lblEtiqueta = new JLabel("   McDonald's");
			lblEtiqueta.setBounds(167, 44, 446, 171);
			lblEtiqueta.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
			lblEtiqueta.setFont(new Font("Arial Black", Font.BOLD, 39));
			lblEtiqueta.setBackground(Color.WHITE);
		}
		return lblEtiqueta;
	}
	private JLabel getLblEtiquetaPedido() {
		if (lblEtiquetaPedido == null) {
			lblEtiquetaPedido = new JLabel("Precio Pedido");
			lblEtiquetaPedido.setBounds(561, 335, 86, 14);
		}
		return lblEtiquetaPedido;
	}
	private JTextField getTextoPrecio() {
		if (textoPrecio == null) {
			textoPrecio = new JTextField();
			textoPrecio.setBounds(561, 369, 86, 27);
			textoPrecio.setEditable(false);
			textoPrecio.setColumns(10);
		}
		return textoPrecio;
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setBounds(644, 429, 89, 23);
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
			btnCancelar.setBounds(745, 429, 89, 23);
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
			lblTotalItems.setBounds(249, 298, 284, 33);
			lblTotalItems.setForeground(new Color(255, 0, 0));
			lblTotalItems.setVisible(false);
		}
		return lblTotalItems;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setBounds(745, 282, 89, 23);
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
			tglbtnCarrito.setBounds(770, 15, 64, 55);
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
			scrollPanePedido = new JScrollPane();
			scrollPanePedido.setBounds(657, 82, 177, 133);
			scrollPanePedido.setVisible(false);
			scrollPanePedido.setBackground(new Color(255, 255, 255));
			scrollPanePedido.setViewportView(getTextAreaPedido());
		}
		return scrollPanePedido;
	}
	private JTextArea getTextAreaPedido() {
		if (textAreaPedido == null) {
			textAreaPedido = new JTextArea();
			textAreaPedido.setRows(5);
			textAreaPedido.setColumns(4);
			textAreaPedido.setEditable(false);
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
	private JLabel getLblArticulo() {
		if (lblArticulo == null) {
			lblArticulo = new JLabel("");
			lblArticulo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/HA01.png")));
			lblArticulo.setBounds(309, 351, 177, 126);
		}
		return lblArticulo;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnPedido());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnPedido() {
		if (mnPedido == null) {
			mnPedido = new JMenu("Pedido");
			mnPedido.setMnemonic('P');
			mnPedido.add(getMntmNuevo());
			mnPedido.add(getSeparator());
			mnPedido.add(getMntmSalir());
		}
		return mnPedido;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('d');
			mnAyuda.add(getMntmContenidos());
			mnAyuda.add(getSeparator_1());
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmNuevo() {
		if (mntmNuevo == null) {
			mntmNuevo = new JMenuItem("Nuevo");
			mntmNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initialize();
				}
			});
			mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmNuevo;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return mntmSalir;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmContenidos() {
		if (mntmContenidos == null) {
			mntmContenidos = new JMenuItem("Contenidos");
			mntmContenidos.setEnabled(false);
			mntmContenidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		}
		return mntmContenidos;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de");
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, " Aplicación para TPV de comida \n Realizada por Elías Fernández Medina \n Prácticas CPM 24-25 \n EII Oviedo", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return mntmAcercaDe;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(new Color(255, 0, 0));
			panel_1.setBounds(0, 0, 112, 490);
			panel_1.setLayout(new GridLayout(5, 1, 3, 3));
			panel_1.add(getBtnTodos());
			panel_1.add(getBtnHamburguesas());
			panel_1.add(getBtnBebidas());
			panel_1.add(getBtnComplementos());
			panel_1.add(getBtnPostres());
		}
		return panel_1;
	}
	private JButton getBtnHamburguesas() {
		if (btnHamburguesas == null) {
			btnHamburguesas = new JButton("Hamburguesas");
			btnHamburguesas.setMnemonic('H');
			btnHamburguesas.setBackground(new Color(255, 255, 255));
			btnHamburguesas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getComboBox().setModel(new DefaultComboBoxModel(mc.getMenuProductsBurgers()));
					Product selectedProduct = (Product) getComboBox().getSelectedItem();
					getLblArticulo().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/" +selectedProduct.getCode() +".png")));
					if(mc.searchUnits(selectedProduct)==0) {
						getBtnEliminar().setEnabled(false);
					}
					getLblTotalItems().setText("Hay " + mc.searchUnits(selectedProduct) + " unidades en el pedido");
				}
			});
			btnHamburguesas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Hamburguesa.png")));
			btnHamburguesas.setFont(new Font("Dialog", Font.BOLD, 10));
			btnHamburguesas.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnHamburguesas.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnHamburguesas;
	}
	private JButton getBtnTodos() {
		if (btnTodos == null) {
			btnTodos = new JButton("Todos");
			btnTodos.setMnemonic('T');
			btnTodos.setBackground(new Color(255, 255, 255));
			btnTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getComboBox().setModel(new DefaultComboBoxModel(mc.getMenuProducts()));
					Product selectedProduct = (Product) getComboBox().getSelectedItem();
					getLblArticulo().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/" +selectedProduct.getCode() +".png")));
					if(mc.searchUnits(selectedProduct)==0) {
						getBtnEliminar().setEnabled(false);
					}
					getLblTotalItems().setText("Hay " + mc.searchUnits(selectedProduct) + " unidades en el pedido");
				}
			});
			btnTodos.setFont(new Font("Dialog", Font.BOLD, 12));
			btnTodos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Todos.png")));
			btnTodos.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnTodos.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnTodos;
	}
	private JButton getBtnPostres() {
		if (btnPostres == null) {
			btnPostres = new JButton("Postres");
			btnPostres.setMnemonic('r');
			btnPostres.setBackground(new Color(255, 255, 255));
			btnPostres.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getComboBox().setModel(new DefaultComboBoxModel(mc.getMenuProductsDesserts()));
					Product selectedProduct = (Product) getComboBox().getSelectedItem();
					getLblArticulo().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/" +selectedProduct.getCode() +".png")));
					if(mc.searchUnits(selectedProduct)==0) {
						getBtnEliminar().setEnabled(false);
					}
					getLblTotalItems().setText("Hay " + mc.searchUnits(selectedProduct) + " unidades en el pedido");
				}
			});
			btnPostres.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Postre.png")));
			btnPostres.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnPostres.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnPostres;
	}
	private JButton getBtnBebidas() {
		if (btnBebidas == null) {
			btnBebidas = new JButton("Bebidas");
			btnBebidas.setMnemonic('B');
			btnBebidas.setBackground(new Color(255, 255, 255));
			btnBebidas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getComboBox().setModel(new DefaultComboBoxModel(mc.getMenuProductsDrinks()));
					Product selectedProduct = (Product) getComboBox().getSelectedItem();
					getLblArticulo().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/" +selectedProduct.getCode() +".png")));
					if(mc.searchUnits(selectedProduct)==0) {
						getBtnEliminar().setEnabled(false);
					}
					getLblTotalItems().setText("Hay " + mc.searchUnits(selectedProduct) + " unidades en el pedido");
				}
			});
			btnBebidas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Bebida.png")));
			btnBebidas.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnBebidas.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnBebidas;
	}
	private JButton getBtnComplementos() {
		if (btnComplementos == null) {
			btnComplementos = new JButton("Complementos");
			btnComplementos.setMnemonic('C');
			btnComplementos.setBackground(new Color(255, 255, 255));
			btnComplementos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					getComboBox().setModel(new DefaultComboBoxModel(mc.getMenuProductsSide()));
					Product selectedProduct = (Product) getComboBox().getSelectedItem();
					getLblArticulo().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/" +selectedProduct.getCode() +".png")));
					if(mc.searchUnits(selectedProduct)==0) {
						getBtnEliminar().setEnabled(false);
					}
					getLblTotalItems().setText("Hay " + mc.searchUnits(selectedProduct) + " unidades en el pedido");
				}
			});
			btnComplementos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/Complemento.png")));
			btnComplementos.setFont(new Font("Dialog", Font.BOLD, 10));
			btnComplementos.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnComplementos.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btnComplementos;
	}
}
