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
package es.fdi.reservas.reserva.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.fdi.reservas.reserva.business.boundary.ReservaSolapadaException;
import es.fdi.reservas.users.business.boundary.UserPasswordException;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(ReservaSolapadaException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult reservaSolapadaException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {    
 
		return new ErrorResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
	
	
	@ExceptionHandler(UserPasswordException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult userPasswordException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {    
 
		return new ErrorResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
	
}
