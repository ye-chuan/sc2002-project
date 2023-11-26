package scs3grp5.controller;

import java.util.*;

import scs3grp5.Main;
import scs3grp5.entity.*;
import scs3grp5.entity.Date;

/**
 * Manages camps in the system
 * 
 */
public class CampController {

	private StaffCampController staffCampCont = new StaffCampController();
	private StudentCampController studentCampCont = new StudentCampController();

	/**
	 * 
	 * Registers student as Participant
	 * @param campID
	 * @param userID studentID
	 * @see StudentCampController#registerAsParticipant(String, String)
	 */
	public void registerAsParticipant(String userID,String campID) throws RegistrationException {
		studentCampCont.registerAsParticipant(userID,campID);
	}

	/**
	 * 
	 * Registers student as Camp Committee Member
	 * @param campID
	 * @param userID
	 * @see StudentCampController#registerAsCommittee(String, String)
	 */
	public void registerAsCommittee(String userID,String campID) throws RegistrationException {
		studentCampCont.registerAsCommittee(userID,campID);
	}

	/**
	 * Withdraws student from Camp, blacklists student from rejoining
	 * @param userID
	 * @param campID
	 * @see StudentCampController#withdraw(String, String)
	 */
	public void withdraw(String userID, String campID) {
		studentCampCont.withdraw(userID, campID);
	}

	/**
	 * Creates new Camp using StaffID
	 * @param staffInCharge ID
	 * @return campID of created camp 
	 * @see StudentCampController#withdraw(String, String)
	 */
	public String create(String staffInChargeID) {
		return staffCampCont.create(staffInChargeID);
	}

	/**
	 * Deletes Camp from Database
	 * @param campID
	 * @see StaffCampController#delete(String)
	 */
	public void delete(String campID) throws EditCampException {
		staffCampCont.delete(campID);
	}


	/**
	 * Changes Name of Camp
	 * @param campID
	 * @param newName
	 * @throws EditCampException if camp name is not unique or length of camp name is too short
	 * @see StaffCampController#changeName(String, String)
	 */
	public void changeName(String campID, String newName) throws EditCampException{
		staffCampCont.changeName(campID, newName);
	}

	/**
	 * toggles the visibility of the camp
	 * @param campID
	 * @param visibility
	 * @throws EditCampException if camp already has students registered
	 * @see StaffCampController#toggleVisibility(String, boolean)
	 */
	public void toggleVisibility(String campID, boolean visibility) throws EditCampException {
		staffCampCont.toggleVisibility(campID, visibility);
	}


	/**
	 * Change start Date of Camp
	 * @param campID
	 * @param date
	 * @throws InvalidDateException 
	 * if start date does not follow order of Today -> Registration Closing Date -> Start Date -> End Date
	 * @see StaffCampController#changeStartDate(String, String)
	 */
	public void changeStartDate(String campID, String date) throws InvalidDateException {
		staffCampCont.changeStartDate(campID, date);
	}

	/**
	 * Change end date of camp
	 * @param campID
	 * @param date
	 * @throws InvalidDateException 
	 * if end date does not follow order of Today -> Registration Closing Date -> Start Date -> End Date
	 * @see StaffCampController#changeEndDate(String, String)
	 */
	public void changeEndDate(String campID, String date) throws InvalidDateException {
		staffCampCont.changeEndDate(campID, date);
	}

	/**
	 * Change registration closing date of camp
	 * @param campID
	 * @param date
	 * @throws InvalidDateException 
	 * if closing date does not follow order of Today -> Registration Closing Date -> Start Date -> End Date
	 * @see StaffCampController#changeClosingDate(String, String)
	 */
	public void changeClosingDate(String campID, String date ) throws InvalidDateException {
		staffCampCont.changeClosingDate(campID, date);		
	}

	/**
	 * Changes location of camp
	 * @param campID
	 * @param location
	 * @throws EditCampException if camp location is blank
	 * @see StaffCampController#changeLocation(String, String)
	 */
	public void changeLocation(String campID, String location) throws EditCampException {
		staffCampCont.changeLocation(campID, location);
	}

	/**
	 * Change description of camp
	 * @param campID
	 * @param description
	 * @throws EditCampException if camp description is blank
	 * @see StaffCampController#changeDescription(String, String)
	 */
	public void changeDescription(String campID, String description) throws EditCampException{
		staffCampCont.changeDescription(campID, description);
	}

	/**
	 * Allows staff to change faculty of camp between own faculty and NTU <p>
	 * Checks whether camp contains students from other faculties when open to staff faculty instead of whole NTU
	 * @param campID
	 * @param String
	 * @param openToFacultyOnly
	 * @throws EditCampException if camp currently contains students not from staff faculty
	 * @see StaffCampController#changeFaculty(String, String, boolean)
	 */
	public void changeFaculty(String staffID, String campID, boolean openToFacultyOnly) throws EditCampException {
		staffCampCont.changeFaculty(staffID, campID, openToFacultyOnly);
	}

	/**
	 * Change camp committee slots for camp <p>
	 * checks for error when downsizing the camp committee slots
	 * @param campID
	 * @param slots
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
	 * @param campID
	 * @param slots
	 * @throws EditCampException if camp participant slots is not at least 1 <p>
	 * and slots smaller than number of camp participant members registered
	 * @see StaffCampController#changeCampParticipantSlots(String, int)
	 */
	public void changeCampParticipantSlots(String campID, int slots) throws EditCampException {
		staffCampCont.changeCampParticipantSlots(campID, slots);
	}

	/**
	 * Get name of camp using CampID
	 * @param campID
	 */
	public String getName(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		return c1.getName();
	}

	/**
	 * Get visibility of camp
	 * @param campID
	 * @return true if visible, false if not visible
	 */
	public boolean getVisibility(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		return c1.isVisible();
	}

	/**
	 * get start date and end date in formatted string
	 * @param campID
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
	 * @param campID
	 * @return location of camp
	 */
	public String getLocation(String campID) {
		CampDatabase cDB = Main.getCampDB();
		Camp c1 = cDB.getItem(campID);
		return c1.getLocation();
	}

	/**
	 * Get faculty of camp
	 * @param campID
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
	 * @param campID
	 * @return description of camp
	 */
	public String getDescription(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);

		return c1.getDescription();
	}

	/**
	 * get total camp committee slots in camp
	 * @param campID
	 * @return int of total camp committee slots
	 */
	public int getTotalCampCommSlots(String campID) {
		CampDatabase cDB = Main.getCampDB();
		

		Camp c1 = cDB.getItem(campID);

		return c1.getCampCommSlots();

	}

	/**
	 * get remaining camp committee slots in camp
	 * @param campID
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
	 * @param campID
	 * @return int of total participant slots
	 */
	public int getTotalParticipantSlots(String campID) {
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);

		return c1.getParticipantSlots();

		
	}

	/**
	 * get remaining participant slots in camp
	 * @param campID
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
	 * @param campID
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
	 * @param campID
	 * @return Camp-In-Charge name
	 */
	public String getCampInCharge(String campID) {
	
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		return c1.getStaffInCharge().getName();
	}

	/**
	 * Checks whether Registration Closing Date is past today
	 * @param campID
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
	 * @param userID
	 * @param campID
	 * @return true if user is a camp committee member or staff in charge of camp
	 */
	public boolean isCommittee(String userID, String campID) {
		System.out.println("campID"+campID);
		CampDatabase cDB = Main.getCampDB();
		System.out.println(cDB.getItem(campID).getName());
		UserDatabase uDB = Main.getUserDB();
		Camp c1 = cDB.getItem(campID);
		User u1 = uDB.getItem(userID);
			System.out.println("AAAAA");
		if (u1 instanceof Staff) {
			System.out.println("BBBBB111");
			Staff s1 = (Staff) uDB.getItem(userID);
			System.out.println("BBBBB222");
			System.out.println(c1.getStaffInCharge() + " " +  s1);
			System.out.println(c1.getStaffInCharge().hashCode() + " " +  s1.hashCode());
			if (c1.getStaffInCharge().equals(s1)){
				System.out.println("BBBBB");
				System.out.println("i come here");
				return true;
			}
			else {
				System.out.println("CCCCC");
				return false;
			}
			
		}
		
		else if (u1 instanceof Student) {
			System.out.println("DDDDD");
			CampRole userRole = getUserStatus(campID, userID);
			if (userRole == CampRole.CAMPCOMM) {
				System.out.println("EEEEE");
				return true;
			}
			else {
				System.out.println("GGGGG");
				return false;
			}
				
		}

		else {
			System.out.println("HHHHH"); 
			return false;
		}
		
	}

	/**
	 * checks whether student is a camp participant or camp committee in camp
	 * @param campID
	 * @param studentID
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


