package scs3grp5.ui.menu;

import java.util.List;
import scs3grp5.controller.EnquiryController;
import scs3grp5.ui.ulti.ChangePage;
import scs3grp5.ui.ulti.PrintHelper;


/**
 * This class is provides the list of enquiries to be printed in the List Enquiries UI
 * This class is called for all user
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class ListEnquiry implements IPrintMenu{

    /**
     * The list of enquiries to be printed
     */
    private List<String> listOfEnquiries; 

    /**
     * to communicate with Enquiry Controller
     */
    private EnquiryController enquiryCont;  

    /**
     * Constructor class for listOfEnquiries
     * 
     * @param listOfEnquiries list of enquiries to be view
     * @param campID campID of the camp
     */
    public ListEnquiry(List<String> listOfEnquiries, String campID){
        this.listOfEnquiries = listOfEnquiries;
        enquiryCont = new EnquiryController(campID);
    }

    /** 
     * This method is to print the list of enquiries 
     * 
     * @return the max number of option 
    */
    @Override
    public int printMenu() {
        int option = 0; 

        ChangePage.changePage();
        System.out.println(PrintHelper.LOGO_STRING);
        System.out.println();
        
        System.out.println("LIST OF ENQUIRIES"); 
        if(listOfEnquiries.size() == 0) System.out.println("(no enquiry for the moment)");
        else System.out.println("┌──────┬─────────────────────────────────────────────────────────────────────────────────────┐");// 85 WHITE SPACE
        for (String enquiryID : listOfEnquiries){
            String text = enquiryCont.getEnquiryText(enquiryID);
            boolean replied = enquiryCont.getStatus(enquiryID);
            String status; 
            if (replied) status = "REPLIED"; 
            else status = "PENDING";

            String optionString = "("+ Integer.toString(++option) + ")"; 
            System.out.println("│"+ PrintHelper.fillUpSpace(optionString, 6, 1, true) + "│" + PrintHelper.fillUpSpace(text, 85, 1, false) + "│"); 
            if (replied) System.out.println("│      │" + PrintHelper.fillUpSpace(" ", 78, 1, false)  + PrintHelper.ANSI_GREEN + status  + PrintHelper.ANSI_RESET + "│");
            else System.out.println("│      │" + PrintHelper.fillUpSpace(" ", 78, 1, false)  + PrintHelper.ANSI_RED + status  + PrintHelper.ANSI_RESET + "│");
            if (option != listOfEnquiries.size())
                System.out.println("├──────┼─────────────────────────────────────────────────────────────────────────────────────┤");
            else 
                System.out.println("└──────┴─────────────────────────────────────────────────────────────────────────────────────┘");
        }
        System.out.println("(press any non-numeric key to go to Enquiry List Menu)");
        return listOfEnquiries.size();
    }
    
}
