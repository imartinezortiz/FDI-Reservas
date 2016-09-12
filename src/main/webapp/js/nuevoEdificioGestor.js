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
		
		
		$("#enlaceCrear").click(function(){
			edificio.nombreEdificio = $("#nombreEdificio").val();
			edificio.direccion = $("#direccion").val();
			edificio.idFacultad = idFacultad;
			//edificio.imagen = $("#idAttachment").val();
			//console.log(edificio);
	    	crearEdificio(edificio,reqHeaders);
		});
});
		
function crearEdificio(edificio, reqHeaders){	
	
	$.ajax({
			url: baseURL + 'gestor/nuevoEdificio',
			type: 'POST',
			headers : reqHeaders,
			data: JSON.stringify(edificio),
			contentType: 'application/json',			
			success : function(datos) {   
				 window.location = "/reservas/gestor/administrar/edificios/page/1";
			},    
			error : function(xhr, status) {
				
				alert('Disculpe, existió un problema');
 			
			}
		});
	}
