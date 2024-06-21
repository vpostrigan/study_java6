package journey.web.form;

/**
 * Command class for login form.
 */
public class LoginForm {
	
	private String email;

	private String password;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String mail) {
		this.email = mail;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
