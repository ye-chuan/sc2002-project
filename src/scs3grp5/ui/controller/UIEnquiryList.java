package scs3grp5.ui.controller;

import java.util.List;
import java.util.Scanner;
import scs3grp5.controller.EnquiryController;
import scs3grp5.ui.input.SelectionFromList;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.ListEnquiry;
import scs3grp5.ui.menu.MenuEnquiryCommitteeList;
import scs3grp5.ui.menu.MenuEnquiryStudentList;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.PrintHelper;

public class UIEnquiryList extends UserInterface{

    /**
     * to communicate with EnquiryController
     */
    private EnquiryController enquiryCont; 

    /**
     * contains the listOfEnquiries to be shown on the UI
     */
    private List<String> listOfEnquiries; 


    public UIEnquiryList(UIInformation uiInfo) {
        super(uiInfo);
        enquiryCont = new EnquiryController(uiInfo.getCampID());
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
                System.out.println(PrintHelper.LOGO_STRING);
                System.out.println();
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                wrongInput = true; 
            }
        }while (wrongInput);

        if (option == 1) {
            listOfEnquiries = enquiryCont.getPendingEnquiries(uiInfo.getUserID());
        }
        else if (option == 2) {
            listOfEnquiries = enquiryCont.getResolvedEnquiries(uiInfo.getUserID());
        }
        else if (option == 3) {
            listOfEnquiries = enquiryCont.getAllEnquiries(uiInfo.getUserID());
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
            else return new UINonPrivilegedCamp(uiInfo);
        }
        else if (option == 6) return new UIHomepage(uiInfo);
        else return null;

        wrongInput = false;
        int listOption = -1; 
        do{
            menu = new ListEnquiry(listOfEnquiries, uiInfo.getCampID());
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
        return null; 
    }

    private String createEnquiryUI(){
        System.out.print("Enter Enquiry: "); 
        Scanner sc = new Scanner(System.in); 
        String enquiry = sc.nextLine();
        return enquiryCont.create(uiInfo.getUserID(), enquiry);
    }
    
}
