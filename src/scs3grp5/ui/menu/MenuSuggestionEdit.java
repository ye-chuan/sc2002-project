package scs3grp5.ui.menu;

/**
 * This class is provides the menu to be printed in the edit suggestion UI.
 * This class is called when the user is a camp commiitee of a camp and their status 
 * of the suggestion have not been process
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class MenuSuggestionEdit implements IPrintMenu {

    /** 
     * This method is to print the list of option for the suggestion menu
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55
        System.out.println("SUGGESTION MENU");
        System.out.printf("\t(%d) Edit suggestion\n", ++option); 
        System.out.printf("\t(%d) Delete suggestion\n", ++option);
        System.out.println("-------------------------------------------------------"); 
        System.out.println("(press any non-numeric key to go to Suggestion List Menu)");
        return option; 
    }
    
}
