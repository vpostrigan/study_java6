/*
 * 30 ��� 2008 10:18:13
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 808 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-12 16:20:03 +0300 (Вт, 12 авг 2008) $
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
import com.eclipsesp.tplanner.core.facade.impl.*;
import com.eclipsesp.tplanner.core.security.*;
import com.eclipsesp.tplanner.web.*;
import com.eclipsesp.tplanner.web.form.*;
import com.eclipsesp.tplanner.web.validators.*;

/**
 * Registration form controller. Binded on pages contains registration preffix
 * 
 * @author akrumin
 */
@Controller
public class RegistrationController {
	@Autowired
	IAccountManager accountManager;

	@Autowired
	IRoleManager roleManager;

	@Autowired
	IPromoManager promoManager;

	@Autowired
	TournamentManager tournamentManager;

	@RequestMapping(value = { "/registration*" }, method = RequestMethod.GET)
	public String getRegistrationPage() {
		return getPageId();
	}

	protected String getPageId() {
		return MessageKeys.REGISTRATION;
	}

	@ModelAttribute("pageid")
	protected PageCC createPageId() {
		PageCC p = new PageCC();
		p.setPageId(getPageId());
		return p;
	}

	@ModelAttribute("regform")
	protected RegistrationCC getRegForm(
	    @RequestParam(value = "promo", required = false)
	    String promo) {
		RegistrationCC reg = new RegistrationCC();
		if (promo != null) {
			reg.setEmail(Security.decodePromoCode(promo));
		}
		return reg;
	}

	/**
	 * Method gets fields from registration page, validating them. If validation
	 * is passed then account data saves in session, and account saves in the
	 * database.
	 * 
	 * @param regform -
	 *            form filled by user
	 * @param result -
	 *            return validation errors to the form
	 * @param acc -
	 *            session attribute
	 * @return path to jsp content
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("regform")
	RegistrationCC regform, Errors result, HttpServletRequest request,
	    @RequestParam(value = "captcha", required = false)
	    String captcha) {
		if (!captcha.equalsIgnoreCase((String) request.getSession().getAttribute(
		        nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY))) {
			result.reject("error.incorrectCaptcha");
		}
		RegistrationValidator validator = new RegistrationValidator();
		validator.validate(regform, result);
		if (this.accountManager.getByEmail(regform.getEmail()) != null) {
			result.reject("error.userExist");
		}
		if (this.accountManager.getByNick(regform.getNick()) != null) {
			result.reject("error.nickExist");
		}
		if (!result.hasErrors()) {
			AccountWithPermission account = regform;
			try {
				this.accountManager.newAccount(account);
				account = this.accountManager.getByEmail(account.getEmail());
				String promoCheck =
				        Security.encodePromoCode(account.getEmail());
				Collection<Promo> promo =
				        this.promoManager.getByPromoCode(promoCheck);
				Iterator<Promo> iterator = promo.iterator();
				while (iterator.hasNext()) {
					Promo promoTemp = iterator.next();
					this.tournamentManager.createUser2Tournament(
					        account.getId(), (int) promoTemp.getTournamentId(),
					        4);
					this.promoManager.delete(promoTemp);
				}

			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:error.html";
			}
			request.getSession().setAttribute("account", account);
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
			int data =
			        this.roleManager.getByRoleName(account.getRole_Name()).getPermission_Def();
			request.getSession().setAttribute("permission", data);
			return "redirect:index.html";
		} else {
			regform.setPassword("");
			regform.setPasswordconf("");
			return getPageId();
		}
	}
}
