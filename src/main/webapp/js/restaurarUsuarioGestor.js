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
	var user = {};
 	var token = $("meta[name='_csrf']").attr("content");
 	var header = $("meta[name='_csrf_header']").attr("content");
 	var reqHeaders = [];
 	reqHeaders[header] = token;
 	
 	$("#texto-busqueda").keyup(function(){
 		var searchTerm = $('#texto-busqueda').val();
 		tipoBusqueda = $('#selec-busqueda').val();
 		
 		var link = '/reservas/gestor/administrar/usuarios/eliminados/' + tipoBusqueda + '/' + searchTerm + '/page/1';
 		$("#busquedaUsuario").attr("href",link);
 		
 	});
 	
 	$('td a').click(function(){
 		
 		user.id =  $(this).attr("data-id");
 		var username = $(this).attr("name");
 		var email = $(this).attr("email");
 		var facultad = $(this).attr("facul");
 		var roles = $(this).attr("roles");
 		var enabled = $(this).attr("act");
 		var accion = $(this).attr("data-accion");
 		var imagen = $(this).attr("img")
 		
 		$('#modalEditarUsuario #idNombre').text(username);
 		$('#modalEditarUsuario #idEmail').text(email);
 		$('#modalEditarUsuario #idFacul').text(facultad);
 		$('#modalEditarUsuario #idRoles').text(roles);
 		$('#modalEditarUsuario #idActivado').text(enabled);
 		$('#modalEditarEdificio #idAttachment').text(imagen);
 		$('#modalEditarUsuario #btn-editar').prop("href", baseURL + "gestor/administrar/usuarios/editar/" + user.id)
 		
 		if (accion == 'Restaurar'){
 			
 				modalRestaurarUsuario(user, reqHeaders);	
 			
 		}else if(accion == 'Ver'){
 		
 			$('#modalEditarUsuario').modal('show');
 		}
 	});
 	
 	$('#selec-busqueda').change(function(){
 		$('#texto-busqueda').val("");
 		if ($(this).val()=="nombre")
 			$('#texto-busqueda').prop("placeholder", "Introduce nombre de usuario");
 		else if ($(this).val()=="email")
 			$('#texto-busqueda').prop("placeholder", "Introduce el correo");

 	});
});

function modalRestaurarUsuario(edificio, reqHeaders){
 	
		$.ajax({
			url: baseURL + "gestor/administrar/user/restaurar/" + edificio.id,
			type: 'DELETE',
			headers : reqHeaders,
			success : function(datos) {
				alert("Usuario restaurado");
				$('#modalEditarUsuario').modal('hide');
				$("#" + edificio.id).remove();
			
			},    
			error : function(xhr, status) {
				alert('Disculpe, existió un problema');
			}
 	});
 
}