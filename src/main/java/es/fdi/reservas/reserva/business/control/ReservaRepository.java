package es.fdi.reservas.reserva.business.control;

import java.util.List;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import es.fdi.reservas.reserva.business.entity.Reserva;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	public List<Reserva> findByUsername(String username); 
	
	//public List<Reserva> findBySpacename(String spacename); 
	
	public List<Reserva> findAll();

	public List<Reserva> findByEspacioId(long idEspacio);

  // http://stackoverflow.com/questions/18082276/spring-data-querying-datetime-with-only-date
	public List<Reserva> findByEspacioIdAndComienzoBetween(Long idEspacio, DateTime start, DateTime end); 

	public List<Reserva> findByEspacioIdAndFinBetween(Long idEspacio, DateTime start, DateTime end);
	
	@Query("FROM Reserva r WHERE (r.espacio.id = :idEspacio) AND (( :start BETWEEN r.comienzo AND r.fin) OR ( :end BETWEEN r.comienzo AND r.fin ) OR (r.comienzo BETWEEN :start AND :end) OR (r.fin BETWEEN :start AND :end) )")
	public List<Reserva> reservasConflictivas(@Param("idEspacio")Long idEspacio, @Param("start") DateTime start, @Param("end") DateTime end);
	
	
	
	
	
}
