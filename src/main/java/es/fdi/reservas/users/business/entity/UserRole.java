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
package es.fdi.reservas.users.business.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.security.core.GrantedAuthority;

@Embeddable
public class UserRole implements GrantedAuthority, Serializable {

	@Basic
	@Column(length=20)
	private String role;
	
	UserRole() {
		
	}
	
	public UserRole(String role) {
		this.role = role;
	}
	
	public String getRole(){
		return this.role;
	}
	
	public String getAuthority() {
		return this.role;
	}
	
	public String rolToString(){
		switch (role){
		case "ROLE_USER":
			return "Usuario";
		case "ROLE_ADMIN":
			return "Administrador";
		case "ROLE_GESTOR":
			return "Gestor";
		default:
			return "";
		}
	}

}
