package scs3grp5.ui.menu;

import java.util.List;

import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.PrintHelper;


/**
 * This class is provides the list of camps to be printed in the List Suggestion UI
 * This class is called when the user is a staff. 
 * This class extends ListStudentCamp class as is it does the same thing but prints more detailed information
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class ListStaffCamp extends ListStudentCamp {

    /**
     * Constructor class for ListStaffCamp 
     * 
     * @param listOfCamps the list of camps to be printed
     */
    public ListStaffCamp(List<String> listOfCamps) {
        super(listOfCamps);
    }

    /** 
     * This method is to print the list of camps in staff view
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu(){
        int option = 0; 

        ChangePage.changePage();
        System.out.println(PrintHelper.LOGO_STRING);
        System.out.println();
        
        System.out.println("LIST OF CAMPS"); 
        if(listOfCamps.size() == 0) System.out.println("(no camp for the moment)");
        else System.out.println("┌──────┬─────────────────────────────────────────────────────────────────────────────────────┐");// 85 WHITE SPACE
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

            String optionString = "("+ Integer.toString(++option) + ")";

            System.out.println("│"+ PrintHelper.fillUpSpace(optionString, 6, 1, true) + "│" + PrintHelper.fillUpSpace(name, 85, 3, false) + "│"); 

            if (date.equals("REGISTRATION CLOSED")) System.out.println("│      │" + PrintHelper.fillUpSpace(" ", 60, 0, false) + PrintHelper.ANSI_RED + PrintHelper.fillUpSpace(date,25,0, false) + PrintHelper.ANSI_RESET + "│"); 
            else System.out.println("│      │" + PrintHelper.fillUpSpace(" ", 60, 0, false) + PrintHelper.fillUpSpace(date,25, 0, false) + "│"); 

            System.out.println("│      │ PARTICIPANT SLOT:" + PrintHelper.fillUpSpace(participant, 67, 1,false)  + "│");
            
            System.out.println("│      │ CAMP COMMITTEE SLOT:" + PrintHelper.fillUpSpace(campComm, 64, 1,false) + "│");

            if (visibility.equals("ON")) System.out.println("│      │ VISIBILITY:" + PrintHelper.ANSI_GREEN + PrintHelper.fillUpSpace(visibility, 73, 1,false) + PrintHelper.ANSI_RESET + "│");
            else System.out.println("│      │ VISIBILITY:" + PrintHelper.ANSI_RED + PrintHelper.fillUpSpace(visibility, 73, 1, false) + PrintHelper.ANSI_RESET + "│");
            
            if (option != listOfCamps.size())
                System.out.println("├──────┼─────────────────────────────────────────────────────────────────────────────────────┤");
            else 
                System.out.println("└──────┴─────────────────────────────────────────────────────────────────────────────────────┘");
        }
        System.out.println("----------------------------------------------"); 
        System.out.println("(press any non-numeric key to go to Camp List Menu)");
        System.out.println("(press 0 to go to filter)");

        return listOfCamps.size();
        }
    
}
