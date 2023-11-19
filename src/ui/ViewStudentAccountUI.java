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
        System.out.println("----------------------------------------------"); 
        System.out.println("Account Detail"); 
        System.out.println("userID: " + userCont.getName(uiInfo.getUserID()).toUpperCase()); 
        System.out.println("Faculty: " + userCont.getFaculty(uiInfo.getUserID()).toUpperCase()); 
        System.out.println("Email: " + userCont.getEmail(useuiInfo.getUserID())); 
        System.out.println("Camp Committee of: " + userCont.getCampComName(uiInfo.getUserID())); 
        System.out.println("Number of points: " + userCont.getPoints(uiInfo.getUserID())); 
        System.out.println("----------------------------------------------"); 
        System.out.println("(press any key to go back to Account Menu)"); 

        Scanner sc = new Scanner(System.in); 
        sc.next(); 
        sc.close(); 
    }

}