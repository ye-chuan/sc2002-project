package scs3grp5.ui.controller;

import java.util.List;
import java.util.Scanner;
import scs3grp5.controller.SuggestionController;
import scs3grp5.ui.input.SelectionFromList;
import scs3grp5.ui.input.SelectionMenu;
import scs3grp5.ui.menu.ListSuggestion;
import scs3grp5.ui.menu.MenuSuggestionCommitteeList;
import scs3grp5.ui.menu.MenuSuggestionStaffList;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;
import scs3grp5.ui.ulti.PrintHelper;


/**
 * This class is a UI controller for the sugestion list UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UISuggestionList extends UserInterface{

    /**
     * To communicate with the Suggestion Controller
     */
    private SuggestionController suggestionCont; 

    /**
     * contains the listOfSuggestions to be shown on the UI
     */
    private List<String> listOfSuggestions; 

    /**
     * Constructor for the UISuggestionList class
     * 
     * @param uiInfo contains the information of the UI 
     */
    public UISuggestionList(UIInformation uiInfo) {
        super(uiInfo);
        suggestionCont = new SuggestionController(uiInfo.getCampID()); 
    }

    /**
     * This method controls the logic of the Suggestion List UI, where it will show and handle the user input 
     * to navigate them to the right UI or action 
     * 
     * @return the next UI page to run
     */
    @Override
    public IUserInterface showUI() {

        int option = -1;
        menu = new MenuSuggestionCommitteeList(); 
        if (uiInfo.getIsStaff()) menu = new MenuSuggestionStaffList();
        optionSelector = new SelectionMenu();

        boolean wrongInput = false;
        do{
            try{
                ChangePage.changePage();
                System.out.println(PrintHelper.LOGO_STRING);
                System.out.println();
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
            else if (listOption == 0) wrongInput = true; 
            else{
                uiInfo.setSuggestionID(listOfSuggestions.get(listOption-1));  
                return new UISuggestion(uiInfo);
            }
        }while (wrongInput);

        return null; 
    }

    /**
     * This method provides the UI for user to submit their suggestion
     * 
     * @return the suggestion to submit
     */
    private String createSuggestionUI(){
        System.out.print("Enter Suggestion:"); 
        Scanner sc = new Scanner(System.in); 
        String suggestion = sc.nextLine();
        return suggestion;
    }
}
