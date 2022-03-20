package com.shopmodule.authentication.service;

import com.shopmodule.authentication.dao.AuthenticatorDAO;
import com.shopmodule.authentication.model.User;

/**
 * Authenticates the type of User with the help of database.
 *
 * @author AswiniN
 */
public class AuthenticatorValidation  {
	
    private static final AuthenticatorDAO AUTHENTICATOR_DAO = new AuthenticatorDAO();

	/**
	 *Validates EmailId.
	 *
	 *@param emailId
	 */
	public boolean validateEmailId(final String emailId) {
		final User user = AUTHENTICATOR_DAO.getUserEmailId(emailId);
		
		if (user.getEmailId() == null) {
			return false;
		} 
		if (user.getEmailId().equals(emailId)) {
		return true;
		}
        return false;		
	}

	/**
	 * Validates Password.
	 * 
	 * @param emailId
	 * @param password
	 * @param preferredChoice
	 */
	public boolean validtePassword(final String emailId, final String password, final int preferredChoice) {
		final User user = AUTHENTICATOR_DAO.getUserEmailId(emailId);
		
		if (preferredChoice == 1) {
		if (user.getPassword().equals(password) && user.getUserType().equals("admin")) {
		 //  ProductManager.showAdminChoice();
		    System.out.println("admin");
			return true;
		}
		} else {
		    return (user.getPassword().equals(password) && user.getUserType().equals("user")) ? true : false;  
		}
		return false;
	}

	/**
	 * Inserts data into user table having Email Id and Password.
	 * 
	 * @param user
	 */
	public boolean insertData(final User user) {
	    final boolean isInserted;
	    
		if (user.getTypeOfUser() == 1) {
	        isInserted = AUTHENTICATOR_DAO.insertUserData(user,"admin");
		} else {
		    isInserted = AUTHENTICATOR_DAO.insertUserData(user,"user");	
		}
		
		if (isInserted) {
		    return true;
		}
		return false; 
	}
}
