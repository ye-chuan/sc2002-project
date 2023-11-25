package scs3grp5.ui;

import scs3grp5.ui.controller.*;

public class UIMain{

    private UIInformation uiInfo; 

    private IUserInterface ui;


    public UIMain(){
        uiInfo = new UIInformation();
    }

    public void runUI(){
        UILogInLogOut logInLogOut = new UILogInLogOut(uiInfo); 

        ui =  logInLogOut.loginUI(); 

        while (ui != null) ui.showUI();

        logInLogOut.logOutUI();
    }

}
