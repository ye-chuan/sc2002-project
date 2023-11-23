package ui;

/**
 * Provide a UI for the view account
 */
public class HomePageUI extends UserInterface{

    /** 
     * Constructor for HomePageUI
     * 
     * @param uiInfo contains the information of the user 
     */
    public HomePageUI(UIInformation uiInfo){
        super(uiInfo); 
    }

    
    @Override
    public void showUI() {
        int option = getUserChoiceUI(); 
        if (option == 1) uiInfo.setUIPage(UiPage.VIEWACCOUNT);
        else if (option == 2) uiInfo.setUIPage(UiPage.CAMPLIST);
        else uiInfo.setUIPage(UiPage.ENDPROGRAM);; 

        return; 
    }

    @Override
    protected int printListOfOption() {
        int option = 1; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55  
        System.out.printf("HOMEPAGE MENU\n"); 
        System.out.printf("\t(%d) View Account\n", option++); 
        System.out.printf("\t(%d) View Camps\n", option); 
        System.out.printf("\t(0) Exit Program\n"); 
        System.out.println("-------------------------------------------------------"); 
        return option; 
    }
    
}
