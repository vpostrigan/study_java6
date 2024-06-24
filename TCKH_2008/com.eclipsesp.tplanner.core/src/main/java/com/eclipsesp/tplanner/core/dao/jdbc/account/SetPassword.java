/*
 * 22.07.2008 16:02:37
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 203 $
 * $Author: akrumin_tckh $
 * $Date: 2008-07-25 14:56:07 +0300 (Пт, 25 июл 2008) $
 */
package com.eclipsesp.tplanner.core.dao.jdbc.account;

import java.sql.*;

import javax.sql.*;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.*;

/**
 * Sets password for the specified account.
 * 
 * @author Pavel Marinchev
 */
public class SetPassword
        extends SqlUpdate {

	/**
	 * Uses special "password(?)" SQL function
	 */
	public SetPassword(DataSource ds) {
		super(ds, "update `account` set `password` = password(?) "
		        + "where `id` = ?");
		declareParameter(new SqlParameter("password", Types.VARCHAR));
		declareParameter(new SqlParameter("id", Types.BIGINT));
		compile();
	}

	public void setPassword(long id, String password) {
		this.update(new Object[] { password, id });
	}
}
