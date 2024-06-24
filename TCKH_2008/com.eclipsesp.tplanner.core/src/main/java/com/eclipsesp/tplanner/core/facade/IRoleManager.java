/*
 * 25.07.2008 10:14:52
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 284 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-29 16:47:06 +0300 (Вт, 29 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;

public interface IRoleManager {

	Collection<Role> getAll() throws DataAccessException;

	void createPermissionDefinition(String roleName, int permission);

	Role getByRoleName(String roleName) throws DataAccessException;

	Role getByPermissionDefinition(int permission) throws DataAccessException;

	void changeRole(String roleName, String newRoleName, int permission)
	    throws DataAccessException;

	void deleteRole(String roleName) throws DataAccessException;

}
