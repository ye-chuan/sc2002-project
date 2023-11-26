package scs3grp5.ui.controller;

import scs3grp5.ui.boundary.IPrintDetail;
import scs3grp5.ui.boundary.PrintStaffCampDetail;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.MenuCampStaff;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.PrintHelper;

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
                System.out.println(PrintHelper.LOGO_STRING);
                System.out.println();
                printDetail.printDetail();
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                wrongInput = true; 
            }
        }while (wrongInput);

        if (option == 1) return new UIEditCamp(uiInfo, false); 
        else if (option == 2){
            generateStudentListUI();
            return new UISuperCamp(uiInfo); 
        }
        else if (option == 3){
            generatePerformanceReportUI(); 
            return new UISuperCamp(uiInfo);
        }
        else if (option == 4) return new UIEnquiryList(uiInfo);
        else if (option == 5) return new UISuggestionList(uiInfo);
        else if (option == 6) return new UICampList(uiInfo); 
        else if (option == 7) return new UIHomepage(uiInfo);
        return null; 
    }

    private void generatePerformanceReportUI(){
        reportCont.generatePerformanceReport(uiInfo.getCampID()); 
        System.out.println("Performance Report Generated Successfully!!");
    }
    
}
