import java.util.Scanner;  // Import the Scanner class

/**
 * Provides a UI for the View Account
 */
public class ViewStaffAccountUI extends UserInterface{

    /**
     * to communicate with User Controller 
     */
    protected UserController userCont; 

    /** 
     * Constructor for ViewStaffAccountUI
     * 
     * @param uiInfo contains the information of the user 
     */
    public ViewStaffAccountUI(UIInformation uiInfo){
        super(uiInfo);
        userCont = new UserController(); 
    }

    @Override
    public void showUI() {
        int option = getUserChoiceUI(); 
        if (option == 1) printAccountDetail();
        else if (option == 2) uiInfo.setUIPage(UIPAGE.PASSWORD); 
        else if (option == 3) uiInfo.setUIPage(UIPAGE.HOMEPAGE);  
        else uiInfo.setUIPage(UIPAGE.ENDPROGRAM);

        return; 
    }

    @Override
    protected int printListOfOption() {
        int option = 1; 
        System.out.println("----------------------------------------------"); 
        System.out.println("ACCOUNT MENU"); 
        System.out.printf("(%d) View Account Detail\n", option++); 
        System.out.printf("(%d) Change Password\n", option++);
        System.out.printf("(%d) Go to HomePage\n", option); 
        System.out.printf("(0) Exit Program\n"); 
        System.out.println("----------------------------------------------"); 
        return option; 
    }
    
    /**
     * This method prints the User's Account detail 
     */
    protected void printAccountDetail(){
        System.out.println("----------------------------------------------"); 
        System.out.println("Account Detail"); 
        System.out.println("userID: " + userCont.getName(uiInfo.getUserID()).toUpperCase()); 
        System.out.println("Faculty: " + userCont.getFaculty(uiInfo.getUserID()).toUpperCase()); 
        System.out.println("Email: " + userCont.getEmail(uiInfo.getUserID())); 
        System.out.println("----------------------------------------------"); 
        System.out.println("(press any key to go back to Account Menu)"); 

        Scanner sc = new Scanner(System.in); 
        sc.next(); 
        sc.close(); 
    }

}
