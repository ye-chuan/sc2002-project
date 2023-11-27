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
    /**
     * Throws exception when user fails to register to camp
     * @param message Exception message for not meeting registration conditions
     */
    public RegistrationException(String message) {
        super(message);
    }
}
