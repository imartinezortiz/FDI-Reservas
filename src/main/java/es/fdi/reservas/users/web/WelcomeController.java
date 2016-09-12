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
package es.fdi.reservas.users.web;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.fdi.reservas.users.business.boundary.UserService;
import es.fdi.reservas.users.business.entity.User;
import es.fdi.reservas.users.business.entity.UserRole;

@Controller
public class WelcomeController {


	private UserService user_service;
	
	@Autowired
	public WelcomeController(UserService us){
		user_service = us;
	}
	
	@RequestMapping(value = "/welcome")
    public String welcome() {

        User user = user_service.getCurrentUser();
        List<String> roles = user.getRoles();

    	if (roles.contains("ROLE_ADMIN")) {
            return "redirect:/admin/administrar";
        }
        else if (roles.contains("ROLE_GESTOR")) {
            return "redirect:/gestor/administrar";
        }
        else{
        	 return "redirect:/mis-reservas/page/1";
        }
       
    }
}
