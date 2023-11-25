package scs3grp5.ui.menu;

public class MenuCampStaff implements IPrintMenu {

    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55 
        System.out.println("CAMP CREATOR MENU");
        System.out.printf("\t(%d) Edit Camp Details\n", ++option); 
        System.out.printf("\t(%d) Generate Attendee List\n", ++option); 
        System.out.printf("\t(%d) Generate Performance Report\n", ++option); 
        System.out.printf("\t(%d) View Enquiries\n", ++option); 
        System.out.printf("\t(%d) View Suggestions\n", ++option);
        System.out.printf("\t(%d) Go to Camp List Menu\n", ++option); 
        System.out.printf("\t(%d) Go to HomePage\n", ++option); 
        System.out.printf("\t(0) Exit Program\n"); 
        System.out.println("-------------------------------------------------------"); 
        return option; 
    }
    
}
