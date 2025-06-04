package uo.cpm.p2.ui;

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
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaRegistro extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btSiguiente;
	private JButton btCancelar;
	private JPanel panel;
	private JTextField NameAndSurname;
	private JComboBox comboBox;
	private JPanel Pedido;
	private JLabel lblAoDeNacimiento;
	private JLabel lblPassword;
	private JLabel lblReintroduzcaPassword;
	private JPasswordField passwordFieldReintroduce;
	private JPasswordField passwordField;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnLlevar;
	private JLabel lblNewLabel;
	private JLabel lblNombreYApellidos;
	private JLabel lblClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistro frame = new VentanaRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaRegistro() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistro.class.getResource("/uo/cpm/p2/ui/mobile-register-icon-online-learning-flat-design-vector-5994807.jpg")));
		setBackground(new Color(255, 255, 255));
		setTitle("Datos de Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // esto sirve para que la app finalice TODOS sus procesos al darle a la x, tambien sirve para que as ventanas secundarias no cierren la app entera
		setBounds(100, 100, 618, 381);
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
					else if(((!c.checkButtons(getRdbtnLlevar())) && (!c.checkButtons(getRdbtnNewRadioButton())) || ((c.checkButtons(getRdbtnLlevar())) && (c.checkButtons(getRdbtnNewRadioButton()))))) {
						JOptionPane.showMessageDialog(null, "Pedido inválido, por favor cambie los ajustes");
					}
					else {
						if(c.checkYear(getComboBox())) 
							JOptionPane.showMessageDialog(null, "Debe ser mayor de edad para disfrutar del servicio completo");
						JOptionPane.showMessageDialog(null, "¡Registro completado!");
					}
					
				}
			});
			btSiguiente.setBounds(360, 296, 89, 23);
			btSiguiente.setForeground(new Color(255, 255, 255));
			btSiguiente.setBackground(new Color(0, 128, 0));
		}
		return btSiguiente;
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
					System.exit(0); //para que se cierre la app
				}
			});
		}
		return btCancelar;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
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
			panel.add(getLblClient());
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
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900"}));
			comboBox.setBounds(190, 74, 140, 21);
		}
		return comboBox;
	}
	private JPanel getPedido() {
		if (Pedido == null) {
			Pedido = new JPanel();
			Pedido.setBounds(36, 277, 268, 57);
			Pedido.setLayout(null);
			Pedido.add(getRdbtnNewRadioButton());
			Pedido.add(getRdbtnLlevar());
			Pedido.add(getLblNewLabel());
		}
		return Pedido;
	}
	private JLabel getLblAoDeNacimiento() {
		if (lblAoDeNacimiento == null) {
			lblAoDeNacimiento = new JLabel("A\u00F1o de nacimiento");
			lblAoDeNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblAoDeNacimiento.setBounds(30, 69, 127, 27);
		}
		return lblAoDeNacimiento;
	}
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblPassword.setBounds(30, 117, 127, 27);
		}
		return lblPassword;
	}
	private JLabel getLblReintroduzcaPassword() {
		if (lblReintroduzcaPassword == null) {
			lblReintroduzcaPassword = new JLabel("Reintroduzca Password");
			lblReintroduzcaPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblReintroduzcaPassword.setBounds(31, 157, 170, 27);
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
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("Local");
			rdbtnNewRadioButton.setBounds(27, 18, 103, 21);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnLlevar() {
		if (rdbtnLlevar == null) {
			rdbtnLlevar = new JRadioButton("Llevar");
			rdbtnLlevar.setBounds(147, 18, 103, 21);
		}
		return rdbtnLlevar;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Pedido");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel.setBounds(10, 0, 45, 13);
		}
		return lblNewLabel;
	}
	private JLabel getLblNombreYApellidos() {
		if (lblNombreYApellidos == null) {
			lblNombreYApellidos = new JLabel("Nombre y Apellidos");
			lblNombreYApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNombreYApellidos.setBounds(30, 28, 140, 27);
		}
		return lblNombreYApellidos;
	}
	private JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Cliente");
			lblClient.setBounds(12, 0, 55, 16);
			lblClient.setFont(new Font("Dialog", Font.BOLD, 15));
		}
		return lblClient;
	}
}
