import java.util.*;

public class Camp implements Identifiable {

	private CampInfomation2 infomation;
	private Collection<Student> registeredStudents;
	private Collection<Student> campCommittee;
	private boolean isVisible = false;
	private SuggestionDatabase suggestionDB;
	private EnquiryDatabase enquiriesDB;

	/**
	 * 
	 * @param name
	 * @param location
	 * @param dates
	 * @param closingDate
	 * @param attendeeSlots
	 * @param campCommSlots
	 * @param staffInCharge
	 * @param openTo
	 */
	public Camp(String name, String location, Collection<Date> dates, Date closingDate, int attendeeSlots, int campCommSlots, Staff staffInCharge, Collection<Faculty> openTo) {
		// TODO - implement Camp.Camp
		throw new UnsupportedOperationException();
	}

	public Collection<Student> getRegisteredStudents() {
		// TODO - implement Camp.getRegisteredStudents
		throw new UnsupportedOperationException();
	}

	public void show() {
		// TODO - implement Camp.show
		throw new UnsupportedOperationException();
	}

	public void hide() {
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

	public Collection<Student> getCampCommMembers() {
		// TODO - implement Camp.getCampCommMembers
		throw new UnsupportedOperationException();
	}

	public String getName() {
		// TODO - implement Camp.getName
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		// TODO - implement Camp.setName
		throw new UnsupportedOperationException();
	}

	public String getLocation() {
		// TODO - implement Camp.getLocation
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		// TODO - implement Camp.setLocation
		throw new UnsupportedOperationException();
	}

	public Date3 getDates() {
		// TODO - implement Camp.getDates
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dates
	 */
	public void setDates(Date3 dates) {
		// TODO - implement Camp.setDates
		throw new UnsupportedOperationException();
	}

	public Date3 getClosingDate() {
		// TODO - implement Camp.getClosingDate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param closingDate
	 */
	public void setClosingDate(Date3 closingDate) {
		// TODO - implement Camp.setClosingDate
		throw new UnsupportedOperationException();
	}

	public int getAttendeeSlots() {
		// TODO - implement Camp.getAttendeeSlots
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param attendeeSlots
	 */
	public void setAttendeeSlots(int attendeeSlots) {
		// TODO - implement Camp.setAttendeeSlots
		throw new UnsupportedOperationException();
	}

	public int getCampCommSlots() {
		// TODO - implement Camp.getCampCommSlots
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campCommSlots
	 */
	public void setCampCommSlots(int campCommSlots) {
		// TODO - implement Camp.setCampCommSlots
		throw new UnsupportedOperationException();
	}

	public String getDescription() {
		// TODO - implement Camp.getDescription
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		// TODO - implement Camp.setDescription
		throw new UnsupportedOperationException();
	}

	public Staff getStaffInCharge() {
		// TODO - implement Camp.getStaffInCharge
		throw new UnsupportedOperationException();
	}

	public Faculty getOpenTo() {
		// TODO - implement Camp.getOpenTo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param openTo
	 */
	public void setOpenTo(Faculty openTo) {
		// TODO - implement Camp.setOpenTo
		throw new UnsupportedOperationException();
	}

}