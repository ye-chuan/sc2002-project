package scs3grp5.ui.menu;

public class MenuSuggestionEdit implements IPrintMenu {

    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55
        System.out.println("SUGGESTION MENU");
        System.out.printf("\t(%d) Edit suggestion\n", ++option); 
        System.out.printf("\t(%d) Delete suggestion\n", ++option);
        System.out.println("-------------------------------------------------------"); 
        System.out.println("(press any non-numeric key to go to Enquiry List Menu)");
        return option; 
    }
    
}
