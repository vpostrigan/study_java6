package journey.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Filter checking session existence and if account attribute does not exist in
 * session, it would be added.
 */
public class SessionFilter implements Filter {
	
	private final Log logger = LogFactory.getLog(this.getClass());

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse response,
	    FilterChain next) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		if (request.getSession().getAttribute("account") == null) {
			//TODO Account
			/*
			 * 
			 * public class AccountWithPermission
        extends Account {
	/**
	 *
	private static final long serialVersionUID = -4453676211105720906L;

	protected int permission;

	public AccountWithPermission() {

	}

	public AccountWithPermission(AccountWithPermission account){
		this.address = account.getAddress();
		this.city = account.getCity();
		this.description = account.getDescription();
		this.email = account.getEmail();
		this.firstName = account.getFirstName();
		this.lastName = account.getLastname();
		this.nick = account.getNick();
		this.password = account.getPassword();
		this.role_Name = account.getRole_Name();
		this.zip = account.getZip();
		this.id = account.getId();
	}
	public int getPermission() {
		return this.permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

}

			 * 
			 * 
			 */
			//AccountWithPermission account = new AccountWithPermission();
			//account.setNick("Guest");
			//request.getSession().setAttribute("account", account);
		}
		//if (request.getSession().getAttribute("permission") == null) {
		//	int permission = 0;
		//	request.getSession().setAttribute("permission", permission);
		//}
		//if (request.getSession().getAttribute("invitescount") == null) {
		//	request.getSession().setAttribute("invitescount", 0);
		//}
		//HttpServletResponse resp = (HttpServletResponse) response;
		//resp.addCookie(new Cookie("email", "asdasdasd"));
		next.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}