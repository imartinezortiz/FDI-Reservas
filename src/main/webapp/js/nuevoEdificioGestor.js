$(document).ready(function(){
		var edificio = {};
		var token = $("meta[name='_csrf']").attr("content");
	 	var header = $("meta[name='_csrf_header']").attr("content");
	 	var reqHeaders = [];
	 	reqHeaders[header] = token;
		
		
		$("#enviar").click(function(){
			edificio.nombreEdificio = $("#nombreEdificio").val();
			edificio.direccion = $("#direccion").val();
			edificio.idFacultad = $("#hFacultad").val();
			edificio.imagen = $("#idAttachment").val();
	    	editarEdificio(edificio,reqHeaders);
		});
});
		
function editarEdificio(edificio, reqHeaders){
	
	console.log(edificio);
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
				alert(baseURL),
 			alert('Disculpe, existió un problema');
 			
			}
		});}
