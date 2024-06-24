/*
 * 24.07.2008 10:28:15
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 202 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-07-25 14:52:33 +0300 (Пт, 25 июл 2008) $
 */
package com.eclipsesp.tplanner.core.facade;

import java.util.*;

import org.springframework.dao.*;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * <p>
 * Defines general contract to manage stages.
 * </p>
 * 
 * @author vpostrigan
 */
public interface IStageManager {

	Collection<Stage> getAll() throws DataAccessException;

	Stage getStage(int id) throws DataAccessException;

	Collection<Stage> getStage(String stageName) throws DataAccessException;

	void createStage(Stage s) throws DataAccessException;

	void updateStageName(int id, String newStageName)
	    throws DataAccessException;

	void deleteStage(int id) throws DataAccessException;
}
