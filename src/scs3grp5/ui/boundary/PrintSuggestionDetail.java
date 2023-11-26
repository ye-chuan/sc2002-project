package scs3grp5.ui.boundary;

import java.util.ArrayList;

import scs3grp5.controller.SuggestionController;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.PrintHelper;

/**
 * This class prints the suggestion details for user to see
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class PrintSuggestionDetail implements IPrintDetail {

    /**
     * to communicate with Suggestion Controller
     */
    private SuggestionController suggestionCont; 

    /**
     * suggestionID is the identifer 
     */
    private String suggestionID; 

    public PrintSuggestionDetail(String campID, String suggestionID){
        this.suggestionID = suggestionID; 
        suggestionCont = new SuggestionController(campID); 
    }

    /**
     * Details for a suggestion 
     */
    @Override
    public void printDetail() {

        String text = suggestionCont.getSuggestionCreator(suggestionID); 
        String status = suggestionCont.getStatus(suggestionID);

        ChangePage.changePage();
        System.out.println(PrintHelper.LOGO_STRING);
        System.out.println();
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────"); //  # ─  = 90
        System.out.println(PrintHelper.fillUpSpace("SUGGESTION INFORMATION", 90, 1, true));
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────"); //  # ─  = 90
        System.out.println("     Asked by: " + (suggestionCont.getSuggestionCreator(suggestionID)).toUpperCase());
        System.out.println("     ┌──────────────────────────────────────────────────────────────────────────────┐");// 78 WHITE SPACE

        ArrayList<String> string = PrintHelper.breakDownString(text, 70);
        for (int i=0; i<string.size(); i++){
            System.out.println("     │" + PrintHelper.fillUpSpace(string.get(i),78 , 4, false) + "│");
        }
        System.out.println("     └──────────────────────────────────────────────────────────────────────────────┘");
        System.out.println("     STATUS: " + status);
        
    }
    
}
