package scs3grp5.ui;

public class MenuEquiryReply implements IPrintMenu{

    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55
        System.out.println("ENQUIRY MENU");
        System.out.printf("\t(%d) Reply enquiry\n", ++option); 
        System.out.println("----------------------------------------------"); 
        System.out.println("(press any non-numeric key to go to Enquiry List Menu)");
        return option; 
    }
    
}
