package scs3grp5.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import scs3grp5.Main;
import scs3grp5.entity.*;
import scs3grp5.entity.filtering.*;

public class CampListController {

	private Collection<Camp> campList;

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

			CampFilterer filterer = new CampFilterer(cDB.getAll());
			filterer.addFilter(CampStaffFilter.onlyBy(userID));
			campList = filterer.filter();

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
	public void setDefaultFilter(String userID, boolean isStaff) {
		CampDatabase cDB = Main.getCampDB();
		CampFilterer filterer = new CampFilterer(cDB.getAll());
		UserDatabase uDB = Main.getUserDB();
		
		filterer.addFilter(CampRegistrationFilter.afterIncl(Date.today()));
		filterer.addFilter(CampDatesFilter.allAfterIncl(Date.today()));
		

		if (!isStaff) {
			filterer.addFilter(CampVisiblityFilter.onlyVisible());
			Student s1 = (Student) uDB.getItem(userID);
			Faculty s1Faculty = s1.getFaculty();
			filterer.addFilter(CampFacultyFilter.onlyOpenedTo(s1Faculty));	
			filterer.addFilter(CampParticipantSlotsFilter.excludeFull());
			filterer.addFilter(CampCampCommSlotsFilter.excludeFull());
		}

		campList = filterer.filter();
		
	}

	/**
	 * 
	 * 
	 */
	public ArrayList<String> viewCamps() {
		
		return sortByNameIDList(campList);
	}

	/**
	 * 
	 * @param location
	 * @param openParticipantSlots
	 * @param openCommSlots
	 * @param date
	 */
	public void FilterBy(String userID, String location, boolean openParticipantSlots, boolean openCommSlots, String fromDate, String toDate, boolean onlyFaculty, boolean visibility) {
		CampDatabase cDB = Main.getCampDB();
		CampFilterer filterer = new CampFilterer(cDB.getAll());
		UserDatabase uDB = Main.getUserDB();
		
		filterer.addFilter(CampLocationFilter.onlyAt(location));
		filterer.addFilter(CampDatesFilter.allWithin(Date.fromString(fromDate), Date.fromString(toDate)));
		if (openCommSlots) 
			filterer.addFilter(CampCampCommSlotsFilter.excludeFull());
		if (openParticipantSlots) 
			filterer.addFilter(CampParticipantSlotsFilter.excludeFull());
		if (visibility) 
			filterer.addFilter(CampVisiblityFilter.onlyVisible());
		else {
			filterer.addFilter(CampVisiblityFilter.onlyInvisible());
			filterer.addFilter(CampVisiblityFilter.onlyVisible());
		}
		if(onlyFaculty) {
			filterer.addFilter(CampFacultyFilter.onlyOpenedTo(uDB.getItem(userID).getFaculty()));
		} 

		campList = filterer.filter();
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



