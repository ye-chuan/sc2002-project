package scs3grp5.entity;

import java.io.Serializable;

/**
 * Represents the Membership association between a {@code Student} and a {@code Camp}
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampMembership implements Serializable {

     /** student The student that is registering */
	Student student;
     /** camp The camp that the student is registering for */
	Camp camp;
     /** role The role that the student registered for (e.g. Participants / Camp Committee) */
	CampRole role;

    /**
    /* Constructs a CampMembership object to represent one association between Student and Camp
     * @param student The student that is registering
     * @param camp The camp that the student is registering for
     * @param role The role that the student registered for (e.g. Participants / Camp Committee)
     */
    public CampMembership(Student student, Camp camp, CampRole role) {
        this.student = student;
        this.camp = camp;
        this.role = role;
    }

}
