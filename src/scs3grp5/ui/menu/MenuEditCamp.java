package scs3grp5.ui.menu;

/**
 * This class is provides the menu to be printed in the edit camp UI.
 * This class is called when the user is a Staff IC of a camp.
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class MenuEditCamp implements IPrintMenu {

    /** 
     * This method is to print the list of option of the edit camp Menu
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55 
        System.out.println("CAMP EDIT MENU");
        System.out.println("\t(1) Camp Name"); 
        System.out.println("\t(2) Start Date of Camp");
        System.out.println("\t(3) End Date of Camp");
        System.out.println("\t(4) Registration closing date");
        System.out.println("\t(5) Location"); 
        System.out.println("\t(6) Participants Slot"); 
        System.out.println("\t(7) Camp Committee Slot"); 
        System.out.println("\t(8) Faculty"); 
        System.out.println("\t(9) Visibility");
        System.out.println("\t(10) Camp Description");
        System.out.println("\t(0) Confirm");
        System.out.println("-------------------------------------------------------"); 
        return 10; 
    }
    
}
