/*
 * 4 Сер 2008 11:34:11
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

/**
 * Controller output information about any unknown error.
 * 
 * @author akrumin
 */
@Controller
public class ErrorController {
	@RequestMapping(value = { "/error*" }, method = RequestMethod.GET)
	public String getRegistrationPage() {
		return getPageId();
	}

	protected String getPageId() {
		return MessageKeys.ERROR;
	}

}
