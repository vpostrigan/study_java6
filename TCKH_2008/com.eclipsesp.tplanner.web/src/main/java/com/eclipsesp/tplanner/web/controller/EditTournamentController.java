/*
 * 1 Сер 2008 17:03:30
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 915 $
 * $Author: rshportko_tckh $
 * $Date: 2008-08-14 18:18:46 +0300 (Чт, 14 авг 2008) $
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
 * Edit tournament page controller. 
 * 
 * @author rshportko
 */
@Controller
@Permission(tournaments = SingleMask.CAN_WRITE_SELF, users = SingleMask.CAN_NOTHING)
public class EditTournamentController {
	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	TournamentManager tournamentManager;

	@Autowired
	AccountManager accountManager;

	@Autowired
	StatusManager statusManager;

	/** Id to update */
	private Long editId;

	@RequestMapping(value = "/editTournament*", method = RequestMethod.GET, params = "!editTournamentId")
	public String getEditTournamentPage() {
		return getPageId();
	}

	protected String getPageId() {
		return MessageKeys.EDIT_TOURNAMENT;
	}

	@RequestMapping(method = RequestMethod.POST, params = "action=Cancel")
	public String cancelEdit(HttpServletRequest request) {
		
		AccountWithPermission account =
	        (AccountWithPermission) request.getSession().getAttribute(
	                "account");
		
		if(TournamentsController.checkMyTournamentsPage(request)){
		return "redirect:tournaments?ownerId="+account.getId();	
		}
		else{
			return "redirect:tournaments";
		}
	}

	/** Executing operation */
	@RequestMapping(method = RequestMethod.POST, params = "action=Accept")
	public String processSubmit(@ModelAttribute("editTournament")
	AddTournamentCC editTournament, Errors result, HttpServletRequest request) {
		AddTournamentValidator validator = new AddTournamentValidator();
		validator.validate(editTournament, result);		
		if (!result.hasErrors()) {
			try {
				editTournament.setId(this.editId);
				Tournament tournament = editTournament;
				this.tournamentManager.update(tournament);
			} catch (Exception e) {
				this.logger.error(e);
				return "redirect:error.html";
			}
			AccountWithPermission account =
		        (AccountWithPermission) request.getSession().getAttribute(
		                "account");
			
			if(TournamentsController.checkMyTournamentsPage(request)){
			return "redirect:tournaments?ownerId="+account.getId();	
			}
			else{
				return "redirect:tournaments";
			}
		} else {
			return getPageId();
		}
	}

	/** Setup edit form */
	@RequestMapping(value = "/editTournament", method = RequestMethod.GET)
	@ModelAttribute("editTournament")
	protected AddTournamentCC getEditForm(
	    @RequestParam(value = "editTournamentId")
	    Long editTournamentId) {
		try {
			if (editTournamentId != null) {
				this.editId = editTournamentId;
				AddTournamentCC editTournamentForm = new AddTournamentCC();
				Tournament tournament =
				        this.tournamentManager.getById(editTournamentId);
				editTournamentForm.setTournamentName(tournament.getTournamentName());
				editTournamentForm.setGameKind(tournament.getGameKind());
				editTournamentForm.setViewStartDate(editTournamentForm.formatDate(tournament.getStartDate()));
				editTournamentForm.setViewFinishDate(editTournamentForm.formatDate(tournament.getFinishDate()));
				editTournamentForm.setVisibility(tournament.getVisibility());
				editTournamentForm.setDescription(tournament.getDescription());
				return editTournamentForm;
			} else {
				return null;
			}
		} catch (Exception e) {
			this.logger.error(e);
		}
		return null;
	}
}
