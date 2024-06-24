/*
 * 29 Лип 2008 16:52:28
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 749 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-11 18:32:38 +0300 (Пн, 11 авг 2008) $
 */
package com.eclipsesp.tplanner.core.facade.impl;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.*;

public class StatusManager
        implements IStatusManager {

	@Autowired
	protected StatusJdbcDaoSupport daoSupport;

	public void create(Status status) throws DataAccessException {
		this.daoSupport.create(status);
	}

	public void delete(Long id) throws DataAccessException {
		this.daoSupport.delete(id);
	}

	public Status getById(Long id) throws DataAccessException {
		return this.daoSupport.getById(id);
	}

	public StatusJdbcDaoSupport getDaoSupport() {
		return this.daoSupport;
	}

	public void setDaoSupport(StatusJdbcDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

}
