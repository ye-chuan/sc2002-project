public class PrivilegedSuggestionList extends NonPrivilegedSuggestionList {

    public PrivilegedSuggestionList(UIInformation uiInfo) {
        super(uiInfo);
    }

    @Override
    public void showUI() {

        int option = getUserChoiceUI();
        String suggestionID; 

        if (option == 1) {
            listOfSuggestions = suggestionCont.getPendingSuggestionByCamp(uiInfo.getCampID());
            suggestionID = selectFromListUI();
        }
        else if (option == 2) {
            listOfSuggestions = suggestionCont.getApprovedSuggestionByCamp(uiInfo.getCampID());
            suggestionID = selectFromListUI();
        }
        else if (option == 3) {
            listOfSuggestions = suggestionCont.getRejectedSuggestionByCamp(uiInfo.getCampID());
            suggestionID = selectFromListUI();
        }
        else if (option == 4) {
            listOfSuggestions = suggestionCont.getAllSuggestionByCamp(uiInfo.getUserID(),uiInfo.getCampID());
            suggestionID = selectFromListUI();
        } 
        else if (option == 5) uiInfo.setUIPage(UIPAGE.CAMP); 
        else if (option == 6) uiInfo.setUIPage(UIPAGE.HOMEPAGE); 
        else uiInfo.setUIPage(UIPAGE.ENDPROGRAM);

        if (suggestionID.isEmpty()) return;
        uiInfo.setSuggestionID(suggestionID);  
        uiInfo.setUIPage(UIPAGE.SUGGESTION); 
    }

}