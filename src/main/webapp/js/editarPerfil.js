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
	 	
	 	// Ocultar el msg de error al hacer click
	 	$("#alertClose").click(function(){
	 		$(".alert").css("display","none");
	 	});
	 	
		$("#enlaceGuardar").click(function(){

			user.id = idUsuario;
			user.oldPassword = $("#oldPassword").val();
			user.username = $("#username").val();
			user.email = $("#email").val();
			user.imagen= $("#idAttachment").val();
			
			if(user.oldPassword != ""){// quiero cambiar de contraseña
				if($("#newPassword1").val() != $("#newPassword2").val())
		    		alert("Las nuevas contraseñas deben coincidir");
		    	else{
		    		user.newPassword = $("#newPassword1").val();
		    		console.log(user);
					editarPerfil(user, reqHeaders);
		    	}
			}
			else{
				user.oldPassword = null;
				editarPerfil(user, reqHeaders);
			}
	    	
	
		});
			
});	

function editarPerfil(usuario, reqHeaders){
	
	$.ajax({
			url: baseURL + 'perfil/editar',
			type: 'PUT',
			headers : reqHeaders,
			data: JSON.stringify(usuario),
			contentType: 'application/json',
			success : function(datos) {   
				 window.location = "/reservas/perfil";
			},    
			error : function(xhr, status) {		
				var x = JSON.parse(xhr.responseText);
				showAlertMsg(x.msg);		
			}
		});
	
}

function showAlertMsg(msg){
	$(".alert").css("display","block");
	$("#alertMsg").text(msg);
}

