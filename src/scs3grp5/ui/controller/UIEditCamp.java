package scs3grp5.ui.controller;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import scs3grp5.controller.CampController;
import scs3grp5.controller.EditCampException;
import scs3grp5.controller.InvalidDateException;
import scs3grp5.ui.boundary.IPrintDetail;
import scs3grp5.ui.boundary.PrintStaffCampDetail;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.MenuEditCamp;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.PrintHelper;
import scs3grp5.ui.ulti.SelectionHelper;


/**
 * This class is a UI controller for the edit camp UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UIEditCamp extends UserInterface{


    /**
     * to communicate with CampController
     */
    private CampController campCont;

    /** 
     * campID of the camp the user using the UI
     */
    private String campID; 

    /**
     * justCreate is a boolean variable, where if its a newly create camp it will be true 
     * else it will be false
     */
    private boolean justCreate; 

    /** 
     * Constructor for CreateEditCampUI class 
     * 
     * @param uiInfo contains the information of the user  
     */
    public UIEditCamp(UIInformation uiInfo, boolean justCreate) {
        super(uiInfo);
        campCont = new CampController();
        campID = uiInfo.getCampID();
        this.justCreate = justCreate;
    }
    
    /**
     * This method controls the logic for the UI to edit camp UI
     * During the creation of camp, we force staff IC to finish creating a camp first before 
     * they can exit the page. 
     * 
     * @return the new UI page to run
     */
    @Override
    public IUserInterface showUI() {

        boolean date = false;
        boolean close = false;
        boolean faculty = false; 

        String errorMessage = ""; 
        boolean error = false; 
        
        IPrintDetail printDetail = new PrintStaffCampDetail(campID);
        menu = new MenuEditCamp(); 
        optionSelector = new SelectionMenu();
        
        int option = -1; 
        boolean wrongInput = false; 
        do{
            do{
                try{
                    ChangePage.changePage();
                    System.out.println(PrintHelper.LOGO_STRING);
                    System.out.println();
                    printDetail.printDetail();
                    if (error) System.out.println(errorMessage);
                    error = false; 
                    option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                    wrongInput = false;
                }
                catch(OptionException e){
                    wrongInput = true; 
                }
            }while (wrongInput);  

            switch (option){
                case 0:
                    if (justCreate){
                        if (date && close && faculty){
                            return new UISuperCamp(uiInfo);  
                        }
                        else{
                            errorMessage = "Please initialise all UNSET camp information";
                            error = true; 
                            option = -1; 
                            break; 
                        }
                    }
                    else{
                        return new UISuperCamp(uiInfo); 
                    }
                    
                case 1: 
                    while (!changeNameUI());
                    break;
                case 2: 
                    while (!changeStartDateUI()); 
                    date = true; 
                    break; 
                case 3: 
                    while (!changeEndDateUI());
                    date = true; 
                    break; 
                case 4: 
                    while (!changeRegistrationClosingDateUI());
                    close = true; 
                    break;
                case 5: 
                    while (!changeLocationUI());
                    break; 
                case 6: 
                    while (!changeNumOfParticipantsUI());
                    break; 
                case 7:
                    while (!changeNumOfCampCommUI());
                    break; 
                case 8:
                    while (!changeFacultyUI());
                    faculty = true;  
                    break; 
                case 9:
                    changeVisibilityUI();
                    break; 
                case 10: 
                    while (!changeDescriptionUI()); 
                    break; 
                default: 
                    option = -1; 
            }
        }while (option != 0);

        return new UISuperCamp(uiInfo); 
    }
  
    /**
     * This method provides the UI to change name
     * We call the CampController to change the name of the camp immediately 
     * This method catch EditCampException when false with exception message 
     * 
     * @return true if the camp is successful, else false 
     */
    private boolean changeNameUI(){
        System.out.print("Camp Name: "); 
        Scanner sc = new Scanner(System.in); 
        String name; 
        try{
            name = sc.nextLine(); 
        }catch(NoSuchElementException e){
            return false;
        }
        try{
            campCont.changeName(campID, name); 
        }catch (EditCampException e){
            System.out.println(e.getMessage());
            return false; 
        }
        return true; 
    }
    
    /**
     * /**
     * This method provides the UI to change start date
     * We call the CampController to change the date of the camp immediately 
     * This method catch EditCampException when false with exception message 
     * 
     * @return true if the camp is successful, else false 
     */
    private boolean changeStartDateUI(){
        System.out.println("-------------------------------------------------------");
        System.out.println("Camp Start Date: "); 
        String date = SelectionHelper.dateSelectUI();
        try{
            campCont.changeStartDate(campID, date); 
        }catch (InvalidDateException e){
            System.out.println(e.getMessage());
            return false; 
        }
        return true; 
    }

    /**
     * This method provides the UI to change end date
     * We call the CampController to change the date end of the camp immediately 
     * This method catch EditCampException when false with exception message 
     * 
     * @return true if the camp is successful, else false 
     */
    private boolean changeEndDateUI(){
        System.out.println("-------------------------------------------------------");
        System.out.println("Camp End Date:"); 
        String date = SelectionHelper.dateSelectUI();
        
        try{
            campCont.changeEndDate(campID, date); 
        }catch (InvalidDateException e){
            System.out.println(e.getMessage());
            return false; 
        }
        return true; 
    }

    /**
     * This method provides the UI to change closing date
     * We call the CampController to change the closing date of the camp immediately
     * This method catch EditCampException when false with exception message  
     * 
     * @return true if the camp is successful, else false 
     */
    private boolean changeRegistrationClosingDateUI(){
        System.out.println("-------------------------------------------------------");
        System.out.println("Camp Registration Closing Date:"); 
        String date = SelectionHelper.dateSelectUI();
        
        try{
            campCont.changeClosingDate(campID, date);
        }catch (InvalidDateException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * This method provides the UI to change location
     * We call the CampController to change the location of the camp immediately 
     * This method catch EditCampException when false with exception message 
     * 
     * @return true if the camp is successful, else false 
     */
    private boolean changeLocationUI(){
        System.out.println("-------------------------------------------------------");
        System.out.print("Camp Location: "); 
        Scanner sc = new Scanner(System.in);
        String location;  
        try{
             location = sc.nextLine(); 
        }catch( NoSuchElementException e){
            return false;
        }
        try{
            campCont.changeLocation(campID, location);
        }catch (EditCampException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true; 
    }

    /**
     * This method provides the UI to change number of participants slot 
     * We call the CampController to change the number of participants slots of the camp immediately
     * This method catch EditCampException when false with exception message 
     * 
     * @return true if the camp is successful, else false 
     */
    private boolean changeNumOfParticipantsUI(){
        System.out.println("-------------------------------------------------------");
        System.out.print("Number of Participants: "); 

        int intInput;
        Scanner sc = new Scanner(System.in); 
        try{
            intInput = sc.nextInt();
        }catch (InputMismatchException e){
            return false; 
        }
        try{
            campCont.changeCampParticipantSlots(campID, intInput); 
        }catch (EditCampException e){
            System.out.println(e.getMessage());
            return false;
        }

        return true; 
         
        
    }

    /**
     * This method provides the UI to change number of camp committee slot 
     * We call the CampController to change the number of camp committee slots of the camp immediately
     * This method catch EditCampException when false with exception message 
     * 
     * @return true if the camp is successful, else false 
     */
    private boolean changeNumOfCampCommUI(){
        System.out.println("-------------------------------------------------------");
        System.out.print("Number of Committee: "); 
        int intInput;
        Scanner sc = new Scanner(System.in); 
        try{
            intInput = sc.nextInt();
        }catch (InputMismatchException e){
            return false; 
        }
        try{
            campCont.changeCampCommSlots(campID, intInput);
        }catch (EditCampException e){
            System.out.println(e.getMessage());
            return false; 
        }

        return true; 
        
    }

    /**
     * This method provides the UI to change faculty
     * We call the CampController to change the faculty of the camp immediately
     * This method catch EditCampException when false with exception message 
     * 
     * @return true if the camp is successful, else false 
     */
    private boolean changeFacultyUI(){
        System.out.println("-------------------------------------------------------");
        System.out.println("Camp Target Audience");
        System.out.println("\t(1) Open to whole NTU"); 
        System.out.println("\t(2) Open only to faculty"); 
        System.out.print("option: "); 

        int intInput; 
        Scanner sc = new Scanner(System.in);
        try{
            intInput = sc.nextInt();
        }catch (InputMismatchException e){
            return false; 
        }
        try{
            if (intInput == 1) campCont.changeFaculty(uiInfo.getUserID(), campID, false); 
            else campCont.changeFaculty(uiInfo.getUserID(), campID, true);
        }catch(EditCampException e){
            System.out.println(e.getMessage());
            return false;
        }
        
        return true; 
    }

    /**
     * This method provides the UI to change the description  
     * We call the CampController to change the description of the camp immediately
     * This method catch EditCampException when false with exception message 
     * 
     * @return true if the camp is successful, else false 
     */
    private boolean changeDescriptionUI(){
        System.out.println("-------------------------------------------------------");
        System.out.print("Camp Description: "); 

        Scanner sc = new Scanner(System.in); 
        String description = "";
        try{
             description = sc.nextLine(); 
        }catch( NoSuchElementException e){
            System.out.println(e.getMessage());
            return false;
        }
        try{
            campCont.changeDescription(campID, description);
        }catch (EditCampException e){
            System.out.println(e.getMessage());
            return false; 
        }
        return true; 
    }

    /**
     * This method provides the UI to change the visibility  
     * We call the CampController to change the visibility of the camp immediately
     * This method catch EditCampException when false with exception message 
     * 
     */
    private void changeVisibilityUI(){
        try{
            campCont.toggleVisibility(campID, !campCont.getVisibility(campID));
        }catch (EditCampException e){
            System.out.println(e.getMessage());
        }
    }
}