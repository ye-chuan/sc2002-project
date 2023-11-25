package scs3grp5.ui.menu;

public class MenuCampStudent implements IPrintMenu {

    /**
     * to communicate with CampController
     */
    private boolean isParticipant; 

    public MenuCampStudent(boolean isParticipant){
        this.isParticipant = isParticipant;
    }
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
        System.out.println("----------------------------------------------"); 
        return option; 
    }
    
}
