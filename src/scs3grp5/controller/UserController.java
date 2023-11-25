package scs3grp5.controller;
import java.util.Collection;

import scs3grp5.Main;
import scs3grp5.entity.*;

public class UserController {

	private PointController pointCont = new PointController();

	/**
	 * 
	 * @param userID
	 */
	public boolean isFirstLogin(String password) {
		if (password == "password") return true;
		else return false;
		
	}
	

	/**
	 * 
	 * @param userID
	 * @param password
	 */
	public boolean login (String userID, String password) {
		UserDatabase uDB = Main.getUserDB();
		try {
			User u1 = uDB.login(userID,password);
		} catch (NullPointerException e) {
			return false;
		}
		return true;
		
	}

	/**
	 * 
	 * @param userID
	 * @param newPassword
	 */
	public void changePassword(String userID, String newPassword) throws PasswordException {
		UserDatabase uDB = Main.getUserDB();
		User u1 = uDB.getItem(userID);
	
		if (!u1.checkPassword(newPassword)) {
			u1.changePassword(newPassword);
		}
		else {
			throw new PasswordException("new password cannot be same as old password");
		}
		
	}

	/**
	 * 
	 * @param userID
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
	 * 
	 * @param userID
	 */
	public boolean isStaffUserType(String userID) {
		if (getDomain(userID)==UserType.STAFF) 
			return true;
		else 
			return false;
	}

	/**
	 * 
	 * @param userID
	 */
	public int getPoints(String userID) {
		UserDatabase uDB = Main.getUserDB();
		User u1 =  uDB.getItem(userID);
		if (getDomain(userID)==UserType.STUDENT) {
			Student s1 = (Student) u1;
			pointCont.updatePoints(userID);
			return s1.getPoints();
		}
		else
			return 0;
		
	}

	/**
	 * 
	 * @param userID
	 */
	public String getStudentCommitteeCampID(String userID) {
		UserDatabase uDB = Main.getUserDB();
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
		if (getDomain(userID)==UserType.STUDENT) {
			Student s1 = (Student) uDB.getItem(userID);

			Collection<Camp> campList = cMemberDB.getCampsJoinedBy(s1);

			for(Camp c : campList) {
				if (cMemberDB.getRoleInCamp(c,s1)  == CampRole.CAMPCOMM) {
					return c.getID();
				}
			}
		}
		return "N.A";
	}

	/**
	 * 
	 * @param newPassword
	 */
	public void isPasswordStrong(String newPassword) throws PasswordException {
		if (newPassword.length() < 8) {
			throw new PasswordException("Password needs be more than 8 characters");
		}
	}

	/**
	 * 
	 * @param userID
	 */
	public String getUserName(String userID) {
		UserDatabase uDB = Main.getUserDB();
		
		User s1 = uDB.getItem(userID);
		
		return s1.getEmail();
	}

	/**
	 * 
	 * @param userID
	 */
	public String getName(String userID) {
		UserDatabase uDB = Main.getUserDB();
		User s1 = uDB.getItem(userID);
		
		return s1.getName();
	}

	/**
	 * 
	 * @param userID
	 */
	public String getFaculty(String userID) {
		UserDatabase uDB = Main.getUserDB();
		User s1 = uDB.getItem(userID);
		
		return s1.getFaculty().name();
	}
}