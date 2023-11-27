package scs3grp5.entity;

/**
 * Represents a Staff in school
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class Student extends User {
    /** The number of points this Student has */
	private int points;
	
    /**
     * Constructs a Student
     * @param userID The unique ID of this student
     * @param password The password of this student
     * @param faculty The Faculty that this student is in
     * @param email The email of this student
     * @param name The name of this student
     */
    public Student(String userID, String password, Faculty faculty, String email, String name) {
        super(userID, password, faculty, email, name);
    }

    /**
     * @return The points of this student
     */
	public int getPoints() {
        return this.points;
	}

	/**
	 * Get the points that this student has
	 * @param points The amount of points to add
     * @return points that the student has */
    public boolean addPoints(int points) {
        this.points += points;
        return true;
	}

	/** Reset the points that this student has to 0 */
    public void resetPoints() {
        this.points = 0;
	}

}
