package scs3grp5.entity;

import java.io.Serializable;

/**
 * Represents a User of this system
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class User implements Identifiable, Serializable {

    /** The unique ID of this user */
	private String userID;
    /** The password of this user */
	private String password;
    /** The faculty of this user */
	private Faculty faculty;
    /** The email of this user */
    private String email;
    /** The name of this user */
    private String name;

    /**
     * Constructs a User
     * @param userID The unique ID of this student
     * @param password The password of this student
     * @param faculty The Faculty that this student is in
     * @param email The email of this student
     * @param name The name of this student
     */
    public User(String userID, String password, Faculty faculty, String email, String name) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
        this.email = email;
        this.name = name;
    }

    /**
     * Change the password of this User
     * @return If the password change was successful
     */
	public boolean changePassword(String password) {
        this.password = password;
        return true;
	}

    /**
     * Gets if the given password matches the user's password
     * @param password The password to check
     * @return If the password was correct
     */
	public boolean checkPassword(String password) {
        return this.password.equals(password);
	}

    /**
     * @return The faculty of this User
     */
	public Faculty getFaculty() {
		return this.faculty;
	}

    /**
     * @return The email of this User
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @return The name of this User
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The email of this User
     */
	public void setEmail(String newEmail) {
        this.email = newEmail;
	}

    @Override
    public String getID() {
        return this.userID;
    }

    @Override
    public String toString() {
        return "User #" + getID() + ": " + getName() + " from " + getFaculty();
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof User))
            return false;
        return this.getID().equals(((User)obj).getID());
    }

    @Override
    public int hashCode() {
        return this.getID().hashCode();
    }
}
