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
		var espacio = {};
		var token = $("meta[name='_csrf']").attr("content");
	 	var header = $("meta[name='_csrf_header']").attr("content");
	 	var reqHeaders = [];
	 	reqHeaders[header] = token;
		
	 	$("#idRestr").change(function(){
	 		if ($("#idRestr").val()!="Por horas")
	 		{
	 			$("#idHoras").hide();
	 			$("#idHoras").val("0");
	 		}
	 		else
	 		{
	 			$("#idHoras").show();
	 		}
	 			
	 	});
		
		
		$("#enlaceGuardar").click(function(){
			espacio.id = idEspacio;
			espacio.nombreEspacio = $("#idNombre").val();
			espacio.idEdificio = $("#edificioHidden").val();
			espacio.capacidad = $("#idCapa").val();
			espacio.microfono = $("#idMicro").val();
			espacio.proyector = $("#idProy").val();
			espacio.tipoEspacio = $("#idTipo").val();
			espacio.imagen = $("#idAttachment").val();
			espacio.tipoAutorizacion=$("#idRestr").val();
			espacio.horasAutorizacion=$("#idHoras").val();
	    	editarEspacio(espacio,reqHeaders);
		});
		
		$("#idEdificio").autocomplete({
			source:function(request, response){
					var tag = request.term;
					
					$.ajax({
						url: '/reservas/gestor/edificio/tag/' + tag,
						type: 'GET',
						contentType: 'application/json',
						success : function(datos) {
							console.log(datos);
							
							response($.map(datos,function(item){
								
									var obj = new Object();
									obj.label = item.id;
									obj.value = item.nombreEdificio; 
									return obj;
				
							}))
							
						},    
					    error : function(xhr, status) {
					        alert('Disculpe, existió un problema');
					    }
					});
			},
			select: function(event, ui){
				$("#edificioHidden").val(ui.item.label);
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

function editarEspacio(espacio, reqHeaders){

	console.log(espacio);
	$.ajax({
			url: baseURL + 'gestor/administrar/espacio/editar/' + idEspacio,
			type: 'PUT',
			headers : reqHeaders,
			data: JSON.stringify(espacio),
			contentType: 'application/json',
			
			success : function(datos) {   
				 window.location = "/reservas/gestor/administrar/espacios";
			},    
			error : function(xhr, status) {
 			alert('Disculpe, existió un problema');
 			
			}
		});
	
}