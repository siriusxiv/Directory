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

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;

@SuppressWarnings("serial")
@Entity
public class Person extends Model{
	@Id
	public String username;
	public String surname;
	public String firstname;
	public Calendar birthday;
	public String mail;
	@Column(columnDefinition = "VARCHAR(20)")
	public String phone;
	public String skype;
	@Column(columnDefinition = "TEXT")
	public String description;
	public boolean accountActivated;
	@OneToOne
	public Secured passw;
	@ManyToOne
	public School schoolOfOrigin;
	@ManyToOne
	public School currentSchool;
	@ManyToOne
	public Country nationality;
	@ManyToOne
	public City city;
	@OneToOne
	public Photo photo;
	@OneToMany
	public List<CV> cvs;
	@OneToOne
	public Options options;
	@OneToOne
	public Secured secured;
	
	public static Finder<String, Person> find = new Finder<String, Person>(String.class, Person.class);
	
	public Person(String username, String surname, String firstname, Calendar birthday, String mail){
		this.username=username;
		this.surname=surname;
		this.firstname=firstname;
		this.birthday=birthday;
		this.mail=mail;
	}
	
	public void activateAccount(){
		accountActivated=true;
		save();
	}
	
	
}
