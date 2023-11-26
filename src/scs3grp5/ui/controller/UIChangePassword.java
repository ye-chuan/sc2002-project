package scs3grp5.ui.controller;

import java.util.Scanner;

import scs3grp5.controller.PasswordException;
import scs3grp5.controller.UserController;

/**
 * This class is a UI controller for the change password UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UIChangePassword implements IUserInterface {
    
    /**
     * uiInfo contains the information of the user using the UI and the next
     * IDs (camp, enquiry, suggestion) they would like to explore during the program
     */
    private UIInformation uiInfo;

    /**
     * To communicate with the User Controller; 
     */
    private UserController userCont; 

    /**
     * Constructor for the UIChangePasswod 
     * 
     * @param uiInfo contains the information of the user
     */
    public UIChangePassword(UIInformation uiInfo){
        this.uiInfo = uiInfo; 
        userCont = new UserController(); 
    }

    /**
     * This method controls the logic for the UI for User to change password 
     * 
     * @return the new UI page to run
     */
    @Override
    public IUserInterface showUI(){

        System.out.println("───────────────────────────────────────────────────────");// # ─ = 55  
        System.out.println("Change of Password Menu"); 

        String newPassword; 
        String reTypePassword; 
        Scanner sc = new Scanner(System.in); 

        do{
            System.out.print("Enter new password: "); 
            newPassword = sc.nextLine(); 

            try{
                userCont.isPasswordStrong(newPassword);
            }catch( PasswordException e){
                System.out.println(e.getMessage());
                System.out.println("Password is not strong");
                continue; 
            }
            
            System.out.print("Re-type new password: "); 
            reTypePassword = sc.nextLine(); 
        
            if (isPasswordSame(newPassword, reTypePassword)){
                try{
                    userCont.changePassword(uiInfo.getUserID(), newPassword);
                    System.out.println("Password change successfully!!");
                    return new UIViewAccount(uiInfo);
                }catch ( PasswordException e){
                    System.out.println(e.getMessage());
                    continue; 
                }
            }
            else{
                System.out.println("Password is not the same"); 
            }
            
        }while (true); 
    }

    /**
     * This method check if the new passwor and the retype password is the same 
     * 
     * @param password User's new password 
     * @param reTypePassword User's retype password 
     * @return true if both is equal else false
     */
    private boolean isPasswordSame(String password, String reTypePassword){
        return password.equals(reTypePassword);  
    }
}
