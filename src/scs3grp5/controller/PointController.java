package scs3grp5.controller;


import scs3grp5.Main;
import scs3grp5.entity.*;

public class PointController {

	private UserController userCont;
	/**
	 * 
	 * @param studentID
	 */
	public void approveSuggestion(String studentID) {
		addPoints(studentID, 2);
	}

	/**
	 * 
	 * @param studentID
	 */
	public void rejectSuggestion(String studentID) {
		addPoints(studentID, 1);
	}

	/**
	 * 
	 * @param studentID
	 */
	public void replyEnquiry(String studentID) {
		addPoints(studentID, 1);
	}

	/**
	 * 
	 * @param studentID
	 * @param points
	 */
	private int addPoints(String studentID, int points) {
		UserDatabase uDB = Main.getUserDB();
		Student s1 = (Student) uDB.getItem(studentID);
		
		s1.addPoints(points);
		return s1.getPoints();
	}

	/**
	 * 
	 * @param studentID
	 */
	public void updatePoints(String studentID) {
		CampDatabase cDB = Main.getCampDB();
		UserDatabase uDB = Main.getUserDB();
		String campID = userCont.getStudentCommitteeCampID(studentID);
		Camp c1 = cDB.getItem(campID);
		Student s1 = (Student) uDB.getItem(studentID);

		Date cDate = c1.getLastCampDate();
		
		if (!cDate.isBefore(Date.today())) {
			s1.resetPoints();
		}
		
	}
	
}