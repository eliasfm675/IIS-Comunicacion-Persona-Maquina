package uo.cpm.p3.ui;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConfirmacion extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblOk;
	private JLabel lblCode;
	private JTextField textCode;
	private JButton btnFinalizar_1;
	private JLabel lblTotal;
	private VentanaRegistro vr;
	public VentanaConfirmacion(VentanaRegistro vr) {
		this.vr = vr;
		setBounds(200, 200, 618, 381);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacion.class.getResource("/img/logo.png")));
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblOk());
		getContentPane().add(getLblCode());
		getContentPane().add(getTextCode());
		getContentPane().add(getBtnFinalizar_1());
		getContentPane().add(getLblTotal());
	}
	private JLabel getLblOk() {
		if (lblOk == null) {
			lblOk = new JLabel("Pulse Finalizar para confirmar su pedido");
			lblOk.setIcon(new ImageIcon(VentanaConfirmacion.class.getResource("/img/ok.png")));
			lblOk.setFont(new Font("Arial Black", Font.BOLD, 20));
			lblOk.setBounds(12, 36, 552, 68);
		}
		return lblOk;
	}
	private JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("El c\u00F3digo de recogida es: ");
			lblCode.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblCode.setBounds(58, 132, 198, 30);
		}
		return lblCode;
	}
	private JTextField getTextCode() {
		if (textCode == null) {
			textCode = new JTextField();
			textCode.setHorizontalAlignment(SwingConstants.CENTER);
			textCode.setText(vr.getVp().getMc().getOrderCode());
			textCode.setEditable(false);
			textCode.setColumns(10);
			textCode.setBounds(211, 135, 97, 28);
		}
		return textCode;
	}
	private JButton getBtnFinalizar_1() {
		if (btnFinalizar_1 == null) {
			btnFinalizar_1 = new JButton("Finalizar");
			btnFinalizar_1.setMnemonic('f');
			btnFinalizar_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					finalizar();
				}
			});
			btnFinalizar_1.setForeground(Color.WHITE);
			btnFinalizar_1.setBackground(new Color(0, 128, 0));
			btnFinalizar_1.setBounds(312, 219, 89, 23);
		}
		return btnFinalizar_1;
	}
	private void finalizar() {
		vr.getVp().getMc().saveOrder();
		JOptionPane.showMessageDialog(null, "Muchas gracias por su pedido. \nDiríjase al mostrador con el código de recogida");
		vr.getVp().initialize();
		vr.dispose();
		dispose();
		
	}
	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel(String.format("El total es de: %.2f €", vr.getVp().getMc().getOrderTotal() ));
			lblTotal.setFont(new Font("Dialog", Font.BOLD, 14));
			lblTotal.setBounds(58, 222, 191, 20);
		}
		return lblTotal;
	}
}
