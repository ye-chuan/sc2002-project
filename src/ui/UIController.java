package ui;

public class UIController {

    private USERTYPE userType; 

    private UiPage nextUIToRun; 

    private UIInformation uiInfo; 

    private IUserInterface ui;

    private UserController userCont; 

    private CampController campCont; 

    public UIController(){
        uiInfo = new UIInformation();
        userCont = new UserController(); 
        campCont = new CampController(); 
    }

    public void runUI(){
        LogInLogOutUI logInLogOut = new LogInLogOutUI(uiInfo); 

        logInLogOut.loginUI();

        userType = userCont.getDomain(uiInfo.getUserID()); 
        nextUIToRun = uiInfo.getUIPage();

        if (userType == UuserType.STUDENT) runStudentUI(); 
        else runStaffUI(); 

        logInLogOut.logOutUI();
    }

    private void runStaffUI(){
        do{
            switch(nextUIToRun){
                case HOMEPAGE: 
                    ui = new HomePageUI(uiInfo); 
                    ui.showUI();
                    break; 
                case VIEWACCOUNT:
                    ui = new ViewStaffAccountUI(uiInfo); 
                    ui.showUI();
                    break; 
                case PASSWORD:
                    ui = new ChangePasswordUI(uiInfo); 
                    ui.showUI();
                    break; 
                case CAMPLIST:
                    ui = new PrivilegedCampList(uiInfo); 
                    ui.showUI();
                    break; 
                case CAMP:
                    if (campCont.isEditable(uiInfo.getUserID(), uiInfo.getCampID())) ui = new SuperCampView(uiInfo); 
                    else ui = new NonPrivilegedCampView(uiInfo);
                    ui.showUI();
                    break; 
                case ENQURYLIST:
                    if (campCont.isEditable(uiInfo.getUserID(), uiInfo.getCampID())) ui = new PrivilegedEnquryList(uiInfo); 
                    else ui = new NonPrivilegedEnquryList(uiInfo); 
                    ui.showUI();
                    break; 
                case ENQURY:
                    // if (campCont.isEditable(uiInfo.getUserID(), uiInfo.getCampID())) ui = new SuperCampView(uiInfo); 
                    // else ui = new NonPrivilegedCampView(uiInfo); 
                    // ui.showUI();
                    // break; 
                case SUGGESTION:
                    //  ui = new NonPrivilegedCampView(uiInfo);; 
                    // ui.showUI();
                    // break; 
                case SUGGESTIONLIST:
                    ui = new PrivilegedSuggestionList(uiInfo); 
                    ui.showUI();
                    break; 
                case EDITCAMP:
                    ui = new EditCampUI(uiInfo); 
                    ui.showUI();
                    break; 
                default: 
            }
            nextUIToRun = uiInfo.getUIPage();
        }while (nextUIToRun != UiPage.ENDPROGRAM);
    }

    
    private void runStudentUI(){
        do{
            switch(nextUIToRun){
                case HOMEPAGE: 
                    ui = new HomePageUI(uiInfo); 
                    ui.showUI();
                    break; 
                case VIEWACCOUNT:
                    ui = new ViewStaffAccountUI(uiInfo); 
                    ui.showUI();
                    break; 
                case PASSWORD:
                    ChangePasswordUI passwordUI = new ChangePasswordUI(uiInfo); 
                    passwordUI.changePasswordUI();
                    break; 
                case CAMPLIST:
                    ui = new NonPrivilegedCampList(uiInfo); 
                    ui.showUI();
                    break; 
                case CAMP:
                    if (userCont.isStudentACommittee(uiInfo.getUserID(), uiInfo.getCampID())) ui = new PrivilegedCampView(uiInfo); 
                    else ui = new NonPrivilegedCampView(uiInfo);
                    ui.showUI();
                    break; 
                case ENQURYLIST:
                    if (userCont.isStudentACommittee(uiInfo.getUserID(), uiInfo.getCampID())) ui = new PrivilegedEnquryList(uiInfo); 
                    else ui = new NonPrivilegedEnquryList(uiInfo); 
                    ui.showUI();
                    break; 
                case ENQURY:
                    // if (campCont.isEditable(uiInfo.getUserID(), uiInfo.getCampID())) ui = new SuperCampView(uiInfo); 
                    // else ui = new NonPrivilegedCampView(uiInfo); 
                    // ui.showUI();
                    // break; 
                case SUGGESTION:
                    // ui = new NonPrivilegedCampView(uiInfo);; 
                    // ui.showUI();
                    // break; 
                case SUGGESTIONLIST:
                    ui = new NonPrivilegedSuggestionList(uiInfo);
                    ui.showUI();
                    break; 
                default: 
            }
            nextUIToRun = uiInfo.getUIPage();
        }while (nextUIToRun != UiPage.ENDPROGRAM);
    }
}
