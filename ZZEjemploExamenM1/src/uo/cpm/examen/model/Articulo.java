package uo.cpm.examen.model;

public class Articulo {
	
	private String Codigo;
	private String tipo;
	private String nombre;
	private float precio;
	public Articulo(String codigo, String tipo, String nombre, float precio) {
		super();
		Codigo = codigo;
		this.tipo = tipo;
		this.nombre = nombre;
		this.precio = precio;
	}
	public String getCodigo() {
		return Codigo;
	}
	private void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	private void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	private void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public String toString() {
		return String.format("%s - %s - %.2f", getTipo(), getNombre(), getPrecio());
	}
}
