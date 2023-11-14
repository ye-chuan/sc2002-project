package entity;

public class Student extends User {

	private int points;

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
