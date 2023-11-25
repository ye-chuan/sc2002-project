package scs3grp5.ui.boundary;

import java.util.ArrayList;

import scs3grp5.controller.EnquiryController;
import scs3grp5.ui.ulti.PrintHelper;

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

        String text =  enquiryCont.getEnquiryText(enquiryID);
        String reply = enquiryCont.getEnquiryReply(enquiryID);
        ArrayList<String> string;

        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────"); //  # ─  = 90
        System.out.println(PrintHelper.fillUpSpace("ENQUIRY INFORMATION", 90, 1, true));
        System.out.println("──────────────────────────────────────────────────────────────────────────────────────────"); //  # ─  = 90
        System.out.println("     Asked by: " + (enquiryCont.getEnquiryCreator(enquiryID)).toUpperCase());
        System.out.println("     ┌──────────────────────────────────────────────────────────────────────────────┐");// 78 WHITE SPACE

        string = PrintHelper.breakDownString(text, 70);
        for (int i=0; i<string.size(); i++){
            System.out.println("     │" + PrintHelper.fillUpSpace(string.get(i),78 , 4, false) + "│");
        }
        System.out.println("     └──────────────────────────────────────────────────────────────────────────────┘");
        System.out.println("     Replied by: " + (enquiryCont.getEnquiryReplyCreator(enquiryID)).toUpperCase());
        System.out.println("     ┌──────────────────────────────────────────────────────────────────────────────┐");// 78 WHITE SPACE
        string = PrintHelper.breakDownString(reply, 70);
        for (int i=0; i<string.size(); i++){
            System.out.println("     │" + PrintHelper.fillUpSpace(string.get(i),78 , 4, false) + "│");
        }
        System.out.println("     └──────────────────────────────────────────────────────────────────────────────┘");
    }
    
}
