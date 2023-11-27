package scs3grp5.ui;

import scs3grp5.ui.controller.*;


/**
 * This class is the MainUI that runs the UI pages
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UIMain{
    
    /** 
     * userID of the user using the UI
     */
    private UIInformation uiInfo; 

    /**
     * This is the next UI to run
     */
    private IUserInterface ui;

    /** 
     * Constructor class for the UIMain 
     * Inside the constructor, we create one uiInfo to be shared 
     * across all UIS 
     */
    public UIMain(){
        uiInfo = new UIInformation();
    }

    /** 
     * This is the method to be called to run the UI of the CAMs 
     */
    public void runUI(){
        UILogInLogOut logInLogOut = new UILogInLogOut(uiInfo); 

        ui =  logInLogOut.loginUI(); 

        while (ui != null) ui = ui.showUI();

        logInLogOut.logOutUI();
    }

}
