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
package es.fdi.reservas.users.business.boundary;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import es.fdi.reservas.fileupload.business.control.AttachmentRepository;
import es.fdi.reservas.fileupload.business.entity.Attachment;
import es.fdi.reservas.reserva.business.entity.Facultad;
import es.fdi.reservas.reserva.business.boundary.ReservaService;
import es.fdi.reservas.reserva.business.entity.Espacio;
import es.fdi.reservas.reserva.business.entity.EstadoReserva;
import es.fdi.reservas.reserva.business.entity.GrupoReserva;
import es.fdi.reservas.reserva.business.entity.Reserva;
import es.fdi.reservas.users.business.control.UserRepository;
import es.fdi.reservas.users.business.entity.User;
import es.fdi.reservas.users.web.UserDTO;
import es.fdi.reservas.users.business.entity.UserRole;

@Service
public class UserService implements UserDetailsService{

	private UserRepository user_ropository;
	private PasswordEncoder password_encoder;
	private ReservaService reserva_service;	
	private AttachmentRepository attachment_repository;
	
	@Autowired
	public UserService(UserRepository usuarios, PasswordEncoder passwordEncoder, AttachmentRepository ar){
		user_ropository = usuarios;
		password_encoder = passwordEncoder;
		attachment_repository = ar;
	}

	
	@Autowired @Lazy
	public void setReservaService(ReservaService rs){
		reserva_service = rs;
	}
	
	public User getUser(Long idUsuario) {
		return user_ropository.findOne(idUsuario);
	}
	
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ( principal instanceof User) {
			return (User) principal;
		}
		return null;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = user_ropository.findByEmail(username);
		if (user == null)  {
			user = user_ropository.findByUsername(username);
		}
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s not found", username));
		}
		
		return user;
	}
	
	public User addNewUser(UserDTO user){
		User newUser = new User(user.getUsername(), user.getEmail());
		newUser.setFacultad(reserva_service.getFacultad(user.getFacultad()));
		newUser.setImagen(attachment_repository.findOne((long) 2));
		newUser.setEnabled(true);
		newUser.addRole(new UserRole("ROLE_USER"));
		newUser.setPassword(password_encoder.encode(user.getPassword()));
		newUser = user_ropository.save(newUser);
		
		return newUser;
	}
	
	public Iterable<User> getUsuarios() {
		return user_ropository.findAll();
	}
	
	public void eliminarUsuario(long idUser) {
		user_ropository.delete(idUser);
	}
	
	public User editarUserDeleted(Long idUsuario){
		User u = getUser(idUsuario);
		u.setEnabled(false);
		
		return user_ropository.save(u);
	}

	public User editaUsuario(UserDTO userActualizado, String user, String admin, String gestor, Attachment imagen) {
		
		User u = getUser(userActualizado.getId());
		u.setUsername(userActualizado.getUsername());
		u.setEmail(userActualizado.getEmail());
		Facultad fac = reserva_service.getFacultad(userActualizado.getFacultad());
		u.setFacultad(fac);
		u.setImagen(imagen);
		attachment_repository.save(imagen);
		if (user.equals("true") || admin.equals("true") || gestor.equals("true")){//si hay alguno seleccionado
			u.getAuthorities().clear();
			if (user.equals("true")){
				u.addRole(new UserRole("ROLE_USER"));
			}
			if (admin.equals("true")){
				u.addRole(new UserRole("ROLE_ADMIN"));
			}
			if (gestor.equals("true")){
				u.addRole(new UserRole("ROLE_GESTOR"));
			}
		}
		return user_ropository.save(u);
		
		
	}
	
	public Page<User> getUsuariosPaginados(PageRequest pageRequest) {
		return user_ropository.findAll(pageRequest);
	}

	public User restaurarUser(long idUsuario) {
		User u = getUser(idUsuario);
		u.setEnabled(true);
		
		return user_ropository.save(u);		
	}

	public List<User> getEliminados() {		
		return user_ropository.recycleBin();
	}

	public List<User> getUsuariosPorTagName(String tagName) {
		return user_ropository.getUsuariosPorTagName(tagName);
	}

	public void editarPerfil(UserDTO userDTO) {
		User user = getUser(userDTO.getId());
		user.setUsername(userDTO.getUsername());
		user.setEmail(userDTO.getEmail());
		if(userDTO.getOldPassword() != null){
			// Cambiar las contraseñas
			if(password_encoder.matches(userDTO.getOldPassword(),user.getPassword())){
				user.setPassword(password_encoder.encode(userDTO.getNewPassword()));
			}
			else{
				// Error: las contraseñas no coinciden
				throw new UserPasswordException("La contraseña actual no es correcta");
			}
		}
		
				
		
		//actualiza la imagen
		Attachment attachment = new Attachment("");
		if (userDTO.getImagen().equals("")){
			attachment = user_ropository.findOne(userDTO.getId()).getImagen();
		}
		else if(userDTO.getImagen().contains("fakepath")){
			String img = userDTO.getImagen();
			String[] barras = img.split("fakepath");
			String fin = barras[1];
			fin = fin.substring(1);
			
			if (attachment_repository.getAttachmentByName(fin).isEmpty()){
				int pos = fin.lastIndexOf(".");
				String punto = fin.substring(0, pos);
				String end = fin.substring(pos+1, fin.length());
				String nom = punto + "-" + userDTO.getId() + "." + end;
				nom = nom.replace(nom, "/img/" + nom);
				
				
				attachment.setAttachmentUrl("/img/" + fin);
				attachment.setStorageKey(nom);
				attachment_repository.save(attachment);
			}
			else{
				attachment = attachment_repository.getAttachmentByName(fin).get(0);
			}
		}
		else {
			if (attachment_repository.getAttachmentByName(userDTO.getImagen()).isEmpty()){
		
				//si no esta, lo añado
				String img = userDTO.getImagen();
				int pos = img.lastIndexOf(".");
				String punto = img.substring(0, pos);
				String fin = img.substring(pos+1, img.length());
				String nom = punto + "-" + userDTO.getId() + "." + fin;
				nom = nom.replace(nom, "/img/" + nom);
				
				
				attachment.setAttachmentUrl("/img/" + userDTO.getImagen());
				attachment.setStorageKey(nom);
				attachment_repository.save(attachment);
				//reserva_service.addAttachment(attachment);
			}else{
				attachment = attachment_repository.getAttachmentByName(userDTO.getImagen()).get(0);
			}
			
		}
		
		user.setImagen(attachment);
		
		// Actualiza el usuario actual sin cerrar sesión
		Authentication request = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());		
		SecurityContextHolder.getContext().setAuthentication(request);
		
		// Guarda los cambios en la base de datos
		user_ropository.save(user);
	}

	public List<Reserva> reservasPendientesUsuario(Long idUsuario, EstadoReserva estadoReserva) {
		return reserva_service.reservasPendientesUsuario(idUsuario, estadoReserva);
	}

	public Iterable<Espacio> getEspacios() {
		return reserva_service.getEspacios();
	}

	public List<GrupoReserva> getGruposUsuario(Long idUsuario) {
		return reserva_service.getGruposUsuario(idUsuario);
	}
	
	public Attachment getAttachment(Long idAttachment){
		return attachment_repository.getOne(idAttachment);
	}

	public List<Attachment> getAttachmentByName(String img) {
		return attachment_repository.getAttachmentByName(img);
	}

	public Page<User> getUsuariosPorEmail(String email, Pageable pagerequest) {
		return user_ropository.getUsuariosPorEmail(email, pagerequest);
	}

	public Page<User> getUsuariosPorNombre(String nombre, Pageable pagerequest) {
		return user_ropository.getUsuariosPorTagName(nombre, pagerequest);
	}

	public Page<User> getUsuariosEliminadosPorFacultad(Long id, Pageable pagerequest) {
		return user_ropository.getUsuariosEliminadosPorFacultadId(id, pagerequest);
	}
	
	public Page<User> getUsuariosPorFacultad(Long id, Pageable pagerequest) {
		return user_ropository.getUsuariosPorFacultadId(id, pagerequest);
	}
	
	public List<User> getUsuariosPorFacultad(String nombre) {
		return user_ropository.getUsuariosPorFacultad(nombre);
	}
	
	public Page<User> getUsuariosPorFacultad(String nombre, Pageable pageable) {
		return user_ropository.getUsuariosPorFacultad(nombre, pageable);
	}
	
	public Page<User> getUsuariosEliminadosPorEmail(String email, Pageable pagerequest) {
		return user_ropository.getUsuariosEliminadosPorEmail(email, pagerequest);
	}

	public Page<User> getUsuariosEliminadosPorNombre(String nombre, Pageable pagerequest) {
		return user_ropository.getUsuariosEliminadosPorTagName(nombre, pagerequest);
	}

	public Page<User> getUsuariosEliminadosPorFacultad(String nombre, Pageable pagerequest) {
		return user_ropository.getUsuariosEliminadosPorFacultad(nombre, pagerequest);
	}

	public void actualizaReferencias(String nombreViejo, String nombreNuevo) {
		List<Attachment> imgs = attachment_repository.getAttachmentByName(nombreViejo);
		Iterator<Attachment> it = imgs.iterator();
		Attachment i;
		while (it.hasNext()){
			i = it.next();
			i.setAttachmentUrl(i.getAttachmentUrl().replace(nombreViejo, nombreNuevo));
			attachment_repository.save(i);
		}
		
	}

	public Page<User> getUsuariosPorNombreYFacultad(String nombre, Long id, Pageable pageable) {
		return user_ropository.getUsuariosPorNombreYFacultad(nombre,id, pageable);
	}
	
	public Page<User> getUsuariosEliminadosPaginados(Pageable pageRequest) {
		return user_ropository.recycleBin(pageRequest);
	}
	
	public User addNewUserLogin(User user) {
		User userByName = user_ropository.findByUsername(user.getUsername());
		
		if(userByName == null){
			userByName = user_ropository.findByEmail(user.getEmail());
		}
		
		if(userByName != null){
			return null;
		}
			
		// Imagen por defecto
		user.setImagen(attachment_repository.findOne((long) 10));
		// Facultad por defecto
		user.setFacultad(reserva_service.getFacultad((long) 1));
		user.addRole(new UserRole("ROLE_USER"));
		user.setEnabled(true);
		user.setPassword(password_encoder.encode(user.getPassword()));
		user = user_ropository.save(user);
		
		return user;	
	}
	
	public Page<User> getUsuariosPorEmailYFacultad(String nombre, Long id, Pageable pageable) {
		return user_ropository.getUsuariosPorEmailYFacultad(nombre,id, pageable);
	}

	public Page<User> getUsuariosBorradosPorNombreYFacultad(String nombre, Long id, Pageable pageable) {
		return user_ropository.getUsuariosBorradosPorNombreYFacultad(nombre, id, pageable);
	}

	public Page<User> getUsuariosBorradosPorEmailYFacultad(String email, Long id, Pageable pageable) {
		return user_ropository.getUsuariosBorradosPorNombreYFacultad(email, id, pageable);
	}


	public void addImagen(Attachment attachment) {
		attachment_repository.save(attachment);
		
	}

}
