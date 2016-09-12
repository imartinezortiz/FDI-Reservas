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
		var facultad = {};
		var token = $("meta[name='_csrf']").attr("content");
	 	var header = $("meta[name='_csrf_header']").attr("content");
	 	var reqHeaders = [];
	 	reqHeaders[header] = token;
	 	
		
		
		
		$("#enlaceGuardar").click(function(){
			facultad.nombreFacultad = $("#idNombre").val();
			facultad.webFacultad = $("#idWeb").val();
			console.log(facultad);
	    	nuevaFacultad(facultad,reqHeaders);
  	
		});
		
});	

function nuevaFacultad(facultad, reqHeaders){
	
	$.ajax({
			url: baseURL + 'admin/nuevaFacultad',
			type: 'PUT',
			headers : reqHeaders,
			data: JSON.stringify(facultad),
			contentType: 'application/json',
			
			success : function(datos) {   
				 window.location = "/reservas/admin/administrar/facultad/page/1";
			},    
			error : function(xhr, status) {
				alert(baseURL),
 			alert('Disculpe, existió un problema');
 			
			}
		});
	
}