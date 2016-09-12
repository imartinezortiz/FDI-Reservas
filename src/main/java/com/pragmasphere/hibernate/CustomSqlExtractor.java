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
package com.pragmasphere.hibernate;

import java.io.IOError;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor;

/**
 * Solución suboptima para la importación de datos con una codificación diferente a la del sistema.
 * Otra solución sería utilizar el parámetro javax.persistence.sql-load-script-source que admite un {@link java.io.Reader}
 *   
 * @author Toilal
 * @see http://stackoverflow.com/questions/8966030/hibernate-jpa-import-sql-utf8-characters-corrupted
 *
 */
public class CustomSqlExtractor extends MultipleLinesSqlCommandExtractor {

  /**
	 * @see java.io.Serializable 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String SOURCE_CHARSET = "UTF-8";

  @Override
  public String[] extractCommands(final Reader reader) {
      String[] lines = super.extractCommands(reader);

      Charset charset = Charset.defaultCharset();
      if (!charset.equals(Charset.forName(SOURCE_CHARSET))) {
          for (int i = 0; i < lines.length; i++) {
              try {
                  lines[i] = new String(lines[i].getBytes(), SOURCE_CHARSET);
              } catch (UnsupportedEncodingException e) {
                  throw new IOError(e);
              }
          }
      }

      return lines;
  }
}