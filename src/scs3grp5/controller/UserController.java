package scs3grp5.controller;

import java.util.Collection;
import java.util.Iterator;

import scs3grp5.Main;
import scs3grp5.entity.*;

public class UserController {

	private PointController pointCont;

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
	public void changePassword(String userID, String newPassword) {
		UserDatabase uDB = Main.getUserDB();
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
	public UserType getDomain(String userID) {
		UserDatabase uDB = Main.getUserDB();
		User u1 = uDB.getItem(userID);
		if (u1 instanceof Student) return UserType.STUDENT;
		if (u1 instanceof Staff) return UserType.STAFF;
		else return null;
	}

	/**
	 * 
	 * @param userID
	 */
	public int getPoints(String userID) {
		UserDatabase uDB = Main.getUserDB();
		Student s1 = (Student) uDB.getItem(userID);
		pointCont.updatePoints(userID);
		
		return s1.getPoints();
	}

	/**
	 * 
	 * @param userID
	 */
	public String getStudentCommitteeCampID(String userID) {
		UserDatabase uDB = Main.getUserDB();
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
		Student s1 = (Student) uDB.getItem(userID);

		Collection<Camp> campList = cMemberDB.getCampsJoinedBy(s1);

		for(Camp c : campList) {
			if (cMemberDB.getRoleInCamp(c,s1)  == CampRole.CAMPCOMM) {
				return c.getID();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param newPassword
	 */
	public boolean isPasswordStrong(String newPassword) {
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
	public String getUserName(String userID) {
		UserDatabase uDB = Main.getUserDB();
		Student s1 = (Student) uDB.getItem(userID);
		
		return s1.getEmail();
	}

	/**
	 * 
	 * @param userID
	 */
	public Faculty getFaculty(String userID) {
		UserDatabase uDB = Main.getUserDB();
		Student s1 = (Student) uDB.getItem(userID);
		
		return s1.getFaculty();
	}
}