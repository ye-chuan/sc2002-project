package scs3grp5.ui.input;

import java.util.InputMismatchException;
import java.util.Scanner;

import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.SelectionHelper;


/**
 * This classes provides UIs to select option from a list of options
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class SelectionFromList implements ISelectOption{

    /**
     * This method helps users to select an option from a list, ensuring that their input 
     * will not go out of range of the valid range. This method throws an exception when
     * they key in a non-numeric key. 
     * 
     * @return the option chosen (if return -1, this means that the option is out of range)
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

        if (!SelectionHelper.validOption(option, 1, maxOption)) return -1; 
        return option;
    }

}
