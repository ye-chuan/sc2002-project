package scs3grp5.entity;

import java.io.Serializable;

/**
 * Represents the Membership association between a {@code Student} and a {@code Camp}
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampMembership implements Serializable {

	Student student;
	Camp camp;
	CampRole role;

    /** Constructs a CampMembership object to represent one association between Student and Camp */
    public CampMembership(Student student, Camp camp, CampRole role) {
        this.student = student;
        this.camp = camp;
        this.role = role;
    }

}
