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
		var reserva = {};
		var token = $("meta[name='_csrf']").attr("content");
	 	var header = $("meta[name='_csrf_header']").attr("content");
	 	var reqHeaders = [];
	 	reqHeaders[header] = token;
	 	
		$(function(){
			$('#cp2').colorpicker();		
		});
		
		/// Controla que la hora de fin no sea menor que la de inicio ///
		$("#datetimepicker1").change(function(){
			var comienzo = es.ucm.fdi.dateUtils.toIso8601($('#datetimepicker1').val());
			var m = new moment(comienzo);
			var fin = m.add(1,'hour');
			$('#datetimepicker2').val(fin.format("DD/MM/YYYY HH:mm"));	
		});

		$("#datetimepicker2").change(function(){
			var comienzo = es.ucm.fdi.dateUtils.toIso8601($('#datetimepicker1').val());
			var fin = es.ucm.fdi.dateUtils.toIso8601($('#datetimepicker2').val());
			var start = new moment(comienzo)
			var end = new moment(fin);
			if(end.isBefore(start)){
				$('#datetimepicker2').val(start.add(1,'hour').format("DD/MM/YYYY HH:mm"));	
			}
			else{
				$('#datetimepicker2').val(end.format("DD/MM/YYYY HH:mm"));	
			}	
		});
		
		
		
		$("#enlaceGuardarGestor").click(function(){
			
			reserva.id = idReserva;
			reserva.title = $("#idAsunto").val();
	    	reserva.start = es.ucm.fdi.dateUtils.toIso8601($("#datetimepicker1").val());
	    	reserva.end = es.ucm.fdi.dateUtils.toIso8601($("#datetimepicker2").val());
	    	reserva.idGrupo = $("#selec_grupo").val();
	        reserva.color = $('#cp2').colorpicker('getValue',"");
	    	reserva.idEspacio = idEspacio;
	    	reserva.recurrenteId = recurrenteId;
	    	reserva.reglasRecurrencia = reglas;
	    	reserva.estado= $("#selec_estado").val();
	    	
	    	console.log(reserva);
	    	editarReserva(reserva,reqHeaders);
  	
		});
		
		
		$("#idUser").autocomplete({
			source:function(request, response){
					var tag = request.term;
					
					$.ajax({
						url: '/reservas/usuarios/tag/' + tag,
						type: 'GET',
						contentType: 'application/json',
						success : function(datos) {
							console.log(datos);
							
							response($.map(datos,function(item){
								
									var obj = new Object();
									obj.label = item.id; 
									obj.value = item.username;
									obj.email = item.email;
									return obj;
				
							}))
							
						},    
					    error : function(xhr, status) {
					        alert('Disculpe, existió un problema');
					    }
					});
			},
			select: function(event, ui){
				reserva.idUsuario=ui.item.label;	
			},
			minLength: 3

		}).autocomplete("instance")._renderItem = function(ul,item){
			
				var inner_html = '<div class="media"><div class="media-left">' + 
				                  '<img class="img-circle" src="http://placehold.it/50x50"/>' + 
				                  '</div>' + 
				                  '<div class="media-body">' + 
				                  '<h5 class="media-heading">'+ item.value +'</h5>' + 
				                  '<p class="small text-muted">'+ item.email +'</p>' + 
				                  '</div></div>';
				                  
				        
				                  
		            return $('<li></li>')
		                    .data("item.autocomplete", item)
		                    .append(inner_html)
		                    .appendTo(ul);
			
		};
		
});	

function editarReserva(reserva, reqHeaders){
	
	$.ajax({
			url: baseURL + 'reserva/editar/' + idReserva,
			type: 'PUT',
			headers : reqHeaders,
			data: JSON.stringify(reserva),
			contentType: 'application/json',
			success : function(datos) {   
				 window.location = "/reservas/gestor/administrar/reservas/page/1";
			},    
			error : function(xhr, status) {
			
 			alert('Disculpe, existió un problema');
 			
			}
		});
	
}