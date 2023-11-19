/**
 * Provide a UI for the view accoutn 
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
        if (option == 1) uiInfo.setUIPage(UIPAGE.VIEWACCOUNT);
        else if (option == 2) uiInfo.setUIPage(UIPAGE.CAMPLIST);
        else uiInfo.setUIPage(UIPAGE.ENDPROGRAM);; 

        return; 
    }

    @Override
    protected int printListOfOption() {
        int option = 1; 
        System.out.println("----------------------------------------------"); 
        System.out.println("HOMEPAGE MENU");
        System.out.printf("(%d) View Account\n", option++); 
        System.out.printf("(%d) View Camps\n", option); 
        System.out.printf("(0) Exit Program\n"); 
        System.out.println("----------------------------------------------"); 
        return option; 
    }
    
}
