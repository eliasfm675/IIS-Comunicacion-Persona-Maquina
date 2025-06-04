package uo.cpm.modulo.model;

import uo.cpm.modulo.model.Casilla.Tipo;

public class Tablero {
	private Casilla[] tablero;
	public static final int  SIZE = 16;
	public Tablero() {
		tablero = new Casilla[SIZE];
		for(int i=0; i<2; i++) {
			colocarCorazones();
			colocarPicas();
			colocarTreboles();
			colocarDiamantes();
			colocarOros();
			colocarCopas();
			colocarBastos();
			colocarEspadas();
		}
		for(int i=0; i<SIZE; i++) {
			if((i+1)%4==0) {
				System.out.print(tablero[i]+"\n");
			}else {
				System.out.print(tablero[i]+"  ");
			}
		}
		
	}
	private void colocarCorazones() {
		Casilla casillaElegida = new Casilla("/img/corazon.png", Tipo.CORAZON);
		int pos = (int) (Math.random()*SIZE);
		while(tablero[pos]!=null) {
			pos = (int) (Math.random()*SIZE);
		}
		tablero[pos]=casillaElegida;
		
	}
	private void colocarPicas() {
		Casilla casillaElegida = new Casilla("/img/picas.png", Tipo.PICAS);
		int pos = (int) (Math.random()*SIZE);
		while(tablero[pos]!=null) {
			pos = (int) (Math.random()*SIZE);
		}
		tablero[pos]=casillaElegida;
		
	}
	private void colocarTreboles() {
		Casilla casillaElegida = new Casilla("/img/trebol.png", Tipo.TREBOL);
		int pos = (int) (Math.random()*SIZE);
		while(tablero[pos]!=null) {
			pos = (int) (Math.random()*SIZE);
		}
		tablero[pos]=casillaElegida;
		
	}
	private void colocarDiamantes() {
		Casilla casillaElegida = new Casilla("/img/diamante.png", Tipo.DIAMANTE);
		int pos = (int) (Math.random()*SIZE);
		while(tablero[pos]!=null) {
			pos = (int) (Math.random()*SIZE);
		}
		tablero[pos]=casillaElegida;
		
	}
	private void colocarOros() {
		Casilla casillaElegida = new Casilla("/img/oros.png", Tipo.OROS);
		int pos = (int) (Math.random()*SIZE);
		while(tablero[pos]!=null) {
			pos = (int) (Math.random()*SIZE);
		}
		tablero[pos]=casillaElegida;
		
	}
	private void colocarCopas() {
		Casilla casillaElegida = new Casilla("/img/copas.png", Tipo.COPAS);
		int pos = (int) (Math.random()*SIZE);
		while(tablero[pos]!=null) {
			pos = (int) (Math.random()*SIZE);
		}
		tablero[pos]=casillaElegida;
		
	}
	private void colocarEspadas() {
		Casilla casillaElegida = new Casilla("/img/espadas.png", Tipo.ESPADAS);
		int pos = (int) (Math.random()*SIZE);
		while(tablero[pos]!=null) {
			pos = (int) (Math.random()*SIZE);
		}
		tablero[pos]=casillaElegida;
		
	}
	private void colocarBastos() {
		Casilla casillaElegida = new Casilla("/img/bastos.png", Tipo.BASTOS);
		int pos = (int) (Math.random()*SIZE);
		while(tablero[pos]!=null) {
			pos = (int) (Math.random()*SIZE);
		}
		tablero[pos]=casillaElegida;
		
	}
	public String getImage(int i) {
		return tablero[i].getImage();
	}
	public Casilla[] getTablero() {
		return tablero;
	}
}
