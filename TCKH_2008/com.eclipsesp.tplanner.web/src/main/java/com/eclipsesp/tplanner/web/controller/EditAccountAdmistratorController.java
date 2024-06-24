/*
 * 12.08.2008 16:50:31
 *
 * (C) Eclipse SP LLC. All rights reserved
 * 
 * $Revision: 36 $
 * $Author: dproshkin_tckh $
 * $Date: 2008-07-17 17:35:40 +0300 (Чт, 17 июл 2008) $
 */
package com.eclipsesp.tplanner.web.controller;

import java.util.*;

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
 * Allows an administrator to edit the personal information of users
 * 
 * @author dproshkin
 */
@Controller
@Permission(tournaments = SingleMask.CAN_WRITE_ALL, users = SingleMask.CAN_WRITE_ALL)
public class EditAccountAdmistratorController {
	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	IAccountManager accountManager;

	@Autowired
	IRoleManager roleManager;
	
	protected String getPageId() {
		return MessageKeys.EDIT_ACCOUNT_ADMINISTRATORS;
	}
	
	
	@RequestMapping(value = { "/editAdministratorAccount*" }, method = RequestMethod.GET)
	public String getEditAdministartorAccountPage() {
		return getPageId();
	}
	
	/** Used for the receipt of list of all roles.What to display in a falling out list. */
	@ModelAttribute("roleNameAll")
	public Collection<Role> getRoleAll(){
		this.logger.info("========Method getRoleAll classEditAccountAdministratorController=========:"+this.roleManager.getAll().size());
		return this.roleManager.getAll();
	}
	
	/** To save change in the personal information of user done an administrator. */
	/**
	 * @param accountAdministratorEditForm
	 *            account view bean.
	 * 
	 * @return redirect to edit page user of account to edit.
	 */
	@RequestMapping(method = RequestMethod.POST, params = "action=edit")
	public String processSubmit(
		@ModelAttribute("accountAdministratorEdit") RegistrationCC editAccount,
		Errors result) {
		this.logger.info("accountAdministratorEdit: < "+
				"Id="+editAccount.getId()+" :"+
				"eMail="+editAccount.getEmail()+" :"+
				"Nick="+editAccount.getNick()+" :"+
				"Address="+editAccount.getAddress()+" :"+
				"City="+editAccount.getCity()+" :"+
				"FirstName="+editAccount.getFirstName()+" :"+
				"LastName="+editAccount.getLastname()+" :"+
				"Zip="+editAccount.getZip()+" :"+
				"Password="+editAccount.getPassword()+" :"+
				"Role Name="+editAccount.getRole_Name()+" :"+
				"Description="+editAccount.getDescription()+" >");
		
			/** Checking for correctness in inserted of information */
			RegistrationValidator validator = new RegistrationValidator();

			validator.validateEMail(editAccount, result);
			validator.validateLength(editAccount, result);
			validator.validateName(editAccount, result);
			validator.validateNick(editAccount, result);
			validator.validateSQLInjection(editAccount, result);
			validator.validateZip(editAccount, result);
			validator.validatePasswordIsEmpty(editAccount, result);
			
		 if (!result.hasErrors()) {
		try {
			this.logger.info("====class EditAccountAdmistratorController,method processSubmit.: Validation is correct====");
			this.accountManager.setAccount(validator.isEquality(this.accountManager.getById((int)editAccount.getId()), editAccount), (int)editAccount.getId());
		} catch (Exception e) {
			this.logger.error(e);
			return "redirect:error.html";
		}
		return "redirect:users.html";
		 }
		 else {
			 this.logger.info("=====Parametr result exception:"+result.getAllErrors()+" =====Parametr result:"+result+"====");	 
			 return getPageId();
		 }	
	}
	/** To delete account of user from databases. */
	/**
	 * @param accountAdministratorEdit
	 *            account view bean to delete.
	 * 
	 * @return redirect to user page user of account.
	 */
	@RequestMapping(method = RequestMethod.POST, params = "action=delete")
	public String deleteAccount(@ModelAttribute("accountAdministratorEdit") RegistrationCC editAccount){
		this.logger.info("==== Class EditAccountAdministratorController,method deleteAccount,parametr editAccount: "+				
				"Id="+editAccount.getId()+" :"+
				"eMail="+editAccount.getEmail()+" :"+
				"Nick="+editAccount.getNick()+" :"+
				"Address="+editAccount.getAddress()+" :"+
				"City="+editAccount.getCity()+" :"+
				"FirstName="+editAccount.getFirstName()+" :"+
				"LastName="+editAccount.getLastname()+" :"+
				"Zip="+editAccount.getZip()+" :"+
				"Password="+editAccount.getPassword()+" :"+
				"Role Name="+editAccount.getRole_Name()+" :"+
				"Description="+editAccount.getDescription()+" ====");
		try {
			this.accountManager.deleteAccount(editAccount);
		} catch (Exception e) {
			this.logger.error(e);
			return "redirect:error.html";
		}
		return "redirect:users.html";
	}
	
	/** Forms a model for a output */
	/**
	 * @param accountAdministratorEdit
	 *            account view bean.
	 * 
	 * @return redirect to edit page with id of account to edit.
	 */
	@ModelAttribute("accountAdministratorEdit")
	public RegistrationCC getEditForm(@RequestParam(value = "id", required = false)Long Id) {
		try{
			 Account account = this.accountManager.getById(Id.longValue());
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
			accountEditForm.setRole_Name(account.getRole_Name());
			accountEditForm.setId(account.getId());
			this.logger.info("====Class EditAccountAdministratorController," +
					"method getEditForm,parametr accountAdministratorEditForm: < "+
					"Id="+accountEditForm.getId()+" :"+
					"eMail="+accountEditForm.getEmail()+" :"+
					"Nick="+accountEditForm.getNick()+" :"+
					"Address="+accountEditForm.getAddress()+" :"+
					"City="+accountEditForm.getCity()+" :"+
					"FirstName="+accountEditForm.getFirstName()+" :"+
					"LastName="+accountEditForm.getLastname()+" :"+
					"Zip="+accountEditForm.getZip()+" :"+
					"Password="+accountEditForm.getPassword()+" :"+
					"Role_Name="+accountEditForm.getRole_Name()+" :"+
					"Description="+accountEditForm.getDescription()+" =====");
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
