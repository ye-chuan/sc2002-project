import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NonPrivilegedEnquryList extends UserInterface implements IListUI{

    /**
     * to communicate with EnquiryController
     */
    protected EnquiryController enquiryCont; 

    /**
     * contains the listOfEnquiries to be shown on the UI
     */
    protected ArrayList<String> listOfEnquiries; 

    public NonPrivilegedEnquryList(UIInformation uiInfo) {
        super(uiInfo);
        enquiryCont = new EnquiryController();
    }

    @Override
    public void showUI() {
        int option = getUserChoiceUI();
        String enquiryID; 

        if (option == 1) {
            listOfEnquiries = enquiryCont.getStudentPendingEnquiryByCamp(uiInfo.getCampID());
            enquiryID = selectEnquiryUI();
        }
        else if (option == 2) {
            listOfEnquiries = enquiryCont.getStudentRepliedEnquiryByCamp(uiInfo.getCampID());
            enquiryID = selectEnquiryUI();
        }
        else if (option == 3) {
            listOfEnquiries = enquiryCont.getAllEnquiryByCamp(uiInfo.getCampID());
            enquiryID = selectEnquiryUI();
        } 
        else if (option == 4) uiInfo.setUIPage(UIPAGE.CAMP); 
        else if (option == 5) uiInfo.setUIPage(UIPAGE.HOMEPAGE); 
        else uiInfo.setUIPage(UIPAGE.ENDPROGRAM);

        if (enquiryID.isEmpty()) return;
        uiInfo.setEnquiryID(enquiryID);  
        uiInfo.setUIPage(UIPAGE.ENQURY); 
    }

    @Override
    protected int printListOfOption() {
        int option = 1; 
        System.out.println("----------------------------------------------"); 
        System.out.println("ENQUIRY LIST MENU");
        System.out.printf("(%d) View pending enquiries\n", option++); 
        System.out.printf("(%d) View replied enquiries\n", option++);
        System.out.printf("(%d) View all enquiry\n", option++); 
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
        System.out.println("List Of Enquiries"); 
        System.out.println();
        for (String enquiryID : listOfEnquiries){
            System.out.printf("\n(%d)\n", option++); 
            System.out.println("Enquiry: "+ enquiryCont.getEnquiryText(enquiryID));
            System.out.println("Asked by: " + enquiryCont.getEnquiryCreator(enquiryID)); 
            System.out.println("Replied: " + enquiryCont.getEnquiryReply(enquiryID));
            System.out.println("Replied by: " + enquiryCont.getEnquiryReplyCreator(enquiryID));
        }
        System.out.println("(press any non-numeric key to go to Enquiry List Menu)");
        System.out.println("----------------------------------------------"); 
    }

    @Override
    public String selectFromListUI(){
        int option;
        int maxOption; 
    
        Scanner sc = new Scanner(System.in); 
    
        do{ 
          maxOption = listOfEnquiries.size() - 1;
          printList();
          System.out.print("Select Enquiry: "); 
          try{
            option = sc.nextInt();
          }
          catch(InputMismatchException e){
            return null; 
          }
          if (!this.validOption(--option, maxOption)) System.out.println("Invalid Option!");
        } while (!this.validOption(option, maxOption)); 
    
        sc.close(); 

        return listOfEnquiries.get(option);
    }
    
}
