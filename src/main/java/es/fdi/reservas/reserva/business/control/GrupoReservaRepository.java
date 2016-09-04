package es.fdi.reservas.reserva.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.fdi.reservas.reserva.business.entity.GrupoReserva;

public interface GrupoReservaRepository extends JpaRepository<GrupoReserva, Long>{

	@Query("from GrupoReserva g where (lower(g.nombreCorto) like lower(concat('%',:nombreCorto, '%')) AND (g.user.id = :idUsuario))")
	public List<GrupoReserva> getGruposPorTagName(@Param("nombreCorto") String nombreCort,@Param("idUsuario") Long idUsuario);
	
	public List<GrupoReserva> findByUserId(Long idUsuario);

}
