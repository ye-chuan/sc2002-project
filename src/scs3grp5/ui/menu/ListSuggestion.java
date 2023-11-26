package scs3grp5.ui.menu;

import java.util.List;

import scs3grp5.controller.SuggestionController;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.PrintHelper;


/**
 * This class is provides the list of suggestions to be printed in the List Suggestion UI
 * This class is called when the user is both a camp commitee and the staff IC
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class ListSuggestion implements IPrintMenu{

    /**
     * to communicate with Suggestion Controller
     */
    private SuggestionController suggestionCont; 

    /**
     * contains the listOfSuggestion to be shown on the UI
     */
    private List<String> listOfSuggestion; 

    public ListSuggestion(List<String> listOfSuggestion, String campID){
        this.listOfSuggestion = listOfSuggestion;
        suggestionCont = new SuggestionController(campID); 
    }

    /** 
     * This method is to print the list of suggestions 
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {

        int option = 1; 
        
        ChangePage.changePage();
        System.out.println(PrintHelper.LOGO_STRING);
        System.out.println();
        
        System.out.println("LIST OF SUGGESTION"); 
        if(listOfSuggestion.size() == 0) System.out.println("(no enquiry for the moment)");
        else System.out.println("┌─────┬─────────────────────────────────────────────────────────────────────────────────────┐");// 85 WHITE SPACE
        for (String suggestionID : listOfSuggestion){
            String text = suggestionCont.getSuggestionText(suggestionID);
            String status = suggestionCont.getStatus(suggestionID);
            

            System.out.println("│ ("+ (++option) + ") │" + PrintHelper.fillUpSpace(text, 85, 1, false) + "│"); 
            if (status.equals("APPROVED")) System.out.println("│     │" + PrintHelper.fillUpSpace(" ", 78, 1, false)  + PrintHelper.ANSI_GREEN + status  + PrintHelper.ANSI_RESET + "│");
            else if (status.equals("REJECTED")) System.out.println("│     │" + PrintHelper.fillUpSpace(" ", 78, 1, false)  + PrintHelper.ANSI_RED + status  + PrintHelper.ANSI_RESET + "│");
            else System.out.println("│     │" + PrintHelper.fillUpSpace(" ", 78, 1, false)  + PrintHelper.ANSI_YELLOW + status  + PrintHelper.ANSI_RESET + "│");

            if (option != listOfSuggestion.size())
                System.out.println("├─────┼─────────────────────────────────────────────────────────────────────────────────────┤");
            else 
                System.out.println("└─────┴─────────────────────────────────────────────────────────────────────────────────────┘");
        }
        System.out.println("(press any non-numeric key to go to Enquiry List Menu)");
        return listOfSuggestion.size();
        
    }
    
}
