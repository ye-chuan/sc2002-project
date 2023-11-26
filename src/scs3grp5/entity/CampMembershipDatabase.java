package scs3grp5.entity;

import java.io.Serializable;
import java.util.*;

/**
 * Handles the all relationships ({@code CampMembership}) between all Students and all Camps.
 * For querying Camp memberships details (i.e. role in camp, blacklists)
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampMembershipDatabase implements Serializable{
    /**
     * {@code CampMembership} stored in a Map structure for faster access.
     *
     * Entries are <CampID: Map<StudentID: CampMembership>>
     */
    private Map<String, Map<String, CampMembership>> campStudentMemberships = new HashMap<String, Map<String, CampMembership>>();

    /**
     * Mapping of Students blacklisted from certain camps.
     *
     * Entries Map<CampID: Set<StudentID>>
     */
    private Map<String, Set<String>> blacklist = new HashMap<String, Set<String>>();

    /**
     * Get number of participants currently in a camp
     * @param camp The camp in question
     * @return Number of participants currently in this camp
     */
    public int getParticipantSize(Camp camp) {
        return getParticipants(camp).size();
    }

    /**
     * Get number of camp committee memebers currently in a camp
     * @param camp The camp in question
     * @return Number of camp committee members currently in this camp
     */
    public int getCampCommSize(Camp camp) {
        return getCampCommMembers(camp).size();
    }

    /**
     * Get the Camps joined by a certain student
     * @param student The student in question
     * @return The Camps joined by a certain student
     */
	public Collection<Camp> getCampsJoinedBy(Student student) {
        return getCampsJoinedBy(student.getID());
	}

    /**
     * Get the Camps joined by a certain student
     * @param studentID The ID of the student in question
     * @return The Camps joined by a certain student
     */
	public Collection<Camp> getCampsJoinedBy(String studentID) {
        Collection<Camp> ret = new ArrayList<Camp>();
        for (Map<String, CampMembership> studentMemberships : campStudentMemberships.values()) {
            if (studentMemberships.containsKey(studentID))
                ret.add(studentMemberships.get(studentID).camp);
        }
        return ret;
	}

    /**
     * Get the participants of a camp
     * @param camp The camp in question
     * @return The participants of a camp
     */
	public Collection<Student> getParticipants(Camp camp) {
        return getParticipants(camp.getID());
	}

    
    /**
     * Get the participants of a camp
     * @param campID ID of the camp
     * @return The participants of a camp
     * Get the participants of a camp
     */
	public Collection<Student> getParticipants(String campID) {
        return getStudentInCampByRole(campID, CampRole.PARTICIPANT);
	}

    /**
     * Get the camp committee members of a camp
     * @param camp The camp to search for
     * @return The camp committee members of a camp
     */
	public Collection<Student> getCampCommMembers(Camp camp) {
        return getCampCommMembers(camp.getID());
	}

	/**
     * Get the camp committee members of a camp
     * @param campID ID of the camp to search for
     * @return The camp committee members of a camp
	 */
	public Collection<Student> getCampCommMembers(String campID) {
        return getStudentInCampByRole(campID, CampRole.CAMPCOMM);
	}

	public void addCampCommMember(Student student, Camp camp) {
        assert camp.getParticipantSlots() > getParticipantSize(camp);
        add(new CampMembership(student, camp, CampRole.CAMPCOMM));
	}

    /**
     * Find the Camp that this Student is a CampCommittee of (there should only be 1 active camp).
     *
     * Defaults to being with respect to the system's "Today" date
     * @param student The specified student
     *
     * @return The camp that the student is currently a Camp Committee of
     *
     * @see #getCampWithCampCommMember(Student, Date)
     */
    public Camp getCampWithCampCommMember(Student student) {
        return getCampWithCampCommMember(student, Date.today());
    }

    /**
     * Find the Camp that this Student is a CampCommittee of (there should only be 1 active camp).
     *
     * @param student The specified student
     * @param today Camps that ended before this date will not be considered
     *
     * @return The camp that the student is currently a Camp Committee of
     */
    public Camp getCampWithCampCommMember(Student student, Date today) {
        Camp ret = null;
        Collection<Camp> campsWithStudent = getCampsJoinedBy(student);
        for (Camp camp : campsWithStudent) {
            if (camp.getLastCampDate().isBefore(today)) { // Do not consider camps that are over
                continue;
            }

            if (getRoleInCamp(camp, student) == CampRole.CAMPCOMM) {
                assert ret == null : "Student is Camp Comm in Multiple Camps!";
                ret = camp;
            }
        }
        return ret;
    }

    /**
     * Add a participant to this camp
     * @param student The student participant to register
     * @param camp The camp to register the student to
     */
	public void addParticipant(Student student, Camp camp) {
        assert camp.getCampCommSlots() > getCampCommSize(camp);
        add(new CampMembership(student, camp, CampRole.PARTICIPANT));
	}

    /**
     * Removes Participant from Camp and add them into the Blacklist
     * 
     * @param student The participant to remove
     * @param camp The camp to remove the participant from 
     * @return Whether the removal was successful (unsuccessful if Student is a Camp Committee member)
     */
	public boolean removeParticipant(Student student, Camp camp) {
        String campID = camp.getID();
        String studentID = student.getID();
        
        if (!blacklist.containsKey(campID))
            blacklist.put(campID, new HashSet<String>());
        blacklist.get(campID).add(studentID);

        if (getRoleInCamp(camp, student) != CampRole.PARTICIPANT) {
            return false;
        }
        remove(new CampMembership(student, camp, CampRole.PARTICIPANT));
        return true;
	}

    /**
     * Get the IDs of the blacklisted student of a camp
     * @param campID The campID of the camp to check
     * @return The IDs of the blacklisted student of a camp
     */
    public Collection<String> getBlacklistedID(String campID) {
        return blacklist.containsKey(campID) ? blacklist.get(campID) : new HashSet<String>();
    }

    /**
     * Get role of a {@code Student} in a {@code Camp}
     * 
     * @param camp The camp to search the student in
     * @param student The student to search for
     * @return Role of student in a camp. If camp doesn't exists or student not a member of the camp, then null
     */
    public CampRole getRoleInCamp(Camp camp, Student student) {
        return getRoleInCamp(camp.getID(), student.getID());
    }

    /**
     * Get role of a {@code Student} in a {@code Camp}
     * 
     * @param campID The ID of the camp to search the student in
     * @param studentID The ID of the student to search for
     * @return Role of student in a camp. If camp doesn't exists or student not a member of the camp, then null
     */
    public CampRole getRoleInCamp(String campID, String studentID) {
        Map<String, CampMembership> studentMemberships = getStudentMembershipsInCamp(campID);
        if (studentMemberships == null)
            return null;
        CampMembership membership = studentMemberships.get(studentID);
        if (membership == null)
            return null;
        return membership.role;
    }
    
    // Might extend from Database with these methods

    /**
     * Adds a CampMembership (an association between a student and a camp) to this Database
     */
    private void add(CampMembership membership) {
        String campID = membership.camp.getID();
        String studentID = membership.student.getID();
        if (!campStudentMemberships.containsKey(campID)) {
            campStudentMemberships.put(campID, new HashMap<String, CampMembership>());
        }
        getStudentMembershipsInCamp(campID).put(studentID, membership);
    }

    /**
     * Removes a CampMembership (an association between a student and a camp) to this Database
     */
    private void remove(CampMembership membership) {
        String campID = membership.camp.getID();
        String studentID = membership.student.getID();
        if (getStudentMembershipsInCamp(campID).get(studentID).role == membership.role)
            getStudentMembershipsInCamp(campID).remove(studentID);
    }

    /**
     * @param campID The ID of the camp
     * @return Get an internal mapping of Student-Membership
     */
    private Map<String, CampMembership> getStudentMembershipsInCamp(String campID) {
        if (campStudentMemberships.containsKey(campID)) {
            return campStudentMemberships.get(campID);
        }
        return new HashMap<String, CampMembership>();
    }

    /**
     * Students that is of a certain role (participant/camp committee) in a camp
     * @param campID ID of the camp in question
     * @param role The student's which this role
     * @return Students that is of a certain role (participant/camp committee) in a camp
     */
	private Collection<Student> getStudentInCampByRole(String campID, CampRole role) {
        Collection<Student> ret = new ArrayList<Student>();
        Map<String, CampMembership> studentMemberships = getStudentMembershipsInCamp(campID);
        for (CampMembership membership : studentMemberships.values()) {
            assert membership.camp.getID().equals(campID);
            if (membership.role == role) {
                ret.add(membership.student);
            }
        }
        return ret;
	}

}
