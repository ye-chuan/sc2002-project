package entity;

import java.io.Serializable;

public class CampMembership implements Serializable {

	Student student;
	Camp camp;
	CampRole role;

    public CampMembership(Student student, Camp camp, CampRole role) {
        this.student = student;
        this.camp = camp;
        this.role = role;
    }

}
