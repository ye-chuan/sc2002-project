import java.util.*;

public class Camp implements Identifiable {

	private int campID;
	private CampInfomation infomation;
	private Collection<Student> registeredStudents;
	private Collection<Student> campCommittee;
	private boolean isVisible = false;
	private SuggestionDatabase suggestionDB;
	private EnquiryDatabase enquiriesDB;

	/**
	 * 
	 * @param name
	 */
	public boolean setName(String name) {
		// TODO - implement Camp.setName
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param description
	 */
	public boolean setDescription(String description) {
		// TODO - implement Camp.setDescription
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param slots
	 */
	public boolean setAttendeeSlots(int slots) {
		// TODO - implement Camp.setAttendeeSlots
		throw new UnsupportedOperationException();
	}

	/**
	 * Max 10
	 * @param slots
	 */
	public boolean setCampCommSlots(int slots) {
		// TODO - implement Camp.setCampCommSlots
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param closingDate
	 */
	public boolean setClosingDate(Date closingDate) {
		// TODO - implement Camp.setClosingDate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dates
	 */
	public boolean setDates(Collection<Date> dates) {
		// TODO - implement Camp.setDates
		throw new UnsupportedOperationException();
	}

	public boolean show() {
		// TODO - implement Camp.show
		throw new UnsupportedOperationException();
	}

	public boolean hide() {
		// TODO - implement Camp.hide
		throw new UnsupportedOperationException();
	}

	/**
	 * Make camp available for all of NTU
	 */
	public boolean openToNTU() {
		// TODO - implement Camp.openToNTU
		throw new UnsupportedOperationException();
	}

	/**
	 * Make camp available for just 1 faculty (not whole school)
	 * @param faculty
	 */
	public boolean openToFaculty(Faculty faculty) {
		// TODO - implement Camp.openToFaculty
		throw new UnsupportedOperationException();
	}

	public Collection<Student> getRegisteredStudents() {
		// TODO - implement Camp.getRegisteredStudents
		throw new UnsupportedOperationException();
	}

	public Collection<Student> getCampCommMembers() {
		// TODO - implement Camp.getCampCommMembers
		throw new UnsupportedOperationException();
	}

}