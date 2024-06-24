/*
 * 24.07.2008 19:03:54
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 170 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-24 21:12:18 +0300 (Чт, 24 июл 2008) $
 */
package com.eclipsesp.tplanner.core.dao.jdbc;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * Game team implementation of JdbcDaoSupport for `gameteam` table access
 * 
 * @author Pavel Marinchev
 */
public class GameTeamJdbcDaoSupport
        extends AbstractGameJdbcDaoSupport<GameTeam> {

	@Override
	public GameTeam make() {
		return new GameTeam();
	}

}
