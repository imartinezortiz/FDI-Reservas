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

import es.fdi.reservas.reserva.business.entity.GrupoReserva;

public class GrupoReservaDTO {

	private Long id;
	private String nombreCorto;
	private String nombreLargo;
	
	public GrupoReservaDTO(){ }
	
	public GrupoReservaDTO(Long idGrupo, String nombreCorto, String nombreLargo){ 
		this.id = idGrupo;
		this.nombreCorto = nombreCorto;
		this.nombreLargo = nombreLargo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public String getNombreLargo() {
		return nombreLargo;
	}

	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}

	public static GrupoReservaDTO fromGrupoReserva(GrupoReserva g){
		return new GrupoReservaDTO(g.getId(), g.getNombreCorto(), g.getNombreLargo());
	}
	
	
}
