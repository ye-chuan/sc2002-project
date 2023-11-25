package ui;

public class MenuHomepage implements IPrintMenu {

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
