package scs3grp5.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import entity.Camp;
import entity.CampDatabase;
import entity.CampMembershipDatabase;
import entity.CampRole;
import entity.Date;
import entity.Enquiry;
import entity.EnquiryDatabase;
import entity.Student;
import entity.Suggestion;
import entity.User;
import entity.UserDatabase;


public class EnquiryController {

	private PointController pointCont; 
	/**
	 * 
	 * @param userID
	 * @param enquiryID
	 * @param reply
	 */
	public void reply(String userID, String enquiryID, String reply) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		UserDatabase uDB = new UserDatabase();
		Enquiry enq1 = eDB.getItem(enquiryID);
		User u1 = uDB.getItem(userID);

		enq1.reply(reply);
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
		EnquiryDatabase eDB = new EnquiryDatabase();
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
	public void edit(String userID, String enquiryID, String newText) throws Exception{
		EnquiryDatabase eDB = new EnquiryDatabase();
		Enquiry enq1 = eDB.getItem(enquiryID);

		if (enq1.isResolved()) {
			throw new Exception("Enquiry is already resolved");
		}
		if (enq1.getAskedBy() == userID) {
			throw new Exception("Enquiry is not created by user");
		}
		enq1.setEnquiry(newText);
	}

	/**
	 * 
	 * @param userID
	 * @param enquiryID
	 */
	public void delete(String userID, String enquiryID) throws Exception{
		EnquiryDatabase eDB = new EnquiryDatabase();
		Enquiry enq1 = eDB.getItem(enquiryID);
	

		if (enq1.isResolved()) {
			throw new Exception("Enquiry is already resolved");
		}
		if (enq1.getAskedBy() == userID) {
			throw new Exception("Enquiry is not created by user");
		}
		eDB.remove(enq1);
	
	}

	/**
	 * 
	 * @param enquiryID
	 */
	public boolean getStatus(String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		Enquiry enq1 = eDB.getItem(enquiryID);
		
		return enq1.isResolved();
		

	}

	/**
	 * 
	 * @param enquiryID
	 */
	public boolean isValidEnquiry(String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
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
	public Collection<String> getPendingEnquiriesByCamp(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		Collection<Enquiry> sugList = c1.getEnquiryDB().getUnresolvedEnquiries();
		

		return sortByNameIDList(sugList);
	}

	/**
	 * 
	 * @param campID
	 */
	public Collection<String> getResolvedEnquiriesByCamp(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		Collection<Enquiry> sugList = c1.getEnquiryDB().getResolvedEnquiries();
		

		return sortByNameIDList(sugList);
	}

	/**
	 * 
	 * @param campID
	 */
	public Collection<String> getAllEnquiriesByCamp(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		Collection<Enquiry> enqList = new ArrayList<Enquiry>();
		enqList.addAll(c1.getEnquiryDB().getResolvedEnquiries());
		enqList.addAll(c1.getEnquiryDB().getUnresolvedEnquiries());

		return sortByNameIDList(enqList);
	}

	/**
	 * 
	 * @param userID
	 */
	public Collection<String> getMyPendingEnquiries(String campID, String userID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		Collection<Enquiry> enqList = c1.getEnquiryDB().getUnresolvedEnquiriesBy(userID);

		return sortByNameIDList(enqList);
	
	}

	/**
	 * 
	 * @param userID
	 */
	public Collection<String> getMyResolvedEnquiries(String campID, String userID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		Collection<Enquiry> enqList = c1.getEnquiryDB().getResolvedEnquiriesBy(userID);

		return sortByNameIDList(enqList);
	
	}

	/**
	 * 
	 * @param userID
	 */
	public Collection<String> getMyEnquiries(String campID, String userID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		Collection<Enquiry> enqList = new ArrayList<Enquiry>();
		enqList.addAll(c1.getEnquiryDB().getResolvedEnquiriesBy(userID));
		enqList.addAll(c1.getEnquiryDB().getUnresolvedEnquiriesBy(userID));

		return sortByNameIDList(enqList);
	
	}


	/**
	 * 
	 * @param enquiryID
	 */
	public String getEnquiryCreator(String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		return eDB.getItem(enquiryID).getAskedBy();

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
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		UserDatabase uDB = new UserDatabase();
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
	private Collection<String> sortByNameIDList(Collection<Enquiry> enquiryList) {
		Collections.sort((ArrayList<Enquiry>)enquiryList, Comparator.comparing(Enquiry::getAskedBy));
		ArrayList<String> enquiryIDList = new ArrayList<String>();
		for (Enquiry e: enquiryList)
		{
			enquiryIDList.add(e.getID());
		}
		return enquiryIDList;
	}


}