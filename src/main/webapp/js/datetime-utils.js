/*
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
'use strict';
// Requiere que esté disponible momentjs
var es = es || {};
es.ucm = es.ucm || {};
es.ucm.fdi = es.ucm.fdi || {};
es.ucm.fdi.dateUtils = (function (undefined){
	var DATE_FORMAT = 'DD/MM/YYYY HH:mm';
	
	function toIso8601(date) {
		return moment(date, DATE_FORMAT).format();
	}
	
	function fromIso8601(date) {
		return moment(date).format(DATE_FORMAT);
	}
	
	return {
		'toIso8601' : toIso8601,
		'fromIso8601' : fromIso8601
	}
})();