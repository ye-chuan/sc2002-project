package ui;

public class PrintCampCommCampDetail implements IPrintDetail {

    private CampController campCont; 
    private String campID; 

    public PrintCampCommCampDetail(String campID){
        this.campID = campID; 
        campCont = new CampController();
    }
    
    @Override
    public void printDetail() {

        int actualCount = campCont.getTotalParticipantSlot(campID) - campCont.getRemainingParticipantSlot(campID); 
        String participant = Integer.toString(actualCount) + "/" + Integer.toString(campCont.getTotalParticipantSlot(campID));
        actualCount = campCont.getTotalCampCommSlot(campID) - campCont.getRemainingCampCommSlot(campID); 
        String campComm = Integer.toString(actualCount) + "/" + Integer.toString(campCont.getTotalCampCommSlots(campID));


        System.out.println("----------------------------------------------"); 
        System.out.println("CAMP INFORMATION"); 
        System.out.println("Camp Name: " + campCont.getName(campID)); 
        System.out.println("Date of Camp: " + campCont.getDate(uiInfo.getCampID()));
        System.out.println("Registration closing date: " + campCont.getClosingDate(campID));
        System.out.println("Camp Staff-In-Charge: " + campCont.getCampInCharge(campID)); 
        System.out.println("Participants Slot: " + participant); 
        System.out.println("Camp Committee Slot: " + campComm); 
        System.out.println("Faculty: " + campCont.getFaculty(campID)); 
        System.out.println("Camp Description: " + campCont.getDescription(campID)); 
        System.out.println("Status: CAMP COMMITTEE");
        System.out.println("----------------------------------------------"); 
    }
    
}
