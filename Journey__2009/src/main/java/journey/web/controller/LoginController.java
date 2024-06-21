package journey.web.controller;

import javax.servlet.http.HttpServletRequest;

import journey.web.form.LoginForm;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Login form controller. Binded on every page contains login prefix
 */
@Controller
@RequestMapping(value = { "/login*" })
@SessionAttributes("account")
public class LoginController {
	//@Autowired
	//IAccountManager accountManager;

	//@Autowired
	//IRoleManager roleManager;

	//@Autowired
	//ITournamentManager tournamentManager;

	@RequestMapping(method = RequestMethod.GET)
	public String getLoginPage(String email) {

		return "login";
	}

	@ModelAttribute("loginform")
	protected LoginForm getLogin() {
		LoginForm loginForm = new LoginForm();
		return loginForm;
	}

	/**
	 * Method gets fields from login page, validating them. If validation is
	 * passed then account data saves in session.
	 * 
	 * @param loginForm -
	 *            form filled by user
	 * @param result -
	 *            return validation errors to the form
	 * @param acc -
	 *            session attribute
	 * @return path to jsp content
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("loginform")
			LoginForm loginForm, Errors result, HttpServletRequest request) {
/*		loginForm.setPassword(Security.encodePass(loginForm.getPassword()));
		LoginValidator validator = new LoginValidator();
		validator.validate(loginForm, result);
		if (!result.hasErrors()) {
			AccountWithPermission account = null;
			try {
				account = this.accountManager.getByEmail(loginForm.getEmail());
			} catch (Exception e) {
				result.reject("error.passwordWrong");
				return getPageId();
			}
			if (account == null) {
				result.reject("error.passwordWrong");
				return getPageId();
			}
			if (account.getPassword().equals(loginForm.getPassword())) {
				request.getSession().setAttribute("account", account);
				int data =
				        this.roleManager.getByRoleName(account.getRole_Name()).getPermission_Def();
				request.getSession().setAttribute("permission", data);
				Collection<Invite> collection =
				        this.tournamentManager.getByAccountId(account.getId());
				Iterator<Invite> i = collection.iterator();
				int count = 0;
				while (i.hasNext()) {
					if (i.next().getStatus() == 4) {
						count++;
					}
				}
				request.getSession().setAttribute("invitescount", count);
				return "redirect:index.html";
			} else {
				result.reject("error.passwordWrong");
				return getPageId();
			}
*/
		/*} else {
			loginForm.setPassword("");*/
			return "login";
		//}


	}

}
