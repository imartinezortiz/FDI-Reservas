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
import org.springframework.stereotype.Service;

import es.fdi.reservas.reserva.business.control.GrupoReservaRepository;
import es.fdi.reservas.reserva.business.entity.EstadoReserva;
import es.fdi.reservas.reserva.business.entity.GrupoReserva;
import es.fdi.reservas.reserva.business.entity.Reserva;
import es.fdi.reservas.reserva.web.GrupoReservaDTO;
import es.fdi.reservas.users.business.boundary.UserService;
import es.fdi.reservas.users.business.entity.User;

@Service
public class GrupoReservaService {

	private GrupoReservaRepository grupo_repository;
	private UserService user_service;
	
	@Autowired
	public GrupoReservaService(GrupoReservaRepository grr, UserService us){
		grupo_repository = grr;
		user_service = us;
	}
	
	public GrupoReserva addNuevoGrupo(GrupoReserva grupo, User user){
		GrupoReserva newGrupo = null;
		List<GrupoReserva> listaGrupos = getGruposUsuario(user.getId());
		
		for(GrupoReserva g : listaGrupos){
			if(g.equals(grupo))
				return newGrupo;
		}
		newGrupo = new GrupoReserva(grupo.getNombreCorto(), grupo.getNombreLargo(), user);
		newGrupo = grupo_repository.save(newGrupo);
		
		return newGrupo;
	}
	
	public List<GrupoReserva> getGruposReservas(){
		return grupo_repository.findAll();
	}

	public List<GrupoReserva> getGruposPorTagName(String tagName, Long idUsuario) {
		return grupo_repository.getGruposPorTagName(tagName,idUsuario);
	}

	public GrupoReserva getGrupoReserva(long idGrupo) {
		return grupo_repository.findOne(idGrupo);
	}

	public void eliminarGrupo(long idGrupo) {
		grupo_repository.delete(idGrupo);
		
	}

	public List<GrupoReserva> getGruposUsuario(Long idUsuario) {
		return grupo_repository.findByUserId(idUsuario);
	}
	
	public GrupoReserva findGrupoReserva(long grupoid){
		return grupo_repository.getOne(grupoid);
	}
	
	public List<GrupoReserva> findGrupoReservaByUserId(long userid){
		return grupo_repository.findByUserId(userid);
	}
	
	public void editarGrupoReserva(Long idGrupo, GrupoReservaDTO grDTO) {
		GrupoReserva grupo = getGrupoReserva(idGrupo);
		grupo.setNombreCorto(grDTO.getNombreCorto());
		grupo.setNombreLargo(grDTO.getNombreLargo());
		
		grupo_repository.save(grupo);		
	}

	public User getCurrentUser() {
		return user_service.getCurrentUser();
	}

	public List<Reserva> reservasPendientesUsuario(Long idUsuario, EstadoReserva estadoReserva) {
		return user_service.reservasPendientesUsuario(idUsuario, estadoReserva);
	}

}
