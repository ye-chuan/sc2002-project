package scs3grp5.ui.controller;

import scs3grp5.ui.boundary.IPrintDetail;
import scs3grp5.ui.boundary.PrintStaffDetail;
import scs3grp5.ui.boundary.PrintStudentDetail;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.input.SelectionNull;
import scs3grp5.ui.menu.MenuNoOption;
import scs3grp5.ui.menu.MenuViewAccount;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.PrintHelper;

/**
 * This class is a UI controller for the view Account UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UIViewAccount extends UserInterface{


    /** 
     * Constructor for ViewStaffAccountUI
     * 
     * @param uiInfo contains the information of the UI 
     */
    public UIViewAccount(UIInformation uiInfo){
        super(uiInfo);
    }

    /**
     * This method controls the logic of the View Account UI, where it will show and handle the user input 
     * to navigate them to the right UI or action 
     * 
     * @return the next UI page to run
     */
    @Override
    public IUserInterface showUI() {

        int option = -1;
        menu = new MenuViewAccount();
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

        if (option == 1){
            IPrintDetail printDetail; 
            if (uiInfo.getIsStaff()) printDetail = new PrintStaffDetail(uiInfo.getUserID());
            else printDetail = new PrintStudentDetail(uiInfo.getUserID());
            
            menu = new MenuNoOption(); 
            optionSelector = new SelectionNull();
            try{
                printDetail.printDetail();
                optionSelector.getUserChoiceUI(menu.printMenu(), true);
            }
            catch (OptionException e){
                return new UIViewAccount(uiInfo);
            }   
        }
        else if (option == 2) return new UIChangePassword(uiInfo); 
        else if (option == 3) return new UIHomepage(uiInfo);  
        return null;
    }
}
