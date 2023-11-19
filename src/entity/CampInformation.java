package entity;
import java.util.*;

public class CampInformation {

	private String campID;
	private String name;
	private String location;
	private List<Date> dates;
	private Date closingDate;
	private int participantSlots;
	private int campCommSlots;
	private String description;
	private Staff staffInCharge;
	private Collection<Faculty> openTo;

    public CampInformation(Staff staffInCharge) {
        this.staffInCharge = staffInCharge;
        this.name = "Unspecified Name";
        this.location = "Unspecified Location";
        this.participantSlots = 0;
        this.campCommSlots = 0;
    }

	/**
	 * 
	 * @param name
	 * @param location
	 * @param dates
	 * @param closingDate
	 * @param participantSlots
	 * @param campCommSlots
	 * @param staffInCharge
	 * @param openTo
	 */
	public CampInformation(String name, String location, Collection<Date> dates, Date closingDate, int participantSlots, int campCommSlots, Staff staffInCharge, Collection<Faculty> openTo) {
        this.name = name;
        this.location = location;
        this.dates = dates;
        this.closingDate = closingDate;
        this.participantSlots = participantSlots;
        this.campCommSlots = campCommSlots;
        this.staffInCharge = staffInCharge;
        this.openTo = openTo;
	}
    
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

	/**
	 * Get the Camp's event dates (not registration date).
     *
	 * @return Camp's event dates in sorted order
	 */
	List<Date> getDates() {
		return this.dates;
	}

	/**
	 * Set the Camp's event dates (not registration date).
     *
	 * @param dates Can be given in a Collection (abitrary/no order)
	 */
	void setDates(Collection<Date> dates) {
        this.dates = new ArrayList<Date>(dates);
		Collections.sort(this.dates);
	}

	Date getClosingDate() {
		return this.closingDate;
	}

	/**
	 * 
	 * @param closingDate
	 */
	void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	int getParticipantSlots() {
		return this.participantSlots;
	}

	/**
	 * 
	 * @param participantSlots
	 */
	void setParticipantSlots(int participantSlots) {
		this.participantSlots = participantSlots;
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

	Collection<Faculty> getOpenTo() {
		return this.openTo;
	}

	/**
	 * 
	 * @param openTo
	 */
	void setOpenTo(Collection<Faculty> faculties) {
		this.openTo = faculties;
	}

	String getCampID() {
        return campID;
	}

	void setCampID(String campID) {
        this.campID = campID;
	}


}
