package ui;

public class UIMain {

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