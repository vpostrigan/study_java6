/*
 * 11.08.2008 11:50:46
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
import javax.servlet.jsp.jstl.core.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.web.*;

/**
 * Change locale
 * 
 * @author vpostrigan
 */
@Controller
public class ChangeLocaleController {

	// private final Log logger = LogFactory.getLog(this.getClass().getName());

	public String getPageId() {
		return MessageKeys.INDEX;
	}

	@RequestMapping(value = "/index.changeLanguage", method = RequestMethod.GET)
	public String getUsers(HttpServletRequest request,
	    @RequestParam(value = "language", required = false)
	    String language) {

		HttpSession session = request.getSession();
		Locale locale = request.getLocale();

		locale = new Locale(language);

		// reset the Struts locale
		// session.setAttribute(Globals.LOCALE_KEY, locale);

		// reset the JSTL locale
		Config.set(session, Config.FMT_LOCALE, locale);

		return this.getPageId();
	}
}
