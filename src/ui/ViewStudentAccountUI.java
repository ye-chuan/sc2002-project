package ui;

import java.util.Scanner;

public class ViewStudentAccountUI extends ViewStaffAccountUI{

    /** 
     * Constructor for ViewStudentAccountUI
     * 
     * @param uiInfo contains the information of the user 
     */
    public ViewStudentAccountUI(UIInformation uiInfo) {
        super(uiInfo);
    }

    @Override
    protected void printAccountDetail(){

        String name = userCont.getName(uiInfo.getUserID()).toUpperCase(); 
        String faculty = userCont.getFaculty(uiInfo.getUserID()).toUpperCase(); 
        String email = userCont.getEmail(uiInfo.getUserID());
        String campComName =  userCont.getCampComName(uiInfo.getUserID());
        String points = Integer.toString(userCont.getPoints(uiInfo.getUserID()));

        System.out.println("ACCOUNT DETAIL"); 
        System.out.println("┌───────────────────┬───────────────────────────────────────────┐"); // 65 
          System.out.println("│ Name              │"+ fillUpSpace(name, 43, 1, false) + "│");
        System.out.println("├───────────────────┼───────────────────────────────────────────┤");
        System.out.println("│ Faculty           │"+ fillUpSpace(faculty, 43, 1, false) + "│");
        System.out.println("├───────────────────┼───────────────────────────────────────────┤");
        System.out.println("│ Email             │"+ fillUpSpace(email, 43, 1, false) + "│");
        System.out.println("├───────────────────┼───────────────────────────────────────────┤");
          System.out.println("│ Camp Committee of │"+ fillUpSpace(campComName, 43, 1, false) + "│");
        System.out.println("├───────────────────┼───────────────────────────────────────────┤");
          System.out.println("│ Number of Points  │"+ fillUpSpace(points, 43, 1,false) + "│");
        System.out.println("└───────────────────┴───────────────────────────────────────────┘");
        System.out.println("(press any key to go back to Account Menu)"); 

        Scanner sc = new Scanner(System.in); 
        sc.next(); 
        sc.close();   
    }

}