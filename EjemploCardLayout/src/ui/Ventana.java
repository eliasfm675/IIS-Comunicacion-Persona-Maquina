package ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JPanel pn1;
	private JButton btnSig1;
	private JPanel pn2;
	private JButton btnAnt2;
	private JButton btnSig2;
	private JPanel pn3;
	private JButton btnFin;
	private JButton btnAnt3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
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
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(getPn1(), "pn1"); //aquí sale un nombre aleatorio y es aconsejable cambiarlo
		contentPane.add(getPn2(), "pn2");
		contentPane.add(getPn3(), "pn3");
	}

	private JPanel getPn1() {
		if (pn1 == null) {
			pn1 = new JPanel();
			pn1.setBackground(new Color(0, 51, 255));
			pn1.setLayout(null);
			pn1.add(getBtnSig1());
		}
		return pn1;
	}
	private JButton getBtnSig1() {
		if (btnSig1 == null) {
			btnSig1 = new JButton("Siguiente");
			btnSig1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPn2();
				}

			});
			btnSig1.setBounds(292, 205, 122, 35);
		}
		return btnSig1;
	}
	private void mostrarPn2() {
		((CardLayout)(getContentPane().getLayout())).show(getContentPane(), "pn2");
		//((CardLayout)(getContentPane().getLayout())).next(getContentPane());
		
	}
	private JPanel getPn2() {
		if (pn2 == null) {
			pn2 = new JPanel();
			pn2.setBackground(new Color(255, 255, 0));
			pn2.setLayout(null);
			pn2.add(getBtnAnt2());
			pn2.add(getBtnSig2());
		}
		return pn2;
	}
	private JButton getBtnAnt2() {
		if (btnAnt2 == null) {
			btnAnt2 = new JButton("Anterior");
			btnAnt2.setBounds(127, 199, 129, 41);
		}
		return btnAnt2;
	}
	private JButton getBtnSig2() {
		if (btnSig2 == null) {
			btnSig2 = new JButton("Siguiente");
			btnSig2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPn3();
					
				}

			
			});
			btnSig2.setBounds(285, 199, 129, 41);
		}
		return btnSig2;
	}
	private void mostrarPn3() {
		((CardLayout)(getContentPane().getLayout())).next(getContentPane());
		
	}
	private JPanel getPn3() {
		if (pn3 == null) {
			pn3 = new JPanel();
			pn3.setBackground(new Color(0, 153, 0));
			pn3.setLayout(null);
			pn3.add(getBtnFin());
			pn3.add(getBtnAnt3());
		}
		return pn3;
	}
	private JButton getBtnFin() {
		if (btnFin == null) {
			btnFin = new JButton("Finalizar");
			btnFin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarPn1();
				}

				
			});
			btnFin.setBounds(278, 197, 136, 43);
		}
		return btnFin;
	}
	private void mostrarPn1() {
		((CardLayout)getContentPane().getLayout()).show(getContentPane(), "pn1");
		
	}
	private JButton getBtnAnt3() {
		if (btnAnt3 == null) {
			btnAnt3 = new JButton("Anterior");
			btnAnt3.setBounds(126, 197, 136, 43);
		}
		return btnAnt3;
	}
}
