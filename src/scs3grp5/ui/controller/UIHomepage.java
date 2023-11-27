package scs3grp5.ui.controller;

import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.MenuHomepage;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.PrintHelper;

/**
 * This class is a UI controller for the Homepage UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UIHomepage extends UserInterface{

    /** 
     * Constructor for HomePageUI
     * 
     * @param uiInfo contains the information of the UI 
     */
    public UIHomepage(UIInformation uiInfo){
        super(uiInfo); 
    }

    /**
     * This method controls the logic of the Homepage UI, where it will show and handle the user input 
     * to navigate them to the right UI or action 
     * 
     * @return the next UI page to run
     */
    @Override
    public IUserInterface showUI() {

        int option = -1;
        menu = new MenuHomepage();
        optionSelector = new SelectionMenu();
        boolean wrongInput = false;

        do{
            try{
                ChangePage.changePage();
                System.out.println(PrintHelper.LOGO_STRING);
                System.out.println();
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
