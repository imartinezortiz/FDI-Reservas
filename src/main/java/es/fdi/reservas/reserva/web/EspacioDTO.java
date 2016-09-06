package es.fdi.reservas.reserva.web;

import es.fdi.reservas.reserva.business.entity.Espacio;

public class EspacioDTO {
	
	private Long id;
	private String nombreEspacio;
	private String edificio;

	private int capacidad;
	private boolean microfono, proyector;
	
	private String tipoEspacio;
	
	private String imagen;
	
	private String tipoAutorizacion;
	
	private Integer horasAutorizacion;
	
	public EspacioDTO(){}
	
	public EspacioDTO(Long id, String nombreEspacio, String edificio, int capacidad, boolean microfono,
			boolean proyector, String tipoEspacio, String imagen) {
		super();
		this.id = id;
		this.nombreEspacio = nombreEspacio;
		this.edificio = edificio;
		this.capacidad = capacidad;
		this.microfono = microfono;
		this.proyector = proyector;
		this.tipoEspacio = tipoEspacio;
		this.imagen = imagen;
	}
	
	public EspacioDTO(String nombreEspacio, String edificio, int capacidad, boolean microfono,
			boolean proyector, String tipoEspacio, String imagen) {
		super();
		this.nombreEspacio = nombreEspacio;
		this.edificio = edificio;
		this.capacidad = capacidad;
		this.microfono = microfono;
		this.proyector = proyector;
		this.tipoEspacio = tipoEspacio;
		this.imagen = imagen;
	}
	
	public EspacioDTO(Long id, String nombreEspacio, String edificio, int capacidad, boolean microfono,
			boolean proyector, String tipoEspacio, String imagen, String tipoAutorizacion, Integer horasAutorizacion) {
		super();
		this.id = id;
		this.nombreEspacio = nombreEspacio;
		this.edificio = edificio;
		this.capacidad = capacidad;
		this.microfono = microfono;
		this.proyector = proyector;
		this.tipoEspacio = tipoEspacio;
		this.imagen = imagen;
		this.tipoAutorizacion = tipoAutorizacion;
		this.horasAutorizacion = horasAutorizacion;
	}
	
	public EspacioDTO(String nombreEspacio, String edificio, int capacidad, boolean microfono,
			boolean proyector, String tipoEspacio, String imagen, String tipoAutorizacion, Integer horasAutorizacion) {
		super();
		this.nombreEspacio = nombreEspacio;
		this.edificio = edificio;
		this.capacidad = capacidad;
		this.microfono = microfono;
		this.proyector = proyector;
		this.tipoEspacio = tipoEspacio;
		this.imagen = imagen;
		this.tipoAutorizacion = tipoAutorizacion;
		this.horasAutorizacion = horasAutorizacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreEspacio() {
		return nombreEspacio;
	}

	public void setNombreEspacio(String nombreEspacio) {
		this.nombreEspacio = nombreEspacio;
	}

	public String getEdificio() {
		return edificio;
	}

	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}
	
	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public boolean isMicrofono() {
		return microfono;
	}

	public void setMicrofono(boolean microfono) {
		this.microfono = microfono;
	}

	public boolean isProyector() {
		return proyector;
	}

	public void setProyector(boolean proyector) {
		this.proyector = proyector;
	}
	
	public String getTipoEspacio() {
		return tipoEspacio;
	}

	public void setTipoEspacio(String tipoEspacio) {
		this.tipoEspacio = tipoEspacio;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTipoAutorizacion() {
		return tipoAutorizacion;
	}

	public void setTipoAutorizacion(String tipoAutorizacion) {
		this.tipoAutorizacion = tipoAutorizacion;
	}

	public Integer getHorasAutorizacion() {
		return horasAutorizacion;
	}

	public void setHorasAutorizacion(Integer horasAutorizacion) {
		this.horasAutorizacion = horasAutorizacion;
	}

	public static EspacioDTO fromEspacioDTOAutocompletar(Espacio e){
		return new EspacioDTO(e.getId(), e.getNombreEspacio(), e.getEdificio().getNombreEdificio(), e.getCapacidad(), e.isMicrofono(), e.isProyector(), e.getTipoEspacio().getTipo(), e.getImagen().getAttachmentUrl(),e.getTipoAutorizacion().toString(), e.getHorasAutorizacion());
	}
	
	public static EspacioDTO fromEspacioDTO(Espacio e){
		return new EspacioDTO(e.getNombreEspacio(), e.getEdificio().getNombreEdificio(), e.getCapacidad(), e.isMicrofono(), e.isProyector(), e.getTipoEspacio().getTipo(), e.getImagen().getAttachmentUrl(), e.getTipoAutorizacion().toString(), e.getHorasAutorizacion());
	}
}
