/*
 * 06.08.2008 16:54:55
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

import com.eclipsesp.tplanner.core.security.Security.*;
import com.eclipsesp.tplanner.core.security.utils.*;
import com.eclipsesp.tplanner.web.*;
import com.eclipsesp.tplanner.web.form.*;

@Controller
@Permission(tournaments = SingleMask.CAN_READ_ALL, users = SingleMask.CAN_WRITE_ALL)
public class ReleaseNotesController {

	@RequestMapping(value = "/releasenotes*", method = RequestMethod.GET)
	public String getTournamentPage() {
		return this.getPageId();
	}

	protected String getPageId() {
		return MessageKeys.RELEASE_NOTES;
	}

	@ModelAttribute("pageid")
	protected PageCC createPageId() {
		PageCC p = new PageCC();
		p.setPageId(this.getPageId());
		return p;
	}

}
