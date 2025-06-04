package uo.cpm.modulo.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

import uo.cpm.modulo.model.Juego;
import uo.cpm.modulo.model.Producto;
import uo.cpm.modulo.service.Pizzeria;

import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.awt.event.InputEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelInicio;
	private JPanel panelBienvenida;
	private JPanel panelIdiomas;
	private JButton btnSpanish;
	private JLabel lblIngredients;
	private JButton btnEnglish;
	private JButton btnFrench;
	private JButton btnItalian;
	private JPanel panelSaludo;
	private JLabel lblWelcome;
	private JButton btnSiguiente_Datos;
	private JLabel lblIngredients2;
	private JLabel lblIngredients2_1;
	private ProcessFrame pf = new  ProcessFrame();
	private JPanel panelRegistro;
	private JPanel panelDatos;
	private JTextField textFieldDNI;
	private JLabel lblDNI;
	private JLabel lblReserva;
	private JButton btnSiguiente_Menu;
	private JPanel panelMenu;
	private JPanel panelFiltros;
	private JButton btnTodos;
	private JButton btnPizzas;
	private JButton btnEntrantes;
	private JButton btnEnsaladas;
	private JButton btnPostres;
	private JButton btnBebidas;
	private JScrollPane scrollPaneProductos;
	private JPanel panelProductos;
	private JPanel panelFinalizarMenu;
	private JLabel lblPlaceHolderMenu;
	private JButton btnFinalizar;
	private JLabel lblPlaceHolderMenu_1;
	private JLabel lblPlaceHolderMenu_3;
	private JPanel panelCarrito;
	private JButton btnCarrito;
	private JScrollPane scrollPaneCarrito;
	private JTextArea txtAreaCarrito;
	private ProcessCart pCrt = new ProcessCart();
	private JPasswordField passwordFieldReserva;
	private JPanel panelPago;
	private JScrollPane scrollPaneListaPedidos;
	private JPanel panelOpcionesFinales;
	private JTextArea textAreaPedidos;
	private JPanel panelMetodoPago;
	private JPanel panelEleccionJugar;
	private JLabel lblOpcionesJugar;
	private JRadioButton rdbtnJugar;
	private JRadioButton rdbtnNoJugar;
	private final ButtonGroup buttonGroupJugar = new ButtonGroup();
	private JLabel lblOpcionesPago;
	private JRadioButton rdbtnTarjeta;
	private JRadioButton rdbtnEfectivo;
	private JPanel panelBtnFinales;
	private JButton btnCancelar;
	private JButton btnFinalizaryPagar;
	private final ButtonGroup buttonGroupPago = new ButtonGroup();
	private JMenuBar menuBar;
	private JMenu mnOptions;
	private JMenuItem mntmSalida;
	private JMenuItem mntmNuevaSesion;
	private JSeparator separator;
	private JMenu mnAyuda;
	private JMenuItem mntmAyuda;
	private ProcessExit pE = new ProcessExit();
	private ProcessWindowHelpFocus pWHF = new  ProcessWindowHelpFocus();
	private Pizzeria pz;
	private Producto[] menuModel;
	private ProcessFilters pft = new ProcessFilters();
	private ProcessBuying pB = new ProcessBuying();
	private JButton btnPastas;
	private JPanel panelListaDePedidos;
	private JPanel panelPedidoIndividual;
	private JLabel lblProducto;
	private JButton btnPapelera;
	private ProcessTrashBin pP = new ProcessTrashBin();
	private JPanel panelPedidoPapelera;
	private JPanel panelObservaciones;
	private JScrollPane scrollPaneObservaciones;
	private JTextArea textAreaObservaciones;
	private JTextField textFieldPlaceHolder;
	class ProcessBuying implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int pos = Integer.parseInt(e.getActionCommand());
			abrirVentanaProducto(pos);
			
//			pz.addToOrder(menuModel[pos], 1);
//			getTxtAreaCarrito().setText(pz.getOrderToStringShoppingCart());
//			getTextAreaPedidos().setText(pz.getOrderToStringShoppingCart());
			
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

		   hb.enableHelpKey(getPanelInicio(),"introduccion", hs);
		   hb.enableHelpOnButton(getMntmAyuda(), "introduccion", hs);
		   hb.enableHelp(getPanelMenu(), "menu", hs);
		   hb.enableHelp(getPanelRegistro(), "registro", hs);
		   hb.enableHelp(getPanelPago(), "pago", hs); //por defecto el foco est� puesto aqu�, ergo no se abrir�a la ayuda "general"
		 }
	protected void abrirVentanaProducto(int pos) {
		Producto product = menuModel[pos];
		VentanaProducto dialog = new VentanaProducto(this, pz, product);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	class ProcessFilters implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int filter = Integer.parseInt(e.getActionCommand());
			quitarBotonesMenu();
			generarBotonesMenu(filter);
			
		}
		
	}
	class ProcessWindowHelpFocus extends WindowAdapter{
		@Override
		public void windowOpened(WindowEvent e) {
			getBtnSiguiente_Datos().requestFocus();
		}
	}
	class ProcessExit implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			confirmChoice();
			
		}

		
		
	}
	protected void confirmChoice() {
		if(JOptionPane.showConfirmDialog(null, "�Est�s seguro/a de que quieres salir de la aplicaci�n?")==JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	class ProcessFrame implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Menu")) {
				cambiarPanelAMenu(e);
			}else if(e.getActionCommand().equals("Revisar y Pagar")){
				cambiarAPanelPago(e);
			}else if(e.getActionCommand().equals("Inicio")){
				cambiarAInicioOJuego(e);
			}else if(e.getActionCommand().equals("Reinicio")){
				reiniciar();
			}else {
				changeFrame(e);
					
			}
			
		}

	
	
		
}
	class ProcessTrashBin implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int i = Integer.parseInt(e.getActionCommand());
			pz.remove(pz.getOrder().getOrderList().get(i));
			if(pz.getOrder().getOrderList().isEmpty()) {
				getPanelPedidoPapelera().removeAll();
				//getPanelListaDePedidos().setLayout(new GridLayout(pz.getOrder().getOrderList().size(), 1, 0, 0));
			}else {
				getPanelPedidoPapelera().remove(i);
				getPanelPedidoPapelera().setLayout(new GridLayout(pz.getOrder().getOrderList().size(), 1, 0, 0));
			}
			getPanelPedidoPapelera().removeAll();
			generarPedido();
			getTxtAreaCarrito().setText(pz.getOrderToStringShoppingCart());
			repaint();
			validate();
			
		}
		
	}
	protected void cambiarPanelAMenu(ActionEvent e) {
		getPanelMenu().requestFocus();
		if(String.valueOf(getPasswordFieldReserva().getPassword()).isBlank() || String.valueOf(getPasswordFieldReserva().getPassword()).isEmpty() || getTextFieldDNI().getText().isBlank() || getTextFieldDNI().getText().isEmpty() || !pz.isUserAClient(getTextFieldDNI().getText(), String.valueOf(getPasswordFieldReserva().getPassword()))) {
			JOptionPane.showMessageDialog(null, "Los datos introducidos no son v�lidos", "Uniovi's Error", JOptionPane.WARNING_MESSAGE);
		}else {
			changeFrame(e);
			if(pz.getClient().getId().equals("") || pz.getClient()==null) {
				pz.saveCustomerData(getTextFieldDNI().getText(), String.valueOf(getPasswordFieldReserva().getPassword()));
			}
			
		}
	}
	protected void cambiarAPanelPago(ActionEvent e) {
		getPanelPago().requestFocus();
		if((float)pz.getOrderTotal()==0) {
			JOptionPane.showMessageDialog(null, "No has seleccionado ning�n producto", "Error", JOptionPane.WARNING_MESSAGE);
		}else {
			changeFrame(e);
			getPanelPedidoPapelera().removeAll();
			generarPedido();
		}
	}
	protected void reiniciar() {
		restart();
		pz.initializeOrderDifferentClient();
		inicializarInterfazNuevoUsuario();
	}
	protected void cambiarAInicioOJuego(ActionEvent e) {
		getPanelInicio().requestFocus();
		if(getRdbtnJugar().isSelected() && !pz.hasClientPlayed()) {
//			pz.getClient().editPlayState(true);
			pz.setClientPlayed(true);
			pz.getClient().editTimesOrdered(pz.getClient().getTimesOrdered()+1);
			JOptionPane.showMessageDialog(null, "�Pedido recibido en cocina!", "Recepci�n del pedido", JOptionPane.INFORMATION_MESSAGE);
			Juego jg = new Juego();
			VentanaJuego dialog = new VentanaJuego(this, pz, jg);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		}else if(getRdbtnJugar().isSelected() && pz.hasClientPlayed()){
			JOptionPane.showMessageDialog(null, "Lo sentimos, s�lo se puede jugar una vez por reserva", "Error", JOptionPane.WARNING_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "�Pedido recibido en cocina!", "Recepci�n del pedido", JOptionPane.INFORMATION_MESSAGE);
			pz.getClient().editTimesOrdered(pz.getClient().getTimesOrdered()+1);
			pz.saveOrder();
			pz.initializeOrder();
			changeFrame(e);
			inicializarInterfaz();
		}
	}

	protected void inicializarInterfaz() {
		getTxtAreaCarrito().setText(pz.getOrderToStringShoppingCart());
		getTextAreaPedidos().setText("");

	}
	protected void inicializarInterfazNuevoUsuario() {
		getTxtAreaCarrito().setText(pz.getOrderToStringShoppingCart());
		getTextAreaPedidos().setText("");
		getTextFieldDNI().setText("");
		getPasswordFieldReserva().setText("");
		getRdbtnJugar().setSelected(true);
		getRdbtnTarjeta().setSelected(true);
	}
	protected void changeFrame(ActionEvent e) {
		getPanelDatos().requestFocus();
		String frame = e.getActionCommand();
		((CardLayout)getContentPane().getLayout()).show(getContentPane(), frame);
		setTitle("Uniovi's: " + frame);
	}
	protected void restart() {
		getPanelInicio().requestFocus();
		((CardLayout)getContentPane().getLayout()).show(getContentPane(), "Inicio");
		setTitle("Uniovi's: ");
	}
	class ProcessCart implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			showShoppingCart();
			
		}

		
		
	}
	protected void showShoppingCart() {
		if(getTxtAreaCarrito().isVisible()) {
			getTxtAreaCarrito().setVisible(false);
		}else {
			getTxtAreaCarrito().setVisible(true);
		}
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(Pizzeria pz) {
		this.pz = pz;
		addWindowListener(pWHF);
		setMinimumSize(new Dimension(550, 350));
		setTitle("Uniovi's ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_4());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPanelInicio(), "Inicio");
		contentPane.add(getPanelRegistro(), "Datos de Registro");
		contentPane.add(getPanelMenu(), "Menu");
		contentPane.add(getPanelPago(), "Revisar y Pagar");
		cargaAyuda();
	}

	private JPanel getPanelInicio() {
		if (panelInicio == null) {
			panelInicio = new JPanel();
			panelInicio.setBackground(new Color(255, 0, 0));
			panelInicio.setLayout(new BorderLayout(0, 0));
			panelInicio.add(getPanelBienvenida(), BorderLayout.CENTER);
			panelInicio.add(getPanelIdiomas(), BorderLayout.NORTH);
			panelInicio.add(getPanelSaludo(), BorderLayout.SOUTH);
		}
		return panelInicio;
	}
	private JPanel getPanelBienvenida() {
		if (panelBienvenida == null) {
			panelBienvenida = new JPanel();
			panelBienvenida.setBackground(new Color(0, 0, 139));
			panelBienvenida.setLayout(new BorderLayout(0, 0));
			panelBienvenida.add(getLblIngredients(), BorderLayout.CENTER);
			panelBienvenida.add(getLblIngredients2(), BorderLayout.WEST);
			panelBienvenida.add(getLblIngredients2_1(), BorderLayout.EAST);
		}
		return panelBienvenida;
	}
	private JPanel getPanelIdiomas() {
		if (panelIdiomas == null) {
			panelIdiomas = new JPanel();
			panelIdiomas.setBackground(new Color(255, 0, 0));
			panelIdiomas.setLayout(new GridLayout(0, 4, 0, 0));
			panelIdiomas.add(getBtnSpanish());
			panelIdiomas.add(getBtnEnglish());
			panelIdiomas.add(getBtnFrench());
			panelIdiomas.add(getBtnItalian());
		}
		return panelIdiomas;
	}
	private JButton getBtnSpanish() {
		if (btnSpanish == null) {
			btnSpanish = new JButton("");
			btnSpanish.setBackground(new Color(255, 255, 255));
			btnSpanish.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/es.png")));
		}
		return btnSpanish;
	}
	private JLabel getLblIngredients() {
		if (lblIngredients == null) {
			lblIngredients = new JLabel("");
			lblIngredients.setHorizontalAlignment(SwingConstants.CENTER);
			lblIngredients.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/bienvenida.png")));
		}
		return lblIngredients;
	}
	private JButton getBtnEnglish() {
		if (btnEnglish == null) {
			btnEnglish = new JButton("");
			btnEnglish.setToolTipText("(no implementado)");
			btnEnglish.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/en.png")));
			btnEnglish.setBackground(Color.WHITE);
		}
		return btnEnglish;
	}
	private JButton getBtnFrench() {
		if (btnFrench == null) {
			btnFrench = new JButton("");
			btnFrench.setToolTipText("(no implementado)");
			btnFrench.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/fr.png")));
			btnFrench.setBackground(Color.WHITE);
		}
		return btnFrench;
	}
	private JButton getBtnItalian() {
		if (btnItalian == null) {
			btnItalian = new JButton("");
			btnItalian.setToolTipText("(no implementado)");
			btnItalian.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/it.png")));
			btnItalian.setBackground(Color.WHITE);
		}
		return btnItalian;
	}
	private JPanel getPanelSaludo() {
		if (panelSaludo == null) {
			panelSaludo = new JPanel();
			panelSaludo.setBackground(new Color(255, 0, 0));
			panelSaludo.setLayout(new GridLayout(0, 2, 0, 0));
			panelSaludo.add(getLblWelcome_1());
			panelSaludo.add(getBtnSiguiente_Datos());
		}
		return panelSaludo;
	}
	private JLabel getLblWelcome_1() {
		if (lblWelcome == null) {
			lblWelcome = new JLabel("\u00A1Bienvenido a Uniovi's!");
			lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
			lblWelcome.setForeground(Color.WHITE);
			lblWelcome.setFont(new Font("Dialog", Font.BOLD, 28));
			lblWelcome.setBorder(new LineBorder(new Color(0, 0, 0), 0));
			lblWelcome.setBackground(Color.WHITE);
		}
		return lblWelcome;
	}
	private JButton getBtnSiguiente_Datos() {
		if (btnSiguiente_Datos == null) {
			btnSiguiente_Datos = new JButton("Continuar");
			btnSiguiente_Datos.setAlignmentX(Component.RIGHT_ALIGNMENT);
			btnSiguiente_Datos.setBackground(new Color(50, 205, 50));
			btnSiguiente_Datos.setForeground(new Color(255, 255, 255));
			btnSiguiente_Datos.setFont(new Font("SansSerif", Font.PLAIN, 24));
			btnSiguiente_Datos.setMnemonic('C');
			btnSiguiente_Datos.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
			btnSiguiente_Datos.setActionCommand("Datos de Registro");
			btnSiguiente_Datos.addActionListener(pf);
		}
		return btnSiguiente_Datos;
	}
	private JLabel getLblIngredients2() {
		if (lblIngredients2 == null) {
			lblIngredients2 = new JLabel("");
			lblIngredients2.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/PI03.png")));
		}
		return lblIngredients2;
	}
	private JLabel getLblIngredients2_1() {
		if (lblIngredients2_1 == null) {
			lblIngredients2_1 = new JLabel("");
			lblIngredients2_1.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/BE02.png")));
		}
		return lblIngredients2_1;
	}
	private JPanel getPanelRegistro() {
		if (panelRegistro == null) {
			panelRegistro = new JPanel();
			panelRegistro.setLayout(null);
			panelRegistro.add(getPanelDatos());
		}
		return panelRegistro;
	}
	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			panelDatos = new JPanel();
			panelDatos.setBounds(0, 0, 1176, 730);
			panelDatos.setBackground(new Color(0, 0, 128));
			panelDatos.setLayout(null);
			panelDatos.add(getLblDNI());
			panelDatos.add(getTextFieldDNI());
			panelDatos.add(getLblReserva());
			panelDatos.add(getPasswordFieldReserva());
			panelDatos.add(getBtnSiguiente_Menu());
		}
		return panelDatos;
	}
	private JTextField getTextFieldDNI() {
		if (textFieldDNI == null) {
			textFieldDNI = new JTextField();
			textFieldDNI.setBounds(104, 131, 466, 93);
			textFieldDNI.setFont(new Font("SansSerif", Font.PLAIN, 28));
			textFieldDNI.setColumns(10);
		}
		return textFieldDNI;
	}
	private JLabel getLblDNI() {
		if (lblDNI == null) {
			lblDNI = new JLabel("DNI / NIE:");
			lblDNI.setBounds(104, 24, 820, 113);
			lblDNI.setLabelFor(getTextFieldDNI());
			lblDNI.setDisplayedMnemonic('D');
			lblDNI.setForeground(new Color(255, 255, 255));
			lblDNI.setFont(new Font("SansSerif", Font.PLAIN, 28));
		}
		return lblDNI;
	}
	private JLabel getLblReserva() {
		if (lblReserva == null) {
			lblReserva = new JLabel("N\u00FAmero de reserva");
			lblReserva.setBounds(104, 359, 820, 113);
			lblReserva.setLabelFor(getPasswordFieldReserva());
			lblReserva.setDisplayedMnemonic('N');
			lblReserva.setForeground(new Color(255, 255, 255));
			lblReserva.setFont(new Font("SansSerif", Font.PLAIN, 28));
		}
		return lblReserva;
	}
	private JButton getBtnSiguiente_Menu() {
		if (btnSiguiente_Menu == null) {
			btnSiguiente_Menu = new JButton("Continuar");
			btnSiguiente_Menu.setBorder(new LineBorder(new Color(0, 0, 0), 4));
			btnSiguiente_Menu.setBounds(817, 589, 304, 86);
			btnSiguiente_Menu.setForeground(new Color(255, 255, 255));
			btnSiguiente_Menu.setBackground(new Color(50, 205, 50));
			btnSiguiente_Menu.setFont(new Font("SansSerif", Font.PLAIN, 28));
			btnSiguiente_Menu.setMnemonic('C');
			btnSiguiente_Menu.setActionCommand("Menu");
			btnSiguiente_Menu.addActionListener(pf);
		}
		return btnSiguiente_Menu;
	}
	private JPanel getPanelMenu() {
		if (panelMenu == null) {
			panelMenu = new JPanel();
			panelMenu.setLayout(new BorderLayout(0, 0));
			panelMenu.add(getPanelFiltros(), BorderLayout.WEST);
			panelMenu.add(getScrollPaneProductos(), BorderLayout.CENTER);
			panelMenu.add(getPanelFinalizarMenu(), BorderLayout.SOUTH);
			panelMenu.add(getPanelCarrito(), BorderLayout.EAST);
		}
		return panelMenu;
	}
	private JPanel getPanelFiltros() {
		if (panelFiltros == null) {
			panelFiltros = new JPanel();
			panelFiltros.setLayout(new GridLayout(7, 0, 0, 0));
			panelFiltros.add(getBtnTodos());
			panelFiltros.add(getBtnPizzas());
			panelFiltros.add(getBtnEntrantes());
			panelFiltros.add(getBtnEnsaladas());
			panelFiltros.add(getBtnPostres());
			panelFiltros.add(getBtnPastas());
			panelFiltros.add(getBtnBebidas());
		}
		return panelFiltros;
	}
	private JButton getBtnTodos() {
		if (btnTodos == null) {
			btnTodos = new JButton("");
			btnTodos.setText("Todos");
			btnTodos.setVerticalTextPosition(SwingConstants.TOP);
			btnTodos.setHorizontalTextPosition(SwingConstants.CENTER);
			btnTodos.setBackground(new Color(255, 255, 255));
			btnTodos.setToolTipText("Filtrar - Todos");
			setImagenAdaptada(btnTodos, "/img/bienvenida.png");
			btnTodos.setActionCommand(String.valueOf(Pizzeria.TODOS));
			btnTodos.addActionListener(pft);
		}
		return btnTodos;
	}
	private JButton getBtnPizzas() {
		if (btnPizzas == null) {
			btnPizzas = new JButton("");
			btnPizzas.setText("Pizzas");
			btnPizzas.setVerticalTextPosition(SwingConstants.TOP);
			btnPizzas.setHorizontalTextPosition(SwingConstants.CENTER);
			btnPizzas.setBackground(new Color(255, 255, 255));
			btnPizzas.setToolTipText("Filtrar - Pizzas");
			setImagenAdaptada(btnPizzas,"/img/PI01.png");
			btnPizzas.addActionListener(pft);
			btnPizzas.setActionCommand(String.valueOf(Pizzeria.PIZZAS));
		}
		return btnPizzas;
	}
	private JButton getBtnEntrantes() {
		if (btnEntrantes == null) {
			btnEntrantes = new JButton("");
			btnEntrantes.setText("Entrantes");
			btnEntrantes.setVerticalTextPosition(SwingConstants.TOP);
			btnEntrantes.setHorizontalTextPosition(SwingConstants.CENTER);
			btnEntrantes.setBackground(new Color(255, 255, 255));
			btnEntrantes.setToolTipText("Filtrar - Entrantes");
			setImagenAdaptada(btnEntrantes, "/img/ET02.png");
			btnEntrantes.addActionListener(pft);
			btnEntrantes.setActionCommand(String.valueOf(Pizzeria.ENTRANTES));
		}
		return btnEntrantes;
	}
	private JButton getBtnEnsaladas() {
		if (btnEnsaladas == null) {
			btnEnsaladas = new JButton("Ensaladas");
			btnEnsaladas.setText("Ensaladas");
			btnEnsaladas.setVerticalTextPosition(SwingConstants.TOP);
			btnEnsaladas.setHorizontalTextPosition(SwingConstants.CENTER);
			btnEnsaladas.setBackground(new Color(255, 255, 255));
			btnEnsaladas.setToolTipText("Filtrar - Ensaladas");
			setImagenAdaptada(btnEnsaladas, "/img/EN02.png");
			btnEnsaladas.addActionListener(pft);
			btnEnsaladas.setActionCommand(String.valueOf(Pizzeria.ENSALADAS));
		}
		return btnEnsaladas;
	}
	private JButton getBtnPostres() {
		if (btnPostres == null) {
			btnPostres = new JButton("");
			btnPostres.setText("Postres");
			btnPostres.setBackground(new Color(255, 255, 255));
			btnPostres.setVerticalTextPosition(SwingConstants.TOP);
			btnPostres.setHorizontalTextPosition(SwingConstants.CENTER);
			btnPostres.setToolTipText("Filtrar - Postres");
			btnPostres.setActionCommand(String.valueOf(Pizzeria.POSTRES));
			setImagenAdaptada(btnPostres, "/img/PO01.png");
			btnPostres.addActionListener(pft);
		}
		return btnPostres;
	}
	private JButton getBtnBebidas() {
		if (btnBebidas == null) {
			btnBebidas = new JButton("");
			btnBebidas.setText("Bebidas");
			btnBebidas.setVerticalTextPosition(SwingConstants.TOP);
			btnBebidas.setHorizontalTextPosition(SwingConstants.CENTER);
			btnBebidas.setBackground(new Color(255, 255, 255));
			btnBebidas.setToolTipText("Filtrar - Bebidas");
			setImagenAdaptada(btnBebidas, "/img/BE03.png");
			btnBebidas.addActionListener(pft);
			btnBebidas.setActionCommand(String.valueOf(Pizzeria.BEBIDAS));
		}
		return btnBebidas;
	}
	private void setImagenAdaptada(JButton boton, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance((int) (70), (int) (70),Image.SCALE_SMOOTH);
		 //si se quiere reescalar la ventana, este 100 tendria que cambiarse por el width y el height  respectivamente de la imagen para que as� se reescale cada vez que cambias el tama�o
		 //adem�s este m�todo deber�a llamarse en el constructor de la ventana mientras se usa un evento componentResized
		 boton.setIcon(new ImageIcon(imgEscalada));
	}
	private JScrollPane getScrollPaneProductos() {
		if (scrollPaneProductos == null) {
			scrollPaneProductos = new JScrollPane();
			scrollPaneProductos.setViewportView(getPanelProductos());
		}
		return scrollPaneProductos;
	}
	private JPanel getPanelProductos() {
		if (panelProductos == null) {
			panelProductos = new JPanel();
			panelProductos.setBackground(new Color(0, 0, 128));
			panelProductos.setLayout(new GridLayout(0, 4, 40, 40));
			generarBotonesMenu(Pizzeria.TODOS);
		}
		return panelProductos;
	}
	private void quitarBotonesMenu() {
		getPanelProductos().removeAll();
		getPanelProductos().repaint();
	}
	protected void generarBotonesMenu(int filter) {
			menuModel = pz.typeOfFilter(filter);
		for(int i=0; i<menuModel.length; i++) {
			JButton j = new JButton("");
			j.setText(menuModel[i].toStringMenu());
			j.setVerticalTextPosition(SwingConstants.BOTTOM); 
			j.setHorizontalTextPosition(SwingConstants.CENTER);
			setImagenAdaptada(j, "/img/" +menuModel[i].getCode() + ".png");
			j.setBackground(Color.black);
			j.setForeground(Color.white);
			if(i==0 && menuModel[i].getType().equals("Pizza")) {
				j.setBorder(new LineBorder(Color.red, 8));
				j.setText("�Pizza del d�a! - " + j.getText());
			}
			j.setActionCommand(String.valueOf(i));
			j.addActionListener(pB);
			panelProductos.add(j);
			validate();
		}
	}
	private JPanel getPanelFinalizarMenu() {
		if (panelFinalizarMenu == null) {
			panelFinalizarMenu = new JPanel();
			panelFinalizarMenu.setBackground(new Color(128, 0, 0));
			panelFinalizarMenu.setLayout(new GridLayout(0, 5, 0, 0));
			panelFinalizarMenu.add(getLblPlaceHolderMenu());
			panelFinalizarMenu.add(getLblPlaceHolderMenu_3());
			panelFinalizarMenu.add(getTextFieldPlaceHolder());
			panelFinalizarMenu.add(getLblPlaceHolderMenu_1());
			panelFinalizarMenu.add(getBtnFinalizar());
		}
		return panelFinalizarMenu;
	}
	private JLabel getLblPlaceHolderMenu() {
		if (lblPlaceHolderMenu == null) {
			lblPlaceHolderMenu = new JLabel("");
		}
		return lblPlaceHolderMenu;
	}
	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setBorder(new LineBorder(new Color(0, 0, 0), 4));
			btnFinalizar.setMnemonic('F');
			btnFinalizar.setForeground(new Color(255, 255, 255));
			btnFinalizar.setBackground(new Color(50, 205, 50));
			btnFinalizar.setFont(new Font("SansSerif", Font.PLAIN, 28));
			btnFinalizar.setActionCommand("Revisar y Pagar");
			btnFinalizar.addActionListener(pf);
			generarPedido();
		}
		return btnFinalizar;
	}
	private JLabel getLblPlaceHolderMenu_1() {
		if (lblPlaceHolderMenu_1 == null) {
			lblPlaceHolderMenu_1 = new JLabel("");
		}
		return lblPlaceHolderMenu_1;
	}
	private JLabel getLblPlaceHolderMenu_3() {
		if (lblPlaceHolderMenu_3 == null) {
			lblPlaceHolderMenu_3 = new JLabel("Canjear Codigo:");
			lblPlaceHolderMenu_3.setForeground(new Color(255, 255, 255));
			lblPlaceHolderMenu_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
			lblPlaceHolderMenu_3.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPlaceHolderMenu_3;
	}
	private JPanel getPanelCarrito() {
		if (panelCarrito == null) {
			panelCarrito = new JPanel();
			panelCarrito.setBackground(new Color(128, 0, 0));
			panelCarrito.setLayout(new BorderLayout(0, 0));
			panelCarrito.add(getBtnCarrito(), BorderLayout.NORTH);
			panelCarrito.add(getScrollPaneCarrito());
		}
		return panelCarrito;
	}
	private JButton getBtnCarrito() {
		if (btnCarrito == null) {
			btnCarrito = new JButton("");
			btnCarrito.setToolTipText("Mostrar/Ocultar Carrito");
			btnCarrito.setBackground(new Color(255, 255, 255));
			setImagenAdaptada(btnCarrito,"/img/carrito.png");
			btnCarrito.addActionListener(pCrt);
		}
		return btnCarrito;
	}
	private JScrollPane getScrollPaneCarrito() {
		if (scrollPaneCarrito == null) {
			scrollPaneCarrito = new JScrollPane();
			scrollPaneCarrito.setEnabled(false);
			scrollPaneCarrito.setViewportView(getTxtAreaCarrito());
		}
		return scrollPaneCarrito;
	}
	protected JTextArea getTxtAreaCarrito() {
		if (txtAreaCarrito == null) {
			txtAreaCarrito = new JTextArea();
			txtAreaCarrito.setBackground(new Color(255, 255, 255));
			txtAreaCarrito.setEditable(false);
			txtAreaCarrito.setVisible(false);
			txtAreaCarrito.setText(pz.getOrderToStringShoppingCart());
		}
		return txtAreaCarrito;
	}
	private JPasswordField getPasswordFieldReserva() {
		if (passwordFieldReserva == null) {
			passwordFieldReserva = new JPasswordField();
			passwordFieldReserva.setBounds(104, 484, 466, 86);
			passwordFieldReserva.setBackground(new Color(255, 255, 255));
			passwordFieldReserva.setFont(new Font("SansSerif", Font.PLAIN, 28));
		}
		return passwordFieldReserva;
	}
	private JPanel getPanelPago() {
		if (panelPago == null) {
			panelPago = new JPanel();
			panelPago.setLayout(new GridLayout(0, 2, 0, 0));
			panelPago.add(getPanelListaDePedidos());
			panelPago.add(getPanelOpcionesFinales());
		}
		return panelPago;
	}
	public JPanel getPanelListaDePedidos() {
		if(panelListaDePedidos==null) {
			panelListaDePedidos = new JPanel();
			panelListaDePedidos.setLayout(null);
			panelListaDePedidos.add(getPanelPedidoPapelera());
			panelListaDePedidos.add(getPanelObservaciones());
			//panelListaDePedidos.add(getPanelPedidoIndividual());
			panelPago.setLayout(new GridLayout(1,1,0,2));
		}
		return panelListaDePedidos;
	}
	public void generarPedido() {
		for(int i=0; i<pz.getOrder().getOrderList().size(); i++) {
			JPanel panelPedidoIndividual = new JPanel();
			panelPedidoIndividual.setLayout(new GridLayout(1,2,0,0));
			panelPedidoIndividual.add(crearEtiqueta(i));
			panelPedidoIndividual.add(crearBotonPapelera(i));
			getPanelPedidoPapelera().add(panelPedidoIndividual);
			}	
		
			
		}
	private JButton crearBotonPapelera(int i) {
		JButton boton = new JButton("");
		boton.setBackground(Color.red);
		setImagenAdaptada(boton, "/img/papelera.png");
		boton.setActionCommand(String.valueOf(i));
		boton.addActionListener(pP);
		return boton;
	}
	private JLabel crearEtiqueta(int i) {
		JLabel label = new JLabel();
		label.setText(pz.getOrder().getOrderList().get(i).toStringShoppingCart());
		label.setFont(new Font("SansSerif", Font.BOLD, 12));
		return label;
	}

	private JPanel getPanelOpcionesFinales() {
		if (panelOpcionesFinales == null) {
			panelOpcionesFinales = new JPanel();
			panelOpcionesFinales.setBackground(new Color(0, 0, 128));
			panelOpcionesFinales.setLayout(new BorderLayout(0, 0));
			panelOpcionesFinales.add(getPanelMetodoPago(), BorderLayout.CENTER);
			panelOpcionesFinales.add(getPanelEleccionJugar(), BorderLayout.NORTH);
			panelOpcionesFinales.add(getPanelBtnFinales(), BorderLayout.SOUTH);
		}
		return panelOpcionesFinales;
	}
	public JTextArea getTextAreaPedidos() {
		if (textAreaPedidos == null) {
			textAreaPedidos = new JTextArea();
			textAreaPedidos.setText("");
			textAreaPedidos.setFont(new Font("SansSerif", Font.PLAIN, 21));
		}
		return textAreaPedidos;
	}
	private JPanel getPanelMetodoPago() {
		if (panelMetodoPago == null) {
			panelMetodoPago = new JPanel();
			panelMetodoPago.setBackground(new Color(0, 0, 128));
			panelMetodoPago.setLayout(new GridLayout(4, 1, 0, 0));
			panelMetodoPago.add(getLblOpcionesPago());
			panelMetodoPago.add(getRdbtnTarjeta());
			panelMetodoPago.add(getRdbtnEfectivo());
		}
		return panelMetodoPago;
	}
	private JPanel getPanelEleccionJugar() {
		if (panelEleccionJugar == null) {
			panelEleccionJugar = new JPanel();
			panelEleccionJugar.setBackground(new Color(0, 0, 128));
			panelEleccionJugar.setLayout(new GridLayout(3, 1, 0, 0));
			panelEleccionJugar.add(getLblOpcionesJugar());
			panelEleccionJugar.add(getRdbtnJugar());
			panelEleccionJugar.add(getRdbtnNoJugar());
		}
		return panelEleccionJugar;
	}
	private JLabel getLblOpcionesJugar() {
		if (lblOpcionesJugar == null) {
			lblOpcionesJugar = new JLabel("\u00BFQuiere jugar para descuentos?");
			lblOpcionesJugar.setForeground(new Color(255, 255, 255));
			lblOpcionesJugar.setFont(new Font("SansSerif", Font.BOLD, 27));
		}
		return lblOpcionesJugar;
	}
	private JRadioButton getRdbtnJugar() {
		if (rdbtnJugar == null) {
			rdbtnJugar = new JRadioButton("Jugar");
			rdbtnJugar.setBackground(new Color(0, 0, 128));
			rdbtnJugar.setFont(new Font("SansSerif", Font.PLAIN, 19));
			rdbtnJugar.setSelected(true);
			rdbtnJugar.setMnemonic('J');
			buttonGroupJugar.add(rdbtnJugar);
			rdbtnJugar.setForeground(new Color(255, 255, 255));
		}
		return rdbtnJugar;
	}
	private JRadioButton getRdbtnNoJugar() {
		if (rdbtnNoJugar == null) {
			rdbtnNoJugar = new JRadioButton("No jugar");
			rdbtnNoJugar.setBackground(new Color(0, 0, 128));
			rdbtnNoJugar.setFont(new Font("SansSerif", Font.PLAIN, 19));
			rdbtnNoJugar.setMnemonic('N');
			buttonGroupJugar.add(rdbtnNoJugar);
			rdbtnNoJugar.setForeground(new Color(255, 255, 255));
		}
		return rdbtnNoJugar;
	}
	private JLabel getLblOpcionesPago() {
		if (lblOpcionesPago == null) {
			lblOpcionesPago = new JLabel("\u00BFC\u00F3mo desea pagar?");
			lblOpcionesPago.setHorizontalAlignment(SwingConstants.LEFT);
			lblOpcionesPago.setFont(new Font("SansSerif", Font.BOLD, 27));
			lblOpcionesPago.setForeground(new Color(255, 255, 255));
		}
		return lblOpcionesPago;
	}
	private JRadioButton getRdbtnTarjeta() {
		if (rdbtnTarjeta == null) {
			rdbtnTarjeta = new JRadioButton("Tarjeta");
			rdbtnTarjeta.setBackground(new Color(0, 0, 128));
			rdbtnTarjeta.setMnemonic('T');
			rdbtnTarjeta.setSelected(true);
			buttonGroupPago.add(rdbtnTarjeta);
			rdbtnTarjeta.setFont(new Font("SansSerif", Font.PLAIN, 19));
			rdbtnTarjeta.setForeground(new Color(255, 255, 255));
			rdbtnTarjeta.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return rdbtnTarjeta;
	}
	private JRadioButton getRdbtnEfectivo() {
		if (rdbtnEfectivo == null) {
			rdbtnEfectivo = new JRadioButton("Efectivo");
			rdbtnEfectivo.setBackground(new Color(0, 0, 128));
			rdbtnEfectivo.setMnemonic('E');
			buttonGroupPago.add(rdbtnEfectivo);
			rdbtnEfectivo.setForeground(new Color(255, 255, 255));
			rdbtnEfectivo.setFont(new Font("SansSerif", Font.PLAIN, 19));
		}
		return rdbtnEfectivo;
	}
	private JPanel getPanelBtnFinales() {
		if (panelBtnFinales == null) {
			panelBtnFinales = new JPanel();
			panelBtnFinales.setLayout(new GridLayout(0, 2, 0, 0));
			panelBtnFinales.add(getBtnCancelar());
			panelBtnFinales.add(getBtnFinalizaryPagar());
		}
		return panelBtnFinales;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBorder(new LineBorder(new Color(0, 0, 0), 4));
			btnCancelar.setForeground(new Color(255, 255, 255));
			btnCancelar.setBackground(new Color(255, 0, 0));
			btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 27));
			btnCancelar.setMnemonic('C');
			btnCancelar.setActionCommand("Menu");
			btnCancelar.addActionListener(pf);
		}
		return btnCancelar;
	}
	private JButton getBtnFinalizaryPagar() {
		if (btnFinalizaryPagar == null) {
			btnFinalizaryPagar = new JButton("Finalizar y pagar");
			btnFinalizaryPagar.setBorder(new LineBorder(new Color(0, 0, 0), 4));
			btnFinalizaryPagar.setMnemonic('F');
			btnFinalizaryPagar.setBackground(new Color(50, 205, 50));
			btnFinalizaryPagar.setForeground(new Color(255, 255, 255));
			btnFinalizaryPagar.setFont(new Font("SansSerif", Font.PLAIN, 23));
			btnFinalizaryPagar.setActionCommand("Inicio");
			btnFinalizaryPagar.addActionListener(pf);
		}
		return btnFinalizaryPagar;
	}
	private JMenuBar getMenuBar_4() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMenu_1());
			menuBar.add(getMenu_2());
		}
		return menuBar;
	}
	private JMenu getMenu_1() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Opciones");
			mnOptions.setMnemonic('O');
			mnOptions.add(getMntmNuevaSesion());
			mnOptions.add(getSeparator());
			mnOptions.add(getMntmSalida());
		}
		return mnOptions;
	}
	private JMenuItem getMntmSalida() {
		if (mntmSalida == null) {
			mntmSalida = new JMenuItem("Salida");
			mntmSalida.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
			mntmSalida.addActionListener(pE);
		}
		return mntmSalida;
	}
	private JMenuItem getMntmNuevaSesion() {
		if (mntmNuevaSesion == null) {
			mntmNuevaSesion = new JMenuItem("Nueva Sesion");
			mntmNuevaSesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
			mntmNuevaSesion.setActionCommand("Reinicio");
			mntmNuevaSesion.addActionListener(pf);
		}
		return mntmNuevaSesion;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenu getMenu_2() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('A');
			mnAyuda.add(getMntmAyuda());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmAyuda() {
		if (mntmAyuda == null) {
			mntmAyuda = new JMenuItem("Abrir men\u00FA de ayuda");
			mntmAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmAyuda;
	}
	private JButton getBtnPastas() {
		if (btnPastas == null) {
			btnPastas = new JButton("Pastas");
			btnPastas.setVerticalTextPosition(SwingConstants.TOP);
			btnPastas.setToolTipText("Filtrar - Pastas");
			btnPastas.setHorizontalTextPosition(SwingConstants.CENTER);
			btnPastas.setBackground(Color.WHITE);
			btnPastas.setActionCommand(String.valueOf(Pizzeria.PASTAS));
			btnPastas.addActionListener(pft);
			setImagenAdaptada(btnPastas, "/img/PA02.png");
		}
		return btnPastas;
	}
	


	JPanel getPanelPedidoPapelera() {
		if (panelPedidoPapelera == null) {
			panelPedidoPapelera = new JPanel();
			panelPedidoPapelera.setBounds(6, 6, 576, 636);
			panelPedidoPapelera.setLayout(new GridLayout(pz.getOrder().getOrderList().size(), 1, 0, 0));
			generarPedido();
		}
		return panelPedidoPapelera;
	}
	private JPanel getPanelObservaciones() {
		if (panelObservaciones == null) {
			panelObservaciones = new JPanel();
			panelObservaciones.setBounds(0, 644, 582, 80);
			panelObservaciones.setLayout(new GridLayout(0, 1, 0, 0));
			panelObservaciones.add(getScrollPaneObservaciones());
		}
		return panelObservaciones;
	}
	private JScrollPane getScrollPaneObservaciones() {
		if (scrollPaneObservaciones == null) {
			scrollPaneObservaciones = new JScrollPane();
			scrollPaneObservaciones.setViewportView(getTextAreaObservaciones());
		}
		return scrollPaneObservaciones;
	}
	public JTextArea getTextAreaObservaciones() {
		if (textAreaObservaciones == null) {
			textAreaObservaciones = new JTextArea();
			textAreaObservaciones.setForeground(new Color(255, 255, 255));
			textAreaObservaciones.setBackground(new Color(0, 0, 0));
			textAreaObservaciones.setEditable(false);
			textAreaObservaciones.setText(pz.getObservations());
		}
		return textAreaObservaciones;
	}
	private JTextField getTextFieldPlaceHolder() {
		if (textFieldPlaceHolder == null) {
			textFieldPlaceHolder = new JTextField();
			textFieldPlaceHolder.setColumns(10);
		}
		return textFieldPlaceHolder;
	}
}
