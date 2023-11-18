package entity;
public class User implements Identifiable {

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
}
