$(document).ready(function(){
		var edificio = {};
		var token = $("meta[name='_csrf']").attr("content");
	 	var header = $("meta[name='_csrf_header']").attr("content");
	 	var reqHeaders = [];
	 	reqHeaders[header] = token;
		
		
		$("#enviar").click(function(){
			espacio.nombreEspacio = $("#nombre").val();
			espacio.capacidad = $("#capacidad").val();
			espacio.idFacultad = $("#hFacultad").val();
			espacio.microfono= $("#microfono").val();
			espacio.proyector= $("#proyector").val();
			espacio.tipoEspacio= $("#tipoEspacio").val();
	    	editarEspacio(espacio,reqHeaders);
		});
});
		
function editarEspacio(espacio, reqHeaders){
	
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
				alert(baseURL),
 			alert('Disculpe, existió un problema');
 			
			}
		});}
