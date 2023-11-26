package scs3grp5.entity;

import java.io.Serializable;

public class User implements Identifiable, Serializable {

	private String userID;
	private String password;
	private Faculty faculty;
    private String email;
    private String name;

    public User(String userID, String password, Faculty faculty, String email, String name) {
        this.userID = userID;
        this.password = password;
        this.faculty = faculty;
        this.email = email;
        this.name = name;
    }

	public boolean changePassword(String password) {
        this.password = password;
        return true;
	}

	public boolean checkPassword(String password) {
        return this.password.equals(password);
	}

	public Faculty getFaculty() {
		return this.faculty;
	}

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

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
