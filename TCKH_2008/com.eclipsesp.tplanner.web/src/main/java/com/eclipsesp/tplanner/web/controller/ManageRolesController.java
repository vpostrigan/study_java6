/*
 * 4 Сер 2008 15:42:33
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.controller;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.core.facade.*;
import com.eclipsesp.tplanner.core.security.*;
import com.eclipsesp.tplanner.core.security.Security.*;
import com.eclipsesp.tplanner.core.security.utils.*;
import com.eclipsesp.tplanner.web.*;
import com.eclipsesp.tplanner.web.form.*;

@Controller
@RequestMapping(value = "/manageroles*")
@Permission(tournaments = SingleMask.CAN_WRITE_ALL, users = SingleMask.CAN_WRITE_ALL)
public class ManageRolesController {

	@Autowired
	IRoleManager roleManager;

	@RequestMapping(method = RequestMethod.GET)
	public String getMyAccountView(@ModelAttribute("roleForm")
	RoleCC role) {

		return getPageId();
	}

	protected String getPageId() {
		return MessageKeys.MANAGEROLES;
	}

	@ModelAttribute("roleForm")
	protected RoleCC getRole() {
		RoleCC r = new RoleCC();
		return r;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String getRolePage(@ModelAttribute("roleForm")
	RoleCC roleform, Errors result, HttpServletRequest request) {
		SinglePermission users = SinglePermission.CAN_NOTHING;
		users.setId(roleform.getUserAccess());
		SinglePermission tournaments = SinglePermission.CAN_NOTHING;
		tournaments.setId(roleform.getUserAccess());
		this.roleManager.createPermissionDefinition(roleform.getRoleName(),
		        Security.compileSecurityData(users, tournaments));
		return getPageId();
	}
}
