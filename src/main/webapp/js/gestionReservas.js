$(document).ready(function(){
	 	var reserva = {};
	 	var token = $("meta[name='_csrf']").attr("content");
	 	var header = $("meta[name='_csrf_header']").attr("content");
	 	var reqHeaders = [];
	 	reqHeaders[header] = token;
	 	var item;
	 	
	 	$('td a').click(function(){
	 		
	 		reserva.id =  $(this).attr("data-id");
	 		reserva.recurrenteId = $(this).attr("data-recurrenteId");
	 		var asunto =  $(this).attr("data-asunto");
	 		var start = $(this).attr("data-start");
	 		var end = $(this).attr("data-end");
	 		var nombreEspacio = $(this).attr("data-espacio");
	 		var nombreGrupo = $(this).attr("data-grupo");
	 		var color = $(this).attr("data-reservaColor");
	 		var accion = $(this).attr("data-accion");
	 		var nombreUser = $(this).attr("data-user");
	 		
	 		console.log(reserva.recurrenteId);
	 		
	 		$('#modalEditarReserva #usuario').text(nombreUser);
	 		$('#modalEditarReserva #asunto').text(asunto);
	 		$('#modalEditarReserva #comienzo').text(es.ucm.fdi.dateUtils.fromIso8601(start));
	 		$('#modalEditarReserva #fin').text(es.ucm.fdi.dateUtils.fromIso8601(end));
	 		$('#modalEditarReserva #nombreEspacio').text(nombreEspacio);
	 		$('#modalEditarReserva #nombreGrupo').text(nombreGrupo);
	 		$('#modalEditarReserva #reservaColor').css("background",color);
	 		$('#modalEditarReserva #enlaceEditar').prop("href", baseURL + "gestor/editar/" + reserva.id)
	 		
	 		if(accion == 'Ver'){
	 			$('#modalEditarReserva').modal('show');
	 		}
	 		else if(accion == 'Eliminar'){
	 			modalEliminarReservaSimple();	
	 		}
	 		
	 		
	 	});
	 	
	 	$("#btn-guardar").click(function(){
	 		reserva.user = $("#modalEditarReserva #idUsuario").val();
	 		reserva.title = $("#modalEditarReserva #idAsunto").val();
	 		reserva.start =	es.ucm.fdi.dateUtils.toIso8601($('#modalEditarReserva #datetimepicker1').val());
	 		reserva.end = es.ucm.fdi.dateUtils.toIso8601($('#modalEditarReserva #datetimepicker2').val());
	 		reserva.estadoReserva = $("#modalEditarReserva #selec_estadoReserva").val();
			console.log(reserva);
	 		$.ajax({
	 			url: baseURL+'reserva/' + reserva.id,
	 			type: 'PUT',
	 			headers : reqHeaders,
	 			data: JSON.stringify(reserva),
	 			contentType: 'application/json',
	 			success : function(datos) {   
	 				//alert("Reserva actualizada");
	 				location.reload();
	 			},    
	 			error : function(xhr, status) {
	     			alert('Disculpe, existió un problema');
	 			}
	 		});
	 	});
	 	
	 	$('#selec-busqueda').change(function(){
	 		$('#texto-busqueda').val("");
	 		if ($(this).val()=="user")
	 			$('#texto-busqueda').prop("placeholder", "Introduce nombre de usuario");
	 		else
	 			$('#texto-busqueda').prop("placeholder", "Introduce nombre de espacio");
	 	});
	 	
	 	$("#btn-eliminar").click(function(){
	 		$.ajax({
	 			url: baseURL+'reserva/' + reserva.id,
	 			type: 'DELETE',
	 			headers : reqHeaders,
	 			success : function(datos) {
	 				alert("Reserva eliminada");
	 				$('#modalEditarReserva').modal('hide');
	 				$("#"+reserva.id).remove();
	 			},    
	 			error : function(xhr, status) {
	 				alert('Disculpe, existió un problema');
	 			}
	 		});
	 	});
		
		$("#boton-busqueda").click(function(){
			var id_busqueda = $("#id-busqueda").val();
			var direccion = $('#selec_busqueda').val();
			window.location = '/reservas/gestor/gestion-reservas/'+direccion+'/'+id_busqueda;
		});
});
	function modalEliminarReservaSimple(){
		$('#modalEditarReserva').modal('hide');
		$('[role="tooltip"]').popover('hide');
		$('#modalEliminarReservaSimple').modal('show');	
	}

