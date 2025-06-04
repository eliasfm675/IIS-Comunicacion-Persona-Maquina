package uo.cpm.examen.ui;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.examen.model.Articulo;
import uo.cpm.examen.service.Ruleta;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnRuleta;
	private JLabel lblRuleta;
	private JTextField textFieldPuntos;
	private JLabel lblNewLabel;
	private Ruleta rul;
	private ProcessAction pa = new ProcessAction();
	private ProcessImage pi = new ProcessImage();
	private ProcessBuy pb = new ProcessBuy();
	private ProcessHide ph = new ProcessHide();
	private ProcessFinal pf = new ProcessFinal();
	private JLabel lblPremio;
	private JComboBox<Articulo> comboBoxPremios;
	private JButton btnComprar;
	private JLabel lblPremios;
	private JLabel lblyaComprado;
	private JToggleButton tglbtnLista;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton btnFinalizar;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmSalir;
	private JMenuItem mntmNuevo;
	private JMenuItem mntmAcercaDe;
	private JSeparator separator;
	private JButton btnInfantil;
	private JButton btnOcio;
	private JButton btnElectronica;
	private class ProcessAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			tirarRuleta();
			
		}

	
		
	}
	private class ProcessImage implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Articulo selected = (Articulo) getComboBoxPremios().getSelectedItem();
			getLblPremio().setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/" + rul.getArticulo(selected).getCodigo() + ".png")));
			getLblyaComprado().setText("");
			
		}
		
	}
	private class ProcessBuy implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Articulo selectedPrize = (Articulo) getComboBoxPremios().getSelectedItem();
			if(rul.purchase(selectedPrize)) {
				actualizarPuntos((int) rul.getPoints());
				getLblyaComprado().setText("¡El producto ha sido canjeado!");
				rul.add(selectedPrize);
				getTextArea().setText(rul.toStringGetArticulosSeleccionados());
				getBtnFinalizar().setEnabled(true);
				
			}else{
				getLblyaComprado().setText("¡No tienes suficientes puntos!");
				
			}
			
			
		}
		
	}
	private class ProcessHide implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(getScrollPane().isVisible()) {
				getScrollPane().setVisible(false);
			}else {
				getScrollPane().setVisible(true);
			}
			
			
		}
		
	}
	private class ProcessFinal implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(JOptionPane.showConfirmDialog(null, "¿Seguro que quieres finalizar?")==JOptionPane.YES_OPTION){
				JOptionPane.showMessageDialog(null, "Enhorabuena has recibido los regalos seleccionados", "Seleccion de regalos", JOptionPane.INFORMATION_MESSAGE);
				getBtnComprar().setEnabled(false);
				getBtnFinalizar().setEnabled(false);
				getBtnRuleta().setEnabled(false);
				getBtnComprar().setEnabled(true);
			}
			
			
			
		}
		
	}
	
	
	public VentanaPrincipal(Ruleta rul) {
		this.rul = rul;
		setTitle("Ruleta - Juego\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/ruleta.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 399);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 184, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnRuleta());
		contentPane.add(getLblRuleta());
		contentPane.add(getTextFieldPuntos());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblPremio());
		contentPane.add(getComboBoxPremios());
		contentPane.add(getBtnComprar());
		contentPane.add(getLblPremios());
		contentPane.add(getLblyaComprado());
		contentPane.add(getTglbtnLista());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnFinalizar());
		contentPane.add(getBtnInfantil());
		contentPane.add(getBtnOcio());
		contentPane.add(getBtnElectronica());
		setLocationRelativeTo(null);
	}
	private JButton getBtnRuleta() {
		if (btnRuleta == null) {
			btnRuleta = new JButton("");
			btnRuleta.setEnabled(true);
			btnRuleta.setMnemonic('R');
			btnRuleta.setBackground(new Color(222, 184, 135));
			btnRuleta.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/ruleta.png")));
			btnRuleta.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/ruleta.png")));
			btnRuleta.setBounds(10, 23, 229, 215);
			btnRuleta.addActionListener(pa);
		}
		return btnRuleta;
	}
	private JLabel getLblRuleta() {
		if (lblRuleta == null) {
			lblRuleta = new JLabel("Haga clic en la ruleta o pulse Alt-R para tirar");
			lblRuleta.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblRuleta.setForeground(new Color(255, 255, 255));
			lblRuleta.setBounds(12, 226, 268, 35);
		}
		return lblRuleta;
	}
	private JTextField getTextFieldPuntos() {
		if (textFieldPuntos == null) {
			textFieldPuntos = new JTextField();
			textFieldPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldPuntos.setEditable(false);
			textFieldPuntos.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textFieldPuntos.setBounds(272, 40, 133, 35);
			textFieldPuntos.setColumns(10);
		}
		return textFieldPuntos;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Puntos");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(260, 10, 145, 23);
		}
		return lblNewLabel;
	}
	private JLabel getLblPremio() {
		if (lblPremio == null) {
			lblPremio = new JLabel("");
			lblPremio.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/EL01.png")));
			lblPremio.setBounds(269, 99, 154, 133);
		}
		return lblPremio;
	}
	private JComboBox getComboBoxPremios() {
		if (comboBoxPremios == null) {
			comboBoxPremios = new JComboBox<Articulo>();
			comboBoxPremios.setModel(new DefaultComboBoxModel<Articulo>(rul.getArticulos()));
			comboBoxPremios.setBounds(247, 297, 306, 23);
			comboBoxPremios.addActionListener(pi);
		
		}
		return comboBoxPremios;
	}
	private void tirarRuleta() {
		int points = rul.lanzar();
		actualizarPuntos(points);
		getBtnRuleta().setEnabled(false);
	}
	protected void actualizarPuntos(int points) {
		rul.editPoints(points);
		getTextFieldPuntos().setText(String.valueOf(rul.getPoints()));
	}
	private JButton getBtnComprar() {
		if (btnComprar == null) {
			btnComprar = new JButton("Comprar producto imagen");
			btnComprar.setMnemonic('C');
			btnComprar.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnComprar.setBounds(20, 273, 195, 35);
			btnComprar.addActionListener(pb);
		}
		return btnComprar;
	}
	private JLabel getLblPremios() {
		if (lblPremios == null) {
			lblPremios = new JLabel("Premios");
			lblPremios.setDisplayedMnemonic('P');
			lblPremios.setLabelFor(getComboBoxPremios());
			lblPremios.setForeground(new Color(255, 255, 255));
			lblPremios.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblPremios.setHorizontalAlignment(SwingConstants.CENTER);
			lblPremios.setBounds(303, 262, 145, 23);
		}
		return lblPremios;
	}
	private JLabel getLblyaComprado() {
		if (lblyaComprado == null) {
			lblyaComprado = new JLabel("");
			lblyaComprado.setBackground(new Color(255, 255, 255));
			lblyaComprado.setFont(new Font("Dialog", Font.BOLD, 10));
			lblyaComprado.setForeground(new Color(255, 255, 255));
			lblyaComprado.setBounds(431, 148, 174, 90);
		}
		return lblyaComprado;
	}
	private JToggleButton getTglbtnLista() {
		if (tglbtnLista == null) {
			tglbtnLista = new JToggleButton("");
			tglbtnLista.setToolTipText("Ocultar o ense\u00F1ar lista");
			tglbtnLista.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/ruleta.png")));
			tglbtnLista.setBounds(576, 23, 44, 41);
			tglbtnLista.addActionListener(ph);
		}
		return tglbtnLista;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(433, 23, 133, 115);
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setText(rul.toStringGetArticulosSeleccionados());
		}
		return textArea;
	}
	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton("Finalizar");
			btnFinalizar.setMnemonic('f');
			btnFinalizar.setBounds(506, 250, 99, 26);
			btnFinalizar.setEnabled(false);
			btnFinalizar.addActionListener(pf);
		}
		return btnFinalizar;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnNewMenu());
		}
		return menuBar;
	}
	private JMenu getMnNewMenu() {
		if (mnNewMenu == null) {
			mnNewMenu = new JMenu("Opciones");
			mnNewMenu.setMnemonic('O');
			mnNewMenu.add(getMntmNuevo());
			mnNewMenu.add(getMntmAcercaDe());
			mnNewMenu.add(getSeparator());
			mnNewMenu.add(getMntmSalir());
		}
		return mnNewMenu;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null, "Seguro que quieres salir?")==JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				}
			});
		}
		return mntmSalir;
	}
	private JMenuItem getMntmNuevo() {
		if (mntmNuevo == null) {
			mntmNuevo = new JMenuItem("Nuevo");
			mntmNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null, "¿Seguro que euieres reiniciar?")==JOptionPane.YES_OPTION){
					rul.restart();
					getTextFieldPuntos().setText(""+rul.getPoints());
					getTextArea().setText(rul.toStringGetArticulosSeleccionados());
					getBtnFinalizar().setEnabled(false);
					getBtnRuleta().setEnabled(true);
					comboBoxPremios.setModel(new DefaultComboBoxModel<Articulo>(rul.getArticulos()));
					getComboBoxPremios().setSelectedIndex(0);
					}
				}
			});
		}
		return mntmNuevo;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de");
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Hecho por Elías Fernádez Medina", "Acerca de - Creador", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return mntmAcercaDe;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JButton getBtnInfantil() {
		if (btnInfantil == null) {
			btnInfantil = new JButton("New button");
			btnInfantil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comboBoxPremios.setModel(new DefaultComboBoxModel<Articulo>(rul.getArticulosInfantil())); {
					};
				}
			});
			btnInfantil.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/iconoInfantil.png")));
			btnInfantil.setBounds(272, 230, 33, 26);
		}
		return btnInfantil;
	}
	private JButton getBtnOcio() {
		if (btnOcio == null) {
			btnOcio = new JButton("New button");
			btnOcio.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/iconoOcio.png")));
			btnOcio.setBounds(309, 230, 46, 31);
			btnOcio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comboBoxPremios.setModel(new DefaultComboBoxModel<Articulo>(rul.getArticulosOcio())); {
					};
				}
			});
		}
		return btnOcio;
	}
	private JButton getBtnElectronica() {
		if (btnElectronica == null) {
			btnElectronica = new JButton("New button");
			btnElectronica.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/iconoElectronica.png")));
			btnElectronica.setBounds(367, 230, 46, 31);
			btnElectronica.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comboBoxPremios.setModel(new DefaultComboBoxModel<Articulo>(rul.getArticulosElectronicos())); {
					};
				}
			});
		}
		return btnElectronica;
	}
}
