/*
 * 30 Лип 2008 14:53:53
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 910 $
 * $Author: rshportko_tckh $
 * $Date: 2008-08-14 18:07:47 +0300 (Чт, 14 авг 2008) $
 */
package com.eclipsesp.tplanner.web.controller;

import javax.servlet.http.*;

import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.facade.impl.*;
import com.eclipsesp.tplanner.core.security.Security.*;
import com.eclipsesp.tplanner.core.security.utils.*;
import com.eclipsesp.tplanner.web.*;
import com.eclipsesp.tplanner.web.form.*;
import com.eclipsesp.tplanner.web.validators.*;

/**
 * Add tournament page controller.
 * 
 * @author rshportko
 */
@Controller
@Permission(tournaments = SingleMask.CAN_WRITE_SELF, users = SingleMask.CAN_NOTHING)
public class AddTournamentController {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	TournamentManager tournamentManager;

	@Autowired
	AccountManager accountManager;

	@Autowired
	StatusManager statusManager;

	@RequestMapping(value = "/addTournament*", method = RequestMethod.GET)
	public String getAddTournamentPage() {
		return getPageId();
	}

	protected String getPageId() {
		return MessageKeys.ADD_TOURNAMENT;
	}

	@ModelAttribute("addTournament")
	protected AddTournamentCC getAddForm() {
		AddTournamentCC addTournamentForm = new AddTournamentCC();
		return addTournamentForm;
	}

	@RequestMapping(method = RequestMethod.POST, params = "action=Cancel")
	public String cancelAdd(HttpServletRequest request) {

		AccountWithPermission account =
		        (AccountWithPermission) request.getSession().getAttribute(
		                "account");

		if (TournamentsController.checkMyTournamentsPage(request)) {
			return "redirect:tournaments?ownerId=" + account.getId();
		} else {
			return "redirect:tournaments";
		}
	}

	/**
	 * Method get's fields from addTournament page, validating them. If
	 * validation is passed - tournament saves in database.
	 * 
	 * @param addTournamentForm -
	 *            form filled by user
	 * @param result -
	 *            return validation errors to the form
	 * 
	 * @return path to jsp content
	 */
	@RequestMapping(method = RequestMethod.POST, params = "action=Accept")
	public String processSubmit(@ModelAttribute("addTournament")
	AddTournamentCC addTournamentForm, Errors result,
	    HttpServletRequest request,
	    @RequestParam(value = "captcha", required = false)
	    String captcha) {
		if (!captcha.equalsIgnoreCase((String) request.getSession().getAttribute(
		        nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY))) {
			result.reject("error.incorrectCaptcha");
		}
		AddTournamentValidator validator = new AddTournamentValidator();
		validator.validate(addTournamentForm, result);
		if(!tournamentManager.getByName(addTournamentForm.getTournamentName()).isEmpty()){
			result.reject("error.existingTournament");
		}
		if (!result.hasErrors()) {
			Account account =
			        (Account) request.getSession().getAttribute("account");
			try {
				addTournamentForm.setAccountId(account.getId());
				Tournament tournament = addTournamentForm;
				this.tournamentManager.create(tournament);
			} catch (Exception e) {
				this.logger.error(e);
				return "redirect:error.html";
			}
			if (TournamentsController.checkMyTournamentsPage(request)) {
				return "redirect:tournaments?ownerId=" + account.getId();
			} else {
				return "redirect:tournaments";
			}
		} else {
			return getPageId();
		}
	}
}
