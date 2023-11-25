package ui;


public class UISuggestion extends UserInterface {

    private SuggestionController suggestionCont; 

    public UISuggestion(UIInformation uiInfo) {
        super(uiInfo);
        suggestionCont = new SuggestionController(); 
    }

    @Override
    public IUserInterface showUI() {

        IPrintDetail printDetail = new PrintSuggestionDetail(uiInfo.getSuggestionID());

        printDetail.printDetail();

        if (suggestionCont.getStatus(uiInfo.getSuggestionID()) != "PENDING" || !suggestionCont.isOwner(uiInfo.getUserID(), uiInfo.getSuggestionID()) || !uiInfo.getIsStaff()){
            menu = new MenuNoOption(); 
            optionSelector = new SelectionNull();
            try{
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
