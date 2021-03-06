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
package es.fdi.reservas.reserva.business.boundary;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;
import es.fdi.reservas.reserva.business.control.EspacioRepository;
import es.fdi.reservas.reserva.business.entity.Edificio;
import es.fdi.reservas.reserva.business.entity.Espacio;
import es.fdi.reservas.reserva.business.entity.TipoEspacio;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.fdi.reservas.fileupload.business.control.AttachmentRepository;
import es.fdi.reservas.fileupload.business.entity.Attachment;
import es.fdi.reservas.reserva.business.control.EspacioRepository;
import es.fdi.reservas.reserva.business.entity.Edificio;
import es.fdi.reservas.reserva.business.entity.Espacio;
import es.fdi.reservas.reserva.business.entity.EstadoReserva;
import es.fdi.reservas.reserva.business.entity.Reserva;
import es.fdi.reservas.reserva.business.entity.TipoEspacio;
import es.fdi.reservas.reserva.web.EspacioDTO;

import es.fdi.reservas.users.business.boundary.UserService;
import es.fdi.reservas.users.business.entity.User;

@Service
public class EspacioService {

	private EspacioRepository espacio_repository;
	private UserService user_service;
	private EdificioService edificio_service;
	private AttachmentRepository attachment_repository;
	
	@Autowired
	public EspacioService(EspacioRepository er, UserService us, AttachmentRepository ar, EdificioService edr) {
		super();
		this.espacio_repository = er;
		user_service = us;
		this.attachment_repository = ar;
		this.edificio_service = edr;
	}

	
	public Page<Espacio> getEspaciosPaginados(Pageable pageRequest) {
		return espacio_repository.findAll(pageRequest);
	}
	
	
	public Espacio editarEspacioDeleted(Long idEspacio){
		Espacio e = espacio_repository.findOne(idEspacio);
		e.setDeleted(true);
		return espacio_repository.save(e);
	}
		
	public Espacio editarEspacio(EspacioDTO espacioDTO){
		Espacio e = espacio_repository.findOne(espacioDTO.getId());

		Attachment attachment = new Attachment("");
		
		if (espacioDTO.getIdEdificio() != null){
			e.setEdificio(edificio_service.getEdificio(espacioDTO.getIdEdificio()));
		}
		
		if (espacioDTO.getImagen().equals("")){
			attachment = espacio_repository.findOne(espacioDTO.getId()).getImagen();
		}
		else if(espacioDTO.getImagen().contains("fakepath")){
			String img = espacioDTO.getImagen();
			String[] barras = img.split("fakepath");
			String fin = barras[1];
			fin = fin.substring(1);
			
			if (attachment_repository.getAttachmentByName(fin).isEmpty()){
				int pos = fin.lastIndexOf(".");
				String punto = fin.substring(0, pos);
				String end = fin.substring(pos+1, fin.length());
				String nom = punto + "-" + espacioDTO.getId() + "." + end;
				nom = nom.replace(nom, "/img/" + nom);
				
				
				attachment.setAttachmentUrl("/img/" + fin);
				attachment.setStorageKey(nom);
				attachment_repository.save(attachment);
			}else{
				attachment = attachment_repository.getAttachmentByName(fin).get(0);
			}
		}
		else {

			
			if (attachment_repository.getAttachmentByName(espacioDTO.getImagen()).isEmpty()){
		
				//si no esta, lo añado
				String img = espacioDTO.getImagen();
				int pos = img.lastIndexOf(".");
				String punto = img.substring(0, pos);
				String fin = img.substring(pos+1, img.length());
				String nom = punto + "-" + espacioDTO.getId() + "." + fin;
				nom = nom.replace(nom, "/img/" + nom);
				
				
				attachment.setAttachmentUrl("/img/" + espacioDTO.getImagen());
				attachment.setStorageKey(nom);
				attachment_repository.save(attachment);
			}else{
				attachment = attachment_repository.getAttachmentByName(espacioDTO.getImagen()).get(0);
			}
		}
		
		e.setNombreEspacio(espacioDTO.getNombreEspacio());
		e.setCapacidad(espacioDTO.getCapacidad());
		e.setMicrofono(espacioDTO.isMicrofono());
		e.setProyector(espacioDTO.isProyector());
		e.setTipoEspacio(TipoEspacio.fromTipoEspacio(espacioDTO.getTipoEspacio()));
		
		e.setImagen(attachment);
		
		return espacio_repository.save(e);
	}
	
	public Espacio editarEspacio(EspacioDTO espacio, Attachment attachment){
		Espacio e = espacio_repository.findOne(espacio.getId());
		e.setNombreEspacio(espacio.getNombreEspacio());
		e.setCapacidad(espacio.getCapacidad());
		e.setMicrofono(espacio.isMicrofono());
		e.setProyector(espacio.isProyector());
		e.setTipoEspacio(TipoEspacio.fromTipoEspacio(espacio.getTipoEspacio()));
		e.setEdificio(edificio_service.getEdificio(espacio.getIdEdificio()));
		e.setImagen(attachment);
		return espacio_repository.save(e);
	}
	
	public Espacio editarEspacioGestor(EspacioDTO espacio, Attachment attachment){
		Espacio e = espacio_repository.findOne(espacio.getId());
		e.setNombreEspacio(espacio.getNombreEspacio());
		e.setCapacidad(espacio.getCapacidad());
		e.setMicrofono(espacio.isMicrofono());
		e.setProyector(espacio.isProyector());
		e.setTipoEspacio(TipoEspacio.fromTipoEspacio(espacio.getTipoEspacio()));
		e.setEdificio(edificio_service.getEdificio(espacio.getIdEdificio()));
		e.setImagen(attachment);
		return espacio_repository.save(e);
	}
	
public Espacio addNewEspacio(EspacioDTO espacioDTO){
		TipoEspacio tipoEspacio = TipoEspacio.fromTipoEspacio(espacioDTO.getTipoEspacio());
		Espacio newEspacio = new Espacio(espacioDTO.getNombreEspacio(),espacioDTO.getCapacidad(), tipoEspacio); 

		newEspacio.setEdificio(edificio_service.getEdificio(espacioDTO.getIdEdificio()));
		newEspacio.setImagen(attachment_repository.findOne((long) 1));
		newEspacio = espacio_repository.save(newEspacio);
		
		return newEspacio;
	}		

	public List<Edificio> getEdificiosPorTagName(String tagName) {	
		return edificio_service.getEdificiosPorTagName(tagName);
	}	

	
	public Espacio restaurarEspacio(Long idEspacio) {
		Espacio e = espacio_repository.findOne(idEspacio);
		e.setDeleted(false);		
		return espacio_repository.save(e);
		
	}

	public List<Attachment> getAttachmentByName(String imagen) {
		return attachment_repository.getAttachmentByName(imagen);
	}

	public Page<Espacio> getEspaciosPorNombre(String nombre, Pageable pagerequest) {
		return espacio_repository.getEspaciosByTagName(nombre,pagerequest);
	}

	public List<Espacio> getEspaciosPorNombre(String nombre) {
		return espacio_repository.getEspaciosByTagName(nombre);
	}
	
	public Page<Espacio> getEspaciosPorEdificio(String tagName, Pageable pagerequest) {
		return espacio_repository.getEspaciosPorEdificio(tagName, pagerequest);
	}
	
	public List<Espacio> getEspaciosPorEdificio(String tagName) {
		return espacio_repository.getEspaciosPorEdificio(tagName);
	}
	
	public Page<Espacio> getEspaciosEliminadosPorNombre(String nombre, Pageable pagerequest) {
		return espacio_repository.getEspaciosEliminadosByTagName(nombre,pagerequest);
	}

	public Page<Espacio> getEspaciosEliminadosPorEdificio(String tagName, Pageable pagerequest) {
		return espacio_repository.getEspaciosEliminadosPorEdificio(tagName, pagerequest);
	}

	public User getCurrentUser(){
		return user_service.getCurrentUser();
	}
	
	public Iterable<Edificio> getEdificios(){
		return edificio_service.getEdificios();
	}
	

	public Espacio getEspacio(long idEspacio){
		return espacio_repository.findOne(idEspacio);
	}

	public Iterable<Espacio> getEspacios() {
		return espacio_repository.findAll();
	}
	

	public List<Espacio> getEspaciosEdificio(long idEdificio) {
		return espacio_repository.findByEdificioId(idEdificio);
	}
	

	// Espacios de un edificio de un TipoEspacio en concreto
	public List<Espacio> getTiposEspacio(long idEdificio, TipoEspacio idTipoEspacio) {
		return espacio_repository.findByEdificioIdAndTipoEspacio(idEdificio, idTipoEspacio);
	}
	
	// Todos los TipoEspacio que tiene un edificio
	public List<TipoEspacio> tiposDeEspacios(long idEdificio) {
		return espacio_repository.tiposDeEspacios(idEdificio);
	}
	

	public List<Espacio> getEspaciosPorTagName(String tag) {
		return espacio_repository.getEspaciosByTagName(tag);
	}
	
	public Page<Espacio> getEspaciosPaginados(PageRequest pageRequest) {
		return espacio_repository.findAll(pageRequest);
	}
	
	public void eliminarEspacio(long idEspacio) {
		espacio_repository.softDelete(Long.toString(idEspacio));
	}
	

	public List<Espacio> getEspaciosEliminados() {		
		return espacio_repository.recycleBin();
	}
	
	public Espacio save(Espacio e){
		return espacio_repository.save(e);
	}
	


	public Page<Espacio> getEspaciosEliminadosPaginados(Pageable pageRequest) {
		return espacio_repository.getEspaciosEliminadosPaginados(pageRequest);
	}

	public List<Reserva> reservasPendientesUsuario(Long idUsuario, EstadoReserva estadoReserva) {
		return user_service.reservasPendientesUsuario(idUsuario, estadoReserva);
	}

	public Page<Espacio> getEspaciosPaginadosPorNombre(PageRequest pageRequest, String nombre) {
		return espacio_repository.getEspaciosByTagName(nombre, pageRequest);
	}
	
	public Page<Espacio> getEspaciosPorNombreYFacultad(String nombre, Long id, Pageable pagerequest) {
		return espacio_repository.getEspaciosByTagNameAndFacultad(nombre, id,pagerequest);
	}

	public Page<Espacio> getEspaciosPorEdificioYFacultad(String tagName, Long id, Pageable pagerequest) {
		return espacio_repository.getEspaciosPorEdificioYFacultad(tagName, id, pagerequest);
	}
	
	public Page<Espacio> getEspaciosEliminadosPorNombreYFacultad(String nombre, Long id, Pageable pagerequest) {
		return espacio_repository.getEspaciosEliminadosByTagNameAndFacultad(nombre, id, pagerequest);
	}

	public Page<Espacio> getEspaciosEliminadosPorEdificioYFacultad(String tagName, Long id, Pageable pagerequest) {
		return espacio_repository.getEspaciosEliminadosPorEdificioYFacultad(tagName, id, pagerequest);
	}
	
	
	public Espacio findEspacio(long espacioid){
		return espacio_repository.findEspacio(espacioid);
	}
	
	public Page<Espacio> findEspacioByFacultadId(long facultadid, Pageable pageable){
		return espacio_repository.findEspacioByFacultadId(facultadid, pageable);
	}
	
	public Page<Espacio> findEspacioDeletedByFacultadId(long facultadid, Pageable pageable){
		return espacio_repository.findEspacioDeletedByFacultadId(facultadid, pageable);
	}

	public Page<Espacio> getEspaciosPaginadosPorEdificio(PageRequest pageRequest, String nombre) {
		return espacio_repository.getEspaciosPorEdificio(nombre, pageRequest);
	}
}


