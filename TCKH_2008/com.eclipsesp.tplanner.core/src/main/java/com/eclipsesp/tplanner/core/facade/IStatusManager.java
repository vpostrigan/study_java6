/*
 * 29 Лип 2008 16:51:04
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 630 $
 * $Author: rshportko_tckh $
 * $Date: 2008-08-08 10:13:22 +0300 (Пт, 08 авг 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;

public interface IStatusManager {

	public Status getById(Long id)
	throws DataAccessException;

public void create(Status status) throws DataAccessException;

public void delete(Long id) throws DataAccessException;
	
}
