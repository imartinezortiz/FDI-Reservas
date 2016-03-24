package es.fdi.reservas.reserva.business.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Edificio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="EdificioId")
	private Long id;
	@NotNull
	private String nombreEdificio;
	
	@OneToMany(mappedBy="edificio")
	private Set<Espacio> espacios;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="FacultadId")
	private Facultad facultad;


	Edificio(){
		
	}
	
	public Edificio(String name){
		nombreEdificio = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEdificio() {
		return nombreEdificio;
	}

	public void setNombreEdificio(String nombreEdificio) {
		this.nombreEdificio = nombreEdificio;
	}
	
	public Set<Espacio> getEspacios() {
		return espacios;
	}

	public void setEspacios(Set<Espacio> espacios) {
		this.espacios = espacios;
	}
	
	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}
	
}
