/**
 * This file is part of reservas Maven Webapp.
 *
 * reservas Maven Webapp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * reservas Maven Webapp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with reservas Maven Webapp.  If not, see <http://www.gnu.org/licenses/>.
 */
package es.fdi.reservas.reserva.business.control;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.fdi.reservas.reserva.business.entity.GrupoReserva;

public interface GrupoReservaRepository extends JpaRepository<GrupoReserva, Long>{

	@Query("from GrupoReserva g where (lower(g.nombreCorto) like lower(concat('%',:nombreCorto, '%')) AND (g.user.id = :idUsuario))")
	public List<GrupoReserva> getGruposPorTagName(@Param("nombreCorto") String nombreCort,@Param("idUsuario") Long idUsuario);
	
	public List<GrupoReserva> findByUserId(Long idUsuario);

}
