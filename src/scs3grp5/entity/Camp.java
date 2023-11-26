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
    /** The {@code CampMembershipDatabase} in charge of keeping track of the members in this camp */
    private CampMembershipDatabase membershipDB;

	/**
     * Construct Camp object exhausively
     *
     * @param membershipDB The {@CampMembershipDatabase} that is going to store the Membership information for this Camp
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
	public Camp(CampMembershipDatabase membershipDB, String name, String location, Date startDate, Date endDate, Date closingDate, int participantSlots, int campCommSlots, Staff staffInCharge, Collection<Faculty> openTo) {
        this.information = new CampInformation(name, location, startDate, endDate, closingDate, participantSlots, campCommSlots, staffInCharge, openTo);
        this.information.setCampID(Main.getIdGenerator().generate());

        this.suggestionDB = new SuggestionDatabase();
        this.enquiriesDB = new EnquiryDatabase();
        this.membershipDB = membershipDB;
	}

    /**
     * Constructs Camp object minimally.
     *
     * @param membershipDB The {@CampMembershipDatabase} that is going to store the Membership information for this Camp
     * @param staffInCharge The staff-in-charge is the only required attribute of a Camp (and unmodifiable after creation)
     */
    public Camp(CampMembershipDatabase membershipDB, Staff staffInCharge) {
        this.information = new CampInformation(staffInCharge);
        this.information.setCampID(Main.getIdGenerator().generate());

        this.suggestionDB = new SuggestionDatabase();
        this.enquiriesDB = new EnquiryDatabase();
        this.membershipDB = membershipDB;
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

	/** Make camp available for just 1 faculty (not whole school) */
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

    /** Set the name of this camp */
	public void setName(String name) {
        this.information.setName(name);
	}

    /**
     * @return The location of this camp
     */
	public String getLocation() {
        return this.information.getLocation();
	}

    /** Set the location of this camp */
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
	 * @param dates Can be given in a Collection (abitrary/no order)
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

    /** Set the last Date of registration */
	public void setClosingDate(Date closingDate) {
        this.information.setClosingDate(closingDate);
	}

    /**
     * @return Total number of participant slots
     */
	public int getParticipantSlots() {
        return this.information.getParticipantSlots();
	}

    /** Set total number of participant slots */
	public void setParticipantSlots(int participantSlots) {
        this.information.setParticipantSlots(participantSlots);
	}

    /**
     * @return Total number of camp committee slots
     */
	public int getCampCommSlots() {
        return this.information.getCampCommSlots();
	}

    /** Set total number of camp committee slots */
	public void setCampCommSlots(int campCommSlots) {
        this.information.setCampCommSlots(campCommSlots);
	}

    /**
     * @return This camp's text description
     */
	public String getDescription() {
        return this.information.getDescription();
	}

    /** Set this camp's text description */
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
     */
	public void setOpenTo(Collection<Faculty> openTo) {
        this.information.setOpenTo(openTo);
	}

    /**
     * Checks whether this camp is open to a Faculty
     *
     * @param The faculty to check
     * @return Whether this camp is open to a Faculty
     */
    public boolean isOpenTo(Faculty faculty) {
        return getOpenTo().contains(faculty);
    }

    /**
     * @return The {@SuggestionDatabase} of this Camp where Suggestions are stored
     */
    public SuggestionDatabase getSuggestionDB() {
        return this.suggestionDB;
    }

    /**
     * @return The {@EnquiryDatabase} of this Camp where Enquiries are stored
     */
    public EnquiryDatabase getEnquiryDB() {
        return this.enquiriesDB;
    }

    /**
     * @return The cuurent number of participants in this camp
     */
    public int getNumParticipants() {
        return membershipDB.getParticipantSize(this);
    }

    /**
     * @return The cuurent number of camp committee members in this camp
     */
    public int getNumCampComm() {
        return membershipDB.getCampCommSize(this);
    }

    /** {@inheritDoc} */
    @Override
    public String getID() {
        return information.getCampID();
    }

    public String toString() {
        return "Camp #" + getID() + ": " + getName();
    }


}
