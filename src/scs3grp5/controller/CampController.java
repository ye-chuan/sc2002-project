package scs3grp5.controller;

import java.util.*;

import scs3grp5.Main;
import scs3grp5.entity.*;
import scs3grp5.entity.Date;


public class CampController {

	/**
	 * 
	 * @param campID
	 * @param userID
	 * registers student as Participant
	 */
	public void registerAsParticipant(String campID, String userID) throws RegistrationException {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		UserDatabase uDB = Main.getUserDB();
		Camp c1 = cDB.getItem(campID);
		User u1 = uDB.getItem(userID);
		if (u1 instanceof Staff){
			throw new RegistrationException("Not a student.");
		} 
		else {
			Student s1 = (Student) u1;
		
			// Faculty s1Faculty = u1.getFaculty();
			
			// 1 Check Sign Up Date
			// 2 Check Faculty
			// 3 Check Blacklist
			// 4 Check Remaining Slots
			// 5 Check Overlap Dates
			if (Date.today().isAfter(c1.getClosingDate())) {
				throw new RegistrationException("Registration is closed");
			}
			// if (!c1.getOpenTo().contains(s1Faculty)) {
			// 	throw new Exception("Camp not open to {s1Faculty}");
			// }
			if (cmemberDB.getBlacklistedID(campID).contains(userID)) {
				throw new RegistrationException("Blacklisted: unable to rejoin camp");
			}
			if (getRemainingParticipantSlots(campID) <= 0) {
				throw new RegistrationException("There are no remaining slots");
			}
			if (overlapDates(c1, s1))
			{
				throw new RegistrationException("Camp dates clashed with camps registered");
			}
			cmemberDB.addParticipant(s1,c1);
		}
	}

	/**
	 * 
	 * @param campID
	 * @param userID
	 * registers student as camp committee
	 */
	public void registerAsCommittee(String campID, String userID) throws RegistrationException {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		UserDatabase uDB = Main.getUserDB();
		Camp c1 = cDB.getItem(campID);
		User u1 = uDB.getItem(userID);

		if (u1 instanceof Staff) {
			throw new RegistrationException("Not a student");
		}
		else {
			Student s1 = (Student) u1;
			// Faculty s1Faculty = s1.getFaculty();
			boolean existingCampComm = false;

			Iterator<Camp> cIterator = cmemberDB.getCampsJoinedBy(s1).iterator();

			while (cIterator.hasNext()) {
				Camp c2Camp = cIterator.next();
				if (cmemberDB.getCampCommMembers(c2Camp).contains(s1)) {
					existingCampComm = true;
				}
			}
			
			// 1 Check Sign Up Date
			// 2 Check Faculty
			// 3 Check Remaining Slots
			// 4 Check Existing Camp Comm
			// 4 Check Overlap Dates

			if (Date.today().isAfter(c1.getClosingDate())) {
				throw new RegistrationException("Registration is closed");
			}
			// if (!c1.getOpenTo().contains(s1Faculty)) {
			// 	throw new Exception("Camp not open to {s1Faculty}");
			// }
			if (getRemainingCampCommSlots(campID) <= 0) {
				throw new RegistrationException("There are no remaining slots");
			}
			if (existingCampComm == true) {
				throw new RegistrationException("Already registered as a camp comm for another camp");
			}
			if (overlapDates(c1, s1)) {
				throw new RegistrationException("Camp dates clashed with camps registered");
			}
			cmemberDB.addParticipant(s1,c1);
		}
	}

	/**
	 * 
	 * @param userID
	 * @param campID
	 */
	public void withdraw(String userID, String campID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		UserDatabase uDB = Main.getUserDB();
		Camp c1 = cDB.getItem(campID);
		User u1 = uDB.getItem(userID);
		Student s1;


		if (u1 instanceof Student) {
			s1 = (Student) u1;
			cmemberDB.removeParticipant(s1,c1);
		}
		// if (u1 instanceof Staff) {
		// 	throw new Exception("Not a student");
		// }
		// else {

		// 	if (cmemberDB.getCampCommMembers(c1).contains(s1)) {
		// 		throw new Exception("Unable to withdraw camp as a camp committee");
		// 	}

		// 	if (!cmemberDB.getParticipants(c1).contains(s1) && !cmemberDB.getCampCommMembers(c1).contains(s1)) {
		// 		throw new Exception("Unable to find participant");
		// 	}
		// 	else {
				
		// 	}	
		// }
	}

	public String create(String staffInChargeID) {
		UserDatabase uDB = Main.getUserDB();
		CampDatabase cDB = Main.getCampDB();
		User u1 = uDB.getItem(staffInChargeID);
		Staff s1;
		Camp newCamp;
		if (u1 instanceof Staff) {
			s1 = (Staff) u1;
			newCamp = new Camp(Main.getMemberDB(), s1);
			cDB.add(newCamp);
			return newCamp.getID();
		}
		else return null;
			
	}

	/**
	 * 
	 * @param userID
	 * @param campID
	 * checks whether user is a camp comm or staff in charge
	 * change all setter functions to remove logic checking
	 */
	public boolean isCommittee(String userID, String campID) {
		// CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		UserDatabase uDB = Main.getUserDB();
		Camp c1 = cDB.getItem(campID);
		User u1 = uDB.getItem(userID);
		
		if (u1 instanceof Staff) {
			Staff s1 = (Staff) uDB.getItem(userID);

			if (c1.getStaffInCharge() == s1) 
				return true;
			else 
				return false;
		}
		
		// change to use getStatus function
		else if (u1 instanceof Student) {
			CampRole userRole = getUserStatus(campID, userID);
			if (userRole == CampRole.CAMPCOMM)
				return true;
			else 
				return false;
		}

		else return false;
		
	}

	/**
	 * 
	 * @param userID
	 * @param campID
	 */
	public void delete(String campID) throws EditCampException {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		// if (!IsEditable(userID, campID)) {
		// 	throw new Exception("No permission to edit");
		// }
		// else {
			Camp c1 = cDB.getItem(campID);
			if(cmemberDB.getParticipantSize(c1)>0||cmemberDB.getCampCommSize(c1)>0) {
				throw new EditCampException("Unable to delete: There are students registered to the camp.");
			}
			else {
				cDB.remove(c1);
			}
		// }

	}


	/**
	 * 
	 * @param campID
	 * @param newName
	 */
	public void changeName(String campID, String newName) throws EditCampException{
		CampDatabase cDB = Main.getCampDB();
		
		// if (!IsEditable(staffID, campID)) {
		// 	throw new Exception("No permission to edit");
		// }
		// else {
			// check minimum character
			if (newName.length()<1) {
				throw new EditCampException("Length of Camp Name < 1");
			}

			Camp c1 = cDB.getItem(campID);
			
			Collection<Camp> campList = cDB.getAll();
			for (Camp c: campList) {
				if(c.getName()==newName)
				throw new EditCampException("Camp Name already existed");
			}
			
			c1.setName(newName);
		// }
	}

	/**
	 * 
	 * @param campID
	 * @param visibility
	 */
	public void toggleVisibility(String campID, boolean visibility) throws EditCampException {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
		// if (!IsEditable(staffID, campID)) {
		// 	throw new Exception("No permission to edit");
		// }
		// else {
			Camp c1 = cDB.getItem(campID);
			if(cmemberDB.getParticipantSize(c1)>0||cmemberDB.getCampCommSize(c1)>0) {
				throw new EditCampException("Unable to toggle: There are students registered to the camp.");
			}
			else {
				if(visibility) c1.show();
				else c1.hide();
			}
		
	}


	/**
	 * 
	 * @param campID
	 * @param dates
	 */
	public void changeStartDate(String campID, String date) throws InvalidDateException {
		CampDatabase cDB = Main.getCampDB();
		
		// if (!IsEditable(staffID, campID)) {
		// 	throw new Exception("No permission to edit");
		// }
		// else {
			Camp c1 = cDB.getItem(campID);
			
			Date start = Date.fromString(date);
			if (start.isBefore(Date.today())) {
				throw new InvalidDateException("Start date must be after today");
			}
			
			DateRange dates = c1.getDates();
			if(dates==null) {
				c1.setDates(start,start);
			}
			else {
				Date end = dates.getEnd();
				Date close = c1.getClosingDate();
				if (close==null) {
						if (!start.isBefore(end)) {
							throw new InvalidDateException("Start date must be before end date");
						}
				}
				else if (!(start.isBefore(end) && start.isAfter(close))) {
					throw new InvalidDateException("Start date must be after closing date, before end date");
				}
				
			c1.setDates(start,end);
			}

	}

	/**
	 * 
	 * @param campID
	 * @param dates
	 */
	public void changeEndDate(String campID, String date) throws InvalidDateException {

		CampDatabase cDB = Main.getCampDB();

		// if (!IsEditable(staffID, campID)) {
		// 	throw new Exception("No permission to edit");
		// }
		// else {
			Camp c1 = cDB.getItem(campID);
			
			Date end = Date.fromString(date);
			if (end.isBefore(Date.today())) {
				throw new InvalidDateException("End date must be after today");
			}
			DateRange dates = c1.getDates();
			if(dates==null) {
				c1.setDates(end,end);
			}
			else {
				Date start = dates.getStart();
				if (!end.isBefore(start)) {
					throw new InvalidDateException("End date must after start date");
				}
				c1.setDates(start,end);
			}

	}


	/**
	 * 
	 * @param campID
	 * @param String
	 */
	public void changeLocation(String campID, String location) throws EditCampException {
		CampDatabase cDB = Main.getCampDB();
		
		// if (!IsEditable(staffID, campID)) {
		// 	throw new EditCampException("No permission to edit");
		// }
		// else {
			if (location.length()<1) {
				throw new EditCampException("Length of Location must be over 1");
			}
			Camp c1 = cDB.getItem(campID);
			c1.setLocation(location);
		// }
		//check string >=1 letter, not blank
	}

	/**
	 * 
	 * @param campID
	 * @param String
	 */
	public void changeDescription(String campID, String description) throws EditCampException{
		CampDatabase cDB = Main.getCampDB();
		
		// if (!IsEditable(staffID, campID)) {
		// 	throw new Exception("No permission to edit");
		// }
		// else {
			if (description.length()<1) {
				throw new EditCampException("Length of Description must be over 1");
			}
			Camp c1 = cDB.getItem(campID);
			c1.setDescription(description);
		// }
		//check minimum character 
	}

	/**
	 * 
	 * @param campID
	 * @param String
	 */
	public void changeFaculty(String staffID, String campID, boolean OpenToFacultyOnly) throws EditCampException {
		CampDatabase cDB = Main.getCampDB();
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
		UserDatabase uDB = Main.getUserDB();
		Faculty sFaculty = uDB.getItem(staffID).getFaculty();

		Camp c1 = cDB.getItem(campID);

		//check participants == faculty
			if (OpenToFacultyOnly) {
				//get participants
				for (Student s: cMemberDB.getCampCommMembers(campID)) {
					if (s.getFaculty()!= sFaculty) {
						throw new EditCampException("Particpants are from other faculties");
					}
				}
				for (Student s: cMemberDB.getParticipants(campID)) {
					if (s.getFaculty()!= sFaculty) {
						throw new EditCampException("Particpants are from other faculties");
					}
				}
				c1.openToFaculty(sFaculty);
			}
			else {
				c1.openToNTU();
			}

		
	}

	/**
	 * 
	 * @param campID
	 * @param slots
	 */
	public void changeCampCommSlots(String campID, int slots) throws EditCampException {
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
		// if (!IsEditable(staffID, campID)) {
		// 	throw new Exception("No permission to edit");
		// }
		// else {
		
			Camp c1 = cDB.getItem(campID);
			if	(cMemberDB.getCampCommSize(c1)< slots) {
				throw new EditCampException("slots must be greater than camp comm size.");
			}
			if	(slots<1) {
				throw new EditCampException("slots must be at least 1.");
			}
			if	(slots>10) {
				throw new EditCampException("maximum camp comm slots is 10.");
			}

			c1.setCampCommSlots(slots);
		// }
		//check positive number, not more than 10
		//check number of slots >= number of registered
	}

	/**
	 * 
	 * @param campID
	 * @param slots
	 */
	public void changeCampParticipantSlots(String campID, int slots) throws EditCampException {
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
		// if (!IsEditable(staffID, campID)) {
		// 	throw new Exception("No permission to edit");
		// }
		// else {
			Camp c1 = cDB.getItem(campID);
			if	(cMemberDB.getCampCommSize(c1)< slots) {
				throw new EditCampException("slots must be greater than camp comm size.");
			}
			if	(slots<1) {
				throw new EditCampException("slots must be at least 1.");
			}
		// }
		
		//check number of slots >= number of registered
		
	}

	/**
	 * 
	 * @param campID
	 * @param dates
	 */
	public void changeClosingDate(String campID, String date ) throws InvalidDateException {
		CampDatabase cDB = Main.getCampDB();
		
			Camp c1 = cDB.getItem(campID);

			Date close = Date.fromString(date);
			if (close.isBefore(Date.today())) {
				throw new InvalidDateException("Closing date must be after today");
			}
			DateRange dates = c1.getDates();
			if(dates==null) {
				c1.setClosingDate(close);
			}
			else {
				Date start = dates.getStart();
				if (close.isAfter(start)) {
					throw new InvalidDateException("Closing date must be before start date");
				}
				c1.setClosingDate(close);
			}
				
	}

	/**
	 * 
	 * @param campID
	 */
	public String getName(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		return c1.getName();
	}

	/**
	 * 
	 * @param campID
	 */
	public boolean getVisibility(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		return c1.isVisible();
	}

	/**
	 * 
	 * @param campID
	 */
	public String getDate(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		if(c1.getDates()==null){
			return null;
		}
		Date start = c1.getDates().getStart();
		Date end = c1.getDates().getEnd();
	
		return String.format("%s-%s", start.toString(), end.toString());
	}

	// /**
	//  * 
	//  * @param campID
	//  */
	// public String getEndDate(String campID) {
	// 	CampMembershipDatabase cmemberDB = Main.getMemberDB();
	// 	CampDatabase cDB = Main.getCampDB();
		
	// 	Camp c1 = cDB.getItem(campID);
	// 	Date end = c1.getDates().getEnd();
	// 	return String.format("%d/%d/%d",end.getDayOfMonth(),end.getMonth(),end.getYear());
	// }

	/**
	 * 
	 * @param campID
	 */
	public String getLocation(String campID) {
		CampDatabase cDB = Main.getCampDB();
		Camp c1 = cDB.getItem(campID);
		return c1.getLocation();
	}

	/**
	 * 
	 * @param campID
	 */
	public String getFaculty(String campID) {
		CampDatabase cDB = Main.getCampDB();
		StringJoiner facList = new StringJoiner(", ");

		
		Camp c1 = cDB.getItem(campID);
		if(c1.getOpenTo().size()==0) {
			return null;
		}

		if (c1.getOpenTo().size() == Faculty.values().length) {
			return "NTU";
		}
		else {
			for(Faculty faculty : c1.getOpenTo()) {
				facList = facList.add(faculty.toString());
			}

			return facList.toString();
		}
		// .append(),, return arraylist<string>
	}

	/**
	 * 
	 * @param campID
	 */
	public String getDescription(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);

		return c1.getDescription();
	}

	/**
	 * 
	 * @param campID
	 */
	public int getTotalCampCommSlots(String campID) {
		CampDatabase cDB = Main.getCampDB();
		

		Camp c1 = cDB.getItem(campID);

		return c1.getCampCommSlots();

	}

	/**
	 * 
	 * @param campID
	 */
	public int getRemainingCampCommSlots(String campID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);

		return c1.getCampCommSlots() - cmemberDB.getCampCommSize(c1);

	}

	/**
	 * 
	 * @param campID
	 */
	public int getTotalParticipantSlots(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);

		return c1.getParticipantSlots();

		
	}

	/**
	 * 
	 * @param campID
	 */
	public int getRemainingParticipantSlots(String campID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		
	
		return c1.getParticipantSlots() - cmemberDB.getParticipantSize(c1);
		
	}

	/**
	 * 
	 * @param campID
	 */
	public String getClosingDate(String campID) {
	
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		if (c1.getClosingDate()==null) {
			return null;
		}
		else 
			return c1.getClosingDate().toString();
	}

	/**
	 * 
	 * @param campID
	 */
	public String getCampInCharge(String campID) {
	
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		return c1.getStaffInCharge().getID();
	}

	/**
	 * 
	 * @param campID
	 */
	public List<String> getCampParticipants(String campID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);

		Collection<Student> studentList = cmemberDB.getParticipants(c1);
		

		return sortByNameIDList(studentList);
	}

	
	/**
	 * 
	 * @param camp
	 * @param student
	 */
	public boolean overlapDates(Camp camp, Student student) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		
		Collection<Camp> campList = cmemberDB.getCampsJoinedBy(student);
		DateRange registerDateRange = camp.getDates();

		for (Camp c: campList) {
			if (c.getDates().overlaps(registerDateRange)) {
				return false;
			}
		}
		return true;
		
	}

	/**
	 * 
	 * @param campID
	 * Checks whether Registration Closing Date is past today
	 */
	public boolean isCampOver(String campID) {
		Date close = Main.getCampDB().getItem(campID).getClosingDate();
		
		if (close.isBefore(Date.today())) 
			return true;
		else
			return false;
		
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
		return null;
		

	}

	/**
	 * 
	 * @param campList
	 */
	private List<String> sortByNameIDList(Collection<Student> studentList) {
		Collections.sort((ArrayList<Student>)studentList, Comparator.comparing(Student::getName));
		ArrayList<String> userIDList = new ArrayList<String>();
		for (User u: studentList)
		{
			userIDList.add(u.getID());
		}
		return userIDList;
	}
}


