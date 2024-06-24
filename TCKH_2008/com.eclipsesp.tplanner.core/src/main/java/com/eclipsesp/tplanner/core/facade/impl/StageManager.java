/*
 * 24.07.2008 10:31:26
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 202 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-07-25 14:52:33 +0300 (Пт, 25 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.*;

/**
 * <p>
 * An implementation of {@link IStageManager} facade. In particular, it deals
 * with JDBC DAO support classes
 * </p>
 * 
 * @author vpostrigan
 */
public class StageManager
        implements IStageManager {

	@Autowired
	protected StageJdbcDaoSupport daoSupport;

	public Collection<Stage> getAll() throws DataAccessException {
		return this.daoSupport.getAll();
	}

	public Stage getStage(int id) throws DataAccessException {
		return this.daoSupport.getStage(id);
	}

	public Collection<Stage> getStage(String stageName)
	    throws DataAccessException {
		return this.daoSupport.getStage(stageName);
	}

	public void createStage(Stage s) throws DataAccessException {
		this.daoSupport.createStage(s);
	}

	public void updateStageName(int id, String newStageName)
	    throws DataAccessException {

		this.daoSupport.updateStageName(id, newStageName);
	}

	public void deleteStage(int id) throws DataAccessException {
		this.daoSupport.deleteStage(id);
	}

	public StageJdbcDaoSupport getDaoSupport() {
		return this.daoSupport;
	}

	public void setDaoSupport(StageJdbcDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
}
