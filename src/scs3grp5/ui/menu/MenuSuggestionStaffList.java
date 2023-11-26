package scs3grp5.ui.menu;


/**
 * This class is provides the menu to be printed for list suggestion UI. 
 * This class is called when the user is a staff and they are in the List suggestion UI.
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class MenuSuggestionStaffList implements IPrintMenu {

    /** 
     * This method is to print the list of option for the suggestion list menu 
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55 
        System.out.println("SUGGESTION LIST MENU");
        System.out.printf("\t(%d) View pending suggestions\n", ++option); 
        System.out.printf("\t(%d) View approved suggestions\n", ++option);
        System.out.printf("\t(%d) View rejected suggestions\n", ++option);
        System.out.printf("\t(%d) View all suggestion\n", ++option); 
        System.out.printf("\t(%d) Go to Camp Menu\n", ++option); 
        System.out.printf("\t(%d) Go to HomePage\n", ++option); 
        System.out.printf("\t(0) Exit Program\n"); 
        System.out.println("-------------------------------------------------------"); 
        return option;
    }
    
}
