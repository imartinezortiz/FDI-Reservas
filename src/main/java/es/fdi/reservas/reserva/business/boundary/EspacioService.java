package es.fdi.reservas.reserva.business.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.fdi.reservas.reserva.business.control.EspacioRepository;
import es.fdi.reservas.reserva.business.entity.Espacio;

@Service
public class EspacioService {

	EspacioRepository espacio_repository;
	
	@Autowired
	public EspacioService(EspacioRepository espacio_repository)
	{
		this.espacio_repository=espacio_repository;
	}
	
	public Espacio findEspacio(long espacioid)
	{
		return espacio_repository.findEspacio(espacioid);
	}
	
	public Page<Espacio> findEspacioByFacultadId(long facultadid, Pageable pageable)
	{
		return espacio_repository.findEspacioByFacultadId(facultadid, pageable);
	}
}
