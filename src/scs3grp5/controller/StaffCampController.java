package scs3grp5.controller;

import java.util.Collection;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages staff actions for camps in the system
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class StaffCampController {


    /**
	 * Creates new Camp using StaffID
	 * @param staffInChargeID The unique ID of the Staff in Charge of Camp
	 * @return campID of created camp 
	 * 
	 */
    public String create(String staffInChargeID) {
		UserDatabase uDB = Main.getUserDB();
		CampDatabase cDB = Main.getCampDB();
		User u1 = uDB.getItem(staffInChargeID);
		Staff s1;
		Camp newCamp;
		if (u1 instanceof Staff) {
			s1 = (Staff) u1;
			newCamp = new Camp(s1);
			cDB.add(newCamp);
			return newCamp.getID();
		}
		else return null;
			
	}

    /**
	 * Deletes Camp from Database
	 * @param campID The unique ID of the Camp
	 * @throws EditCampException when students are registered to the camp
	 */
	public void delete(String campID) throws EditCampException {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
			Camp c1 = cDB.getItem(campID);
			if(cmemberDB.getParticipantSize(c1)>0||cmemberDB.getCampCommSize(c1)>0) {
				throw new EditCampException("Unable to delete: There are students registered to the camp.");
			}
			else {
				cDB.remove(c1);
			}

	}

    /**
	 * Changes Name of Camp
	 * @param campID The unique ID of the Camp
	 * @param newName The new name of the Camp
	 * @throws EditCampException if camp name is not unique or length of camp name is too short
	 * 
	 */
	public void changeName(String campID, String newName) throws EditCampException{
		CampDatabase cDB = Main.getCampDB();

		if (newName.length()<1) {
			throw new EditCampException("Length of Camp Name < 1");
		}

		Camp c1 = cDB.getItem(campID);
		
		Collection<Camp> campList = cDB.getAll();
		for (Camp c: campList) {
			if(c.getName().equals(newName))
			throw new EditCampException("Camp Name already existed");
		}
			
		c1.setName(newName);

	}

	/**
	 * toggles the visibility of the camp
	 * @param campID The unique ID of the Camp
	 * @param visibility The visibility of the camp
	 * @throws EditCampException if camp already has students registered
	 * 
	 */
	public void toggleVisibility(String campID, boolean visibility) throws EditCampException {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
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
	 * Change start Date of Camp
	 * @param campID The unique ID of the Camp
	 * @param date The start date of the Camp
	 * @throws InvalidDateException 
	 * if start date does not follow order of Today -> Registration Closing Date -> Start Date -> End Date
	 * 
	 */
	public void changeStartDate(String campID, String date) throws InvalidDateException {
		CampDatabase cDB = Main.getCampDB();

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
	 * Change end date of camp
	 * @param campID The unique ID of the Camp
	 * @param date The end date of the Camp
	 * @throws InvalidDateException 
	 * if end date does not follow order of Today -> Registration Closing Date -> Start Date -> End Date
	 */
	public void changeEndDate(String campID, String date) throws InvalidDateException {

		CampDatabase cDB = Main.getCampDB();

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
			if (end.isBefore(start)) {
				throw new InvalidDateException("End date must after start date");
			}
			c1.setDates(start,end);
		}

	}


	/**
	 * Changes location of camp
	 * @param campID The unique ID of the Camp
	 * @param location The new location of the Camp
	 * @throws EditCampException if camp location is blank
	 * 
	 */
	public void changeLocation(String campID, String location) throws EditCampException {
		CampDatabase cDB = Main.getCampDB();
		
		if (location.length()<1) {
			throw new EditCampException("Length of Location must be over 1");
		}
		Camp c1 = cDB.getItem(campID);
		c1.setLocation(location);

	}

	/**
	 * Change description of camp
	 * @param campID The unique ID of the Camp
	 * @param description The new description for the Camp
	 * @throws EditCampException if camp description is blank
	 * 
	 */
	public void changeDescription(String campID, String description) throws EditCampException{
		CampDatabase cDB = Main.getCampDB();
		
		if (description.length()<1) {
			throw new EditCampException("Length of Description must be over 1");
		}
		Camp c1 = cDB.getItem(campID);
		c1.setDescription(description);
	}

	/**
	 * Allows staff to change faculty of camp between own faculty and NTU <p>
	 * Checks whether camp contains students from other faculties when open to staff faculty instead of whole NTU
	 * @param campID The unique ID for the Camp
	 * @param staffID The unique ID for the Staff
	 * @param openToFacultyOnly boolean value of whether camp is open to faculty only or open to NTU
	 * @throws EditCampException if camp currently contains students not from staff faculty
	 * 
	 */
	public void changeFaculty(String staffID, String campID, boolean openToFacultyOnly) throws EditCampException {
		CampDatabase cDB = Main.getCampDB();
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
		UserDatabase uDB = Main.getUserDB();
		Faculty sFaculty = uDB.getItem(staffID).getFaculty();

		Camp c1 = cDB.getItem(campID);

		//check participants == faculty
		if (openToFacultyOnly) {
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
	 * Change camp committee slots for camp <p>
	 * checks for error when downsizing the camp committee slots
	 * @param campID The unique ID of the Camp
	 * @param slots The total number of Camp Committee slots
	 * @throws EditCampException if camp committee slots not within range 1-10, <p>
	 * and slots smaller than number of camp committee members registered
	 * 
	 */
	public void changeCampCommSlots(String campID, int slots) throws EditCampException {
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		if	(cMemberDB.getCampCommSize(c1) > slots) {
			throw new EditCampException("slots must be greater than camp comm size.");
		}
		if	(slots<1) {
			throw new EditCampException("slots must be at least 1.");
		}
		if	(slots>10) {
			throw new EditCampException("maximum camp comm slots is 10.");
		}

		c1.setCampCommSlots(slots);
	}

	/**
	 * Change camp partcipant slots for camp <p>
	 * Checks for error when downsizing the camp participant slots
	 * @param campID The unique ID of the Camp
	 * @param slots The total number of Camp Participant slots
	 * @throws EditCampException if camp participant slots is not at least 1 <p>
	 * and slots smaller than number of camp participant members registered
	 * 
	 */
	public void changeCampParticipantSlots(String campID, int slots) throws EditCampException {
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		
		Camp c1 = cDB.getItem(campID);
		if	(cMemberDB.getCampCommSize(c1) > slots) {
			throw new EditCampException("slots must be greater than participant size.");
		}
		if	(slots<1) {
			throw new EditCampException("slots must be at least 1.");
		}
		c1.setParticipantSlots(slots);
		
	}

	/**
	 * Change registration closing date of camp
	 * @param campID The unique ID of the Camp
	 * @param date The closing registration date
	 * @throws InvalidDateException 
	 * if closing date does not follow order of Today -> Registration Closing Date -> Start Date -> End Date
	 * 
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
}
