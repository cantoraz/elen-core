/**
 * 
 */
package cn.caz.elen.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Cantoraz Zhou
 * 
 */
@Entity
public class User extends ElenModel {

	private static final long serialVersionUID = 4323514972837220047L;

	@Column(unique = true)
	private String email;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column(name = "passwd")
	private String password;

	@Column
	private byte[] portrait;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the portrait
	 */
	public byte[] getPortrait() {
		return this.portrait;
	}

	/**
	 * @param portrait
	 *            the portrait to set
	 */
	public void setPortrait(byte[] portrait) {
		this.portrait = portrait;
	}

}
