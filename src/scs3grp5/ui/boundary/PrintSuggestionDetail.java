package scs3grp5.ui.boundary;

import java.util.ArrayList;

import scs3grp5.controller.SuggestionController;
import scs3grp5.ui.ulti.PrintHelper;

public class PrintSuggestionDetail implements IPrintDetail {

    /**
     * to communicate with Suggestion Controller
     */
    private SuggestionController suggestionCont; 

    private String suggestionID; 

    public PrintSuggestionDetail(String campID, String suggestionID){
        this.suggestionID = suggestionID; 
        suggestionCont = new SuggestionController(campID); 
    }

    @Override
    public void printDetail() {

        String text = suggestionCont.getSuggestionCreator(suggestionID); 
        String status = suggestionCont.getStatus(suggestionID);


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
