package scs3grp5.ui.ulti;

import java.util.NoSuchElementException;
import java.util.Scanner;

import scs3grp5.entity.Date;

/** 
 * A class that contains static methods that will be used in the program to 
 * check if a selection or input is made legally.It is use throughout the UI program
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class SelectionHelper {
    
    /**
     * This method check if the option is a valid option 
     * 
     * @param option Option chosen by the user 
     * @param  min the minimum option can take
     * @param  max the maximum option can take
     * @return True if its a valid option else False
    */
    public static boolean validOption(int option, int min, int max){
        if (option >= min && option <= max) return true; 
        return false;   
    }

    /**
     * This method provides the UI for users to key in a date
     * 
     * @return a valid date
     */
    public static String dateSelectUI(){

        Scanner sc = new Scanner(System.in); 
        String date =""; 
        boolean loop = false; 
        do{ 
            if (loop) System.out.println("Invalid Date!");
            System.out.print("Enter date in this form DD/MM/YYYY:");
            try{
                date = sc.nextLine();
                loop = false; 
            }catch(NoSuchElementException e){
                loop = true; 
                continue; 
            }
            if (!Date.isValidDate(date)) loop = true;
        }while (loop);

        return date; 
    }
}
