package ui;

public class NonPrivilegedCampView extends UserInterface{

    /**
     * to communicate with CampController
     */
    private CampController campCont; 

    /** 
     * Constructor for NonPrivilegedCampView class 
     * 
     * @param uiInfo contains the information of the user  
     */
    public NonPrivilegedCampView(UIInformation uiInfo) {
        super(uiInfo);
        campCont = new CampController(); 
    }

    @Override
    public void showUI() {
        String status  = campCont.getUserStatus(uiInfo.getUserID(), uiInfo.getCampID());
        int option = getUserChoiceUI();
        if (status.isEmpty()) option = option-2; 
        if (status.equals("PARTICIPANT")) option--; 

        if (option == -1) campCont.registerAsCommittee(uiInfo.getUserID(), uiInfo.getCampID()); 
        else if (option == 0){
            if (status.equals("PARTICIPANT")) campCont.withdraw(uiInfo.getUserID(), uiInfo.getCampID());
            else campCont.registerAsParticipant(uiInfo.getUserID(), uiInfo.getCampID()); 
        }
        else if (option == 1) uiInfo.setUIPage(UiPage.ENQURYLIST);
        else if (option == 2) uiInfo.setUIPage(UiPage.CAMPLIST); 
        else if (option == 3) uiInfo.setUIPage(UiPage.HOMEPAGE); 
        else uiInfo.setUIPage(UiPage.ENDPROGRAM);

        return; 
    }

    @Override
    protected int printListOfOption() {
        
        int option = 1; 
        String status  = campCont.getUserStatus(uiInfo.getUserID(), uiInfo.getCampID());
        
        printCampDetail();
        System.out.println("----------------------------------------------"); 
        System.out.println("CAMP MENU");
        if (status.isEmpty()){
            System.out.printf("(%d) Register as Committee\n", option++); 
            System.out.printf("(%d) Register as Participant\n", option++); 
        } 
        if (status.equals("PARTICIPANT")){
            System.out.printf("(%d) Withdraw from camp\n", option++);
        }
        System.out.printf("(%d) View Enquiries\n", option++); 
        System.out.printf("(%d) Go to Camp List Menu\n", option++); 
        System.out.printf("(%d) Go to HomePage\n", option++); 
        System.out.printf("(0) Exit Program\n"); 
        System.out.println("----------------------------------------------"); 
        return option; 
    }

    /** 
     * This method prints the camp details for this User
     */
    protected void printCampDetail(){
        System.out.println("----------------------------------------------"); 
        System.out.println("CAMP INFORMATION"); 
        System.out.println("Camp Name: " + campCont.getName(uiInfo.getCampID())); 
        System.out.println("Date of Camp: " + campCont.getDate(uiInfo.getCampID()));
        System.out.println("Registration closing date: " + campCont.getClosingDate(uiInfo.getCampID()));
        System.out.println("Available Participants Slot: " + campCont.getRemainingParticipantSlot(uiInfo.getCampID())); 
        System.out.println("Available Camp Committee Slot: " + campCont.getRemainingCommitteeSlot(uiInfo.getCampID())); 
        System.out.println("Faculty: " + campCont.getFaculty(uiInfo.getCampID())); 
        System.out.println("Camp Description: " + campCont.getDescription(uiInfo.getCampID())); 
        System.out.println("Status: " + campCont.getUserStatus(uiInfo.getUserID(), uiInfo.getCampID()));
        System.out.println("----------------------------------------------"); 

    }
    
}
