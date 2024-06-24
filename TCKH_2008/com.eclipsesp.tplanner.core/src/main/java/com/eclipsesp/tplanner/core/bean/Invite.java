/*
 * 11 Сер 2008 17:41:29
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.core.bean;

public class Invite {
	protected String tournamentName;

	protected int status;

	protected int tournamentid;

	protected String statusName;

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getTournamentName() {
		return this.tournamentName;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTournamentid() {
		return this.tournamentid;
	}

	public void setTournamentid(int tournamentid) {
		this.tournamentid = tournamentid;
	}

}
