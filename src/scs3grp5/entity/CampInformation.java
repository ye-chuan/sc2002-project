package scs3grp5.entity;
import java.io.Serializable;
import java.util.*;

/**
 * A "Dataclass" of Camp Information (should only be used internally / package)
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampInformation implements Serializable {

    /** The unique ID for this camp (for database purposes) */
    private String campID;
    /** Name of the camp */
    private String name;
    /** Location where the camp is held */
    private String location;
    /** Camp Dates as a range */
    private DateRange campDates;
    /** Registration's Last Date */
    private Date closingDate;
    /** Total slots for participants (not camp committee) */
    private int participantSlots;
    /** Total slots for camp committee members (not participants) */
    private int campCommSlots;
    /** Text description of the Camp */
    private String description;
    /** The staff in charge of the camp (cannot be changed) */
    private Staff staffInCharge;
    /** The collection of faculties that this camp is open to */
    private Collection<Faculty> openTo = new ArrayList<Faculty>();

    /**
     * Constucts the Camp Information object minimally, using sensible defaults
     * @param staffInCharge The only required (and unchangeable) attribute is the staff-in-charge
     */
    public CampInformation(Staff staffInCharge) {
        this.staffInCharge = staffInCharge;
        this.name = "Unspecified Name";
        this.location = "Unspecified Location";
        this.participantSlots = 0;
        this.campCommSlots = 0;
        this.description = "No Description";
    }

    /**
     * Constructs the Camp Information object exhausively
     * 
     * @param name Name of the camp
     * @param location Location where the camp is held
     * @param startDate Start date of the camp
     * @param endDate End date of the camp
     * @param closingDate Registration's Last Date
     * @param participantSlots Total slots for participants (not camp committee)
     * @param campCommSlots Total slots for camp committee members (not participants)
     * @param staffInCharge The staff in charge of the camp (cannot be changed)
     * @param openTo The collection of faculties that this camp is open to
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

    /**
     * @return The name of this camp
     */
    String getName() {
        return this.name;
    }

    /**
     * Sets the name of this camp
     * @param name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * @return The location of this camp
     */
    String getLocation() {
        return this.location;
    }

    /**
     * Sets the locaiton of this camp
     * @param location
     */
    void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the Camp's event dates (not registration date).
     *
     * @return Camp's event dates as a {@DateRange}
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

    /** Get the last registration day of this camp */
    Date getClosingDate() {
        return this.closingDate;
    }

    /**
     * Set the last registration day of this camp
     * @param closingDate
     */
    void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    /** Get the total number of slots available for participants */
    int getParticipantSlots() {
        return this.participantSlots;
    }

    /**
     * Set the total number of slots available for participants
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
