package scs3grp5.entity;

import scs3grp5.Main;
import java.io.Serializable;
import java.util.*;

/**
 * Represents a Camp created by Staff and joinable by Students
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class Camp implements Identifiable, Serializable{

    /** The camp's static information (doesn't include members) */
	private CampInformation information;
    /** The visibility of the camp */
	private boolean visible = false;
    /** This camp's {@code SuggestionDatabase} where suggestions are stored */
	private SuggestionDatabase suggestionDB;
    /** This camp's {@code EnquiryDatabase} where enquiries are stored */
	private EnquiryDatabase enquiriesDB;

	/**
     * Construct Camp object exhausively
     *
	 * @param name Name of the camp
	 * @param location Location where the camp is held
	 * @param startDate The camp's first day
     * @param endDate The camp's last day
	 * @param closingDate Registration's Last Date
	 * @param participantSlots Total slots for participants (not camp committee)
	 * @param campCommSlots Total slots for camp committee members (not participants)
	 * @param staffInCharge The staff in charge of the camp (cannot be changed)
	 * @param openTo The faculty that the camp is opened to
	 */
	public Camp(String name, String location, Date startDate, Date endDate, Date closingDate, int participantSlots, int campCommSlots, Staff staffInCharge, Collection<Faculty> openTo) {
        this.information = new CampInformation(name, location, startDate, endDate, closingDate, participantSlots, campCommSlots, staffInCharge, openTo);
        this.information.setCampID(Main.getIdGenerator().generate());

        this.suggestionDB = new SuggestionDatabase();
        this.enquiriesDB = new EnquiryDatabase();
	}

    /**
     * Constructs Camp object minimally.
     *
     * @param staffInCharge The staff-in-charge is the only required attribute of a Camp (and unmodifiable after creation)
     */
    public Camp(Staff staffInCharge) {
        this.information = new CampInformation(staffInCharge);
        this.information.setCampID(Main.getIdGenerator().generate());

        this.suggestionDB = new SuggestionDatabase();
        this.enquiriesDB = new EnquiryDatabase();
    }

    /**
     * @return Whether the camp is visible
     */
    public boolean isVisible() {
        return this.visible;
    }

    /** Make this camp visible */
	public void show() {
        this.visible = true;
	}

    /** Make this camp invisible */
	public void hide() {
        this.visible = false;
	}

	/** Make camp available for all of NTU */
	public void openToNTU() {
        information.setOpenTo(Arrays.asList(Faculty.values()));
	}

	/**
     * Make camp available for just 1 faculty (not whole school)
     * @param faculty The only faculty to open this camp to
     */
	public void openToFaculty(Faculty faculty) {
        Collection<Faculty> openToFaculties = new ArrayList<Faculty>();
        openToFaculties.add(faculty);
        information.setOpenTo(openToFaculties);
	}

    /**
     * @return The name of this camp
     */
	public String getName() {
        return this.information.getName();
	}

    /**
     * Set the name of this camp
     * @param name The new camp name
     */
	public void setName(String name) {
        this.information.setName(name);
	}

    /**
     * @return The location of this camp
     */
	public String getLocation() {
        return this.information.getLocation();
	}

    /**
     * Set the location of this camp
     * @param location The new location of this camp
     */
	public void setLocation(String location) {
        this.information.setLocation(location);
	}

	/**
	 * Get the Camp's event dates (not registration date).
     *
	 * @return Camp's event dates in sorted order
	 */
	public DateRange getDates() {
        return this.information.getCampDates();
	}

	/**
	 * Set the Camp's event dates (not registration date).
     *
	 * @param start Start date of the camp
     * @param end End date of the camp
	 */
	public void setDates(Date start, Date end) {
        this.information.setDates(start, end);
	}

    /**
     * @return The last Date of the camp
     */
    public Date getLastCampDate() {
        return getDates().getEnd();
    }

    /**
     * @return The last Date of registration
     */
	public Date getClosingDate() {
        return this.information.getClosingDate();
	}

    /**
     * Set the last Date of registration
     * @param closingDate The new registration closing date for this camp
     */
	public void setClosingDate(Date closingDate) {
        this.information.setClosingDate(closingDate);
	}

    /**
     * @return Total number of participant slots
     */
	public int getParticipantSlots() {
        return this.information.getParticipantSlots();
	}

    /** 
     * Set total number of participant slots
     * @param participantSlots The new total participant slots
     */
	public void setParticipantSlots(int participantSlots) {
        this.information.setParticipantSlots(participantSlots);
	}

    /**
     * @return Total number of camp committee slots
     */
	public int getCampCommSlots() {
        return this.information.getCampCommSlots();
	}

    /**
     * Set total number of camp committee slots
     * @param campCommSlots The new total camp committee slots
     */
	public void setCampCommSlots(int campCommSlots) {
        this.information.setCampCommSlots(campCommSlots);
	}

    /**
     * @return This camp's text description
     */
	public String getDescription() {
        return this.information.getDescription();
	}

    /**
     * Set this camp's text description
     * @param description The new camp description
     */
	public void setDescription(String description) {
        this.information.setDescription(description);
	}

    /**
     * @return This camp's staff-in-charge
     */
	public Staff getStaffInCharge() {
        return this.information.getStaffInCharge();
	}

    /**
     * @return Collection of Faculties that this camp is open to
     */
	public Collection<Faculty> getOpenTo() {
        return this.information.getOpenTo();
	}

    /**
     * Set the Collection of Faculties that this camp is open to
     * @param openTo The new set of faculties to open this camp to
     */
	public void setOpenTo(Collection<Faculty> openTo) {
        this.information.setOpenTo(openTo);
	}

    /**
     * Checks whether this camp is open to a Faculty
     *
     * @param faculty The faculty to check
     * @return Whether this camp is open to a Faculty
     */
    public boolean isOpenTo(Faculty faculty) {
        return getOpenTo().contains(faculty);
    }

    /**
     * @return The {@code SuggestionDatabase} of this Camp where Suggestions are stored
     */
    public SuggestionDatabase getSuggestionDB() {
        return this.suggestionDB;
    }

    /**
     * @return The {@code EnquiryDatabase} of this Camp where Enquiries are stored
     */
    public EnquiryDatabase getEnquiryDB() {
        return this.enquiriesDB;
    }

    /** {@inheritDoc} */
    @Override
    public String getID() {
        return information.getCampID();
    }

    @Override
    public String toString() {
        return "Camp #" + getID() + ": " + getName();
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Camp))
            return false;
        return this.getID().equals(((Camp)obj).getID());
    }

    @Override
    public int hashCode() {
        return this.getID().hashCode();
    }

}
