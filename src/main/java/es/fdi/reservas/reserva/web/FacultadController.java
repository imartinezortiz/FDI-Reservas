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
package es.fdi.reservas.reserva.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import es.fdi.reservas.reserva.business.boundary.FacultadService;
import es.fdi.reservas.reserva.business.entity.EstadoReserva;
import es.fdi.reservas.reserva.business.entity.Facultad;
import es.fdi.reservas.users.business.entity.User;

@Controller
public class FacultadController {


	private FacultadService facultad_service;
	
	@Autowired
	public FacultadController(FacultadService fs){
		facultad_service = fs;
	}
	
	@RequestMapping(value="/admin/administrar/facultad")
	public String espacios(){
		return "redirect:/admin/administrar/facultad/page/1";
	}
	
	@RequestMapping(value="/admin/administrar/facultad/page/{pageNumber}", method=RequestMethod.GET)
    public String misFacultadesPaginadas(@PathVariable Integer pageNumber, Model model) {
		User u = facultad_service.getCurrentUser();
		
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
        Page<Facultad> currentResults = facultad_service.getFacultadesPaginadas(pageRequest);
                
        model.addAttribute("currentResults", currentResults);
        
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("reservasPendientes", facultad_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current); 
		model.addAttribute("User", u);
		model.addAttribute("view", "admin/administrar_facultad");
		
        return "index";
    }
	
	/*
	 * Filtrar por nombre
	 */
	@RequestMapping(value="/admin/administrar/facultad/nombre/{nombre}/page/{pageNumber}", method=RequestMethod.GET)
    public String misFacultadesPaginadasPorNombre(@PathVariable Integer pageNumber, Model model, @PathVariable String nombre) {
		User u = facultad_service.getCurrentUser();
		
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
        Page<Facultad> currentResults = facultad_service.getFacultadesPorTagName(nombre, pageRequest);
               
        model.addAttribute("currentResults", currentResults);
        
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages());

        model.addAttribute("reservasPendientes", facultad_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
		model.addAttribute("User", u);
		model.addAttribute("view", "admin/filtrar_facultad");
		
        return "index";
    }
	
	@RequestMapping(value="/admin/administrar/facultad/restaurar/nombre/{nombre}/page/{pageNumber}", method=RequestMethod.GET)
    public String misFacultadesPaginadasPorNombreRestaurar(@PathVariable Integer pageNumber, Model model, @PathVariable String nombre) {
		User u = facultad_service.getCurrentUser();
		
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
        Page<Facultad> currentResults = facultad_service.getFacultadesEliminadasPorTagName(nombre, pageRequest);
               
        model.addAttribute("currentResults", currentResults);
        
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages());

        model.addAttribute("reservasPendientes", facultad_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
		model.addAttribute("User", u);
		model.addAttribute("view", "admin/papelera_facultades");
		
        return "index";
    }
	
	/*
	 * Filtrar por web
	 */
	
	@RequestMapping(value="/admin/administrar/facultad/web/{nombre}/page/{pageNumber}", method=RequestMethod.GET)
    public String misFacultadesPaginadasPorWeb(@PathVariable Integer pageNumber, Model model, @PathVariable String nombre) {
		User u = facultad_service.getCurrentUser();
		
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
		Page<Facultad> currentResults = facultad_service.getFacultadesPorWeb(nombre, pageRequest);
                
        model.addAttribute("currentResults", currentResults);
        
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("reservasPendientes", facultad_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
		model.addAttribute("User", u);
		model.addAttribute("view", "admin/filtrar_facultad");
		
        return "index";
    }
	
	@RequestMapping(value="/admin/administrar/facultad/restaurar/web/{nombre}/page/{pageNumber}", method=RequestMethod.GET)
    public String misFacultadesPaginadasPorWebRestaurar(@PathVariable Integer pageNumber, Model model, @PathVariable String nombre) {
		User u = facultad_service.getCurrentUser();
		
		PageRequest pageRequest = new PageRequest(pageNumber - 1, 5);
		Page<Facultad> currentResults = facultad_service.getFacultadesEliminadasPorWeb(nombre, pageRequest);
                
        model.addAttribute("currentResults", currentResults);
        
        int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 

        model.addAttribute("reservasPendientes", facultad_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
		model.addAttribute("User", u);
		model.addAttribute("view", "admin/papelera_facultades");
		
        return "index";
    }
	
	@RequestMapping(value="/admin/administrar/facultad/editar/{idFacul}", method=RequestMethod.GET)
	public String editarFacultad(@PathVariable("idFacul") long idFacul, Model model){
		User u = facultad_service.getCurrentUser();
		model.addAttribute("User", u);
		model.addAttribute("facultad", facultad_service.getFacultad(idFacul));
		model.addAttribute("reservasPendientes", facultad_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
		model.addAttribute("view", "admin/editarFacultad");
		return "index";
	}
	
	@RequestMapping(value="/admin/nuevaFacultad",method=RequestMethod.GET)
	public String nuevaFacultad(Model model){

		//return new ModelAndView("admin/nuevaFacultad", "Facultad", new Facultad());
		User u = facultad_service.getCurrentUser();
		model.addAttribute("User", u);
		model.addAttribute("Facultad", new Facultad());
		model.addAttribute("reservasPendientes", facultad_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
		model.addAttribute("view", "admin/nuevaFacultad");
		return "index";
	}

	@RequestMapping(value = "/admin/administrar/facultad/restaurar/page/{numPag}",method=RequestMethod.GET)
	public String restaurarFacultades(@PathVariable("numPag") Integer numPag, Model model){
		
		User u = facultad_service.getCurrentUser();
		
		PageRequest pageRequest = new PageRequest(numPag - 1, 5);
		Page<Facultad> currentResults = facultad_service.getFacultadesEliminadasPaginadas(pageRequest);

		
		int current = currentResults.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, currentResults.getTotalPages()); 
		
		model.addAttribute("currentResults", currentResults);
		model.addAttribute("User", u);
		model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
		model.addAttribute("reservasPendientes", facultad_service.reservasPendientesUsuario(u.getId(), EstadoReserva.PENDIENTE).size());
		model.addAttribute("pagina", numPag);
		model.addAttribute("view", "admin/papelera_facultades");
		
		return "index";
	}
	

	@RequestMapping(value = "/facultades/tag/{tagName}", method = RequestMethod.GET)
	public List<FacultadDTO> facultadesFiltro(@PathVariable("tagName") String tagName) {
		
		List<FacultadDTO> result = new ArrayList<>();
		List<Facultad> facultades = new ArrayList<>();

		facultades = facultad_service.getFacultadesPorTagName(tagName);
				
		for(Facultad f : facultades) {
			result.add(FacultadDTO.fromFacultadDTOAutocompletar(f));
		}
		 
		return result;
	}
	
}
