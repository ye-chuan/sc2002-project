package scs3grp5.ui.controller;

import java.util.Scanner;

import scs3grp5.controller.PasswordException;
import scs3grp5.controller.UserController;
import scs3grp5.ui.ulti.ChangePage;

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

            try{
                userCont.isPasswordStrong(newPassword);
            }catch( PasswordException e){
                System.out.println(e.getMessage());
                System.out.println("Password is not strong"); 
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

    protected boolean isPasswordSame(String password, String reTypePassword){
        return password.equals(reTypePassword);  
    }
}
