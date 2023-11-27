package scs3grp5.controller;

/**
 * The {@link PasswordException} class is a custom exception that is thrown when  
 * password entered does not meet the system requirements
 * It extends the {@link Exception} class.
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class PasswordException extends Exception {
    public PasswordException(String message) {
        super(message);
    }
}
