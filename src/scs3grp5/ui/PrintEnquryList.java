package scs3grp5.ui;

import java.util.ArrayList;
import scs3grp5.controller.EnquiryController;

public class PrintEnquryList implements IPrintMenu{

    private ArrayList<String> listOfEnquiries; 

    /**
     * to communicate with CampController
     */
    private EnquiryController enquiryCont;  

    public PrintEnquryList(ArrayList<String> listOfEnquiries){
        this.listOfEnquiries = listOfEnquiries;
        enquiryCont = new EnquiryController();
    }

    @Override
    public int printMenu() {
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
        System.out.println("----------------------------------------------"); 
        System.out.println("(press any non-numeric key to go to Enquiry List Menu)");
        return listOfEnquiries.size();
    }
    
}
