/*
 * 22.07.2008 16:02:37
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 158 $
 * $Author: akrumin_tckh $
 * $Date: 2008-07-24 16:12:38 +0300 (Чт, 24 июл 2008) $
 */
package com.eclipsesp.tplanner.core.dao.jdbc;

import javax.sql.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.simple.*;
import org.springframework.jdbc.core.support.*;

/**
 * Base class for all classes in project that will implement
 * {@link JdbcDaoSupport} interface
 * 
 * @author Pavel Marinchev
 */
public abstract class AbstractSimpleJdbcDaoSupport
        extends SimpleJdbcDaoSupport {

	@Autowired
	public void init(DataSource ds) {
		this.setDataSource(ds);
	}

	@Override
	protected abstract void initDao() throws Exception;

}
