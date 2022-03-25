package com.shopmodule.generalinputs.userinput;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Get Input from user.
 *
 * @author AswiniN
 */
public class UserInput {

    public final Scanner SCANNER = new Scanner(System.in);
    private static UserInput userInput;

    private UserInput() {
    }

    /**
     * Creates instance for this class.
     * @return
     */
    public static UserInput getInstance() {
        if (userInput == null) {
            userInput = new UserInput();
        }
        return userInput;
    }

    /**
     * Get string inputs
     *
     * @param credential
     * @return
     */
    public String getString(final String credential) {
        System.out.println(credential);
        return SCANNER.next().trim();
    }

    /**
     * Get integer inputs
     *
     * @param credential
     * @return
     */
    public int getInt(final String credential) {
        System.out.println(credential);

        try {
            return SCANNER.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("give valid integers.");
            SCANNER.next();
            return getInt(credential);
        }
    }

    /**
     * Get double type input
     *
     * @param credential
     * @return
     */
    public double getDouble(final String credential) {
        System.out.println(credential);

        try {
            return SCANNER.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Give Double or integer type of inputs only.");
            return getDouble(credential);
        }
    }
}
