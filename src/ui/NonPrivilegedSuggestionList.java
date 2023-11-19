import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NonPrivilegedSuggestionList extends UserInterface implements IListUI{

    protected SuggestionController suggestionCont; 

    /**
     * contains the listOfEnquiries to be shown on the UI
     */
    protected ArrayList<String> listOfSuggestions; 

    public NonPrivilegedSuggestionList(UIInformation uiInfo) {
        super(uiInfo);
        suggestionCont = new SuggestionController(); 
    }

    @Override
    public void showUI() {

        int option = getUserChoiceUI();
        String suggestionID; 

        if (option == 1) {
            listOfSuggestions = suggestionCont.getCCPendingSuggestionByCamp(uiInfo.getUserID(), uiInfo.getCampID());
            suggestionID = selectFromListUI();
        }
        else if (option == 2) {
            listOfSuggestions = suggestionCont.getCCApprovedSuggestionByCamp(uiInfo.getUserID(),uiInfo.getCampID());
            suggestionID = selectFromListUI();
        }
        else if (option == 3) {
            listOfSuggestions = suggestionCont.getCCRejectedSuggestionByCamp(uiInfo.getUserID(),uiInfo.getCampID());
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

    @Override
    protected int printListOfOption() {
        int option = 1; 
        System.out.println("----------------------------------------------"); 
        System.out.println("SUGGESTION LIST MENU");
        System.out.printf("(%d) View pending suggestions\n", option++); 
        System.out.printf("(%d) View approved suggestions\n", option++);
        System.out.printf("(%d) View rejected suggestions\n", option++);
        System.out.printf("(%d) View all suggestion\n", option++); 
        System.out.printf("(%d) Go to Camp Menu\n", option++); 
        System.out.printf("(%d) Go to HomePage\n", option++); 
        System.out.printf("(0) Exit Program\n"); 
        System.out.println("----------------------------------------------"); 
        return option; 
    }
    
    @Override
    public void printList(){
        int option = 1; 
        System.out.println("----------------------------------------------"); 
        System.out.println("List Of Suggestion"); 
        System.out.println();
        for (String suggestionID : listOfSuggestions){
            System.out.printf("\n(%d)\n", option++); 
            System.out.println("Suggestion: "+ suggestionCont.getSuggestionText(suggestionID));
            System.out.println("Asked by: " + suggestionContquiry.getSuggestionCreator(suggestionID)); 
            System.out.println("Status: " + suggestionCont.getStatus(suggestionID));
        }
        System.out.println("(press any non-numeric key to go to Suggestion List Menu)");
        System.out.println("----------------------------------------------"); 
    }

    @Override
    public String selectFromListUI(){
        int option;
        int maxOption; 
    
        Scanner sc = new Scanner(System.in); 
    
        do{ 
          maxOption = listOfSuggestions.size() - 1;
          printList();
          System.out.print("Select Suggestion: "); 
          try{
            option = sc.nextInt();
          }
          catch(InputMismatchException e){
            return null; 
          }
          if (!this.validOption(--option, maxOption)) System.out.println("Invalid Option!");
        } while (!this.validOption(option, maxOption)); 
    
        sc.close(); 

        return listOfSuggestions.get(option);
    }
    
}
