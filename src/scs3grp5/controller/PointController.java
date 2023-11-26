package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages student points in the system
 * 
 */
public class PointController {
	/**
	 * add 2 points to suggester/camp committee student
	 * @param studentID
	 */
	public void approveSuggestion(String studentID) {
		addPoints(studentID, 2);
	}

	/**
	 * add 1 points to suggester/camp committee student
	 * @param studentID
	 */
	public void rejectSuggestion(String studentID) {
		addPoints(studentID, 1);
	}

	/**
	 * add 1 points to camp committee student for replying
	 * @param studentID
	 */
	public void replyEnquiry(String studentID) {
		addPoints(studentID, 1);
	}

	/**
	 * add points to student
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
	 * update points of student
	 * @param studentID
	 */
	public void updatePoints(String studentID) {
		CampDatabase cDB = Main.getCampDB();
		UserDatabase uDB = Main.getUserDB();
		UserController userCont = new UserController();
		String campID = userCont.getStudentCommitteeCampID(studentID);
		Camp c1 = cDB.getItem(campID);
		Student s1 = (Student) uDB.getItem(studentID);
	
		if(c1!=null) {
			Date cDate = c1.getLastCampDate();
			
			if (!cDate.isBefore(Date.today())) {
				s1.resetPoints();
			}
		}
		else s1.resetPoints();
	}
		
	
	
}