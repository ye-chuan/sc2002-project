package scs3grp5.ui.controller;

import java.util.Scanner;
import scs3grp5.controller.UserController;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.PrintHelper;

/**
 * This class is a UI controller for the LogOutLogIn UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UILogInLogOut {

    private UserController userCont;
    
    /**
     * userID of the user using the UI
     */
    private UIInformation uiInfo; 

    /** 
     * Constructor for LogInLogOut class 
     */
    public UILogInLogOut(UIInformation uiInfo){
        this.uiInfo = uiInfo;
        userCont = new UserController(); 
    }

    /**
     * Log Out UI
     */
    public void logOutUI(){
        System.out.println(PrintHelper.END_PROGRAM); 
    }

    /** 
     * Log In UI 
     */
    public IUserInterface loginUI(){

        String userID; 
        String password; 
        Scanner sc = new Scanner(System.in); 

        ChangePage.changePage();

        System.out.println(PrintHelper.LOGO_STRING);
        System.out.println();
        System.out.println("───────────────────────────────────────────────────────────────"); 
        System.out.println("Welcome to Camp Application and Management System (CAMs)"); 
            
        do{
            System.out.print("UserID: "); 
            userID = sc.next(); 

            System.out.print("Password: "); 
            password = sc.next();
            
            if (!userCont.login(userID, password)){
                System.out.println("Login credentials not found!"); 
                System.out.println("Please try again..."); 
                System.out.println("───────────────────────────────────────────────────────────────");
            }
            else break; 
        }while (true); 

        System.out.println("Login successful..."); 
        System.out.println("───────────────────────────────────────────────────────────────");

        uiInfo.setUserID(userID); 
        uiInfo.setIsStaff(userCont.isStaffUserType(uiInfo.getUserID()));

        //suite for first login
        if (userCont.isFirstLogin(password)){
            System.out.println("Password is default password"); 
            System.out.println("Please change to a strong password"); 
            return new UIChangePassword(uiInfo);
        } 
        return new UIHomepage(uiInfo);
    }

 

}