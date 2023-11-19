


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import entity.Camp;
import entity.CampDatabase;
import entity.CampMembershipDatabase;
import entity.Date;
import entity.Faculty;
import entity.Student;
import entity.UserDatabase;
import entity.CampDatabase.Query;

public class CampListController {

	/**
	 * 
	 * @param userID
	 */
	public Collection<String> getCreatedCamps(String userID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Query query = new Query();

		query.onlyCampsBy(userID);

		Iterator<Camp> cIterator = cDB.getCamps(query).iterator();
		Collection<String> campIDList = new ArrayList<String>();

		while (cIterator.hasNext()) {
			Camp c1 = cIterator.next();
			campIDList.add(c1.getID());
		}
		
		return campIDList;


	}

	/**
	 * 
	 * @param userID
	 */
	public Collection<String> getJoinedCamps(String userID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		UserDatabase uDB = new UserDatabase();
		Student student1 = (Student) uDB.getItem(userID);
		
		Iterator<Camp> cIterator = cmemberDB.getCampsJoinedBy(student1).iterator();
		Collection<String> campIDList = new ArrayList<String>();

		while (cIterator.hasNext()) {
			Camp c1 = cIterator.next();
			campIDList.add(c1.getID());
		}
		
		return campIDList;

		
	}

	/**
	 * 
	 * @param userID
	 */
	public Collection<String> getAllCamps(String userID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Query query = new Query();

		query.excludeInvisible();
		query.registrationAfterIncl(Date.today());
		query.campDatesAllAfterIncl(Date.today());
		query.excludeFullCampCommSlots();
		query.excludeFullParticipantSlot();

		Iterator<Camp> cIterator = cDB.getCamps(query).iterator();
		Collection<String> campIDList = new ArrayList<String>();

		while (cIterator.hasNext()) {
			Camp c1 = cIterator.next();
			campIDList.add(c1.getID());
		}
		
		return campIDList;
	}

	/**
	 * 
	 * @param userID
	 */
	public Collection<String> getAvailableCamps(String userID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		UserDatabase uDB = new UserDatabase();
		Query query = new Query();
		Student s1 = (Student) uDB.getItem(userID);
		Faculty s1Faculty = s1.getFaculty();

		query.excludeInvisible();
		query.registrationAfterIncl(Date.today());
		query.campDatesAllAfterIncl(Date.today());
		query.excludeFullCampCommSlots();
		query.excludeFullParticipantSlot();
		query.onlyOpenToFaculty(s1Faculty);

		Iterator<Camp> cIterator = cDB.getCamps(query).iterator();
		Collection<String> campIDList = new ArrayList<String>();

		while (cIterator.hasNext()) {
			Camp c1 = cIterator.next();
			campIDList.add(c1.getID());
		}
		
		return campIDList;
	}

	/**
	 * 
	 * @param location
	 * @param openParticipantSlots
	 * @param openCommSlots
	 * @param date
	 */
	public Collection<String> FilterBy(String location, boolean openParticipantSlots, boolean openCommSlots, Date date, Faculty faculty, boolean visibility) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Query query = new Query();
		
		if (openCommSlots) query.excludeFullCampCommSlots();
		if (openParticipantSlots) query.excludeFullParticipantSlot();
		query.registrationAfterIncl(Date.today());
		query.onlyOpenToFaculty(faculty);
		if(!visibility) query.excludeVisible();

		Iterator<Camp> cIterator = cDB.getCamps(query).iterator();
		Collection<String> campIDList = new ArrayList<String>();

		while (cIterator.hasNext()) {
			Camp c1 = cIterator.next();
			if (c1.getLocation() == location) {
				campIDList.add(c1.getID());
			}
		}
		
		return campIDList;
	}

}