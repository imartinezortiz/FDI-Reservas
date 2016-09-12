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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.fdi.reservas.fileupload.business.entity.Attachment;
import es.fdi.reservas.reserva.business.boundary.EspacioService;
import es.fdi.reservas.reserva.business.entity.Edificio;
import es.fdi.reservas.reserva.business.entity.Espacio;
import org.springframework.web.servlet.ModelAndView;

import es.fdi.reservas.reserva.business.boundary.ReservaService;
import es.fdi.reservas.reserva.business.entity.Espacio;
import es.fdi.reservas.users.business.boundary.UserService;
import es.fdi.reservas.users.business.entity.User;
import es.fdi.reservas.users.web.UserDTO;

@RestController
public class EspaciosRestController {

	private EspacioService espacio_service;
	
	@Autowired
	public EspaciosRestController(EspacioService es){
		espacio_service = es;
	}
	
	@RequestMapping(value = "/espacio/{idEspacio}", method = RequestMethod.GET)
	public EspacioDTO espacio(@PathVariable("idEspacio") long idEspacio) {
		Espacio e = espacio_service.getEspacio(idEspacio);
		
		return EspacioDTO.fromEspacioDTO(e);
	}
	
	@RequestMapping(value = "/espacio/{idEspacio}", method = RequestMethod.DELETE)
	public void eliminarEspacios(@PathVariable("idEspacio") long idEspacio) {
		espacio_service.editarEspacioDeleted(idEspacio);
	}
	
	@RequestMapping(value = "/admin/administrar/espacio/editar/{idEspacio}", method = RequestMethod.PUT)
	public void editarEspacios(@PathVariable("idEspacio") long idEspacio, @RequestBody EspacioDTO espacioDTO) {
	
		espacio_service.editarEspacio(espacioDTO);

	}
	
	@RequestMapping(value = "/gestor/administrar/espacio/editar/{idEspacio}", method = RequestMethod.PUT)
	public String editarEspaciosGestor(@PathVariable("idEspacio") long idEspacio, @RequestBody EspacioDTO espacioActualizado) {
		
		Attachment attachment = new Attachment("");
		if (espacioActualizado.getImagen().equals("")){
			attachment = espacio_service.getEspacio(espacioActualizado.getId()).getImagen();
		}
		else {
//			String img = "/img/users/" + user_service.getUser(idUser).getUsername();
//			String nombreViejo = user_service.getUser(idUser).getUsername();
//			String nombreNuevo = userActualizado.getUsername();
//			
//			if (!nombreViejo.equalsIgnoreCase(nombreNuevo)){
//				//si el nombre de usuario ha cambiado, hay que renombrar el directorio y las referencias
//				//File dirViejo = new File("../src/main/webapp/img/"  + nombreViejo);
//				File dirNuevo = new File("../../img/"  + nombreNuevo);
//				boolean correcto = dirNuevo.mkdir();
//				
//			}
			
			if (espacio_service.getAttachmentByName(espacioActualizado.getImagen()).isEmpty()){
		
				//si no esta, lo añado
								
				attachment.setAttachmentUrl("/img/" + espacioActualizado.getImagen());
				attachment.setStorageKey(espacio_service.getEspacio(idEspacio).getNombreEspacio() + "/" + espacioActualizado.getImagen());
				//reserva_service.addAttachment(attachment);
			}else{
				attachment = espacio_service.getAttachmentByName(espacioActualizado.getImagen()).get(0);
			}
		}
		espacio_service.editarEspacioGestor(espacioActualizado, attachment);
		//System.out.println(imagen + " Existe");
//	}else{
//		System.out.println(imagen + " No existe");
//	}
		
		//espacio_service.editarEspacio(espacioActualizado);
		return "redirect:/gestor/administrar/espacios/1";
	}
	
	
	@RequestMapping(value = "/admin/administrar/restaurar/espacio/{idEspacio}", method = RequestMethod.DELETE)
	public void restaurarEspacio(@PathVariable("idEspacio") Long idEspacio){
		espacio_service.restaurarEspacio(idEspacio);
	
	}
	
	@RequestMapping(value = "/gestor/administrar/espacio/restaurar/{idEspacio}", method = RequestMethod.DELETE)
	public String restaurarEspacioGestor(@PathVariable("idEspacio") Long idEspacio){
		espacio_service.restaurarEspacio(idEspacio);
		return "redirect:/gestor/administrar/espacio/eliminados/page/1";
	}
	
	@RequestMapping(value="/admin/nuevoEspacio", method=RequestMethod.POST)
	public void crearEspacio(@RequestBody EspacioDTO e){
		 espacio_service.addNewEspacio(e);
	}
	
	@RequestMapping(value="/gestor/nuevoEspacio", method=RequestMethod.POST)
	public void crearEspacioGestor(@RequestBody EspacioDTO e){
		espacio_service.addNewEspacio(e);
	}
	
	@RequestMapping(value = "/admin/edificio/tag/{tagName}", method = RequestMethod.GET)
	public List<EdificioDTO> edificioFiltroAutocompletar(@PathVariable("tagName") String tagName) {

		List<EdificioDTO> result = new ArrayList<>();
		List<Edificio> usuarios = new ArrayList<>();

		usuarios = espacio_service.getEdificiosPorTagName(tagName);

		for (Edificio u : usuarios) {
			result.add(EdificioDTO.fromEdificioDTOAutocompletar(u));
		}

		return result;
	}
	
	@RequestMapping(value = "/gestor/edificio/tag/{tagName}", method = RequestMethod.GET)
	public List<EdificioDTO> edificioFiltroAutocompletarGestor(@PathVariable("tagName") String tagName) {

		List<EdificioDTO> result = new ArrayList<>();
		List<Edificio> usuarios = new ArrayList<>();

		usuarios = espacio_service.getEdificiosPorTagName(tagName);

		for (Edificio u : usuarios) {
			result.add(EdificioDTO.fromEdificioDTOAutocompletar(u));
		}

		return result;
	}
	
//	@RequestMapping(value = "/admin/espacio/tag/{tagName}", method = RequestMethod.GET)
//	public List<EspacioDTO> espacioFiltroAutocompletar(@PathVariable("tagName") String tagName) {
//
//		List<EspacioDTO> result = new ArrayList<>();
//		List<Espacio> usuarios = new ArrayList<>();
//
//		usuarios = espacio_service.getEspaciosPorTagName(tagName);
//
//		for (Espacio u : usuarios) {
//			result.add(EspacioDTO.fromEspacioDTOAutocompletar(u));
//		}
//
//		return result;
//	}
//	
//	@RequestMapping(value = "/admin/espacio/edificio/tag/{tagName}", method = RequestMethod.GET)
//	public List<EspacioDTO> espacioEdificioFiltroAutocompletar(@PathVariable("tagName") String tagName) {
//
//		List<EspacioDTO> result = new ArrayList<>();
//		List<Espacio> usuarios = new ArrayList<>();
//
//		usuarios = espacio_service.getEspaciosPorEdificio(tagName);
//
//		for (Espacio u : usuarios) {
//			result.add(EspacioDTO.fromEspacioDTOAutocompletar(u));
//		}
//
//		return result;
//	}
}
