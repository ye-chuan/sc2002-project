import java.util.*;

public class CampInformation {

	private String campID;
	private String name;
	private String location;
	private Collection<Date3> dates;
	private Date3 closingDate;
	private int attendeeSlots;
	private int campCommSlots;
	private String description;
	private Staff staffInCharge;
	private Collection<Faculty> openTo;

	String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	void setName(String name) {
		this.name = name;
	}

	String getLocation() {
		return this.location;
	}

	/**
	 * 
	 * @param location
	 */
	void setLocation(String location) {
		this.location = location;
	}

	Date3 getDates() {
		return this.dates;
	}

	/**
	 * 
	 * @param dates
	 */
	void setDates(Date3 dates) {
		this.dates = dates;
	}

	Date3 getClosingDate() {
		return this.closingDate;
	}

	/**
	 * 
	 * @param closingDate
	 */
	void setClosingDate(Date3 closingDate) {
		this.closingDate = closingDate;
	}

	int getAttendeeSlots() {
		return this.attendeeSlots;
	}

	/**
	 * 
	 * @param attendeeSlots
	 */
	void setAttendeeSlots(int attendeeSlots) {
		this.attendeeSlots = attendeeSlots;
	}

	int getCampCommSlots() {
		return this.campCommSlots;
	}

	/**
	 * 
	 * @param campCommSlots
	 */
	void setCampCommSlots(int campCommSlots) {
		this.campCommSlots = campCommSlots;
	}

	String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	void setDescription(String description) {
		this.description = description;
	}

	Staff getStaffInCharge() {
		return this.staffInCharge;
	}

	/**
	 * 
	 * @param staffInCharge
	 */
	void setStaffInCharge(Staff staffInCharge) {
		this.staffInCharge = staffInCharge;
	}

	Faculty getOpenTo() {
		return this.openTo;
	}

	/**
	 * 
	 * @param openTo
	 */
	void setOpenTo(Faculty openTo) {
		this.openTo = openTo;
	}

	public int getCampID() {
		// TODO - implement CampInformation.getCampID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campID
	 */
	public void setCampID(int campID) {
		// TODO - implement CampInformation.setCampID
		throw new UnsupportedOperationException();
	}

}