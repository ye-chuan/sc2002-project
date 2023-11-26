package scs3grp5.ui.menu;

import java.util.List;
import scs3grp5.controller.EnquiryController;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.PrintHelper;

public class ListEnquiry implements IPrintMenu{

    private List<String> listOfEnquiries; 

    /**
     * to communicate with CampController
     */
    private EnquiryController enquiryCont;  

    public ListEnquiry(List<String> listOfEnquiries, String campID){
        this.listOfEnquiries = listOfEnquiries;
        enquiryCont = new EnquiryController(campID);
    }

    @Override
    public int printMenu() {
        int option = 0; 

        ChangePage.changePage();
        System.out.println(PrintHelper.LOGO_STRING);
        System.out.println();
        
        System.out.println("LIST OF ENQUIRIES"); 
        if(listOfEnquiries.size() == 0) System.out.println("(no enquiry for the moment)");
        else System.out.println("┌─────┬─────────────────────────────────────────────────────────────────────────────────────┐");// 85 WHITE SPACE
        for (String enquiryID : listOfEnquiries){
            String text = enquiryCont.getEnquiryText(enquiryID);
            boolean replied = enquiryCont.getStatus(enquiryID);
            String status; 
            if (replied) status = "REPLIED"; 
            else status = "PENDING";

            System.out.println("│ ("+ (++option) + ") │" + PrintHelper.fillUpSpace(text, 85, 1, false) + "│"); 
            if (replied) System.out.println("│     │" + PrintHelper.fillUpSpace(" ", 78, 1, false)  + PrintHelper.ANSI_GREEN + status  + PrintHelper.ANSI_RESET + "│");
            else System.out.println("│     │" + PrintHelper.fillUpSpace(" ", 78, 1, false)  + PrintHelper.ANSI_RED + status  + PrintHelper.ANSI_RESET + "│");
            if (option != listOfEnquiries.size())
                System.out.println("├─────┼─────────────────────────────────────────────────────────────────────────────────────┤");
            else 
                System.out.println("└─────┴─────────────────────────────────────────────────────────────────────────────────────┘");
        }
        System.out.println("(press any non-numeric key to go to Enquiry List Menu)");
        return listOfEnquiries.size();
    }
    
}
