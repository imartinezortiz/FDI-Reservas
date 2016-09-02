package es.fdi.reservas.reserva.business.control;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import es.fdi.reservas.reserva.business.entity.Edificio;
import es.fdi.reservas.reserva.business.entity.Facultad;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Long>{

	
	@Query("select f from #{#entityName} f where f.deleted=false")
	List<Edificio> findAll();

	@Query("select e from #{#entityName} e where e.deleted=true")
	List<Edificio> recycleBin();
	
	@Modifying
	@Query("update #{#entityName} e set e.deleted=true where e.id= :idEdificio")
	void softDelete(@Param("idEdificio") String idEdificio);

	@Query("select e from #{#entityName} e where (e.deleted=true) AND (e.facultad.id = :idFacultad)")
	public List<Edificio> findByFacultadId(@Param("idFacultad") Long idFacultad);

	@Query("select e from #{#entityName} e where (e.id = :id)")
	public Edificio findEdificio(@Param("id") long id);
	
	@Query("select e from #{#entityName} e where (e.deleted=true) AND (e.facultad.id = :idFacultad)")
	public Page<Edificio> findEdificioByFacultadId(@Param("idFacultad") Long idFacultad, Pageable pageable);
}