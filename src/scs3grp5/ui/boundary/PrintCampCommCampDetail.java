package scs3grp5.ui.boundary;

import java.util.ArrayList;

import scs3grp5.controller.CampController;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.PrintHelper;


/**
 * This class prints the camp detail for a camp committee  
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class PrintCampCommCampDetail implements IPrintDetail {

    /**
     * To communicate with the camp controller
     */
    private CampController campCont; 

    /**
     * CampID is the identify for the respective camp to print
     */
    private String campID; 

    /**
     * Constructor for PrintCampCommCampDetail class
     * 
     * @param campID the campID of the camp to print
     */
    public PrintCampCommCampDetail(String campID){
        this.campID = campID; 
        campCont = new CampController();
    }
    
    /**
     * Details for Camp Committee Camp view
     */
    @Override
    public void printDetail() {

        int actualCount = campCont.getTotalParticipantSlots(campID) - campCont.getRemainingParticipantSlots(campID); 
        String participant = Integer.toString(actualCount) + "/" + Integer.toString(campCont.getTotalParticipantSlots(campID));
        actualCount = campCont.getTotalCampCommSlots(campID) - campCont.getRemainingCampCommSlots(campID); 
        String campComm = Integer.toString(actualCount) + "/" + Integer.toString(campCont.getTotalCampCommSlots(campID));

        String campName =  campCont.getName(campID);
        String date = campCont.getDate(campID); 
        String closingDate = campCont.getClosingDate(campID);
        String location = campCont.getLocation(campID);
        String staffIC = campCont.getCampInCharge(campID);
        String faculty = campCont.getFaculty(campID);
        String status = "CAMP COMMITTEE";
        String description = campCont.getDescription(campID);


        ChangePage.changePage();
        System.out.println(PrintHelper.LOGO_STRING);
        System.out.println();
        
        System.out.println("CAMP INFORMATION");
        System.out.println("┌──────────────────────────────────────────────────────────────────────────────────────────┐");// 90 WHITE SPACE

        ArrayList<String> string2 = PrintHelper.breakDownString(campName, 60);
        for (int i=0; i<string2.size(); i++){
            System.out.println("│" + PrintHelper.fillUpSpace(string2.get(i),90 , 5, true) + "│"); 
        }
        System.out.println("│" + PrintHelper.fillUpSpace(" ",90 , 1, false) + "│");
        System.out.println("│  CAMP DATES               :" + PrintHelper.fillUpSpace(date,62 , 1, false) + "│");
        System.out.println("│  REGISTRATION CLOSING DATE:" + PrintHelper.fillUpSpace(closingDate,62 , 1, false) + "│");
        System.out.println("│  LOCATION                 :" + PrintHelper.fillUpSpace(location,62 , 1, false) + "│");
        System.out.println("│" + PrintHelper.fillUpSpace(" ",90 , 1, false) + "│");
        System.out.println("│  PARTICIPATION SLOT:" + PrintHelper.fillUpSpace(participant,69 , 1, false) + "│");
        System.out.println("│  COMMITTEE SLOT    :" + PrintHelper.fillUpSpace(campComm,69 , 1, false) + "│");
        System.out.println("│  STAFF-IN CHARGE   :" + PrintHelper.fillUpSpace(staffIC,69 , 1, false) + "│");
        System.out.println("│" + PrintHelper.fillUpSpace(" ",90 , 1, false) + "│");
        System.out.println("│  FACULTY:" + PrintHelper.fillUpSpace( faculty,80 , 1, false) + "│");
        System.out.println("│  STATUS :" + PrintHelper.fillUpSpace(status,80 , 1, false) + "│");
        System.out.println("│" + PrintHelper.fillUpSpace(" ",90 , 1, false) + "│");
        System.out.println("│" + "   ────────────────────────────────────────────────────────────────────────────────────   " + "│");
        System.out.println("│  CAMP DESCRIPTION:" + PrintHelper.fillUpSpace(" ",71 , 1, false) + "│");
        
        string2 = PrintHelper.breakDownString(description, 70);
        for (int i=0; i<string2.size(); i++){
            System.out.println("│" + PrintHelper.fillUpSpace(string2.get(i),90 , 10, false) + "│"); 
        }

        System.out.println("└──────────────────────────────────────────────────────────────────────────────────────────┘");
    }
    
}
