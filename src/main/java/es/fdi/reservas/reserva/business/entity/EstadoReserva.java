/**
 * This file is part of reservas Maven Webapp.
 *
 * reservas Maven Webapp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * reservas Maven Webapp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with reservas Maven Webapp.  If not, see <http://www.gnu.org/licenses/>.
 */
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
