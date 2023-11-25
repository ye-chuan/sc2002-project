package scs3grp5.controller;

import java.util.Collection;
import java.util.Iterator;

import entity.Camp;
import entity.CampMembershipDatabase;
import entity.CampRole;
import entity.Date;
import entity.Faculty;
import entity.Staff;
import entity.Student;
import entity.User;
import entity.UserDatabase;

public class UserController {

	private PointController pointCont;

	/**
	 * 
	 * @param userID
	 */
	public String isFirstLogin(String password) {
		if (password == "password") return null;
		else return "Password needs to be more than 8 characters\n";
		
	}
	

	/**
	 * 
	 * @param userID
	 * @param password
	 */
	public boolean login (String userID, String password) {
		UserDatabase uDB = new UserDatabase();
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
	public UserType getDomain(String userID) {
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
	public int getPoints(String userID) {
		UserDatabase uDB = new UserDatabase();
		Student s1 = (Student) uDB.getItem(userID);
		pointCont.updatePoints(userID);
		
		return s1.getPoints();
	}

	/**
	 * 
	 * @param userID
	 */
	public String getStudentCommitteeCampID(String userID) {
		UserDatabase uDB = new UserDatabase();
		CampMembershipDatabase cMemberDB = new CampMembershipDatabase();
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
		UserDatabase uDB = new UserDatabase();
		Student s1 = (Student) uDB.getItem(userID);
		
		return s1.getEmail();
	}

	/**
	 * 
	 * @param userID
	 */
	public Faculty getFaculty(String userID) {
		UserDatabase uDB = new UserDatabase();
		Student s1 = (Student) uDB.getItem(userID);
		
		return s1.getFaculty();
	}
}