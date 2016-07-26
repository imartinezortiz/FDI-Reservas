$(document).ready(function(){
		var user = {};
		var token = $("meta[name='_csrf']").attr("content");
	 	var header = $("meta[name='_csrf_header']").attr("content");
	 	var reqHeaders = [];
	 	reqHeaders[header] = token;
	 	
		
		
		
		$("#enlaceGuardar").click(function(){
			user.id = idUsuario;
			alert("1");
			user.username = $("#idNombre").val();
			alert("1");
			user.email = $("#idEmail").val();
			user.facultad = $("#autoFacultades").val();
			alert("2");
	    	editarUsuario(user,reqHeaders);
  	
		});
		
		
		$("#autoFacultades").autocomplete({
			source:function(request, response){
					var tag = request.term;
					
					$.ajax({
						url: '/facultades/tag/' + tag,
						
						type: 'GET',
						contentType: 'application/json',
						success : function(datos) {
							console.log(datos);
							
							response($.map(datos,function(item){
								
									var obj = new Object();
									obj.label = item.id; 
									obj.value = item.nombreFacultad;
									obj.webFacultad = item.webFacultad;
									return obj;
				
							}))
							
						},    
					    error : function(xhr, status) {
					        alert('Disculpe, existió un problema');
					    }
					});
			},
			select: function(event, ui){
				var img = '<img class="img-circle" src="http://placehold.it/30x30" data-toggle="tooltip" data-placement="bottom" title="' + ui.item.value + '" />' ;
				$("#facultad").append(img);
				$('[data-toggle="tooltip"]').tooltip();
				
			},
			minLength: 3

		}).autocomplete("instance")._renderItem = function(ul,item){
			
				var inner_html = '<div class="media"><div class="media-left">' + 
				                  '<img class="img-circle" src="http://placehold.it/50x50"/>' + 
				                  '</div>' + 
				                  '<div class="media-body">' + 
				                  '<h5 class="media-heading">'+ item.value +'</h5>' + 
				                  '<p class="small text-muted">'+ item.webFacultad +'</p>' + 
				                  '</div></div>';
				                  
				        
				                  
		            return $('<li></li>')
		                    .data("item.autocomplete", item)
		                    .append(inner_html)
		                    .appendTo(ul);
			
		};
		
});	

function editarUsuario(user, reqHeaders){
	
	$.ajax({
			url: baseURL + 'admin/administrar/usuarios/editar/' + idUsuario + '/' + usuario + '/' + admin + '/' + gestor,
			type: 'PUT',
			headers : reqHeaders,
			data: JSON.stringify(user),
			contentType: 'application/json',
			
			success : function(datos) {   
				 window.location = "/reservas/admin/administrar/usuarios";
			},    
			error : function(xhr, status) {
				alert(baseURL),
 			alert('Disculpe, existió un problema');
 			
			}
		});
	
}