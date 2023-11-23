package ui;

import java.util.Scanner;

public class ChangePasswordUI implements IUserInterface {
    
    private UIInformation uiInfo;

    private UserController userCont; 

    public ChangePasswordUI(UIInformation uiInfo){
        this.uiInfo = uiInfo; 
        userCont = new UserController(); 
    }

    /**
     * UI for User to change password 
     */
    public void showUI(){

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
                    break; 
                }
                else{
                    System.out.println("Password is not the same"); 
                }
            }
            else{
                System.out.println("Password is not strong"); 
            } 
            
        }while (true); 

        uiInfo.setUIPage(UiPage.VIEWACCOUNT);
    }

    protected boolean isPasswordSame(String password, String reTypePassword){
        return password.equals(reTypePassword);  
    }
}
