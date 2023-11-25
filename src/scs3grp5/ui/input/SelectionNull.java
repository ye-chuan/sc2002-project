package scs3grp5.ui.input;

import java.util.Scanner;
import scs3grp5.ui.ulti.OptionException;

public class SelectionNull implements ISelectOption{

    @Override
    public int getUserChoiceUI(int maxOption, boolean wrongInput) throws OptionException {
        Scanner sc = new Scanner(System.in); 
        sc.next(); 
        throw new OptionException();
    }
    
}
