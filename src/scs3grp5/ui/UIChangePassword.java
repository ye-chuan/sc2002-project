package scs3grp5.ui;

import java.util.Scanner;
import scs3grp5.controller.UserController;

public class UIChangePassword implements IUserInterface {
    
    private UIInformation uiInfo;

    private UserController userCont; 

    public UIChangePassword(UIInformation uiInfo){
        this.uiInfo = uiInfo; 
        userCont = new UserController(); 
    }

    /**
     * UI for User to change password 
     */
    public IUserInterface showUI(){

        ChangePage.changePage();
        System.out.println("Change of Password Menu"); 

        String newPassword; 
        String reTypePassword; 
        Scanner sc = new Scanner(System.in); 

        do{
            System.out.print("Enter new password: "); 
            newPassword = sc.nextLine(); 

            if (userCont.isPasswordStrong(newPassword)){
                System.out.print("Re-type new password: "); 
                reTypePassword = sc.nextLine(); 
            
                if (isPasswordSame(newPassword, reTypePassword)){
                    sc.close(); 
                    System.out.println("Password change successfully!!"); 
                    userCont.changePassword(uiInfo.getUserID(), newPassword); 
                    return new UIViewAccount(uiInfo);
                }
                else{
                    System.out.println("Password is not the same"); 
                }
            }
            else{
                System.out.println("Password is not strong"); 
            } 
            
        }while (true); 
    }

    protected boolean isPasswordSame(String password, String reTypePassword){
        return password.equals(reTypePassword);  
    }
}
