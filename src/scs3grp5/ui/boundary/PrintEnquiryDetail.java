package scs3grp5.ui.boundary;

import java.util.ArrayList;

import scs3grp5.controller.EnquiryController;
import scs3grp5.ui.ulti.PrintHelper;


/**
 * This class prints the enquiry detail
 * 
 * @author Belvedere Song Zheng Yi 
 * @version 1.0
 * @since 2023-11-26
 */
public class PrintEnquiryDetail implements IPrintDetail{

    /**
     * To communicate with Enquiry Controller
     */
    private EnquiryController enquiryCont; 

    /**
     * EnquiryID is the identify for the respective enquiry to print
     */
    private String enquiryID; 

    /**
     * Constructor for PrintEnquiryDetail class 
     * 
     * @param campID The campID that the enquiry is from 
     * @param enquiryID The enquiryID that the enquiry we are interested in
     */
    public PrintEnquiryDetail(String campID, String enquiryID){
        this.enquiryID = enquiryID; 
        enquiryCont = new EnquiryController(campID);
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
        System.out.println("     Replied by: " + (enquiryCont.getEnquiryRepliesCreator(enquiryID)).toUpperCase());
        System.out.println("     ┌──────────────────────────────────────────────────────────────────────────────┐");// 78 WHITE SPACE
        string = PrintHelper.breakDownString(reply, 70);
        for (int i=0; i<string.size(); i++){
            System.out.println("     │" + PrintHelper.fillUpSpace(string.get(i),78 , 4, false) + "│");
        }
        System.out.println("     └──────────────────────────────────────────────────────────────────────────────┘");
    }
    
}
