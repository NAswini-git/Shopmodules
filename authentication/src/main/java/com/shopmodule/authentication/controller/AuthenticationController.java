package com.shopmodule.authentication.controller;

import com.shopmodule.authentication.model.User;
import com.shopmodule.authentication.service.AuthenticatorValidation;

/**
 * Controls the flow between view and service implementation
 * for user table while authentication.
 *
 * @author AswiniN
 */
public class AuthenticationController {

    private static final AuthenticatorValidation AUTHENTICATOR_VALIDATION = new AuthenticatorValidation();

    /**
     * Validates EmailId.
     *
     * @param emailId
     */
    public boolean validateEmailId(final String emailId) {
        return AUTHENTICATOR_VALIDATION.validateEmailId(emailId);
    }

    /**
     * Validates Password.
     *
     * @param emailId
     * @param password
     * @param typeOfUser
     */
    public boolean validatePassword(final String emailId, final String password, int typeOfUser) {
        return AUTHENTICATOR_VALIDATION.validtePassword(emailId, password, typeOfUser);
    }

    /**
     * Insert user details.
     *
     * @param user
     */
    public boolean insertData(User user) {
        return AUTHENTICATOR_VALIDATION.insertData(user);
    }
}
