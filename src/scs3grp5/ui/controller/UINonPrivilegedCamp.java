package scs3grp5.ui.controller;

import scs3grp5.controller.CampController;
import scs3grp5.controller.RegistrationException;
import scs3grp5.ui.boundary.IPrintDetail;
import scs3grp5.ui.boundary.PrintStudentCampDetail;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.MenuCampStudent;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.PrintHelper;

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
        boolean wrongInput = false; 
        int option = -1; 
        
        String errorMessage = ""; 
        boolean errorRegistration = false; 
        do{
            if (campCont.getUserStatus(uiInfo.getCampID(), uiInfo.getUserID()) == null) isParticipant = false;
            else isParticipant = true; 
    
            menu = new MenuCampStudent(isParticipant); 
            optionSelector = new SelectionMenu();
            IPrintDetail printDetail = new PrintStudentCampDetail(uiInfo.getCampID(), isParticipant);
            
            try{ 
                ChangePage.changePage();
                System.out.println(PrintHelper.LOGO_STRING);
                System.out.println();
                printDetail.printDetail();
                if (errorRegistration) System.out.println(errorMessage.toUpperCase());
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                wrongInput = true; 
            }

            if (isParticipant) option -= 1; 
            else option -= 2; 

            errorRegistration = false; 
            if (option == -1) {
                try{
                    campCont.registerAsCommittee(uiInfo.getUserID(), uiInfo.getCampID());
                    return new UIPrivilegedCamp(uiInfo);
                }catch(RegistrationException e){
                    errorMessage = e.getMessage(); 
                }
                errorRegistration = true; 
            }
            else if (option == 0){
                if (isParticipant){
                    campCont.withdraw(uiInfo.getUserID(), uiInfo.getCampID());
                    errorMessage = "Withdraw as Participant successfully!";
                }
                else {
                    try{
                        campCont.registerAsParticipant(uiInfo.getUserID(), uiInfo.getCampID());
                        errorMessage = "Registrated as Participant successfully!";
                    }catch(RegistrationException e){
                        errorMessage = e.getMessage(); 
                    } 
                }
                errorRegistration = true;
            }
        }while (wrongInput || errorRegistration);

        if (option == 1) return new UIEnquiryList(uiInfo);
        else if (option == 2) return new UICampList(uiInfo);
        else if (option == 3) return new UIHomepage(uiInfo); 
        else return null;
    }
}
