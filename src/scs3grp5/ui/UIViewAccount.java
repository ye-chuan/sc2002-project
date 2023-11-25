package ui;

/**
 * Provides a UI for the View Account
 */
public class UIViewAccount extends UserInterface{

    private boolean isStaff; 
    /** 
     * Constructor for ViewStaffAccountUI
     * 
     * @param uiInfo contains the information of the user 
     */
    public UIViewAccount(UIInformation uiInfo, boolean isStaff){
        super(uiInfo);
        this.isStaff = isStaff; 
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
            if (isStaff) printDetail = new PrintStaffDetail(uiInfo.getUserID());
            else printDetail = new PrintStudentDetail(uiInfo.getUserID());
            printDetail.printDetail();
        }
        else if (option == 2) return new UIChangePassword(uiInfo); 
        else if (option == 3) return new UIHomepage(uiInfo);  
        return null;
    }
}
