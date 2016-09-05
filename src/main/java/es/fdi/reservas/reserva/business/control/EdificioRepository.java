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

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Long>{

	//Todos
	@Query("select f from #{#entityName} f where f.deleted=false")
	List<Edificio> findAll();

	@Query("select e from #{#entityName} e where e.deleted=true")
	List<Edificio> recycleBin();
	
	@Modifying
	@Query("update #{#entityName} e set e.deleted=true where e.id= :idEdificio")
	void softDelete(@Param("idEdificio") String idEdificio);

	//Por facultad
	@Query("from #{#entityName} e where (e.deleted=false) AND (e.facultad.id = :idFacultad)")
	public Page<Edificio> findByFacultadId(@Param("idFacultad") Long idFacultad, Pageable pageable);
	
	@Query("from #{#entityName} e where (e.deleted=false) AND (e.facultad.id = :idFacultad)")
	public List<Edificio> findByFacultadId(@Param("idFacultad") Long idFacultad);
	
	@Query("from #{#entityName} e where (e.deleted=true) AND (e.facultad.id = :idFacultad)")
	public List<Edificio> findDeletedByFacultadId(@Param("idFacultad") Long idFacultad);
	
	@Query("from #{#entityName} e where (e.deleted=true) AND (e.facultad.id = :idFacultad)")
	public Page<Edificio> findDeletedByFacultadId(@Param("idFacultad") Long idFacultad, Pageable pageable);

	//Por ID
	@Query("select e from #{#entityName} e where (e.id = :id)")
	public Edificio findEdificio(@Param("id") long id);
	
	//Por TagName de Nombre
	@Query("from Edificio f where f.deleted=false and lower(f.nombreEdificio) like lower(concat('%',:nombreEdificio, '%'))")
	public List<Edificio> getEdificiosPorTagName(@Param("nombreEdificio") String nombreEdificio);
	
	@Query("from Edificio f where f.deleted=false and lower(f.nombreEdificio) like lower(concat('%',:nombreEdificio, '%'))")
	public Page<Edificio> getEdificiosPorTagName(@Param("nombreEdificio") String nombreEdificio, Pageable pagerequest);

	@Query("from Edificio f where f.deleted=true and lower(f.nombreEdificio) like lower(concat('%',:nombreEdificio, '%'))")
	public Page<Edificio> getEdificiosEliminadosPorTagName(@Param("nombreEdificio") String nombreEdificio, Pageable pagerequest);
	
	@Query("from Edificio f where f.deleted=true and lower(f.nombreEdificio) like lower(concat('%',:nombreEdificio, '%'))")
	public List<Edificio> getEdificiosEliminadosPorTagName(@Param("nombreEdificio") String nombreEdificio);
	
	//Por Facultad y TagName de Nombre 
	@Query("from Edificio f where f.deleted=false and f.facultad.id = :idFacultad and lower(f.nombreEdificio) like lower(concat('%',:nombreEdificio, '%'))")
	public List<Edificio> getEdificiosPorTagNameYFacultad(@Param("nombreEdificio") String nombreEdificio, @Param("idFacultad") Long idFacultad);
	
	@Query("from Edificio f where f.deleted=false and f.facultad.id = :idFacultad and lower(f.nombreEdificio) like lower(concat('%',:nombreEdificio, '%'))")
	public Page<Edificio> getEdificiosPorTagNameYFacultad(@Param("nombreEdificio") String nombreEdificio, @Param("idFacultad") Long idFacultad, Pageable pagerequest);

	@Query("from Edificio f where f.deleted=true and f.facultad.id = :idFacultad and lower(f.nombreEdificio) like lower(concat('%',:nombreEdificio, '%'))")
	public Page<Edificio> getEdificiosEliminadosPorTagNameYFacultad(@Param("nombreEdificio") String nombreEdificio, @Param("idFacultad") Long idFacultad, Pageable pagerequest);
	
	@Query("from Edificio f where f.deleted=true and f.facultad.id = :idFacultad and lower(f.nombreEdificio) like lower(concat('%',:nombreEdificio, '%'))")
	public List<Edificio> getEdificiosEliminadosPorTagNameYFacultad(@Param("nombreEdificio") String nombreEdificio, @Param("idFacultad") Long idFacultad);
		
//	@Query("from Edificio f where lower(f.direccion) like lower(concat('%',:direccion, '%'))")
//	List<Edificio> getEdificiosPorDireccion(@Param("direccion") String tagName);
	
	//Por TagName de direccion
	@Query("from Edificio f where f.deleted=false and lower(f.direccion) like lower(concat('%',:direccion, '%'))")
	public Page<Edificio> getEdificiosPorDireccion(@Param("direccion") String tagName, Pageable pagerequest);
	
	@Query("from Edificio f where f.deleted=true and lower(f.direccion) like lower(concat('%',:direccion, '%'))")
	public Page<Edificio> getEdificiosEliminadosPorDireccion(@Param("direccion") String tagName, Pageable pagerequest);
	
	@Query("from Edificio f where f.deleted=false and lower(f.direccion) like lower(concat('%',:direccion, '%'))")
	public List<Edificio> getEdificiosPorDireccion(@Param("direccion") String tagName);
	
	@Query("from Edificio f where f.deleted=true and lower(f.direccion) like lower(concat('%',:direccion, '%'))")
	public List<Edificio> getEdificiosEliminadosPorDireccion(@Param("direccion") String tagName);
	
	@Query("from Edificio f where f.deleted=false and f.facultad.id = :idFacultad and lower(f.direccion) like lower(concat('%',:direccion, '%'))")
	public Page<Edificio> getEdificiosPorDireccionYFacultad(@Param("direccion") String tagName, @Param("idFacultad") Long idFacultad, Pageable pagerequest);
	
	@Query("from Edificio f where f.deleted=true and f.facultad.id = :idFacultad and lower(f.direccion) like lower(concat('%',:direccion, '%'))")
	public Page<Edificio> getEdificiosEliminadosPorDireccionYFacultad(@Param("direccion") String tagName, @Param("idFacultad") Long idFacultad, Pageable pagerequest);
	
	@Query("from Edificio f where f.deleted=false and f.facultad.id = :idFacultad and lower(f.direccion) like lower(concat('%',:direccion, '%'))")
	public List<Edificio> getEdificiosPorDireccionYFacultad(@Param("direccion") String tagName, @Param("idFacultad") Long idFacultad);
	
	@Query("from Edificio f where f.deleted=true and f.facultad.id = :idFacultad and lower(f.direccion) like lower(concat('%',:direccion, '%'))")
	public List<Edificio> getEdificiosEliminadosPorDireccionYFacultad(@Param("direccion") String tagName, @Param("idFacultad") Long idFacultad);
	
	
//	@Query("select e from #{#entityName} e where e.deleted=false and e.facultad.nombreFacultad like lower(concat('%',:nombre, '%'))")
//	List<Edificio> getEdificiosPorFacultad(@Param("nombre") String nombre);
	
	//Por TagName de Facultad
	@Query("select e from #{#entityName} e where e.facultad.nombreFacultad like lower(concat('%',:nombre, '%'))")
	public Page<Edificio> getEdificiosEliminadosPorFacultad(@Param("nombre") String nombre, Pageable pagerequest);
	
	@Query("select e from #{#entityName} e where e.deleted=false and e.facultad.nombreFacultad like lower(concat('%',:nombre, '%'))")
	public Page<Edificio> getEdificiosPorFacultad(@Param("nombre") String nombre, Pageable pagerequest);
	
	@Query("select e from #{#entityName} e where e.facultad.nombreFacultad like lower(concat('%',:nombre, '%'))")
	public List<Edificio> getEdificiosEliminadosPorFacultad(@Param("nombre") String nombre);
	
	@Query("select e from #{#entityName} e where e.deleted=false and e.facultad.nombreFacultad like lower(concat('%',:nombre, '%'))")
	public List<Edificio> getEdificiosPorFacultad(@Param("nombre") String nombre);
	
	//MIRAR SI SALE MAL y si hay que usarlo
	@Query("select e from #{#entityName} e where e.deleted=false and e.nombreEdificio = :edificio")
	public Edificio getEdificiosPorNombre(@Param("edificio") String edificio);
}