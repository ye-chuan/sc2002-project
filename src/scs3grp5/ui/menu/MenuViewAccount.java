package scs3grp5.ui.menu;

/**
 * This class is provides the menu to be printed in the viewAccount UI
 * This class is called regardless of the user type.
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class MenuViewAccount implements IPrintMenu{

    /** 
     * This method is to print the list of option for the menu page for a user
     * to view their account 
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55   
        System.out.println("ACCOUNT MENU"); 
        System.out.printf("\t(%d) View Account Detail\n", ++option); 
        System.out.printf("\t(%d) Change Password\n", ++option); 
        System.out.printf("\t(%d) Go to HomePage\n", ++option); 
        System.out.printf("\t(0) Exit Program\n"); 
        System.out.println("-------------------------------------------------------"); 
        return option; 
    }
    
}
