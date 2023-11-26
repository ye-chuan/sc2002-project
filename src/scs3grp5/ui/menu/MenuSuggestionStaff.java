package scs3grp5.ui.menu;

/**
 * This class is provides the menu to be printed in the suggesiton UI.
 * This clas is called when the user is a Staff and they processing a suggestion
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class MenuSuggestionStaff implements IPrintMenu{
    
    /** 
     * This method is to print the list of option when they are in the suggestion UI
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55
        System.out.println("SUGGESTION MENU");
        System.out.printf("\t(%d) Approve suggestion\n", ++option); 
        System.out.printf("\t(%d) Reject suggestion\n", ++option);
        System.out.println("-------------------------------------------------------"); 
        System.out.println("(press any non-numeric key to go to Suggestion List Menu)");
        return option; 
    }
    
}
