package uo.cpm.p11.service;

import uo.cpm.p11.model.*;

public class McDonalds {
	Carta carta = new Carta();
	Pedido pedido = new Pedido(); 
	
	public McDonalds() {
		//inicializarPedido();
	}

	public Articulo[] getArticulosCarta() {
			return carta.getArticulos();
	}
	
	public int getNumArticulos() {
		return carta.getArticulos().length;
	}
	
	public void inicializarPedido() {
		pedido.inicializar();
	}
	
	
	public String getCodigoPedido() {
		return pedido.getCodigo();
	}
	
	
	public void añadirAPedido(Articulo articulo, int unidades) {
		pedido.add(articulo, unidades);
	
	}
	
	public void eliminarDePedido(Articulo articulo, int unidades) {
		pedido.remove(articulo, unidades);
	}

	public float getImportePedido() {
		return pedido.getImporte();
	}
	
	public void grabarPedido() {
		pedido.grabarPedido();
	}
	
	
	public int buscarUnidades(Articulo a) {
		return pedido.buscarUnidades(a);
	}
	
	
	public boolean sePuedenEliminar( Articulo a,int unidades){
		return pedido.sePuedenEliminar(a,unidades);
	}
	
		
	public int cuantasUnidades(Articulo articuloSeleccionado) {
		return pedido.buscarUnidades(articuloSeleccionado);
	}
	
	public void guardarCliente(String nombre, int anio, String password) {
		pedido.setCliente(new Cliente(nombre, anio, password));
	}
	
	public void actualizarTipoPedido (boolean llevar) {
		pedido.actualizarTipoPedido(llevar);	
	}
		
	public String getTextoPedido() {
		return pedido.toString();
	}
	
	public boolean noHayUnidadesArticulo(Articulo a) {
		return pedido.noHayUnidadesArticulo(a);
	}
	
	public boolean noHayPedido() {
		return pedido.noHayPedido();
	}
	
	public Articulo getArticulo(int i) {
	   return carta.getListaArticulos().get(i);
   }

	public String getCodigoArticulo(int i) {
		return carta.getArticulos()[i].getCodigo();
	}

	public String getToStringArticulo(int i) {
		
		return carta.getArticulos()[i].toString();
	}
	
}
