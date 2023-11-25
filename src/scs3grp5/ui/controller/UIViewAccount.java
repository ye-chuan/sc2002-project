package scs3grp5.ui.controller;

/**
 * Provides a UI for the View Account
 */
public class UIViewAccount extends UserInterface{


    /** 
     * Constructor for ViewStaffAccountUI
     * 
     * @param uiInfo contains the information of the user 
     */
    public UIViewAccount(UIInformation uiInfo){
        super(uiInfo);
    }

    @Override
    public IUserInterface showUI() {

        int option = -1;
        menu = new MenuViewAccount();
        optionSelector = new SelectionMenu();
        boolean wrongInput = false;

        do{
            try{
                ChangePage.changePage();
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
            printDetail.printDetail();
            menu = new MenuNoOption(); 
            optionSelector = new SelectionNull();
            try{
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
