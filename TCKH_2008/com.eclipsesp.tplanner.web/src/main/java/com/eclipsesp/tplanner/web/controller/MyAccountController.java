/*
 * 29 Лип 2008 19:14:07
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 505 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-08-05 10:17:18 +0300 (Вт, 05 авг 2008) $
 */
package com.eclipsesp.tplanner.web.controller;

import javax.servlet.http.*;

import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.facade.*;
import com.eclipsesp.tplanner.core.security.Security.*;
import com.eclipsesp.tplanner.core.security.utils.*;
import com.eclipsesp.tplanner.web.*;

@Controller
@Permission(tournaments = SingleMask.CAN_WRITE_SELF, users = SingleMask.CAN_READ_SELF)
public class MyAccountController {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	ITournamentManager tournamentManager;

	@Autowired
	IAccountManager accountManager;

	@Autowired
	IStatusManager statusManager;

	@RequestMapping(value = "/myaccount*")
	protected String getPageId() {
		return MessageKeys.ACCOUNT;
	}

	@ModelAttribute("accountForm")
	public Account listTournaments(HttpServletRequest request) {
		Account account =
		        (Account) request.getSession().getAttribute("account");
		this.logger.info("Object account in session"+account);
		return account;
	}

}
