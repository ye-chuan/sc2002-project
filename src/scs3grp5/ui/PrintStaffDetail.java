package scs3grp5.ui;

import scs3grp5.controller.UserController;

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
     * Constructor for PrintStaffUI
     * 
     * @param userID contains the userID for the user 
     */
    public PrintStaffDetail(String userID){
        this.userID = userID;
        userCont = new UserController(); 
    }
    
    @Override
    public void printDetail(){

        String name = userCont.getName(userID).toUpperCase(); 
        String faculty = userCont.getFaculty(userID).toUpperCase(); 
        String email = userCont.getEmail(userID);

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
