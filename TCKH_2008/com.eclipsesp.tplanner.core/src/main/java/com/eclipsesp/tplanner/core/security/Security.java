/*
 * 17 ��� 2008 19:06:52
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 904 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-14 16:34:27 +0300 (Чт, 14 авг 2008) $
 */
package com.eclipsesp.tplanner.core.security;

import java.io.*;
import java.lang.annotation.*;
import java.security.*;
import java.util.*;
import java.util.regex.*;

import org.apache.commons.logging.*;

import sun.misc.*;

import com.eclipsesp.tplanner.core.security.utils.*;

/**
 * Security utilities class. <br>
 * Contains just static methods.
 * 
 * @author akrumin
 */
public class Security {

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Permission {
		SingleMask users();

		SingleMask tournaments();
	}

	private static final String FORBIDDENSYMBOLS =
	        "` ' \" # % $ ? & ; * > < \\";

	/**
	 * Logger available to subclasses.
	 */
	protected final static Log logger = LogFactory.getLog(Security.class);

	/** Regular expression pattern for e-mail exploration */
	public static final Pattern EMAILPATTERN =
	        Pattern.compile("[a-zA-Z]{1}[a-zA-Z\\d\\u002E\\u005F0-9._-]+@([a-zA-Z0-9._-]+\\u002E){1,2}[a-zA-Z]{2,3}");

	/** Regular expression pattern for password exploration */
	public static final Pattern PASSPATTERN =
	        Pattern.compile("[\\w\\p{Punct}]{6,}");

	/** Regular expression pattern for zipcode exploration */
	public static final Pattern ZIPPATTERN = Pattern.compile("[0-9]{5,6}");

	/** Regular expression pattern for date exploration */
	public static final Pattern DATE_PATTERN =
	        Pattern.compile("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}");

	/**
	 * Generates permission data.
	 * 
	 * @param user -
	 *            set permission to users data
	 * @param tournament -
	 *            set permissions to tournament data
	 * @return mask to set access
	 */
	public static int compileSecurityData(SinglePermission user,
	    SinglePermission tournament) {
		int result = 0;
		result = Security.compileMask(user, result);
		result = Security.compileMask(tournament, result);
		return result;
	}

	/**
	 * Generates mask of permissions.
	 * 
	 * @param user -
	 *            set permission to users data
	 * @param tournament -
	 *            set permissions to tournament data
	 * @return mask to check access
	 */
	public static int compileSecurityMask(SingleMask user, SingleMask tournament) {
		int result = 0;
		result = Security.compileMask(user, result);
		result = Security.compileMask(tournament, result);
		return result;
	}

	/**
	 * Verification of user's access by mask
	 * 
	 * @param securityData -
	 *            user's permission
	 * @param permissionMask -
	 *            mask
	 * @return boolean value
	 */
	public static boolean checkPermissionMask(int securityData,
	    int permissionMask) {
		int temp = securityData &= permissionMask;
		return temp == permissionMask;
	}

	/**
	 * Generates mask from SinglePermission enumerator
	 * 
	 * @param permission -
	 *            users permission
	 * @param mask -
	 *            method generates mask from single parameter, so use previous
	 *            returned value or 0
	 * @return permission mask
	 */
	private static int compileMask(IMask permission, int mask) {
		mask = mask << 4;
		mask |= permission.getId();
		return mask;
	}

	/**
	 * check the correctness of users email address
	 * 
	 * @param source -
	 *            text, you need to check
	 * @param pattern -
	 *            regular expression. <br>
	 *            Use EMAILPATTERN or PASSPATTERN from this class
	 * @return boolean value
	 */
	public static boolean checkByPattern(String source, Pattern pattern) {
		Matcher matcher = pattern.matcher(source);
		return matcher.matches();
	}

	/**
	 * Generate hash from users password, to keep in database.
	 * 
	 * @param pass -
	 *            users password
	 * @return hash in String type
	 */
	public static String encodePass(String pass) {
		try {
			MessageDigest alg = MessageDigest.getInstance("MD5");
			// to make brute-force harder
			for (int i = 0; i < 100; i++) {
				alg.reset();
				StringBuffer buf;
				buf = new StringBuffer();
				byte hash[] = alg.digest(pass.getBytes("UTF-8"));
				// converting array of byte to StringBuffer
				for (byte b : hash) {
					if ((0xff & b) < 0x10) {
						buf.append("0" + Integer.toHexString((0xFF & b)));
					} else {
						buf.append(Integer.toHexString(0xFF & b));
					}
				}
				pass = buf.toString();
			}
			return pass;
		} catch (Exception ex) {
			Security.logger.error(ex);
		}
		return null;
	}

	/**
	 * Generates promocode which send's to user, as prompt
	 * 
	 * @param email -
	 *            Users E-mail value.
	 * @return promocode value.
	 */
	public static String encodePromoCode(String email) {
		BASE64Encoder algorithm = new BASE64Encoder();
		return algorithm.encode(email.getBytes());
	}

	/**
	 * decode's promocode
	 * 
	 * @param promoCode -
	 *            users promocode
	 * @return assigned e-mail
	 */
	public static String decodePromoCode(String promoCode) {
		BASE64Decoder algorithm = new BASE64Decoder();
		String result = "";
		try {
			result = new String(algorithm.decodeBuffer(promoCode), "UTF-8");
		} catch (IOException e) {
			Security.logger.error(e);
		}
		return result;
	}

	/**
	 * Checking all map elements for forbidden symbols. To prevent from sql
	 * injection attack
	 * 
	 * @param source -
	 *            map to check.
	 * @return return true if there is no forbidden symbols, and false if yes.
	 */
	public static boolean checkOnSQLInjection(Map<String, Object> source) {
		for (Object o : source.values()) {
			if (o != null) {
				StringTokenizer s =
				        new StringTokenizer(Security.FORBIDDENSYMBOLS, " ");
				while (s.hasMoreElements()) {
					if (o.toString().contains(s.nextToken())) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
