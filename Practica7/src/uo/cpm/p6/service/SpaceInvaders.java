package uo.cpm.p6.service;

import uo.cpm.p6.game.Juego;
import uo.cpm.p6.model.Nivel;
import uo.cpm.p6.model.Tablero;

public class SpaceInvaders {

	private Juego juego = new Juego();

	public Tablero getTablero()
	{
		return juego.getTablero();
	}

	public void inicializar(Nivel nivel)
	{
		juego.inicializarJuego(nivel);
	}

	public void dispara(int i)
	{
		juego.dispara(i);
	}

	public boolean isFinJuego()
	{
		return juego.isFinJuego();
	}
//	public boolean isInvaderShot() {
//		return juego.isInvaderShot();
//	}
//	public boolean isMeteoriteShot() {
//		return juego.isMeteoriteShot();
//	}
	public int getPuntuacion()
	{
		return juego.getPuntos();
	}

	public void lanzarDado()
	{
		juego.lanzar();
	}

	public int getDisparos()
	{
		return juego.getDisparos();
	}

	public String obtenerImagen(int i) 
	{
		return juego.obtenerImagen(i);
	}
	public String getMotivoFin() {
		return juego.motivoFin();
	}
	public Nivel getNivel() {
		return juego.getNivel();
	}
	public void editNivel(Nivel n) {
		juego.editarNivel(n);
	}

}