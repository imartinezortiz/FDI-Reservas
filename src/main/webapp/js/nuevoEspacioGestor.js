$(document).ready(function(){
		var espacio = {};
		var token = $("meta[name='_csrf']").attr("content");
	 	var header = $("meta[name='_csrf_header']").attr("content");
	 	var reqHeaders = [];
	 	reqHeaders[header] = token;
		
		
		$("#enlaceCrear").click(function(){
			espacio.nombreEspacio = $("#nombre").val();
			espacio.tipoEspacio = $("#tipoEspacio").val();
			espacio.idEdificio =$("#idEdif").val();
	    	crearEspacio(espacio,reqHeaders);
		});
});
		
function crearEspacio(espacio, reqHeaders){
	console.log(espacio);
	$.ajax({
			url: baseURL + 'gestor/nuevoEspacio',
			type: 'POST',
			headers : reqHeaders,
			data: JSON.stringify(espacio),
			contentType: 'application/json',
			
			success : function(datos) {   
				 window.location = "/reservas/gestor/administrar/espacios/page/1";
			},    
			error : function(xhr, status) {
				
				alert('Disculpe, existió un problema');
 			
			}
		});
}


