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

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import es.fdi.reservas.users.business.entity.User;

@Entity
public class Facultad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="FacultadId")
	private Long id;
	@NotNull
	private String nombreFacultad;
	@NotNull
	private String webFacultad;
	
	@OneToMany(mappedBy="facultad")
	private Set<Edificio> edificios;
	
	@OneToMany(mappedBy="facultad")
	private Set<User> usuarios;
	
	@NotNull
	private boolean deleted;
	
	public Facultad(){
		
	}
	
	
	public Facultad(String nombreFacultad, String web) {
		
		this.nombreFacultad = nombreFacultad;
		this.webFacultad = web;
		this.deleted = false;
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
	
	
}
