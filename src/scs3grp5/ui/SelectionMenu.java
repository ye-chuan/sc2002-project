package ui;

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

        if (!validOption(option, maxOption)) throw new OptionException(); 
        return option;
    }

    /**
   * This method check if the option is a valid option 
   * 
   * @param option Option chosen by the user 
   * @param max the maximum option can take
   * @return True if its a valid option else False
   */
    private boolean validOption(int option, int max){
        if (option >= 0 && option <= max) return true; 
        return false;   
    }

}
