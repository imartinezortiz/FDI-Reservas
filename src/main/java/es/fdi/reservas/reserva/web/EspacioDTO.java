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

public class EspacioDTO {
	
	private Long id;
	
	private String nombreEspacio;
	
	private Long idEdificio;

	private int capacidad;
	
	private boolean microfono, proyector;
	
	private String tipoEspacio;
	
	private String imagen;
	
	private String tipoAutorizacion;
	
	private Integer horasAutorizacion;
	
	public EspacioDTO(){}
	
	public EspacioDTO(Long id, String nombreEspacio, Long edificio, int capacidad, boolean microfono,
			boolean proyector, String tipoEspacio, String imagen) {
		super();
		this.id = id;
		this.nombreEspacio = nombreEspacio;
		this.idEdificio = edificio;
		this.capacidad = capacidad;
		this.microfono = microfono;
		this.proyector = proyector;
		this.tipoEspacio = tipoEspacio;
		this.imagen = imagen;
	}
	
	public EspacioDTO(String nombreEspacio, Long edificio, int capacidad, boolean microfono,
			boolean proyector, String tipoEspacio, String imagen) {
		super();
		this.nombreEspacio = nombreEspacio;
		this.idEdificio = edificio;
		this.capacidad = capacidad;
		this.microfono = microfono;
		this.proyector = proyector;
		this.tipoEspacio = tipoEspacio;
		this.imagen = imagen;
	}
	
	public EspacioDTO(Long id, String nombreEspacio, Long idedificio, int capacidad, boolean microfono,
			boolean proyector, String tipoEspacio, String imagen, String tipoAutorizacion, Integer horasAutorizacion) {
		super();
		this.id = id;
		this.nombreEspacio = nombreEspacio;
		this.idEdificio = idedificio;
		this.capacidad = capacidad;
		this.microfono = microfono;
		this.proyector = proyector;
		this.tipoEspacio = tipoEspacio;
		this.imagen = imagen;
		this.tipoAutorizacion = tipoAutorizacion;
		this.horasAutorizacion = horasAutorizacion;
	}
	
	public EspacioDTO(String nombreEspacio, Long edificio, int capacidad, boolean microfono,
			boolean proyector, String tipoEspacio, String imagen, String tipoAutorizacion, Integer horasAutorizacion) {
		super();
		this.nombreEspacio = nombreEspacio;
		this.idEdificio = edificio;
		this.capacidad = capacidad;
		this.microfono = microfono;
		this.proyector = proyector;
		this.tipoEspacio = tipoEspacio;
		this.imagen = imagen;
		this.tipoAutorizacion = tipoAutorizacion;
		this.horasAutorizacion = horasAutorizacion;
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

	
	
	public Long getIdEdificio() {
		return idEdificio;
	}

	public void setIdEdificio(Long idEdificio) {
		this.idEdificio = idEdificio;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public boolean isMicrofono() {
		return microfono;
	}

	public void setMicrofono(boolean microfono) {
		this.microfono = microfono;
	}

	public boolean isProyector() {
		return proyector;
	}

	public void setProyector(boolean proyector) {
		this.proyector = proyector;
	}
	
	public String getTipoEspacio() {
		return tipoEspacio;
	}

	public void setTipoEspacio(String tipoEspacio) {
		this.tipoEspacio = tipoEspacio;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTipoAutorizacion() {
		return tipoAutorizacion;
	}

	public void setTipoAutorizacion(String tipoAutorizacion) {
		this.tipoAutorizacion = tipoAutorizacion;
	}

	public Integer getHorasAutorizacion() {
		return horasAutorizacion;
	}

	public void setHorasAutorizacion(Integer horasAutorizacion) {
		this.horasAutorizacion = horasAutorizacion;
	}

	public static EspacioDTO fromEspacioDTOAutocompletar(Espacio e){
		return new EspacioDTO(e.getId(), e.getNombreEspacio(), e.getEdificio().getId(), e.getCapacidad(), e.isMicrofono(), e.isProyector(), e.getTipoEspacio().getTipo(), e.getImagen().getAttachmentUrl(),e.getTipoAutorizacion().toString(), e.getHorasAutorizacion());
	}
	
	public static EspacioDTO fromEspacioDTO(Espacio e){
		return new EspacioDTO(e.getNombreEspacio(), e.getEdificio().getId(), e.getCapacidad(), e.isMicrofono(), e.isProyector(), e.getTipoEspacio().getTipo(), e.getImagen().getAttachmentUrl(), e.getTipoAutorizacion().toString(), e.getHorasAutorizacion());
	}
}
