package scs3grp5.controller;

import java.util.*;

import scs3grp5.Main;
import scs3grp5.entity.*;
import scs3grp5.entity.Date;
import scs3grp5.entity.filtering.*;

/**
 * Manages camps list viewing in the system
 * 
 */
public class CampListController {

	private Collection<Camp> campList;

	/**
	 * 
	 * stores filtered list in class
	 * For User student: returns camps joined by student <p>
	 * For User staff: returns camps created by staff
	 * @param userID
	 * 
	 */
	public List<String> viewMyCamp(String userID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		UserDatabase uDB = Main.getUserDB();
		
		User u1 = uDB.getItem(userID);

		if(u1 instanceof Staff) {
			// get camps created by staff
			CampFilterer filterer = new CampFilterer(cDB.getAll());
			filterer.addFilter(CampStaffFilter.onlyBy(userID));
			campList = filterer.filter();
			
		}
		else if (u1 instanceof Student) {
			Student student1 = (Student) u1;
			// get camps registered by student
			campList = cmemberDB.getCampsJoinedBy(student1);
		}
		return sortByCampNameIDList(campList);

	}

	/**
	 * Set default filter for viewing all camps after today<p>
	 * and stores the filtered list in the class
	 * For User student: set filter visible, open to their faculty, and has remaining slots in camp
	 * 
	 * @param userID
	 * 
	 */
	public void viewCamps(String userID) {

		CampDatabase cDB = Main.getCampDB();
		CampFilterer filterer = new CampFilterer(cDB.getAll());
		UserDatabase uDB = Main.getUserDB();

		User u1 = uDB.getItem(userID);

		if (u1 instanceof Student) {
			Student student1 = (Student) u1;
			Faculty s1Faculty = student1.getFaculty();
			//open to NTU and Faculty
			filterer.addFilter(CampFacultyFilter.openedTo(s1Faculty));
			//visible
			filterer.addFilter(CampVisiblityFilter.onlyVisible());
		}
		//registration date and camp dates after today
		filterer.addFilter(CampRegistrationFilter.afterIncl(Date.today()));
		filterer.addFilter(CampDatesFilter.allAfterIncl(Date.today()));
	
		campList = filterer.filter();
	}

	/**
	 * provides a filtered list of camps based on filter set.
	 * @return list of campID sorted alphabetical by camp name
	 */
	public List<String> listOfCampRequest() {
	
		return sortByCampNameIDList(campList);
	}

	/**
	 * sets the filter by user and stores the filtered list into the class
	 * @param userID	userID for obtaining faculty of user
	 * @param location	filter by location
	 * @param openParticipantSlots filter by camps with available participant slots
	 * @param openCommSlots filter by camps with available camp committee slots
	 * @param fromDate filter by after start date
	 * @param toDate filter by before end date
	 * @param onlyFaculty filter by user faculty if true, by NTU if false
	 * @param visibility filter by visible camps if true
	 */
	public void FilterBy(String userID, String location, boolean openParticipantSlots, boolean openCommSlots, String fromDate, String toDate, boolean onlyFaculty, boolean visibility) {
		
		CampFilterer filterer = new CampFilterer(campList);
		UserDatabase uDB = Main.getUserDB();
		//filter by location if not empty
		if(!location.equals(""))
			filterer.addFilter(CampLocationFilter.onlyAt(location));
		//filter by date before toDate
		if(fromDate.equals("")&&!toDate.equals(""))
			filterer.addFilter(CampDatesFilter.allBeforeIncl(Date.fromString(toDate)));
		//filter by date to fromDate
		if(!fromDate.equals("")&&toDate.equals(""))
			filterer.addFilter(CampDatesFilter.allBeforeIncl(Date.fromString(toDate)));
		//filter by date between From Date to To Date
		if(!fromDate.equals("")&&!toDate.equals(""))
			filterer.addFilter(CampDatesFilter.allWithin(Date.fromString(fromDate), Date.fromString(toDate)));

		//filter by only open camp committee slots if true	
		if (openCommSlots) 
			filterer.addFilter(CampCampCommSlotsFilter.excludeFull(Main.getMemberDB()));
		//filter by only open camp participant slots if true
		if (openParticipantSlots) 
			filterer.addFilter(CampParticipantSlotsFilter.excludeFull(Main.getMemberDB()));
		//filter by is visible if true
		if (visibility) 
			filterer.addFilter(CampVisiblityFilter.onlyVisible());
		//filter by only camp faculty (excl. NTU) if true
		if(onlyFaculty) {
			filterer.addFilter(CampFacultyFilter.onlyOpenedTo(uDB.getItem(userID).getFaculty()));
		} 

		campList = filterer.filter();
	
	}

	/**
	 * provides a list of camp participants by campID
	 * @param campID
	 * @return List of studentID sorted alphabetically by name
	 */
	public List<String> getCampParticipantsList(String campID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);

		Collection<Student> studentList = cmemberDB.getParticipants(c1);
		

		return sortByStudentNameIDList(studentList);
	}

	/**
	 * provides a list of camp committee members by campID
	 * @param campID
	 * @return List of studentID sorted alphabetically by name
	 */
	public List<String> getCampCommitteeList(String campID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);

		Collection<Student> studentList = cmemberDB.getCampCommMembers(c1);
		

		return sortByStudentNameIDList(studentList);
	}

	/**
	 * sorts a list of camps alphabetically by name
	 * @param campList
	 * @return List of campID sorted alphabetically by camp name 
	 */
	private List<String> sortByCampNameIDList(Collection<Camp> campList) {
		Collections.sort((ArrayList<Camp>)campList, Comparator.comparing(Camp::getName));
		ArrayList<String> campIDList = new ArrayList<String>();
		for (Camp c: campList)
		{
			campIDList.add(c.getID());
		}
		return campIDList;
	}

	/**
	 * sorts a list of students alphabetically by name
	 * @param campList
	 * @return List of studentID sorted alphabetically by student name 
	 */
	private List<String> sortByStudentNameIDList(Collection<Student> studentList) {
		Collections.sort((ArrayList<Student>)studentList, Comparator.comparing(Student::getName));
		ArrayList<String> userIDList = new ArrayList<String>();
		for (User u: studentList)
		{
			userIDList.add(u.getID());
		}
		return userIDList;
	}
	
}



