package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages replier actions for enquiries in the system
 * 
 */
public class ReplierController {

    private PointController pointCont = new PointController(); 
    private String campID;

    /**
	 * Constructor for ReplierController
	 * @param campID
	 * 
	 */
	public ReplierController(String campID) {
		this.campID = campID;
	}


    /**
	 * reply to enquiry and mark as resolved
	 * @param userID
	 * @param enquiryID
	 * @param reply
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
