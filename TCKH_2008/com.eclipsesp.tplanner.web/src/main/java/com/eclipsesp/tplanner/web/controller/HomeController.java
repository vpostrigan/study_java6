/*
 * 22.07.2008 15:39:25
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 419 $
 * $Author: vpostrigan_tckh $
 * $Date: 2008-08-01 17:24:17 +0300 (Пт, 01 авг 2008) $
 */
package com.eclipsesp.tplanner.web.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.web.*;

/**
 * Home page controller
 * 
 * @author Pavel Marinchev
 */
@Controller
public class HomeController {

	@RequestMapping(value = { "/index*" }, method = RequestMethod.GET)
	public String getHomePage() {
		return this.getPageId();
	}

	protected String getPageId() {
		return MessageKeys.INDEX;
	}

	/*
	 * protected String getPageId() { return IPageIdentifiers.TPLANNER_TITLE; }
	 */
}
