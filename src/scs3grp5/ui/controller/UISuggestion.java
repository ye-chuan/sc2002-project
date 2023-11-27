package scs3grp5.ui.controller;

import java.util.Scanner;

import scs3grp5.controller.SuggestionController;
import scs3grp5.ui.boundary.IPrintDetail;
import scs3grp5.ui.boundary.PrintSuggestionDetail;
import scs3grp5.ui.input.SelectionFromList;
import scs3grp5.ui.input.SelectionNull;
import scs3grp5.ui.menu.MenuNoOption;
import scs3grp5.ui.menu.MenuSuggestionEdit;
import scs3grp5.ui.menu.MenuSuggestionStaff;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.OptionException;


/**
 * This class is a UI controller for the suggestion UI
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class UISuggestion extends UserInterface {

    /**
     * To communicate with the Suggestion Controller
     */
    private SuggestionController suggestionCont; 

    /**
     * Constructor class for the UISuggestion 
     * 
     * @param uiInfo contains the information of the UI 
     */
    public UISuggestion(UIInformation uiInfo) {
        super(uiInfo);
        suggestionCont = new SuggestionController(uiInfo.getCampID()); 
    }
    
    /**
     * This method controls the logic of the Suggestion UI, where it will show and handle the user input 
     * to navigate them to the right UI or action 
     * 
     * @return the next UI page to run
     */
    @Override
    public IUserInterface showUI() {

        IPrintDetail printDetail = new PrintSuggestionDetail(uiInfo.getCampID(), uiInfo.getSuggestionID());

        printDetail.printDetail();

        if (!(suggestionCont.getStatus(uiInfo.getSuggestionID()).equals("PENDING") && (suggestionCont.isOwner(uiInfo.getUserID(), uiInfo.getSuggestionID()) || uiInfo.getIsStaff()))){
            menu = new MenuNoOption(); 
            optionSelector = new SelectionNull();
            try{
                printDetail.printDetail();
                optionSelector.getUserChoiceUI(menu.printMenu(), true);
            }
            catch (OptionException e){
                return new UISuggestionList(uiInfo);
            }
        }

        int option = -1;
        menu = new MenuSuggestionEdit();
        if (uiInfo.getIsStaff()) menu = new MenuSuggestionStaff();
        optionSelector = new SelectionFromList();
        boolean wrongInput = false;

        do{
            try{
                ChangePage.changePage();
                printDetail.printDetail();
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                return new UISuggestionList(uiInfo);
            }
        }while (wrongInput); 
        
        if (option == 1){
            if (uiInfo.getIsStaff()) suggestionCont.approve(uiInfo.getSuggestionID());
            else suggestionCont.edit(uiInfo.getUserID(), uiInfo.getSuggestionID(), editSuggestionUI()); 
        } 
        else if (option == 2){
            if (uiInfo.getIsStaff()) suggestionCont.reject(uiInfo.getSuggestionID());
            else {
                suggestionCont.delete(uiInfo.getUserID(), uiInfo.getSuggestionID());
                return new UISuggestionList(uiInfo);
            }
        }
        return new UISuggestion(uiInfo);
    }

    /**
     * This method provides the UI for the user to edit their suggestion
     * 
     * @return The edited suggestion
     */
    private String editSuggestionUI() {
        System.out.print("Enter Suggestion:"); 
        Scanner sc = new Scanner(System.in); 
        String suggestion = sc.nextLine();
        return suggestion;
    }
    
}
