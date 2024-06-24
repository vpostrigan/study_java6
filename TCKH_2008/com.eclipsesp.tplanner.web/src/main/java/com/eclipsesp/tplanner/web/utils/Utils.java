/*
 * 6 Сер 2008 17:27:52
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 668 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-08 15:47:11 +0300 (РџС‚, 08 Р°РІРі 2008) $
 */
package com.eclipsesp.tplanner.web.utils;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.validation.*;

/**
 * Some utilities required for web part of application
 * 
 * @author akrumin
 */
public class Utils {
	/**
	 * Method sends email.
	 * 
	 * @param server -
	 *            SMTP server host
	 * @param port -
	 *            SMTP server port(usually 25)
	 * @param to -
	 *            E-mail address of receiver
	 * @param from -
	 *            E-mail address of sender
	 * @param theme -
	 *            Theme of letter
	 * @param text -
	 *            main text in html format
	 * @param error -
	 *            result set
	 * @return
	 */
	public static boolean sendEmail(String server, int port, String to,
	    String from, String theme, String text, Errors error) {
		try {
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", server);
			props.setProperty("mail.smtp.port", String.valueOf(port));
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(theme);
			Multipart mp = new MimeMultipart();
			MimeBodyPart txt = new MimeBodyPart();
			txt.setContent(text, "text/html");
			mp.addBodyPart(txt);
			msg.setContent(mp);
			Transport.send(msg);
		} catch (Exception e) {
			error.reject("error.emailSendingError");
			return false;
		}
		return true;
	}

	public static String compileText(String pattern, String...params) {
		String text = String.format(pattern, (Object[]) params);
		return text;

	}

}
