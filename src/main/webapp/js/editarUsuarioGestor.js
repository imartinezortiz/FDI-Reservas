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
		
	 	$("#enlaceGuardar").click(function(){
			user.id = idUsuario;
			user.username = $("#idNombre").val();
			user.email = $("#idEmail").val();
			user.facultad = $("idFacultad").val();
			user.imagen = $("#idAttachment").val();
	    	editarUsuario(user,reqHeaders);
	    	
		});
	 	
//		for(var i in roles){
//			if(roles[i].role == "ROLE_USER"){
//				$("#chkUser").prop("checked","true");
//			}
//			else if(roles[i].role == "ROLE_ADMIN"){
//				$("#chkAdmin").prop("checked","true");
//			}
//			else{
//				$("#chkSecre").prop("checked","true");
//			}
//		}
		
		
});	

function editarUsuario(user, reqHeaders){

	//var inputElements = document.getElementsByClassName('checkbox');
//	for(var i=0; inputElements[i]; ++i){
//	      if(inputElements[i].checked){
//	    	  if ((i == 1) || (i == 3)){
//	           admin = inputElements[i].value;
////	           break;
//	    	  }
//	    	  else if ((i == 5) || (i == 7)){
//	           usuario = inputElements[i].value;
////	           break;
//	    	  }
//	    	  else if ((9 == 1) || (i == 11)){
//	           gestor = inputElements[i].value;
////	           break;
//	    	  }
//	      }
//	}
	
	var usuario = document.getElementById("chkUser").checked.toString();
	var admin = document.getElementById("chkAdmin").checked.toString();
	var gestor = document.getElementById("chkSecre").checked.toString();
	$.ajax({
			
			url: baseURL + 'gestor/administrar/usuarios/editar/' + idUsuario + '/' + usuario + '/' + admin + '/' + gestor,
			type: 'PUT',
			headers : reqHeaders,
			data: JSON.stringify(user),
			contentType: 'application/json',
			
			success : function(datos) {   
				 window.location = "/reservas/gestor/administrar/usuarios/page/1";
			},    
			error : function(xhr, status) {
				alert(usuario + admin + gestor),
 			alert('Disculpe, existió un problema');
 			
			}
		});
	
}