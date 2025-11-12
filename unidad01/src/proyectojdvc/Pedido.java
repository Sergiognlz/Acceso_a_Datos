package proyectojdvc;

public class Pedido {

	private int idPedido;
	
	private int idFactura;
	
	private int idProducto;
	
	private int cantidad;
	
	
	public Pedido(int idPedido, int idFactura, int idProducto, int cantidad) {
		this.idPedido=idPedido;
		this.idFactura=idFactura;
		this.idProducto=idProducto;
		this.cantidad=cantidad;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public int getIdPedido() {
		return idPedido;
	}


	public int getIdFactura() {
		return idFactura;
	}


	public int getIdProducto() {
		return idProducto;
	}
	
	
	
	
	
	
}
