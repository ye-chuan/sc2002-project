package scs3grp5.ui.menu;

import java.util.List;

import scs3grp5.controller.CampController;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.PrintHelper;

/**
 * This class is provides the list of camps to be printed in the List Suggestion UI
 * This class is called when the user is a student
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class ListStudentCamp implements IPrintMenu {

    /**
     * Contains the list of camps to that the user can view 
     */
    protected List<String> listOfCamps; 

    /**
     * To communicate with Camp Controller
     * 
     * @param listOfCamps the list of camps to be printed
     */
    protected CampController campCont;  

    /**
     * Constructor class for ListStudentCamp 
     * 
     * @param listOfCamps the list of camps to be printed
     */
    public ListStudentCamp(List<String> listOfCamps){
        this.listOfCamps = listOfCamps;
        campCont = new CampController();
    }
    
    /** 
     * This method is to print the list of camps in student view. 
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        int option = 0; 

        ChangePage.changePage();
        System.out.println(PrintHelper.LOGO_STRING);
        System.out.println();
        
        System.out.println("List Of Camps"); 
        if(listOfCamps.size() == 0) System.out.println("(no camps for the moment)");
        else System.out.println("┌──────┬─────────────────────────────────────────────────────────────────────────────────────┐");// 85 WHITE SPACE
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
            String optionString = "("+ Integer.toString(++option) + ")"; 
            System.out.println("│"+ PrintHelper.fillUpSpace(optionString, 6, 1, true) + "│" + PrintHelper.fillUpSpace(name, 85, 3,false) + "│"); 

            if (date.equals("REGISTRATION CLOSED")) System.out.println("│      │" + PrintHelper.fillUpSpace(" ", 60, 0, false) + PrintHelper.ANSI_RED + PrintHelper.fillUpSpace(date,25, 0, false) + PrintHelper.ANSI_RESET + "│"); 
            else System.out.println("│      │" + PrintHelper.fillUpSpace(" ", 60, 0, false) + PrintHelper.fillUpSpace(date, 25, 0, false) + "│"); 

            if (participant.equals("AVAILABLE")) System.out.println("│      │ PARTICIPANT SLOT:" + PrintHelper.ANSI_GREEN + PrintHelper.fillUpSpace(participant, 67, 1, false) + PrintHelper.ANSI_RESET + "│");
            else System.out.println("│      │ PARTICIPANT SLOT:" + PrintHelper.ANSI_RED + PrintHelper.fillUpSpace(participant, 67, 1, false) + PrintHelper.ANSI_RESET + "│");
            
            if (campComm.equals("AVAILABLE")) System.out.println("│      │ CAMP COMMITTEE SLOT:" + PrintHelper.ANSI_GREEN + PrintHelper.fillUpSpace(campComm, 64, 1, false) + PrintHelper.ANSI_RESET + "│");
            else System.out.println("│      │ CAMP COMMITTEE SLOT:" + PrintHelper.ANSI_RED + PrintHelper.fillUpSpace(campComm, 64, 1, false) + PrintHelper.ANSI_RESET + "│");
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
