package uo.cpm.p11.model;

import java.util.*;

import uo.cpm.p11.util.FileUtil;

public class Pedido {

	private List<Articulo> listaPedido;
	private String codigo;
	private float importe;
	private Cliente cliente;
	private boolean local;

	public Pedido() {
		listaPedido = new ArrayList<Articulo>();
		inicializar();
	}

	public void inicializar() {
		listaPedido.clear();
		generarCodigo();
		setImporte(0);
		setCliente(null);
		setLocal(true);
	}
	
	

	public void add(Articulo articuloDelCatalogo, int unidades) {
		Articulo articuloEnPedido = null;
		for (Articulo a : listaPedido) {
			if (a.getCodigo().equals(articuloDelCatalogo.getCodigo())) {
				articuloEnPedido = a;
				articuloEnPedido.setUnidades(articuloEnPedido.getUnidades() + unidades);
				break;
			}
		}

		if (articuloEnPedido == null) {
			articuloEnPedido = new Articulo(articuloDelCatalogo);
			articuloEnPedido.setUnidades(unidades);
			listaPedido.add(articuloEnPedido);
		}
		calcularImporte();
	}

	public void remove(Articulo articulo, int unidades) {
		Articulo articuloEnPedido = null;
		for (Articulo a : listaPedido)
			if (a.getCodigo().equals(articulo.getCodigo()))
				articuloEnPedido = a;

		if (articuloEnPedido != null) {
			int totalUnidades = articuloEnPedido.getUnidades() - unidades;
			if (totalUnidades <= 0)
				listaPedido.remove(articuloEnPedido);
			else
				articuloEnPedido.setUnidades(totalUnidades);

			calcularImporte();
		}
	}

	private void calcularImporte() {
		importe = 0;
		for (Articulo a : listaPedido)
			importe += a.getPrecio() * a.getUnidades();
		if (!isLocal())
			importe += 0.15F;
	}

	public void grabarPedido() {
		FileUtil.saveToFile(this);
	}

	public List<Articulo> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Articulo> listaPedido) {
		this.listaPedido = listaPedido;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isLocal() {
		return local;
	}

	public void setLocal(boolean tipo) {
		local = tipo;
	}

	public void generarCodigo() {
		codigo = "";
		String base = "0123456789abcdefghijklmnopqrstuvwxyz";
		int longitudCodigo = 8;
		for (int i = 0; i < longitudCodigo; i++) {
			int numero = (int) (Math.random() * (base.length()));
			codigo += base.charAt(numero);
		}
	}

	public int buscarUnidades(Articulo articuloSeleccionado) {
		for (Articulo a : listaPedido) {
			if (a.getCodigo().equals(articuloSeleccionado.getCodigo()))
				return a.getUnidades();
		}
		return 0;
	}

	public void actualizarTipoPedido(boolean llevar) {
		setLocal(llevar);
		calcularImporte();
	}
	
	public boolean sePuedenEliminar(Articulo a, int unidades) {
		return buscarUnidades(a)>=unidades;
	}
	
	public boolean noHayUnidadesArticulo(Articulo a) {
		return buscarUnidades(a)==0; 
	}

	public boolean noHayPedido() {
		return getImporte()==0;
	}
	public String toString() {
		String strPedido = "";

		if (cliente == null) {
			if (getImporte() != 0) 
				strPedido = generarDetallePedido(strPedido);
			}
		else {
	         
			String strCabecera = ("Cliente: " + this.getCliente().getNombreYApellidos()) + ("\n");
			strCabecera +="----------------------------------" + ("\n");			
			strPedido =generarDetallePedido(strCabecera);
			strPedido += ("Pedido para ") + ((isLocal() ? "consumir en local" : "llevar"));
		}

		return strPedido;
	}

	private String generarDetallePedido(String strPedido) {

		for (Articulo a : listaPedido) {
			strPedido += a.getDenominacion() + " - " + a.getUnidades() + (" uds.") + ("\n");
		}
		strPedido +=  ("Total: " + (String.format("%.2f", getImporte()))) + ("\n");
		return strPedido;
	}
	
	
}
