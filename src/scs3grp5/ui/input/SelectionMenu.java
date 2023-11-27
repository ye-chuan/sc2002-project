package scs3grp5.ui.input;

import java.util.InputMismatchException;
import java.util.Scanner;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.SelectionHelper;

/**
 * This classes provides UIs to select option from a menu
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class SelectionMenu implements ISelectOption {

    /**
     * This method helps users to select an option from a menu. This method throws an exception when
     * the user input is out of range and when they key in a non-numeric input.
     * 
     * @throws OptionException when User key in a out of range option or non-numeric key
     * @return the option chosen
     */
    @Override
    public int getUserChoiceUI(int maxOption, boolean wrongInput) throws OptionException{
    
        int option;
        Scanner sc = new Scanner(System.in); 
        
        if (wrongInput) System.out.println("Wrong Input!");
        System.out.print("Select option: "); 
        try{
            option = sc.nextInt();
        }
        catch(InputMismatchException e){
            throw new OptionException();
        }

        if (!SelectionHelper.validOption(option, 0, maxOption)) throw new OptionException(); 
        return option;
    }

}
