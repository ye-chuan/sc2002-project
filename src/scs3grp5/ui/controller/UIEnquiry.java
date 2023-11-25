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

public class UIEnquiry extends UserInterface {

    private EnquiryController enquiryCont; 

    public UIEnquiry(UIInformation uiInfo) {
        super(uiInfo);
        enquiryCont = new EnquiryController(); 
    }

    @Override
    public IUserInterface showUI() {

        IPrintDetail printDetail = new PrintEnquiryDetail(uiInfo.getEnquiryID());

        printDetail.printDetail();

        if (!enquiryCont.getStatus(uiInfo.getEnquiryID()) || !(enquiryCont.isOwner(uiInfo.getUserID(), uiInfo.enquiryID) || uiInfo.getIsCommittee())){
            menu = new MenuNoOption(); 
            optionSelector = new SelectionNull();
            try{
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
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                return new UIEnquiryList(uiInfo);
            }
        }while (wrongInput); 
        
        if (option == 1){
            if (uiInfo.getIsCommittee()) enquiryCont.reply(uiInfo.getUserID(), uiInfo.getEnquiryID(), replyEnquiryUI());
            else enquiryCont.edit(uiInfo.getEnquiryID(), editEnquiryUI()); 
        } 
        else if (option == 2){
            enquiryCont.delete(uiInfo.getEnquiryID());
        }
        return new UIEnquiry(uiInfo);
    }

    private String replyEnquiryUI() {
        return null;
    }

    private String editEnquiryUI() {
        return null;
    }
    
}