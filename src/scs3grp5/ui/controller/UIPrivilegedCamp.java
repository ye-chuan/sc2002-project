package scs3grp5.ui.controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import scs3grp5.controller.REPORTFILTER;
import scs3grp5.controller.ReportController;
import scs3grp5.ui.boundary.IPrintDetail;
import scs3grp5.ui.boundary.PrintCampCommCampDetail;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.MenuCampCampComm;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.PrintHelper;
import scs3grp5.ui.ulti.SelectionHelper;


/**
 * This class is a UI controller for the Privileged Camp UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UIPrivilegedCamp extends UserInterface{

    /**
     * To communicate with the ReportController
     */
    protected ReportController reportCont; 

    protected String errorMessage ="";

    /**
     * Constructor for PrivilegedCampView class 
     * 
     * @param uiInfo contains the information of the user 
     */
    public UIPrivilegedCamp(UIInformation uiInfo) {
        super(uiInfo);
        reportCont = new ReportController(); 
    }

    /** {@inheritDoc} */
    @Override
    public IUserInterface showUI() {
        
      boolean wrongInput = false;
      boolean errorRegistration = false;  
      int option = -1; 

      menu = new MenuCampCampComm(); 
      optionSelector = new SelectionMenu();
      IPrintDetail printDetail = new PrintCampCommCampDetail(uiInfo.getCampID());

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
            errorRegistration = false; 
        }
        catch(OptionException e){
            wrongInput = true; 
        }

        if (option == 1){
          generateStudentListUI();
          errorRegistration = true; 
        }
      }while (wrongInput || errorRegistration);

      if (option == 2) return new UIEnquiryList(uiInfo);
      else if (option == 3) return new UISuggestionList(uiInfo);
      else if (option == 4) return new UICampList(uiInfo);
      else if (option == 5) return new UIHomepage(uiInfo);
      return null;
    }

    /**
     * This method provides the UI to choose the student list they can generate
     */
    protected void generateStudentListUI(){
        
        Scanner sc = new Scanner(System.in); 
        int option;
        int maxOption = 3; 
    
        do{ 
          System.out.println("───────────────────────────────────────────────────────");// # ─ = 55  
          System.out.println("Generate Student List"); 
          System.out.println("\t(1) PARTICIPANTS ONLY");
          System.out.println("\t(2) CAMP COMMITTEE ONLY"); 
          System.out.println("\t(3) ALL");
          System.out.println("----------------------------------------------"); 
          System.out.println("(press any non-numeric key to go back to Camp Menu)");
          System.out.print("Select Option: "); 
          try{
            option = sc.nextInt(); 
          }
          catch(InputMismatchException e){
            return; 
          }
          if (!SelectionHelper.validOption(option,1, maxOption)) System.out.println("Invalid Option!");
    
        } while (!SelectionHelper.validOption(option,1, maxOption)); 

        REPORTFILTER filter; 

        switch(option){
          case 1:
            filter = REPORTFILTER.PARTICIPANTS; 
            break; 
          case 2: 
            filter = REPORTFILTER.CAMPCOMM; 
          default:
            filter = REPORTFILTER.ALL;
        }
        try{
           reportCont.generateCampReport(uiInfo.getCampID(), filter);
           errorMessage = "Student Report Generated Successfully!!";
        }catch(IOException e){
          errorMessage = e.getMessage();
        }
    }

}
