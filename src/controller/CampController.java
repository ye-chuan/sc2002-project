import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import entity.Camp;
import entity.CampDatabase;
import entity.CampMembershipDatabase;
import entity.CampRole;
import entity.Date;
import entity.Faculty;
import entity.Staff;
import entity.Student;

import entity.UserDatabase;

public class CampController {

	/**
	 * 
	 * @param campID
	 */
	public boolean isValidCampID(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		if (cDB.getItem(campID)== null) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * 
	 * @param campID
	 * @param userID
	 */
	public void registerAsParticipant(String campID, String userID) throws Exception {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		UserDatabase uDB = new UserDatabase();
		Camp c1 = cDB.getItem(campID);
		Student s1 = (Student) uDB.getItem(userID);
		Faculty s1Faculty = s1.getFaculty();
		
		// 1 Check Sign Up Date
		// 2 Check Faculty
		// 3 Check Blacklist
		// 4 Check Remaining Slots
		// 5 Check Overlap Dates
		if (Date.today().isAfter(c1.getClosingDate())) {
			throw new Exception("Registration is closed");
		}
		if (!c1.getOpenTo().contains(s1Faculty)) {
			throw new Exception("Camp not open to {s1Faculty}");
		}
		if (cmemberDB.getBlacklistedID(campID).contains(userID)) {
			throw new Exception("Blacklisted: unable to rejoin camp");
		}
		if (getRemainingParticipantSlots(campID) <= 0) {
			throw new Exception("There are no remaining slots");
		}
		if (overlapDates(c1, s1))
		{
			throw new Exception("Camp dates clashed with camps registered");
		}
		cmemberDB.addParticipant(s1,c1);
	}

	/**
	 * 
	 * @param campID
	 * @param userID
	 */
	public void registerAsCommittee(String campID, String userID) throws Exception {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		UserDatabase uDB = new UserDatabase();
		Camp c1 = cDB.getItem(campID);
		Student s1 = (Student) uDB.getItem(userID);
		Faculty s1Faculty = s1.getFaculty();
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
			throw new Exception("Registration is closed");
		}
		if (!c1.getOpenTo().contains(s1Faculty)) {
			throw new Exception("Camp not open to {s1Faculty}");
		}
		if (getRemainingCampCommSlots(campID) <= 0) {
			throw new Exception("There are no remaining slots");
		}
		if (existingCampComm == true) {
			throw new Exception("Already registered as a camp comm for another camp");
		}
		if (overlapDates(c1, s1)) {
			throw new Exception("Camp dates clashed with camps registered");
		}
		cmemberDB.addParticipant(s1,c1);
	}

	/**
	 * 
	 * @param userID
	 * @param campID
	 */
	public void withdraw(String userID, String campID) throws Exception{
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		UserDatabase uDB = new UserDatabase();
		Camp c1 = cDB.getItem(campID);
		Student s1 = (Student) uDB.getItem(userID);

		if (cmemberDB.getCampCommMembers(c1).contains(s1)) {
			throw new Exception("Unable to withdraw camp as a camp committee");
		}

		if (!cmemberDB.getParticipants(c1).contains(s1) && !cmemberDB.getCampCommMembers(c1).contains(s1)) {
			throw new Exception("Unable to find participant");
		}
		else {
			cmemberDB.removeParticipant(s1,c1);
		}	
	}

	public String create(String staffInChargeID) {
		UserDatabase uDB = new UserDatabase();
		Staff s1 = (Staff) uDB.getItem(staffInChargeID);
		Camp newCamp = new Camp(s1);
		return newCamp.getID();
			
	}

	/**
	 * 
	 * @param userID
	 * @param campID
	 */
	public boolean IsEditable(String userID, String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		UserDatabase uDB = new UserDatabase();
		Camp c1 = cDB.getItem(campID);
		Staff s1 = (Staff) uDB.getItem(userID);

		if (c1.getStaffInCharge() == s1) 
			return true;
		else 
			return false;
	}

	/**
	 * 
	 * @param userID
	 * @param campID
	 */
	public void delete(String userID, String campID) throws Exception {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		if (!IsEditable(userID, campID)) {
			throw new Exception("No permission to edit");
		}
		else {
			Camp c1 = cDB.getItem(campID);
			cDB.remove(c1);
		}

	}
// Invalid Function
	// /**
	//  * 
	//  * @param userID
	//  * @param campID
	//  */
	// public void changeCampInCharge(String userID, String newStaffID, String campID) {
	// 	CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
	// 	CampDatabase cDB = new CampDatabase(cmemberDB);
	// 	UserDatabase uDB = new UserDatabase();
	// 	if (!IsEditable(userID, campID)) {
	// 		throw new Exception("No permission to edit");
	// 	}
	// 	else {
	// 		Camp c1 = cDB.getItem(campID);
	// 		Staff s1 = (Staff) uDB.getItem(newStaffID);
	// 		c1.setStaffInCharge(s1);
	// 	}
	// }

	/**
	 * 
	 * @param campID
	 * @param newName
	 */
	public void changeName(String staffID, String campID, String newName) throws Exception{
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		if (!IsEditable(staffID, campID)) {
			throw new Exception("No permission to edit");
		}
		else {
			Camp c1 = cDB.getItem(campID);
			c1.setName(newName);
		}
	}

	/**
	 * 
	 * @param campID
	 * @param visibility
	 */
	public void toggleVisibility(String staffID, String campID, boolean visibility) throws Exception{
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		if (!IsEditable(staffID, campID)) {
			throw new Exception("No permission to edit");
		}
		else {
			Camp c1 = cDB.getItem(campID);
			if(visibility) c1.show();
			else c1.hide();
		}
	}

	/**
	 * 
	 * @param campID
	 * @param dates
	 */
	public void changeDates(String staffID, String campID, Collection<Date> dates) throws Exception{
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		if (!IsEditable(staffID, campID)) {
			throw new Exception("No permission to edit");
		}
		else {
			Camp c1 = cDB.getItem(campID);
			c1.setDates(dates);
		}
	}

	/**
	 * 
	 * @param campID
	 * @param String
	 */
	public void changeLocation(String staffID, String campID, String location) throws Exception {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		if (!IsEditable(staffID, campID)) {
			throw new Exception("No permission to edit");
		}
		else {
			Camp c1 = cDB.getItem(campID);
			c1.setLocation(location);
		}
	}

	/**
	 * 
	 * @param campID
	 * @param String
	 */
	public void changeDescription(String staffID, String campID, String description) throws Exception{
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		if (!IsEditable(staffID, campID)) {
			throw new Exception("No permission to edit");
		}
		else {
			Camp c1 = cDB.getItem(campID);
			c1.setDescription(description);
		}
	}

	/**
	 * 
	 * @param campID
	 * @param slots
	 */
	public void changeCampCommSlots(String staffID, String campID, int slots) throws Exception {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		if (!IsEditable(staffID, campID)) {
			throw new Exception("No permission to edit");
		}
		else {
			Camp c1 = cDB.getItem(campID);
			c1.setCampCommSlots(slots);
		}
	}

	/**
	 * 
	 * @param campID
	 * @param slots
	 */
	public void changeCampParticipantSlots(String staffID, String campID, int slots) throws Exception {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		if (!IsEditable(staffID, campID)) {
			throw new Exception("No permission to edit");
		}
		else {
			Camp c1 = cDB.getItem(campID);
			c1.setParticipantSlots(slots);
		}
	}

	/**
	 * 
	 * @param campID
	 * @param dates
	 */
	public void changeClosingDate(String staffID, String campID, Date dates) throws Exception{
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		if (!IsEditable(staffID, campID)) {
			throw new Exception("No permission to edit");
		}
		else {
			Camp c1 = cDB.getItem(campID);
			c1.setClosingDate(dates);
		}
	}

	/**
	 * 
	 * @param campID
	 */
	public String getName(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		return c1.getName();
	}

	/**
	 * 
	 * @param campID
	 */
	public boolean getVisibility(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		return c1.isVisible();
	}

	/**
	 * 
	 * @param campID
	 */
	public Collection<Date> getDates(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		return c1.getDates();
	}

	/**
	 * 
	 * @param campID
	 */
	public String getLocation(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		return c1.getLocation();
	}

	/**
	 * 
	 * @param campID
	 */
	public String getFaculty(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		
		if (c1.getOpenTo().size()==1)
		return c1.getOpenTo().iterator().next().toString();
		else return "ALL";
	}

	/**
	 * 
	 * @param campID
	 */
	public String getDescription(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		return c1.getDescription();
	}

	/**
	 * 
	 * @param campID
	 */
	public int getTotalCampCommSlots(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		return c1.getCampCommSlots();
	}

	/**
	 * 
	 * @param campID
	 */
	public int getRemainingCampCommSlots(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		return c1.getCampCommSlots() - cmemberDB.getCampCommSize(c1);
	}

	/**
	 * 
	 * @param campID
	 */
	public int getTotalParticipantSlots(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		return c1.getParticipantSlots();
	}

	/**
	 * 
	 * @param campID
	 */
	public int getRemainingParticipantSlots(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		return c1.getParticipantSlots() - cmemberDB.getParticipantSize(c1);
	}

	/**
	 * 
	 * @param campID
	 */
	public String getClosingDate(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		return c1.getClosingDate().toString();
	}

	/**
	 * 
	 * @param campID
	 */
	public String getCampInCharge(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		return c1.getStaffInCharge().getID();
	}

	/**
	 * 
	 * @param campID
	 */
	public Collection<String> getCampParticipants(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);

		Iterator<Student> sIterator = cmemberDB.getParticipants(c1).iterator();
		Collection<String> studentIDList = new ArrayList<String>();

		while (sIterator.hasNext()) {
			Student s1 = sIterator.next();
			studentIDList.add(s1.getID());
		}

		return studentIDList;
	}

	
	/**
	 * 
	 * @param camp
	 * @param student
	 */
	public boolean overlapDates(Camp camp, Student student) {
		// TODO - implement CampController.overlapDates
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		Iterator<Camp> cIterator = cmemberDB.getCampsJoinedBy(student).iterator();
		Collection<Date> studentNotFreeDates = new ArrayList<Date>();

		while (cIterator.hasNext()) {
			Camp c1 = cIterator.next();
			studentNotFreeDates.addAll(c1.getDates());
		}

		Iterator<Date> DateIterator = studentNotFreeDates.iterator();
		while (DateIterator.hasNext()) {
			Date campDates = DateIterator.next();
			if (studentNotFreeDates.contains(campDates)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param campID
	 */
	public String getUserStatus(String campID, String studentID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		UserDatabase uDB = new UserDatabase();
		Camp c1 = cDB.getItem(campID);
		Student s1 = (Student) uDB.getItem(studentID);
		CampRole s1Role = cmemberDB.getRoleInCamp(c1,s1);

		if (s1Role.equals(CampRole.CAMPCOMM)) {
			return "Camp Committee";
		}
		else if (s1Role.equals(CampRole.PARTICIPANT)) {
			return "Participant";
		}
		else return "";

	}

}


