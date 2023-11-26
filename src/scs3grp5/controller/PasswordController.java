package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages user password handling in the system
 * 
 */
public class PasswordController {
    
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
	 * @param newPassword
	 */
	public void isPasswordStrong(String newPassword) throws PasswordException {
		if (newPassword.length() < 8) {
			throw new PasswordException("Password needs be more than 8 characters");
		}
	}


}
