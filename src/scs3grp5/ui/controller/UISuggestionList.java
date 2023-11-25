package scs3grp5.ui.controller;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import scs3grp5.controller.SuggestionController;
import scs3grp5.ui.input.SelectionFromList;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.ListSuggestion;
import scs3grp5.ui.menu.MenuEnquiryCommitteeList;
import scs3grp5.ui.menu.MenuSuggestionCommitteeList;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;

public class UISuggestionList extends UserInterface{

    private SuggestionController suggestionCont; 

    /**
     * contains the listOfSuggestions to be shown on the UI
     */
    private List<String> listOfSuggestions; 


    public UISuggestionList(UIInformation uiInfo) {
        super(uiInfo);
        suggestionCont = new SuggestionController(uiInfo.getCampID()); 
    }

    @Override
    public IUserInterface showUI() {

        int option = -1;
        menu = new MenuSuggestionCommitteeList(); 
        if (uiInfo.getIsStaff()) menu = new MenuEnquiryCommitteeList();
        optionSelector = new SelectionMenu();

        boolean wrongInput = false;
        do{
            try{
                ChangePage.changePage();
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                wrongInput = true; 
            }
        }while (wrongInput); 

        if (option == 1) {
            listOfSuggestions = suggestionCont.getPendingSuggestions(uiInfo.getUserID());
        }
        else if (option == 2) {
            listOfSuggestions = suggestionCont.getApprovedSuggestion(uiInfo.getUserID());
        }
        else if (option == 3) {
            listOfSuggestions = suggestionCont.getRejectedSuggestions(uiInfo.getUserID());
        }
        else if (option == 4) {
            listOfSuggestions = suggestionCont.getAllSuggestion(uiInfo.getUserID());
        } 
        else if (option == 5){
            if (uiInfo.getIsStaff()) return new UISuperCamp(uiInfo);
            else {
                uiInfo.setSuggestionID(suggestionCont.create(uiInfo.getUserID(),createSuggestionUI())); // create a suggestion
                return new UISuggestion(uiInfo);
            } 
        } 
        else if (option == 6){
            if (uiInfo.getIsStaff()) return new UIHomepage(uiInfo);
            else return new UIPrivilegedCamp(uiInfo);
        } 
        else if (option == 7) return new UIHomepage(uiInfo);
        else return null;

        wrongInput = false;
        int listOption = -1; 
        do{
            menu = new ListSuggestion(listOfSuggestions, uiInfo.getCampID());
            optionSelector = new SelectionFromList(); 

            try{
                ChangePage.changePage();
                listOption = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
            }
            catch(OptionException e){
                return new UISuggestionList(uiInfo);  
            }
            
            if (listOption == -1) wrongInput = true; 
            else{
                uiInfo.setCampID(listOfSuggestions.get(listOption-1));  
                return new UISuggestion(uiInfo);
            }
        }while (wrongInput);

        return null; 
    }

    private String createSuggestionUI(){
        System.out.print("Enter Suggestion:"); 
        Scanner sc = new Scanner(System.in); 
        String suggestion = sc.nextLine();
        return suggestion;
    }
}
