package scs3grp5.ui;

import scs3grp5.controller.CampController;

public class PrintStudentCampDetail implements IPrintDetail {

    private String campID; 
    private boolean isParticipant; 
    private CampController campCont; 

    public PrintStudentCampDetail(String campID, boolean isParticipant){
        this.campID = campID; 
        this.isParticipant = isParticipant;
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
        System.out.println("Date of Camp: " + campCont.getDate(campID));
        System.out.println("Registration closing date: " + campCont.getClosingDate(campID));
        System.out.println("Participants Slot: " + participant); 
        System.out.println("Camp Committee Slot: " + campComm); 
        System.out.println("Faculty: " + campCont.getFaculty(campID)); 
        System.out.println("Camp Description: " + campCont.getDescription(campID)); 
        if (isParticipant) System.out.println("Status: JOINED"); 
        else System.out.println("Status: NOT JOINED");
        System.out.println("----------------------------------------------"); 
    }
    
}
