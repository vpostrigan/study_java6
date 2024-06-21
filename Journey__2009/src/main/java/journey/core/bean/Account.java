package journey.core.bean;

import java.util.Date;

public class Account extends Bean {

	private static final long serialVersionUID = 3018796824461255460L;

	/** User's e-mail address */
	protected String email;

	/** User's first name */
	protected String firstname;
	
	protected String lastname;

	/** User's password */
	protected String password;
	
	protected String gender;
	
	protected Date birthday;
	
	/** User's city */
	protected String city;
	
	protected String state;
	
	protected String country;
	
	/** User's zone improvement plan code */
	protected String zip;

	/** User's address */
	protected String street1;
	
	protected String street2;	

	protected String status;

	protected Date registered;

	protected String bio;

	/** Public default constructor */
	public Account() {
		// do nothing
	}

	public Account(long id, String email, String firstname, String lastname,
			String password, String gender, Date birthday, String city,
			String state, String country, String zip, String street1,
			String street2, String status, Date registered, String bio) {
		super(id);
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.gender = gender;
		this.birthday = birthday;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zip = zip;
		this.street1 = street1;
		this.street2 = street2;
		this.status = status;
		this.registered = registered;
		this.bio = bio;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Account other = (Account) obj;
		if (bio == null) {
			if (other.bio != null) {
				return false;
			}
		} else if (!bio.equals(other.bio)) {
			return false;
		}
		if (birthday == null) {
			if (other.birthday != null) {
				return false;
			}
		} else if (!birthday.equals(other.birthday)) {
			return false;
		}
		if (city == null) {
			if (other.city != null) {
				return false;
			}
		} else if (!city.equals(other.city)) {
			return false;
		}
		if (country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!country.equals(other.country)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (firstname == null) {
			if (other.firstname != null) {
				return false;
			}
		} else if (!firstname.equals(other.firstname)) {
			return false;
		}
		if (gender == null) {
			if (other.gender != null) {
				return false;
			}
		} else if (!gender.equals(other.gender)) {
			return false;
		}
		if (lastname == null) {
			if (other.lastname != null) {
				return false;
			}
		} else if (!lastname.equals(other.lastname)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (registered == null) {
			if (other.registered != null) {
				return false;
			}
		} else if (!registered.equals(other.registered)) {
			return false;
		}
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (street1 == null) {
			if (other.street1 != null) {
				return false;
			}
		} else if (!street1.equals(other.street1)) {
			return false;
		}
		if (street2 == null) {
			if (other.street2 != null) {
				return false;
			}
		} else if (!street2.equals(other.street2)) {
			return false;
		}
		if (zip == null) {
			if (other.zip != null) {
				return false;
			}
		} else if (!zip.equals(other.zip)) {
			return false;
		}
		return true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
}
