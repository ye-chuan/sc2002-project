package scs3grp5.ui.menu;

/**
 * This class is provides the menu to be printed in the camp committee camp UI.
 * This class is called when the user is a camp committee of a camp.
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class MenuCampCampComm implements IPrintMenu {

    /** 
     * This method is to print the list of option for the camp camp committee menu
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        int option = 0;
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55  
        System.out.println("CAMP COMMITTEE MENU");
        System.out.printf("\t(%d) Generate Attendee List\n", ++option); 
        System.out.printf("\t(%d) View Enquiries\n", ++option); 
        System.out.printf("\t(%d) View Suggestions\n", ++option);
        System.out.printf("\t(%d) Go to Camp List Menu\n", ++option); 
        System.out.printf("\t(%d) Go to HomePage\n", ++option); 
        System.out.printf("\t(0) Exit Program\n"); 
        System.out.println("-------------------------------------------------------"); 
        return option; 
    }
    
}
