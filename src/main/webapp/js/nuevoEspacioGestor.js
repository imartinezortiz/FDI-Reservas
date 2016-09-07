$(document).ready(function(){
		var espacio = {};
		var token = $("meta[name='_csrf']").attr("content");
	 	var header = $("meta[name='_csrf_header']").attr("content");
	 	var reqHeaders = [];
	 	reqHeaders[header] = token;
		
		
		$("#enviar").click(function(){
			espacio.nombreEspacio = $("#nombre").val();
			espacio.capacidad = $("#capacidad").val();
			espacio.microfono= $("#microfono").val();
			espacio.proyector= $("#proyector").val();
			espacio.tipoEspacio= $("#tipoEspacio").val();
			espacio.idEdificio=$("#hEdif").val();
	    	editarEspacio(espacio,reqHeaders);
		});
});
		
function editarEspacio(espacio, reqHeaders){
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
				alert(baseURL),
 			alert('Disculpe, existió un problema');
 			
			}
		});}

$("#idEdif").autocomplete({
	source:function(request, response){
			var tag = request.term;
			
			$.ajax({
				url: '/reservas/gestor/edificio/tag/' + tag,
				type: 'GET',
				contentType: 'application/json',
				success : function(datos) {
					console.log(datos);
					
					response($.map(datos,function(item){
						
							var obj = new Object();
							obj.label = item.id;
							obj.value = item.nombreEdificio; 
							return obj;
		
					}))
					
				},    
			    error : function(xhr, status) {
			        alert('Disculpe, existió un problema');
			    }
			});
	},
	select: function(event, ui){
		espacio.edificio = ui.item.label;
		$("#hEdif").val(ui.item.label);
	},
	minLength: 3

}).autocomplete("instance")._renderItem = function(ul,item){
	
		var inner_html = '<div class="media"><div class="media-left">' + 
		                  '</div>' + 
		                  '<div class="media-body">' + 
		                  '<h5 class="media-heading">'+ item.value +'</h5>' + 
		                  '</div></div>';
		                  
		        
		                  
            return $('<li></li>')
                    .data("item.autocomplete", item)
                    .append(inner_html)
                    .appendTo(ul);
	
};
