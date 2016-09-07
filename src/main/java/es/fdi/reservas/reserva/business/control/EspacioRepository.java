package es.fdi.reservas.reserva.business.control;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.fdi.reservas.reserva.business.entity.Espacio;
import es.fdi.reservas.reserva.business.entity.TipoEspacio;


@Repository
public interface EspacioRepository extends JpaRepository<Espacio, Long>{

	@Query("select f from #{#entityName} f where f.deleted=false and f.edificio.id = :idEdificio")
	public List<Espacio> findByEdificioId(@Param("idEdificio") Long idEdificio);

	@Query("select f from #{#entityName} f where f.deleted=false and f.edificio.id = :idEdificio")
	public Page<Espacio> findByEdificioId(@Param("idEdificio") Long idEdificio, Pageable pageable);

	@Query("select f from #{#entityName} f where f.deleted=true and f.edificio.id = :idEdificio")
	public List<Espacio> findDeletedByEdificioId(@Param("idEdificio") Long idEdificio);

	@Query("select f from #{#entityName} f where f.deleted=true and f.edificio.id = :idEdificio")
	public Page<Espacio> findDeletedByEdificioId(@Param("idEdificio") Long idEdificio, Pageable pageable);
	
	public List<Espacio> findById(Long Id);
	
	//Por Edificio y TipoEspacio
	@Query("select f from #{#entityName} f where f.deleted=false and f.edificio.id = :idEdificio and f.tipoEspacio = :tipoEspacio")
	public List<Espacio> findByEdificioIdAndTipoEspacio(@Param("idEdificio") Long idEdificio, @Param("tipoEspacio") TipoEspacio tipoEspacio);
	
	@Query("select f from #{#entityName} f where f.deleted=false and f.edificio.id = :idEdificio and f.tipoEspacio = :tipoEspacio")
	public Page<Espacio> findByEdificioIdAndTipoEspacio(@Param("idEdificio") Long idEdificio, @Param("tipoEspacio") TipoEspacio tipoEspacio, Pageable pageable);
	
	//Tipos de espacio
	@Query("SELECT DISTINCT e.tipoEspacio FROM Espacio e WHERE e.edificio.id = :idEdificio")
	public List<TipoEspacio> tiposDeEspacios(@Param("idEdificio") long idEdificio);
	
	//Todos
	@Query("select f from #{#entityName} f where f.deleted=false")
	List<Espacio> findAll();

	@Query("select e from #{#entityName} e where e.deleted=true")
	List<Espacio> recycleBin();
	
	@Query("select f from #{#entityName} f where f.deleted=false")
	Page<Espacio> findAll(Pageable pageable);

	@Query("select e from #{#entityName} e where e.deleted=true")
	Page<Espacio> recycleBin(Pageable pageable);
	
	@Modifying
	@Query("update #{#entityName} e set e.deleted=true where e.id= :idEspacio")
	void softDelete(@Param("idEspacio") String idEspacio);
	
	//Por TagName de Nombre
	@Query("from #{#entityName} e where lower(e.nombreEspacio) like lower(concat('%',:nombreEspacio, '%'))")
	public Page<Espacio> getEspaciosByTagName(@Param("nombreEspacio") String nombreEspacio, Pageable pagerequest);
	
	@Query("from #{#entityName} e where e.deleted=true and lower(e.nombreEspacio) like lower(concat('%',:nombreEspacio, '%'))")
	public Page<Espacio> getEspaciosEliminadosByTagName(@Param("nombreEspacio") String nombreEspacio, Pageable pagerequest);
	
	@Query("from #{#entityName} e where lower(e.nombreEspacio) like lower(concat('%',:nombreEspacio, '%'))")
	public List<Espacio> getEspaciosByTagName(@Param("nombreEspacio") String nombreEspacio);

	@Query("from #{#entityName} e where e.deleted=true and lower(e.nombreEspacio) like lower(concat('%',:nombreEspacio, '%'))")
	public List<Espacio> getEspaciosEliminadosByTagName(@Param("nombreEspacio") String nombreEspacio);

	//Por Facultad y TagName de Nombre
	@Query("from #{#entityName} e where e.deleted=false and e.edificio.facultad.id = :idFacultad and lower(e.nombreEspacio) like lower(concat('%',:nombreEspacio, '%'))")
	public Page<Espacio> getEspaciosByTagNameAndFacultad(@Param("nombreEspacio") String nombreEspacio, @Param("idFacultad") Long idFacultad, Pageable pagerequest);
	
	@Query("from #{#entityName} e where e.deleted=true and e.edificio.facultad.id = :idFacultad and lower(e.nombreEspacio) like lower(concat('%',:nombreEspacio, '%'))")
	public Page<Espacio> getEspaciosEliminadosByTagNameAndFacultad(@Param("nombreEspacio") String nombreEspacio, @Param("idFacultad") Long idFacultad, Pageable pagerequest);
	
	@Query("from #{#entityName} e where e.deleted=false and e.edificio.facultad.id = :idFacultad and lower(e.nombreEspacio) like lower(concat('%',:nombreEspacio, '%'))")
	public List<Espacio> getEspaciosByTagNameAndFacultad(@Param("nombreEspacio") String nombreEspacio, @Param("idFacultad") Long idFacultad);

	@Query("from #{#entityName} e where e.deleted=true and e.edificio.facultad.id = :idFacultad and lower(e.nombreEspacio) like lower(concat('%',:nombreEspacio, '%'))")
	public List<Espacio> getEspaciosEliminadosByTagNameAndFacultad(@Param("nombreEspacio") String nombreEspacio, @Param("idFacultad") Long idFacultad);
	
	
	//Por TagName de Edificio
	@Query("select e from #{#entityName} e where e.deleted=false and e.edificio.nombreEdificio like lower(concat('%',:nombre, '%'))")
	public Page<Espacio> getEspaciosPorEdificio(@Param("nombre") String nombre, Pageable pagerequest);
	
	@Query("select e from #{#entityName} e where e.deleted=true and e.edificio.nombreEdificio like lower(concat('%',:nombre, '%'))")
	public Page<Espacio> getEspaciosEliminadosPorEdificio(@Param("nombre") String nombre, Pageable pagerequest);

	@Query("select e from #{#entityName} e where e.deleted=true")
	public Page<Espacio> getEspaciosEliminadosPaginados(Pageable page);

	
	@Query("select e from #{#entityName} e where e.deleted=false and e.edificio.nombreEdificio like lower(concat('%',:nombre, '%'))")
	public List<Espacio> getEspaciosPorEdificio(@Param("nombre") String nombre);
	
	@Query("select e from #{#entityName} e where e.deleted=true and e.edificio.nombreEdificio like lower(concat('%',:nombre, '%'))")
	public List<Espacio> getEspaciosEliminadosPorEdificio(@Param("nombre") String nombre);
	
	//Por Facultad y TagName de Edificio
	@Query("select e from #{#entityName} e where e.deleted=false and e.edificio.facultad.id = :idFacultad and e.edificio.nombreEdificio like lower(concat('%',:nombre, '%'))")
	public Page<Espacio> getEspaciosPorEdificioYFacultad(@Param("nombre") String nombre, @Param("idFacultad") Long idFacultad, Pageable pagerequest);
	
	@Query("select e from #{#entityName} e where e.deleted=true and e.edificio.facultad.id = :idFacultad and e.edificio.nombreEdificio like lower(concat('%',:nombre, '%'))")
	public Page<Espacio> getEspaciosEliminadosPorEdificioYFacultad(@Param("nombre") String nombre, @Param("idFacultad") Long idFacultad, Pageable pagerequest);
		
	@Query("select e from #{#entityName} e where e.deleted=false and e.edificio.facultad.id = :idFacultad and e.edificio.nombreEdificio like lower(concat('%',:nombre, '%'))")
	public List<Espacio> getEspaciosPorEdificioYFacultad(@Param("nombre") String nombre, @Param("idFacultad") Long idFacultad);
		
	@Query("select e from #{#entityName} e where e.deleted=true and e.edificio.facultad.id = :idFacultad and e.edificio.nombreEdificio like lower(concat('%',:nombre, '%'))")
	public List<Espacio> getEspaciosEliminadosPorEdificioYFacultad(@Param("nombre") String nombre, @Param("idFacultad") Long idFacultad);
		
	
	
	//////////////////JAVIER////////////////////////////
	@Query("select f from Espacio f where f.id = :idEspacio")
	public Espacio findEspacio(@Param("idEspacio") long idEspacio);
	//Por Facultad
	@Query("select f from Espacio f where f.deleted=false and f.edificio.facultad.id = :idFacultad")
	public Page<Espacio> findEspacioByFacultadId(@Param("idFacultad") long idFacultad, Pageable pageable);

	@Query("select f from Espacio f where f.deleted=true and f.edificio.facultad.id = :idFacultad")
	public List<Espacio> findEspacioDeletedByFacultadId(@Param("idFacultad") long idFacultad);

	@Query("select f from Espacio f where f.deleted=false and f.edificio.facultad.id = :idFacultad")
	public List<Espacio> findEspacioByFacultadId(@Param("idFacultad") long idFacultad);

	@Query("select f from Espacio f where f.deleted=true and f.edificio.facultad.id = :idFacultad")
	public Page<Espacio> findEspacioDeletedByFacultadId(@Param("idFacultad") long idFacultad, Pageable pageable);
}
