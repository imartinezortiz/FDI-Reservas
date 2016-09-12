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
		var grupo = {};
		var token = $("meta[name='_csrf']").attr("content");
	 	var header = $("meta[name='_csrf_header']").attr("content");
	 	var reqHeaders = [];
	 	reqHeaders[header] = token;
	 		
	 	console.log(idGrupo);
	 	
		$("#enlaceGuardar").click(function(){

			grupo.id = idGrupo;
			grupo.nombreCorto = $("#nombreCorto").val();
			grupo.nombreLargo = $("#nombreLargo").val();
	    	
	    	
	    	editarGrupo(grupo,reqHeaders);
  	
		});
			
});	

function editarGrupo(grupo, reqHeaders){
	
	$.ajax({
			url: baseURL + 'reserva/grupo/editar/' + idGrupo,
			type: 'PUT',
			headers : reqHeaders,
			data: JSON.stringify(grupo),
			contentType: 'application/json',
			success : function(datos) {   
				 window.location = "/reservas/mis-reservas";
			},    
			error : function(xhr, status) {
			
 			alert('Disculpe, existió un problema');
 			
			}
		});
	
}