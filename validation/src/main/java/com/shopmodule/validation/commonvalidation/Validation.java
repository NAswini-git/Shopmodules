package com.shopmodule.validation.commonvalidation;

/**
 * Validates the input given by the user.
 *
 * @author AswiniN
 *
 */
public class Validation {

    /**
     * Validates EmailId.
     *
     * @param emailId
     */
    public static boolean validateEmailId(final String emailId) {
        return (emailId.matches("^[A-Za-z]{1,}[0-9]{0,}+[.]?[A-Za-z0-9]{1,}+@[a-z]+.[a-z]{2,3}$") || emailId.matches("[a-zA-Z]{1}[0-9]{1,}+[.]?[a-zA-Z0-9]{1,}+@[a-z]+.[a-z]{2,3}")) ? true : false;
    }

    /**
     * Validates Password.
     *
     * @param password
     */
    public static boolean validatePassword(final String password) {
        return (password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^!&-+=()])(?=\\S+$).{6,10}$")) ? true : false;
    }

    /**
     * Validates Name.
     *
     * @param name
     */
    public static boolean validateName(final String name) {
        return (name != null && name.matches("^[A-Za-z\\s ]{1,20}$")) ? true : false;
    }
}
