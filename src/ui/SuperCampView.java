package ui;

public class SuperCampView extends PrivilegedCampView{

    public SuperCampView(UIInformation uiInfo) {
        super(uiInfo);
    }

    @Override
    public void showUI() {

        int option = getUserChoiceUI();
        if (option == 1) uiInfo.setUIPage(UiPage.EDITCAMP); 
        else if (option == 2) generateStudentListUI();
        else if (option == 3) generatePerformanceReportUI(); 
        else if (option == 4) uiInfo.setUIPage(UiPage.ENQURYLIST);
        else if (option == 5) uiInfo.setUIPage(UiPage.SUGGESTIONLIST);
        else if (option == 6) uiInfo.setUIPage(UiPage.CAMPLIST); 
        else if (option == 7) uiInfo.setUIPage(UiPage.HOMEPAGE); 
        else uiInfo.setUIPage(UiPage.ENDPROGRAM);
        
    }

    @Override
    protected int printListOfOption() {
        int option = 1; 
        printCampDetail();
        System.out.println("----------------------------------------------"); 
        System.out.println("CAMP CREATOR MENU");
        System.out.printf("(%d) Edit Camp Details\n", option++); 
        System.out.printf("(%d) Generate Attendee List\n", option++); 
        System.out.printf("(%d) Generate Performance Report\n", option++); 
        System.out.printf("(%d) View Enquiries\n", option++); 
        System.out.printf("(%d) View Suggestions\n", option++);
        System.out.printf("(%d) Go to Camp List Menu\n", option++); 
        System.out.printf("(%d) Go to HomePage\n", option++); 
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
        System.out.println("Visibility: " + campCont.getVisibility(uiInfo.getCampID()));
        System.out.println("----------------------------------------------"); 

    }


    private void generatePerformanceReportUI(){
        reportCont.generatePerformanceReport(uiInfo.getCampID()); 
    }
    
}
