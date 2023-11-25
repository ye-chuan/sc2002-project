package scs3grp5.entity;
import java.io.Serializable;
import java.util.*;

/** A "Dataclass" of Camp Information (should only be used internally / package)
 */
public class CampInformation implements Serializable {

	private String campID;
	private String name;
	private String location;
	private DateRange campDates;
	private Date closingDate;
	private int participantSlots;
	private int campCommSlots;
	private String description;
	private Staff staffInCharge;
	private Collection<Faculty> openTo;

    /**
     *
     * @param staffInCharge The only required (and unchangeable) attribute is the staff-in-charge
     */
    public CampInformation(Staff staffInCharge) {
        this.staffInCharge = staffInCharge;
        this.name = "Unspecified Name";
        this.location = "Unspecified Location";
        this.participantSlots = 0;
        this.campCommSlots = 0;
    }

	/**
     * Constructs the Camp Information object exhausively
	 * 
	 * @param name
	 * @param location
	 * @param startDate
	 * @param endDate
	 * @param closingDate
	 * @param participantSlots
	 * @param campCommSlots
	 * @param staffInCharge
	 * @param openTo
	 */
	CampInformation(String name, String location, Date startDate, Date endDate, Date closingDate, int participantSlots, int campCommSlots, Staff staffInCharge, Collection<Faculty> openTo) {
        this.name = name;
        this.location = location;
        this.closingDate = closingDate;
        this.participantSlots = participantSlots;
        this.campCommSlots = campCommSlots;
        this.staffInCharge = staffInCharge;
        this.openTo = openTo;

        setDates(startDate, endDate);
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
	DateRange getCampDates() {
		return this.campDates;
	}

	/**
	 * Set the Camp's event dates (not registration date).
     *
	 * @param startDate The camp's first day
     * @param endDate The camp's last day
	 */
	void setDates(Date startDate, Date endDate) {
        assert !(startDate.isAfter(endDate));
        this.campDates = new DateRange(startDate, endDate);
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
