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

import java.util.HashSet;
import java.util.Set;

import es.fdi.reservas.reserva.business.entity.Edificio;
import es.fdi.reservas.reserva.business.entity.Facultad;
import es.fdi.reservas.users.business.entity.User;

public class FacultadDTO {

	private Long id;
	private String nombreFacultad;
	private String webFacultad;
	
	private Set<Edificio> edificios;
	
	private Set<User> usuarios;
	
	private boolean deleted;
	
	public FacultadDTO(){}
	
	public FacultadDTO(Long id, String name, String web){
		super();
		this.id = id;
		this.nombreFacultad = name;
		this.webFacultad = web;
	}
	
	public FacultadDTO(String nombreFacultad, String web){
		this.nombreFacultad = nombreFacultad;
		this.webFacultad = web;
		this.deleted = false;
		this.edificios = new HashSet<Edificio>();
		this.usuarios = new HashSet<User>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getNombreFacultad() {
		return nombreFacultad;
	}

	public void setNombreFacultad(String nombreFacultad) {
		this.nombreFacultad = nombreFacultad;
	}


	public String getWebFacultad() {
		return webFacultad;
	}

	public void setWebFacultad(String webFacultad) {
		this.webFacultad = webFacultad;
	}

	public Set<Edificio> getEdificios() {
		return edificios;
	}

	public void setEdificios(Set<Edificio> edificios) {
		this.edificios = edificios;
	}

	public Set<User> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<User> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public static FacultadDTO fromFacultadDTOAutocompletar(Facultad f){
		
		return new FacultadDTO(f.getId(), f.getNombreFacultad(), f.getWebFacultad());
	}
	
	public static FacultadDTO fromFacultadDTO(Facultad f){
		
		return new FacultadDTO(f.getNombreFacultad(), f.getWebFacultad());
	}
}
