package scs3grp5.ui.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import scs3grp5.controller.EnquiryController;

public class UIEnquiryList extends UserInterface{

    /**
     * to communicate with EnquiryController
     */
    private EnquiryController enquiryCont; 

    /**
     * contains the listOfEnquiries to be shown on the UI
     */
    private ArrayList<String> listOfEnquiries; 


    public UIEnquiryList(UIInformation uiInfo) {
        super(uiInfo);
        enquiryCont = new EnquiryController();
    }

    @Override
    public IUserInterface showUI() {
        int option = -1;
        menu = new MenuEnquiryStudentList(); 
        if (uiInfo.getIsCommittee()) menu = new MenuEnquiryCommitteeList();
        optionSelector = new SelectionMenu();

        boolean wrongInput = false;
        do{
            try{
                ChangePage.changePage();
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                wrongInput = true; 
            }
        }while (wrongInput);

        if (option == 1) {
            listOfEnquiries = enquiryCont.getPendingEnquiryByCamp(uiInfo.getUserID(), uiInfo.getCampID());
        }
        else if (option == 2) {
            listOfEnquiries = enquiryCont.getRepliedEnquiryByCamp(uiInfo.getUserID(), uiInfo.getCampID());
        }
        else if (option == 3) {
            listOfEnquiries = enquiryCont.getAllEnquiryByCamp(uiInfo.getUserID(), uiInfo.getCampID());
        } 
        else if (option == 4) {
            if (uiInfo.getIsCommittee()){
                if (uiInfo.getIsStaff()) return new UISuperCamp(uiInfo); 
                return new UIPrivilegedCamp(uiInfo);
            } 
            else {
                uiInfo.setEnquiryID(createEnquiryUI()); // create an enquiry
                return new UIEnquiry(uiInfo);
            }
        }
        else if (option == 5){
            if (uiInfo.getIsCommittee()) return new UIHomepage(uiInfo);
            else {
                if (uiInfo.getIsCommittee()){
                    if (uiInfo.getIsStaff()) return new UISuperCamp(uiInfo); 
                    return new UIPrivilegedCamp(uiInfo);
                }
            } 
        }
        else if (option == 6) return new UIHomepage(uiInfo);
        else return null;

        wrongInput = false;
        int listOption = -1; 
        do{
            menu = new PrintEnquryList(listOfEnquiries);
            optionSelector = new SelectionFromList(); 

            try{
                ChangePage.changePage();
                listOption = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
            }
            catch(OptionException e){
                return new UIEnquiryList(uiInfo);  
            }
            
            if (listOption == -1) wrongInput = true; 
            else{
                uiInfo.setCampID(listOfEnquiries.get(listOption-1));  
                return new UIEnquiry(uiInfo);
            }
        }while (wrongInput);
    }

    private String createEnquiryUI(){
        // get enquiry text
        return enquiryCont.create(uiInfo.getUserID());
    }
    
}
