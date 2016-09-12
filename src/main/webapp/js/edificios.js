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
	 	
	 	$("li>div").click(function(){
			var idEdificio = $(this).attr("data-id");
			
			$("li>div").each(function(){
				$(this).removeClass("selectedEdif");
			});
			
			$(this).addClass("selectedEdif");
			var link = 'edificio/' + idEdificio + '/espacios';
			$("#edificio_link").attr("href",link);
		});
		
		$("#edificio_link").click(function(){
			var link = $("#edificio_link").attr('href');
			if(link == '')
			   alert("Debes seleccionar un edificio.");
			
		});
	 
	 
 });