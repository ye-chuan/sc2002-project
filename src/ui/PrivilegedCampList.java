package ui;

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
        String campID = ""; 

        if (option == 1){
            CampListCont.setDefaultFilter(uiInfo.getUserID());
            includeFilterSetting = true; 
            campID = selectFromListUI();
        }
        else if (option == 2){
            includeFilterSetting = false; 
            campID = selectFromListUI();
        }
        else if (option == 3){
            campID = createCampUI();
            uiInfo.setCampID(campID);  
            uiInfo.setUIPage(UiPage.EDITCAMP);
            return;  
        }
        else if (option == 4){
            uiInfo.setUIPage(UiPage.HOMEPAGE);
            return; 
        }
        else{
            uiInfo.setUIPage(UiPage.ENDPROGRAM); 
            return; 
        } 
        if (campID.isEmpty()) return;
        uiInfo.setCampID(campID);  
        uiInfo.setUIPage(UiPage.CAMP); 
    }

    @Override
    protected int printListOfOption() {
        int option = 1; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55   
        System.out.println("CAMP MENU"); 
        System.out.printf("\t(%d) View All Camps\n", option++); 
        System.out.printf("\t(%d) View My Camps\n", option++); 
        System.out.printf("\t(%d) Create a new Camp\n", option++);
        System.out.printf("\t(%d) Go to Home Page\n", option); 
        System.out.printf("\t(0) Exit Program\n"); 
        System.out.println("----------------------------------------------"); 
        return option; 
    }

    @Override
    public void printList(){
        int option = 0; 

        System.out.println("List Of Camps"); 
        System.out.println("┌─────┬─────────────────────────────────────────────────────────────────────────────────────┐");// 85 WHITE SPACE
        for (String campID : listOfCamps){
            String date = campCont.getDate(uiInfo.getCampID()); 

            int actualCount = campCont.getTotalParticipantSlot(uiInfo.getCampID()) - campCont.getRemainingParticipantSlot(uiInfo.getCampID()); 
            String participant = Integer.toString(actualCount) + "/" + Integer.toString(campCont.getTotalParticipantSlot(uiInfo.getCampID()));
            actualCount = campCont.getTotalCampCommSlot(uiInfo.getCampID()) - campCont.getRemainingCampCommSlot(uiInfo.getCampID()); 
            String campComm = Integer.toString(actualCount) + "/" + Integer.toString(campCont.getTotalCampCommSlots(uiInfo.getCampID()));
            String visibility = "ON";

            String name = campCont.getName(campID); 
            if (campCont.isCampOver(campID)) date = "REGISTRATION CLOSED";

            System.out.println("│ ("+ (++option) + ") │" + fillUpSpace(name, 85, 3, false) + "│"); 

            if (date.equals("REGISTRATION CLOSED")) System.out.println("│     │" + fillUpSpace(" ", 60, 0, false) + StringConstants.ANSI_RED + fillUpSpace(date,25,0, false) + StringConstants.ANSI_RESET + "│"); 
            else System.out.println("│     │" + fillUpSpace(" ", 60, 0, false) + fillUpSpace(date,25, 0, false) + "│"); 

            System.out.println("│     │ PARTICIPANT SLOT:" + fillUpSpace(participant, 67, 1,false)  + "│");
            
            System.out.println("│     │ CAMP COMMITTEE SLOT:" + fillUpSpace(campComm, 64, 1,false) + "│");

            if (visibility.equals("ON")) System.out.println("│     │ VISIBILITY:" + StringConstants.ANSI_GREEN +fillUpSpace(visibility, 73, 1,false) + StringConstants.ANSI_RESET + "│");
            else System.out.println("│     │ VISIBILITY:" + StringConstants.ANSI_RED + fillUpSpace(visibility, 73, 1, false) + StringConstants.ANSI_RESET + "│");
            
            if (option != listOfCamps.size())
                System.out.println("├─────┼─────────────────────────────────────────────────────────────────────────────────────┤");
            else 
                System.out.println("└─────┴─────────────────────────────────────────────────────────────────────────────────────┘");
        }
        System.out.println("(press any non-numeric key to go to Camp List Menu)");
        System.out.println("----------------------------------------------"); 
        }
    
    /** 
     * This method provides the UI for user to create a camp
     */
    protected String createCampUI(){
        return campCont.create(uiInfo.getUserID());
    }
    
}
