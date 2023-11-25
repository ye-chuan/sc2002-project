package ui;

import java.util.Scanner;

public class UISuggestion extends UserInterface {

    private SuggestionController suggestionCont; 

    public UISuggestion(UIInformation uiInfo) {
        super(uiInfo);
        suggestionCont = new SuggestionController(); 
    }

    @Override
    public IUserInterface showUI() {

        IPrintDetail printDetail = new PrintSuggestionDetail(uiInfo.getSuggestionID());

        if (suggestionCont.getStatus(uiInfo.getSuggestionID()) != "PENDING" || !suggestionCont.isOwner(uiInfo.getUserID(), uiInfo.getSuggestionID()) || !uiInfo.getIsStaff()){
            System.out.println("Press any key to go back to Suggestion List Menu");
            Scanner sc = new Scanner(System.in); 
            sc.next(); 
            return new UISuggestionList(uiInfo);
        }

        int option = -1;
        menu = new MenuSuggestionEdit();
        if (uiInfo.getIsStaff()) menu = new MenuSuggestionStaff();
        optionSelector = new SelectionFromList();
        boolean wrongInput = false;

        do{
            try{
                ChangePage.changePage();
                option = optionSelector.getUserChoiceUI(menu.printMenu(), wrongInput);
                wrongInput = false;
            }
            catch(OptionException e){
                return new UISuggestionList(uiInfo);
            }
        }while (wrongInput); 
        
        if (option == 1){
            if (uiInfo.getIsStaff()) suggestionCont.approve(uiInfo.getSuggestionID());
            else suggestionCont.edit(uiInfo.getSuggestionID(), editSuggestionUI()); 
        } 
        else if (option == 2){
            if (uiInfo.getIsStaff()) suggestionCont.reject(uiInfo.getSuggestionID());
            else suggestionCont.delete(uiInfo.getSuggestionID());
        }
        return new UISuggestionList(uiInfo);
    }

    private String editSuggestionUI() {
        return null;
    }
    
}
