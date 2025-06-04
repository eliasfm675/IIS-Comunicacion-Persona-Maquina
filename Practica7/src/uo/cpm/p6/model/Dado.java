package uo.cpm.p6.model;
import uo.cpm.p6.game.Juego;


public class Dado {
	
	public static int lanzar(int nDisparos)
	{ 
		return ((int) (Math.random() * nDisparos) + 1);
	}
}
