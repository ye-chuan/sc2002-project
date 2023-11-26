package scs3grp5.ui.input;

import scs3grp5.ui.ulti.OptionException;

/**
 * this interface gives classes the ability to select option
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public interface ISelectOption  {

    /**
     * This method helps to get a valid option from the user.
     * At each try, they will access if the option is an invalid input
     * if it is invalid, this method will throw an exception signalling that there
     * is a option exception.  
     * 
     * @return the option chosen by the user
    */
    public int getUserChoiceUI(int maxOption, boolean wrongInput) throws OptionException;
}
