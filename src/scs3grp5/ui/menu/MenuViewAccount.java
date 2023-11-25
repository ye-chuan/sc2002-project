package scs3grp5.ui.menu;

public class MenuViewAccount implements IPrintMenu{

    @Override
    public int printMenu() {
        int option = 1; 
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
