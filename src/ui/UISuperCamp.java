package ui;

public class UISuperCamp extends UIPrivilegedCamp{

    public UISuperCamp(UIInformation uiInfo) {
        super(uiInfo);
    }

    @Override
    public IUserInterface showUI() {

        boolean wrongInput = false; 
        int option = -1; 

        menu = new MenuCampStaff(); 
        optionSelector = new SelectionMenu();
        IPrintDetail printDetail = new PrintStaffCampDetail(uiInfo.getCampID());

        do{
            try{
                ChangePage.changePage();
                printDetail.printDetail();
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                wrongInput = true; 
            }
        }while (wrongInput);

        if (option == 1) uiInfo.setUIPage(UiPage.EDITCAMP); 
        else if (option == 2) generateStudentListUI();
        else if (option == 3) generatePerformanceReportUI(); 
        else if (option == 4) return new UIEnquiryList(uiInfo);
        else if (option == 5) return new UISuggestionList(uiInfo);
        else if (option == 6) return new UICampList(uiInfo); 
        else if (option == 7) return new UIHomepage(uiInfo);
        return null; 
    }

    private void generatePerformanceReportUI(){
        reportCont.generatePerformanceReport(uiInfo.getCampID()); 
    }
    
}
