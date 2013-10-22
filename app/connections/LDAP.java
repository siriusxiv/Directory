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
package connections;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import config.Config;
import config.TestMode;

/**
 * LDAP requests to connect
 * Beware! You cannot connect to LDAP from the wifi of Centrale as the port is blocked!
 * You must be connected via Ethernet.
 * @author malik
 *
 */
public class LDAP{
	private String server;
	
	/**
	 * Open a connection to the LDAP server stored in the configuration file
	 */
	public LDAP(){
		this.server = Config.getString("ldap.url");
	}
	
	/**
	 * Open a connection to any server given you provide his url
	 * @param serverUrl server's url
	 */
	public LDAP(String serverUrl){
		this.server=serverUrl;
	}
	
	/**
	 * Check that the couple login/passw is correct for Centrale LDAP server.
	 * @param login
	 * @param passw
	 * @return true or false
	 */
	public boolean check(String login, String passw){
		if(TestMode.isEnabled() && Config.getString("test.login").equals(login) && Config.getString("test.passw").equals(passw)){
			return true;
		}
		Hashtable<String,String> properties = new Hashtable<String,String>();
		properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		properties.put(Context.PROVIDER_URL, server);
		properties.put(Context.SECURITY_AUTHENTICATION, "simple");
		properties.put(Context.SECURITY_PRINCIPAL, "uid="+login+", ou=people, dc=ec-nantes, dc=fr");
		properties.put(Context.SECURITY_CREDENTIALS, passw);
        try {
        	System.out.println("trying to get identified...");
        	DirContext ctx = new InitialDirContext(properties);
        	System.out.println("identified");
            ctx.close();
		    return true;
        } catch (NamingException e) {
        	System.out.println(e.getMessage());
            return false;
        }
	}
}
