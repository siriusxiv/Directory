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

import play.Play;

import com.typesafe.plugin.MailerAPI;
import com.typesafe.plugin.MailerPlugin;

/**
 * A mail object
 * @author malik
 *
 */
public class Mail{
	//A recipient string looks like this : "Firstname Surname <mailAdress@foo.com>"
	private String recipient;
	private String from;
	private String subject;
	private String content;
	private boolean html;
	
	/**
	 * Create a text-only mail (no HTML)
	 * @param recipient
	 * @param subject
	 * @param from
	 * @param content
	 */
	public Mail(String recipient, String from, String subject, String content){
		this.recipient=recipient;
		this.subject=subject;
		this.from=from;
		this.content=content;
		this.html=false;
	}
	/**
	 * Create a mail with or without html
	 * @param recipient
	 * @param subject
	 * @param from
	 * @param content
	 * @param html
	 */
	public Mail(String recipient, String from, String subject, String content, boolean html){
		this.recipient=recipient;
		this.subject=subject;
		this.from=from;
		this.content=content;
		this.html=html;
	}
	
	/**
	 * Send the mail
	 */
	public void send(){
			MailerAPI mail = Play.application().plugin(MailerPlugin.class).email();
			mail.addRecipient(recipient);
			mail.setSubject(subject);
			mail.addFrom(from);
			if(html)	mail.sendHtml(content);
			else		mail.send(content);
	}
	
}

