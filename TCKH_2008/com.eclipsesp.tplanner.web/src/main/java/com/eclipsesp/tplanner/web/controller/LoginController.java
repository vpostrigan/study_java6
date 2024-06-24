/*
 * 28 Лип 2008 18:41:24
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 808 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-12 16:20:03 +0300 (Р’С‚, 12 Р°РІРі 2008) $
 */
package com.eclipsesp.tplanner.web.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.facade.*;
import com.eclipsesp.tplanner.core.security.*;
import com.eclipsesp.tplanner.core.security.Security.*;
import com.eclipsesp.tplanner.core.security.utils.*;
import com.eclipsesp.tplanner.web.*;
import com.eclipsesp.tplanner.web.form.*;
import com.eclipsesp.tplanner.web.validators.*;

/**
 * Login form controller. Binded on every page contains login prefix
 * 
 * @author akrumin
 */
@Controller
@RequestMapping(value = { "/login*" })
@SessionAttributes("account")
@Permission(users = SingleMask.CAN_READ_SELF, tournaments = SingleMask.CAN_NOTHING)
public class LoginController {
	@Autowired
	IAccountManager accountManager;

	@Autowired
	IRoleManager roleManager;

	@Autowired
	ITournamentManager tournamentManager;

	@RequestMapping(method = RequestMethod.GET)
	public String getLoginPage(String email) {

		return getPageId();
	}

	protected String getPageId() {
		return MessageKeys.LOGIN;
	}

	@ModelAttribute("loginform")
	protected LoginCC getLogin() {
		LoginCC l = new LoginCC();
		return l;
	}

	@ModelAttribute("pageid")
	protected PageCC createPageId() {
		PageCC p = new PageCC();
		p.setPageId(getPageId());
		return p;
	}

	/**
	 * Method gets fields from login page, validating them. If validation is
	 * passed then account data saves in session.
	 * 
	 * @param loginForm -
	 *            form filled by user
	 * @param result -
	 *            return validation errors to the form
	 * @param acc -
	 *            session attribute
	 * @return path to jsp content
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("loginform")
	LoginCC loginForm, Errors result, HttpServletRequest request) {
		loginForm.setPassword(Security.encodePass(loginForm.getPassword()));
		LoginValidator validator = new LoginValidator();
		validator.validate(loginForm, result);
		if (!result.hasErrors()) {
			AccountWithPermission account = null;
			try {
				account = this.accountManager.getByEmail(loginForm.getEmail());
			} catch (Exception e) {
				result.reject("error.passwordWrong");
				return getPageId();
			}
			if (account == null) {
				result.reject("error.passwordWrong");
				return getPageId();
			}
			if (account.getPassword().equals(loginForm.getPassword())) {
				request.getSession().setAttribute("account", account);
				int data =
				        this.roleManager.getByRoleName(account.getRole_Name()).getPermission_Def();
				request.getSession().setAttribute("permission", data);
				Collection<Invite> collection =
				        this.tournamentManager.getByAccountId(account.getId());
				Iterator<Invite> i = collection.iterator();
				int count = 0;
				while (i.hasNext()) {
					if (i.next().getStatus() == 4) {
						count++;
					}
				}
				request.getSession().setAttribute("invitescount", count);
				return "redirect:index.html";
			} else {
				result.reject("error.passwordWrong");
				return getPageId();
			}

		} else {
			loginForm.setPassword("");
			return getPageId();
		}

	}

}
