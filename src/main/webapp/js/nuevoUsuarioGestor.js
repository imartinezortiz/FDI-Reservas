$(document).ready(function(){
		var user = {};
		var token = $("meta[name='_csrf']").attr("content");
	 	var header = $("meta[name='_csrf_header']").attr("content");
	 	var reqHeaders = [];
	 	reqHeaders[header] = token;
		
		
		$("#enviar").click(function(){
			user.username = $("#idnombre").val();
			user.email = $("#email").val();
			user.password = $("#pass").val();
			user.facultad = $("#idFacul").val();
			user.imagen = $("#imagen").val();
	    	editarUser(user,reqHeaders);
		});
});
		
function editarUser(user, reqHeaders){
	console.log(user);
	$.ajax({
			url: baseURL + 'gestor/nuevoUsuario',
			type: 'POST',
			headers : reqHeaders,
			data: JSON.stringify(user),
			contentType: 'application/json',
			
			success : function(datos) {   
				 window.location = "/reservas/gestor/administrar/usuarios/page/1";
			},    
			error : function(xhr, status) {
				alert(baseURL),
 			alert('Disculpe, existió un problema');
 			
			}
		});}
