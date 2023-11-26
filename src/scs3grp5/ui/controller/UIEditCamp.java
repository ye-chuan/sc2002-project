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

    private String campID; 

    /** 
     * Constructor for CreateEditCampUI class 
     * 
     * @param uiInfo contains the information of the user  
     */
    public UIEditCamp(UIInformation uiInfo) {
        super(uiInfo);
        campCont = new CampController();
        campID = uiInfo.getCampID();
    }
    
    /**
     * This method controls the logic for the UI to edit camp UI
     * 
     * @return the new UI page to run
     */
    @Override
    public IUserInterface showUI() {
        
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
                    option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                    wrongInput = false;
                }
                catch(OptionException e){
                    wrongInput = true; 
                }
            }while (wrongInput);  

            switch (option){
                case 0: 
                    return new UISuperCamp(uiInfo); 
                case 1: 
                    while (!changeNameUI()); 
                    break;
                case 2: 
                    while (!changeStartDateUI()); 
                    break; 
                case 3: 
                    while (!changeEndDateUI());
                    break; 
                case 4: 
                    while (!changeRegistrationClosingDateUI());
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

    private void changeVisibilityUI(){
        try{
            campCont.toggleVisibility(campID, !campCont.getVisibility(campID));
        }catch (EditCampException e){
            System.out.println(e.getMessage());
        }
    }
}