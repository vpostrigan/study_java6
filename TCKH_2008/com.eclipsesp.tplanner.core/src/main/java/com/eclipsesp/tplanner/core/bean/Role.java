/*
 * 17 Лип 2008 18:54:59
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 425 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-01 19:08:38 +0300 (Пт, 01 авг 2008) $
 */
package com.eclipsesp.tplanner.core.bean;

import java.io.*;

/**
 * Business bean for Role of User. Bean it is used for definition of the rights
 * of the user implements {@link Serializable} interface
 * 
 * @author dproshkin
 */
public class Role
        implements Serializable {

	private static final long serialVersionUID = 912118122326472190L;

	public Role() {
		// Do nothing
	}

	public Role(String roleName, int permissionDef) {
		super();
		this.roleName = roleName;
		this.permission_Def = permissionDef;
	}

	/** The name of a role of the user */
	private String roleName;

	/** Value of a role of the user */
	private int permission_Def;

	/** Returns the name of a role of the user */
	public String getRoleName() {
		return this.roleName;
	}

	/** Sets the name of a role of the user */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/** Returns value of a role of the user */
	public int getPermission_Def() {
		return this.permission_Def;
	}

	/** Sets value of a role of the user */
	public void setPermission_Def(int permissionDef) {
		this.permission_Def = permissionDef;
	}

	@Override
	/** Reception of the unique identifier */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.permission_Def;
		result =
		        prime
		                * result
		                + (this.roleName == null ? 0 : this.roleName.hashCode());
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
		if (!(obj instanceof Role)) {
			return false;
		}
		final Role other = (Role) obj;
		if (this.permission_Def != other.permission_Def) {
			return false;
		}
		if (this.roleName == null) {
			if (other.roleName != null) {
				return false;
			}
		} else if (!this.roleName.equals(other.roleName)) {
			return false;
		}
		return true;
	}

}
