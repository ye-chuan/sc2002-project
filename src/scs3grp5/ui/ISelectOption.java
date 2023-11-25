package ui;

/**
 * this interface gives classes the ability to select option
 */
public interface ISelectOption  {

    /**
     * This method helps to get a valid option from the user.
     * At each try, printListOfOption will be called and user will be prompt
     * to key in their input. 
     * 
     * @return the option chosen by the user
    */
    public int getUserChoiceUI(int maxOption, boolean wrongInput) throws OptionException;
}
