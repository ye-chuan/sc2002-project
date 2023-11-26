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
	 * 
	 * @param campID
	 * 
	 */
	public ReplierController(String campID) {
		this.campID = campID;
	}


    /**
	 * 
	 * @param userID
	 * @param enquiryID
	 * @param reply
	 */
	public void reply(String userID, String enquiryID, String reply) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		UserDatabase uDB = Main.getUserDB();
		Enquiry enq1 = eDB.getItem(enquiryID);
		User u1 = uDB.getItem(userID);

		enq1.reply(reply, userID);
		if (u1 instanceof Student) {
			pointCont.replyEnquiry(userID);
		}
	}

}
