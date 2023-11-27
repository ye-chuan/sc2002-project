package scs3grp5.ui.controller;

import java.io.IOException;

import scs3grp5.controller.CampController;
import scs3grp5.controller.EditCampException;
import scs3grp5.ui.boundary.IPrintDetail;
import scs3grp5.ui.boundary.PrintStaffCampDetail;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.MenuCampStaff;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.PrintHelper;


/**
 * This class is a UI controller for the super camp UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UISuperCamp extends UIPrivilegedCamp{
    
    /**
     * To communiate with the Camp controller 
     */
    private CampController campCont; 

    /**
     * Contructor for the UISuperCamp class 
     * 
     * @param uiInfo contain the information of the UI
     */
    public UISuperCamp(UIInformation uiInfo) {
        super(uiInfo);
        this.campCont = new CampController(); 
    }
    
    /**
     * This method controls the logic of the Super Camp UI, where it will show and handle the user input 
     * to navigate them to the right UI or action 
     * 
     * @return the next UI page to run
     */
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
            errorRegistration = false; 
            if (option == 2){
                generateStudentListUI(); 
                errorRegistration = true; 
            }
            else if (option == 3){
                generatePerformanceReportUI();
                errorRegistration = true; 
            }
            System.out.println(errorMessage);

            if (option == 6){
                try{
                    campCont.delete(uiInfo.getCampID());
                    return new UICampList(uiInfo); 
                }catch (EditCampException e){
                    errorMessage = e.getMessage();
                    errorRegistration = true; 
                }
            }
        }while (wrongInput || errorRegistration);

        if (option == 1) return new UIEditCamp(uiInfo, false);  
        else if (option == 4) return new UIEnquiryList(uiInfo);
        else if (option == 5) return new UISuggestionList(uiInfo);
        else if (option == 7) return new UICampList(uiInfo); 
        else if (option == 8) return new UIHomepage(uiInfo);
        else return null;
    }

    /**
     * This method call on the report controller to generate performance report for student
     * it will catch an error if an exception occur else it will print successful
     */
    private void generatePerformanceReportUI(){
        try{
            reportCont.generatePerformanceReport(uiInfo.getCampID()); 
            errorMessage = "Performance Report Generated Successfully!!";
        }catch (IOException e){
            errorMessage = e.getMessage();
        }
    }
    
}
