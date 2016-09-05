package es.fdi.reservas.reserva.business.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.fdi.reservas.reserva.business.boundary.EdificioService;
import es.fdi.reservas.reserva.business.boundary.EspacioService;
import es.fdi.reservas.reserva.business.boundary.GrupoReservaService;
import es.fdi.reservas.reserva.business.boundary.ReservaService;
import es.fdi.reservas.reserva.business.entity.Edificio;
import es.fdi.reservas.reserva.business.entity.Espacio;
import es.fdi.reservas.reserva.business.entity.EstadoReserva;
import es.fdi.reservas.reserva.business.entity.GrupoReserva;
import es.fdi.reservas.reserva.business.entity.Reserva;
import es.fdi.reservas.users.business.boundary.UserService;
import es.fdi.reservas.users.business.entity.User;

@Service
public class GestorService {

	private ReservaService reserva_service;
	private EdificioService edificio_service;
	private EspacioService espacio_service;
	private GrupoReservaService grupo_service;
	private UserService user_service;
	
	
	@Autowired
	public GestorService(ReservaService r_s, EdificioService e_s, EspacioService es_s, 
						 GrupoReservaService g_s, UserService u_s) {
		super();
		this.reserva_service = r_s;
		this.edificio_service = e_s;
		this.espacio_service = es_s;
		this.grupo_service = g_s;
		this.user_service = u_s;
	}

	public Reserva getReserva(long reservaid)
	{
		return reserva_service.getReserva(reservaid);
	}
	
	public Page<Reserva> getReservasByFacultadId(long facultadid, Pageable pageable)
	{
		return reserva_service.findByFacultadId(facultadid, pageable);
	}
	
	public Page<Reserva> getReservasByUserId(Long userid, Pageable pageable)
	{
		return reserva_service.getReservasUsuario(userid, pageable);
	}
	
	public Page<Reserva> getReservasByUserId(String userid, long facultadid, Pageable pageable)
	{
		return reserva_service.findByUserIdAndFacultadId(userid, facultadid, pageable);
	}
	
	public Page<Reserva> getReservasByEspacioId(String espacioid, long facultadid, Pageable pageable)
	{
		return reserva_service.findByEspacioIdAndFacultadId(espacioid, facultadid, pageable);
	}
	
	public Page<Reserva> getReservasByEstadoReserva(EstadoReserva estadoreserva, long facultadid, Pageable pageable)
	{
		return reserva_service.findByEstadoReservaAndFacultadId(estadoreserva, facultadid, pageable);
	}
	
	public Edificio getEdificio(long edificioid)
	{
		return edificio_service.findEdificio(edificioid);
		
	}
	
	public Page<Edificio> getEdificioByFacultadId(long facultadid, PageRequest pageable)
	{
		return edificio_service.findEdificioByFacultadId(facultadid, pageable);	
	}
	
	public Page<Edificio> getEdificioDeletedByFacultadId(long facultadid, Pageable pageable)
	{
		return edificio_service.getEdificiosEliminadosFacultad(facultadid, pageable);
	}
	
	public Page<Edificio> getEdificioByTagNombreAndFacutadId(String cadena,long facultadid, Pageable pageable)
	{
		return edificio_service.getEdificiosPorTagNameYFacultad(cadena, facultadid, pageable);
	}
	
	public Page<Edificio> getEdificioByTagDireccionAndFacutadId(String cadena,long facultadid, Pageable pageable)
	{
		return edificio_service.getEdificiosPorDireccionYFacultad(cadena, facultadid, pageable);
	}
	
	public Espacio getEspacio(long espacioid)
	{
		return espacio_service.findEspacio(espacioid);
	}
	
	public Page<Espacio> getEspaciosByFacultad(long facultadid, Pageable pageable)
	{
		return espacio_service.findEspacioByFacultadId(facultadid, pageable);
	}
	
	public Page<Espacio> getEspaciosByFacultadAndNombre(String nombre, long facultadid, Pageable pageable)
	{
		return espacio_service.getEspaciosPorNombreYFacultad(nombre, facultadid, pageable);
	}
	
	public Page<Espacio> getEspaciosByFacultadAndEdificio(String edificio, long facultadid, Pageable pageable)
	{
		return espacio_service.getEspaciosPorEdificioYFacultad(edificio, facultadid, pageable);
	}
	
	public Page<Espacio> getEspaciosDeletedByFacultad(long facultadid, Pageable pageable)
	{
		return espacio_service.findEspacioDeletedByFacultadId(facultadid, pageable);
	}
	
	public GrupoReserva getGrupoReserva(long grupoid)
	{
		return grupo_service.findGrupoReserva(grupoid);
	}
	
	public List<GrupoReserva> getGrupoReservaByUserId(long userid)
	{
		return grupo_service.findGrupoReservaByUserId(userid);
	}
	
	public User getUsuarioActual()
	{
		return user_service.getCurrentUser();
	}

	public List<Reserva> getReservasPendientes(Long id, EstadoReserva pendiente) {
		return reserva_service.reservasPendientesUsuario(id, pendiente);
	}
	
	public List<Reserva> reservasPendientesUsuario(Long userId, EstadoReserva er)
	{
		return reserva_service.reservasPendientesUsuario(userId, er);
	}
}
