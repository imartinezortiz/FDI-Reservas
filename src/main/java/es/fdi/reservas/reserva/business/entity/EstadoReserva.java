package es.fdi.reservas.reserva.business.entity;

import java.util.ArrayList;
import java.util.List;

public enum EstadoReserva {

	CONFIRMADA("Confirmada"), PENDIENTE("Pendiente"), DENEGADA("Denegada"), OTRO("Otro");
	
	private String estado;
	
	private EstadoReserva(String estado){
		if (estado.equalsIgnoreCase("Confirmada")) 
			this.setEstado("Confirmada");
		else if (estado.equalsIgnoreCase("Pendiente"))
			this.setEstado("Pendiente");
		else if (estado.equalsIgnoreCase("Denegada"))
			this.setEstado("Denegada");
		else
			this.setEstado("otro");
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public static EstadoReserva fromEstadoReserva(String estado){
		if(estado.equalsIgnoreCase("Confirmada"))
			return EstadoReserva.CONFIRMADA;
		else if(estado.equalsIgnoreCase("Pendiente"))
			return EstadoReserva.PENDIENTE;
		else if(estado.equalsIgnoreCase("Denegada"))
		    return EstadoReserva.DENEGADA;
		else
			return EstadoReserva.OTRO;
	}
	
	public static List<EstadoReserva> getAll()
	{
		List<EstadoReserva> lista= new ArrayList<EstadoReserva>();
		lista.add(CONFIRMADA);
		lista.add(PENDIENTE);
		lista.add(DENEGADA);
		return lista;
	}
	
	@Override
	public String toString() {
		return estado;
	}
}
