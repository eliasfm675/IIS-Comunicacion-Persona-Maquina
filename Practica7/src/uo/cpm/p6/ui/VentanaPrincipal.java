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

import uo.cpm.p6.model.Nivel;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JPanel gridPanel;
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
	private ProcesaAccionBtTablero proAT = new ProcesaAccionBtTablero();
	private JMenu mnNivel;
	private JRadioButton rdbtnFacil;
	private JRadioButton rdbtnNormal;
	private JRadioButton rdbtnDificil;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ProcesaAccionNiveles proNV = new ProcesaAccionNiveles();
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
	private class ProcesaAccionBtTablero implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			dispara(Integer.parseInt(((JButton) e.getSource()).getActionCommand()));
		}
		
	}
	private class ProcesaAccionNiveles implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			switch(((JRadioButton)e.getSource()).getActionCommand()) {
			case "N":
				spi.inicializar(Nivel.INTERMEDIO);
				break;
			case "D":
				spi.inicializar(Nivel.DIFICIL);
				break;
			case "F":
				spi.inicializar(Nivel.FACIL);
				break;
			}
			inicializarJuego();
			
		}
		
	}
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(SpaceInvaders spi) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				confirmar();
			}

			
		});
		this.spi = spi;
		setTitle("Space Invaders");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/earth.jpg")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1080, 385);
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
	private void confirmar() {
		// TODO Auto-generated method stub
		if(JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres salir de la aplicación?") == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	private JPanel getGridPanel() {
		if (gridPanel == null) {
			gridPanel = new JPanel();
			gridPanel.setBackground(new Color(0, 0, 0));
			gridPanel.setBorder(new LineBorder(new Color(147, 112, 219), 3));
			actualizarPanelBotones();
			crearBotones();
			habilitaTablero(false);
		}
		return gridPanel;
	}
	private void crearBotones() {
		for(int i=0; i< spi.getTablero().getCasillas().length; i++) {
			JButton btn = new JButton("");
			btn.setActionCommand(String.valueOf(i));
			btn.addActionListener(proAT);
			btn.setBackground(new Color(0, 0, 0));
			gridPanel.add(btn);
		}
	}
	private void habilitaTablero(boolean b) {
		for(int i=0; i < getGridPanel().getComponents().length; i++) {
			getGridPanel().getComponents()[i].setEnabled(b);
		}
		
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
			descubreTablero();
			JOptionPane.showMessageDialog(this, spi.getMotivoFin(), "Space Invaders EII", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	private void descubreTablero() {
		for(int i=0; i<getGridPanel().getComponentCount(); i++) {
			pintaCasilla(i);
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
	private JLabel getLblEarth() {
		if (lblEarth == null) {
			lblEarth = new JLabel("");
			lblEarth.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/earth.jpg")));
			lblEarth.setBounds(783, 0, 199, 210);
		}
		return lblEarth;
	}
	private JLabel getLblScore() {
		if (lblScore == null) {
			lblScore = new JLabel("Score");
			lblScore.setFont(new Font("Tempus Sans ITC", Font.BOLD, 23));
			lblScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblScore.setForeground(new Color(255, 255, 255));
			lblScore.setBounds(640, 20, 122, 42);
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
			textFieldScore.setBounds(653, 71, 102, 36);
			textFieldScore.setColumns(10);
			textFieldScore.setText(String.valueOf(spi.getPuntuacion()));
		}
		return textFieldScore;
	}
	private JPanel getShootFlowPanel() {
		if (shootFlowPanel == null) {
			shootFlowPanel = new JPanel();
			shootFlowPanel.setBackground(new Color(0, 0, 0));
			shootFlowPanel.setBounds(136, 119, 490, 80);
			shootFlowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return shootFlowPanel;
	}
	private JLabel getLblSpaceship() {
		if (lblSpaceship == null) {
			lblSpaceship = new JLabel("");
			lblSpaceship.setHorizontalAlignment(SwingConstants.CENTER);
			lblSpaceship.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/spaceship.png")));
			lblSpaceship.setBounds(242, 20, 204, 87);
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
			menuBar.add(getMnNivel());
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
					confirmarNuevaPartida();
					
				}
			});
			mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmNuevo;
	}

	private void inicializarJuego() {
		spi.inicializar(spi.getNivel());
		getBtnDice().setEnabled(true);
		eliminaDisparos();
		pintaPuntos();
		actualizarBotones();
		actualizarPanelBotones();
		despintaTablero();
		habilitaTablero(false); 
	}
	private void actualizarPanelBotones() {
		getGridPanel().setBounds(20, 208, spi.getNivel().getNumBorder(),98);
		getGridPanel().setLayout(new GridLayout(1, spi.getNivel().getNumCasillas(), 4, 0));
		
	}
	protected void actualizarBotones() {
		eliminarBotones();
		crearBotones();
	}
	private void eliminarBotones() {
		// TODO Auto-generated method stub
		getGridPanel().removeAll();
		getGridPanel().repaint();
		
	}
	private void confirmarNuevaPartida() {
		if(JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres empezar una partida nueva?")==JOptionPane.YES_OPTION) {
			inicializarJuego();
		}
		
	}
	private void despintaTablero() {
		for(int i=0; i<getGridPanel().getComponentCount(); i++) {
			despintaCasilla(i);
		}
	}
	private void eliminaDisparos() {
		getShootFlowPanel().removeAll();
		getShootFlowPanel().repaint();
	}
	private void despintaCasilla(int i) {
		((JButton)getGridPanel().getComponent(i)).setIcon(null);
		((JButton)getGridPanel().getComponent(i)).setDisabledIcon(null);
		validate();
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					confirmar();
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
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('A');
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
	private JMenu getMnNivel() {
		if (mnNivel == null) {
			mnNivel = new JMenu("Nivel");
			mnNivel.setMnemonic('n');
			mnNivel.add(getRdbtnFacil());
			mnNivel.add(getRdbtnNormal());
			mnNivel.add(getRdbtnDificil());
		}
		return mnNivel;
	}
	private JRadioButton getRdbtnFacil() {
		if (rdbtnFacil == null) {
			rdbtnFacil = new JRadioButton("F\u00E1cil");
			buttonGroup.add(rdbtnFacil);
			rdbtnFacil.setActionCommand("F");
			rdbtnFacil.addActionListener(proNV);
		}
		return rdbtnFacil;
	}
	private JRadioButton getRdbtnNormal() {
		if (rdbtnNormal == null) {
			rdbtnNormal = new JRadioButton("Normal");
			rdbtnNormal.setSelected(true);
			buttonGroup.add(rdbtnNormal);
			rdbtnNormal.setActionCommand("N");
			rdbtnNormal.addActionListener(proNV);
		}
		return rdbtnNormal;
	}
	private JRadioButton getRdbtnDificil() {
		if (rdbtnDificil == null) {
			rdbtnDificil = new JRadioButton("Dif\u00EDcil");
			buttonGroup.add(rdbtnDificil);
			rdbtnDificil.setActionCommand("D");
			rdbtnDificil.addActionListener(proNV);
			
			
		}
		return rdbtnDificil;
	}
}
