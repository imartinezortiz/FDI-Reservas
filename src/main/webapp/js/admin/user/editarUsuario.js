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
		
		$("#enlaceGuardar").click(function(){
			user.id = idUsuario;
			user.username = $("#idNombre").val();
			user.email = $("#idEmail").val();
			//user.facultad = $("#idFacultad").val();
			user.imagen = $("#idAttachment").val();
			console.log(user);
	    	editarUsuario(user,reqHeaders);
		});
		
		$("#idFacultad").autocomplete({
			source:function(request, response){
					var tag = request.term;
					
					$.ajax({
						url: '/reservas/facultad/tag/' + tag,
						type: 'GET',
						contentType: 'application/json',
						success : function(datos) {
							console.log(datos);
							
							response($.map(datos,function(item){
								
									var obj = new Object();
									obj.label = item.id;
									obj.value = item.nombreFacultad; 
									//obj.webFacultad = item.webFacultad;
									return obj;
				
							}))
							
						},    
					    error : function(xhr, status) {
					        alert('Disculpe, existió un problema');
					    }
					});
			},
			select: function(event, ui){
				user.facultad = ui.item.label;
				//console.log(idFacultad);
				//$("#idFacultad").prop("name", idFacultad);
			},
			minLength: 3

		}).autocomplete("instance")._renderItem = function(ul,item){
			
				var inner_html = '<div class="media"><div class="media-left">' + 
				                  '</div>' + 
				                  '<div class="media-body">' + 
				                  '<h5 class="media-heading">'+ item.value +'</h5>' + 
				                  '</div></div>';
				                  
				        
				                  
		            return $('<li></li>')
		                    .data("item.autocomplete", item)
		                    .append(inner_html)
		                    .appendTo(ul);
			
		};
		
});	

function editarUsuario(user, reqHeaders){
	
	var usuario = document.getElementById("chkUser").checked.toString();
	var admin = document.getElementById("chkAdmin").checked.toString();
	var gestor = document.getElementById("chkSecre").checked.toString();
	
	$.ajax({
			
			url: baseURL + 'admin/administrar/usuarios/editar/' + idUsuario + '/' + usuario + '/' + admin + '/' + gestor,
			//url: baseURL + 'admin/administrar/usuarios/editar/' + idUsuario ,
			type: 'PUT',
			headers : reqHeaders,
			data: JSON.stringify(user),
			contentType: 'application/json',
			
			success : function(datos) {   
				 window.location = "/reservas/admin/administrar/usuarios/page/1";
			},    
			error : function(xhr, status) {
				alert(usuario + admin + gestor),
 			alert('Disculpe, existió un problema');
 			
			}
		});
	
}

function calcAdmin()
{
  if (document.getElementById('chkAdmin').checked) 
  {
      $("#chkSecre").prop("checked", false);
  } 
}

function calcGestor()
{
  if (document.getElementById('chkSecre').checked) 
  {
	  $("#chkAdmin").prop("checked", false);
  } 
}