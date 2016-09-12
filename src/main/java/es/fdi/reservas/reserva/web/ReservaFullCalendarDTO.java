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
package es.fdi.reservas.reserva.web;

import java.util.List;

import org.joda.time.DateTime;
import es.fdi.reservas.reserva.business.entity.Reserva;

public class ReservaFullCalendarDTO {

	private Long id;
	private String title;
	private DateTime start;
	private DateTime end;
	private String nombreEspacio;
	private Long idEspacio;
	private String estadoReserva;
	private String color;
	private List<String> reglasRecurrencia;
	private String recurrenteId;
	

	public ReservaFullCalendarDTO(){
		
	}
	

	public ReservaFullCalendarDTO(Long id, String title, DateTime start, DateTime end, String nombreEspacio,
			                      Long idEspacio, String color, List<String> reglas, String recurId) {
		this.id = id;
		this.title = title;
		this.start = start;
		this.end = end;
		this.nombreEspacio = nombreEspacio;
		this.idEspacio = idEspacio;
		this.color = color;
		this.reglasRecurrencia = reglas;
		this.recurrenteId = recurId;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public DateTime getStart() {
		return this.start;
	}

	public DateTime getEnd() {
		return this.end;
	}


	public String getNombreEspacio() {
		return nombreEspacio;
	}


	public void setNombreEspacio(String nombreEspacio) {
		this.nombreEspacio = nombreEspacio;
	}


	public void setStart(DateTime start) {
		this.start = start;
	}


	public void setEnd(DateTime end) {
		this.end = end;
	}

  
	public Long getIdEspacio() {
		return idEspacio;
	}


	public void setIdEspacio(Long idEspacio) {
		this.idEspacio = idEspacio;
	}

	public String getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(String estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
    
	public List<String> getReglasRecurrencia() {
		return reglasRecurrencia;
	}


	public void setReglasRecurrencia(List<String> reglasRecurrencia) {
		this.reglasRecurrencia = reglasRecurrencia;
	}
	

	public String getRecurrenteId() {
		return recurrenteId;
	}


	public void setRecurrenteId(String recurrenteId) {
		this.recurrenteId = recurrenteId;
	}
	
	public static ReservaFullCalendarDTO fromReserva(Reserva reserva) {
		return new ReservaFullCalendarDTO(reserva.getId(), reserva.getAsunto(), 
				                          reserva.getComienzo(), reserva.getFin(),
				                          reserva.getEspacio().getNombreEspacio(),
				                          reserva.getEspacio().getId(), reserva.getReservaColor(),
				                          reserva.getReglasRecurrencia(), reserva.getRecurrenteId());
	}


}
