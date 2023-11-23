package ui;

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
        else if (option == 2) uiInfo.setUIPage(UiPage.PASSWORD); 
        else if (option == 3) uiInfo.setUIPage(UiPage.HOMEPAGE);  
        else uiInfo.setUIPage(UiPage.ENDPROGRAM);

        return; 
    }

    @Override
    protected int printListOfOption() {
        int option = 1; 
        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55   
        System.out.println("ACCOUNT MENU"); 
        System.out.printf("\t(%d) View Account Detail\n", option++); 
        System.out.printf("\t(%d) Change Password\n", option++);
        System.out.printf("\t(%d) Go to HomePage\n", option); 
        System.out.printf("\t(0) Exit Program\n"); 
        System.out.println("-------------------------------------------------------");
        return option; 
    }
    
    /**
     * This method prints the User's Account detail 
     */
    protected void printAccountDetail(){

        String name = userCont.getName(uiInfo.getUserID()).toUpperCase(); 
        String faculty = userCont.getFaculty(uiInfo.getUserID()).toUpperCase(); 
        String email = userCont.getEmail(uiInfo.getUserID());

        System.out.println("ACCOUNT DETAIL"); 
        System.out.println("┌───────────────────┬───────────────────────────────────────────┐"); // 65 
          System.out.println("│ Name              │"+ fillUpSpace(name, 43, 1, false) + "│");
        System.out.println("├───────────────────┼───────────────────────────────────────────┤");
        System.out.println("│ Faculty           │"+ fillUpSpace(faculty, 43, 1,false) + "│");
        System.out.println("├───────────────────┼───────────────────────────────────────────┤");
          System.out.println("│ Email             │"+ fillUpSpace(email, 43, 1, false) + "│");
        System.out.println("└───────────────────┴───────────────────────────────────────────┘");
        System.out.println("(press any key to go back to Account Menu)"); 

        Scanner sc = new Scanner(System.in); 
        sc.next(); 
        sc.close(); 
    }
    //43
    

}
