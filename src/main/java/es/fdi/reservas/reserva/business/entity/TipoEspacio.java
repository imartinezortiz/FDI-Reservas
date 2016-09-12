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

public enum TipoEspacio {

	AULA("Aula"), SALA("Sala"), LABORATORIO("Laboratorio");
	
	private String tipo;
	
	private TipoEspacio(String tipo){
		this.setTipo(tipo);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public static TipoEspacio fromTipoEspacio(String tipo){
		if(tipo.equals("Sala"))
			return TipoEspacio.SALA;
		else if(tipo.equals("Aula"))
			return TipoEspacio.AULA;
		else
		    return TipoEspacio.LABORATORIO;
	}
	
	@Override
	public String toString() {
		return tipo;
	}
}
