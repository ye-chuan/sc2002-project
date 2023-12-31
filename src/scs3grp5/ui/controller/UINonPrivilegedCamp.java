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


/**
 * This class is a UI controller for the Non Privileged Camp UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
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

    /**
     * This method controls the logic of the NonPrivilegedCamp UI, where it will show and handle the user input 
     * to navigate them to the right UI or action 
     * 
     * @return the next UI page to run
     */
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
            
            if(option == 0) return null; 

            if (isParticipant) option -= 1; 
            else option -= 2; 

            errorRegistration = false; 
            if (option == -1) {
                try{
                    campCont.registerAsCommittee(uiInfo.getUserID(), uiInfo.getCampID());
                    uiInfo.setIsCommittee(true);
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

        return null; 
    }
}
