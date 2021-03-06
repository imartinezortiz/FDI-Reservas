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
package es.fdi.reservas.users.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import es.fdi.reservas.reserva.business.entity.EstadoReserva;
import es.fdi.reservas.users.business.boundary.UserService;
import es.fdi.reservas.users.business.entity.User;


@Controller
public class UserController {

	private UserService user_service;	
	
	@Autowired
	public UserController(UserService us){
		user_service = us;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){
	   return "login";
	}

	@RequestMapping(value="/login-error", method=RequestMethod.GET)
    public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
    }
	
	@RequestMapping(value="/registro", method=RequestMethod.GET)
	public String registro(){
		return "registro";
	}
	
	@RequestMapping(value="/registro-error", method=RequestMethod.GET)
    public String registroError(Model model) {
		model.addAttribute("registroError", true);
		return "registro";
    }
	
	@RequestMapping(value="/registroUser", method=RequestMethod.POST)
	public String crearUsuarioLogin(@ModelAttribute User user){
		
		User newUser = user_service.addNewUserLogin(user);
		
		if(newUser == null){
			return "redirect:/registro-error";
		}
		
	    return "redirect:/login";	
	}
	
	@RequestMapping(value ="/admin/administrar/usuarios")
    public String usuarios() {
        return "redirect:/admin/administrar/usuarios/page/1";
    }
	
	@RequestMapping(value="/admin/nuevoUsuario", method=RequestMethod.GET)
	public String nuevoUsuario(Model model){
		
		User u = user_service.getCurrentUser();
		model.addAttribute("User", u);
		model.addAttribute("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
		model.addAttribute("us", new User());
		model.addAttribute("view", "admin/nuevoUsuario");
	   return "index";
	}
	
	
	@RequestMapping(value = "usuario/tag/{tagName}", method = RequestMethod.GET)
	public List<UserDTO> usuariosFiltro(@PathVariable("tagName") String tagName) {
		
		List<UserDTO> result = new ArrayList<>();
		List<User> usuarios = new ArrayList<>();
		
		
		usuarios = user_service.getUsuariosPorTagName(tagName);
		
		
		for(User u : usuarios) {
			result.add(UserDTO.fromUserDTO(u));
		}
		 
		return result;

	}
	
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/admin/administrar",method=RequestMethod.GET)
	public ModelAndView administrar(){
		ModelAndView model = new ModelAndView("index");
		User u = user_service.getCurrentUser();
		model.addObject("User", u);
		model.addObject("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(),EstadoReserva.PENDIENTE).size());
		model.addObject("userList", user_service.getUsuarios());
		model.addObject("view", "administrar_usuarios");
		model.addObject("url","/administrar/usuarios" );

		model.addObject("view", "admin/administrar");

		return model;
	}
	
	@RequestMapping(value="/admin/administrar/usuarios/page/{pageNumber}", method=RequestMethod.GET)
    public String misUsuariosPaginados(@PathVariable Integer pageNumber, Model model) {
		User u = user_service.getCurrentUser();
	
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
        Page<User> currentResults = user_service.getUsuariosPaginados(pageRequest);
        
        model.addAttribute("currentResults", currentResults);

        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPages", currentResults.getTotalPages());
		model.addAttribute("User", u);
		model.addAttribute("view", "admin/administrar_usuarios");
		
        return "index";
    }
	
	/*
	 * Filtro por nombre
	 */
	
	@RequestMapping(value="/admin/administrar/usuarios/nombre/{nombre}/page/{pageNumber}", method=RequestMethod.GET)
    public String misUsuariosPaginadosPorNombre(@PathVariable Integer pageNumber, Model model, @PathVariable String nombre) {
		User u = user_service.getCurrentUser();

		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
		Page<User> currentResults = user_service.getUsuariosPorNombre(nombre,pageRequest);
        
        
        model.addAttribute("currentResults", currentResults);
        
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
        
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
		model.addAttribute("User", u);
		model.addAttribute("view", "admin/filtrar_usuarios");
		
        return "index";
    }
	
	@RequestMapping(value="/admin/administrar/usuarios/restaurar/nombre/{nombre}/page/{pageNumber}", method=RequestMethod.GET)
    public String misUsuariosPaginadosPorNombreRest(@PathVariable Integer pageNumber, Model model, @PathVariable String nombre) {
		User u = user_service.getCurrentUser();

		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
		Page<User> currentResults = user_service.getUsuariosEliminadosPorNombre(nombre,pageRequest);
        
        
        model.addAttribute("currentResults", currentResults);
        
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
        
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
		model.addAttribute("User", u);
		model.addAttribute("view", "admin/papelera_usuarios");
		
        return "index";
    }
	
	/*
	 * Filtrar por email
	 */
	
	@RequestMapping(value="/admin/administrar/usuarios/email/{nombre}/page/{pageNumber}", method=RequestMethod.GET)
    public String misUsuariosPaginadosPorEmail(@PathVariable Integer pageNumber, Model model, @PathVariable String nombre) {
		User u = user_service.getCurrentUser();

	
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
		Page<User> currentResults = user_service.getUsuariosPorEmail(nombre, pageRequest);
        
        model.addAttribute("currentResults", currentResults);
        
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current); 
		model.addAttribute("User", u);
		model.addAttribute("view", "admin/filtrar_usuarios");
		
        return "index";
    }
	
	@RequestMapping(value="/admin/administrar/usuarios/restaurar/email/{nombre}/page/{pageNumber}", method=RequestMethod.GET)
    public String misUsuariosPaginadosPorEmailRest(@PathVariable Integer pageNumber, Model model, @PathVariable String nombre) {
		User u = user_service.getCurrentUser();

	
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
		Page<User> currentResults = user_service.getUsuariosEliminadosPorEmail(nombre, pageRequest);
        
        model.addAttribute("currentResults", currentResults);
        
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current); 
		model.addAttribute("User", u);
		model.addAttribute("view", "admin/papelera_usuarios");
		
        return "index";
    }
	
	/*
	 * Filtrar por facultad
	 */
	@RequestMapping(value="/admin/administrar/usuarios/facultad/{nombre}/page/{pageNumber}", method=RequestMethod.GET)
    public String misUsuariosPaginadosPorFacultad(@PathVariable Integer pageNumber, Model model, @PathVariable String nombre) {
		User u = user_service.getCurrentUser();

	
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
		Page<User> currentResults = user_service.getUsuariosPorFacultad(nombre, pageRequest);
        
        model.addAttribute("currentResults", currentResults);
        
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current); 
		model.addAttribute("User", u);
		model.addAttribute("view", "admin/filtrar_usuarios");
		
        return "index";
    }
	
	@RequestMapping(value="/admin/administrar/usuarios/restaurar/facultad/{nombre}/page/{pageNumber}", method=RequestMethod.GET)
    public String misUsuariosPaginadosPorFacultadRest(@PathVariable Integer pageNumber, Model model, @PathVariable String nombre) {
		User u = user_service.getCurrentUser();

	
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
		Page<User> currentResults = user_service.getUsuariosEliminadosPorFacultad(nombre, pageRequest);
        
        model.addAttribute("currentResults", currentResults);
        
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current); 
		model.addAttribute("User", u);
		model.addAttribute("view", "admin/papelera_usuarios");
		
        return "index";
    }

	
	@RequestMapping(value="/admin/administrar/usuarios/editar/{idUser}", method=RequestMethod.GET)
	public String editarUsuario(@PathVariable("idUser") long idUser, Model model){
		User u = user_service.getCurrentUser();

		model.addAttribute("User", u);
		model.addAttribute("usuario", user_service.getUser(idUser));
		//System.out.println(user_service.getUser(idUser).getUsername());
		model.addAttribute("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
		model.addAttribute("view", "admin/editarUsuario");
		return "index";
	}
	
	@RequestMapping(value = "/admin/administrar/usuarios/restaurar/page/{numPag}",method=RequestMethod.GET)
	public String restaurarUsuarios(@PathVariable("numPag") Integer numPag, Model model){
		
		User u = user_service.getCurrentUser();

		PageRequest pageRequest = new PageRequest(numPag - 1, 5);
		Page<User> currentResults = user_service.getUsuariosEliminadosPaginados(pageRequest);
        
        model.addAttribute("currentResults", currentResults);
        
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 
		
		model.addAttribute("User", u);
		model.addAttribute("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
		model.addAttribute("pagina", numPag);
		model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current); 
		model.addAttribute("view", "admin/papelera_usuarios");
		return "index";
	}	

	@RequestMapping(value="/perfil", method=RequestMethod.GET)
	public ModelAndView verPerfil(){
		ModelAndView model = new ModelAndView("index");
		User u = user_service.getCurrentUser();
		model.addObject("User", u);
		model.addObject("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(),EstadoReserva.PENDIENTE).size());
		model.addObject("GruposReservas", user_service.getGruposUsuario(u.getId()));
		model.addObject("view", "perfil");
		
	   return model;
	}
	
	@RequestMapping(value="/perfil/editar", method=RequestMethod.GET)
	public ModelAndView editarPerfil(){
		ModelAndView model = new ModelAndView("index");
		User u = user_service.getCurrentUser();
		model.addObject("User", u);
		model.addObject("reservasPendientes", user_service.reservasPendientesUsuario(u.getId(),EstadoReserva.PENDIENTE).size());
		model.addObject("GruposReservas", user_service.getGruposUsuario(u.getId()));
		model.addObject("view", "editarPerfil");
		
	   return model;
	}
}
