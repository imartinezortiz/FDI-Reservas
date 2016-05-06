$(document).ready(function(){
	  
var reserva = {};
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var reqHeaders = [];
reqHeaders[header] = token;


$("#modalEditarReserva #datetimepicker1").change(function(){
	var comienzo = es.ucm.fdi.dateUtils.toIso8601($('#modalEditarReserva #datetimepicker1').val());
	var m = new moment(comienzo);
	var fin = m.add(1,'hour');
	$('#modalEditarReserva #datetimepicker2').val(fin.format("DD/MM/YYYY HH:mm"));	
});

$("#modalEditarReserva #datetimepicker2").change(function(){
	var comienzo = es.ucm.fdi.dateUtils.toIso8601($('#modalEditarReserva #datetimepicker1').val());
	var fin = es.ucm.fdi.dateUtils.toIso8601($('#modalEditarReserva #datetimepicker2').val());
	var start = new moment(comienzo)
	var end = new moment(fin);
	if(end.isBefore(start)){
		$('#modalEditarReserva #datetimepicker2').val(start.add(1,'hour').format("DD/MM/YYYY HH:mm"));	
	}
	else{
		$('#modalEditarReserva #datetimepicker2').val(end.format("DD/MM/YYYY HH:mm"));	
	}
	
	
});

/// Ver como Lista y Calendario ///
$("#iconoLista").click(function(){
	$(this).addClass("red");
	$("#iconoCalendario").removeClass("red");
	$("#vistaLista").show();
	$("#calendar").hide();
});

$("#iconoCalendario").click(function(){
	$(this).addClass("red");
	$("#iconoLista").removeClass("red");
	$("#vistaLista").hide();
	loadCalendar();
	$("#calendar").show();
});


$('td a').click(function(){
	
	reserva.id =  $(this).attr("data-id");
	var asunto =  $(this).attr("data-asunto");
	var start = $(this).attr("data-start");
	var end = $(this).attr("data-end");
	var nombreEspacio = $(this).attr("data-espacio");
	var nombreGrupo = $(this).attr("data-grupo");
	var color = $(this).attr("data-reservaColor");
	var accion = $(this).attr("data-accion");
	
	$('#modalEditarReserva #asunto').text(asunto);
	$('#modalEditarReserva #comienzo').text(es.ucm.fdi.dateUtils.fromIso8601(start));
	$('#modalEditarReserva #fin').text(es.ucm.fdi.dateUtils.fromIso8601(end));
	$('#modalEditarReserva #nombreEspacio').text(nombreEspacio);
	$('#modalEditarReserva #nombreGrupo').text(nombreGrupo);
	$('#modalEditarReserva #reservaColor').css("background",color);
	$('#modalEditarReserva #enlaceEditar').prop("href", baseURL + "editar/" + reserva.id)
	
	if(accion == 'Ver'){
		$('#modalEditarReserva').modal('show');
	}
	else if(accion == 'Eliminar'){
		modalEliminarReserva(reserva);
	}
	
	
});


$("#enlaceEliminar").click(function(){
	modalEliminarReserva(reserva);
});

$("#aceptarEliminar").click(function(){
	eliminarReserva(reqHeaders, reserva.id);
});


$('#solo_esta').click(function(){
	
	var w = reserva.recurrenteId.split("_");
	var reservaPadre = w[0];
	var exDate = "EXDATE:";
	exDate += "VALUE=" + w[1]; 
	reserva.id = reservaPadre;
	var recurrencia = [];
	recurrencia.push(exDate);
	reserva.reglasRecurrencia = recurrencia;
		 		
	$.ajax({
		url: baseURL + 'editarReserRecurrente',
		headers : reqHeaders,
		type: 'POST',		 				 			
		data: JSON.stringify(reserva),
		contentType: 'application/json',
		success : function(datos) {  
			$("#modalRecurrente").modal('hide');	 				
			$("#calendar").fullCalendar('refetchEvents');
			
		},    
		error : function(xhr, status) {			
 			alert('Disculpe, existió un problema');			
		}
	});
});
	
$('#toda_la_serie').click(function(){
	
	$.ajax({
		url: baseURL + 'reserva/' + reserva.id,
		type: 'DELETE',
		headers : reqHeaders,
		success : function(datos) {
			$("#modalRecurrente").modal('hide');	
			$("#" + reserva.id).remove();
			$("#calendar").fullCalendar('refetchEvents');
		},    
		error : function(xhr, status) {
			alert('Disculpe, existió un problema');
		}
	});
});
	

/// FullCalendar ///
function loadCalendar(){
 	$('#calendar').fullCalendar({
		lang: 'es',
		timezone: 'local',
	    header: {
	        left: 'prev,next today',
	        center: 'title',
	        right: 'month,agendaWeek,agendaDay'
	    },	    
	    eventClick : function(calEvent,jsEvent, view) {
	    	console.log(calEvent);
	    	reserva.id = calEvent.id;
	    	reserva.recurrenteId = calEvent.recurrenteId;
			$('#modalEditarReserva').modal('show');
			$('#modalEditarReserva #idAsunto').val(calEvent.title);
			$('#modalEditarReserva #datetimepicker1').val(calEvent.start.format("DD/MM/YYYY HH:mm"));
			$('#modalEditarReserva #datetimepicker2').val(calEvent.end.format("DD/MM/YYYY HH:mm"));
			$('#modalEditarReserva #nombreEspacio').text(calEvent.nombreEspacio);
		},
	    defaultDate: '2016-03-12',
	    editable: true,
	    eventLimit: true,
	    eventClick: function(event, jsEvent, view){
	    	$('[role="tooltip"]').popover('hide');
	    	reserva.id = event.id;
    		var cuerpo = "<div>Donde: <b>" + event.nombreEspacio + "</b><br/>"+
			  			 "De " + event.start.format("HH:mm") + " a " + event.end.format("HH:mm") + 
			  			 "<br/>Asunto: " + event.title + "</div><br/>" +
			  			 "<div class='row'>" +
						 "<div class='col-md-6 text-left'><a onclick='modalEliminarReserva(event)'>" + 'Eliminar' + "</a></div>" +
						 "<div class='col-md-6 text-right'><a href='/reservas/editar/" + event.id + "'>" + 'Editar' + "</a></div>" +
						 "</div>";
		

	    	$(this).popover({						
				placement : 'auto',
				html : true,
				animation : 'true',
				container:'#calendar',
				content : cuerpo
						  
			}).popover('show');
	    },
		eventResize: function(event, delta, revertFunc, jsEvent) {

			editarReserva(event, reqHeaders, event.id);
	    },
	    eventDrop: function(event, delta, revertFunc) {

	        editarReserva(event, reqHeaders, event.id);
  
	    },
	    viewRender: function(view, element){
	    	$('[role="tooltip"]').popover('hide');
	    },
	    eventSources: [ 
		        {
		        	url: '/reservas/misEventos',							   
		            textColor: 'black'  
		        }	        
	    ]
	       	
	    
	});
}	

});


function editarReserva(reserva, reqHeaders, idReserva){
	$.ajax({
			url: baseURL + 'reserva/editar/' + idReserva,
			type: 'PUT',
			headers : reqHeaders,
			data: JSON.stringify(reserva),
			contentType: 'application/json',    
			error : function(xhr, status) {
				revertFunc();
			}
		});
}

function eliminarReserva(reqHeaders, idReserva){
	$.ajax({
			url: baseURL + 'reserva/' + idReserva,
			type: 'DELETE',
			headers : reqHeaders,
			success : function(datos) {
				$('#modalEliminarReservaSimple').modal('hide');
				$("#" + idReserva).remove();
				$("#calendar").fullCalendar('refetchEvents');
			},    
			error : function(xhr, status) {
				alert('Disculpe, existió un problema');
			}
		});
}

function modalEliminarReserva(reserva){
	$('#modalEditarReserva').modal('hide');
	$('[role="tooltip"]').popover('hide');
	// Si la reserva es recurrente
	if(reserva.recurrenteId != null){	
		$("#modalRecurrente").modal('show');
	}
	// Si la reserva es simple
	else{
		$('#modalEliminarReservaSimple').modal('show');
	}
}


