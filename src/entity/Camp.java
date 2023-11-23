package entity;
import java.io.Serializable;
import java.util.*;

public class Camp implements Identifiable, Serializable{

	private CampInformation information;
	private boolean visible = false;
	private SuggestionDatabase suggestionDB;
	private EnquiryDatabase enquiriesDB;

    private boolean hasSomethingNothing;
    public boolean getHasSomethingNothing() {return this.hasSomethingNothing;}

	/**
	 * @param name Name of the camp
	 * @param location Location where the camp is held
	 * @param dates Camp dates (not registration date)
	 * @param closingDate Registration's Last Date
	 * @param participantSlots Total slots for participants (not camp committee)
	 * @param campCommSlots Total slots for camp committee members (not participants)
	 * @param staffInCharge The staff in charge of the camp (cannot be changed)
	 * @param openTo The faculty that the camp is opened to
	 */
	public Camp(String name, String location, Collection<Date> dates, Date closingDate, int participantSlots, int campCommSlots, Staff staffInCharge, Collection<Faculty> openTo) {
        this.information = new CampInformation(name, location, dates, closingDate, participantSlots, campCommSlots, staffInCharge, openTo);
        this.information.setCampID(UniqueIDGenerator.generate());

        this.suggestionDB = new SuggestionDatabase();
        this.enquiriesDB = new EnquiryDatabase();
	}

    public Camp(Staff staffInCharge) {
        this.information = new CampInformation(staffInCharge);
        this.information.setCampID(UniqueIDGenerator.generate());

        this.suggestionDB = new SuggestionDatabase();
        this.enquiriesDB = new EnquiryDatabase();
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
	public List<Date> getDates() {
        return this.information.getDates();
	}

	/**
	 * Set the Camp's event dates (not registration date).
     *
	 * @param dates Can be given in a Collection (abitrary/no order)
	 */
	public void setDates(Collection<Date> dates) {
        this.information.setDates(dates);
	}

    public Date getLastCampDate() {
        List<Date> campDates = getDates();
        return campDates.get(campDates.size() - 1);
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

    @Override
    public String getID() {
        return information.getCampID();
    }

    public String toString() {
        return "Camp #" + getID() + ": " + getName();
    }


}
