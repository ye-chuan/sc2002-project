package scs3grp5.ui.controller;

import java.io.IOException;

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
        boolean errorRegistration = false; 

        menu = new MenuCampStaff(); 
        optionSelector = new SelectionMenu();
        IPrintDetail printDetail = new PrintStaffCampDetail(uiInfo.getCampID());

        do{
            try{
                ChangePage.changePage();
                System.out.println(PrintHelper.LOGO_STRING);
                System.out.println();
                printDetail.printDetail();
                System.out.println(errorMessage);
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                errorMessage = "";
                wrongInput = false;
            }
            catch(OptionException e){
                wrongInput = true; 
            }

            if (option == 2){
                generateStudentListUI(); 
                errorRegistration = true; 
            }
            else if (option == 3){
                generatePerformanceReportUI();
                errorRegistration = true; 
            }
            System.out.println(errorMessage);
        }while (wrongInput || errorRegistration);

        if (option == 1) return new UIEditCamp(uiInfo, false);  
        else if (option == 4) return new UIEnquiryList(uiInfo);
        else if (option == 5) return new UISuggestionList(uiInfo);
        else if (option == 6) return new UICampList(uiInfo); 
        else if (option == 7) return new UIHomepage(uiInfo);
        return null; 
    }

    private void generatePerformanceReportUI(){
        try{
            reportCont.generatePerformanceReport(uiInfo.getCampID()); 
            errorMessage = "Performance Report Generated Successfully!!";
        }catch (IOException e){
            errorMessage = e.getMessage();
        }
    }
    
}
