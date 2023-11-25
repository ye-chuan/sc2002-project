package scs3grp5.ui.menu;

import java.util.ArrayList;
import scs3grp5.controller.EnquiryController;
import scs3grp5.ui.ulti.PrintHelper;

public class ListEnquiry implements IPrintMenu{

    private ArrayList<String> listOfEnquiries; 

    /**
     * to communicate with CampController
     */
    private EnquiryController enquiryCont;  

    public ListEnquiry(ArrayList<String> listOfEnquiries){
        this.listOfEnquiries = listOfEnquiries;
        enquiryCont = new EnquiryController();
    }

    @Override
    public int printMenu() {
        int option = 0; 
        System.out.println("LIST OF ENQUIRIES"); 
        System.out.println("┌─────┬─────────────────────────────────────────────────────────────────────────────────────┐");// 85 WHITE SPACE
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
