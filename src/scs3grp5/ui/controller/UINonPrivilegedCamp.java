package scs3grp5.ui.controller;

import scs3grp5.controller.CampController;
import scs3grp5.entity.CampRole;
import scs3grp5.ui.boundary.IPrintDetail;
import scs3grp5.ui.boundary.PrintStudentCampDetail;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.MenuCampStudent;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;

public class UINonPrivilegedCamp extends UserInterface{

    /**
     * to communicate with CampController
     */
    private CampController campCont; 

    /** 
     * Constructor for NonPrivilegedCampView class 
     * 
     * @param uiInfo contains the information of the user  
     */
    public UINonPrivilegedCamp(UIInformation uiInfo) {
        super(uiInfo);
        campCont = new CampController(); 
    }

    @Override
    public IUserInterface showUI() {

        boolean isParticipant; 
        boolean wrongInput; 
        int option = -1; 
        
        if (campCont.getUserStatus(uiInfo.getUserID(), uiInfo.getCampID()) == null) isParticipant = false;
        else isParticipant = true; 
        
        menu = new MenuCampStudent(isParticipant); 
        optionSelector = new SelectionMenu();
        IPrintDetail printDetail = new PrintStudentCampDetail(uiInfo.getCampID(), isParticipant);

        do{
            try{
                ChangePage.changePage();
                printDetail.printDetail();
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                wrongInput = true; 
            }
        }while (wrongInput);

        if (isParticipant) option -= 1; 
        else option -= 2; 

        if (option == -1) campCont.registerAsCommittee(uiInfo.getUserID(), uiInfo.getCampID()); 
        else if (option == 0){
            if (isParticipant) campCont.withdraw(uiInfo.getUserID(), uiInfo.getCampID());
            else campCont.registerAsParticipant(uiInfo.getUserID(), uiInfo.getCampID()); 
        }
        else if (option == 1) return new UIEnquiryList(uiInfo);
        else if (option == 2) return new UICampList(uiInfo);
        else if (option == 3) return new UIHomepage(uiInfo); 
        return null;
    }
    
}
