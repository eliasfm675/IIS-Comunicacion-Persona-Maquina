package uo.cpm.p11.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import uo.cpm.p11.model.Articulo;
import uo.cpm.p11.model.Cliente;
import uo.cpm.p11.service.McDonalds;
import javax.swing.ListSelectionModel;



public class VentanaPrincipal extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogo;
	private JLabel lblLblnombre;
	private JButton btSig1;
	private JPanel pnSur;
	private JPanel pnNorte;
	private JPanel pnArticulos;
	private JPanel pnInfo;
	private JTextField txPrecio;
	private JButton btAnular1;
	private JPanel pnCentro;
	private JPanel pnDatosCliente;
	private JLabel lbNombre;
	private JTextField txNombre;
	private JLabel lbAño;
	private JComboBox<String> cbAnio;
	private JLabel lbPasw1;
	private JPasswordField pwPass1;
	private JLabel lbPasw2;
	private JPasswordField pwPass2;
	private JPanel pnRegistro;
	private JPanel pnDatosPedido;
	private JRadioButton rbLocal;
	private JRadioButton rbLlevar;
	private JButton btAnt2;
	private JButton btSig2;
	private JPanel pnConfirmacion;
	private JButton btAnt3;
	private JButton btFin3;
	private JLabel lbAviso;
	private JLabel lbOk;
	private JLabel lbCodigo;
	private JTextField txCodigo;
	private final ButtonGroup grPedido = new ButtonGroup();
	private McDonalds mcDonalds;
	private JScrollPane pnPrincipal;
	private JPanel pnBts;
	private JPanel pnBtPrincipal;
	private JPanel pnBtRegistro;
	private JPanel pnBtConfirmacion;
	private JTabbedPane pnPedido;
	private JScrollPane scrollComida;
	private JScrollPane scrollBebida;
	private JList<Articulo> listComida;
	private JList<Articulo> listBebida;
	private DefaultListModel<Articulo> modeloListComida;
	private DefaultListModel<Articulo> modeloListBebida;
	private AccionBoton aB = new AccionBoton();
	class AccionBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton bt = (JButton) e.getSource();
			anadirAPedido(Integer.parseInt(bt.getActionCommand()));
			//tambien se puede hacer añadirAPedido(Integer.parseint(e.getActionCommand()));
			
		}

		
		
	}
	private void anadirAPedido(int posArticuloEnCarta) {
		Articulo a = mcDonalds.getArticulo(posArticuloEnCarta);
		mcDonalds.añadirAPedido(a, 1);
		mostrarEnLista(a);
		getTxPrecio().setText("Precio: " + String.format("%.2f", mcDonalds.getImportePedido()));
		if(!getBtSig1().isEnabled()) {
			getBtSig1().setEnabled(true);
		}
		
	}

	private void mostrarEnLista(Articulo a) {
		if(a.getTipo().equals("Bebida")){
			modeloListBebida.addElement(a);
		}else {
			modeloListComida.addElement(a);
		}
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(McDonalds mc1) {
		mcDonalds = mc1;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logo.PNG")));
		setTitle("McDonald's España");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 876);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnNorte(), BorderLayout.NORTH);
		contentPane.add(getPnCentro(), BorderLayout.CENTER);
		contentPane.add(getPnSur(), BorderLayout.SOUTH);
	}
	
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.PNG")));
		}
		return lblLogo;
	}
	private JLabel getLblLblnombre() {
		if (lblLblnombre == null) {
			lblLblnombre = new JLabel("McDonald's");
			lblLblnombre.setFont(new Font("Arial Black", Font.PLAIN, 44));
			lblLblnombre.setForeground(Color.BLACK);
		}
		return lblLblnombre;
	}
	private JButton getBtSig1() {
		if (btSig1 == null) {
			btSig1 = new JButton("Siguiente");
			btSig1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnRegistro();
				}
			});
			btSig1.setEnabled(false);
			btSig1.setForeground(Color.WHITE);
			btSig1.setBackground(new Color(0, 128, 0));
			btSig1.setFont(new Font("Arial Black", Font.PLAIN, 16));
			btSig1.setMnemonic('S');
			
		}
		return btSig1;
	}

	private void mostrarPnRegistro() {
		((CardLayout)getPnCentro().getLayout()).show(getPnCentro(), "pnRegistro");
		((CardLayout)getPnBts().getLayout()).show(getPnBts(), "pnBtRegistro");
	}
	private void mostrarPnConfirmacion() {
		((CardLayout)getPnCentro().getLayout()).show(getPnCentro(), "pnConfirmacion");
		((CardLayout)getPnBts().getLayout()).show(getPnBts(), "pnBtConfirmacion");
	}
	
	private void inicializar() {
		mcDonalds.inicializarPedido();
		getTxPrecio().setText("Precio: "+mcDonalds.getImportePedido());
		modeloListComida = new DefaultListModel<Articulo>();
		modeloListBebida = new DefaultListModel<Articulo>();
		getListComida().setModel(modeloListComida);
		getListBebida().setModel(modeloListBebida);
		getBtSig1().setEnabled(false);
	}
	
	private JPanel getPnSur() {
		if (pnSur == null) {
			pnSur = new JPanel();
			pnSur.setBackground(Color.WHITE);
			pnSur.setLayout(new BorderLayout(0, 0));
			pnSur.add(getPnInfo(), BorderLayout.SOUTH);
			pnSur.add(getPnPedido(), BorderLayout.CENTER);
		
		}
		return pnSur;
	}
	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setBackground(Color.WHITE);
			pnNorte.setLayout(new GridLayout(1, 0, 0, 0));
			pnNorte.add(getLblLogo());
			pnNorte.add(getLblLblnombre());
		}
		return pnNorte;
	}
	
	private JScrollPane getPnPrincipal() {
		if (pnPrincipal == null) {
			pnPrincipal = new JScrollPane();
			pnPrincipal.setBorder(new LineBorder(Color.ORANGE, 4));
			pnPrincipal.setBackground(Color.WHITE);
			pnPrincipal.setViewportView(getPnArticulos());
			
		}
		return pnPrincipal;
	}
	
	private JPanel getPnArticulos() {
		if (pnArticulos == null) {
			pnArticulos = new JPanel();
			pnArticulos.setBorder(new LineBorder(Color.ORANGE, 4));
			pnArticulos.setBackground(Color.WHITE);
			// razonar el layout adecuado
			pnArticulos.setLayout(new GridLayout(4, 6, 0, 3));
			creaBotonesArticulos();
		}
		return pnArticulos;
	}
	
	private void setImagenAdaptada(JButton boton, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance((int) (100), (int) (100),Image.SCALE_SMOOTH);
		 //si se quiere reescalar la ventana, este 100 tendria que cambiarse por el width y el height  respectivamente de la imagen para que así se reescale cada vez que cambias el tamaño
		 //además este método debería llamarse en el constructor de la ventana mientras se usa un evento componentResized
		 boton.setIcon(new ImageIcon(imgEscalada));
	}
	
	private void creaBotonesArticulos() {
		//Eliminamos antes todos los botones para que no se sumen a los anteriores
		getPnArticulos().removeAll();
		for (int i = 0; i < mcDonalds.getNumArticulos(); i++)     
			getPnArticulos().add(nuevoBoton(i));
		}
	

	private JButton nuevoBoton(int posicion) {
		JButton boton = new JButton("");
		boton.setBackground(Color.white);
		boton.setBorder(new LineBorder(Color.LIGHT_GRAY, 2 , true));
		boton.setToolTipText(mcDonalds.getToStringArticulo(posicion));
		boton.setActionCommand(String.valueOf(posicion));
		setImagenAdaptada(boton, "/img/" + mcDonalds.getCodigoArticulo(posicion) + ".png");
		boton.addActionListener(aB);
		return boton;
	}

	
	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBackground(Color.WHITE);
			pnInfo.setLayout(new GridLayout(1, 3, 0, 0));
			pnInfo.add(getTxPrecio());
			pnInfo.add(getPnBts());
		}
		return pnInfo;
	}
	
	private JTextField getTxPrecio() {
		if (txPrecio == null) {
			txPrecio = new JTextField();
			txPrecio.setText("Precio: 0.0");
			txPrecio.setForeground(Color.WHITE);
			txPrecio.setFont(new Font("Arial Black", Font.BOLD, 16));
			txPrecio.setEditable(false);
			txPrecio.setColumns(10);
			txPrecio.setBackground(Color.ORANGE);
		}
		return txPrecio;
	}
	
	private JButton getBtAnular1() {
		if (btAnular1 == null) {
			btAnular1 = new JButton("Anular");
			btAnular1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			btAnular1.setMnemonic('A');
			btAnular1.setForeground(Color.WHITE);
			btAnular1.setFont(new Font("Arial Black", Font.PLAIN, 16));
			btAnular1.setBackground(Color.RED);
		}
		return btAnular1;
	}
	
	
	
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setLayout(new CardLayout(0, 0));
			pnCentro.add(getPnPrincipal(), "pnPrincipal");
			pnCentro.add(getPnRegistro(), "pnRegistro");
			pnCentro.add(getPnConfirmacion(), "pnConfirmacion");
		}
		return pnCentro;
	}
	
	
	private JComboBox<String> getCbAnio() {
		if (cbAnio == null) {
			cbAnio = new JComboBox<String>();
			cbAnio.setFont(new Font("Arial", Font.PLAIN, 14));
			cbAnio.setModel(new DefaultComboBoxModel<String>(getAnios()));
			cbAnio.setBounds(new Rectangle(249, 77, 157, 25));
		}
		return cbAnio;
	}
	
	public String[] getAnios() {
		int anioActual = LocalDateTime.now().getYear();
		int rango = Cliente.EDAD_MAX-Cliente.EDAD_MIN;
		String[]anios = new String[rango];
		for (int i=0;i<rango;i++)
			anios[i] = String.valueOf((anioActual-Cliente.EDAD_MIN)-i);
		return anios;
	}

	
	private JPanel getPnDatosCliente() {
		if (pnDatosCliente == null) {
			pnDatosCliente = new JPanel();
			pnDatosCliente.setBounds(104, 58, 656, 255);
			pnDatosCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", java.awt.Font.BOLD, 14), new java.awt.Color(51,51,51)));
			pnDatosCliente.setBackground(Color.WHITE);
			pnDatosCliente.setLayout(null);
			pnDatosCliente.add(getLbNombre());
			pnDatosCliente.add(getTxNombre());
			pnDatosCliente.add(getLbAño());
			pnDatosCliente.add(getCbAnio());
			pnDatosCliente.add(getLbPasw1());
			pnDatosCliente.add(getPwPass1());
			pnDatosCliente.add(getLbPasw2());
			pnDatosCliente.add(getPwPass2());
		}
		return pnDatosCliente;
	}
	
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel();
			lbNombre.setText("Nombre y Apellidos:");
			lbNombre.setFont(new Font("Arial", Font.PLAIN, 14));
			lbNombre.setDisplayedMnemonic('N');
			lbNombre.setBounds(30, 31, 132, 20);
		}
		return lbNombre;
	}
	
	private JTextField getTxNombre() {
		if (txNombre == null) {
			txNombre = new JTextField();
			txNombre.setFont(new Font("Arial", Font.PLAIN, 14));
			txNombre.setBounds(249, 24, 330, 25);
		}
		return txNombre;
	}
	
	private JLabel getLbAño() {
		if (lbAño == null) {
			lbAño = new JLabel("A\u00F1o de nacimiento:");
			lbAño.setFont(new Font("Arial", Font.PLAIN, 14));
			lbAño.setDisplayedMnemonic('A');
			lbAño.setBounds(30, 81, 167, 16);
		}
		return lbAño;
	}

	private JLabel getLbPasw1() {
		if (lbPasw1 == null) {
			lbPasw1 = new JLabel();
			lbPasw1.setText("Password:");
			lbPasw1.setFont(new Font("Arial", Font.PLAIN, 14));
			lbPasw1.setDisplayedMnemonic('P');
			lbPasw1.setBounds(new Rectangle(13, 123, 105, 16));
			lbPasw1.setBounds(30, 129, 105, 16);
		}
		return lbPasw1;
	}
	
	private JPasswordField getPwPass1() {
		if (pwPass1 == null) {
			pwPass1 = new JPasswordField();
			pwPass1.setFont(new Font("Arial", Font.PLAIN, 14));
			pwPass1.setBounds(new Rectangle(176, 121, 218, 25));
			pwPass1.setBounds(249, 122, 218, 25);
		}
		return pwPass1;
	}
	
	private JLabel getLbPasw2() {
		if (lbPasw2 == null) {
			lbPasw2 = new JLabel();
			lbPasw2.setText("Reintroduzca password:");
			lbPasw2.setFont(new Font("Arial", Font.PLAIN, 14));
			lbPasw2.setDisplayedMnemonic('R');
			lbPasw2.setBounds(new Rectangle(13, 167, 151, 16));
			lbPasw2.setBounds(30, 181, 195, 16);
		}
		return lbPasw2;
	}
	
	private JPasswordField getPwPass2() {
		if (pwPass2 == null) {
			pwPass2 = new JPasswordField();
			pwPass2.setFont(new Font("Arial", Font.PLAIN, 14));
			pwPass2.setBounds(new Rectangle(176, 163, 218, 25));
			pwPass2.setBounds(249, 172, 218, 25);
		}
		return pwPass2;
	}
	
	
	private boolean validarFormulario() {
		if (isVacio())
			JOptionPane.showMessageDialog(null, "Error: Hay algún campo en blanco");
		else
			if (isIncorrecta())
				JOptionPane.showMessageDialog(null, "Error: Las passwords no coinciden");
			else {
				actualizarDatosPedido();
				return true;
			}
		return false;
	}
	
	private void actualizarDatosPedido() {
		mcDonalds.guardarCliente(this.getTxNombre().getText(), Integer.parseInt((String)this.getCbAnio().getSelectedItem()), String.valueOf(getPwPass1().getPassword()));
		mcDonalds.actualizarTipoPedido(getRbLocal().isSelected());
	}
	
	private boolean isVacio() {
		return (getTxNombre().getText().isBlank()||(String.valueOf(getPwPass1().getPassword()).isBlank())||(String.valueOf(getPwPass2().getPassword()).isBlank())); 
	}

	private boolean isIncorrecta() {
		return (!String.valueOf(getPwPass2().getPassword()).equals(String.valueOf(getPwPass1().getPassword())));
	}
	
	
	
	private JPanel getPnRegistro() {
		if (pnRegistro == null) {
			pnRegistro = new JPanel();
			pnRegistro.setBorder(new LineBorder(Color.ORANGE, 4));
			pnRegistro.setBackground(Color.WHITE);
			pnRegistro.setLayout(null);
			pnRegistro.add(getPnDatosCliente());
			pnRegistro.add(getPnDatosPedido());
		}
		return pnRegistro;
	}
	
	private JPanel getPnDatosPedido() {
		if (pnDatosPedido == null) {
			pnDatosPedido = new JPanel();
			pnDatosPedido.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Pedido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", java.awt.Font.BOLD, 14), new java.awt.Color(51,51,51)));
			pnDatosPedido.setBackground(Color.WHITE);
			pnDatosPedido.setBounds(104, 339, 248, 75);
			pnDatosPedido.add(getRbLocal());
			pnDatosPedido.add(getRbLlevar());
		}
		return pnDatosPedido;
	}
	
	private JRadioButton getRbLocal() {
		if (rbLocal == null) {
			rbLocal = new JRadioButton();
			grPedido.add(rbLocal);
			rbLocal.setText("Local");
			rbLocal.setSelected(true);
			rbLocal.setMnemonic('L');
			rbLocal.setFont(new Font("Arial", Font.PLAIN, 14));
			rbLocal.setBounds(new Rectangle(17, 27, 94, 24));
			rbLocal.setBackground(Color.WHITE);
		}
		return rbLocal;
	}
	
	private JRadioButton getRbLlevar() {
		if (rbLlevar == null) {
			rbLlevar = new JRadioButton();
			grPedido.add(rbLlevar);
			rbLlevar.setText("Llevar");
			rbLlevar.setMnemonic('r');
			rbLlevar.setFont(new Font("Arial", Font.PLAIN, 14));
			rbLlevar.setBounds(new Rectangle(115, 27, 86, 24));
			rbLlevar.setBackground(Color.WHITE);
		}
		return rbLlevar;
	}
	
	private JButton getBtAnt2() {
		if (btAnt2 == null) {
			btAnt2 = new JButton("Anterior");
			btAnt2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnPrincipal();
				}
			});
			btAnt2.setMnemonic('A');
			btAnt2.setForeground(Color.WHITE);
			btAnt2.setFont(new Font("Arial Black", Font.PLAIN, 16));
			btAnt2.setBackground(Color.RED);
		}
		return btAnt2;
	}

	private void mostrarPnPrincipal() {
		((CardLayout)getPnCentro().getLayout()).show(getPnCentro(), "pnPrincipal");
		((CardLayout)getPnBts().getLayout()).show(getPnBts(), "pnBtPrincipal");
	}
	
	private JButton getBtSig2() {
		if (btSig2 == null) {
			btSig2 = new JButton("Siguiente");
			btSig2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(validarFormulario()) {
						mostrarPnConfirmacion();
					}
					
				}
			});
			btSig2.setMnemonic('S');
			btSig2.setForeground(Color.WHITE);
			btSig2.setFont(new Font("Arial Black", Font.PLAIN, 16));
			btSig2.setBackground(new Color(0, 128, 0));
		}
		return btSig2;
	}
	
	private JPanel getPnConfirmacion() {
		if (pnConfirmacion == null) {
			pnConfirmacion = new JPanel();
			pnConfirmacion.setBorder(new LineBorder(Color.ORANGE, 4));
			pnConfirmacion.setBackground(Color.WHITE);
			pnConfirmacion.setLayout(null);
			pnConfirmacion.add(getLbAviso());
			pnConfirmacion.add(getLbOk());
			pnConfirmacion.add(getLbCodigo());
			pnConfirmacion.add(getTxCodigo());
		}
		return pnConfirmacion;
	}
	
	private JButton getBtAnt3() {
		if (btAnt3 == null) {
			btAnt3 = new JButton("Anterior");
			btAnt3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPnRegistro();
				}
			});
			btAnt3.setMnemonic('A');
			btAnt3.setForeground(Color.WHITE);
			btAnt3.setFont(new Font("Arial Black", Font.PLAIN, 16));
			btAnt3.setBackground(Color.RED);
		}
		return btAnt3;
	}
	
	private JButton getBtFin3() {
		if (btFin3 == null) {
			btFin3 = new JButton("Finalizar");
			btFin3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					finalizar();
				}
			});
			btFin3.setMnemonic('F');
			btFin3.setForeground(Color.WHITE);
			btFin3.setFont(new Font("Arial Black", Font.PLAIN, 16));
			btFin3.setBackground(new Color(0, 128, 0));
		}
		return btFin3;
	}
	
	private void finalizar() {
		mcDonalds.grabarPedido();
		inicializar();
		// completar para pasar al panel principal 
		mostrarPnPrincipal();
	}
	
	private JLabel getLbAviso() {
		if (lbAviso == null) {
			lbAviso = new JLabel("Pulse Finalizar para confirmar su pedido");
			lbAviso.setFont(new Font("Tahoma", Font.BOLD, 28));
			lbAviso.setBounds(135, 104, 568, 35);
		}
		return lbAviso;
	}
	
	private JLabel getLbOk() {
		if (lbOk == null) {
			lbOk = new JLabel("");
			lbOk.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/ok.png")));
			lbOk.setBounds(50, 91, 73, 52);
		}
		return lbOk;
	}
	
	private JLabel getLbCodigo() {
		if (lbCodigo == null) {
			lbCodigo = new JLabel("El c\u00F3digo de recogida es:");
			lbCodigo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lbCodigo.setBounds(138, 172, 191, 26);
		}
		return lbCodigo;
	}
	
	private JTextField getTxCodigo() {
		if (txCodigo == null) {
			txCodigo = new JTextField();
			txCodigo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txCodigo.setEditable(false);
			txCodigo.setText(mcDonalds.getCodigoPedido());
			txCodigo.setBounds(341, 161, 191, 45);
			txCodigo.setColumns(10);
		}
		return txCodigo;
	}
	private JPanel getPnBts() {
		if (pnBts == null) {
			pnBts = new JPanel();
			pnBts.setLayout(new CardLayout(0, 0));
			pnBts.add(getPnBtPrincipal(), "pnBtPrincipal");
			pnBts.add(getPnBtRegistro(), "pnBtRegistro");
			pnBts.add(getPnBtConfirmacion(), "pnBtConfirmacion");
			
		}
		return pnBts;
	}
	private JPanel getPnBtPrincipal() {
		if (pnBtPrincipal == null) {
			pnBtPrincipal = new JPanel();
			pnBtPrincipal.setLayout(new GridLayout(1, 2, 0, 0));
			pnBtPrincipal.add(getBtAnular1());
			pnBtPrincipal.add(getBtSig1());
		}
		return pnBtPrincipal;
	}
	private JPanel getPnBtRegistro() {
		if (pnBtRegistro == null) {
			pnBtRegistro = new JPanel();
			pnBtRegistro.setLayout(new GridLayout(1, 2, 0, 0));
			pnBtRegistro.add(getBtAnt2());
			pnBtRegistro.add(getBtSig2());
		}
		return pnBtRegistro;
	}
	private JPanel getPnBtConfirmacion() {
		if (pnBtConfirmacion == null) {
			pnBtConfirmacion = new JPanel();
			pnBtConfirmacion.setLayout(new GridLayout(0, 2, 0, 0));
			pnBtConfirmacion.add(getBtAnt3());
			pnBtConfirmacion.add(getBtFin3());
		}
		return pnBtConfirmacion;
	}
	private JTabbedPane getPnPedido() {
		if (pnPedido == null) {
			pnPedido = new JTabbedPane(JTabbedPane.TOP);
			pnPedido.addTab("Comida", null, getScrollComida(), null);
			pnPedido.setDisplayedMnemonicIndexAt(0, 0);
			pnPedido.addTab("Bebida", null, getScrollBebida(), null);
			pnPedido.setDisplayedMnemonicIndexAt(1, 0);
		}
		return pnPedido;
	}
	private JScrollPane getScrollComida() {
		if (scrollComida == null) {
			scrollComida = new JScrollPane();
			scrollComida.setViewportView(getListComida());
		}
		return scrollComida;
	}
	private JScrollPane getScrollBebida() {
		if (scrollBebida == null) {
			scrollBebida = new JScrollPane();
			scrollBebida.setViewportView(getListBebida());
		}
		return scrollBebida;
	}
	private JList<Articulo> getListComida() {
		if (listComida == null) {
			modeloListComida = new DefaultListModel<Articulo>();
			listComida = new JList<Articulo>(modeloListComida);
			listComida.setSelectedIndex(0);
			listComida.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		}
		return listComida;
	}
	private JList<Articulo> getListBebida() {
		if (listBebida == null) {
			modeloListBebida = new DefaultListModel<Articulo>();
			listBebida = new JList<Articulo>(modeloListBebida);
			listBebida.setSelectedIndex(0);
			listBebida.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listBebida;
	}
}
