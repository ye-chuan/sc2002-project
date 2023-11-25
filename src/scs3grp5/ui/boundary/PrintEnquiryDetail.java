package scs3grp5.ui.boundary;

import scs3grp5.controller.EnquiryController;

public class PrintEnquiryDetail implements IPrintDetail{

    /**
     * to communicate with CampController
     */
    private EnquiryController enquiryCont; 

    private String enquiryID; 

    public PrintEnquiryDetail(String enquiryID){
        this.enquiryID = enquiryID; 
        enquiryCont = new EnquiryController();
    }
    
    @Override
    public void printDetail() {
        System.out.println("Enquiry: "+ enquiryCont.getEnquiryText(enquiryID));
        System.out.println("Asked by: " + enquiryCont.getEnquiryCreator(enquiryID)); 
        System.out.println("Replied: " + enquiryCont.getEnquiryReply(enquiryID));
        System.out.println("Replied by: " + enquiryCont.getEnquiryReplyCreator(enquiryID));
    }
    
}
