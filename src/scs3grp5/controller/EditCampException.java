package scs3grp5.controller;

/**
 * The {@link EditCampException} class is a custom exception that is thrown when  
 * the camp in charge is prevented from modifying camp information or deleting the camp.
 * It extends the {@link Exception} class.
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class EditCampException extends Exception {
    /**
     * Exception for editing or deleting camp details
     * @param message Exception Message
     */
    public EditCampException(String message) {
        super(message);
    }
}
