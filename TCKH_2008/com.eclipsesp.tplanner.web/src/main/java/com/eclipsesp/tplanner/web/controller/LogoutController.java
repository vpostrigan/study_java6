/*
 * 5 Сер 2008 13:50:31
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.controller;

import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.core.bean.*;

/**
 * Controller for users login
 * 
 * @author akrumin
 */
@Controller
public class LogoutController {

	@RequestMapping(value = { "/logout*" })
	public String doLogout(HttpServletRequest request) {
		AccountWithPermission account = new AccountWithPermission();
		account.setNick("Guest");
		request.getSession().setAttribute("account", account);
		request.getSession().setAttribute("permission", 0);
		return "redirect:index.html";
	}

}
