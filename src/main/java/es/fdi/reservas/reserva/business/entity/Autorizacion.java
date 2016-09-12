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

public enum Autorizacion {

	NECESARIA("Necesaria"), PORHORAS("Por horas"), INNECESARIA("Innecesaria");
	
	private String tipoAutorizacion;
	
	private Autorizacion(String estado){
		this.setTipoAutorizacion(estado);
	}

	public String getTipoAutorizacion() {
		return tipoAutorizacion;
	}

	public void setTipoAutorizacion(String estado) {
		this.tipoAutorizacion = estado;
	}
	
	public static Autorizacion fromEstadoReserva(String tipoAutorizacion){
		if(tipoAutorizacion.equals("Necesaria"))
			return Autorizacion.NECESARIA;
		else if(tipoAutorizacion.equals("Por horas"))
			return Autorizacion.PORHORAS;
		else
		    return Autorizacion.INNECESARIA;
	}
	
	@Override
	public String toString() {
		return tipoAutorizacion;
	}
}
