/*
 * 18 Лип 2008 11:32:08
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

import com.eclipsesp.tplanner.core.utils.*;

/**
 * Business bean for Game of Group and Team, User implements
 * {@link Serializable} interface Shouldn't be ever instanced. Appropriate
 * subclasses should be used instead
 * 
 * @author dproshkin
 */
public abstract class Game
        extends Bean
        implements Serializable, ClazzFactory<Game> {

	public Game() {
		// Do nothing
	}

	public Game(long id, long group_Id, long winner, Date date, int score_1,
	        int score_2, long participant1, long participant2) {
		super(id);
		this.group_Id = group_Id;
		this.winner = winner;
		this.date = date;
		this.score_1 = score_1;
		this.score_2 = score_2;
		this.participant1 = participant1;
		this.participant2 = participant2;
	}

	/** Initializes bean's identifier */
	private long group_Id;

	/** The winner in pair */
	private long winner;

	/** Dates of game */
	private Date date;

	/** Result of a team1 in game */
	private int score_1;

	/** Result of a team2 in game */
	private int score_2;

	/** Command the participant 1 */
	private long participant1;

	/** Command the participant 2 */
	private long participant2;

	/** Returns bean's identifier */
	public long getGroupId() {
		return this.group_Id;
	}

	/** Sets bean's identifier */
	public void setGroupId(long groupId) {
		this.group_Id = groupId;
	}

	/** Returns winner in pair */
	public long getWinner() {
		return this.winner;
	}

	/** Sets winner in pair */
	public void setWinner(long winner) {
		this.winner = winner;
	}

	/** Returns dates of game */
	public Date getDate() {
		return this.date;
	}

	/** Sets dates of game */
	public void setDate(Date date) {
		this.date = date;
	}

	/** Returns result of a team1 in game */
	public int getScore1() {
		return this.score_1;
	}

	/** Sets result of a team1 in game */
	public void setScore1(int score1) {
		this.score_1 = score1;
	}

	/** Returns result of a team2 in game */
	public int getScore2() {
		return this.score_2;
	}

	/** Sets result of a team2 in game */
	public void setScore2(int score2) {
		this.score_2 = score2;
	}

	/** Returns command the participant 1 */
	public long getParticipant1() {
		return this.participant1;
	}

	/** Sets command the participant 1 */
	public void setParticipant1(long participant1) {
		this.participant1 = participant1;
	}

	/** Returns command the participant 2 */
	public long getParticipant2() {
		return this.participant2;
	}

	/** Sets command the participant 2 */
	public void setParticipant2(long participant2) {
		this.participant2 = participant2;
	}

	@Override
	/** Reception of the unique identifier */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result =
		        prime * result + (this.date == null ? 0 : this.date.hashCode());
		result = prime * result + (int) (this.group_Id ^ this.group_Id >>> 32);
		result = prime * result + this.score_1;
		result = prime * result + this.score_2;
		result =
		        prime * result
		                + (int) (this.participant1 ^ this.participant1 >>> 32);
		result =
		        prime * result
		                + (int) (this.participant2 ^ this.participant2 >>> 32);
		result = prime * result + (int) (this.winner ^ this.winner >>> 32);
		return result;
	}

	@Override
	/** Check on identity of objects */
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Game)) {
			return false;
		}
		final Game other = (Game) obj;
		if (this.date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!this.date.equals(other.date)) {
			return false;
		}
		if (this.group_Id != other.group_Id) {
			return false;
		}
		if (this.score_1 != other.score_1) {
			return false;
		}
		if (this.score_2 != other.score_2) {
			return false;
		}
		if (this.participant1 != other.participant1) {
			return false;
		}
		if (this.participant2 != other.participant2) {
			return false;
		}
		if (this.winner != other.winner) {
			return false;
		}
		return true;
	}

	public abstract String getTableName();
}
