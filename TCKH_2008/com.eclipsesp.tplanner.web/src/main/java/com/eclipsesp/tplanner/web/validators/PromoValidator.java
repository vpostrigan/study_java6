/*
 * 6 Сер 2008 15:52:21
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: $
 * $Author: $
 * $Date: $
 */
package com.eclipsesp.tplanner.web.validators;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.validation.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.security.*;
import com.eclipsesp.tplanner.web.form.*;

@Component
public class PromoValidator {

	public void validate(PromoCC promo, Errors errors,
	    HttpServletRequest request) {
		validateOwnEmail(promo, errors, request);
		validateEmail(promo, errors, request);

	}

	public void validateOwnEmail(PromoCC promo, Errors errors,
	    HttpServletRequest request) {
		AccountWithPermission account =
		        (AccountWithPermission) request.getSession().getAttribute(
		                "account");
		if (!promo.getOwnerEmail().equalsIgnoreCase(account.getEmail())) {
			errors.reject("error.incorrectOwnEmail");
		}
	}

	public void validateEmail(PromoCC promo, Errors errors,
	    HttpServletRequest request) {
		if (promo.getEmail() != null) {
			StringTokenizer tokens = new StringTokenizer(promo.getEmail(), ";");
			if (tokens.hasMoreTokens()) {
				while (tokens.hasMoreTokens()) {
					String email = tokens.nextToken();
					if (!Security.checkByPattern(email, Security.EMAILPATTERN)) {
						errors.reject("error.incorrectSingleEmail");
						return;
					}
					if (email.equalsIgnoreCase(promo.getOwnerEmail())) {
						errors.reject("error.selfSendingError");
						return;
					}
				}
			} else {
				errors.reject("error.incorrectEmail");
			}
		} else {
			errors.reject("error.incorrectEmail");
		}
	}

}
