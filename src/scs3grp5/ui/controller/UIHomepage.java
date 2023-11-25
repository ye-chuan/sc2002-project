package scs3grp5.ui.controller;

import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.MenuHomepage;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;

/**
 * Provide a UI for the view account
 */
public class UIHomepage extends UserInterface{

    /** 
     * Constructor for HomePageUI
     * 
     * @param uiInfo contains the information of the user 
     */
    public UIHomepage(UIInformation uiInfo){
        super(uiInfo); 
    }

    
    @Override
    public IUserInterface showUI() {

        int option = -1;
        menu = new MenuHomepage();
        optionSelector = new SelectionMenu();
        boolean wrongInput = false;

        do{
            try{
                ChangePage.changePage();
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
                System.out.println(option);
            }
            catch(OptionException e){
                wrongInput = true; 
            }
        }while (wrongInput); 
        if (option == 1) return new UIViewAccount(uiInfo);
        else if (option == 2) return new UICampList(uiInfo);
        return null; 
    }
}
