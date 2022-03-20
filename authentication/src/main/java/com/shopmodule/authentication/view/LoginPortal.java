package com.shopmodule.authentication.view;

import com.shopmodule.authentication.model.User;
import com.shopmodule.authentication.controller.AuthenticationController;
import com.shopmodule.generalinputs.userinput.UserInput;
import com.shopmodule.validation.commonvalidation.Validation;
import com.shopmodule.products.view.ProductDetails;
import com.shopmodule.products.view.ProductManager;
import org.apache.log4j.Logger;

/**
 * User registration and login.
 *
 * @author AswiniN
 */
public class LoginPortal {

    private static final AuthenticationController AUTHENTICATION_CONTROLLER = new AuthenticationController();
    private static final Logger LOGGER = Logger.getLogger(LoginPortal.class);
    private static final UserInput USER_INPUT = UserInput.getInstance();

    /**
     * Register or Login choice.
     */
    public static void renderLoginPortal() {
        final User user = new User();

            user.setPreferredChoice(USER_INPUT.getInt("1. SignUp 2. SignIn"));
            if (user.getPreferredChoice() == 1 || user.getPreferredChoice() == 2) {
                LoginPortal.renderUserChoicePortal(user);
            } else {
                LOGGER.warn("Invalid choice. Give a valid input.");
                LoginPortal.renderLoginPortal();
            }
    }

    /**
     * Gets the type of user either admin or user.
     *
     * @param user
     */
    private static void renderUserChoicePortal(final User user) {

            user.setTypeOfUser(USER_INPUT.getInt("1. Admin  2. User "));

            if (user.getTypeOfUser() == 1 || user.getTypeOfUser() == 2) {
                LoginPortal.signUpOrSignIn(user);
            } else {
                LOGGER.warn("Invalid choice. Give a valid input.");
                LoginPortal.renderUserChoicePortal(user);
            }
        }

    /**
     * Routes the user based on the selected choice.
     *
     * @param user
     */
    private static void signUpOrSignIn(final User user) {
        user.setEmailId(getEmailId());
        final boolean isEmailValid = AUTHENTICATION_CONTROLLER.validateEmailId(user.getEmailId());

        checkEmail(isEmailValid, user);
        user.setPassword(LoginPortal.getPassword());

        if (user.getPreferredChoice() == 1) {

            if (user.getTypeOfUser() == 1) {
                LoginPortal.checkKey(user);
            }

            if (AUTHENTICATION_CONTROLLER.insertData(user)) {
                LOGGER.info("Data Inserted Successfully");
                LoginPortal.showProductDetails(user);
            } else {
                LOGGER.info("Insertion failed");
            }
        } else {

            if (user.getPreferredChoice() == 2) {
                LoginPortal.checkPassword(user);
            }
        }
    }

    /**
     * Gets EmailId.
     */
    private static String getEmailId() {
        final String emailId = goToLoginPortal(USER_INPUT.getString("Enter EmailId: {Press 00 to go back to LoginPortal}"));

        if (Validation.validateEmailId(emailId)) {
            return emailId;
        } else {
            LOGGER.warn(
                    "Give a valid Email Id (Email Id should have proper domain name, upper or lower case, digits only before @.)");
            return LoginPortal.getEmailId();
        }
    }

    /**
     * Gets Password.
     */
    private static String getPassword() {
        final String password = goToLoginPortal(USER_INPUT.getString("Enter Password: {Press 00 to go to LoginPortal}"));

        if (Validation.validatePassword(password)) {
            return password;
        } else {
            LOGGER.warn(
                    "Give a valid Password (Password should contain min 6 characters, 1 Uppercase, 1 Lowercase 1, Special character, 1 digit)");
            return LoginPortal.getPassword();
        }
    }

    /**
     * Gets authenticator key if the user is admin.
     * 
     * @param user
     */
    private static void checkKey(final User user) {

        if ("admin".equals(goToLoginPortal(USER_INPUT.getString("Enter Key: {Press 00 to go back to LoginPortal}")))) {
            LOGGER.info("Valid key.");
        } else {
            LOGGER.warn("Invalid key.");
            LoginPortal.checkKey(user);
        }
    }

    /**
     * Validates the Email Id.
     *
     * @param user
     */
    private static void checkEmail(final boolean isEmailValid, final User user) {

        if (user.getPreferredChoice() == 1) {

            if (isEmailValid) {
                LOGGER.info("EmailId already exists.");
                LoginPortal.signUpOrSignIn(user);
            }
        } else {

            if (!isEmailValid) {
                LOGGER.warn("Invalid login details. Reenter it.");
                LoginPortal.signUpOrSignIn(user);
            }
        }
    }

    /**
     * Validates the password if the user is logging in.
     * 
     * @param user
     */
    private static void checkPassword(final User user) {

        if (AUTHENTICATION_CONTROLLER.validatePassword(user.getEmailId(), user.getPassword(), user.getTypeOfUser())) {
            LOGGER.info("Logged in successfully");
            LoginPortal.showProductDetails(user);
        } else {
            LOGGER.warn("Login unsuccessful. Reenter your details.");
            LoginPortal.signUpOrSignIn(user);
        }
    }

    /**
     * Shows the products available in the database.
     * 
     * @param user
     */
    private static void showProductDetails(final User user) {

        if (user.getTypeOfUser() == 1) {
            ProductManager.showAdminChoice();
        } else {
            ProductDetails.showProductDetails();
            ProductManager.searchProduct();
            LoginPortal.choicetoContinue();
        }
    }

    /**
     * Shows Login Page.
     *
     * @param choice
     */
    public static String goToLoginPortal(final String choice) {

        if ("00".equals(choice)) {
            LoginPortal.renderLoginPortal();
        }
        return choice;
    }
    /**
     * Routes the user based on the choice.
     */
    private static void choicetoContinue() {

        do {
            final String choice = USER_INPUT.getString("Do you need to search products: yes/no");
            
            if ("Yes".equalsIgnoreCase(choice)) {
                ProductManager.searchProduct();
            } else if ("No".equalsIgnoreCase(choice)) {
                LOGGER.info("Thank you for using this application.");
                break;
            } else {
                LOGGER.warn("Invalid choice");
            }
        } while (true);
    }
}
