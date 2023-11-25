package ui;

public class MenuSuggestionCommitteeList implements IPrintMenu{

    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55 
        System.out.println("SUGGESTION LIST MENU");
        System.out.printf("\t(%d) View pending suggestions\n", ++option); 
        System.out.printf("\t(%d) View approved suggestions\n", ++option);
        System.out.printf("\t(%d) View rejected suggestions\n", ++option);
        System.out.printf("\t(%d) View all suggestion\n", ++option);
        System.out.printf("\t(%d) Create new suggestion\n", ++option);  
        System.out.printf("\t(%d) Go to Camp Menu\n", ++option); 
        System.out.printf("\t(%d) Go to HomePage\n", ++option); 
        System.out.printf("\t(0) Exit Program\n"); 
        System.out.println("----------------------------------------------"); 
        return option;
    }
    
}
