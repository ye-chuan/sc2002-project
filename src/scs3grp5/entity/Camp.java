package scs3grp5.entity;

import scs3grp5.Main;
import java.io.Serializable;
import java.util.*;

public class Camp implements Identifiable, Serializable{

	private CampInformation information;
	private boolean visible = false;
	private SuggestionDatabase suggestionDB;
	private EnquiryDatabase enquiriesDB;
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

    public boolean isVisible() {
        return this.visible;
    }

	public void show() {
        this.visible = true;
	}

	public void hide() {
        this.visible = false;
	}

	/**
	 * Make camp available for all of NTU
	 */
	public void openToNTU() {
        information.setOpenTo(Arrays.asList(Faculty.values()));
	}

	/**
	 * Make camp available for just 1 faculty (not whole school)
	 */
	public void openToFaculty(Faculty faculty) {
        Collection<Faculty> openToFaculties = new ArrayList<Faculty>();
        openToFaculties.add(faculty);
        information.setOpenTo(openToFaculties);
	}

	public String getName() {
        return this.information.getName();
	}

	public void setName(String name) {
        this.information.setName(name);
	}

	public String getLocation() {
        return this.information.getLocation();
	}

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

    public Date getLastCampDate() {
        return getDates().getEnd();
    }

	public Date getClosingDate() {
        return this.information.getClosingDate();
	}

	public void setClosingDate(Date closingDate) {
        this.information.setClosingDate(closingDate);
	}

	public int getParticipantSlots() {
        return this.information.getParticipantSlots();
	}

	public void setParticipantSlots(int participantSlots) {
        this.information.setParticipantSlots(participantSlots);
	}

	public int getCampCommSlots() {
        return this.information.getCampCommSlots();
	}

	public void setCampCommSlots(int campCommSlots) {
        this.information.setCampCommSlots(campCommSlots);
	}

	public String getDescription() {
        return this.information.getDescription();
	}

	public void setDescription(String description) {
        this.information.setDescription(description);
	}

	public Staff getStaffInCharge() {
        return this.information.getStaffInCharge();
	}

	public Collection<Faculty> getOpenTo() {
        return this.information.getOpenTo();
	}

	public void setOpenTo(Collection<Faculty> openTo) {
        this.information.setOpenTo(openTo);
	}

    public boolean isOpenTo(Faculty faculty) {
        return getOpenTo().contains(faculty);
    }

    public SuggestionDatabase getSuggestionDB() {
        return this.suggestionDB;
    }

    public EnquiryDatabase getEnquiryDB() {
        return this.enquiriesDB;
    }

    public int getNumParticipants() {
        return membershipDB.getParticipantSize(this);
    }

    public int getNumCampComm() {
        return membershipDB.getCampCommSize(this);
    }

    @Override
    public String getID() {
        return information.getCampID();
    }

    public String toString() {
        return "Camp #" + getID() + ": " + getName();
    }


}
