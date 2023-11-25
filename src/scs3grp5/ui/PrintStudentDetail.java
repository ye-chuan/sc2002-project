package ui;

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

        String name = userCont.getName(userID).toUpperCase(); 
        String faculty = userCont.getFaculty(userID).toUpperCase(); 
        String email = userCont.getEmail(userID);
        String campComName =  userCont.getCampComName(userID);
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
