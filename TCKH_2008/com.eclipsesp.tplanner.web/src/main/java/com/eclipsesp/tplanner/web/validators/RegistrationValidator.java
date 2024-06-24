/*
 * 30 ��� 2008 11:13:01
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 928 $
 * $Author: akrumin_tckh $
 * $Date: 2008-08-15 11:19:31 +0300 (Пт, 15 авг 2008) $
 */
package com.eclipsesp.tplanner.web.validators;

import org.apache.commons.logging.*;
import org.springframework.validation.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.security.*;
import com.eclipsesp.tplanner.core.utils.*;
import com.eclipsesp.tplanner.web.form.*;

/**
 * Validate data from registration controller.
 * 
 * @author akrumin
 */
public class RegistrationValidator {

	private final Log logger = LogFactory.getLog(getClass());

	public void validate(RegistrationCC regForm, Errors errors) {
		validateEMail(regForm, errors);
		validatePassword(regForm, errors);
		validateName(regForm, errors);
		validateNick(regForm, errors);
		validateZip(regForm, errors);
		validateSQLInjection(regForm, errors);
		validateLength(regForm, errors);
	}

	public void validatePassword(RegistrationCC regForm, Errors errors) {
		if (regForm.getPassword().compareTo(regForm.getPasswordconf()) == 0) {
			if (Security.checkByPattern(regForm.getPassword(),
			        Security.PASSPATTERN)) {
				regForm.setPassword(Security.encodePass(regForm.getPassword()));
				regForm.setPasswordconf(Security.encodePass(regForm.getPassword()));
			} else {
				errors.reject("error.incorrectPassword");
			}
		} else {
			errors.reject("error.incorrectConfirmation");
		}

	}

	public void validatePasswordIsEmpty(RegistrationCC regForm, Errors errors) {
		if (regForm.getPassword().isEmpty()
		        && regForm.getPasswordconf().isEmpty()) {
			return;
		}
		if (regForm.getPassword().compareTo(regForm.getPasswordconf()) == 0) {
			if (Security.checkByPattern(regForm.getPassword(),
			        Security.PASSPATTERN)) {
				regForm.setPassword(Security.encodePass(regForm.getPassword()));
				regForm.setPasswordconf(Security.encodePass(regForm.getPassword()));
			} else {
				errors.reject("error.incorrectPassword");
			}
		} else {
			errors.reject("error.incorrectConfirmation");
		}

	}

	public void validateEMail(RegistrationCC regForm, Errors errors) {
		if (!Security.checkByPattern(regForm.getEmail(), Security.EMAILPATTERN)) {
			this.logger.info("========class RegistrationValidator,method validateEMail,parametr regForm.getEmail():"
			        + regForm.getEmail() + "========");
			errors.reject("error.incorrectEmail");
			return;
		}
	}

	public void validateNick(RegistrationCC regForm, Errors errors) {
		if (regForm.getNick().length() < 3) {
			errors.reject("error.incorrectNick");
			return;
		}
	}

	public void validateName(RegistrationCC regForm, Errors errors) {
		if (regForm.getFirstName().length() < 3) {
			errors.reject("error.incorrectFirstName");
		}
	}

	public void validateZip(RegistrationCC regForm, Errors errors) {
		if (!regForm.getZip().equals("")) {
			if (!Security.checkByPattern(regForm.getZip(), Security.ZIPPATTERN)) {
				errors.reject("error.incorrectZipCode");
			}
		}
	}

	public void validateSQLInjection(RegistrationCC regForm, Errors errors) {
		if (!Security.checkOnSQLInjection(DataUtilities.convertClassToMap(
		        RegistrationCC.class, regForm))) {
			errors.reject("error.sqlInjection");
		}
	}

	public void validateLength(RegistrationCC regForm, Errors errors) {
		int errorFlag = 0;
		if (regForm.getAddress().length() > 100) {
			// regForm.setAddress("So long address!!!");
			errorFlag++;
		}
		if (regForm.getEmail().length() > 40) {
			// regForm.setEmail("So long email!!!");
			errorFlag++;
		}
		if (regForm.getCity().length() > 20) {
			// regForm.setCity("So long city!!!");
			errorFlag++;
		}
		if (regForm.getNick().length() > 12) {
			// regForm.setNick("So long nick!!!");
			errorFlag++;
		}
		if (regForm.getLastname().length() > 20) {
			// regForm.setLastname("So long LastName!!!");
			errorFlag++;
		}
		if (regForm.getFirstName().length() > 20) {
			// regForm.setFirstName("So long Firstname!!!");
			errorFlag++;
		}
		if (errorFlag > 0) {
			errors.reject("error.soLong");
		}
	}

	public RegistrationCC isEquality(Account account,
	    RegistrationCC registration) {
		this.logger.info("======== class RegistrationValidator,method isEquality,parametr account:"
		        + "Id="
		        + account.getId()
		        + " :"
		        + "eMail="
		        + account.getEmail()
		        + " :"
		        + "Nick="
		        + account.getNick()
		        + " :"
		        + "Address="
		        + account.getAddress()
		        + " :"
		        + "City="
		        + account.getCity()
		        + " :"
		        + "FirstName="
		        + account.getFirstName()
		        + " :"
		        + "LastName="
		        + account.getLastname()
		        + " :"
		        + "Zip="
		        + account.getZip()
		        + " :"
		        + "Password="
		        + account.getPassword()
		        + " :"
		        + "Role Name="
		        + account.getRole_Name()
		        + " :"
		        + "Description=" + account.getDescription() + " >");
		this.logger.info("======== class RegistrationValidator,method isEquality,parametr registration:"
		        + "Id="
		        + registration.getId()
		        + " :"
		        + "eMail="
		        + registration.getEmail()
		        + " :"
		        + "Nick="
		        + registration.getNick()
		        + " :"
		        + "Address="
		        + registration.getAddress()
		        + " :"
		        + "City="
		        + registration.getCity()
		        + " :"
		        + "FirstName="
		        + registration.getFirstName()
		        + " :"
		        + "LastName="
		        + registration.getLastname()
		        + " :"
		        + "Zip="
		        + registration.getZip()
		        + " :"
		        + "Password="
		        + registration.getPassword()
		        + " :"
		        + "Role Name="
		        + registration.getRole_Name()
		        + " :"
		        + "Description="
		        + registration.getDescription() + " >");

		if (registration.getEmail().isEmpty() && !account.getEmail().isEmpty()) {
			registration.setEmail(account.getEmail());
		}
		if (registration.getNick().isEmpty() && !account.getNick().isEmpty()) {
			registration.setNick(account.getNick());
		}
		if (registration.getFirstName().isEmpty()
		        && !account.getFirstName().isEmpty()) {
			registration.setFirstName(account.getFirstName());
		}
		if (registration.getRole_Name().isEmpty()
		        && !account.getRole_Name().isEmpty()) {
			registration.setRole_Name(account.getRole_Name());
		}
		if (registration.getPassword().isEmpty()
		        && !account.getPassword().isEmpty()) {
			registration.setPassword(account.getPassword());
		}
		return registration;
	}

}
