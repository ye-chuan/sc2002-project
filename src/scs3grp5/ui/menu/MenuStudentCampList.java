package scs3grp5.ui.menu;

/**
 * This class is provides the menu to be printed in the list camp UI.
 * This class is called when the user is student
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class MenuStudentCampList implements IPrintMenu{

    /** 
     * This method is to print the list of option for the student camp list menu.
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55  
        System.out.println("CAMP LIST MENU"); 
        System.out.printf("\t(%d) View All Camps\n", ++option); 
        System.out.printf("\t(%d) View My Camps\n", ++option);
        System.out.printf("\t(%d) Go to Home Page\n", ++option); 
        System.out.printf("\t(0) Exit Program\n");
        System.out.println("-------------------------------------------------------"); 
        return option; 
    }
    
}
