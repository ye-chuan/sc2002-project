package ui;

public class MenuEnquiryStudentList implements IPrintMenu {

    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55          System.out.println("ENQUIRY LIST MENU");
        System.out.printf("\t(%d) View pending enquiries\n", ++option); 
        System.out.printf("\t(%d) View replied enquiries\n", ++option);
        System.out.printf("\t(%d) View all enquiry\n", ++option); 
        System.out.printf("\t(%d) Create new enquiry\n", ++option);
        System.out.printf("\t(%d) Go to Camp Menu\n", ++option); 
        System.out.printf("\t(%d) Go to HomePage\n", ++option); 
        System.out.printf("\t(0) Exit Program\n"); 
        System.out.println("----------------------------------------------"); 
        return option;
    }
    
}
