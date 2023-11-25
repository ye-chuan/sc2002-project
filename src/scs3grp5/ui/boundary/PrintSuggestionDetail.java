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

    public PrintSuggestionDetail(String suggestionID){
        this.suggestionID = suggestionID; 
        suggestionCont = new SuggestionController(); 
    }

    @Override
    public void printDetail() {

        String text = suggestionContquiry.getSuggestionCreator(suggestionID); 
        String status = suggestionCont.getStatus(suggestionID);

        ArrayList<String> string;

        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────"); //  # ─  = 90
        System.out.println(PrintHelper.fillUpSpace("SUGGESTION INFORMATION", 90, 1, true));
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────"); //  # ─  = 90
        System.out.println("     Asked by: " + (suggestionContquiry.getSuggestionCreator(suggestionID)).toUpperCase());
        System.out.println("     ┌──────────────────────────────────────────────────────────────────────────────┐");// 78 WHITE SPACE

        string = PrintHelper.breakDownString(text, 70);
        for (int i=0; i<string.size(); i++){
            System.out.println("     │" + PrintHelper.fillUpSpace(string.get(i),78 , 4, false) + "│");
        }
        System.out.println("     └──────────────────────────────────────────────────────────────────────────────┘");
        System.out.println("     STATUS: " + status);
        
    }
    
}
