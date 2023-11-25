package scs3grp5.ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import scs3grp5.controller.ReportController;

public class UIPrivilegedCamp extends UserInterface{

    /**
     * To communicate with the ReportController
     */
    protected ReportController reportCont; 

    /**
     * Constructor for PrivilegedCampView class 
     * 
     * @param uiInfo contains the information of the user 
     */
    public UIPrivilegedCamp(UIInformation uiInfo) {
        super(uiInfo);
        reportCont = new ReportController(); 
    }

    @Override
    public IUserInterface showUI() {
        
      boolean wrongInput = false; 
      int option = -1; 

      menu = new MenuCampCampComm(); 
      optionSelector = new SelectionMenu();
      IPrintDetail printDetail = new PrintCampCommCampDetail(uiInfo.getCampID());

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

      if (option == 1) generateStudentListUI();
      else if (option == 2) return new UIEnquiryList(uiInfo);
      else if (option == 3) return new UISuggestionList(uiInfo);
      else if (option == 4) return new UICampList(uiInfo);
      else if (option == 5) return new UIHomepage(uiInfo);
      return null;
    }

    
    protected void generateStudentListUI(){
        
        Scanner sc = new Scanner(System.in); 
        int option;
        int maxOption = 2; 
    
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
          if (!this.validOption(--option, maxOption)) System.out.println("Invalid Option!");
    
        } while (!this.validOption(option, maxOption)); 
    
        sc.close(); 
        reportCont.campReportFilterBy(uiInfo.getCampID(), option); 
    }

    /**
     * This method check if the option is a valid option 
     * 
     * @param option Option chosen by the user 
     * @param max the maximum option can take
     * @return True if its a valid option else False
    */
    private boolean validOption(int option, int max){
      if (option >= 0 && option <= max) return true; 
      return false;   
  }

}
