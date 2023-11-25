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
	public Collection<String> viewMyCamp(String userID) {
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
			
			campList = cmemberDB.getCampsJoinedBy(student1);
		}
		
		
		return sortByNameIDList(campList);


	}

	/**
	 * 
	 * @param userID
	 */
	public void setDefaultFilter(String userID) {
		UserDatabase uDB = Main.getUserDB();

		User u1 = uDB.getItem(userID);
		

		query.excludeInvisible();
		query.registrationAfterIncl(Date.today());
		query.campDatesAllAfterIncl(Date.today());
		query.excludeFullCampCommSlots();
		query.excludeFullParticipantSlot();

		if (u1 instanceof Student) {
			Student s1 = (Student) uDB.getItem(userID);
			Faculty s1Faculty = s1.getFaculty();
			query.onlyOpenToFaculty(s1Faculty);
		}
		
	}

	/**
	 * 
	 * 
	 */
	public Collection<String> viewCamps() {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
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
	public Collection<String> FilterBy(String location, boolean openParticipantSlots, boolean openCommSlots, Date date, Faculty faculty, boolean visibility) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		CampDatabase.Query query = new CampDatabase.Query();
		
		if (openCommSlots) query.excludeFullCampCommSlots();
		if (openParticipantSlots) query.excludeFullParticipantSlot();
		query.registrationAfterIncl(Date.today());
		query.onlyOpenToFaculty(faculty);
		if(!visibility) query.excludeVisible();


		Collection<Camp> campList = new ArrayList<Camp>();
		for (Camp c : cDB.getCamps(query)) {
			if (c.getLocation() == location) {
				campList.add(c);
			}
		}
		
		return sortByNameIDList(campList);
	}

	/**
	 * 
	 * @param campList
	 */
	private Collection<String> sortByNameIDList(Collection<Camp> campList) {
		Collections.sort((ArrayList<Camp>)campList, Comparator.comparing(Camp::getName));
		ArrayList<String> campIDList = new ArrayList<String>();
		for (Camp c: campList)
		{
			campIDList.add(c.getID());
		}
		return campIDList;
	}
}