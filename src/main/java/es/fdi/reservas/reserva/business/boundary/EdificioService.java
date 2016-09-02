package es.fdi.reservas.reserva.business.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.fdi.reservas.reserva.business.control.EdificioRepository;
import es.fdi.reservas.reserva.business.entity.Edificio;
import es.fdi.reservas.reserva.business.entity.Espacio;

@Service
public class EdificioService {
	
	EdificioRepository edificio_repository;
	
	@Autowired
	public EdificioService(EdificioRepository e_r)
	{
		this.edificio_repository=e_r;
	}
	
	public Edificio findEdificio(long edificioid)
	{
		return edificio_repository.findEdificio(edificioid);
	}
	
	public Page<Edificio> findEdificioByFacultadId(long facultadid, Pageable pageable)
	{
		return edificio_repository.findEdificioByFacultadId(facultadid, pageable);
	}

}
