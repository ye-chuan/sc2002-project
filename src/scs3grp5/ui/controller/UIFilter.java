package scs3grp5.ui.controller;

import java.util.Scanner;
import scs3grp5.controller.CampListController;
import scs3grp5.ui.input.ISelectOption;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.IPrintMenu;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.PrintHelper;
import scs3grp5.ui.ulti.SelectionHelper;

/**
 * This class is a UI class for the UI Filter
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UIFilter implements IPrintMenu, IUserInterface {

    /**
     * to communicate with CampListController
     */
    private CampListController campListCont;

    /**
     * Filter setting for user to toggle
     * This filter is to show the camps that is still available for students to join as participants 
     */
    private boolean availableParticipant;

    /** 
     * Filter setting for user to toggle 
     * This filter is to show the camps that is still available for students to join as committee 
     */
    private boolean availableCommittee; 

    /** 
     * Filter setting for user to toggle
     * This filter is to show the camps that is available in their faculty 
     * true if it is for their faculty else false
     */
    private boolean byFaculty; 

    /** 
     * Filter setting for user to toggle 
     * This filter is to show the camps by their visibility status (for staff only)
     */
    private boolean byVisibility; 

     /** 
     * Filter setting for user to toggle 
     * This filter is to show the camps that is within the specified date 
     */
    private String byDate;

    /** 
     * Filter setting for user to toggle 
     * This filter is to show the camp with the specified location 
     */
    private String byLocation; 

    /**
     * If user is currently aStaff 
     * true if isStaff, else student 
     */
    private boolean isStaff; 

    /**
     * The userID of the string
     */
    private String userID; 

    /**
     * Constructor class for the UIFilter 
     * 
     * @param userID is the userID using this UI
     * @param isStaff if the user is a staff 
     * @param campListCont the same camp controller that the campListUI is using 
     */
    public UIFilter(String userID, boolean isStaff, CampListController campListCont){
        this.isStaff = isStaff; 
        this.userID = userID; 
        this.campListCont = campListCont; 
        setDefaultFilter();
    }

    /**
     * This method controls the logic when user is selecting their option to filter, 
     * This method will end when user key in 0 to confirm their filter selection,
     * else when a non-numeric key is key to go back to the campList view
     */
    @Override
    public IUserInterface showUI() {

        ISelectOption optionSelector = new SelectionMenu();
        
        do{ 

            ChangePage.changePage();
            System.out.println(PrintHelper.LOGO_STRING);
            System.out.println();
            int option = -1; 

            boolean wrongInput = false;
            try{
                option = optionSelector.getUserChoiceUI(printMenu(), wrongInput);
            }
            catch (OptionException e){
                return null; 
            }
        
            switch(option){
                case 0: 
                    filterCampToPrint(); 
                    return null; 
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
                    byLocation = setLocationUI(); 
                    break; 
                case 5: 
                    byDate = setDateUI();
                    break;
                case 6:
                    if (isStaff) byVisibility = toggleChoice(byVisibility); 
                    else setDefaultFilter(); 
                    break; 
                case 7: 
                    setDefaultFilter();
                default: 
                    wrongInput = true;
            }
        }while (true);

    }

    /**
     * This method shows the menu for the filter. The setting of the filter will be shown on the UI as well
     * User are able to see the filter setting before confirming or going back to the list of camps
     */
    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55   
        System.out.println("Filter Selection");
        
        System.out.printf("\t(%d) By available participants slot: ", ++option); 
        if (availableParticipant) System.out.println(PrintHelper.ANSI_GREEN + availableParticipant + PrintHelper.ANSI_RESET); 
        else System.out.println(PrintHelper.ANSI_RED + availableParticipant + PrintHelper.ANSI_RESET); 

        System.out.printf("\t(%d) By available camp committee slot: ", ++option, availableCommittee);
        if (availableCommittee) System.out.println(PrintHelper.ANSI_GREEN + availableCommittee + PrintHelper.ANSI_RESET); 
        else System.out.println(PrintHelper.ANSI_RED + availableCommittee + PrintHelper.ANSI_RESET); 

        System.out.printf("\t(%d) By faculty: ", ++option, byFaculty);
        if (byFaculty) System.out.println(PrintHelper.ANSI_GREEN + byFaculty + PrintHelper.ANSI_RESET); 
        else System.out.println(PrintHelper.ANSI_RED + byFaculty + PrintHelper.ANSI_RESET); 

        System.out.println("\t("+ ++option + ") By Location: " + PrintHelper.ANSI_YELLOW + byLocation + PrintHelper.ANSI_RESET); 
        System.out.println("\t("+ ++option + ") By Date: " + PrintHelper.ANSI_YELLOW + byDate + PrintHelper.ANSI_RESET);
   
        if (isStaff){
            System.out.printf("\t(%d) By visibility: ", ++option);
            if (byVisibility) System.out.println(PrintHelper.ANSI_GREEN + byVisibility + PrintHelper.ANSI_RESET); 
            else System.out.println(PrintHelper.ANSI_RED + byVisibility + PrintHelper.ANSI_RESET); 
        }

        System.out.printf("\t(%d) Reset Filter\n", ++option); 
        System.out.println("\t(0) Confirm filter selection"); 
        System.out.println("----------------------------------------------");
        System.out.println("(press any non-numeric key to go back to Camp List)"); 

        return option; 
    }

    /**
     * This method tells the CampList Controller the filters the user Set and Update the listOfCamps to be printed
     */
    private void filterCampToPrint(){
        // byDate is DD/MM/YYYY-DD/MM/YYYY
        String startDate, endDate; 
        if (!byDate.equals("")){
            int i = 0; 
            for (i=0; i<byDate.length();i++){
                if (byDate.charAt(i) == '-' ) break; 
            }
            startDate = byDate.substring(0,i); 
            endDate = byDate.substring(i+1);
        }
        else{
            startDate = "";
            endDate = "";
        }
        campListCont.FilterBy(userID, byLocation, availableParticipant, availableCommittee, startDate, endDate, byFaculty, byVisibility); 
    }

    /**
     * This method provides the UI for user to select a specific date range
     * 
     * @return the specified date 
     */
    private String setDateUI(){
        System.out.println("----------------------------------------------");
        System.out.println("Filter date by start date - end date"); 
        System.out.println("Start Date"); 
        String start = SelectionHelper.dateSelectUI(); 
        System.out.println("End Date"); 
        String end = SelectionHelper.dateSelectUI(); 

        String returnString = start + "-" + end;
    
        return returnString; 
    }

    /**
     * This method provides the UI for user to select a specific location 
     * 
     * @return the specified date 
     */
    private String setLocationUI(){
        System.out.println("----------------------------------------------");
        System.out.print("Enter location to filter: "); 
        Scanner sc = new Scanner(System.in); 
        String location = sc.nextLine();
        return location;
    }

    /**
     * This method set the filter to their default state for this user 
     */
    private void setDefaultFilter(){
        availableParticipant = !isStaff;
        availableCommittee = !isStaff; 
        byFaculty = false; 
        byVisibility = !isStaff;
        byDate = "";
        byLocation = ""; 
    }

    /** 
     * This method toggles the setting of the filters
     * 
     * @return the new setting of the filters 
     */
    private boolean toggleChoice(boolean choice){
        if (choice) return false; 
        else return true;
    }
}

// 