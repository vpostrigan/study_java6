/*
 * 04.08.2008 15:52:40
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 871 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-08-13 18:06:50 +0300 (Ср, 13 авг 2008) $
 */
package com.eclipsesp.tplanner.web.controller;

import javax.servlet.http.*;

import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.eclipsesp.tplanner.core.bean.*;
import com.eclipsesp.tplanner.core.facade.*;
import com.eclipsesp.tplanner.core.security.Security.*;
import com.eclipsesp.tplanner.core.security.utils.*;
import com.eclipsesp.tplanner.web.*;
import com.eclipsesp.tplanner.web.form.*;
import com.eclipsesp.tplanner.web.validators.*;

/**
 * 
 * Edit account page controller. 
 * Allows an user to edit the personal information of self
 * 
 * @author dproshkin
 */
@Controller
@Permission(tournaments = SingleMask.CAN_WRITE_SELF, users = SingleMask.CAN_WRITE_SELF)
public class EditAccountController {
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	IAccountManager accountManager;

	@RequestMapping(value = { "/editAccount*" }, method = RequestMethod.GET)
	public String getEditAccountPage() {
		return getPageId();
	}
	
	protected String getPageId() {
		return MessageKeys.EDIT_ACCOUNT;
	}
	
	/** To save change in the personal information of user done an self user. */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(HttpServletRequest httpServletRequest,
		@ModelAttribute("accountEdit") RegistrationCC editAccount,
		@ModelAttribute("accountEditForm") Account account,	
			Errors result) {
		editAccount.setId(account.getId());
		this.logger.info("accountEditForm: < "+
				"Id="+editAccount.getId()+" :"+
				"eMail="+editAccount.getEmail()+" :"+
				"Nick="+editAccount.getNick()+" :"+
				"Address="+editAccount.getAddress()+" :"+
				"City="+editAccount.getCity()+" :"+
				"FirstName="+editAccount.getFirstName()+" :"+
				"LastName="+editAccount.getLastname()+" :"+
				"Zip="+editAccount.getZip()+" :"+
				"Password="+editAccount.getPassword()+" :"+
				"Description="+editAccount.getDescription()+" >");
	
			RegistrationValidator validator = new RegistrationValidator();
			
			validator.validate(editAccount, result);
		 if (!result.hasErrors()) {
		try {
			this.accountManager.setAccount(editAccount, (int)editAccount.getId());
			AccountWithPermission accountWithPermission = (AccountWithPermission) 
							httpServletRequest.getSession().getAttribute("account");
			AccountWithPermission temp = new AccountWithPermission(editAccount);
			temp.setPermission(accountWithPermission.getPermission());
			httpServletRequest.getSession().setAttribute("account",	temp);
		} catch (Exception e) {
			this.logger.error(e);
			return "redirect:error.html";
		}
		return "redirect:index.html";
		 }
		 else {
		 return getPageId();
		 }	
	}
	
	/** Setup edit form */
	@ModelAttribute("accountEditForm")
	public RegistrationCC getEditForm(HttpServletRequest request) {
		 try{
			 Account account = (Account) request.getSession().getAttribute("account");
		if (!account.equals(null)) {
			RegistrationCC accountEditForm = new RegistrationCC();
			accountEditForm.setEmail(account.getEmail());
			accountEditForm.setAddress(account.getAddress());
			accountEditForm.setCity(account.getCity());
			accountEditForm.setDescription(account.getDescription());
			accountEditForm.setFirstName(account.getFirstName());
			accountEditForm.setLastname(account.getLastname());
			accountEditForm.setNick(account.getNick());
			accountEditForm.setPassword(account.getPassword());
			accountEditForm.setPasswordconf(account.getPassword());
			accountEditForm.setZip(account.getZip());
			accountEditForm.setId(account.getId());
			this.logger.info("accountEditForm: < "+
					"Id="+accountEditForm.getId()+" :"+
					"eMail="+accountEditForm.getEmail()+" :"+
					"Nick="+accountEditForm.getNick()+" :"+
					"Address="+accountEditForm.getAddress()+" :"+
					"City="+accountEditForm.getCity()+" :"+
					"FirstName="+accountEditForm.getFirstName()+" :"+
					"LastName="+accountEditForm.getLastname()+" :"+
					"Zip="+accountEditForm.getZip()+" :"+
					"Password="+accountEditForm.getPassword()+" :"+
					"Description="+accountEditForm.getDescription()+" >");
			return accountEditForm;
		} else {
			return null;
		}
		 }catch(Exception e){
		 this.logger.error(e);
		 }
		 return null;
	}	

}
