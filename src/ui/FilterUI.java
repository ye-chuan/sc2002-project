package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FilterUI {


    /**
     * to communicate with CampListController
     */
    protected CampListController campListCont;

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


    private boolean isStaff; 

    public FilterUI(boolean isStaff){
        this.isStaff = isStaff; 
        setDefaultFilter();
    }

    /**
     * This method prints the available filters for filtering the camp of list to show on the screen
     * 
     * @return the max number of options available 
     */
    private int printFilterSelection(){
        int option = 1; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55   
        System.out.println("Filter Selection");
        
        System.out.printf("\t(%d) By available participants slot: ", option++); 
        if (availableParticipant) System.out.println(StringConstants.ANSI_GREEN + availableParticipant + StringConstants.ANSI_RESET); 
        else System.out.println(StringConstants.ANSI_RED + availableParticipant + StringConstants.ANSI_RESET); 

        System.out.printf("\t(%d) By available camp committee slot: ", option++, availableCommittee);
        if (availableCommittee) System.out.println(StringConstants.ANSI_GREEN + availableCommittee + StringConstants.ANSI_RESET); 
        else System.out.println(StringConstants.ANSI_RED + availableCommittee + StringConstants.ANSI_RESET); 

        System.out.printf("\t(%d) By faculty: ", option++, byFaculty);
        if (byFaculty) System.out.println(StringConstants.ANSI_GREEN + byFaculty + StringConstants.ANSI_RESET); 
        else System.out.println(StringConstants.ANSI_RED + byFaculty + StringConstants.ANSI_RESET); 

        System.out.println("\t("+ option++ + ") By Location: " + StringConstants.ANSI_YELLOW + byLocation + StringConstants.ANSI_RESET); 
        System.out.println("\t("+ option++ + ") By Date: " + StringConstants.ANSI_YELLOW + byDate + StringConstants.ANSI_RESET);

        if (isStaff){
            System.out.printf("\t(%d) By visibility: ", option);
            if (byVisibility) System.out.println(StringConstants.ANSI_GREEN + byVisibility + StringConstants.ANSI_RESET); 
            else System.out.println(StringConstants.ANSI_RED + byVisibility + StringConstants.ANSI_RESET); 
        }
        

        System.out.println("(9) Reset Filter");
        System.out.println("(0) Confirm filter selection"); 
        System.out.println("(press any non-numeric key to go back)");
        System.out.println("----------------------------------------------"); 

        return option; 
    }


    /**
     * This method provides the UI for user to Set/Unset their filter during filter selection 
     */
    public boolean filterSelection(){

        Scanner sc = new Scanner(System.in); 
        int option; 
        boolean wrongInput = false; 

        do{ 
            ChangePage.changePage();
            int maxOption = printFilterSelection();
            if (wrongInput) System.out.println("Invalid Option!"); 
            System.out.print("Set/Unset Filter: ");
            try{
                option = sc.nextInt();
            }
            catch(InputMismatchException e){
                return false;
            }
            wrongInput = false; 
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
                    break; 
                case 6:
                    byVisibility = toggleChoice(byVisibility); 
                    break; 
                case 9: 
                    campListCont.setDefaultFilter(isStaff);
                    sc.close(); 
                    return true; 
                default: 
                    wrongInput = true; 
            }
        }while (option != 0); 

        sc.close();
        return true; 
    }


    /**
     * This method tells the CampList Controller the filters the user Set and Update the listOfCamps to be printed
     */
    private void filterCampToPrint(){
        CampListCont.filterBy(isStaff, byLocation, availableParticipant, availableCommittee, byDate, byFaculty, byVisibility); 
    }

    /**
     * This method provides the UI for user to select a specific date 
     * 
     * @return the specified date 
     */
    private String setDateUI(){

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
        availableParticipant = true;
        availableCommittee = true; 
        byFaculty = false; 
        byVisibility = true;
        byDate = "";
        byLocation = ""; 
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
