package uo.cpm.examen.service;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.examen.model.Articulo;
import uo.cpm.examen.util.FileUtil;

public class Ruleta {
	public static final String FILE = "files/premios.dat";
	private List<Articulo> listaArticulos = new ArrayList<>();
	private List<Articulo> listaSeleccionados = new ArrayList<>();
	private float points;
	public Ruleta() {
		FileUtil.loadFile(FILE, listaArticulos);
		this.points = 0;
	}
	public int lanzar() { 
		return ((int) (Math.random()*5)+1)*100;

	 }
	public Articulo getArticulo(Articulo p) {
		for(Articulo a: listaArticulos) {
			if(p.getCodigo().equals(a.getCodigo())) {
				return a;
			}
		}
		return null;
	}
	public Articulo[] getArticulos(){
		Articulo[] articulos = listaArticulos.toArray(new Articulo[listaArticulos.size()]);
		return articulos;	
	}
	public float getPoints() {
		return points;
	}
	public void editPoints(float points) {
		this.points = points;
	}
	public boolean purchase(Articulo a) {
		if(getArticulo(a).getPrecio()<=getPoints()) {
			editPoints(getPoints()-a.getPrecio());
			if(getPoints()<0) {
				editPoints(0);
			}
			return true;
		}else {
			return false;
		}
	}
	public Articulo[] getArticulosSeleccionados(){
		Articulo[] articulos = listaSeleccionados.toArray(new Articulo[listaSeleccionados.size()]);
		return articulos;	
	}
	public void add(Articulo a) {
		listaSeleccionados.add(a);
		editPoints(0);
		
	}
	public String toStringGetArticulosSeleccionados() {
		StringBuilder sb = new StringBuilder();
		for(Articulo a: getArticulosSeleccionados()) {
			sb.append(a.toString() +"\n");
		}
		return sb.toString();
	}
	public void restart() {
		listaSeleccionados.removeAll(listaSeleccionados);
	}
	public Articulo[] getArticulosElectronicos(){
		List<Articulo> l = new ArrayList<>();
		for(Articulo a: listaArticulos) {
			if(a.getTipo().equals("Electronica")) {
				l.add(a);
			}
		}
		Articulo[] articulos = l.toArray(new Articulo[l.size()]);
		return articulos;	
	}
	public Articulo[] getArticulosOcio(){
		List<Articulo> l = new ArrayList<>();
		for(Articulo a: listaArticulos) {
			if(a.getTipo().equals("Ocio")) {
				l.add(a);
			}
		}
		Articulo[] articulos = l.toArray(new Articulo[l.size()]);
		return articulos;	
	}
	public Articulo[] getArticulosInfantil(){
		List<Articulo> l = new ArrayList<>();
		for(Articulo a: listaArticulos) {
			if(a.getTipo().equals("Infantil")) {
				l.add(a);
			}
		}
		Articulo[] articulos = l.toArray(new Articulo[l.size()]);
		return articulos;	
	}
}
