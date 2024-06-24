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
 * Game user implementation of JdbcDaoSupport for `gameuser` table access
 * 
 * @author Pavel Marinchev
 */
public class GameUserJdbcDaoSupport
        extends AbstractGameJdbcDaoSupport<GameUser> {

	@Override
	public GameUser make() {
		return new GameUser();
	}

}
