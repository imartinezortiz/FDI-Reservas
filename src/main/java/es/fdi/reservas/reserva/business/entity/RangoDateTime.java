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

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class RangoDateTime implements Comparable<RangoDateTime>{

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private DateTime comienzo,fin;
	
	public RangoDateTime(DateTime comienzo, DateTime fin){
		this.comienzo = comienzo;
		this.fin = fin;
	}
	
	@Override
	public int compareTo(RangoDateTime rango) {
		return this.compareTo(rango);
	}

	public DateTime getComienzo() {
		return comienzo;
	}

	public void setComienzo(DateTime comienzo) {
		this.comienzo = comienzo;
	}

	public DateTime getFin() {
		return fin;
	}

	public void setFin(DateTime fin) {
		this.fin = fin;
	}

	

}
