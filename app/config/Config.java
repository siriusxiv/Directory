/******************************************************************************

    A Directory project for ACCENTS, A Club of CENtrale for Travelling Students
    Copyright (C) 2013  ACCENTS

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
    
*******************************************************************************/
package config;

import play.Play;

/**
 * Just there to load info from application.conf more quickly
 * @author malik
 *
 */
public class Config {
	
	/**
	 * Get the value of arg0 inside the application.conf file
	 * and return it as a string
	 * @param arg0
	 * @return string value of arg0
	 */
	public static String getString(String arg0){
		return Play.application().configuration().getString(arg0);
	}
}
