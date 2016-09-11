package es.fdi.reservas.users.business.control;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import es.fdi.reservas.users.business.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("select e from #{#entityName} e where e.enabled=true")
	List<User> findAll();
	
	public User findByEmail(String username); 
	
	public User findByUsername(String username);

	@Query("select e from #{#entityName} e where e.enabled=false")
	public Page<User> recycleBin(Pageable pagerequest);
	
	@Query("select e from #{#entityName} e where e.enabled=false")
	public List<User> recycleBin();
	
	@Query("from User u where lower(u.username) like lower(concat('%',:username, '%'))")
	Page<User> getUsuariosPorTagName(@Param("username") String username, Pageable pagerequest);
	
	@Query("from User u where u.enabled=false and lower(u.username) like lower(concat('%',:username, '%'))")
	Page<User> getUsuariosEliminadosPorTagName(@Param("username") String username, Pageable pagerequest);
	
	@Query("from User u where lower(u.username) like lower(concat('%',:username, '%'))")
	List<User> getUsuariosPorTagName(@Param("username") String username);

	@Query("select e from #{#entityName} e where e.enabled=true and e.facultad.nombreFacultad like lower(concat('%',:nombre, '%'))")
	public Page<User> getUsuariosPorFacultad(@Param("nombre") String nombre, Pageable pagerequest);
	
	@Query("select e from #{#entityName} e where e.enabled=false and e.facultad.nombreFacultad like lower(concat('%',:nombre, '%'))")
	Page<User> getUsuariosEliminadosPorFacultad(@Param("nombre") String nombre, Pageable pagerequest);

	@Query("from User u where lower(u.email) like lower(concat('%',:email, '%'))")
	Page<User> getUsuariosPorEmail(@Param("email") String email, Pageable pagerequest);
	
	@Query("from User u where u.enabled=false and lower(u.email) like lower(concat('%',:email, '%'))")
	Page<User> getUsuariosEliminadosPorEmail(@Param("email") String email, Pageable pagerequest);
	
	@Query("select e from #{#entityName} e where e.enabled=true and e.facultad.nombreFacultad like lower(concat('%',:nombre, '%'))")
	List<User> getUsuariosPorFacultad(@Param("nombre") String nombre);

	@Query("from User u where lower(u.email) like lower(concat('%',:email, '%'))")
	List<User> getUsuariosPorEmail(@Param("email") String email);
	
	@Query("select e from #{#entityName} e where e.enabled=true and e.facultad.id = :facultadId")
	Page<User> getUsuariosPorFacultadId(@Param("facultadId") Long facultadId, Pageable pagerequest);
	
	@Query("select e from #{#entityName} e where e.enabled=false and e.facultad.id = :facultadId")
	Page<User> getUsuariosEliminadosPorFacultadId(@Param("facultadId") Long facultadId, Pageable pagerequest);
	
	@Query("select e from #{#entityName} e where e.enabled=true and e.facultad.id = :idFacultad and lower(e.username) like lower(concat('%',:username, '%'))")
	public Page<User> getUsuariosPorNombreYFacultad(@Param("username")String username, @Param("idFacultad")Long idFacultad, Pageable pageable);

	@Query("select e from #{#entityName} e where e.enabled=true and e.facultad.id = :idFacultad and lower(e.email) like lower(concat('%',:email, '%'))")
	public Page<User> getUsuariosPorEmailYFacultad(@Param("email")String email, @Param("idFacultad")Long idFacultad, Pageable pageable);

	@Query("select e from #{#entityName} e where e.enabled=false and e.facultad.id = :idFacultad and lower(e.username) like lower(concat('%',:username, '%'))")
	public Page<User> getUsuariosBorradosPorNombreYFacultad(@Param("username") String nombre,  @Param("idFacultad")Long id, Pageable pageable);
	
	@Query("select e from #{#entityName} e where e.enabled=false and e.facultad.id = :idFacultad and lower(e.email) like lower(concat('%',:email, '%'))")
	public Page<User> getUsuarioBorradossPorEmailYFacultad(@Param("email") String email, @Param("idFacultad")Long idFacultad, Pageable pageable);

	
}
