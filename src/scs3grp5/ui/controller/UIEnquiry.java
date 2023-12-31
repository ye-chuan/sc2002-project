package scs3grp5.ui.controller;

import java.util.Scanner;
import scs3grp5.controller.EnquiryController;
import scs3grp5.ui.boundary.IPrintDetail;
import scs3grp5.ui.boundary.PrintEnquiryDetail;
import scs3grp5.ui.input.SelectionFromList;
import scs3grp5.ui.input.SelectionNull;
import scs3grp5.ui.menu.MenuEquiryEdit;
import scs3grp5.ui.menu.MenuEquiryReply;
import scs3grp5.ui.menu.MenuNoOption;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;


/**
 * This class is a UI controller for the enquiry UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UIEnquiry extends UserInterface {

    /**
     * To communicate with the Enquiry Controller
     */
    private EnquiryController enquiryCont; 

    /**
     * Constructor class for UIEnquiry
     * 
     * @param uiInfo contains the information of the UI 
     */
    public UIEnquiry(UIInformation uiInfo) {
        super(uiInfo);
        enquiryCont = new EnquiryController(uiInfo.getCampID()); 
    }
    
    /**
     * This method controls the logic of the Enquiry UI, where it will show and handle the user input 
     * to navigate them to the right UI or action 
     * 
     * @return the next UI page to run
     */
    @Override
    public IUserInterface showUI() {

        IPrintDetail printDetail = new PrintEnquiryDetail(uiInfo.getCampID(), uiInfo.getEnquiryID());

        printDetail.printDetail();

        if ((enquiryCont.getStatus(uiInfo.getEnquiryID())) || !(enquiryCont.isOwner(uiInfo.getUserID(), uiInfo.getEnquiryID()) || uiInfo.getIsCommittee())){
            menu = new MenuNoOption();  
            optionSelector = new SelectionNull();
            try{
                printDetail.printDetail();
                optionSelector.getUserChoiceUI(menu.printMenu(), true);
            }
            catch (OptionException e){
                return new UIEnquiryList(uiInfo);
            }
        }
        
        int option = -1;
        menu = new MenuEquiryEdit();
        if (uiInfo.getIsCommittee()) menu = new MenuEquiryReply();
        optionSelector = new SelectionFromList();
        boolean wrongInput = false;

        do{
            try{
                ChangePage.changePage();
                printDetail.printDetail();
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                return new UIEnquiryList(uiInfo);
            }
            if (option == -1) wrongInput = true; 
            else if (option == 0) wrongInput = true; 

        }while (wrongInput); 
        
        
        if (option == 1){
            if (uiInfo.getIsCommittee()) enquiryCont.reply(uiInfo.getUserID(), uiInfo.getEnquiryID(), replyEnquiryUI());
            else enquiryCont.edit(uiInfo.getEnquiryID(), editEnquiryUI()); 
        } 
        else if (option == 2){
            enquiryCont.delete(uiInfo.getEnquiryID());
            return new UIEnquiryList(uiInfo);
        }
        return new UIEnquiry(uiInfo);
    }

    /**
     * This method provides the UI for user to reply to an enquiry
     * 
     * @return the replied enquiry
     */
    private String replyEnquiryUI() {
        System.out.print("Enter Reply: "); 
        Scanner sc = new Scanner(System.in); 
        String enquiry = sc.nextLine();
        return enquiry;
    }

    /**
     * This method provides the UI for user to edit to their own enquiry
     * 
     * @return their edited enquiry
     */
    private String editEnquiryUI() {
        System.out.print("Enter Enquiry: "); 
        Scanner sc = new Scanner(System.in); 
        String enquiry = sc.nextLine();
        return enquiry;
    }
    
}
