$(document).ready(function(){
		var edificio = {};
		var token = $("meta[name='_csrf']").attr("content");
	 	var header = $("meta[name='_csrf_header']").attr("content");
	 	var reqHeaders = [];
	 	reqHeaders[header] = token;
		
		
		$("#enlaceGuardar").click(function(){
			edificio.id = idEdificio;
			edificio.nombreEdificio = $("#idNombre").val();
			edificio.direccion = $("#idDir").val();
			edificio.facultad = $("#idFacultad").val();
			edificio.imagen = $("#idAttachment").val();
			
	    	editarEdificio(edificio,reqHeaders);
	    	
		});
		
		$("#idFacultad").autocomplete({
			source:function(request, response){
					var tag = request.term;
					
					$.ajax({
						url: '/reservas/facultad/tag/' + tag,
						type: 'GET',
						contentType: 'application/json',
						success : function(datos) {
							console.log(datos);
							
							response($.map(datos,function(item){
								
									var obj = new Object();
									obj.label = item.nombreFacultad; 
									obj.value = item.id;
									//obj.webFacultad = item.webFacultad;
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
				                  '<h5 class="media-heading">'+ item.nombreFacultad +'</h5>' + 
				                  '</div></div>';
				                  
				        
				                  
		            return $('<li></li>')
		                    .data("item.autocomplete", item)
		                    .append(inner_html)
		                    .appendTo(ul);
			
		};
		
});	

function editarEdificio(edificio, reqHeaders){
	
	$.ajax({
			url: baseURL + 'admin/administrar/edificios/editar/' + idEdificio + "/" + edificio.imagen,
			type: 'PUT',
			headers : reqHeaders,
			data: JSON.stringify(edificio),
			contentType: 'application/json',
			
			success : function(datos) {   
				 window.location = "/reservas/admin/administrar/edificios/1";
			},    
			error : function(xhr, status) {
				alert(baseURL),
 			alert('Disculpe, existió un problema');
 			
			}
		});
	
}