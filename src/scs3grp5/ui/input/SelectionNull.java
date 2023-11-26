package scs3grp5.ui.input;

import java.util.Scanner;
import scs3grp5.ui.ulti.OptionException;

/**
 * This classes provides UIs to keep polling and only continue after user have input a key .
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class SelectionNull implements ISelectOption{

    /**
     * This method polls for user input and throws an exception when polling is stop. 
     * 
     * @return null
     */
    @Override
    public int getUserChoiceUI(int maxOption, boolean wrongInput) throws OptionException {
        Scanner sc = new Scanner(System.in); 
        sc.next(); 
        throw new OptionException();
    }
    
}
