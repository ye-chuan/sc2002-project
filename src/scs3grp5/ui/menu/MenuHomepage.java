package scs3grp5.ui.menu;

/**
 * This class is provides the menu to be printed in the Homepage UI.
 * This class is called when the user is staff or student
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class MenuHomepage implements IPrintMenu {

    /** 
     * This method is to print the list of option of the homepage menu
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55  
        System.out.printf("HOMEPAGE MENU\n"); 
        System.out.printf("\t(%d) View Account\n", ++option); 
        System.out.printf("\t(%d) View Camps\n", ++option); 
        System.out.printf("\t(0) Exit Program\n"); 
        System.out.println("-------------------------------------------------------"); 
        return option; 
    }
    
}
