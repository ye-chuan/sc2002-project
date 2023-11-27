package scs3grp5.controller;

import java.util.*;

import scs3grp5.Main;
import scs3grp5.entity.*;
import scs3grp5.entity.Date;

/**
 * Manages camps in the system
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 * 
 */
public class CampController {

	private StaffCampController staffCampCont = new StaffCampController();
	private StudentCampController studentCampCont = new StudentCampController();

	/**
	 * 
	 * Registers student as Participant
	 * @param userID The unique ID of the User
	 * @param campID The unique ID of the Camp
	 * @see StudentCampController#registerAsParticipant(String, String)
	 * @throws RegistrationException if fail to register
	 */
	public void registerAsParticipant(String userID,String campID) throws RegistrationException {
		studentCampCont.registerAsParticipant(userID,campID);
	}

	/**
	 * 
	 * Registers student as Camp Committee Member
	 * @param userID The unique ID of the User
	 * @param campID The unique ID of the Camp
	 * @see StudentCampController#registerAsCommittee(String, String)
	 * @throws RegistrationException if fail to register
	 */
	public void registerAsCommittee(String userID,String campID) throws RegistrationException {
		studentCampCont.registerAsCommittee(userID,campID);
	}

	/**
	 * Withdraws student from Camp, blacklists student from rejoining
	 * @param userID The unique ID of the User
	 * @param campID The unique ID of the Camp
	 * @see StudentCampController#withdraw(String, String)
	 */
	public void withdraw(String userID, String campID) {
		studentCampCont.withdraw(userID, campID);
	}

	/**
	 * Creates new Camp using StaffID
	 * @param staffInChargeID The unique ID of the staff in charge of camp
	 * @return campID of created camp 
	 * @see StudentCampController#withdraw(String, String)
	 */
	public String create(String staffInChargeID) {
		return staffCampCont.create(staffInChargeID);
	}

	/**
	 * Deletes Camp from Database
	 * @param campID The unique ID of the Camp
	 * @see StaffCampController#delete(String)
	 * @throws EditCampException when students are registered to the camp
	 */
	public void delete(String campID) throws EditCampException {
		staffCampCont.delete(campID);
	}


	/**
	 * Changes Name of Camp
	 * @param campID The unique ID of the Camp
	 * @param newName The new name of the Camp
	 * @throws EditCampException if camp name is not unique or length of camp name is too short
	 * @see StaffCampController#changeName(String, String)
	 */
	public void changeName(String campID, String newName) throws EditCampException{
		staffCampCont.changeName(campID, newName);
	}

	/**
	 * toggles the visibility of the camp
	 * @param campID The unique ID of the Camp
	 * @param visibility The boolean visibility of the camp
	 * @throws EditCampException if camp already has students registered
	 * @see StaffCampController#toggleVisibility(String, boolean)
	 */
	public void toggleVisibility(String campID, boolean visibility) throws EditCampException {
		staffCampCont.toggleVisibility(campID, visibility);
	}


	/**
	 * Change start Date of Camp
	 * @param campID The unique ID of the Camp
	 * @param date The start Date of the Camp
	 * @throws InvalidDateException 
	 * if start date does not follow order of Today -> Registration Closing Date -> Start Date -> End Date
	 * @see StaffCampController#changeStartDate(String, String)
	 */
	public void changeStartDate(String campID, String date) throws InvalidDateException {
		staffCampCont.changeStartDate(campID, date);
	}

	/**
	 * Change end date of camp
	 * @param campID The unique ID of the Camp
	 * @param date The start Date of the Camp
	 * @throws InvalidDateException 
	 * if end date does not follow order of Today -> Registration Closing Date -> Start Date -> End Date
	 * @see StaffCampController#changeEndDate(String, String)
	 */
	public void changeEndDate(String campID, String date) throws InvalidDateException {
		staffCampCont.changeEndDate(campID, date);
	}

	/**
	 * Change registration closing date of camp
	 * @param campID The unique ID of the Camp
	 * @param date The start Date of the Camp
	 * @throws InvalidDateException 
	 * if closing date does not follow order of Today -> Registration Closing Date -> Start Date -> End Date
	 * @see StaffCampController#changeClosingDate(String, String)
	 */
	public void changeClosingDate(String campID, String date ) throws InvalidDateException {
		staffCampCont.changeClosingDate(campID, date);		
	}

	/**
	 * Changes location of camp
	 * @param campID The unique ID of the Camp
	 * @param location The location string of the Camp
	 * @throws EditCampException if camp location is blank
	 * @see StaffCampController#changeLocation(String, String)
	 */
	public void changeLocation(String campID, String location) throws EditCampException {
		staffCampCont.changeLocation(campID, location);
	}

	/**
	 * Change description of camp
	 * @param campID The unique ID of the Camp
	 * @param description The description of the Camp
	 * @throws EditCampException if camp description is blank
	 * @see StaffCampController#changeDescription(String, String)
	 */
	public void changeDescription(String campID, String description) throws EditCampException{
		staffCampCont.changeDescription(campID, description);
	}

	/**
	 * Allows staff to change faculty of camp between own faculty and NTU <p>
	 * Checks whether camp contains students from other faculties when open to staff faculty instead of whole NTU
	 * @param staffID The unique ID of the Staff
	 * @param campID The unique ID of the Camp
	 * @param openToFacultyOnly boolean value indicating whether Camp is open to Faculty only or whole of NTU
	 * @throws EditCampException if camp currently contains students not from staff faculty
	 * @see StaffCampController#changeFaculty(String, String, boolean)
	 */
	public void changeFaculty(String staffID, String campID, boolean openToFacultyOnly) throws EditCampException {
		staffCampCont.changeFaculty(staffID, campID, openToFacultyOnly);
	}

	/**
	 * Change camp committee slots for camp <p>
	 * checks for error when downsizing the camp committee slots
	 * @param campID The unique ID of the Camp
	 * @param slots The number of Camp Committee slots set
	 * @throws EditCampException if camp committee slots not within range 1-10, <p>
	 * and slots smaller than number of camp committee members registered
	 * @see StaffCampController#changeCampCommSlots(String, int)
	 */
	public void changeCampCommSlots(String campID, int slots) throws EditCampException {
		staffCampCont.changeCampCommSlots(campID, slots);
	}

	/**
	 * Change camp partcipant slots for camp <p>
	 * Checks for error when downsizing the camp participant slots
	 * @param campID The unique ID of the Camp
	 * @param slots The number of Camp Participant slots set
	 * @throws EditCampException if camp participant slots is not at least 1 <p>
	 * and slots smaller than number of camp participant members registered
	 * @see StaffCampController#changeCampParticipantSlots(String, int)
	 */
	public void changeCampParticipantSlots(String campID, int slots) throws EditCampException {
		staffCampCont.changeCampParticipantSlots(campID, slots);
	}

	/**
	 * Get name of camp using CampID
	 * @param campID The unique ID of the Camp
	 * @return Name of the Camp
	 */
	public String getName(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		return c1.getName();
	}

	/**
	 * Get visibility of camp
	 * @param campID The unique ID of the Camp
	 * @return true if visible, false if not visible
	 */
	public boolean getVisibility(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		return c1.isVisible();
	}

	/**
	 * get start date and end date in formatted string
	 * @param campID The unique ID of the Camp
	 * @return formatted string of "dd/mm/yy-dd/mm/yy"
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

	/**
	 * Get location of camp
	 * @param campID The unique ID of the Camp
	 * @return location of camp
	 */
	public String getLocation(String campID) {
		CampDatabase cDB = Main.getCampDB();
		Camp c1 = cDB.getItem(campID);
		return c1.getLocation();
	}

	/**
	 * Get faculty of camp
	 * @param campID The unique ID of the Camp
	 * @return faculty of camp: open to faculty or NTU
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
	}

	/**
	 * get description of camp
	 * @param campID The unique ID of the Camp
	 * @return description of camp
	 */
	public String getDescription(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);

		return c1.getDescription();
	}

	/**
	 * get total camp committee slots in camp
	 * @param campID The unique ID of the Camp
	 * @return int of total camp committee slots
	 */
	public int getTotalCampCommSlots(String campID) {
		CampDatabase cDB = Main.getCampDB();
		

		Camp c1 = cDB.getItem(campID);

		return c1.getCampCommSlots();

	}

	/**
	 * get remaining camp committee slots in camp
	 * @param campID The unique ID of the Camp
	 * @return int of remaining camp committee slots
	 */
	public int getRemainingCampCommSlots(String campID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);

		return c1.getCampCommSlots() - cmemberDB.getCampCommSize(c1);

	}

	/**
	 * get total participant slots in camp
	 * @param campID The unique ID of the Camp
	 * @return int of total participant slots
	 */
	public int getTotalParticipantSlots(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);

		return c1.getParticipantSlots();

		
	}

	/**
	 * get remaining participant slots in camp
	 * @param campID The unique ID of the Camp
	 * @return int of reamining participant slots
	 */
	public int getRemainingParticipantSlots(String campID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		
	
		return c1.getParticipantSlots() - cmemberDB.getParticipantSize(c1);
		
	}

	/**
	 * get registration closing date in camp
	 * @param campID The unique ID of the Camp
	 * @return formatted string of close date
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
	 * get staff user who created the camp
	 * @param campID The unique ID of the Camp
	 * @return Camp-In-Charge name
	 */
	public String getCampInCharge(String campID) {
	
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		return c1.getStaffInCharge().getName();
	}

	/**
	 * Checks whether Registration Closing Date is past today
	 * @param campID The unique ID of the Camp
	 * @return true if camp is over
	 */
	public boolean isCampOver(String campID) {
		Date close = Main.getCampDB().getItem(campID).getClosingDate();
		
		if (close.isBefore(Date.today())) 
			return true;
		else
			return false;
		
	}

	/**
	 * checks whether user is a camp committee or staff in charge of camp
	 * @param userID The unique ID of the User
	 * @param campID The unique ID of the Camp
	 * @return true if user is a camp committee member or staff in charge of camp
	 */
	public boolean isCommittee(String userID, String campID) {
		
		CampDatabase cDB = Main.getCampDB();
		
		UserDatabase uDB = Main.getUserDB();
		Camp c1 = cDB.getItem(campID);
		User u1 = uDB.getItem(userID);
			
		if (u1 instanceof Staff) {
			
			Staff s1 = (Staff) uDB.getItem(userID);
			
			if (c1.getStaffInCharge().equals(s1)){
				
				return true;
			}
			else {
				
				return false;
			}
			
		}
		
		else if (u1 instanceof Student) {
			
			CampRole userRole = getUserStatus(campID, userID);
			if (userRole == CampRole.CAMPCOMM) {
				
				return true;
			}
			else {
				
				return false;
			}
				
		}

		else {
			
			return false;
		}
		
	}

	/**
	 * checks whether student is a camp participant or camp committee in camp
	 * @param campID The unique ID of the Camp
	 * @param studentID The unique ID of the Student
	 * @return Camp Role of the student in the camp specified
	 */
	public CampRole getUserStatus(String campID, String studentID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		CampRole s1Role;
		
		UserDatabase uDB = Main.getUserDB();
		Camp c1 = cDB.getItem(campID);
		User u1 = uDB.getItem(studentID);
		if(u1 instanceof Student) {
			Student s1 = (Student) u1;
			s1Role = cmemberDB.getRoleInCamp(c1,s1);
			return s1Role;
		}
		else if (u1 instanceof Staff) {
			s1Role = null;
		}
		else s1Role = null;

		return s1Role;
	}

	
}


