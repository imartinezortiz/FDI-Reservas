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
	
	public List<Reserva> findByUserId(long idUsuario);  

	public List<Reserva> findByEspacioId(long idEspacio);
	
	public Page<Reserva> findByUserId(long idUsuario, Pageable pageable); 

	public Page<Reserva> findByEspacioId(long idEspacio, Pageable pageable); 

	public Page<Reserva> findByEstadoReserva(EstadoReserva estado, Pageable pageable);
 
	@Query("FROM Reserva r WHERE (r.espacio.edificio.facultad.id = :idFacultad)")
	public Page<Reserva> findByFacultadId(@Param("idFacultad")Long idFacultad, Pageable pageable);
	
	@Query("FROM Reserva r WHERE (r.espacio.edificio.facultad.id = :idFacultad)")
	public List<Reserva> findByFacultadId(@Param("idFacultad")Long idFacultad);
	
	
	//Por Usuario y Facultad
	@Query("FROM Reserva r WHERE (r.espacio.edificio.facultad.id = :idFacultad) AND (r.user.id = :idUser)")
	public Page<Reserva> findByUserIdAndFacultadId(@Param("idUser")Long idUser, @Param("idFacultad")Long idFacultad, Pageable pageable);
	
	@Query("FROM Reserva r WHERE (r.espacio.edificio.facultad.id = :idFacultad) AND (r.user.id = :idUser)")
	public List<Reserva> findByUserIdAndFacultadId(@Param("idUser")Long idUser, @Param("idFacultad")Long idFacultad);
	
	//Por Espacio y Facultad
	@Query("FROM Reserva r WHERE (r.espacio.id = :idEspacio) AND (r.user.id = :idUser)")
	public Page<Reserva> findByEspacioIdAndFacultadId(@Param("idUser")Long idUser, @Param("idEspacio")Long idEspacio, Pageable pageable);
	
	@Query("FROM Reserva r WHERE (r.espacio.id = :idEspacio) AND (r.user.id = :idUser)")
	public List<Reserva> findByEspacioIdAndFacultadId(@Param("idUser")Long idUser, @Param("idEspacio")Long idEspacio);
	
	//Por EstadoReserva y facultad
	@Query("From Reserva r WHERE (r.estadoReserva=:estado) and (r.espacio.edificio.facultad.id=:idFacultad)")
	public Page<Reserva> findByEstadoReservaAndFacultadId(@Param("estado")EstadoReserva estado, @Param("idFacultad")Long idFacultad, Pageable pageable);
	
	@Query("From Reserva r WHERE (r.estadoReserva=:estado) and (r.espacio.edificio.facultad.id=:idFacultad)")
	public List<Reserva> findByEstadoReservaAndFacultadId(@Param("estado")EstadoReserva estado, @Param("idFacultad")Long idFacultad);
	
	// http://stackoverflow.com/questions/18082276/spring-data-querying-datetime-with-only-date
	public List<Reserva> findByEspacioIdAndComienzoBetween(Long idEspacio, DateTime start, DateTime end); 

	public List<Reserva> findByEspacioIdAndFinBetween(Long idEspacio, DateTime start, DateTime end);
	
	@Query("FROM Reserva r WHERE (r.espacio.id = :idEspacio) AND (( :start BETWEEN r.comienzo AND r.fin) OR ( :end BETWEEN r.comienzo AND r.fin ) OR (r.comienzo BETWEEN :start AND :end) OR (r.fin BETWEEN :start AND :end) )")
	public List<Reserva> reservasConflictivas(@Param("idEspacio")Long idEspacio, @Param("start") DateTime start, @Param("end") DateTime end);
	
	@Query("FROM Reserva r WHERE (r.espacio.id = :idEspacio) AND ((r.endRecurrencia > :startRecurrencia) OR (r.startRecurrencia < :endRecurrencia))")
	public List<Reserva> reservasRecurrentes(@Param("idEspacio")Long idEspacio, @Param("startRecurrencia") DateTime startRecurrencia, @Param("endRecurrencia") DateTime endRecurrencia);
	
	@Query("FROM Reserva r WHERE (r.espacio.id = :idEspacio) AND (DATE_FORMAT(r.comienzo,'%H:%i') < '15:00')")
	public List<Reserva> reservasEspacioDeMañana(@Param("idEspacio")Long idEspacio);
	
	@Query("FROM Reserva r WHERE (r.espacio.id = :idEspacio) AND (DATE_FORMAT(r.comienzo,'%H:%i') >= '15:00')")
	public List<Reserva> reservasEspacioDeTarde(@Param("idEspacio")Long idEspacio);

	public List<Reserva> findByGrupoReservaIdAndUserId(Long idGrupo, Long idUsuario);
	
	@Query("FROM Reserva r WHERE (r.user.id = :idUsuario) AND (r.estadoReserva = :estado)")
	public List<Reserva> reservasPendientesUsuario(@Param("idUsuario") Long idUsuario,@Param("estado") EstadoReserva estado);
	
	
}
