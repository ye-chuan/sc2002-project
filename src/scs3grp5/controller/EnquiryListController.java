package scs3grp5.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages enquiry list viewing in the system
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class EnquiryListController {

    private String campID;
    
    /**
	 * Constructor for EnquiryListController
	 * @param campID The unique ID of the Camp
	 * 
	 */
	public EnquiryListController(String campID) {
		this.campID = campID;
	}


    /**
	 * get unresolved enquiries by camp or user depending on camp role
	 * @param userID The unique ID of the User
	 * @see EnquiryController#getPendingEnquiries(String)
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
	 * get resolved enquiries by camp or user depending on camp role
	 * @param userID The unique ID of the User
	 * @see EnquiryController#getResolvedEnquiries(String)
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
	 * get all enquiries by camp or user depending on camp role
	 * @param userID The unique ID of the User
	 * @see EnquiryController#getAllEnquiries(String)
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

    /**
	 * sorts list of enquiries alphabetically by the creator name of enquiry
	 * @param enquiryList An unsorted collection of Enquiry objects
	 * @return sorted list of enquiryID
	 */
	private List<String> sortByNameIDList(Collection<Enquiry> enquiryList) {
		Collections.sort((ArrayList<Enquiry>)enquiryList, Comparator.comparing(Enquiry::getEnquiry));
		ArrayList<String> enquiryIDList = new ArrayList<String>();
		for (Enquiry e: enquiryList)
		{
			enquiryIDList.add(e.getID());
		}
		return enquiryIDList;
	}


}
