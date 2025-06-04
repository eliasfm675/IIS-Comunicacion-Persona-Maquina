package uo.cpm.p8.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p8.player.MusicPlayer;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JFileChooser selector;
	private JPanel panelNorte;
	private JLabel lblIcono;
	private JSlider sliderVolumen;
	private JPanel panelVolumen;
	private JTextField textFieldVolumen;
	private JLabel lblVol;
	private MusicPlayer mp;
	private JPanel panelCentro;
	private JPanel panelLib;
	private JLabel lblLibreria;
	private JPanel panelLibSur;
	private JButton btnAddtoPlayList;
	private JButton btnDelete;
	private JScrollPane scrollPaneLibrary;
	private JList<File> listLibrary;
	private JPanel panelPlayList;
	private JLabel lblPlayList;
	private JPanel panelPlayListSur;
	private JButton btnPrevSong;
	private JButton btnUnpause;
	private JButton btnStop;
	private JButton btnNextSong;
	private JButton btnPlayListDelete;
	private JScrollPane scrollPanePlayList;
	private JList<File> listPlayList;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpen;
	private JMenuItem mntmExit;
	private JSeparator separator;
	private JMenu mnPlay;
	private JMenu mnOptions;
	private JMenu mnHelp;
	private DefaultListModel<File> modeloListLibrary;
	private DefaultListModel<File> modeloListPlay;
	private ProcesaTamano pT;
	class ProcesaTamano extends ComponentAdapter{
		@Override 
		public void componentResized(ComponentEvent e) {
			mostrarSizeVentana();
		}

	}
	private void mostrarSizeVentana() {
		System.out.println(this.getSize());
		
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(MusicPlayer mp) {
		addComponentListener(pT);
		this.mp = mp;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/logoTitulo.png")));
		setTitle("MusicPlayer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 486);
		setLocationRelativeTo(null);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setMinimumSize(new Dimension(550, 250));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelNorte(), BorderLayout.NORTH);
		contentPane.add(getPanelCentro(), BorderLayout.CENTER);
	}
	private JFileChooser getSelector() {
		if(selector==null) {
			selector = new JFileChooser();
			selector.setMultiSelectionEnabled(true);
			selector.setFileFilter(new FileNameExtensionFilter("Archivos mp3", "mp3"));
			//selector.setCurrentDirectory(new File(System.getProperty("user.dir"))); // fija el directorio de ejecución del programa
			selector.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop")); 
		}
		return selector;
	}
	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.setBackground(new Color(0, 0, 0));
			panelNorte.setLayout(new GridLayout(1, 3, 0, 0));
			panelNorte.add(getLblIcono());
			panelNorte.add(getSliderVolumen());
			panelNorte.add(getPanelVolumen());
		}
		return panelNorte;
	}
	private JLabel getLblIcono() {
		if (lblIcono == null) {
			lblIcono = new JLabel("");
			lblIcono.setFont(new Font("Dialog", Font.BOLD, 12));
			lblIcono.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/logo.png")));
		}
		return lblIcono;
	}
	private JSlider getSliderVolumen() {
		if (sliderVolumen == null) {
			sliderVolumen = new JSlider();
			sliderVolumen.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					editarVolumen();
				}

				
			});
			sliderVolumen.setMajorTickSpacing(20);
			sliderVolumen.setFont(new Font("Dialog", Font.BOLD, 14));
			sliderVolumen.setForeground(new Color(255, 255, 255));
			sliderVolumen.setBackground(new Color(0, 0, 0));
			sliderVolumen.setMinorTickSpacing(5);
			sliderVolumen.setPaintLabels(true);
			sliderVolumen.setPaintTicks(true);
			sliderVolumen.setMaximum((int) mp.VOLUME_MAX);
			sliderVolumen.setMinimum((int) mp.VOLUME_MIN);
			sliderVolumen.setValue(mp.getVolume());
		}
		return sliderVolumen;
	}
	private void editarVolumen() {
		getTextFieldVolumen().setText(String.valueOf(getSliderVolumen().getValue()));
		mp.setVolume(getSliderVolumen().getValue());
	}
	private JPanel getPanelVolumen() {
		if (panelVolumen == null) {
			panelVolumen = new JPanel();
			panelVolumen.setBackground(new Color(0, 0, 0));
			FlowLayout flowLayout = (FlowLayout) panelVolumen.getLayout();
			panelVolumen.add(getLblVol_1());
			panelVolumen.add(getTextFieldVolumen());
		}
		return panelVolumen;
	}
	private JTextField getTextFieldVolumen() {
		if (textFieldVolumen == null) {
			textFieldVolumen = new JTextField();
			textFieldVolumen.setBackground(new Color(0, 0, 0));
			textFieldVolumen.setForeground(new Color(255, 255, 255));
			textFieldVolumen.setFont(new Font("Dialog", Font.PLAIN, 30));
			textFieldVolumen.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldVolumen.setEditable(false);
			textFieldVolumen.setColumns(3);
			textFieldVolumen.setText(String.valueOf(mp.getVolume()));
		}
		return textFieldVolumen;
	}
	private JLabel getLblVol_1() {
		if (lblVol == null) {
			lblVol = new JLabel("Vol");
			lblVol.setForeground(new Color(255, 140, 0));
			lblVol.setHorizontalAlignment(SwingConstants.CENTER);
			lblVol.setFont(new Font("Dialog", Font.BOLD, 28));
			
		}
		return lblVol;
	}
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
			panelCentro.add(getPanelLib());
			panelCentro.add(getPanelPlayList());
		}
		return panelCentro;
	}
	private JPanel getPanelLib() {
		if (panelLib == null) {
			panelLib = new JPanel();
			panelLib.setBackground(new Color(0, 0, 0));
			panelLib.setLayout(new BorderLayout(0, 0));
			panelLib.add(getLblLibreria(), BorderLayout.NORTH);
			panelLib.add(getPanelLibSur(), BorderLayout.SOUTH);
			panelLib.add(getScrollPaneLibrary(), BorderLayout.CENTER);
		}
		return panelLib;
	}
	private JLabel getLblLibreria() {
		if (lblLibreria == null) {
			lblLibreria = new JLabel("\u266A Library:");
			lblLibreria.setFont(new Font("Dialog", Font.BOLD, 12));
			lblLibreria.setBackground(new Color(0, 0, 0));
			lblLibreria.setForeground(new Color(255, 140, 0));
		}
		return lblLibreria;
	}
	private JPanel getPanelLibSur() {
		if (panelLibSur == null) {
			panelLibSur = new JPanel();
			panelLibSur.setBackground(new Color(0, 0, 0));
			panelLibSur.setLayout(new GridLayout(0, 2, 0, 0));
			panelLibSur.add(getBtnAddtoPlayList());
			panelLibSur.add(getBtnDelete());
		}
		return panelLibSur;
	}
	private JButton getBtnAddtoPlayList() {
		if (btnAddtoPlayList == null) {
			btnAddtoPlayList = new JButton("Add to Playlist");
			btnAddtoPlayList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirPlayList();
				}

			});
			btnAddtoPlayList.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return btnAddtoPlayList;
	}

	private void añadirPlayList() {
		mp.addListPLay(getListLibrary().getSelectedValuesList());
		//interfaz
		modeloListPlay.removeAllElements();
		modeloListPlay.addAll(mp.getListPlay());
		
		getListPlayList().setSelectedValue(mp.getListPlay(), true);
		
	}
	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//mp.borrarCancionLibrary(getListLibrary().getSelectedValue());
					mp.borrarCancionLibraryVarias(getListLibrary().getSelectedValuesList());
					modeloListLibrary.removeAllElements();
					modeloListLibrary.addAll(mp.getListLibrary());
					modeloListPlay.removeAllElements();
					modeloListPlay.addAll(mp.getListPlay());
				}
			});
			btnDelete.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return btnDelete;
	}
	private JScrollPane getScrollPaneLibrary() {
		if (scrollPaneLibrary == null) {
			scrollPaneLibrary = new JScrollPane();
			scrollPaneLibrary.setBorder(new LineBorder(new Color(255, 140, 0), 3));
			scrollPaneLibrary.setViewportView(getListLibrary());
		}
		return scrollPaneLibrary;
	}
	private JList<File> getListLibrary() {
		if (listLibrary == null) {
			modeloListLibrary = new DefaultListModel<File>();
			listLibrary = new JList<File>(modeloListLibrary);
			listLibrary.setFont(new Font("Tahoma", Font.BOLD, 14));
			listLibrary.setForeground(new Color(255, 255, 255));
			listLibrary.setBackground(new Color(0, 0, 0));
		}
		return listLibrary;
	}
	private JPanel getPanelPlayList() {
		if (panelPlayList == null) {
			panelPlayList = new JPanel();
			panelPlayList.setBackground(new Color(0, 0, 0));
			panelPlayList.setLayout(new BorderLayout(0, 0));
			panelPlayList.add(getLblPlayList(), BorderLayout.NORTH);
			panelPlayList.add(getPanelPlayListSur(), BorderLayout.SOUTH);
			panelPlayList.add(getScrollPanePlayList(), BorderLayout.CENTER);
		}
		return panelPlayList;
	}
	private JLabel getLblPlayList() {
		if (lblPlayList == null) {
			lblPlayList = new JLabel("\u266B PlayList");
			lblPlayList.setForeground(new Color(255, 140, 0));
		}
		return lblPlayList;
	}
	private JPanel getPanelPlayListSur() {
		if (panelPlayListSur == null) {
			panelPlayListSur = new JPanel();
			panelPlayListSur.setLayout(new GridLayout(0, 5, 0, 0));
			panelPlayListSur.add(getBtnPrevSong());
			panelPlayListSur.add(getBtnUnpause());
			panelPlayListSur.add(getBtnStop());
			panelPlayListSur.add(getBtnNextSong());
			panelPlayListSur.add(getBtnPlayListDelete());
		}
		return panelPlayListSur;
	}
	private JButton getBtnPrevSong() {
		if (btnPrevSong == null) {
			btnPrevSong = new JButton("\u25C4\u25C4");
			btnPrevSong.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mp.stop();
					mp.previaCancion(mp.getCancionActual());
					getListPlayList().setSelectedValue(mp.getCancionActual(), true);
					mp.play();
				}
			});
			btnPrevSong.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return btnPrevSong;
	}
	private JButton getBtnUnpause() {
		if (btnUnpause == null) {
			btnUnpause = new JButton("\u25BA");
			btnUnpause.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mp.play();
				}
			});
			btnUnpause.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return btnUnpause;
	}
	private JButton getBtnStop() {
		if (btnStop == null) {
			btnStop = new JButton("\u25A0");
			btnStop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mp.stop();
				}
			});
			btnStop.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return btnStop;
	}
	private JButton getBtnNextSong() {
		if (btnNextSong == null) {
			btnNextSong = new JButton("\u25BA\u25BA");
			btnNextSong.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mp.stop();
					mp.siguienteCancion(mp.getCancionActual());
					getListPlayList().setSelectedValue(mp.getCancionActual(), true);
					mp.play();
				}
			});
			btnNextSong.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return btnNextSong;
	}
	private JButton getBtnPlayListDelete() {
		if (btnPlayListDelete == null) {
			btnPlayListDelete = new JButton("Del");
			btnPlayListDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//mp.borrarCancionPlayList(getListPlayList().getSelectedValue());
					mp.borrarCancionPlayListVarias( getListPlayList().getSelectedValuesList());
					modeloListPlay.removeAllElements();
					modeloListPlay.addAll(mp.getListPlay());
				}
			});
			btnPlayListDelete.setFont(new Font("Arial", Font.BOLD, 16));
		}
		return btnPlayListDelete;
	}
	private JScrollPane getScrollPanePlayList() {
		if (scrollPanePlayList == null) {
			scrollPanePlayList = new JScrollPane();
			scrollPanePlayList.setViewportView(getListPlayList());
		}
		return scrollPanePlayList;
	}
	private JList<File> getListPlayList() {
		if (listPlayList == null) {
			modeloListPlay = new DefaultListModel<File>();
			listPlayList = new JList<File>(modeloListPlay);
			listPlayList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mp.setCancionActual(getListPlayList().getSelectedValue());
				}
			});
			listPlayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPlayList.setForeground(new Color(255, 255, 255));
			listPlayList.setFont(new Font("Tahoma", Font.BOLD, 14));
			listPlayList.setBorder(new LineBorder(new Color(255, 140, 0), 3));
			listPlayList.setBackground(new Color(0, 0, 0));
		}
		return listPlayList;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnPlay());
			menuBar.add(getMnOptions());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setMnemonic('F');
			mnFile.add(getMntmOpen());
			mnFile.add(getSeparator());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}
	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open");
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarFicheros();
				}
			});
			mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmOpen;
	}
	private void cargarFicheros() {
		if(getSelector().showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
			//lógica
			mp.addListLibrary(selector.getSelectedFiles());
			//interfaz
			modeloListLibrary.removeAllElements();
			modeloListLibrary.addAll(mp.getListLibrary());
			
		}
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
		}
		return mntmExit;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
			mnPlay.setMnemonic('P');
		}
		return mnPlay;
	}
	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
			mnOptions.setMnemonic('O');
		}
		return mnOptions;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('H');
		}
		return mnHelp;
	}
}
