/*
 * 6 Сер 2008 12:43:13
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
import com.eclipsesp.tplanner.web.utils.*;
import com.eclipsesp.tplanner.web.validators.*;

/**
 * Controller to send promt to unregistered users. To send more than 1 letter
 * separate addresses by ";"
 * 
 * @author akrumin
 */
@Controller
@RequestMapping(value = { "/promosend*" })
@Permission(users = SingleMask.CAN_NOTHING, tournaments = SingleMask.CAN_WRITE_SELF)
public class SendPromoCodeController {

	@Autowired
	TournamentManager tournamentManager;

	@Autowired
	IAccountManager accountManager;

	@Autowired
	IPromoManager promoManager;

	//@Autowired
	//org.springframework.mail.javamail.JavaMailSenderImpl mailSender;

	@Autowired
	InvationMessageBean promtData;

	@RequestMapping(method = RequestMethod.GET)
	public String getPromoPage(@ModelAttribute("promoform")
	PromoCC promoform, HttpServletRequest request,
	    @RequestParam(value = "id", required = false)
	    Integer tournamentId) {
		AccountWithPermission account =
		        (AccountWithPermission) request.getSession().getAttribute(
		                "account");
		if (account.getEmail() != null) {
			if (tournamentId != null) {
				promoform.setOwnerEmail(account.getEmail());
				promoform.setId(tournamentId);
				return getPageId();
			} else {
				return "redirect:tournaments.html";
			}
		} else {
			return getPageId();
		}
	}

	protected String getPageId() {
		return MessageKeys.PROMOSEND;
	}

	@ModelAttribute("pageid2")
	protected PageCC createPageId() {
		PageCC p = new PageCC();
		p.setPageId(getPageId());
		return p;
	}

	@ModelAttribute("promoform")
	protected PromoCC getPromo() {
		PromoCC p = new PromoCC();
		return p;
	}

	/**
	 * TODO - check users registered - done<br/> TODO - get tournament name-
	 * done<br/> TODO - send promt to registered users - done<br/> TODO - If
	 * promt sent then block resending
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String getPromoPage(@ModelAttribute("promoform")
	PromoCC promoform, Errors result, HttpServletRequest request) {
		AccountWithPermission account =
		        (AccountWithPermission) request.getSession().getAttribute(
		                "account");
		Tournament tour =
		        this.tournamentManager.getById((long) promoform.getId());
		long userId = tour.getAccountId();
		if (userId == account.getId()
		        || Security.checkPermissionMask(account.getPermission(),
		                Security.compileSecurityMask(SingleMask.CAN_READ_ALL,
		                        SingleMask.CAN_WRITE_ALL))) {
			PromoValidator validator = new PromoValidator();
			validator.validate(promoform, result, request);
			if (!result.hasErrors()) {
				StringTokenizer tokens =
				        new StringTokenizer(promoform.getEmail(), ";");
				while (tokens.hasMoreTokens()) {
					String email = tokens.nextToken();
					AccountWithPermission acc =
					        this.accountManager.getByEmail(email);
					if (acc == null) {
						String promocode = Security.encodePromoCode(email);
						String text =
						        Utils.compileText(this.promtData.getMainText(),
						                promoform.getOwnerEmail(),
						                tour.getTournamentName(),
						                this.promtData.getRegAddress()
						                        + "?promo=" + promocode);
						Promo promo = new Promo(promoform.getId(), promocode);
						Iterator<Promo> iterator =
						        this.promoManager.getByPromoCode(promocode).iterator();
						boolean errorFlag = false;
						while (iterator.hasNext()) {
							Promo temp = iterator.next();
							if (temp.getPromo().contentEquals(promo.getPromo())
							        && temp.getTournamentId() == promo.getTournamentId()) {
								errorFlag = true;
								result.reject("error.promosended");
							}
						}
						if (!errorFlag) {/*
							if (Utils.sendEmail(this.mailSender.getHost(),
							        this.mailSender.getPort(), email,
							        promoform.getOwnerEmail(), "Promt", text,
							        result)) {
								this.promoManager.create(promo);
							}*/
						}
					} else {
						result.reject("error.emailRegisteredError");
						result.reject("error.emailRegisteredErrorName", email);
						if (!this.tournamentManager.checkUser2Tournament(
						        Long.valueOf(promoform.getId()),
						        Long.valueOf(acc.getId()))) {
							this.tournamentManager.createUser2Tournament(
							        acc.getId(), promoform.getId(), 4);
						} else {
							result.reject("error.promosended");
						}
					}

				}
				promoform.setEmail("");
				return getPageId();
			} else {
				return getPageId();
			}
		} else {
			result.reject("error.incorrectOwnTournament");
		}
		return getPageId();
	}
}
