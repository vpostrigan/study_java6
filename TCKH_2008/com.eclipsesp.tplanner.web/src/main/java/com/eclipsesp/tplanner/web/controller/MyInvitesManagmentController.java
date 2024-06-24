/*
 * 11 Сер 2008 17:29:15
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

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.facade.*;
import com.eclipsesp.tplanner.core.facade.impl.*;
import com.eclipsesp.tplanner.core.security.Security.*;
import com.eclipsesp.tplanner.core.security.utils.*;
import com.eclipsesp.tplanner.web.*;

@Controller
@RequestMapping(value = { "/myinvites*" })
@Permission(users = SingleMask.CAN_WRITE_SELF, tournaments = SingleMask.CAN_WRITE_SELF)
public class MyInvitesManagmentController {
	@Autowired
	ITournamentManager tournamentManager;

	@Autowired
	AccountManager accountManager;

	@Autowired
	StatusManager statusManager;

	@RequestMapping(method = RequestMethod.GET)
	public String getInvitesPage() {

		return getPageId();
	}

	protected String getPageId() {
		return MessageKeys.INVITES;
	}

	@ModelAttribute("invites")
	public Collection<Invite> getInvites(HttpServletRequest request) {
		AccountWithPermission account =
		        (AccountWithPermission) request.getSession().getAttribute(
		                "account");
		Collection<Invite> collection = new ArrayList<Invite>();
		if (!(account.getEmail() == null)) {
			collection = this.tournamentManager.getByAccountId(account.getId());
			if (collection == null) {
				collection = new ArrayList<Invite>();
			}
			Iterator<Invite> i = collection.iterator();
			int count = 0;
			while (i.hasNext()) {
				if (i.next().getStatus() == 4) {
					count++;
				}
			}
			request.getSession().setAttribute("invitescount", count);
		}
		return collection;
	}

	@RequestMapping(method = RequestMethod.GET, params = "action=reject")
	public String rejectionOfInvite(HttpServletRequest request,
	    @RequestParam(value = "id", required = false)
	    int id) {
		AccountWithPermission account =
		        (AccountWithPermission) request.getSession().getAttribute(
		                "account");
		User2Tournament c =
		        this.tournamentManager.getUser2TournamentFull(account.getId(),
		                (long) id, (long) 5);
		if (c == null) {
			this.tournamentManager.deleteUser2Tournament(account.getId(), id);
		}
		return "redirect:myinvites.html";
	}

	@RequestMapping(method = RequestMethod.GET, params = "action=accept")
	public String acceptOfInvite(HttpServletRequest request,
	    @RequestParam(value = "id", required = false)
	    int id) {
		AccountWithPermission account =
		        (AccountWithPermission) request.getSession().getAttribute(
		                "account");

		User2Tournament c =
		        this.tournamentManager.getUser2TournamentFull(account.getId(),
		                (long) id, (long) 5);
		if (c == null) {
			this.tournamentManager.updateUser2Tournament(account.getId(), id, 5);
		}
		return "redirect:myinvites.html";
	}
}
