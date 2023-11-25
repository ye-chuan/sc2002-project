package scs3grp5.ui.menu;

import java.util.List;

import scs3grp5.controller.CampController;
import scs3grp5.ui.ulti.PrintHelper;

public class ListStudentCamp implements IPrintMenu {

    protected List<String> listOfCamps; 

    /**
     * to communicate with CampController
     */
    protected CampController campCont;  

    public ListStudentCamp(List<String> listOfCamps){
        this.listOfCamps = listOfCamps;
        campCont = new CampController();
    }
    
    @Override
    public int printMenu() {
        int option = 0; 

        System.out.println("List Of Camps"); 
        if(listOfCamps.size() == 0) System.out.println("(no enquiry for the moment)");
        else System.out.println("┌─────┬─────────────────────────────────────────────────────────────────────────────────────┐");// 85 WHITE SPACE
        for (String campID : listOfCamps){
            String date = campCont.getDate(campID); 
            String participant = "AVAILABLE";
            String campComm = "AVAILABLE"; 

            String name = campCont.getName(campID); 
            if (campCont.getRemainingParticipantSlots(campID) == 0) participant = "FULL"; 
            if (campCont.getRemainingCampCommSlots(campID) == 0) campComm = "FULL"; 
            if (campCont.isCampOver(campID)){
                date = "REGISTRATION CLOSED";
                participant = "N/A"; 
                campComm = "N/A";
            }

            System.out.println("│ ("+ (++option) + ") │" + PrintHelper.fillUpSpace(name, 85, 3,false) + "│"); 

            if (date.equals("REGISTRATION CLOSED")) System.out.println("│     │" + PrintHelper.fillUpSpace(" ", 60, 0, false) + PrintHelper.ANSI_RED + PrintHelper.fillUpSpace(date,25, 0, false) + PrintHelper.ANSI_RESET + "│"); 
            else System.out.println("│     │" + PrintHelper.fillUpSpace(" ", 60, 0, false) + PrintHelper.fillUpSpace(date, 25, 0, false) + "│"); 

            if (participant.equals("AVAILABLE")) System.out.println("│     │ PARTICIPANT SLOT:" + PrintHelper.ANSI_GREEN + PrintHelper.fillUpSpace(participant, 67, 1, false) + PrintHelper.ANSI_RESET + "│");
            else System.out.println("│     │ PARTICIPANT SLOT:" + PrintHelper.ANSI_RED + PrintHelper.fillUpSpace(participant, 67, 1, false) + PrintHelper.ANSI_RESET + "│");
            
            if (campComm.equals("AVAILABLE")) System.out.println("│     │ CAMP COMMITTEE SLOT:" + PrintHelper.ANSI_GREEN + PrintHelper.fillUpSpace(campComm, 64, 1, false) + PrintHelper.ANSI_RESET + "│");
            else System.out.println("│     │ CAMP COMMITTEE SLOT:" + PrintHelper.ANSI_RED + PrintHelper.fillUpSpace(campComm, 64, 1, false) + PrintHelper.ANSI_RESET + "│");
            if (option != listOfCamps.size())
                System.out.println("├─────┼─────────────────────────────────────────────────────────────────────────────────────┤");
            else 
                System.out.println("└─────┴─────────────────────────────────────────────────────────────────────────────────────┘");
        }
        System.out.println("----------------------------------------------"); 
        System.out.println("(press any non-numeric key to go to Camp List Menu)");

        return listOfCamps.size();
    }
    
}
