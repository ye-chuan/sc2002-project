package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages user login in the system
 * 
 */
public class LoginController {
    
    /**
	 * 
	 * @param userID
	 */
	public boolean isFirstLogin(String password) {
		if (password.equals("password")) return true;
		else return false;
		
	}
	

	/**
	 * 
	 * @param userID
	 * @param password
	 */
	public boolean login (String userID, String password) {
		UserDatabase uDB = Main.getUserDB();
		
		User u1 = uDB.login(userID,password);
		if (u1==null) {
			return false;
		} 
		else
			return true;
		
	}
    
}
