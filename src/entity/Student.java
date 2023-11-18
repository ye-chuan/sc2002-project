package entity;

public class Student extends User {

	private int points;
	
    public Student(String userID, String password, Faculty faculty, String email, String name) {
        super(userID, password, faculty, email, name);
    }

	public int getPoints() {
        return this.points;
	}

	/**
	 * Get the points that this student has
	 * @param points
     * @return points that the student has */
    public boolean addPoints(int points) {
        this.points += points;
        return true;
	}

}
