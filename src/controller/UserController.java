
import java.util.Iterator;

import entity.Camp;
import entity.CampMembershipDatabase;
import entity.CampRole;
import entity.Faculty;
import entity.Staff;
import entity.Student;
import entity.User;
import entity.UserDatabase;

public class UserController {

	/**
	 * 
	 * @param userID
	 */
	public static boolean isFirstLogin(String password) {
		if (password == "password") return true;
		else return false;
	}

	/**
	 * 
	 * @param userID
	 * @param password
	 */
	public static void login(String userID, String password) throws Exception {
		UserDatabase uDB = new UserDatabase();
		try {
			uDB.login(userID,password);
		} catch (Exception e) {
			throw new Exception("Login Fail");
		}
	}

	/**
	 * 
	 * @param userID
	 * @param newPassword
	 */
	public static void changePassword(String userID, String newPassword) {
		UserDatabase uDB = new UserDatabase();
		User u1 = uDB.getItem(userID);
	
		if (u1.checkPassword(newPassword)) {
			u1.changePassword(newPassword);
		}

		else throw new IllegalArgumentException("Same password as old password");
		
	}

	/**
	 * 
	 * @param userID
	 */
	public static UserType getDomain(String userID) {
		UserDatabase uDB = new UserDatabase();
		User u1 = uDB.getItem(userID);
		if (u1 instanceof Student) return UserType.STUDENT;
		if (u1 instanceof Staff) return UserType.STAFF;
		else return null;
	}

	/**
	 * 
	 * @param userID
	 */
	public static int getPoints(String userID) {
		UserDatabase uDB = new UserDatabase();
		Student s1 = (Student) uDB.getItem(userID);
		PointController.updatePoints(userID);
		
		return s1.getPoints();
	}

	/**
	 * 
	 * @param userID
	 */
	public static String getStudentCommitteeCampID(String userID) {
		UserDatabase uDB = new UserDatabase();
		CampMembershipDatabase cMemberDB = new CampMembershipDatabase();
		Student s1 = (Student) uDB.getItem(userID);

		Iterator<Camp> cIterator = cMemberDB.getCampsJoinedBy(s1).iterator();

		while (cIterator.hasNext()) {
			Camp c1 = cIterator.next();
			CampRole studentRole = cMemberDB.getRoleInCamp(c1,s1);
			if (studentRole == CampRole.CAMPCOMM) {
				return c1.getID();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param newPassword
	 */
	public static boolean isStrongPassword(String newPassword) {
		if (newPassword.length() < 8) {
			return false;
		}
		else { 
			return true;
		}
	}

	/**
	 * 
	 * @param userID
	 */
	public static String getUserName(String userID) {
		UserDatabase uDB = new UserDatabase();
		Student s1 = (Student) uDB.getItem(userID);
		
		return s1.getEmail();
	}

	/**
	 * 
	 * @param userID
	 */
	public static Faculty getFaculty(String userID) {
		UserDatabase uDB = new UserDatabase();
		Student s1 = (Student) uDB.getItem(userID);
		
		return s1.getFaculty();
	}

}