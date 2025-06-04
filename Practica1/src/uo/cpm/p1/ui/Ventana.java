package uo.cpm.p1.ui;

import java.awt.Color;

import javax.swing.*;


public class Ventana extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel pnPrincipal;
	private JButton btAceptar;
	private JButton btCancelar;

	public Ventana() {
		this.setTitle("Ventana Principal"); //título
		this.setBounds(100, 100, 450, 300); //dimensionar
		pnPrincipal = new JPanel();
		pnPrincipal.setBackground(Color.cyan); //cambiar color de fondo
		pnPrincipal.setLayout(null); //
		this.setContentPane(pnPrincipal); //colocar el panel que hemos creado
		
		btAceptar = new JButton(); 
		btAceptar.setText("Aceptar");
		btAceptar.setBounds(70, 220, 100, 20); //dimensionar componentes
		//btAceptar.setBackground(Color.RED);
		btAceptar.setForeground(Color.blue); //cambiar el color del texto
		
		btCancelar = new JButton();
		btCancelar.setText("Cancelar");
		btCancelar.setBounds(280, 220, 100, 20);
		btCancelar.setForeground(Color.red);
		
		pnPrincipal.add(btAceptar);//colocar un componente encima de otro (el de dentro del parentesis se pondrá encima)
		pnPrincipal.add(btCancelar);
		
	}

	public static void main(String[] args) {
		Ventana ventana = new Ventana();
		ventana.setVisible(true);

	}

}
