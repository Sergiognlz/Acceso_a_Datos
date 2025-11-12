package proyectojdvc;

public class Factura {
	
	private int idFactura;
	
	private int idMesa;
	
	private String tipoPago;
	
	private double importe;

	
	public Factura(int idFactura, int idMesa, String tipoPago, double importe) {
		this.idFactura=idFactura;
		this.idMesa=idMesa;
		this.tipoPago=tipoPago;
		this.importe=importe;
	}


	public String getTipoPago() {
		return tipoPago;
	}


	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}


	public double getImporte() {
		return importe;
	}


	public void setImporte(double importe) {
		this.importe = importe;
	}


	public int getIdFactura() {
		return idFactura;
	}


	public int getIdMesa() {
		return idMesa;
	}
	

}
