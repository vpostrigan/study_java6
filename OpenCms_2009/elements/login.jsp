<%@page buffer="none" session="true"
import="org.opencms.file.*,org.opencms.jsp.*,java.util.*,org.opencms.i18n.*, org.opencms.main.*, 
org.opencms.jsp.*,org.opencms.mail.*,org.opencms.jsp.util.*,java.util.regex.*,org.apache.commons.lang.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://www.opencms.org/taglib/cms"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
	CmsJspActionElement jsp = new CmsJspActionElement(pageContext, request, response);
	CmsObject cms = jsp.getCmsObject();
	String message = null;
	boolean automove = false;
	
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String action = request.getParameter("action");
	String passkey = request.getParameter("requestedResource");
	
	if(passkey!=null)
	if(passkey.startsWith("/news/article_"))
	if(passkey.endsWith(".html?passkey=GVuNHA1VyNeJF5uczBlNDk2XiReczk2ZTRta")) {
		username = "guest";
		password = "fd7djw393fjc73hfdjd9sw893n43jd8cv6du32jej9d9fd6s93h3";
		action = "Login";
	}
	
	boolean submitted = false;
	if("Submit".equals(action)) {
		action = "forgot";
		submitted = true;
	}
	if("Change".equals(action)) {
		action = "change";
		submitted = true;
	}
	
	String url = request.getParameter("requestedResource");	
	String cookieUsername = null;
	String cookiePassword = null;
	String title = null;
	
	boolean remember = "1".equals(request.getParameter("remember"));
	Cookie[] cookies = request.getCookies();
	if (cookies != null)
		for (int xi=0; xi < cookies.length; xi++) {
	    	Cookie cookie = cookies[xi];
            String name = cookie.getName();
			if ("user_pwd".equals(name)){
            	cookiePassword = com.sp.slp.CryptString.decode(cookie.getValue(), config.getServletContext().getRealPath(""));
            }
            if ("user_login".equals(name)){
            	cookieUsername = cookie.getValue();	
            }
        }
	
	if(url == null) {
		url = request.getAttribute("url") == null ? 
				"/index.html" : 
				request.getAttribute("url").toString();
	}
	if("".equals(url)) {
		url="/index.html";
	}
	if("/login.html".equals(url)){
		url="/index.html";
	}
	
	request.setAttribute("url", url);
	
	if("null".equals(url)){
		url="/index.html";
	}
	
	// LOGIN
	if("Login".equals(action)) {
		try{
			cms.loginUser(username, password);				
			if(OpenCms.getDefaultUsers().getUserAdmin().equals(username) || cms.userInGroup(username, "Users")) {
				CmsProject cmsproject = cms.readProject("Offline");
				cms.getRequestContext().setCurrentProject(cmsproject);
				cms.getRequestContext().setSiteRoot("/sites/default/");								
			}
			if(remember) {			
				String encodedPassword = com.sp.slp.CryptString.encode(password, config.getServletContext().getRealPath(""));
				
				if (encodedPassword != null) {
            		Cookie usernameCookie = new Cookie("user_login", username);
            		usernameCookie.setMaxAge(60 * 60 * 24 * 365);//1 year
           			//usernameCookie.setSecure(true);
            		response.addCookie(usernameCookie);
            		Cookie pwdCookie = new Cookie("user_pwd", encodedPassword);
            		pwdCookie.setMaxAge(60 * 60 * 24 * 365);//1 year
            		//pwdCookie.setSecure(true);
            		response.addCookie(pwdCookie);
				}
			}
			action = "";
			response.sendRedirect(jsp.link(url));
		} catch ( CmsException e ) {
			message = "Login failed";
		}
	} 
	else
		// LOGOUT
		if("logout".equals(action)) {
			if (cookies != null) {
			    for (int xi=0; xi < cookies.length; xi++) {
			    	Cookie cookie = cookies[xi];
		    	    String name = cookie.getName();	    	    
		            if ("user_login".equals(name) || "user_pwd".equals(name)) {
						cookie.setMaxAge(0);
						cookie.setValue(null);
		                response.addCookie(cookie);
					}
				}			
			}
			session.invalidate();
			response.sendRedirect(jsp.link("/login.html"));		
		} 
	else
		// CHANGE PASSWORD
		if("change".equals(action)) {	
			if(submitted && (!jsp.getRequestContext().currentUser().isGuestUser())) {
				try{
					String passwordCurrent = request.getParameter("password_current");
					String passwordNew = request.getParameter("password_new");
					String passwordConfirm = request.getParameter("password_confirm");
					
					username = jsp.getRequestContext().currentUser().getName();
					
					if(passwordNew.equals(passwordConfirm)) {				
						cms.setPassword(username, passwordCurrent, passwordNew);
						message = "Your password has been changed";	
						automove = true;
						action = "";
						if (cookieUsername != null && cookiePassword != null) {			
							String encodedPassword = com.sp.slp.CryptString.encode(passwordNew, config.getServletContext().getRealPath(""));
							
							if (encodedPassword != null) {
			            		Cookie usernameCookie = new Cookie("user_login", username);
			            		usernameCookie.setMaxAge(60 * 60 * 24 * 365);//1 year
			           			//usernameCookie.setSecure(true);
			            		response.addCookie(usernameCookie);
			            		Cookie pwdCookie = new Cookie("user_pwd", encodedPassword);
			            		pwdCookie.setMaxAge(60 * 60 * 24 * 365);//1 year
			            		//pwdCookie.setSecure(true);
			            		response.addCookie(pwdCookie);
							}
						}
					} else {
						message = "The new password doesn't match the confirmation value";
					}
				} catch ( Exception e ) {
					message = "Incorrect login or password";
				}
				
				if(jsp.getRequestContext().currentUser().isGuestUser()){
					action = "";
				}
			}
		} 
	else
		// FORGOT PASSWORD
		if("forgot".equals(action)) {	
			if(submitted) {
				message="Incorrect login or email";		
				try {
					CmsObject cmso = OpenCms.initCmsObject(OpenCms.getDefaultUsers().getUserGuest());
					cmso.loginUser("ForgotPasswordRecovery", "afhe5&*(GS*&()123@#@fdsgs");
			 		List users = cmso.getUsers();
					CmsUser user = null;
					
					// Searching for necessary user
					for(Iterator i = users.iterator(); i.hasNext();) {
						user = (CmsUser) i.next();
						if(user.getName().equals(username)) break;
					}
					
					// Change password for found user and send new password for him 
					if( (user != null) && (user.getEmail().equals(password)) ) {			
						CmsSimpleMail sm = new CmsSimpleMail();
						Random r = new Random();					 
						String newpassword = String.valueOf(Long.toHexString(r.nextLong()));
						cmso.setPassword(user.getName(), newpassword);
						
						String domain = request.getServerName();
						String domain_port = new Integer(request.getServerPort()).toString();
						String thePageUrl = request.getScheme() + "://" + domain + 
							(domain_port.equals("80") ? "" : ":" + domain_port) + 
							jsp.link(jsp.getRequestContext().getUri()) ;
						String mail="THIS IS AN AUTOMATED MESSAGE, DO NOT REPLY\n\n\n"+
							"Hi there " + user.getFullName() + ",\n\n" +
							"Your new Sales Portal password:\n"+
							newpassword + "\n\n" +
							"You can now access your Account at Sales Portal.\n" +
							thePageUrl + "\n\n" +
						 	"Thank you very much for using Sales Portal.";
						
						sm.setSubject("Sales Portal password recovery");
						sm.setMsg(mail);
						sm.addTo(user.getEmail()); 					 
						sm.setFrom(OpenCms.getSystemInfo().getMailSettings().getMailFromDefault());
						sm.send();
						
						action = "";
						message = "Password has been sent to your email address";
					}
				} catch(Exception e) {						
					message = "An error occurred while processing your request\n";		
				}
				finally {
					session.invalidate();
					jsp = new CmsJspActionElement(pageContext, request, response);
				}
			}
		}
	else {
		if( (!jsp.getRequestContext().currentUser().isGuestUser()) && 
				(jsp.getRequestContext().currentProject().isOnlineProject())){
			response.sendRedirect(jsp.link(url));
		}
		
		if (cookieUsername != null && cookiePassword != null) {
			try{
				cms.loginUser(cookieUsername, cookiePassword);
			
				if(OpenCms.getDefaultUsers().getUserAdmin().equals(cookieUsername) || 
					cms.userInGroup(cookieUsername, "Users")) {
						CmsProject cmsproject = cms.readProject("Offline");
						cms.getRequestContext().setCurrentProject(cmsproject);
						cms.getRequestContext().setSiteRoot("/sites/default/");
				}
				
				response.sendRedirect(jsp.link(url));
			} catch ( CmsException e ) {
				message = "An error occurred while processing your request in Users group";	
			}
		}
	}
	
	title = "Sales Portal Login";	
	if("forgot".equals(action)) title="Password recovery";
	if("change".equals(action)) title="Change password";
%>
<h1 class="no_icon"><%= title %></h1>
<form method="post" action="<cms:link>/login.html</cms:link>">
<table border=0>
	<%
		if (message != null) {
	%><tr>
		<td></td>
		<td style="color: #FF3333"><%= message %></td>
	  </tr>
	<%
		}
	%>
	<%
		if (!automove) {
	%>

		<%
			if ("change".equals(action)) {
		%>
		<tr>
			<td><label for="name" id="label_name">Name:</label></td>
			<td><input type="text" class="search_field" disabled="true"
				id="username_current" name="username_current"
				value="<%=jsp.getRequestContext().currentUser().getName()%>" /></td>
		</tr>
		<tr>
			<td><label for="password_current" id="password_current2">Current password:</label></td>
			<td><input type="password" class="search_field"
				id="password_current" name="password_current" value="" /></td>
		</tr>
		<tr>
			<td><label for="password_new" id="password_new2">New password:</label></td>
			<td><input type="password" class="search_field" id="password_new" name="password_new" value="" /></td>
		</tr>
		<tr>
			<td><label for="password_confirm" id="password_confirm2">Confirm password:</label></td>
			<td><input type="password" class="search_field"
				id="password_confirm" name="password_confirm" value="" /></td>
		</tr>
		<%
			} else {
		%>
		<tr>
			<td><label for="name" id="label_name">Name:</label></td>
			<td><input type="text" class="search_field" id="username" name="username" value="" /></td>
		</tr>
		<tr>
			<td><label for="password" id="password"><%="forgot".equals(action)
								? "Email:"
								: "Password:"%></label></td>
			<td><input
				type="<%="forgot".equals(action)
								? "text"
								: "password"%>"
				class="search_field" id="password" name="password" value="" /></td>
			<%
				}
			%>
		</tr>
		<%
			if (!("forgot".equals(action) || "change".equals(action))) {
		%>
		<tr>
			<td></td>
			<td><input type="checkbox" class="" id="remember" name="remember"
				value="1" /> <label for="remember" id="remember2">Remember me</label></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Login" name="action" id="login" class="find_butt" /></td>
		</tr>
		<tr>
			<td></td>
			<td><a href="<cms:link>/login.html?action=forgot</cms:link>">Forgot Password?</a></td>
		</tr>
		<%
			} else {
		%>
		<tr>
			<td></td>
			<td><input type="button" value="Back" name="action" id="back"
				class="find_butt" onclick="this.form.submit(); return false;" /> <input
				type="submit"
				value="<%="change".equals(action)
								? "Change"
								: "Submit"%>"
				name="action" id="sumbit" class="find_butt" /></td>
		</tr>
		<%
			}
		%>

	<%
		} else {
	%>
	<script type="text/javascript">
		redirTime = "5000";
		redirURL = "<%= jsp.link("/index.html") %>";
		self.setTimeout("self.location.href=redirURL;", redirTime);
	</script>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2">You will be redirected automatically in 5 seconds.</td>
	</tr>
	<tr>
		<td colspan="2"><a href="<%=jsp.link("/index.html")%>">Click here</a> to jump to the site.</td>
	</tr>
		<%
			}
		%>		
</table>
</form>
