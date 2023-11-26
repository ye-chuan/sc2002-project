package scs3grp5.ui.menu;

/**
 * This class is provides the menu to be printed in the student camp UI.
 * This class is called when the user is a non commitee member of a camp. (not staff IC and camp commitee)
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class MenuCampStudent implements IPrintMenu {

    /**
     * to communicate with CampController
     */
    private boolean isParticipant; 

    /**
     * Constructor for the MenuCampStudent class 
     * 
     * @param isParticipant is they are already a participants 
     */
    public MenuCampStudent(boolean isParticipant){
        this.isParticipant = isParticipant;
    }

    /** 
     * This method is to print the list of option for the student camp menu
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        int option = 0; 
        
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55  
        System.out.println("CAMP MENU");
        if (!isParticipant){
            System.out.printf("\t(%d) Register as Committee\n", ++option); 
            System.out.printf("\t(%d) Register as Participant\n", ++option); 
        } 
        if (isParticipant){
            System.out.printf("\t(%d) Withdraw from camp\n", ++option);
        }
        System.out.printf("\t(%d) View Enquiries\n", ++option); 
        System.out.printf("\t(%d) Go to Camp List Menu\n", ++option); 
        System.out.printf("\t(%d) Go to HomePage\n", ++option); 
        System.out.printf("\t(0) Exit Program\n"); 
        System.out.println("-------------------------------------------------------"); 
        return option; 
    }
    
}
