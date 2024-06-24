/*
 * 9 Сер 2008 16:35:34
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.form;

public class RoleCC {
	protected String roleName;

	protected int userAccess;

	protected int tournamentAccess;

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getUserAccess() {
		return this.userAccess;
	}

	public void setUserAccess(int userAccess) {
		this.userAccess = userAccess;
	}

	public int getTournamentAccess() {
		return this.tournamentAccess;
	}

	public void setTournamentAccess(int tournamentAccess) {
		this.tournamentAccess = tournamentAccess;
	}

}
