package ui;

import java.util.ArrayList;

public class PrintSuggestionList implements IPrintMenu{

    /**
     * to communicate with Suggestion Controller
     */
    private SuggestionController suggestionCont; 

    /**
     * contains the listOfSuggestion to be shown on the UI
     */
    private ArrayList<String> listOfSuggestion; 

    public PrintSuggestionList(ArrayList<String> listOfSuggestion){{
        this.listOfSuggestion = listOfSuggestion;
        suggestionCont = new SuggestionController(); 
    }}

    @Override
    public int printMenu() {

        int option = 1; 
        System.out.println("----------------------------------------------"); 
        System.out.println("List Of Suggestion"); 
        System.out.println();
        for (String suggestionID : listOfSuggestion){
            System.out.printf("\n(%d)\n", option++); 
            System.out.println("Suggestion: "+ suggestionCont.getSuggestionText(suggestionID));
            System.out.println("Asked by: " + suggestionContquiry.getSuggestionCreator(suggestionID)); 
            System.out.println("Status: " + suggestionCont.getStatus(suggestionID));
        }
        System.out.println("----------------------------------------------"); 
        System.out.println("(press any non-numeric key to go to Suggestion List Menu)");
        return listOfSuggestion.size();
        
    }
    
}
