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
import es.fdi.reservas.reserva.business.control.FacultadRepository;
import es.fdi.reservas.reserva.business.entity.EstadoReserva;
import es.fdi.reservas.reserva.business.entity.Facultad;
import es.fdi.reservas.reserva.business.entity.Reserva;
import es.fdi.reservas.users.business.boundary.UserService;
import es.fdi.reservas.users.business.entity.User;
import org.springframework.data.domain.Pageable;
import es.fdi.reservas.reserva.web.FacultadDTO;

@Service
public class FacultadService {

	private FacultadRepository facultad_repository;
	private UserService user_service;
	
	@Autowired
	public FacultadService(FacultadRepository fr, UserService us){
		facultad_repository = fr;
		user_service = us;
	}
	
	public User getCurrentUser(){
		return user_service.getCurrentUser();
	}

	public Facultad editarFacultad(FacultadDTO facultad){
		Facultad f = facultad_repository.findOne(facultad.getId());
		f.setNombreFacultad(facultad.getNombreFacultad());
		f.setWebFacultad(facultad.getWebFacultad());
		
		return facultad_repository.save(f);
	}
	
	public Facultad editarFacultadDeleted(Long idFacultad){
		Facultad f = facultad_repository.findOne(idFacultad);
		f.setDeleted(true);
		
		return facultad_repository.save(f);
	}
	
	public Page<Facultad> getFacultadesPorTagName(String tagName, Pageable pagerequest) {
		return facultad_repository.getFacultadesPorTagName(tagName, pagerequest);
	}

	public Page<Facultad> getFacultadesEliminadasPorTagName(String tagName, Pageable pagerequest) {
		return facultad_repository.getFacultadesEliminadasPorTagName(tagName, pagerequest);
	}
	
	public Facultad getFacultad(long idFacultad){
		return facultad_repository.findOne(idFacultad);
	}
	
	public Iterable<Facultad> getFacultades() {
		return facultad_repository.findAll();
	}
	
	public List<Facultad> getFacultadesPorTagName(String tagName) {
		return facultad_repository.getFacultadesPorTagName(tagName);
	}

	public Facultad addNewFacultad(FacultadDTO facultad){
		Facultad newFacultad = new Facultad(facultad.getNombreFacultad(), facultad.getWebFacultad());
		newFacultad = facultad_repository.save(newFacultad);
				
		return newFacultad;
	}
	
	public Facultad restaurarFacultad(Long idFacultad) {
		Facultad e = facultad_repository.findOne(idFacultad);
		e.setDeleted(false);
		
		return facultad_repository.save(e);	
	}
	
	public Facultad getFacultadPorId(long l) {
		return facultad_repository.getFacultadPorId(l);
	}
	
	public Page<Facultad> getFacultadesEliminadasPaginadas(Pageable pr) {	
		return facultad_repository.recycleBin(pr);
	}

	public Page<Facultad> getFacultadesPaginadasPorNombre(Long nombre, PageRequest pageRequest) {		
		return null;
	}

	public Page<Facultad> getFacultadesPorWeb(String tagName, Pageable pagerequest) {		
		return facultad_repository.getFacultadesPorWeb(tagName, pagerequest);
	}
	
	public Page<Facultad> getFacultadesEliminadasPorWeb(String tagName, Pageable pagerequest) {		
			return facultad_repository.getFacultadesEliminadasPorWeb(tagName, pagerequest);
	}
	
	public Page<Facultad> getFacultadesPaginadas(PageRequest pageRequest) {
		return facultad_repository.findAll(pageRequest);
	}
	
	public void eliminarFacultad(Long idFacultad) {
		facultad_repository.softDelete(idFacultad);	
	}
	
	public List<Facultad> getFacultadesEliminadas() {		
		return facultad_repository.recycleBin();
	}
	
	public Facultad save(Facultad f){
		return facultad_repository.save(f);
	}

	public List<Reserva> reservasPendientesUsuario(Long idUsuario, EstadoReserva estadoReserva) {
		return user_service.reservasPendientesUsuario(idUsuario, estadoReserva);
	}
	
}
