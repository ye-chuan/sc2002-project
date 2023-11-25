package scs3grp5.ui.input;

import java.util.Scanner;

public class SelectionNull implements ISelectOption{

    @Override
    public int getUserChoiceUI(int maxOption, boolean wrongInput) throws OptionException {
        System.out.println("(press any key to go back)");
        Scanner sc = new Scanner(System.in); 
        sc.next(); 
        sc.close(); 
        throw new OptionException();
    }
    
}
