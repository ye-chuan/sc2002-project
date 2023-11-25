package ui;

import java.util.Scanner;

public class UIEnquiry extends UserInterface {

    private EnquiryController enquiryCont; 

    public UIEnquiry(UIInformation uiInfo) {
        super(uiInfo);
        enquiryCont = new EnquiryController(); 
    }

    @Override
    public IUserInterface showUI() {

        IPrintDetail printDetail = new PrintEnquiryDetail(uiInfo.getEnquiryID());

        if (!enquiryCont.getStatus(uiInfo.getEnquiryID()) || !enquiryCont.isOwner(uiInfo.getUserID(), uiInfo.enquiryID) || !uiInfo.getIsCommittee()){
            System.out.println("Press any key to go back to Enquiry List Menu");
            Scanner sc = new Scanner(System.in); 
            sc.next(); 
            return new UIEnquiryList(uiInfo);
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
