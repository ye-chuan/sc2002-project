import java.util.Iterator;

import entity.Camp;
import entity.CampDatabase;
import entity.CampMembershipDatabase;
import entity.Date;
import entity.Student;
import entity.UserDatabase;

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
		UserDatabase uDB = new UserDatabase();
		Student s1 = (Student) uDB.getItem(studentID);
		
		s1.addPoints(points);
		return s1.getPoints();
	}

	/**
	 * 
	 * @param studentID
	 */
	public void updatePoints(String studentID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		UserDatabase uDB = new UserDatabase();
		String campID = userCont.getStudentCommitteeCampID(studentID);
		Camp c1 = cDB.getItem(campID);
		Student s1 = (Student) uDB.getItem(studentID);

		Iterator<Date> cIterator = c1.getDates().iterator();
		while (cIterator.hasNext()) {
			Date cDate = cIterator.next();
			if (!cDate.isBefore(Date.today())) {
				s1.resetPoints();
			}
		}
	}
	
}