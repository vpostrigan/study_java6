/*
 * 08.08.2008 16:13:17
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.web.*;
import com.eclipsesp.tplanner.web.form.*;

@Controller
// @Permission(tournaments = SingleMask.CAN_NOTHING, users =
// SingleMask.CAN_NOTHING)
public class NewsController {

	@RequestMapping(value = "/news*", method = RequestMethod.GET)
	public String getTournamentPage() {
		return this.getPageId();
	}

	protected String getPageId() {
		return MessageKeys.NEWS;
	}

	@ModelAttribute("pageid")
	protected PageCC createPageId() {
		PageCC p = new PageCC();
		p.setPageId(this.getPageId());
		return p;
	}

}
