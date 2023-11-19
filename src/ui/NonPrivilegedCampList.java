import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NonPrivilegedCampList extends UserInterface implements IListUI{

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
     * Filter setting for user to toggle
     * This filter is to show the camps that is still available for students to join as participants 
     */
    protected boolean availableParticipant;

    /** 
     * Filter setting for user to toggle 
     * This filter is to show the camps that is still available for students to join as committee 
     */
    protected boolean availableCommittee; 

    /** 
     * Filter setting for user to toggle
     * This filter is to show the camps that is available in their faculty 
     */
    protected boolean byFaculty; 

    /** 
     * Filter setting for user to toggle 
     * This filter is to show the camps by their visibility status (for staff only)
     */
    protected boolean byVisibility; 

     /** 
     * Filter setting for user to toggle 
     * This filter is to show the camps that is within the specified date 
     */
    protected String byDate;

    /** 
     * Filter setting for user to toggle 
     * This filter is to show the camp with the specified location 
     */
    protected String byLocation; 

    /** 
     * Constructor for StudentCampList class 
     * 
     * @param uiInfo contains the information of the user 
     */
    public NonPrivilegedCampList(UIInformation uiInfo) {
        super(uiInfo);
        campListCont = new CampListController(); 
        campCont = new CampController(); 
        setDefaultFilter();
    }

    @Override
    public void showUI() {
        int option = getUserChoiceUI();
        String campID; 

        if (option == 1){
            listOfCamps = CampListCont.viewAvailableCamp(uiInfo.getUserID());
            campID = selectFromListUI();
        }
        else if (option == 2){
            listOfCamps = CampListCont.viewJoinedCamp(uiInfo.getUserID()); 
            campID = selectFromListUI();
        }
        else if (option == 3){
            if (!filterSelection()) return; 
            campID = selectFromListUI();
        }
        else if (option == 4) uiInfo.setUIPage(UIPAGE.HOMEPAGE);
        else uiInfo.setUIPage(UIPAGE.ENDPROGRAM); 

        if (campID.isEmpty()) return;
        uiInfo.setCampID(campID);  
        uiInfo.setUIPage(UIPAGE.CAMP); 
    }

    @Override
    protected int printListOfOption() {
        int option = 1; 
        System.out.println("----------------------------------------------"); 
        System.out.println("CAMP LIST MENU"); 
        System.out.printf("(%d) View All Camps\n", option++); 
        System.out.printf("(%d) View My Camps\n", option++); 
        System.out.printf("(%d) Filter Selection of Camp\n", option++); 
        System.out.printf("(%d) Go to HomePage\n", option++); 
        System.out.printf("(0) Exit Program\n");
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
            System.out.printf("\n(%d)\n", option++); 
            System.out.println("Camp Name: " + campCont.getName(campID)); 
            System.out.println("Date of Camp: " + campCont.getDate(campID));
            System.out.println("Registration closing date: " + campCont.getClosingDate(campID));
            System.out.println("Available Participants Slot: " + campCont.getRemainingParticipantSlot(campID)); 
            System.out.println("Available Camp Committee Slot: " + campCont.getRemainingCommitteeSlot(campID)); 
            System.out.println("Faculty: " + campCont.getFaculty(campID)); 
        }
        System.out.println("(press any non-numeric key to go to Camp List Menu)");
        System.out.println("----------------------------------------------"); 
    }
    
    @Override
    public String selectFromListUI(){
        int option;
        int maxOption; 
    
        Scanner sc = new Scanner(System.in); 
    
        do{ 
          maxOption = listOfCamps.size()-1;
          printList();
          System.out.print("Select Camp: "); 
          try{
            option = sc.nextInt();
          }
          catch(InputMismatchException e){
            return null; 
          }
          if (!this.validOption(--option, maxOption)) System.out.println("Invalid Option!");
        } while (!this.validOption(option, maxOption)); 
    
        sc.close(); 

        return listOfCamps.get(option);
    }

    
    /**
     * This method prints the available filters for filtering the camp of list to show on the screen
     * 
     * @return the max number of options available 
     */
    protected int printFilterSelection(){
        int option = 1; 
        System.out.println("----------------------------------------------"); 
        System.out.println("Filter Selection");
        System.out.printf("(%d) By available participants slot: %s\n", option++, availableParticipant); 
        System.out.printf("(%d) By available camp committee slot: %s\n", option++, availableCommittee);
        System.out.printf("(%d) By faculty: %s\n", option++, byFaculty);
        System.out.printf("(%d) By Location: %s\n", option++, byLocation); 
        System.out.printf("(%d) By date: %s\n", option, byDate);
        System.out.println("(0) Confirm filter selection"); 
        System.out.println("(press any non-numeric key to go back)");
        System.out.println("----------------------------------------------"); 

        return option; 
    }


    /**
     * This method provides the UI for user to Set/Unset their filter during filter selection 
     */
    protected boolean filterSelection(){
        Scanner sc = new Scanner(System.in); 
        int option; 
        do{
            int maxOption = printFilterSelection(); 
            System.out.print("Set/Unset Filter: ");
            try{
                option = sc.nextInt();
            }
            catch(InputMismatchException e){
                return false;
            }
            
            if (option >= 0 && option <= maxOption){
                switch(option){
                    case 0: 
                        filterCampToPrint(); 
                        break; 
                    case 1: 
                        availableParticipant = toggleChoice(availableParticipant); 
                        break; 
                    case 2: 
                        availableCommittee = toggleChoice(availableCommittee);
                        break; 
                    case 3: 
                        byFaculty = toggleChoice(byFaculty); 
                        break;
                    case 4: 
                        byDate = setDateUI();
                        break; 
                    case 5: 
                        byLocation = setLocationUI(); 
                    case 6:
                        byVisibility = toggleChoice(byVisibility); 
                        break; 
                }
            }
            else{
                System.out.println("Invalid Option!");
            }
        }while (option != 0); 

        sc.close();
        return true; 
    }


    /**
     * This method tells the CampList Controller the filters the user Set and Update the listOfCamps to be printed
     */
    protected void filterCampToPrint(){
        listOfCamps = CampListCont.filterBy(uiInfo.getUserID(), byLocation, availableParticipant, availableCommittee, byDate, byFaculty, byVisibility); 
    }

    /**
     * This method provides the UI for user to select a specific date 
     * 
     * @return the specified date 
     */
    protected String setDateUI(){

    }


    /**
     * This method provides the UI for user to select a specific location 
     * 
     * @return the specified date 
     */
    protected String setLocationUI(){

    }


    /**
     * This method set the filter to their default state for this user 
     */
    protected void setDefaultFilter(){
        availableParticipant = false;
        availableCommittee = false; 
        byFaculty = false; 
        byVisibility = true;
        byDate = null;
        byLocation = null; 
    }

    /** 
     * This method toggles the setting of the filters
     * 
     * @return the new setting of the filters 
     */
    protected boolean toggleChoice(boolean choice){
        if (choice) return false; 
        else return true;
    }
    
}
