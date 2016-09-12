/*
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
$(document).ready(function(){
	
	var edificio = {};
 	var token = $("meta[name='_csrf']").attr("content");
 	var header = $("meta[name='_csrf_header']").attr("content");
 	var reqHeaders = [];
 	reqHeaders[header] = token;
 	
 	$("#texto-busqueda").keyup(function(){
 		var searchTerm = $('#texto-busqueda').val();
 		tipoBusqueda = $('#selec-busqueda').val();
 		
 		var link = '/reservas/gestor/administrar/edificios/eliminados/' + tipoBusqueda + '/' + searchTerm + '/page/1';
 		$("#busquedaEdificio").attr("href",link);
 		
 	});
 	
 	$('td a').click(function(){
 		
 		
 		edificio.id =  $(this).attr("data-id");
 		var nombreEdificio = $(this).attr("name");
 		var direccion = $(this).attr("dir");
 		var deleted = $(this).attr("act");
 		var facultad = $(this).attr("fac");
 		var imagen = "../../.." + $(this).attr("img");
 		var accion = $(this).attr("data-accion");
 		
 		$('#modalEditarEdificio #idNombre').text(nombreEdificio);
 		$('#modalEditarEdificio #idDir').text(direccion);
 		$('#modalEditarEdificio #idFacul').text(facultad);
 		$('#modalEditarEdificio #idActivado').text(deleted);
 		$('#modalEditarEdificio #idAttachment').attr("src",imagen);
 		$('#modalEditarEdificio #btn-editar').prop("href", baseURL + "gestor/administrar/edificios/editar/" + edificio.id);
 		
 		
 		if (accion == 'Restaurar'){
 			
				modalRestaurarEdificio(edificio, reqHeaders);	
			
 		}else if(accion == 'Ver'){
 		
 			$('#modalEditarEdificio').modal('show');
 		}
 	});
 	
 	$('#selec-busqueda').change(function(){
 		$('#texto-busqueda').val("");
 		if ($(this).val()=="nombre")
 			$('#texto-busqueda').prop("placeholder", "Introduce nombre de edificio");
 		else if ($(this).val()=="web")
 			$('#texto-busqueda').prop("placeholder", "Introduce la dirección web");
 	});
	
});

function modalRestaurarEdificio(edificio, reqHeaders){
		$.ajax({
			url: baseURL + "gestor/administrar/edificio/restaurar/" + edificio.id,
			type: 'DELETE',
			headers : reqHeaders,
			success : function(datos) {
				$('#modalEditarEdificio').modal('hide');
				$("#" + edificio.id).remove();
			
			},    
			error : function(xhr, status) {
				alert('Disculpe, existió un problema');
			}
 	});
 
}