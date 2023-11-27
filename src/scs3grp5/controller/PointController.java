package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages student points in the system
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class PointController {
	/**
	 * add 2 points to suggester/camp committee student
	 * @param studentID The unique ID of the Student
	 */
	public void approveSuggestion(String studentID) {
		addPoints(studentID, 2);
	}

	/**
	 * add 1 points to suggester/camp committee student
	 * @param studentID The unique ID of the Student
	 */
	public void rejectSuggestion(String studentID) {
		addPoints(studentID, 1);
	}

	/**
	 * add 1 points to camp committee student for replying
	 * @param studentID The unique ID of the Student
	 */
	public void replyEnquiry(String studentID) {
		addPoints(studentID, 1);
	}

	/**
	 * add points to student
	 * @param studentID The unique ID of the Student
	 * @param points The number of points to be added to Student profile
	 */
	private int addPoints(String studentID, int points) {
		UserDatabase uDB = Main.getUserDB();
		Student s1 = (Student) uDB.getItem(studentID);
		
		s1.addPoints(points);
		return s1.getPoints();
	}

	/**
	 * update points of student
	 * @param studentID The unique ID of the Student
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