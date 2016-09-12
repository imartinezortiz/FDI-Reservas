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
package es.fdi.reservas.reserva.business.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import es.fdi.reservas.users.business.entity.User;

@Entity
public class GrupoReserva {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="GrupoReservaId")
	private Long id;
	
	@Size(min=3, message = "Error: \"${validatedValue}\" es demasiado corto. Mínimo 3 caracteres.")
	private String nombreCorto;
	
	@Size(min=10, message = "Error: \"${validatedValue}\" es demasiado corto. Mínimo 10 caracteres.")
	private String nombreLargo;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="grupoReserva")
	private Set<Reserva> reservasGrupo;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="UserId")
	private User user;
	
	public GrupoReserva(){
		
	}
	
	public GrupoReserva(String nombreCorto, String nombreLargo, User user){
		this.nombreCorto = nombreCorto;
		this.nombreLargo = nombreLargo;
		this.reservasGrupo = new HashSet<Reserva>();
		this.user = user;
	}


	public void addReserva(Reserva reserva){
		reservasGrupo.add(reserva);
	}
	
	public Long getId() {
		return id;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public String getNombreLargo() {
		return nombreLargo;
	}

	public void setNombreLargo(String nombreLargo) {
		this.nombreLargo = nombreLargo;
	}

	public Set<Reserva> getReservasGrupo() {
		return reservasGrupo;
	}


	public void setReservasGrupo(Set<Reserva> reservasGrupo) {
		this.reservasGrupo = reservasGrupo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean equals(GrupoReserva gr){
		return (this.id.equals(gr.id) 
				|| this.nombreCorto.equals(gr.nombreCorto));
	}
	
	
}
