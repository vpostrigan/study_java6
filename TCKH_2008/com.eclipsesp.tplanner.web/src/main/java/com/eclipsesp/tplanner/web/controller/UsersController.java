/*
 * 31.07.2008 12:43:46
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.controller;

import java.util.*;

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
import com.eclipsesp.tplanner.web.form.*;

/**
 * Show page with all available users by role name
 * 
 * @author vpostrigan
 */
@Permission(users = SingleMask.CAN_READ_ALL, tournaments = SingleMask.CAN_NOTHING)
@Controller
public class UsersController {

	// private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	IAccountManager accountManager;

	@Autowired
	TournamentManager tournamentManager;

	@RequestMapping(value = "/users.*", method = RequestMethod.GET)
	public String getUsers() {
		return this.getPageId();
	}

	// //////////////////////////////////////////

	@ModelAttribute("searchUsersForm")
	protected SearchCC getForm() {
		SearchCC searchCC = new SearchCC();
		return searchCC;
	}

	protected String getPageId() {
		return MessageKeys.USERS;
	}

	@ModelAttribute("pageid")
	protected PageCC createPageId() {
		PageCC p = new PageCC();
		p.setPageId(this.getPageId());
		return p;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("searchUsers")
	SearchCC searchCC) {

		return this.getPageId();
	}

	// //////////////////////////////////////////

	@ModelAttribute("letters")
	public Collection<LettersView> listLetters() {

		Collection<LettersView> lettersView = new ArrayList<LettersView>();

		String letters[] =
		        { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
		                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
		                "X", "Y", "Z" };

		for (String element : letters) {
			lettersView.add(new LettersView(element));
		}

		return lettersView;
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
	public Collection<UsersView> listUsers(@ModelAttribute("searchUsers")
	SearchCC searchCC, @RequestParam(value = "l", required = false)
	String l, @RequestParam(value = "tournamentId", required = false)
	String tournamentId) {

		Collection<Account> users = new ArrayList<Account>();
		String search = searchCC.getSearch();

		try {
			if (search == null) {
				if (l != null) {

					return this.getAccountByNickFirstLetter(l);
				} else {

					if (tournamentId != null) {
						users =
						        this.accountManager.getAllFromUser2Tournament(Integer.parseInt(tournamentId));
					} else {
						users = this.accountManager.getAll();
					}

					return this.getAccounts(users);
				}
			} else {

				String s = "%" + searchCC.getSearch() + "%";

				users =
				        this.accountManager.searchByNickSurnameNameDescription(
				                s, s, s, s, s);
				return this.getAccounts(users);

			}
		} catch (Exception e) {
			return this.getAccounts(users);
		}
	}

	/** Get all account by chose letter */
	public Collection<UsersView> getAccountByNickFirstLetter(String l) {

		Collection<Account> users;

		if (l.equals("#") || l.equals("%23")) {
			users =
			        this.accountManager.getByNickFirstLetter("^[^{a-z}]",
			                "^[^{A-Z}]");
		} else {
			users =
			        this.accountManager.getByNickFirstLetter("^[{"
			                + l.toLowerCase() + "}]", "^[{" + l.toUpperCase()
			                + "}]");
		}

		return this.getAccounts(users);
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
}
