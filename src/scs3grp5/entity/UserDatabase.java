package scs3grp5.entity;

import java.util.Collection;
import java.util.ArrayList;

public class UserDatabase extends Database<User> {

    /** Constructs Empty UserDatabase */
    public UserDatabase() {}

    /**
     * Construct UserDatabase from a list.
     *
     * @param staffs Collection of Staff to add to database
     * @param students Collection of Student to add to database
     */
    public UserDatabase(Collection<Staff> staffs, Collection<Student> students) {
        Collection<User> users = new ArrayList<User>();
        users.addAll(staffs);
        users.addAll(students);
        addMany(users);
    }
	/**
	 * Attempt to log the user in with the given credentials
	 * @param userID The entered userID
	 * @param password The entered password
     * @return The {@code User} object if login was successfull, else {@code null}
	 */
	public User login(String userID, String password) {
        User user = getItem(userID);
        if (user == null)
            return null;
        return user.checkPassword(password) ? user : null;
	}
}
