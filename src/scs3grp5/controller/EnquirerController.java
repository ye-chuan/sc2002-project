package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages enquirer actions for enquiries in the system
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class EnquirerController {
    
    private String campID;

    /**
	 * Constructor for EnquirerController Object
	 * @param campID The unique ID of the Camp
	 * 
	 */
	public EnquirerController(String campID) {
		this.campID = campID;
	}

    /**
	 * Creates student enquiry for camp
	 * @param userID The unique ID of the User
	 * @param text Enquiry text by the Enquirer
	 * @return unique ID of enquiry created
	 */
	public String create(String userID, String text) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		Enquiry enq1 = new Enquiry(text, userID);
		eDB.add(enq1);
		return enq1.getID();
	}

	/**
	 * Modifies enquiry with new text if it is not resolved.
	 * @param enquiryID The unique ID of the Enquiry
	 * @param newText  new Enquiry text by the Enquirer
	 */
	public void edit(String enquiryID, String newText) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		Enquiry enq1 = eDB.getItem(enquiryID);

		enq1.setEnquiry(newText);
	}

	/**
	 * Delete enquiry before it is resolved
	 * @param enquiryID The unique ID of the Enquiry
	 */
	public void delete(String enquiryID) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		Enquiry enq1 = eDB.getItem(enquiryID);

		eDB.remove(enq1);
	}

}
