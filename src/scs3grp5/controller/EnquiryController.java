package scs3grp5.controller;

import java.util.List;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages enquiries in the system
 * 
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class EnquiryController {

	private String campID;
	private EnquirerController enquirerCont;
	private ReplierController replierCont;
	private EnquiryListController enqListCont;

	/**
	 * Constructor for EnquiryController object
	 * 
	 * @param campID The unique ID of the Camp
	 * 
	 */
	public EnquiryController(String campID) {
		this.campID = campID;
		enquirerCont = new EnquirerController(campID);
		replierCont = new ReplierController(campID);
		enqListCont = new EnquiryListController(campID);
	}

	/**
	 * reply to enquiry and mark as resolved
	 * 
	 * @param userID    The unique ID of the User
	 * @param enquiryID The unique ID of the Enquiry
	 * @param reply The reply text to the Enquiry
	 * @see ReplierController#reply(String, String, String)
	 */
	public void reply(String userID, String enquiryID, String reply) {
		replierCont.reply(userID, enquiryID, reply);
	}

	/**
	 * creates enquiry
	 * 
	 * @param userID The unique ID of the User
	 * @param text   the enquiry text by the Enquirer
	 * @return unique ID of enquiry created
	 * @see EnquirerController#create(String, String)
	 */
	public String create(String userID, String text) {
		return enquirerCont.create(userID, text);
	}

	/**
	 * edit enquiry with new text before it is resolved
	 * 
	 * @param enquiryID The unique ID of the Enquiry
	 * @param newText   the new enquiry text by the Enquirer
	 * @see EnquirerController#edit(String, String)
	 */
	public void edit(String enquiryID, String newText) {
		enquirerCont.edit(enquiryID, newText);
	}

	/**
	 * delete enquiry before it is resolved
	 * 
	 * @param enquiryID The unique ID of the Enquiry
	 * @see EnquirerController#delete(String)
	 */
	public void delete(String enquiryID) {
		enquirerCont.delete(enquiryID);
	}

	/**
	 * get status of whether enquiry is resolved
	 * 
	 * @param enquiryID The unique ID of the Enquiry
	 * @return true if enquiry is resolved
	 * 
	 */
	public boolean getStatus(String enquiryID) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		Enquiry enq1 = eDB.getItem(enquiryID);

		return enq1.isResolved();

	}

	// /**
	// *
	// * @param enquiryID The unique ID of the Enquiry
	// */
	// public boolean isValidEnquiry(String enquiryID) {
	// EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
	// if (eDB.getItem(enquiryID)== null) {
	// return false;
	// }
	// else {
	// return true;
	// }
	// }

	/**
	 * get list of unresolved enquiries.
	 * If user is camp committee member or staff in charge of camp, camp pending
	 * enquires to be replied will be returned
	 * If user is participant or non-member of camp, camp pending enquiries created
	 * by them will be returned
	 * 
	 * @param userID The unique ID of the User
	 * @return list of unresolved enquiries sorted alphabetically by enquiry creator
	 *         name
	 * @see EnquiryListController#getPendingEnquiries(String)
	 */
	public List<String> getPendingEnquiries(String userID) {
		return enqListCont.getPendingEnquiries(userID);
	}

	/**
	 * get list of resolved enquiries.
	 * If user is camp committee member or staff in charge of camp, camp resolved
	 * enquires will be returned
	 * If user is participant or non-member of camp, camp resolved enquiries created
	 * by them will be returned
	 * 
	 * @param userID The unique ID of the User
	 * @return list of resolved enquiries sorted alphabetically by enquiry creator
	 *         name
	 * @see EnquiryListController#getResolvedEnquiries(String)
	 */
	public List<String> getResolvedEnquiries(String userID) {
		return enqListCont.getResolvedEnquiries(userID);
	}

	/**
	 * get list of resolved and unresolved enquiries.
	 * <p>
	 * If user is camp committee member or staff in charge of camp, camp resolved
	 * and unresolved enquires will be returned
	 * <p>
	 * If user is participant or non-member of camp, camp resolved and unresolved
	 * enquiries created by them will be returned
	 * 
	 * @param userID The unique ID of the User
	 * @return list of resolved enquiries sorted alphabetically by enquiry creator
	 *         name
	 * @see EnquiryListController#getAllEnquiries(String)
	 */
	public List<String> getAllEnquiries(String userID) {
		return enqListCont.getAllEnquiries(userID);
	}

	/**
	 * get name of enquiry creator
	 * 
	 * @param enquiryID The unique ID of the Enquiry
	 * @return name of enquiry creator
	 */
	public String getEnquiryCreator(String enquiryID) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		UserDatabase uDB = Main.getUserDB();

		String userID = eDB.getItem(enquiryID).getAskedBy();
		return uDB.getItem(userID).getName();

	}

	/**
	 * get name of enquiry replier
	 * 
	 * @param enquiryID The unique ID of the Enquiry
	 * @return name of enquiry replier
	 */
	public String getEnquiryRepliesCreator(String enquiryID) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		UserDatabase uDB = Main.getUserDB();
		String userID = eDB.getItem(enquiryID).getRepliedBy();
		if (userID == null)
			return "N.A";
		return uDB.getItem(userID).getName();

	}

	/**
	 * check whether a enquiry is created by user
	 * 
	 * @param userID The unique ID of the Enquiry
	 * @param enquiryID The unique ID of the Enquiry
	 * @return true is enquired is created by user
	 */
	public boolean isOwner(String userID, String enquiryID) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		return eDB.getItem(enquiryID).getAskedBy().equals(userID);

	}

	/**
	 * get enquiry text
	 * 
	 * @param enquiryID The unique ID of the Enquiry
	 * @return enquiry text
	 */
	public String getEnquiryText(String enquiryID) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		return eDB.getItem(enquiryID).getEnquiry();
	}

	/**
	 * get enquiry reply
	 * 
	 * @param enquiryID The unique ID of the Enquiry
	 * @return enquiry text
	 */
	public String getEnquiryReply(String enquiryID) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		return eDB.getItem(enquiryID).getReply();
	}

	/**
	 * get student status in camp
	 * 
	 * @param campID The unique ID of the Camp
	 * @param studentID The unique ID of the Student
	 * @return camp role of student, participant or camp comm
	 *         <p>
	 *         null if not a member of camp
	 */
	public CampRole getUserStatus(String campID, String studentID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		UserDatabase uDB = Main.getUserDB();
		Camp c1 = cDB.getItem(campID);
		Student s1 = (Student) uDB.getItem(studentID);
		CampRole s1Role = cmemberDB.getRoleInCamp(c1, s1);

		if (s1Role != null) {
			return s1Role;
		} else
			return null;

	}

}