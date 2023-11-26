package scs3grp5.ui.menu;


/**
 * This class is provides the menu to be printed in the edit enquiry UI.
 * This class is called when the user is student and a participant of a camp. 
 * They will only be able to access this page if their enquiry have not been replied.
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class MenuEquiryEdit implements IPrintMenu{

    /** 
     * This method is to print the list of option edit equiry menu
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55
        System.out.println("ENQUIRY MENU");
        System.out.printf("\t(%d) Edit enquiry\n", ++option); 
        System.out.printf("\t(%d) Delete enquiry\n", ++option);
        System.out.println("-------------------------------------------------------"); 
        System.out.println("(press any non-numeric key to go to Enquiry List Menu)");
        return option; 
    }
    
}
