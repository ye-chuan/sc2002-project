import java.util.InputMismatchException;
import java.util.Scanner;

public class PrivilegedCampView extends NonPrivilegedCampView{

    /**
     * To communicate with the ReportController
     */
    protected ReportController reportCont; 

    /**
     * Constructor for PrivilegedCampView class 
     * 
     * @param uiInfo contains the information of the user 
     */
    public PrivilegedCampView(UIInformation uiInfo) {
        super(uiInfo);
        reportCont = new ReportController(); 
    }

    @Override
    public void showUI() {
        
        int option = getUserChoiceUI();
        if (option == 1) generateStudentListUI();
        else if (option == 2) uiInfo.setUIPage(UIPAGE.ENQURYLIST);
        else if (option == 3) uiInfo.setUIPage(UIPAGE.SUGGESTIONLIST);
        else if (option == 4) uiInfo.setUIPage(UIPAGE.CAMPLIST); 
        else if (option == 5) uiInfo.setUIPage(UIPAGE.HOMEPAGE); 
        else uiInfo.setUIPage(UIPAGE.ENDPROGRAM);
    }

    
    @Override
    protected int printListOfOption() {
        int option = 1;

        printCampDetail();
        System.out.println("----------------------------------------------"); 
        System.out.println("CAMP COMMITTEE MENU");
        System.out.printf("(%d) Generate Attendee List\n", option++); 
        System.out.printf("(%d) View Enquiries\n", option++); 
        System.out.printf("(%d) View Suggestions\n", option++);
        System.out.printf("(%d) Go to Camp List Menu\n", option++); 
        System.out.printf("(%d) Go to HomePage\n", option); 
        System.out.printf("(0) Exit Program\n"); 
        System.out.println("----------------------------------------------"); 
        return option; 
    }


    @Override
    protected void printCampDetail(){

        System.out.println("----------------------------------------------"); 
        System.out.println("CAMP INFORMATION"); 
        System.out.println("Camp Name: " + campCont.getName(uiInfo.getCampID())); 
        System.out.println("Date of Camp: " + campCont.getDate(uiInfo.getCampID()));
        System.out.println("Registration closing date: " + campCont.getClosingDate(uiInfo.getCampID()));
        System.out.println("Camp Staff-In-Charge: " + campCont.getCampInCharge(uiInfo.getCampID())); 
        System.out.println("Participants Slot: " + campCont.getRemainingParticipantSlot(uiInfo.getCampID()) + "/" + campCont.getTotalParticipantSlot(uiInfo.getCampID())); 
        System.out.println("Camp Committee Slot: " + campCont.getRemainingCommitteeSlot(uiInfo.getCampID()) + "/" + campCont.getTotalCampCommSlot(uiInfo.getCampID())); 
        System.out.println("Faculty: " + campCont.getFaculty(uiInfo.getCampID())); 
        System.out.println("Camp Description: " + campCont.getDescription(uiInfo.getCampID())); 
        System.out.println("----------------------------------------------"); 
    }
    
    protected void generateStudentListUI(){
        
        Scanner sc = new Scanner(System.in); 
        int option;
        int maxOption = 2; 
    
        do{ 
          System.out.println("----------------------------------------------"); 
          System.out.println("Generate Student List"); 
          System.out.println("(1) PARTICIPANTS ONLY");
          System.out.println("(2) CAMP COMMITTEE ONLY"); 
          System.out.println("(3) ALL");
          System.out.println("(press any non-numeric key to go back to Camp Menu)");
          System.out.println("----------------------------------------------"); 
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

}
