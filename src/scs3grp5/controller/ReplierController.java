package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages replier actions for enquiries in the system
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class ReplierController {

    private PointController pointCont = new PointController(); 
    private String campID;

    /**
	 * Constructor for ReplierController
	 * @param campID The unique ID of the Camp
	 * 
	 */
	public ReplierController(String campID) {
		this.campID = campID;
	}


    /**
	 * reply to enquiry and mark as resolved
	 * @param userID The unique ID of the User Replier
	 * @param enquiryID The unique ID of the Enquiry
	 * @param reply The reply text to the Enquiry
	 */
	public void reply(String userID, String enquiryID, String reply) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		UserDatabase uDB = Main.getUserDB();
		Enquiry enq1 = eDB.getItem(enquiryID);
		User replier = uDB.getItem(userID);

		enq1.reply(reply, userID);
		if (replier instanceof Student) {
			pointCont.replyEnquiry(userID);
		}
	}

}
