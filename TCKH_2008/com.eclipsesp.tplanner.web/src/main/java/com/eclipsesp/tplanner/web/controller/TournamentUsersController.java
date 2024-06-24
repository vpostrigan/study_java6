/*
 * 08.08.2008 15:44:26
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 907 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-08-14 17:08:11 +0300 (Чт, 14 авг 2008) $
 */
package com.eclipsesp.tplanner.web.controller;

import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.facade.*;
import com.eclipsesp.tplanner.core.facade.impl.*;
import com.eclipsesp.tplanner.core.security.*;
import com.eclipsesp.tplanner.core.security.Security.*;
import com.eclipsesp.tplanner.core.security.utils.*;
import com.eclipsesp.tplanner.web.*;
import com.eclipsesp.tplanner.web.bean.*;
import com.eclipsesp.tplanner.web.form.*;

/**
 * Show page with all available users by role name and tournament
 * 
 * @author vpostrigan
 */
@Permission(users = SingleMask.CAN_READ_ALL, tournaments = SingleMask.CAN_NOTHING)
@Controller
public class TournamentUsersController {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	IAccountManager accountManager;

	@Autowired
	TournamentManager tournamentManager;

	@Autowired
	StatusManager statusManager;

	int maskForAdmin =
	        Security.compileSecurityMask(SingleMask.CAN_NOTHING,
	                SingleMask.CAN_WRITE_ALL);

	@RequestMapping(value = "/tournamentUsers.*", method = RequestMethod.GET)
	public String getUsers(HttpServletRequest request,
	    @RequestParam(value = "tournamentId", required = false)
	    int tournamentId) {

		if (this.checkPermission(request, tournamentId)) {
			return MessageKeys.TOURNAMENT_USERS;
		} else {
			return "redirect:tournaments.jsp";
		}
	}

	// //////////////////////////////////////////

	@ModelAttribute("tournaments")
	protected TournamentsView getForm() {
		TournamentsView tournamentsView = new TournamentsView();
		return tournamentsView;
	}

	protected String getPageId() {
		return MessageKeys.TOURNAMENT_USERS;
	}

	@ModelAttribute("pageid")
	protected PageCC createPageId() {
		PageCC p = new PageCC();
		p.setPageId(this.getPageId());
		return p;
	}

	// //////////////////////////////////////////

	// / For tournament
	@ModelAttribute("tournamentId")
	public String tournamentId(
	    @RequestParam(value = "tournamentId", required = false)
	    String tournamentId) {

		return tournamentId;
	}

	@RequestMapping(method = RequestMethod.POST, params = "action=Delete")
	public String delete(@ModelAttribute("tournaments")
	TournamentsView tournamentsView, Errors errors, HttpServletRequest request,
	    @RequestParam(value = "tournamentId", required = false)
	    int tournamentId) {

		if (this.checkPermission(request, tournamentId)) {
			try {
				for (Long t : tournamentsView.getId()) {
					this.tournamentManager.deleteUser2Tournament(t.longValue(),
					        tournamentId);
				}
				return "redirect:" + this.getPageId() + ".jsp";
			} catch (Exception e) {

				this.logger.error("error.cannotAddUser2Tournament: \n" + e);
				errors.reject("error.cannotDeleteUser2Tournament");
				return "redirect:" + this.getPageId() + ".jsp";
			}
		} else {
			errors.reject("error.incorrectOwnTournament");
			return "redirect:tournaments.jsp";
		}
	}

	@RequestMapping(method = RequestMethod.POST, params = "action=Add")
	public String add(@ModelAttribute("tournaments")
	TournamentsView tournamentsView, Errors errors, HttpServletRequest request,
	    @RequestParam(value = "tournamentId", required = false)
	    int tournamentId) {

		if (this.checkPermission(request, tournamentId)) {
			try {
				for (Long t : tournamentsView.getId()) {
					this.tournamentManager.createUser2Tournament(t.longValue(),
					        tournamentId, 4);
				}
				return "redirect:" + this.getPageId() + ".jsp";
			} catch (Exception e) {

				this.logger.error("error.cannotAddUser2Tournament: \n" + e);
				errors.reject("error.cannotAddUser2Tournament");
				return "redirect:" + this.getPageId() + ".jsp";
			}
		} else {

			errors.reject("error.incorrectOwnTournament");
			return "redirect:tournaments.jsp";
		}
	}

	@ModelAttribute("tournamentName")
	public String tName(@RequestParam(value = "tournamentId", required = false)
	String tournamentId) {

		Tournament tournament = new Tournament();

		try {
			if (tournamentId != null) {
				tournament =
				        this.tournamentManager.getById(Long.parseLong(tournamentId));
			}

			return tournament.getTournamentName();
		} catch (Exception e) {
			return tournament.getTournamentName();
		}
	}

	// /////////////// getAllNotFromUser2Tournament

	@ModelAttribute("usersTournament")
	public Collection<UsersView> listUsersForT(
	    @RequestParam(value = "tournamentId", required = false)
	    String tournamentId) {

		Collection<Account> users = new ArrayList<Account>();

		try {
			if (tournamentId != null) {
				users =
				        this.accountManager.getAllNotFromUser2Tournament(Integer.parseInt(tournamentId));
			}

			return this.getAccounts(users);
		} catch (Exception e) {
			return this.getAccounts(users);
		}
	}

	/**
	 * Returned collection by l (chose letter), All collection, search String
	 * 
	 * @param searchCC -
	 *            search string
	 * @param l -
	 *            chose letter
	 * @return Collection of UsersView
	 */
	@ModelAttribute("users")
	public Collection<UsersView> listUsers(
	    @RequestParam(value = "tournamentId", required = false)
	    String tournamentId) {

		Collection<Account> users = new ArrayList<Account>();

		try {
			if (tournamentId != null) {
				users =
				        this.accountManager.getAllFromUser2Tournament(Integer.parseInt(tournamentId));
			} else {
				users = this.accountManager.getAll();
			}

			return this.getAccounts(users);
		} catch (Exception e) {
			return this.getAccounts(users);
		}
	}

	/** Convert from Collection&lt;Account&gt; to Collection&lt;UsersView&gt; */
	public Collection<UsersView> getAccounts(Collection<Account> users) {

		Collection<UsersView> usersViews = new ArrayList<UsersView>();

		for (Account a : users) {

			String description =
			        this.setDescription(a.getCity(), a.getAddress());

			String lastName = a.getLastname();

			if (lastName == null || lastName.equals("")) {
				lastName = "&nbsp;";
			}

			UsersView usersView =
			        new UsersView(a.getId(), a.getNick(), a.getFirstName(),
			                lastName, description);
			usersViews.add(usersView);
		}

		return usersViews;
	}

	/** Description == City + address */
	public String setDescription(String city, String address) {
		String description = "";

		if (city != null) {
			description = city;
		}

		if (address == null || address.equals("")) {
			if (description == null || description.equals("")) {
				description = "&nbsp;";
			}
		} else {
			if (description == null || description.equals("")) {
				description = address;
			} else {
				description = description + " " + address;
			}
		}

		return description;
	}

	/** Check the permission to view user list */
	public boolean checkPermission(HttpServletRequest request, int tournamentId) {
		AccountWithPermission account =
		        (AccountWithPermission) request.getSession().getAttribute(
		                "account");
		try {
			long userId =
			        this.tournamentManager.getById((long) tournamentId).getAccountId();

			if (userId == account.getId()) {
				return true;
			} else {
				int data = account.getPermission();
				if (Security.checkPermissionMask(data, this.maskForAdmin)) {
					return true;
				}
				return false;
			}
		} catch (Exception e) {
			this.logger.error("\nNot found tournament:\n" + e);
			return false;
		}
	}
}
