package scs3grp5.controller;

/**
 * The {@link EditCampException} class is a custom exception that is thrown when  
 * the camp in charge is prevented from modifying camp information or deleting the camp.
 * It extends the {@link Exception} class.
 */
public class EditCampException extends Exception {
    public EditCampException(String message) {
        super(message);
    }
}
