package es.fdi.reservas.reserva.business.boundary;

import org.springframework.data.domain.Page;
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
	
	public Reserva getReserva(long reservaid)
	{
		return reserva_service.getReserva(reservaid);
	}
	
	public Page<Reserva> getReservasByFacultadId(long facultadid, Pageable pageable)
	{
		return reserva_service.findByFacultadId(facultadid, pageable);
	}
	
	public Page<Reserva> getReservasByUserId(long userid, long facultadid, Pageable pageable)
	{
		return reserva_service.findByUserIdAndFacultadId(userid, facultadid, pageable);
	}
	
	public Page<Reserva> getReservasByEspacioId(long espacioid, long facultadid, Pageable pageable)
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
	
	public Page<Edificio> getEdificioByFacultadId(long facultadid, Pageable pageable)
	{
		return edificio_service.findEdificio(facultadid, pageable);
		
	}
	
	public Espacio getEspacio(long espacioid)
	{
		return espacio_service.findEspacio(espacioid);
	}
	
	public Page<Espacio> getEspaciosByFacultad(long facultadid, Pageable pageable)
	{
		return espacio_service.findByFacultadId(facultadid, pageable);
	}
	
	public GrupoReserva getGrupoReserva(long grupoid)
	{
		return grupo_service.findGrupoReserva(grupoid);
	}
	
	public Page<GrupoReserva> getGrupoReservaByUserId(long userid)
	{
		return grupo_service.findGrupoReservaByuserId(userid);
	}
	
	public User getUsuarioActual()
	{
		return user_service.getCurrentUser();
	}
}
