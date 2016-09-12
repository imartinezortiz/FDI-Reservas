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
