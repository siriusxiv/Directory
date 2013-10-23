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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@SuppressWarnings("serial")
@Entity
public class Secured extends Model{
	@Id
	public Long id;
	public String salt;
	@Column(columnDefinition = "VARCHAR(128)")
	public String passHash;
	@Column(columnDefinition = "VARCHAR(8)")
	public String centraleUID;
	@Column(columnDefinition = "VARCHAR(128)")
	public String centralePassHash;

	public static Finder<Long, Secured> find = new Finder<Long, Secured>(Long.class, Secured.class);
	
	/**
	 * Creates an instance of the class secured.
	 * The salt is randomly generated here. The passwords' hash value is then calculated.
	 * @param chosenPassword
	 * @param centraleUID
	 * @param centralePassword
	 */
	public Secured(String chosenPassword, String centraleUID, String centralePassword){
		try {
			this.salt=Security.getSalt();
			this.passHash=Security.hash(salt+chosenPassword);
			this.centraleUID=centraleUID;
			this.centralePassHash=Security.hash(salt+centraleUID);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Salt generator and hash calculator are here
	 * @author malik
	 *
	 */
	private static class Security {
		/**
		 * Generate a 255-character-long random string.
		 * @return a random 255-character-long string
		 */
		private static String getSalt(){
			String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			Random rng = new Random();
			char[] text = new char[255];
			for (int i = 0; i < 255; i++){
				text[i] = characters.charAt(rng.nextInt(characters.length()));
			}
			return new String(text);
		}
		/**
		 * Calculates the hash value of message (SHA-512)
		 * @param message a string input
		 * @return SHA-512 hash value of input
		 * @throws NoSuchAlgorithmException
		 */
		private static String hash(String message) throws NoSuchAlgorithmException{
			MessageDigest md;
			try {
				md= MessageDigest.getInstance("SHA-512");
				md.update(message.getBytes());
				byte[] mb = md.digest();
				String out = "";
				for (int i = 0; i < mb.length; i++) {
					byte temp = mb[i];
					String s = Integer.toHexString(new Byte(temp));
					while (s.length() < 2) {
						s = "0" + s;
					}
					s = s.substring(s.length() - 2);
					out += s;
				}
				return out;
			} catch (NoSuchAlgorithmException e) {
				System.out.println("ERROR: " + e.getMessage());
				throw e;
			}
		}
		/**
		 * Calculates s1's hash value and compare it to hashValue
		 * @param s1
		 * @param hashValue
		 * @return true or false
		 */
		private static boolean compareHash(String s1, String hashValue){
			try {
				return hash(s1).equals(hashValue);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
	
	/**
	 * Check whether a couple login/password is right.
	 * This is _not_ Centrale's login and password.
	 * @param login
	 * @param passw
	 * @return true or false
	 */
	public static boolean isPasswordRight(String login, String passw){
		Person person = Person.find.byId(login);
		if(person==null)	return false;
		else return Security.compareHash(person.secured.salt+passw,person.secured.passHash);
	}
	/**
	 * For people who already registered on the site, check whether their Centrale
	 * password is right even after their account was discontinued.
	 * If the right password is given, this method returns the person to whom this
	 * account belongs or belonged. Else, it returns null.
	 * @param login
	 * @param passw
	 * @return the person in the database whose login and passwords are the one given
	 */
	public static Person isCentralePasswordRight(String login, String passw){
		List<Secured> secureds = find.where().eq("centraleUID",login).findList();
		for(Secured s : secureds){
			if(Security.compareHash(s.salt+passw,s.centralePassHash)){
				return Person.find.where().eq("secured",s).findUnique();
			}
		}
		return null;
	}
}
