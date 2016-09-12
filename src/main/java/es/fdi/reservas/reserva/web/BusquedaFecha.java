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

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class BusquedaFecha {
	private Long idEdificio;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private DateTime desde;
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private DateTime hasta;
	
	public BusquedaFecha(Long id, DateTime ini, DateTime fin){
		this.idEdificio = id;
		this.desde = ini;
		this.hasta = fin;
	}

	public Long getIdEdificio() {
		return idEdificio;
	}

	public void setIdEdificio(Long idEdificio) {
		this.idEdificio = idEdificio;
	}

	public DateTime getDesde() {
		return desde;
	}

	public void setDesde(DateTime desde) {
		this.desde = desde;
	}

	public DateTime getHasta() {
		return hasta;
	}

	public void setHasta(DateTime hasta) {
		this.hasta = hasta;
	}
}
