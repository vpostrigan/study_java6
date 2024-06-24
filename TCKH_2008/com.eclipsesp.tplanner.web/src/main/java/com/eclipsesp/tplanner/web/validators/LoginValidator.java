/*
 * 31 Лип 2008 13:28:47
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 380 $
 * $Author: akrumin_tckh $
 * $Date: 2008-07-31 19:07:43 +0300 (Р§С‚, 31 РёСЋР» 2008) $
 */
package com.eclipsesp.tplanner.web.validators;

import org.springframework.validation.*;

import com.eclipsesp.tplanner.core.security.*;
import com.eclipsesp.tplanner.core.utils.*;
import com.eclipsesp.tplanner.web.form.*;

/**
 * Validate data from login controller.
 * 
 * @author akrumin
 */
public class LoginValidator {

	public void validate(LoginCC loginForm, Errors errors) {
		validateEMail(loginForm, errors);
		validateSQLInjection(loginForm, errors);
	}

	public void validateEMail(LoginCC loginForm, Errors errors) {
		if (!Security.checkByPattern(loginForm.getEmail(),
		        Security.EMAILPATTERN)) {
			errors.reject("error.incorrectEmail");
		}
	}

	public void validateSQLInjection(LoginCC loginForm, Errors errors) {
		if (!Security.checkOnSQLInjection(DataUtilities.convertClassToMap(
		        LoginCC.class, loginForm))) {
			errors.reject("error.sqlInjection");
		}
	}
}
