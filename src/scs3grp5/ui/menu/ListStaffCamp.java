package scs3grp5.ui.menu;

import java.util.List;
import scs3grp5.ui.ulti.PrintHelper;

public class ListStaffCamp extends ListStudentCamp {

    public ListStaffCamp(List<String> listOfCamps) {
        super(listOfCamps);
    }

    @Override
    public int printMenu(){
        int option = 0; 

        System.out.println("LIST OF CAMPS"); 
        if(listOfCamps.size() == 0) System.out.println("(no camp for the moment)");
        else System.out.println("┌─────┬─────────────────────────────────────────────────────────────────────────────────────┐");// 85 WHITE SPACE
        for (String campID : listOfCamps){
            String date = campCont.getDate(campID); 

            int actualCount = campCont.getTotalParticipantSlots(campID) - campCont.getRemainingParticipantSlots(campID); 
            String participant = Integer.toString(actualCount) + "/" + Integer.toString(campCont.getTotalParticipantSlots(campID));
            actualCount = campCont.getTotalCampCommSlots(campID) - campCont.getRemainingCampCommSlots(campID); 
            String campComm = Integer.toString(actualCount) + "/" + Integer.toString(campCont.getTotalCampCommSlots(campID));
            String visibility; 
            if (campCont.getVisibility(campID)) visibility = "ON";
            else visibility = "OFF";

            String name = campCont.getName(campID); 
            if (campCont.isCampOver(campID)) date = "REGISTRATION CLOSED";

            System.out.println("│ ("+ (++option) + ") │" + PrintHelper.fillUpSpace(name, 85, 3, false) + "│"); 

            if (date.equals("REGISTRATION CLOSED")) System.out.println("│     │" + PrintHelper.fillUpSpace(" ", 60, 0, false) + PrintHelper.ANSI_RED + PrintHelper.fillUpSpace(date,25,0, false) + PrintHelper.ANSI_RESET + "│"); 
            else System.out.println("│     │" + PrintHelper.fillUpSpace(" ", 60, 0, false) + PrintHelper.fillUpSpace(date,25, 0, false) + "│"); 

            System.out.println("│     │ PARTICIPANT SLOT:" + PrintHelper.fillUpSpace(participant, 67, 1,false)  + "│");
            
            System.out.println("│     │ CAMP COMMITTEE SLOT:" + PrintHelper.fillUpSpace(campComm, 64, 1,false) + "│");

            if (visibility.equals("ON")) System.out.println("│     │ VISIBILITY:" + PrintHelper.ANSI_GREEN + PrintHelper.fillUpSpace(visibility, 73, 1,false) + PrintHelper.ANSI_RESET + "│");
            else System.out.println("│     │ VISIBILITY:" + PrintHelper.ANSI_RED + PrintHelper.fillUpSpace(visibility, 73, 1, false) + PrintHelper.ANSI_RESET + "│");
            
            if (option != listOfCamps.size())
                System.out.println("├─────┼─────────────────────────────────────────────────────────────────────────────────────┤");
            else 
                System.out.println("└─────┴─────────────────────────────────────────────────────────────────────────────────────┘");
        }
        System.out.println("(press any non-numeric key to go to Camp List Menu)");
        System.out.println("----------------------------------------------"); 

        return listOfCamps.size();
        }
    
}
