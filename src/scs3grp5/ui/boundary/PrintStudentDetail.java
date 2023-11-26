package scs3grp5.ui.boundary;

import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.PrintHelper;

/**
 * This class prints the user detail for a student
 * It inherit from the PrintStaffDetail class as the class prints more 
 * detail such as the name of the camp they are a camp committee and the number of points they have obtained 
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class PrintStudentDetail extends PrintStaffDetail {

    /** 
     * Constructor for PrintStudentDetail
     * 
     * @param userID contains the userID for the user  
     */
    public PrintStudentDetail(String userID){
        super(userID); 
    }
    
    @Override
    public void printDetail(){

        ChangePage.changePage();
        System.out.println(PrintHelper.LOGO_STRING);
        System.out.println();

        String name = userCont.getName(userID).toUpperCase(); 
        String faculty = userCont.getFaculty(userID).toUpperCase(); 
        String email = userCont.getUserName(userID) + "@ntu.edu.sg";
        String campComName =  userCont.getStudentCommitteeCampID(userID);
        String points = Integer.toString(userCont.getPoints(userID));

        System.out.println("ACCOUNT DETAIL"); 
        System.out.println("┌───────────────────┬───────────────────────────────────────────┐"); // 65 
          System.out.println("│ Name              │"+ PrintHelper.fillUpSpace(name, 43, 1, false) + "│");
        System.out.println("├───────────────────┼───────────────────────────────────────────┤");
        System.out.println("│ Faculty           │"+ PrintHelper.fillUpSpace(faculty, 43, 1, false) + "│");
        System.out.println("├───────────────────┼───────────────────────────────────────────┤");
        System.out.println("│ Email             │"+ PrintHelper.fillUpSpace(email, 43, 1, false) + "│");
        System.out.println("├───────────────────┼───────────────────────────────────────────┤");
          System.out.println("│ Camp Committee of │"+ PrintHelper.fillUpSpace(campComName, 43, 1, false) + "│");
        System.out.println("├───────────────────┼───────────────────────────────────────────┤");
          System.out.println("│ Number of Points  │"+ PrintHelper.fillUpSpace(points, 43, 1,false) + "│");
        System.out.println("└───────────────────┴───────────────────────────────────────────┘");
    }
    
}
