package scs3grp5.ui.menu;

/**
 * This class is provides the menu to be printed in the list enquiry UI.
 * This class is called when the user is a commitee member of a camp. (staff IC and camp commitee)
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class MenuEnquiryCommitteeList implements IPrintMenu {

    /** 
     * This method is to print the list of option of a committee enquiry list 
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55   
        System.out.printf("\t(%d) View pending enquiries\n", ++option); 
        System.out.printf("\t(%d) View replied enquiries\n", ++option);
        System.out.printf("\t(%d) View all enquiry\n", ++option); 
        System.out.printf("\t(%d) Go to Camp Menu\n", ++option); 
        System.out.printf("\t(%d) Go to HomePage\n", ++option); 
        System.out.printf("\t(0) Exit Program\n"); 
        System.out.println("-------------------------------------------------------");  
        return option;
    }
    
}
