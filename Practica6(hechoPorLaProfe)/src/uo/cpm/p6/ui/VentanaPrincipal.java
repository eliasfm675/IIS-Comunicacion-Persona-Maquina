package uo.cpm.p6.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import uo.cpm.p6.service.SpaceInvaders;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JMenu;
import javax.swing.JSeparator;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JPanel gridPanel;
	private JButton btn0;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JLabel lblEarth;
	private JLabel lblScore;
	private JTextField textFieldScore;
	private JPanel shootFlowPanel;
	private JLabel lblSpaceship;
	private SpaceInvaders spi;
	private JButton btnDice;
	private JLabel lblShoot;
	private JMenuBar menuBar;
	private JMenu mnJuego;
	private JMenuItem mntmNuevo;
	private JMenuItem mntmSalir;
	private JSeparator separator;
	private JMenu mnAyuda;
	private JMenuItem mntmAcercaDe;
	private JMenuItem mntmContenidos;
	private JSeparator separator_1;
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
	public VentanaPrincipal(SpaceInvaders spi) {
		this.spi = spi;
		setTitle("Space Invaders");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/earth.jpg")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 400);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getGridPanel());
		contentPane.add(getLblEarth());
		contentPane.add(getLblScore());
		contentPane.add(getTextFieldScore());
		contentPane.add(getShootFlowPanel());
		contentPane.add(getLblSpaceship());
		contentPane.add(getBtnDice());
	}
	private JPanel getGridPanel() {
		if (gridPanel == null) {
			gridPanel = new JPanel();
			gridPanel.setBackground(new Color(0, 0, 0));
			gridPanel.setBorder(new LineBorder(new Color(147, 112, 219), 3));
			gridPanel.setBounds(30, 240, 795, 95);
			gridPanel.setLayout(new GridLayout(0, 8, 4, 0));
			gridPanel.add(getBtn0());
			gridPanel.add(getBtn1());
			gridPanel.add(getBtn2());
			gridPanel.add(getBtn3());
			gridPanel.add(getBtn4());
			gridPanel.add(getBtn5());
			gridPanel.add(getBtn6());
			gridPanel.add(getBtn7());
			habilitaTablero(false);
		}
		return gridPanel;
	}
	private void habilitaTablero(boolean b) {
		for(int i=0; i < getGridPanel().getComponents().length; i++) {
			getGridPanel().getComponents()[i].setEnabled(b);
		}
		
	}
	private JButton getBtn0() {
		if (btn0 == null) {
			btn0 = new JButton("");
			btn0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(0);
				}
			});
			btn0.setBackground(new Color(0, 0, 0));
		}
		return btn0;
	}
	private void dispara(int i) {
		spi.dispara(i);
		representaSpaceInvaders(i);
		
	}
	private void representaSpaceInvaders(int i) {
		pintaPuntos();
		despintaDisparo();
		pintaCasilla(i);
		deshabilitaBoton(i);
		compruebaFin();
		
	}
	private void despintaDisparo() {
		getShootFlowPanel().remove(0);
		getShootFlowPanel().repaint(); //obliga a repintarse al panel cuando se elimina un componente
		
	}
	private void compruebaFin() {
		if(spi.isFinJuego()) {
			habilitaTablero(false);
			JOptionPane.showMessageDialog(this, "Partida finalizada", "Space Invaders EII", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	private void deshabilitaBoton(int i) {
		((JButton) getGridPanel().getComponent(i)).setEnabled(false);
		
	}
	private void pintaCasilla(int i) {
		ImageIcon imagen = new ImageIcon(VentanaPrincipal.class.getResource(spi.obtenerImagen(i)));
		((JButton) getGridPanel().getComponent(i)).setIcon(imagen);
		((JButton) getGridPanel().getComponent(i)).setDisabledIcon(imagen);
	}
	private void pintaPuntos() {
		getTextFieldScore().setText(String.valueOf(spi.getPuntuacion()));
		
	}
	private JButton getBtn1() {
		if (btn1 == null) {
			btn1 = new JButton("");
			btn1.setBackground(Color.BLACK);
			btn1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(1);
				}
			});
		}
		return btn1;
	}
	private JButton getBtn2() {
		if (btn2 == null) {
			btn2 = new JButton("");
			btn2.setBackground(Color.BLACK);
			btn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(2);
				}
			});
		}
		return btn2;
	}
	private JButton getBtn3() {
		if (btn3 == null) {
			btn3 = new JButton("");
			btn3.setBackground(Color.BLACK);
			btn3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(3);
				}
			});
		}
		return btn3;
	}
	private JButton getBtn4() {
		if (btn4 == null) {
			btn4 = new JButton("");
			btn4.setBackground(Color.BLACK);
			btn4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(4);
				}
			});
		}
		return btn4;
	}
	private JButton getBtn5() {
		if (btn5 == null) {
			btn5 = new JButton("");
			btn5.setBackground(Color.BLACK);
			btn5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(5);
				}
			});
		}
		return btn5;
	}
	private JButton getBtn6() {
		if (btn6 == null) {
			btn6 = new JButton("");
			btn6.setBackground(Color.BLACK);
			btn6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(6);
				}
			});
		}
		return btn6;
	}
	private JButton getBtn7() {
		if (btn7 == null) {
			btn7 = new JButton("");
			btn7.setBackground(Color.BLACK);
			btn7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispara(7);
				}
			});
		}
		return btn7;
	}
	private JLabel getLblEarth() {
		if (lblEarth == null) {
			lblEarth = new JLabel("");
			lblEarth.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/earth.jpg")));
			lblEarth.setBounds(660, 11, 184, 198);
		}
		return lblEarth;
	}
	private JLabel getLblScore() {
		if (lblScore == null) {
			lblScore = new JLabel("Score");
			lblScore.setFont(new Font("Tempus Sans ITC", Font.BOLD, 23));
			lblScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblScore.setForeground(new Color(255, 255, 255));
			lblScore.setBounds(501, 21, 122, 42);
		}
		return lblScore;
	}
	private JTextField getTextFieldScore() {
		if (textFieldScore == null) {
			textFieldScore = new JTextField();
			textFieldScore.setEditable(false);
			textFieldScore.setFont(new Font("Tahoma", Font.PLAIN, 23));
			textFieldScore.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldScore.setForeground(new Color(255, 255, 0));
			textFieldScore.setBorder(new LineBorder(new Color(255, 255, 255), 3));
			textFieldScore.setBackground(new Color(0, 0, 0));
			textFieldScore.setBounds(513, 78, 102, 36);
			textFieldScore.setColumns(10);
			textFieldScore.setText(String.valueOf(spi.getPuntuacion()));
		}
		return textFieldScore;
	}
	private JPanel getShootFlowPanel() {
		if (shootFlowPanel == null) {
			shootFlowPanel = new JPanel();
			shootFlowPanel.setBackground(new Color(0, 0, 0));
			shootFlowPanel.setBounds(180, 119, 310, 78);
			shootFlowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return shootFlowPanel;
	}
	private JLabel getLblSpaceship() {
		if (lblSpaceship == null) {
			lblSpaceship = new JLabel("");
			lblSpaceship.setHorizontalAlignment(SwingConstants.CENTER);
			lblSpaceship.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/spaceship.png")));
			lblSpaceship.setBounds(224, 21, 204, 87);
		}
		return lblSpaceship;
	}
	private JButton getBtnDice() {
		if (btnDice == null) {
			btnDice = new JButton("");
			btnDice.setFocusPainted(false);
			btnDice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					iniciarSpaceInvaders(); //para quitar lineas amarillas es focuspainted = false
				}					
				}
			);
			btnDice.setToolTipText("Pulsa para obtener el n\u00BA de disparos");
			btnDice.setBorder(null);
			btnDice.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dice.jpg")));
			btnDice.setDisabledIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dice.jpg")));
			btnDice.setBackground(Color.BLACK);
			btnDice.setBounds(12, 28, 114, 107);
		}
		return btnDice;
	}

	private void iniciarSpaceInvaders() {
		spi.lanzarDado();
		pintaDisparo();
		getBtnDice().setEnabled(false);
		habilitaTablero(true);
	}
	private void pintaDisparo() {
		for(int i=0; i<spi.getDisparos();i++) {
			getShootFlowPanel().add(nuevoDisparo());
		}
		validate(); //obliga a recargar la página // obliga a repintarse al panel para mostrar los disparos
	}
	private Component nuevoDisparo() {
		lblShoot = new JLabel("");
		lblShoot.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/shoot.png")));
		lblShoot.setBorder(new LineBorder(new Color(0, 255, 0), 3));
		return lblShoot;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnJuego());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnJuego() {
		if (mnJuego == null) {
			mnJuego = new JMenu("Juego");
			mnJuego.setMnemonic('J');
			mnJuego.add(getMntmNuevo());
			mnJuego.add(getSeparator());
			mnJuego.add(getMntmSalir());
		}
		return mnJuego;
	}
	private JMenuItem getMntmNuevo() {
		if (mntmNuevo == null) {
			mntmNuevo = new JMenuItem("Nuevo");
			mntmNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					spi.inicializar();
					habilitaTablero(false);
					getBtnDice().setEnabled(true);
					getShootFlowPanel().removeAll();
					getShootFlowPanel().repaint();
					pintaPuntos();
				}
			});
			mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmNuevo;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
		}
		return mntmSalir;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.add(getMntmContenidos());
			mnAyuda.add(getSeparator_1());
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de");
			mntmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Hecho por Elías Fernández Medina en la sesión 6 de CPM", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return mntmAcercaDe;
	}
	private JMenuItem getMntmContenidos() {
		if (mntmContenidos == null) {
			mntmContenidos = new JMenuItem("Contenidos");
			mntmContenidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		}
		return mntmContenidos;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
}
