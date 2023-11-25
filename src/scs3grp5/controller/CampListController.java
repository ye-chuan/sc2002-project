package scs3grp5.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import scs3grp5.Main;
import scs3grp5.entity.*;

public class CampListController {

	private CampDatabase.Query query;

	/**
	 * 
	 * @param userID
	 */
	public ArrayList<String> viewMyCamp(String userID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		UserDatabase uDB = Main.getUserDB();
		
		User u1 = uDB.getItem(userID);
		Collection<Camp> campList = new ArrayList<Camp>();

		if(u1 instanceof Staff) {
			CampDatabase.Query query = new CampDatabase.Query();

			query.onlyCampsBy(userID);

			campList = cDB.getCamps(query);
		}
		else if (u1 instanceof Student) {
			Student student1 = (Student) u1;
			//filter?
			
			campList = cmemberDB.getCampsJoinedBy(student1);
		}
		
		
		return sortByNameIDList(campList);


	}

	/**
	 * 
	 * @param userID
	 */
	public void setDefaultFilter(String userID, boolean isStaff) {
		UserDatabase uDB = Main.getUserDB();

		query.registrationAfterIncl(Date.today());
		query.campDatesAllAfterIncl(Date.today());

		if (!isStaff) {
			query.excludeInvisible();
			Student s1 = (Student) uDB.getItem(userID);
			Faculty s1Faculty = s1.getFaculty();
			query.onlyOpenToFaculty(s1Faculty);
			query.excludeFullCampCommSlots();
			query.excludeFullParticipantSlot();
		}
		
	}

	/**
	 * 
	 * 
	 */
	public ArrayList<String> viewCamps() {
		CampDatabase cDB = Main.getCampDB();
		Collection<Camp> campList = new ArrayList<Camp>();
		
		campList = cDB.getCamps(query);
		
		return sortByNameIDList(campList);
	}

	/**
	 * 
	 * @param location
	 * @param openParticipantSlots
	 * @param openCommSlots
	 * @param date
	 */
	public void FilterBy(String location, boolean openParticipantSlots, boolean openCommSlots, int fromDate, int toDate, Faculty faculty, boolean visibility) {
		CampDatabase.Query query = new CampDatabase.Query();
		DateController dateCont = new DateController();
		
		if (openCommSlots) query.excludeFullCampCommSlots();
		if (openParticipantSlots) query.excludeFullParticipantSlot();
		query.registrationAfterIncl(Date.today());
		query.onlyOpenToFaculty(faculty);
		if(!visibility) query.excludeVisible();

		query.campDatesAllWithin(dateCont.toDate(fromDate), dateCont.toDate(toDate));

		// Collection<Camp> campList = new ArrayList<Camp>();
		// for (Camp c : cDB.getCamps(query)) {
		// 	if (c.getLocation() == location) {
		// 		campList.add(c);
		// 	}
		// }
		
	}

	/**
	 * 
	 * @param campList
	 */
	private ArrayList<String> sortByNameIDList(Collection<Camp> campList) {
		Collections.sort((ArrayList<Camp>)campList, Comparator.comparing(Camp::getName));
		ArrayList<String> campIDList = new ArrayList<String>();
		for (Camp c: campList)
		{
			campIDList.add(c.getID());
		}
		return campIDList;
	}

	
}



