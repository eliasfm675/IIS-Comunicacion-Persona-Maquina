package uo.cpm.p6.game;

import uo.cpm.p6.model.*;
import uo.cpm.p6.model.Casilla.TipoCasilla;

public class Juego {
	
	private int puntos;
	private int disparos;
	private Tablero tablero; 
	private Casilla casillaDisparada;
	private boolean finJuego;
	private Nivel nivel;
	
	
	public Juego(){
		inicializarJuego(Nivel.INTERMEDIO);
	}

	public void inicializarJuego(Nivel nivel){
		tablero = new Tablero(nivel.getNumCasillas(), nivel.getNumInvasores(), nivel.getNumMeteoritos());
		puntos = 1000;
		disparos = 0;
		casillaDisparada=null;
		finJuego = false;
		this.nivel = nivel;
		}
	public Nivel getNivel() {
		return nivel;
	}
	public Tablero getTablero() {
		return tablero;
	}
	
	public Casilla getCasillaDisparada() {
		return casillaDisparada;
	}

	public void setCasillaDisparada(Casilla casillaDisparada) {
		this.casillaDisparada = casillaDisparada;
	}
	
	public boolean isFinJuego() {
		return finJuego;
	}
	
	public void dispara(int i){
		disparos --;
		setCasillaDisparada(tablero.getCasillas()[i]); // guardamos la casilla sobre la que se ha disparado		
		puntos += tablero.getCasillas()[i].getPuntos();
		finJuego = isPartidaFinalizada();
	}
	
	public boolean isPartidaFinalizada() { 
		return (getCasillaDisparada().getTipoCasilla().equals(TipoCasilla.ENEMIGO) || disparos == 0 || getCasillaDisparada().getTipoCasilla().equals(TipoCasilla.METEORITO)) ;
	}
//	public boolean isInvaderShot() {
//		return getCasillaDisparada().getTipoCasilla().equals(TipoCasilla.ENEMIGO);
//	}
	public int getPuntos() {
		return puntos;
	}

	public void lanzar() {
		setDisparos(Dado.lanzar(nivel.getNumDisparos()));	
	}
	
	public int getDisparos() {
		return disparos;
	}

	private void setDisparos(int disparos) {
		this.disparos = disparos;
	}
	private void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	public String obtenerImagen(int i) {
		return getTablero().obtenerImagen(i);
	}
	public void editarNivel(Nivel n) {
		this.nivel = n;
	}
//	public boolean isMeteoriteShot() {
//		return getCasillaDisparada().getTipoCasilla().equals(TipoCasilla.METEORITO); 
//	}
	public String motivoFin() {
		if(!getCasillaDisparada().getTipoCasilla().equals(TipoCasilla.NEUTRO)) {
			return "Partida finalizada: " + getCasillaDisparada().getNombre() + " encontrado";
		}else {
			return "Partida finalizada: disparos agotados";
		}
	}
	
}
