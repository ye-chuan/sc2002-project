import java.util.Scanner;

public class PrivilegedCampList extends NonPrivilegedCampList{

    /**
     * Contructor for StaffCampList class 
     * 
     * @param userID of the user using the UI 
     */
    public PrivilegedCampList(UIInformation uiInfo) {
        super(uiInfo);
    }

    @Override
    public void showUI() {
        int option = getUserChoiceUI();
        String campID; 

        if (option == 1){
            listOfCamps = CampListCont.viewAllCamp(uiInfo.getUserID());
            campID = selectFromListUI();
        }
        else if (option == 2){
            listOfCamps = CampListCont.viewCreatedCamp(uiInfo.getUserID()); 
            campID = selectFromListUI();
        }
        else if (option == 3){
            if (!filterSelection()) return; 
            campID = selectFromListUI();
        }
        else if (option == 4){
            campID = createCampUI();
            uiInfo.setCampID(campID);  
            uiInfo.setUIPage(UIPAGE.EDITCAMP);
            return;  
        }
        else if (option == 5){
            uiInfo.setUIPage(UIPAGE.HOMEPAGE);
            return; 
        }
        else{
            uiInfo.setUIPage(UIPAGE.ENDPROGRAM); 
            return; 
        } 
        if (campID.isEmpty()) return;
        uiInfo.setCampID(campID);  
        uiInfo.setUIPage(UIPAGE.CAMP); 
    }

    @Override
    protected int printListOfOption() {
        int option = 1; 
        System.out.println("----------------------------------------------"); 
        System.out.println("CAMP MENU"); 
        System.out.printf("(%d) View All Camps\n", option++); 
        System.out.printf("(%d) View My Camps\n", option++); 
        System.out.printf("(%d) Filter Selection of Camp\n", option++); 
        System.out.printf("(%d) Create a new Camp\n", option++);
        System.out.printf("(%d) Go to HomePage\n", option++); 
        System.out.printf("(%d) Exit Program\n", option); 
        System.out.println("----------------------------------------------"); 
        return option; 
    }

    @Override
    public void printList(){
        int option = 1; 

        System.out.println("----------------------------------------------"); 
        System.out.println("List Of Camps"); 
        System.out.println();
        for (String campID : listOfCamps){
            System.out.printf("(%d)\n", option++); 
            System.out.println("Camp Name: " + campCont.getName(campID)); 
            System.out.println("Date of Camp: " + campCont.getDate(campID));
            System.out.println("Registration closing date: " + campCont.getClosingDate(campID));
            System.out.println("Camp Staff-In-Charge: " + campCont.getCampInCharge(campID)); 
            System.out.println("Available Participants Slot: " + campCont.getRemainingParticipantSlot(campID)); 
            System.out.println("Available Camp Committee Slot: " + campCont.getRemainingCommitteeSlot(campID)); 
            System.out.println("Faculty: " + campCont.getFaculty(campID)); 
            System.out.println("Visibility: " + campCont.getVisibility(campID)); 
            System.out.println(); 
        }
        System.out.println("----------------------------------------------"); 
    }

    @Override
    protected int printFilterSelection(){
        int option = 1; 
        System.out.println("----------------------------------------------"); 
        System.out.println("Filter Selection");
        System.out.printf("(%d) By available participants slot: %s\n", option++, availableParticipant); 
        System.out.printf("(%d) By available camp committee slot: %s\n", option++, availableCommittee);
        System.out.printf("(%d) By faculty: %s\n", option++, byFaculty);
        System.out.printf("(%d) By location: %s\n", option++, byLocation); 
        System.out.printf("(%d) By date: %s\n", option++, byDate);
        System.out.printf("(%d) By visibility: %s\n", option, byVisibility);
        System.out.println("(0) Confirm filter selection"); 
        System.out.println("----------------------------------------------"); 

        return option; 
    }
    
    @Override
    protected void setDefaultFilter(){
        availableParticipant = false;
        availableCommittee = false; 
        byFaculty = false; 
        byVisibility = false;
        byDate = null;
        byLocation = null; 
    }
    
    /** 
     * This method provides the UI for user to create a camp
     */
    protected String createCampUI(){
        return campCont.create(uiInfo.getUserID());
    }
    
}
