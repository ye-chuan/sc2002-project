package scs3grp5.ui.controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import scs3grp5.controller.CampListController;
import scs3grp5.ui.input.ISelectOption;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.IPrintMenu;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.PrintHelper;

import javax.swing.text.html.Option;

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

    private String byRegistrationDate;

    private boolean isStaff; 

    public UIFilter(boolean isStaff){
        this.isStaff = isStaff; 
        setDefaultFilter();
        campListCont = new CampListController(); 
    }

    @Override
    public IUserInterface showUI() {

        ISelectOption optionSelector = new SelectionMenu();
        
        do{
            ChangePage.changePage();
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
                    byRegistrationDate = setDateUI();
                    break; 
                case 7:
                    if (isStaff) byVisibility = toggleChoice(byVisibility); 
                    else wrongInput = true; 
                    break; 
                case 8: 
                    campListCont.setDefaultFilter(isStaff);
                    return null;
                default: 
                    wrongInput = true;
            }
        }while (true);

    }


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
        System.out.println("\t("+ ++option + ") By Registration Date: " + PrintHelper.ANSI_YELLOW + byRegistrationDate + PrintHelper.ANSI_RESET);
   
        if (isStaff){
            System.out.printf("\t(%d) By visibility: ", ++option);
            if (byVisibility) System.out.println(PrintHelper.ANSI_GREEN + byVisibility + PrintHelper.ANSI_RESET); 
            else System.out.println(PrintHelper.ANSI_RED + byVisibility + PrintHelper.ANSI_RESET); 
        }

        System.out.printf("\t(%d) Reset Filter: ", ++option); 
        System.out.println("(0) Confirm filter selection"); 
        System.out.println("----------------------------------------------");
        System.out.println("(press any non-numeric key to go back to Camp List)"); 

        return option; 
    }

    /**
     * This method tells the CampList Controller the filters the user Set and Update the listOfCamps to be printed
     */
    private void filterCampToPrint(){
        campListCont.filterBy(isStaff, byLocation, availableParticipant, availableCommittee, byDate, byRegistrationDate, byFaculty, byVisibility); 
    }

    /**
     * This method provides the UI for user to select a specific date 
     * 
     * @return the specified date 
     */
    private String setDateUI(){
        // start and end range 6 int
    }

    /**
     * This method provides the UI for user to select a specific location 
     * 
     * @return the specified date 
     */
    private String setLocationUI(){
        System.out.print("Enter location to filter: "); 
        Scanner sc = new Scanner(System.in); 
        String location = sc.next(); 
        sc.close(); 
        return location;
    }

    /**
     * This method set the filter to their default state for this user 
     */
    private void setDefaultFilter(){
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
    private boolean toggleChoice(boolean choice){
        if (choice) return false; 
        else return true;
    }
}

// 