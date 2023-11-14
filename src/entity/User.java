package entity;
public class User implements Identifiable {

	private String userID;
	private String password;
	private Faculty faculty;
    private String email;

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

	public void setEmail(String newEmail) {
        this.email = newEmail;
	}

    @Override
    public String getID() {
        return this.userID;
    }
}
