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

import es.fdi.reservas.reserva.business.entity.Espacio;

public class EspacioTipoDTO {
	
	private Long id;
	private String nombreEspacio;
	
	public EspacioTipoDTO(Long id, String nombre){
		this.id = id;
		this.nombreEspacio = nombre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEspacio() {
		return nombreEspacio;
	}

	public void setNombreEspacio(String nombreEspacio) {
		this.nombreEspacio = nombreEspacio;
	}
	
	public static EspacioTipoDTO fromEspacioTipoDTO(Espacio e){
		return new EspacioTipoDTO(e.getId(), e.getNombreEspacio());
	}
}
