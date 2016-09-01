package es.fdi.reservas.reserva.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.fdi.reservas.reserva.business.boundary.GestorService;
import es.fdi.reservas.reserva.business.boundary.GrupoReservaService;
import es.fdi.reservas.reserva.business.boundary.ReservaService;
import es.fdi.reservas.reserva.business.entity.EstadoReserva;
import es.fdi.reservas.reserva.business.entity.Reserva;
import es.fdi.reservas.users.business.boundary.UserService;
import es.fdi.reservas.users.business.entity.User;

@PreAuthorize("hasRole('ROLE_GESTOR')")
@Controller
public class GestorController {

	private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);
	
	private GestorService gestor_service;
	
	@Autowired
	public GestorController(GestorService gs){
		gestor_service = gs;
	}
	
	@RequestMapping({"/gestor/mis-reservas"})
    public String misReservas() {
        return "redirect:/gestor/mis-reservas/page/1";
    }
	
	@RequestMapping({"/gestor","/gestor/gestion-reservas"})
    public String gestionReservas() {
        return "redirect:/gestor/gestion-reservas/page/1";
    }
	
	@RequestMapping(value="/gestor/mis-reservas/page/{pageNumber}", method=RequestMethod.GET)
    public String misReservasPaginadas(@PathVariable Integer pageNumber, Model model) {
		User u = gestor_service.getUsuarioActual();
		
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 7, new Sort(new Sort.Order(Sort.Direction.ASC,"comienzo")));
        Page<Reserva> currentResults = gestor_service.getReservasByUserId(u.getId(), u.getFacultad().getId(), pageRequest);
        
        model.addAttribute("currentResults", currentResults);
    
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current); 
		model.addAttribute("view", "mis-reservas");
		model.addAttribute("User", u);
		model.addAttribute("GruposReservas", gestor_service.getGrupoReservaByUserId(u.getId()));
		model.addAttribute("view", "mis-reservas");
		
        return "index";
    }
	
	@RequestMapping(value="/gestor/gestion-reservas/page/{pageNumber}", method=RequestMethod.GET)
    public String gestiona_reservas(@PathVariable Integer pageNumber, Model model) {
		User u= gestor_service.getUsuarioActual();
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 7, new Sort(new Sort.Order(Sort.Direction.ASC,"comienzo")));
        Page<Reserva> currentResults = gestor_service.getReservasByFacultadId(u.getFacultad().getId(), pageRequest);
        
        model.addAttribute("currentResults", currentResults);
    
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current); 
		model.addAttribute("User", u);
		model.addAttribute("GruposReservas", gestor_service.getGrupoReservaByUserId(u.getId()));
		model.addAttribute("view", "gestor/gestion-reservas");
		
        return "index";
    }
	
	@RequestMapping(value="/gestor/gestion-reservas/user/{user}/page/{pageNumber}", method=RequestMethod.GET)
    public String gestiona_reservas_usuario(@PathVariable Long user, @PathVariable Integer pageNumber, Model model) {
		User u = gestor_service.getUsuarioActual();
		
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
        Page<Reserva> currentResults = gestor_service.getReservasByUserId(user, u.getFacultad().getId(), pageRequest);
        
        model.addAttribute("currentResults", currentResults);
    
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current); 
		model.addAttribute("User", u);
		model.addAttribute("view", "gestor/gestion-reservas");
		
        return "index";
    }
	
	@RequestMapping(value="/gestor/gestion-reservas/espacio/{espacio}/page/{pageNumber}", method=RequestMethod.GET)
    public String gestiona_reservas_sala(@PathVariable Long espacio, @PathVariable Integer pageNumber, Model model) {
		User u = gestor_service.getUsuarioActual();
		
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
        Page<Reserva> currentResults = gestor_service.getReservasByEspacioId(espacio, u.getFacultad().getId(), pageRequest);
        
        model.addAttribute("currentResults", currentResults);
    
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current); 
		model.addAttribute("User", u);
		model.addAttribute("view", "gestor/gestion-reservas");
		
        return "index";
    }
	
	@RequestMapping(value="/gestor/gestion-reservas/estado/{estado}/page/{pageNumber}", method=RequestMethod.GET)
    public String gestiona_reservas_sala(@PathVariable String estado, @PathVariable Integer pageNumber, Model model) {
		User u = gestor_service.getUsuarioActual();
		
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
		String estadoAlt;
		char char0= estado.charAt(0);
		if (char0=='c' || char0=='C')
			estadoAlt="Confirmada";
		else if (char0=='p' || char0=='P')
			estadoAlt="Pendiente";
		else if (char0=='d' || char0=='D')
			estadoAlt="Denegada";
		else
			estadoAlt="Otro";
        Page<Reserva> currentResults = gestor_service.getReservasByEstadoReserva(EstadoReserva.fromEstadoReserva(estadoAlt), u.getFacultad().getId(), pageRequest);
        
        model.addAttribute("currentResults", currentResults);
    
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current); 
		model.addAttribute("User", u);
		model.addAttribute("view", "gestor/gestion-reservas");
		
        return "index";
    }
	
	@RequestMapping(value="/gestor/editar/{idReserva}", method=RequestMethod.GET)
    public String edita_reservas_gestor(@PathVariable Long idReserva, Model model) {
		
		User u = gestor_service.getUsuarioActual();
		List<EstadoReserva> lista= EstadoReserva.getAll();
		model.addAttribute("User", u);	
		model.addAttribute("Reserva", gestor_service.getReserva(idReserva));
		model.addAttribute("EstadosReserva", lista);
		Long id= gestor_service.getReserva(idReserva).getUser().getId();
		model.addAttribute("GruposReservas", gestor_service.getGrupoReservaByUserId(u.getId()));
		model.addAttribute("GruposReservasUser", gestor_service.getGrupoReservaByUserId(id));
		model.addAttribute("view", "gestor/editarReserva");
		

        return "index";
    }
}
