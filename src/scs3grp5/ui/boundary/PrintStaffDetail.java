package scs3grp5.ui.boundary;

import scs3grp5.controller.UserController;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.PrintHelper;

/**
 * This class prints the user detail for a staff  
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class PrintStaffDetail implements IPrintDetail{

    /**
     * to communicate with User Controller 
     */
    protected UserController userCont; 

    /** 
     * userID of the user using the UI
     */
    protected String userID;

    /** 
     * Constructor for PrintStaffDetail class 
     * 
     * @param userID contains the userID for the user 
     */
    public PrintStaffDetail(String userID){
        this.userID = userID;
        userCont = new UserController(); 
    }
    
    @Override
    public void printDetail(){

        ChangePage.changePage();
        System.out.println(PrintHelper.LOGO_STRING);
        System.out.println();

        String name = (userCont.getName(userID)).toUpperCase(); 
        String faculty = (userCont.getFaculty(userID)).toUpperCase(); 
        String email = userCont.getUserName(userID)+ "@ntu.edu.sg";

        System.out.println("ACCOUNT DETAIL"); 
        System.out.println("┌───────────────────┬───────────────────────────────────────────┐"); // 65 
          System.out.println("│ Name              │"+ PrintHelper.fillUpSpace(name, 43, 1, false) + "│");
        System.out.println("├───────────────────┼───────────────────────────────────────────┤");
        System.out.println("│ Faculty           │"+ PrintHelper.fillUpSpace(faculty, 43, 1,false) + "│");
        System.out.println("├───────────────────┼───────────────────────────────────────────┤");
          System.out.println("│ Email             │"+ PrintHelper.fillUpSpace(email, 43, 1, false) + "│");
        System.out.println("└───────────────────┴───────────────────────────────────────────┘"); 
    }
    
}
