package entity;

public class CampMembership {

	Student student;
	Camp camp;
	CampRole role;

    public CampMembership(Student student, Camp camp, CampRole role) {
        this.student = student;
        this.camp = camp;
        this.role = role;
    }

}
