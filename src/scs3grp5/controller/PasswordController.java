package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages user password handling in the system
 * 
 */
public class PasswordController {
    
    /**
	 * check if old password matches new password and changes the password for user
	 * @param userID
	 * @param newPassword
	 * 
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
	 * checks if new password fits system requirements
	 * @param newPassword
	 * 
	 */
	public void isPasswordStrong(String newPassword) throws PasswordException {
		if (newPassword.length() < 8) {
			throw new PasswordException("Password needs be more than 8 characters");
		}
		boolean lower = false;
		boolean upper = false; 
		boolean special = false; 
		boolean number = false; 

		char[] ch = newPassword.toCharArray(); 
		
		for (int i=0; i<newPassword.length();i++){
			if (Character.isLowerCase(ch[i])) lower = true; 
			else if (Character.isUpperCase(ch[i])) upper = true; 
			else if (Character.isDigit(ch[i])) number = true; 
			else special = true; 
		}

		if (!lower) throw new PasswordException("Password must contain a lower character!");
		if (!upper) throw new PasswordException("Password must contain an upper character!");
		if (!special) throw new PasswordException("Password must contain a special character");
		if (!number) throw new PasswordException("Password must contain a digit");
	}


}
