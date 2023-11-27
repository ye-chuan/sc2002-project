package scs3grp5.controller;

/**
 * The {@link InvalidDateException} class is a custom exception that is thrown when  
 * the camp in charge is prevented from modifying camp information due to illegal dates entered that are not in order.
 * It extends the {@link Exception} class.
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class InvalidDateException extends Exception {
    /**
     * Custom Exception that throw error message when Date is invalid for Camp
     * @param message Exception message for Invalid Date
     */
    public InvalidDateException(String message) {
        super(message);
    }
}
