package scs3grp5.controller;

import java.util.Collection;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages student actions for camps in the system
 * 
 */
public class StudentCampController {
	
	private CampController campCont;

    
    /**
	 * Registers student as Participant
	 * @param campID
	 * @param userID studentID
	 * 
	 */
	public void registerAsParticipant(String userID,String campID) throws RegistrationException {
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
			if (Date.today().isAfter(c1.getClosingDate())) {
				throw new RegistrationException("Registration is closed");
			}
			if (cmemberDB.getBlacklistedID(campID).contains(userID)) {
				throw new RegistrationException("Blacklisted: unable to rejoin camp");
			}
			campCont = new CampController();
			if (campCont.getRemainingParticipantSlots(campID) <= 0) {
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
	 * Registers student as Camp Committee Member
	 * @param campID
	 * @param userID
	 * 
	 */
	public void registerAsCommittee(String userID,String campID) throws RegistrationException {
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

			if (Date.today().isAfter(c1.getClosingDate())) {
				throw new RegistrationException("Registration is closed");
			}
			campCont = new CampController();
			if (campCont.getRemainingCampCommSlots(campID) <= 0) {
				throw new RegistrationException("There are no remaining slots");
			}
			if (isExistingCampComm(c1, s1)) {
				throw new RegistrationException("Already registered as a camp comm for another camp");
			}
			if (overlapDates(c1, s1)) {
				throw new RegistrationException("Camp dates clashed with camps registered");
			}
			cmemberDB.addCampCommMember(s1, c1);
		}
	}

	/**
	 * Withdraws student from Camp, blacklists student from rejoining
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
	}

	/**
	 * checks whether there is any conflicts in schedule for joining camp
	 * @param camp camp to register
	 * @param student
	 */
	private boolean overlapDates(Camp camp, Student student) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		
		Collection<Camp> campList = cmemberDB.getCampsJoinedBy(student);
		DateRange registerDateRange = camp.getDates();

		for (Camp c: campList) {
			if (c.getDates().overlaps(registerDateRange)) {
				return true;
			}
		}
		return false;
		
	}

    /**
	 * checks whether student is holding onto a camp committee position for another camp
	 * @param camp camp to register
	 * @param student
	 */
	private boolean isExistingCampComm(Camp camp, Student student) {
		
        CampMembershipDatabase cmemberDB = Main.getMemberDB();
        Collection<Camp> campJoinList = cmemberDB.getCampsJoinedBy(student);

        for (Camp c: campJoinList) {
            if (cmemberDB.getCampCommMembers(c).contains(student)) {
                return true;
            }
        }
        return false;
		
	}
}
