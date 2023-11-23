package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PrivilegedEnquryList extends NonPrivilegedEnquryList {

    
    public PrivilegedEnquryList(UIInformation uiInfo) {
        super(uiInfo);
    }

    @Override
    public void showUI() {
        int option = getUserChoiceUI();
        String enquiryID; 

        if (option == 1) {
            listOfEnquiries = enquiryCont.getPendingEnquiryByCamp(uiInfo.getCampID());
            enquiryID = selectEnquiryUI();
        }
        else if (option == 2) {
            listOfEnquiries = enquiryCont.getPendingEnquiryByCamp(uiInfo.getCampID());
            enquiryID = selectEnquiryUI();
        }
        else if (option == 3) {
            listOfEnquiries = enquiryCont.getPendingEnquiryByCamp(uiInfo.getCampID());
            enquiryID = selectEnquiryUI();
        } 
        else if (option == 4) uiInfo.setUIPage(UiPage.CAMP); 
        else if (option == 5) uiInfo.setUIPage(UiPage.HOMEPAGE); 
        else uiInfo.setUIPage(UiPage.ENDPROGRAM);

        if (enquiryID.isEmpty()) return;
        uiInfo.setEnquiryID(enquiryID);  
        uiInfo.setUIPage(UiPage.ENQURY); 
    }  
}
