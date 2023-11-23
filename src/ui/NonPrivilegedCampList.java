package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NonPrivilegedCampList extends UserInterface implements IListUi{

    /**
     * to communicate with CampListController
     */
    protected CampListController campListCont;

    /**
     * to communicate with CampController
     */
    protected CampController campCont; 

    /**
     * contains the listOfCamps to be shown on the UI
     */
    protected ArrayList<String> listOfCamps; 

    /**
     * This attribute allow us to decide to include the filter option for user \
     * during the selection of the list of camps 
     */
    protected boolean includeFilterSetting;

    /** 
     * Constructor for StudentCampList class 
     * 
     * @param uiInfo contains the information of the user 
     */
    public NonPrivilegedCampList(UIInformation uiInfo) {
        super(uiInfo);
        campListCont = new CampListController(); 
        campCont = new CampController(); 
    }

    @Override
    public void showUI() {
        int option = getUserChoiceUI();
        String campID; 

        if (option == 1){
            CampListCont.setDefaultFilter(uiInfo.getUserID());
            includeFilterSetting = true; 
            campID = selectFromListUI();
        }
        else if (option == 2){
            includeFilterSetting = false; 
            campID = selectFromListUI();
        }
        else if (option == 3) uiInfo.setUIPage(UiPage.HOMEPAGE);
        else uiInfo.setUIPage(UiPage.ENDPROGRAM); 

        if (campID.isEmpty()) return;
        uiInfo.setCampID(campID);  
        uiInfo.setUIPage(UiPage.CAMP); 
    }

    @Override
    protected int printListOfOption() {
        int option = 1; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55  
        System.out.println("CAMP LIST MENU"); 
        System.out.printf("\t(%d) View All Camps\n", option++); 
        System.out.printf("\t(%d) View My Camps\n", option++); 
        System.out.printf("\t(%d) Go to Home Page\n", option++); 
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
            String participant = "AVAILABLE";
            String campComm = "AVAILABLE"; 

            String name = campCont.getName(campID); 
            if (campCont.getRemainingParticipantSlot(campID) == 0) participant = "FULL"; 
            if (campCont.getRemainingCommitteeSlot(campID) == 0) campComm = "FULL"; 
            if (campCont.isCampOver(campID)){
                date = "REGISTRATION CLOSED";
                participant = "N/A"; 
                campComm = "N/A";
            }

            System.out.println("│ ("+ (++option) + ") │" + fillUpSpace(name, 85, 3,false) + "│"); 

            if (date.equals("REGISTRATION CLOSED")) System.out.println("│     │" + fillUpSpace(" ", 60, 0, false) + StringConstants.ANSI_RED + fillUpSpace(date,25, 0, false) + StringConstants.ANSI_RESET + "│"); 
            else System.out.println("│     │" + fillUpSpace(" ", 60, 0, false) + fillUpSpace(date, 25, 0, false) + "│"); 

            if (participant.equals("AVAILABLE")) System.out.println("│     │ PARTICIPANT SLOT:" + StringConstants.ANSI_GREEN +fillUpSpace(participant, 67, 1, false) + StringConstants.ANSI_RESET + "│");
            else System.out.println("│     │ PARTICIPANT SLOT:" + StringConstants.ANSI_RED + fillUpSpace(participant, 67, 1, false) + StringConstants.ANSI_RESET + "│");
            
            if (campComm.equals("AVAILABLE")) System.out.println("│     │ CAMP COMMITTEE SLOT:" + StringConstants.ANSI_GREEN +fillUpSpace(campComm, 64, 1, false) + StringConstants.ANSI_RESET + "│");
            else System.out.println("│     │ CAMP COMMITTEE SLOT:" + StringConstants.ANSI_RED + fillUpSpace(campComm, 64, 1, false) + StringConstants.ANSI_RESET + "│");
            if (option != listOfCamps.size())
                System.out.println("├─────┼─────────────────────────────────────────────────────────────────────────────────────┤");
            else 
                System.out.println("└─────┴─────────────────────────────────────────────────────────────────────────────────────┘");
        }
        System.out.println("(press any non-numeric key to go to Camp List Menu)");
        System.out.println("----------------------------------------------"); 
    }
    

    @Override
    public String selectFromListUI(){
        int option;
        int maxOption; 
        boolean wrongInput = false;
    
        Scanner sc = new Scanner(System.in); 
    
        do{ 
          ChangePage.changePage();

          if(includeFilterSetting){
            listOfCamps = campCont.viewCamps();
            printList(); 
            System.out.println("(press 0 to filter list)");
          }
          else{
            listOfCamps = campCont.viewMyCamps(uiInfo.getUserID());
            printList();
          }
          maxOption = listOfCamps.size()-1;

          if (wrongInput){
            System.out.println("Invalid OPTION!");
            System.out.println("Select a valid Camp: ");
          }
          else System.out.print("Select Camp: "); 

          try{
            option = sc.nextInt();
          }
          catch(InputMismatchException e){
            return null; 
          }

          if (--option == -1 && includeFilterSetting){
            FilterUI filterUI = new FilterUI(campListCont.isStaff(uiInfo.getUserID()));
            if (filterUI.filterSelection()) continue; 
          }
          wrongInput = true; 
        } while (!this.validOption(option, maxOption)); 
    
        sc.close(); 

        return listOfCamps.get(option);
    }
}
