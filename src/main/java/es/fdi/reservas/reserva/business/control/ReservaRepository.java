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
package es.fdi.reservas.reserva.business.control;

import java.util.List;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.fdi.reservas.reserva.business.entity.EstadoReserva;
import es.fdi.reservas.reserva.business.entity.Reserva;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	
	// Reservas de un usuario
	public List<Reserva> findByUserId(long idUsuario);
	// Reservas no denegadas de un usuario
	@Query("FROM Reserva r WHERE (r.user.id = :idUsuario) AND (r.estadoReserva <> 2)")
	public List<Reserva> misReservasCalendario(@Param("idUsuario") long idUsuario);
	// Reservas que no estén denegadas de un espacio
	@Query("FROM Reserva r WHERE (r.espacio.id = :idEspacio) AND (r.estadoReserva <> 2)")
	public List<Reserva> reservasEspacio(@Param("idEspacio") long idEspacio);
	// Reservas paginadas de un usuario
	public Page<Reserva> findByUserId(long idUsuario, Pageable pageable); 
	// Reservas paginadas de un edificio
	public Page<Reserva> findByEspacioId(long idEspacio, Pageable pageable); 


	public Page<Reserva> findByEstadoReserva(EstadoReserva estado, Pageable pageable);
 
	@Query("FROM Reserva r WHERE (r.espacio.edificio.facultad.id = :idFacultad)")
	public Page<Reserva> findByFacultadId(@Param("idFacultad")Long idFacultad, Pageable pageable);
	
	@Query("FROM Reserva r WHERE (r.espacio.edificio.facultad.id = :idFacultad)")
	public List<Reserva> findByFacultadId(@Param("idFacultad")Long idFacultad);
	
	
	//Por Usuario y Facultad
	@Query("from Reserva e where (e.espacio.edificio.facultad.id = :idFacultad) and ((lower(e.user.username) like lower(concat('%',:user, '%'))) or (lower(e.user.email) like lower(concat('%',:user, '%'))))")
	public Page<Reserva> findByUserIdAndFacultadId(@Param("user")String user, @Param("idFacultad")Long idFacultad, Pageable pageable);
	
	@Query("from Reserva e where (e.espacio.edificio.facultad.id = :idFacultad) and ((lower(e.user.username) like lower(concat('%',:user, '%'))) or (lower(e.user.email) like lower(concat('%',:user, '%'))))")
	public List<Reserva> findByUserIdAndFacultadId(@Param("user")String user, @Param("idFacultad")Long idFacultad);
	
	//Por Espacio y Facultad
	@Query("from Reserva e where (e.espacio.edificio.facultad.id = :idFacultad) and (lower(e.espacio.nombreEspacio) like lower(concat('%',:espacio, '%')))")
	public Page<Reserva> findByEspacioIdAndFacultadId(@Param("espacio")String espacio, @Param("idFacultad")Long idFacultad, Pageable pageable);
	
	@Query("from Reserva e where (e.espacio.edificio.facultad.id = :idFacultad) and (lower(e.espacio.nombreEspacio) like lower(concat('%',:espacio, '%')))")
	public List<Reserva> findByEspacioIdAndFacultadId(@Param("espacio")String espacio, @Param("idFacultad")Long idFacultad);
	
	//Por EstadoReserva y facultad
	@Query("From Reserva r WHERE (r.estadoReserva=:estado) and (r.espacio.edificio.facultad.id=:idFacultad)")
	public Page<Reserva> findByEstadoReservaAndFacultadId(@Param("estado")EstadoReserva estado, @Param("idFacultad")Long idFacultad, Pageable pageable);
	
	@Query("From Reserva r WHERE (r.estadoReserva=:estado) and (r.espacio.edificio.facultad.id=:idFacultad)")
	public List<Reserva> findByEstadoReservaAndFacultadId(@Param("estado")EstadoReserva estado, @Param("idFacultad")Long idFacultad);
	
	// http://stackoverflow.com/questions/18082276/spring-data-querying-datetime-with-only-date
	public List<Reserva> findByEspacioIdAndComienzoBetween(Long idEspacio, DateTime start, DateTime end); 

	public List<Reserva> findByEspacioIdAndFinBetween(Long idEspacio, DateTime start, DateTime end);
	
	public List<Reserva> findByEspacioId(long idEspacio);
	
	@Query("FROM Reserva r WHERE (r.espacio.id = :idEspacio) AND (r.estadoReserva <> 2) AND (( :start BETWEEN r.comienzo AND r.fin) OR ( :end BETWEEN r.comienzo AND r.fin ) OR (r.comienzo BETWEEN :start AND :end) OR (r.fin BETWEEN :start AND :end) )")
	public List<Reserva> reservasConflictivas(@Param("idEspacio")Long idEspacio, @Param("start") DateTime start, @Param("end") DateTime end);
	
	@Query("FROM Reserva r WHERE (r.espacio.id = :idEspacio) AND (r.estadoReserva <> 2) AND ((r.endRecurrencia > :startRecurrencia) OR (r.startRecurrencia < :endRecurrencia))")
	public List<Reserva> reservasRecurrentes(@Param("idEspacio")Long idEspacio, @Param("startRecurrencia") DateTime startRecurrencia, @Param("endRecurrencia") DateTime endRecurrencia);
	
	@Query("FROM Reserva r WHERE (r.espacio.id = :idEspacio) AND (r.estadoReserva <> 2) AND (DATE_FORMAT(r.comienzo,'%H:%i') < '15:00')")
	public List<Reserva> reservasEspacioDeMañana(@Param("idEspacio")Long idEspacio);
	
	@Query("FROM Reserva r WHERE (r.espacio.id = :idEspacio) AND (r.estadoReserva <> 2) AND (DATE_FORMAT(r.comienzo,'%H:%i') >= '15:00')")
	public List<Reserva> reservasEspacioDeTarde(@Param("idEspacio")Long idEspacio);

	@Query("FROM Reserva r WHERE (r.user.id = :idUsuario) AND (r.grupoReserva.id = :idGrupo) AND (r.estadoReserva <> 2)")
	public List<Reserva> reservasGrupoUsuario(@Param("idGrupo") Long idGrupo, @Param("idUsuario") Long idUsuario);
	
	@Query("FROM Reserva r WHERE (r.user.id = :idUsuario) AND (r.estadoReserva = :estado)")
	public List<Reserva> reservasPendientesUsuario(@Param("idUsuario") Long idUsuario,@Param("estado") EstadoReserva estado);
	
	
}
