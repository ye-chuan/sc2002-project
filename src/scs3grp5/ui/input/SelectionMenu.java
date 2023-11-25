package scs3grp5.ui.input;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SelectionMenu implements ISelectOption {

    @Override
    public int getUserChoiceUI(int maxOption, boolean wrongInput) throws OptionException{
    
        int option;
        Scanner sc = new Scanner(System.in); 
        
        if (wrongInput) System.out.println("Wrong Input!");
        System.out.print("Select option: "); 
        try{
            option = sc.nextInt();
            sc.close();
        }
        catch(InputMismatchException e){
            throw new OptionException();
        }

        if (!SelectionHelper.validOption(option, 0, maxOption)) throw new OptionException(); 
        return option;
    }

}
