package scs3grp5.ui.boundary;

import scs3grp5.controller.SuggestionController;

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
        System.out.println("Suggestion: "+ suggestionCont.getSuggestionText(suggestionID));
        System.out.println("Asked by: " + suggestionContquiry.getSuggestionCreator(suggestionID)); 
        System.out.println("Status: " + suggestionCont.getStatus(suggestionID));
    }
    
}
