package scs3grp5.controller;

/**
 * The {@link RegistrationException} class is a custom exception that is thrown when  
 * user fails to join the camp due to user status and camp status
 * It extends the {@link Exception} class.
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class RegistrationException extends Exception {
    public RegistrationException(String message) {
        super(message);
    }
}
