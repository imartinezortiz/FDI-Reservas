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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import es.fdi.reservas.reserva.business.boundary.EspacioService;
import es.fdi.reservas.reserva.business.entity.Espacio;

public class EspacioConverter implements Converter<Long, Espacio>{

	private EspacioService espacio_service;
	
	@Autowired
    public EspacioConverter(EspacioService es) {
		espacio_service = es;
	} 
	
	@Override
	public Espacio convert(Long idEspacio) {	
		if(idEspacio == null)
			throw new IllegalArgumentException();
		
		return espacio_service.getEspacio(idEspacio);
	}

}
