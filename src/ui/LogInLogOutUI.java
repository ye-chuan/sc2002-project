package ui;

import java.util.Scanner;

/**
 * Handles the LogIn and LogOut UI
 */
public class LogInLogOutUI {

    private UserController userCont;
    
    /**
     * userID of the user using the UI
     */
    protected UIInformation uiInfo; 

    /** 
     * Constructor for LogInLogOut class 
     */
    public LogInLogOutUI(UIInformation uiInfo){
        this.uiInfo = uiInfo;
        userCont = new UserController(); 
    }

    /**
     * Log Out UI
     */
    public void logOutUI(){
        System.out.println("Exitting Program"); 
    }

    /** 
     * Log In UI 
     */
    public void loginUI(){

        String userID; 
        String password; 
        Scanner sc = new Scanner(System.in); 
        System.out.println("----------------------------------------------"); 
        System.out.println("Welcome to Camp Application and Management System (CAMs)"); 
            
        do{
            System.out.print("UserID: "); 
            userID = sc.next(); 

            System.out.print("Password: "); 
            password = sc.next();
            
            if (!userCont.login(userID, password)){
                System.out.println("Login credentials not found!"); 
                System.out.println("Please try again..."); 
                System.out.println("----------------------------------------------");
            }
            else break; 
        }while (true); 

        System.out.println("Login successful..."); 
        System.out.println("----------------------------------------------"); 
        sc.close();

        uiInfo.setUserID(userID); 

        //suite for first login
        if (userCont.isFirstLogin()){
            System.out.println("Password is default password"); 
            System.out.println("Please change to a strong password"); 
            uiInfo.setUIPage(UiPage.PASSWORD);
        } 
        else uiInfo.setUIPage(UiPage.HOMEPAGE);
    }

 

}