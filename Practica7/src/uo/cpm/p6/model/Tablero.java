package uo.cpm.p6.model;

import uo.cpm.p6.model.Casilla.TipoCasilla;

public class Tablero {
	
	public static final int DIM = 8;
	Casilla[] casillas;
	
//	public Tablero() {
//		casillas = new Casilla[DIM];
//		for (int i=0;i<DIM;i++)
//			casillas[i] = new Casilla ("Espacio",TipoCasilla.NEUTRO,-200,"/img/space.jpg");
//		colocaInvasor();
//		colocaMeteorito();
//		}
	public Tablero(int nCasillas, int nInvasores, int nMeteoritos) {
		casillas = new Casilla[nCasillas];
		for (int i=0;i<nCasillas;i++)
			casillas[i] = new Casilla ("Espacio",TipoCasilla.NEUTRO,-200,"/img/space.jpg");
		for(int i=0; i<nInvasores; i++) {
			colocaInvasor(nCasillas);
		}
		for(int i=0; i<nMeteoritos; i++) {
			colocaMeteorito(nCasillas);
		}
	
	}

	private void colocaInvasor(int nCasillas) {
		int posicionInvasor = (int) (Math.random() * nCasillas);
		while(casillas[posicionInvasor].getTipoCasilla().equals(TipoCasilla.ENEMIGO)) {
			posicionInvasor = (int) (Math.random() * nCasillas);
		}
		casillas[posicionInvasor] = new  Casilla ("Invasor",TipoCasilla.ENEMIGO,3000,"/img/invader.jpg");	
		System.out.println("Posición invasor = "+posicionInvasor);
	}
	
	private void colocaMeteorito(int nCasillas) {
		int posicionMeteorito = (int) (Math.random() * nCasillas);
		while(casillas[posicionMeteorito].getTipoCasilla().equals(TipoCasilla.ENEMIGO) || casillas[posicionMeteorito].getTipoCasilla().equals(TipoCasilla.METEORITO)) {
			posicionMeteorito = (int) (Math.random() * nCasillas);
		}
		casillas[posicionMeteorito] = new  Casilla ("Meteorito",TipoCasilla.METEORITO,0,"/img/meteorite.jpg");	
		System.out.println("Posición meteorito = "+posicionMeteorito);
	}
	public Casilla[] getCasillas() {
		return casillas;
	}
	
	public String obtenerImagen(int i) {
		return casillas[i].getImagen();
	}
}
