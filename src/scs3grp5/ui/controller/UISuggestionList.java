package scs3grp5.ui.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
     * contains the listOfEnquiries to be shown on the UI
     */
    private ArrayList<String> listOfSuggestions; 


    public UISuggestionList(UIInformation uiInfo) {
        super(uiInfo);
        suggestionCont = new SuggestionController(); 
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
            listOfSuggestions = suggestionCont.getPendingSuggestionByCamp(uiInfo.getUserID(), uiInfo.getCampID());
        }
        else if (option == 2) {
            listOfSuggestions = suggestionCont.getApprovedSuggestionByCamp(uiInfo.getUserID(),uiInfo.getCampID());
        }
        else if (option == 3) {
            listOfSuggestions = suggestionCont.getRejectedSuggestionByCamp(uiInfo.getUserID(),uiInfo.getCampID());
        }
        else if (option == 4) {
            listOfSuggestions = suggestionCont.getAllSuggestionByCamp(uiInfo.getUserID(),uiInfo.getCampID());
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
            menu = new ListSuggestion(listOfSuggestions);
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
    }

    private String createSuggestionUI(){
        return null;
    }
}
