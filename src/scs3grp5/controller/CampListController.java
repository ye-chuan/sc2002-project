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
	 * View Camp List<p>
	 * and stores filtered list in class
	 * For User student: returns camps joined by student <p>
	 * For User staff: returns camps created by staff
	 * @param userID
	 * @return campID List of camps joined or created depending on user type, <p> sorted alphabetically by name of camp
	 */
	public List<String> viewMyCamp(String userID) {
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
		
		return sortByCampNameIDList(campList);


	}

	/**
	 * Set default filter for viewing all camps after today<p>
	 * and stores the filtered list in the class
	 * For User student: set filter visible, open to their faculty, and has remaining slots in camp
	 * 
	 * @param userID
	 * @return set filter of available camps depending on user type
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
	 * provides a filtered list of camps based on filter set.
	 * @return list of campID sorted alphabetical by camp name
	 */
	public List<String> viewCamps() {
		
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



