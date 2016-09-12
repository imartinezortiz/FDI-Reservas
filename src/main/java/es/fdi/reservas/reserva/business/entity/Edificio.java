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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import es.fdi.reservas.fileupload.business.entity.Attachment;

@Entity
public class Edificio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="EdificioId")
	private Long id;
	
	@NotNull
	private String nombreEdificio;
	
	@NotNull
	private String direccion;
	
	@OneToMany(mappedBy="edificio")
	private Set<Espacio> espacios;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="FacultadId")
	private Facultad facultad;

	@NotNull
	private boolean deleted;
	
	@OneToOne(optional=true)
	@JoinColumn(name="ImagenId")
	private Attachment imagen;
	
	public Edificio(){
		
	}
	

	public Edificio(String nombreEdificio,String direccion, Facultad facultad, Attachment img) {
		super();
		this.nombreEdificio = nombreEdificio;
		this.direccion = direccion;
		this.deleted = false;
		this.imagen = img;
		this.facultad= facultad;
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

	public String getNombreEdificio() {
		return nombreEdificio;
	}

	public void setNombreEdificio(String nombreEdificio) {
		this.nombreEdificio = nombreEdificio;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Set<Espacio> getEspacios() {
		return espacios;
	}

	public void setEspacios(Set<Espacio> espacios) {
		this.espacios = espacios;
	}
	
	public Facultad getFacultad() {
		return facultad;
	}
	
	public Attachment getImagen() {
		return imagen;
	}

	public void setImagen(Attachment imagen) {
		this.imagen = imagen;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}
	
}
