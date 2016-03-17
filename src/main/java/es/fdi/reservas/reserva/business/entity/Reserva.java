package es.fdi.reservas.reserva.business.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="RESERVA_ID")
	private Long id;
	
	@NotNull
	private String asunto;
	
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private DateTime comienzo;
	
	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private DateTime fin;
	
	@Enumerated(EnumType.ORDINAL)
	private EstadoReserva estadoReserva;
	
	@NotNull
	private String username;
	//@NotNull
	//private String spacename;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="ESPACIO_ID")
	private Espacio espacio;
	
	private String[] recurrencia;
	

	public Reserva(){
		
	}
	
	public Reserva(String a, DateTime ini, DateTime fin, String user_name, Espacio esp){
		this.asunto = a;
		this.comienzo = ini;
		this.fin = fin;
		this.estadoReserva = EstadoReserva.CONFIRMADA;
		this.username = user_name;
		this.espacio = esp;
	}

	

	

	public String[] getRecurrencia() {
		return recurrencia;
	}

	public void setRecurrencia(String[] recurrencia) {
		this.recurrencia = recurrencia;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	public DateTime getComienzo() {
		return comienzo;
	}

	public void setComienzo(DateTime fecha_ini) {
		this.comienzo = fecha_ini;
	}

	public DateTime getFin() {
		return fin;
	}

	public void setFin(DateTime fecha_fin) {
		this.fin = fecha_fin;
	}

	public String getUsername() {
		return username;
	}

	public EstadoReserva getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public Long getId() {
		return id;
	}
	
	public Espacio getEspacio() {
		return espacio;
	}

	public void setEspacio(Espacio espacio) {
		this.espacio = espacio;
	}
	
	public boolean reservaAConfirmar(){
		DateTime finEsperado;
		finEsperado=comienzo.plusHours(2);
		return fin.compareTo(finEsperado) >= 0;
	}

	public boolean solapa(DateTime start, DateTime end) {
		/*
		 *             fecha_ini          fecha_fin
		 * --------------|------------------------|----------------
		 *               --------------------------
		 *   a)          |                        |
		 *               --------------------------
		 *           --------------------------------------
		 *   b)      |                                    |
		 *           --------------------------------------
		 *                     ----------------------------
		 *   c)                |                          |
		 *                     ----------------------------
		 *           -----------------
		 *   d)      |               |
		 *           -----------------
		 *                   ----------------
		 *   e)              |              |
		 *                   ----------------
		 */
		
		// a), b), d)
		boolean solapa = (start.compareTo(comienzo)) <= 0 && !(end.compareTo(comienzo) <= 0);
		// c), d)
		solapa = solapa || (start.compareTo(comienzo)>= 0 && start.compareTo(fin) < 0);
		return solapa;
	}

}
