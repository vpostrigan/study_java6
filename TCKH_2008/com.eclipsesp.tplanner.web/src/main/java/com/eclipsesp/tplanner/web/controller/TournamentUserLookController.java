/*
 * 13.08.2008 15:47:34
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.controller;

import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.facade.*;
import com.eclipsesp.tplanner.core.facade.impl.*;
import com.eclipsesp.tplanner.core.security.Security.*;
import com.eclipsesp.tplanner.core.security.utils.*;
import com.eclipsesp.tplanner.web.*;
import com.eclipsesp.tplanner.web.bean.*;

/**
 * Show page with all tournament users
 * 
 * @author vpostrigan
 */
@Permission(users = SingleMask.CAN_READ_ALL, tournaments = SingleMask.CAN_NOTHING)
@Controller
public class TournamentUserLookController {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	IAccountManager accountManager;

	@Autowired
	TournamentManager tournamentManager;

	@Autowired
	StatusManager statusManager;

	protected String getPageId() {
		return MessageKeys.TOURNAMENT_USERS_LOOK;
	}

	@RequestMapping(value = "/tournamentUsersLook.*", method = RequestMethod.GET)
	public String getUsers(HttpServletRequest request,
	    @RequestParam(value = "tournamentId", required = false)
	    int tournamentId) {

		if (this.checkPermission(request, tournamentId)) {
			return this.getPageId();
		} else {
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

	@ModelAttribute("tournamentId")
	public String tournamentId(
	    @RequestParam(value = "tournamentId", required = false)
	    String tournamentId) {

		return tournamentId;
	}

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

			String visibility =
			        this.tournamentManager.getById((long) tournamentId).getVisibility();

			if (userId == account.getId()) {
				return true;
			} else {
				if (visibility.equalsIgnoreCase("Public")) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			this.logger.error("\nNot found tournament:\n" + e);
			return false;
		}
	}
}
