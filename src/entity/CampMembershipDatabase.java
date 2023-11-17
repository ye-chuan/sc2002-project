package entity;

import java.util.*;

/**
 * Handles the relationships between Student and Camps.
 * For querying Camp memberships details (i.e. role in camp, blacklists)
 */
public class CampMembershipDatabase {
    
    private Map<String, Map<String, CampMembership>> campStudentMemberships = new HashMap<String, Map<String, CampMembership>>();
    private Map<String, Set<String>> blacklist = new HashMap<String, Set<String>>();

    public int getParticipantSize(Camp camp) {
        return getParticipants(camp).size();
    }

    public int getCampCommSize(Camp camp) {
        return getCampCommMembers(camp).size();
    }

	public Collection<Camp> getCampsJoinedBy(Student student) {
        return getCampsJoinedBy(student.getID());
	}

	public Collection<Camp> getCampsJoinedBy(String studentID) {
        Collection<Camp> ret = new ArrayList<Camp>();
        for (Map<String, CampMembership> studentMemberships : campStudentMemberships.values()) {
            if (studentMemberships.containsKey(studentID))
                ret.add(studentMemberships.get(studentID).camp);
        }
        return ret;
	}

	public Collection<Student> getParticipants(Camp camp) {
        return getParticipants(camp.getID());
	}

	public Collection<Student> getParticipants(String campID) {
        return getStudentInCampByRole(campID, CampRole.PARTICIPANT);
	}

	public Collection<Student> getCampCommMembers(Camp camp) {
        return getCampCommMembers(camp.getID());
	}

	public Collection<Student> getCampCommMembers(String campID) {
        return getStudentInCampByRole(campID, CampRole.CAMPCOMM);
	}

	public void addCampCommMember(Student student, Camp camp) {
        assert camp.getParticipantSlots() > getParticipantSize(camp);
        add(new CampMembership(student, camp, CampRole.CAMPCOMM));
	}

	public void addParticipant(Student student, Camp camp) {
        assert camp.getCampCommSlots() > getCampCommSize(camp);
        add(new CampMembership(student, camp, CampRole.PARTICIPANT));
	}

	public void removeParticipant(Student student, Camp camp) {
        String campID = camp.getID();
        String studentID = student.getID();
        
        if (!blacklist.containsKey(campID))
            blacklist.put(campID, new HashSet<String>());
        blacklist.get(campID).add(studentID);

        remove(new CampMembership(student, camp, CampRole.PARTICIPANT));
	}

    public Collection<String> getBlacklistedID(String campID) {
        return blacklist.containsKey(campID) ? blacklist.get(campID) : new HashSet<String>();
    }

    /**
     * Get role of a {@code Student} in a {@code Camp}
     * 
     * @return Role of student in a camp. If camp doesn't exists or student not a member of the camp, then null
     */
    public CampRole getRoleInCamp(Camp camp, Student student) {
        return getRoleInCamp(camp.getID(), student.getID());
    }

    /**
     * Get role of a {@code Student} in a {@code Camp}
     * 
     * @return Role of student in a camp. If camp doesn't exists or student not a member of the camp, then null
     */
    public CampRole getRoleInCamp(String campID, String studentID) {
        Map<String, CampMembership> studentMemberships = campStudentMemberships.get(campID);
        if (studentMemberships == null)
            return null;
        CampMembership membership = studentMemberships.get(studentID);
        if (membership == null)
            return null;
        return membership.role;
    }
    
    // Might extend from Database with these methods

    private void add(CampMembership membership) {
        String campID = membership.camp.getID();
        String studentID = membership.student.getID();
        if (!campStudentMemberships.containsKey(campID)) {
            campStudentMemberships.put(campID, new HashMap<String, CampMembership>());
        }
        campStudentMemberships.get(campID).put(studentID, membership);
    }

    private void remove(CampMembership membership) {
        String campID = membership.camp.getID();
        String studentID = membership.student.getID();
        if (campStudentMemberships.get(campID).get(studentID).role == membership.role)
            campStudentMemberships.get(campID).remove(studentID);
    }

	private Collection<Student> getStudentInCampByRole(String campID, CampRole role) {
        Collection<Student> ret = new ArrayList<Student>();
        Map<String, CampMembership> studentMemberships = campStudentMemberships.get(campID);
        for (CampMembership membership : studentMemberships.values()) {
            assert membership.camp.getID().equals(campID);
            if (membership.role == role) {
                ret.add(membership.student);
            }
        }
        return ret;
	}

}