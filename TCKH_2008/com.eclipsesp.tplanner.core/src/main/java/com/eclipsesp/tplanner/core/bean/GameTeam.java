/*
 * 17 Лип 2008 17:33:47
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 243 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-28 11:17:14 +0300 (Пн, 28 июл 2008) $
 */
package com.eclipsesp.tplanner.core.bean;

import java.io.*;
import java.util.*;

/**
 * Business bean for GameTeam of Group and Team implements {@link Serializable}
 * interface
 * 
 * @author dproshkin
 */

public class GameTeam
        extends Game {

	private static final long serialVersionUID = -4910298029187201366L;

	public GameTeam() {
		// Do nothing
	}

	public GameTeam(long id, long group_Id, long winner, Date date,
	        int score1, int score2, long participant1, long participant2) {
		super(id, group_Id, winner, date, score1, score2, participant1,
		        participant2);
	}

	public GameTeam make() {
		return new GameTeam();
	}

	@Override
	public String getTableName() {
		return "gameteam";
	}
}
