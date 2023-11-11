public class User implements Identifiable {

	private String userID;
	private String password;
	private Faculty faculty;

	public String getUserID() {
		return this.userID;
	}

	/**
	 * 
	 * @param newEmail
	 */
	public boolean changeEmail(String newEmail) {
		// TODO - implement User.changeEmail
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param password
	 */
	public boolean changePassword(String password) {
		// TODO - implement User.changePassword
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param password
	 */
	public boolean checkPassword(String password) {
		// TODO - implement User.checkPassword
		throw new UnsupportedOperationException();
	}

	public Faculty getFaculty() {
		return this.faculty;
	}

}