/*
 * 23.07.2008 15:56:43
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 163 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-07-24 18:42:01 +0300 (Чт, 24 июл 2008) $
 */

package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * <p>
 * Defines general contract to manage groups.
 * </p>
 * 
 * @author vpostrigan
 */
public interface IGroupManager {

	Collection<Group> getAll() throws DataAccessException;

	Group getGroup(int id) throws DataAccessException;

	Collection<Group> getGroup(String groupName) throws DataAccessException;

	void createGroup(String groupName) throws DataAccessException;

	void updateGroupName(int id, String newGroupName)
	    throws DataAccessException;

	void deleteGroup(int id) throws DataAccessException;
}
