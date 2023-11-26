package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages enquirer actions for enquiries in the system
 * 
 */
public class EnquirerController {
    
    private String campID;

    /**
	 * Constructor for EnquirerController Object
	 * @param campID
	 * 
	 */
	public EnquirerController(String campID) {
		this.campID = campID;
	}

    /**
	 * Creates student enquiry for camp
	 * @param userID
	 * @param text
	 */
	public String create(String userID, String text) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		Enquiry enq1 = new Enquiry(text, userID);
		eDB.add(enq1);

		return enq1.getID();
	}

	/**
	 * Modifies enquiry with new text if it is not resolved.
	 * @param enquiryID
	 * @param newText
	 */
	public void edit(String enquiryID, String newText) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		Enquiry enq1 = eDB.getItem(enquiryID);

		enq1.setEnquiry(newText);
	}

	/**
	 * Delete enquiry before it is resolved
	 * @param enquiryID
	 */
	public void delete(String enquiryID) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		Enquiry enq1 = eDB.getItem(enquiryID);

		eDB.remove(enq1);
	}

}
