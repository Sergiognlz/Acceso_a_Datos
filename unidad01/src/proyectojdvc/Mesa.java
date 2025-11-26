package proyectojdvc;

public class Mesa {

	private int idMesa;
	
	private int numComensales;
	
	private short reserva; 
	
	
	public Mesa(int idMesa, int numComensales, short reserva) {
		this.idMesa=idMesa;
		this.numComensales=numComensales;
		if(reserva==1||reserva==0) {
		this.reserva=reserva;
		}
	}
	
	public int getIdMesa(){
		
		return this.idMesa;
	}
	
	public int getNumComensales() {
		
		return this.numComensales;
	}
	
	public void setNumComensales(int numComensales) {
		
			this.numComensales=numComensales;
	
	
	}
	
	public short getReserva() {
		
		return this.reserva;
	}
	
}


