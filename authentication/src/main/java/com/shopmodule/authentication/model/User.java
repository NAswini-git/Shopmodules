package com.shopmodule.authentication.model;

/**
 * Class for authentication purpose.
 * 
 * @author AswiniN
 */
public class User {
	private int preferredChoice;
	private int typeOfUser;
	private String emailId;
	private String password;
	private String userType;
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getPreferredChoice() {
		return preferredChoice;
	}

	public void setPreferredChoice(int preferredChoice) {
		this.preferredChoice = preferredChoice;
	}

	public int getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(int typeOfUser) {
	    this.typeOfUser = typeOfUser;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
