/*
 * 25.07.2008 10:45:58
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 286 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-29 16:47:20 +0300 (Вт, 29 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.dao.jdbc.*;
import com.eclipsesp.tplanner.core.facade.*;

public class RoleManager
        implements IRoleManager {

	@Autowired
	protected RoleJdbcDaoSupport daoSupport;

	public Collection<Role> getAll() throws DataAccessException {
		return this.daoSupport.getAll();
	}

	public void createPermissionDefinition(String roleName, int permission)
	    throws DataAccessException {

		this.daoSupport.createPermissionDefinition(roleName, permission);
	}

	public Role getByRoleName(String roleName) throws DataAccessException {
		return this.daoSupport.getByRoleName(roleName);
	}

	public Role getByPermissionDefinition(int permission)
	    throws DataAccessException {
		return this.daoSupport.getByPermissionDefinition(permission);
	}

	public void changeRole(String roleName, String newRoleName, int permission)
	    throws DataAccessException {
		this.daoSupport.changeRole(roleName, newRoleName, permission);
	}

	public void deleteRole(String roleName) throws DataAccessException {
		this.daoSupport.deleteRole(roleName);
	}

	public void setDaoSupport(RoleJdbcDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}

}
