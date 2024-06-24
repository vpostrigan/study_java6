/*
 * 23.07.2008 16:07:14
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 163 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-07-24 18:42:01 +0300 (Чт, 24 июл 2008) $
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
 * An implementation of {@link IGroupManager} facade. In particular, it deals
 * with JDBC DAO support classes
 * </p>
 * 
 * @author vpostrigan
 */
public class GroupManager
        implements IGroupManager {

	@Autowired
	protected GroupJdbcDaoSupport daoSupport;

	public Collection<Group> getAll() throws DataAccessException {
		return this.daoSupport.getAll();
	}

	public Group getGroup(int id) throws DataAccessException {
		return this.daoSupport.getGroup(id);
	}

	public Collection<Group> getGroup(String groupName)
	    throws DataAccessException {
		return this.daoSupport.getGroup(groupName);
	}

	public void createGroup(String groupName) throws DataAccessException {
		this.daoSupport.createGroup(groupName);
	}

	public void updateGroupName(int id, String newGroupName)
	    throws DataAccessException {

		this.daoSupport.updateGroupName(id, newGroupName);
	}

	public void deleteGroup(int id) throws DataAccessException {
		this.daoSupport.deleteGroup(id);
	}

	public GroupJdbcDaoSupport getDaoSupport() {
		return this.daoSupport;
	}

	public void setDaoSupport(GroupJdbcDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
}
