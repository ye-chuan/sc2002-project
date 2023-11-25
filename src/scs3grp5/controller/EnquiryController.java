package scs3grp5.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import scs3grp5.Main;
import scs3grp5.entity.*;


public class EnquiryController {

	private PointController pointCont = new PointController(); 
	private String campID;

	/**
	 * 
	 * @param campID
	 * 
	 */
	public EnquiryController(String campID) {
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

	/**
	 * 
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
	 * 
	 * @param userID
	 * @param enquiryID
	 * @param newText
	 */
	public void edit(String enquiryID, String newText) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		Enquiry enq1 = eDB.getItem(enquiryID);

		// if (enq1.isResolved()) {
		// 	throw new Exception("Enquiry is already resolved");
		// }
		// if (enq1.getAskedBy() == userID) {
		// 	throw new Exception("Enquiry is not created by user");
		// }
		enq1.setEnquiry(newText);
	}

	/**
	 * 
	 * @param userID
	 * @param enquiryID
	 */
	public void delete(String enquiryID) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		Enquiry enq1 = eDB.getItem(enquiryID);
	

		// if (enq1.isResolved()) {
		// 	throw new Exception("Enquiry is already resolved");
		// }
		// if (enq1.getAskedBy() == userID) {
		// 	throw new Exception("Enquiry is not created by user");
		// }
		eDB.remove(enq1);
	
	}

	/**
	 * 
	 * @param enquiryID
	 */
	public boolean getStatus(String enquiryID) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		Enquiry enq1 = eDB.getItem(enquiryID);
		
		return enq1.isResolved();
		

	}

	/**
	 * 
	 * @param enquiryID
	 */
	public boolean isValidEnquiry(String enquiryID) {
		EnquiryDatabase eDB = Main.getCampDB().getItem(campID).getEnquiryDB();
		if (eDB.getItem(enquiryID)== null) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * 
	 * @param campID
	 */
	public List<String> getPendingEnquiries(String userID) {
		CampDatabase cDB = Main.getCampDB();
		CampController campCont = new CampController();
		Collection<Enquiry> enqList = new ArrayList<Enquiry>();

		
		Camp c1 = cDB.getItem(campID);
		if (campCont.isCommittee(userID, campID)) {
			enqList = c1.getEnquiryDB().getUnresolvedEnquiries();
		}
		else {
			enqList = c1.getEnquiryDB().getUnresolvedEnquiriesBy(userID);
		}

		return sortByNameIDList(enqList);
	}

	/**
	 * 
	 * @param campID
	 */
	public List<String> getResolvedEnquiries(String userID) {
		CampDatabase cDB = Main.getCampDB();
		CampController campCont = new CampController();
		Collection<Enquiry> enqList = new ArrayList<Enquiry>();

		
		Camp c1 = cDB.getItem(campID);
		if (campCont.isCommittee(userID, campID)) {
			enqList = c1.getEnquiryDB().getResolvedEnquiries();
		}
		else {
			enqList = c1.getEnquiryDB().getResolvedEnquiriesBy(userID);
		}

		return sortByNameIDList(enqList);
	}

	/**
	 * 
	 * @param campID
	 */
	public List<String> getAllEnquiries(String userID) {
		CampDatabase cDB = Main.getCampDB();
		CampController campCont = new CampController();
		Collection<Enquiry> enqList = new ArrayList<Enquiry>();
	
		Camp c1 = cDB.getItem(campID);
		if (campCont.isCommittee(userID, campID)) {
			enqList.addAll(c1.getEnquiryDB().getResolvedEnquiries());
			enqList.addAll(c1.getEnquiryDB().getUnresolvedEnquiries());
		}
		else {
			enqList.addAll(c1.getEnquiryDB().getResolvedEnquiriesBy(userID));
			enqList.addAll(c1.getEnquiryDB().getUnresolvedEnquiriesBy(userID));
		}

		return sortByNameIDList(enqList);
	}

	// /**
	//  * 
	//  * @param userID
	//  */
	// public Collection<String> getMyPendingEnquiries(String campID, String userID) {
	// 	CampMembershipDatabase cmemberDB = Main.getMemberDB();
	// 	CampDatabase cDB = Main.getCampDB();
	// 	Camp c1 = cDB.getItem(campID);
	// 	Collection<Enquiry> enqList = c1.getEnquiryDB().getUnresolvedEnquiriesBy(userID);

	// 	return sortByNameIDList(enqList);
	
	// }

	// /**
	//  * 
	//  * @param userID
	//  */
	// public Collection<String> getMyResolvedEnquiries(String campID, String userID) {
	// 	CampMembershipDatabase cmemberDB = Main.getMemberDB();
	// 	CampDatabase cDB = Main.getCampDB();
	// 	Camp c1 = cDB.getItem(campID);
	// 	Collection<Enquiry> enqList = c1.getEnquiryDB().getResolvedEnquiriesBy(userID);

	// 	return sortByNameIDList(enqList);
	
	// }

	// /**
	//  * 
	//  * @param userID
	//  */
	// public Collection<String> getMyEnquiries(String campID, String userID) {
	// 	CampMembershipDatabase cmemberDB = Main.getMemberDB();
	// 	CampDatabase cDB = Main.getCampDB();
	// 	Camp c1 = cDB.getItem(campID);
	// 	Collection<Enquiry> enqList = new ArrayList<Enquiry>();
	// 	enqList.addAll(c1.getEnquiryDB().getResolvedEnquiriesBy(userID));
	// 	enqList.addAll(c1.getEnquiryDB().getUnresolvedEnquiriesBy(userID));

	// 	return sortByNameIDList(enqList);
	
	// }


	/**
	 * 
	 * @param enquiryID
	 */
	public String getEnquiryCreator(String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		UserDatabase uDB = Main.getUserDB();

		String userID = eDB.getItem(enquiryID).getAskedBy();
		return uDB.getItem(userID).getName();

	}

	/**
	 * 
	 * @param enquiryID
	 */
	public String getEnquiryRepliesCreator(String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		UserDatabase uDB = Main.getUserDB();
		String userID = eDB.getItem(enquiryID).getRepliedBy();
		return uDB.getItem(userID).getName();

	}

	/**
	 * 
	 * @param enquiryID
	 */
	public boolean isOwner(String userID, String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		return eDB.getItem(enquiryID).getAskedBy()==userID;

	}

	/**
	 * 
	 * @param enquiryID
	 */
	public String getEnquiryText(String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		return eDB.getItem(enquiryID).getEnquiry();
	}

	/**
	 * 
	 * @param enquiryID
	 */
	public String getEnquiryReply(String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		return eDB.getItem(enquiryID).getReply();
	}



	/**
	 * 
	 * @param campID
	 */
	public CampRole getUserStatus(String campID, String studentID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		UserDatabase uDB = Main.getUserDB();
		Camp c1 = cDB.getItem(campID);
		Student s1 = (Student) uDB.getItem(studentID);
		CampRole s1Role = cmemberDB.getRoleInCamp(c1,s1);

		if (s1Role != null) {
			return s1Role;
		}
		else return null;

	}

	/**
	 * 
	 * @param campList
	 */
	private List<String> sortByNameIDList(Collection<Enquiry> enquiryList) {
		Collections.sort((ArrayList<Enquiry>)enquiryList, Comparator.comparing(Enquiry::getAskedBy));
		ArrayList<String> enquiryIDList = new ArrayList<String>();
		for (Enquiry e: enquiryList)
		{
			enquiryIDList.add(e.getID());
		}
		return enquiryIDList;
	}


}