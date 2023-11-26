package scs3grp5.controller;

import java.util.Collection;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages student actions for camps in the system
 * 
 */
public class StudentCampController {
	
	CampController campCont = new CampController();
    
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
			if (Date.today().isAfter(c1.getClosingDate())) {
				throw new RegistrationException("Registration is closed");
			}
			if (cmemberDB.getBlacklistedID(campID).contains(userID)) {
				throw new RegistrationException("Blacklisted: unable to rejoin camp");
			}
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

			if (Date.today().isAfter(c1.getClosingDate())) {
				throw new RegistrationException("Registration is closed");
			}
			if (campCont.getRemainingCampCommSlots(campID) <= 0) {
				throw new RegistrationException("There are no remaining slots");
			}
			if (isExistingCampComm(c1, s1)) {
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
	}

	/**
	 * 
	 * @param camp
	 * @param student
	 */
	private boolean overlapDates(Camp camp, Student student) {
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
	 * @param camp
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
