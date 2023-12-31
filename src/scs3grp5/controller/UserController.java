package scs3grp5.controller;
import java.util.Collection;

import scs3grp5.Main;
import scs3grp5.entity.*;


/**
 * Manages user account and login in the system
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class UserController {

	private LoginController loginCont = new LoginController();
	private PasswordController passwordCont = new PasswordController();

	/**
	 * checks whether it is the first time user log in to the system
	 * @param password current password of the User
	 * @return true if first login
	 * @see LoginController#isFirstLogin(String)
	 */
	public boolean isFirstLogin(String password) {
		return loginCont.isFirstLogin(password);
	}
	

	/**
	 * check successful login by user
	 * @param userID The unique ID of the User
	 * @param password current password of the User
	 * @return true if login success
	 * @see LoginController#login(String, String)
	 */
	public boolean login (String userID, String password) {
		return loginCont.login(userID, password);
	}

	/**
	 * check if old password matches new password and changes the password for user
	 * @param userID The unique ID of the User
	 * @param newPassword new password of the User
	 * @throws PasswordException if new password is the same as current
	 * @see PasswordController#changePassword(String, String)
	 */
	public void changePassword(String userID, String newPassword) throws PasswordException {
		passwordCont.changePassword(userID, newPassword);
	}

	/**
	 * checks if new password fits system requirements
	 * @param newPassword new password of the User
	 * @throws PasswordException if new password is not strong
	 * @see PasswordController#isPasswordStrong(String)
	 */
	public void isPasswordStrong(String newPassword) throws PasswordException {
		passwordCont.isPasswordStrong(newPassword);
	}

	/**
	 * get domain/ user type of user currently in the system
	 * @param userID The unique ID of the User
	 * @return STAFF or STUDENT
	 */
	public UserType getDomain(String userID) {
		UserDatabase uDB = Main.getUserDB();
		User u1 = uDB.getItem(userID);
		if (u1 instanceof Student) 
			return UserType.STUDENT;
		if (u1 instanceof Staff) 
			return UserType.STAFF;
		else 
			return null;
	}

	/**
	 * check whether user in the system is STAFF
	 * @param userID The unique ID of the User
	 * @return true if user is staff
	 */
	public boolean isStaffUserType(String userID) {
		if (getDomain(userID)==UserType.STAFF) 
			return true;
		else 
			return false;
	}

	/**
	 * get points of the student
	 * @param userID The unique ID of the User
	 * @return points
	 */
	public int getPoints(String userID) {
		UserDatabase uDB = Main.getUserDB();
		User u1 =  uDB.getItem(userID);
		if (getDomain(userID)==UserType.STUDENT) {
			Student s1 = (Student) u1;
			
			return s1.getPoints();
		}
		else return 0;
		
	}

	/**
	 * check for whether student is currently a camp committee
	 * @param userID The unique ID of the User
	 * @return name of Camp which student is a camp committee
	 */
	public String getStudentCommitteeCampID(String userID) {
		UserDatabase uDB = Main.getUserDB();
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
		if (getDomain(userID)==UserType.STUDENT) {
			Student s1 = (Student) uDB.getItem(userID);

			Collection<Camp> campList = cMemberDB.getCampsJoinedBy(s1);

			for(Camp c : campList) {
				if (cMemberDB.getRoleInCamp(c,s1)  == CampRole.CAMPCOMM) {
					return c.getName();
				}
			}
		}
		return "N.A";
	}

	

	/**
	 * get username of user
	 * @param userID The unique ID of the User
	 * @return userName or email of the User
	 */
	public String getUserName(String userID) {
		UserDatabase uDB = Main.getUserDB();
		
		User s1 = uDB.getItem(userID);
		
		return s1.getEmail();
	}

	/**
	 * get name of user
	 * @param userID The unique ID of the User
	 * @return name of the User
	 */
	public String getName(String userID) {
		UserDatabase uDB = Main.getUserDB();
		User s1 = uDB.getItem(userID);
		
		return s1.getName();
	}

	/**
	 * get faculty of user
	 * @param userID The unique ID of the User
	 * @return faculty of the User
	 */
	public String getFaculty(String userID) {
		UserDatabase uDB = Main.getUserDB();
		User s1 = uDB.getItem(userID);
		
		return s1.getFaculty().name();
	}
}