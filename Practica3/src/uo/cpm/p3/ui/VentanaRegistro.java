package uo.cpm.p3.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;

import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;

import uo.cpm.p3.model.Customer;
import uo.cpm.p3.service.McDonalds;

public class VentanaRegistro extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btSiguiente;
	private JButton btCancelar;
	private JPanel panel;
	private JTextField NameAndSurname;
	private JComboBox<Integer> comboBox;
	private JPanel Pedido;
	private JLabel lblAoDeNacimiento;
	private JLabel lblPassword;
	private JLabel lblReintroduzcaPassword;
	private JPasswordField passwordFieldReintroduce;
	private JPasswordField passwordField;
	private JRadioButton rdbtnLocal;
	private JRadioButton rdbtnLlevar;
	private JLabel lblNombreYApellidos;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private VentanaPrincipal vp;
	


	/**
	 * Create the frame.
	 * @param actionListener 
	 */
	public VentanaRegistro(VentanaPrincipal vp) {
		this.vp = vp;
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistro.class.getResource("/img/logo.png")));
		setBackground(new Color(255, 255, 255));
		setTitle("McDonald's: Datos de Registro");
		setDefaultCloseOperation(HIDE_ON_CLOSE); // esto sirve para que la app finalice TODOS sus procesos al darle a la x, tambien sirve para que as ventanas secundarias no cierren la app entera
		setBounds(100, 100, 618, 381);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtSiguiente());
		contentPane.add(getBtCancelar());
		contentPane.add(getPanel());
		contentPane.add(getPedido());
	}
	private JButton getBtSiguiente() {
		if (btSiguiente == null) {
			btSiguiente = new JButton("Siguiente");
			btSiguiente.setMnemonic('A');
			btSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Checker c = new Checker();
					if(!c.checkTextField(getNameAndSurname())) {
						JOptionPane.showMessageDialog(null, "Por favor introduzca su nombre y apellidos");
					}
					else if(!c.checkPasswordEmpty(getPasswordFieldReintroduce())) {
						JOptionPane.showMessageDialog(null, "Por favor introduzca su contraseña");
					}
					else if(!c.checkPasswordEmpty(getPasswordField())) {
						JOptionPane.showMessageDialog(null, "Por favor reintroduzca su contraseña");
					}
					else if(!c.checkPasswordTheSame(getPasswordFieldReintroduce(), getPasswordField())) {
						JOptionPane.showMessageDialog(null, "Las contraseñas no son iguales, por favor reintroduzca su contraseña");
					}
					else {
						if(c.checkYear(getComboBox())) 
							JOptionPane.showMessageDialog(null, "Debe ser mayor de edad para disfrutar del servicio completo");
						JOptionPane.showMessageDialog(null, "¡Registro completado!");
						if(c.checkButtons(getRdbtnLlevar())) {
							vp.getMc().setOrderType(true);
						}
						actualizarDatosPedido();
						mostrarVentanaConfirmacion();
					}
					
				}

				
				private void actualizarDatosPedido() {
					vp.getMc().saveCustomerData(getNameAndSurname().getText(), (int)getComboBox().getSelectedItem(), String.valueOf(getPasswordField().getPassword()));
				}
			});
			
			
			btSiguiente.setBounds(360, 296, 89, 23);
			btSiguiente.setForeground(new Color(255, 255, 255));
			btSiguiente.setBackground(new Color(0, 128, 0));
		}
		return btSiguiente;
	}
	private void mostrarVentanaConfirmacion() {
		VentanaConfirmacion vr = new VentanaConfirmacion(this);
		vr.setModal(true);
		vr.setVisible(true);
		
	}
	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.setMnemonic('B');
			btCancelar.setBounds(459, 296, 89, 23);
			btCancelar.setForeground(new Color(255, 255, 255));
			btCancelar.setBackground(new Color(255, 0, 0));
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose(); //para que se cierre la app
				}
			});
		}
		return btCancelar;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBackground(Color.WHITE);
			panel.setBounds(36, 35, 524, 216);
			panel.setLayout(null);
			panel.add(getNameAndSurname());
			panel.add(getComboBox());
			panel.add(getLblAoDeNacimiento());
			panel.add(getLblPassword());
			panel.add(getLblReintroduzcaPassword());
			panel.add(getPasswordFieldReintroduce());
			panel.add(getPasswordField());
			panel.add(getLblNombreYApellidos());
		}
		return panel;
	}
	private JTextField getNameAndSurname() {
		if (NameAndSurname == null) {
			NameAndSurname = new JTextField();
			NameAndSurname.setColumns(10);
			NameAndSurname.setBounds(190, 34, 255, 19);
		}
		return NameAndSurname;
	}
	private JComboBox<Integer> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<Integer>();
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
			comboBox.setModel(new DefaultComboBoxModel<Integer>(yearGenerator()));
			comboBox.setBounds(190, 74, 140, 21);
		}
		return comboBox;
	}
	private Integer[] yearGenerator() {
		int anoActual = LocalDateTime.now().getYear();
		int rango = Customer.EDAD_MAX-Customer.EDAD_MIN;
		Integer[] years = new Integer[rango];
		for(int i=0; i<rango; i++) {
			years[i]=(anoActual-Customer.EDAD_MIN)-i;
		}
		return years;
	}
	private JPanel getPedido() {
		if (Pedido == null) {
			Pedido = new JPanel();
			Pedido.setBorder(new TitledBorder(null, "Pedido", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			Pedido.setBackground(Color.WHITE);
			Pedido.setBounds(36, 277, 268, 57);
			Pedido.setLayout(null);
			Pedido.add(getRdbtnLocal());
			Pedido.add(getRdbtnLlevar());
		}
		return Pedido;
	}
	private JLabel getLblAoDeNacimiento() {
		if (lblAoDeNacimiento == null) {
			lblAoDeNacimiento = new JLabel("A\u00F1o de nacimiento:");
			lblAoDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblAoDeNacimiento.setBounds(30, 69, 127, 27);
		}
		return lblAoDeNacimiento;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password:");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblPassword.setBounds(30, 117, 127, 27);
		}
		return lblPassword;
	}
	private JLabel getLblReintroduzcaPassword() {
		if (lblReintroduzcaPassword == null) {
			lblReintroduzcaPassword = new JLabel("Reintroduzca Password:");
			lblReintroduzcaPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblReintroduzcaPassword.setBounds(30, 159, 170, 27);
		}
		return lblReintroduzcaPassword;
	}
	private JPasswordField getPasswordFieldReintroduce() {
		if (passwordFieldReintroduce == null) {
			passwordFieldReintroduce = new JPasswordField();
			passwordFieldReintroduce.setBounds(190, 163, 188, 19);
		}
		return passwordFieldReintroduce;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(190, 123, 188, 19);
		}
		return passwordField;
	}
	private JRadioButton getRdbtnLocal() {
		if (rdbtnLocal == null) {
			rdbtnLocal = new JRadioButton("Local");
			rdbtnLocal.setBackground(Color.WHITE);
			rdbtnLocal.setSelected(true);
			buttonGroup.add(rdbtnLocal);
			rdbtnLocal.setBounds(27, 18, 103, 21);
		}
		return rdbtnLocal;
	}
	private JRadioButton getRdbtnLlevar() {
		if (rdbtnLlevar == null) {
			rdbtnLlevar = new JRadioButton("Llevar");
			rdbtnLlevar.setBackground(Color.WHITE);
			buttonGroup.add(rdbtnLlevar);
			rdbtnLlevar.setBounds(147, 18, 103, 21);
		}
		return rdbtnLlevar;
	}
	private JLabel getLblNombreYApellidos() {
		if (lblNombreYApellidos == null) {
			lblNombreYApellidos = new JLabel("Nombre y Apellidos:");
			lblNombreYApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNombreYApellidos.setBounds(30, 28, 140, 27);
		}
		return lblNombreYApellidos;
	}
	public VentanaPrincipal getVp() {
		return vp;
	}
}
