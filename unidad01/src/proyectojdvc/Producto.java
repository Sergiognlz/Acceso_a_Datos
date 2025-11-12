package proyectojdvc;

public class Producto {
	
	private int idProducto;
	
	private String denominacion;
	
	private double precio;
	
	
	public Producto(int idProducto, String denominacion, double precio) {
		this.idProducto=idProducto;
		this.denominacion=denominacion;
		this.precio=precio;
	}


	public String getDenominacion() {
		return denominacion;
	}


	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getIdProducto() {
		return idProducto;
	}
	
	
	

}
