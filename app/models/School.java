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
package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@SuppressWarnings("serial")
@Entity
public class School extends Model{
	@Id
	public Long id;
	public String name;
	@ManyToOne
	public City city;
	
	public static Finder<Long, School> find = new Finder<Long, School>(Long.class, School.class);
	
	public School(String name, City city){
		this.name=name;
		this.city=city;
	}
}
