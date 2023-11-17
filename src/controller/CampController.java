import entity.User;

public class CampController {

	private Camp camp;
	private String campID;

	/**
	 * 
	 * @param campID
	 */
	public bool isValidCampID(String campID) {
		// TODO - implement CampController.isValidCampID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 */
	public bool registerAsParticipant(String userID) {
		// TODO - implement CampController.registerAsParticipant
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 */
	public bool registerAsCommittee(String userID) {
		// TODO - implement CampController.registerAsCommittee
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param campID
	 */
	public bool withdraw(String userID, int campID) {
		// TODO - implement CampController.withdraw
		throw new UnsupportedOperationException();
	}

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
	public campID create(String name, String location, Collection<String> dates, String closingDate, int attendeeSlots, int campCommSlots, staffID staffInCharge, Collection<Faculty> openTo) {
		// TODO - implement CampController.create
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param campID
	 */
	public bool IsEditable(String userID, int campID) {
		// TODO - implement CampController.IsEditable
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param campID
	 */
	public bool delete(String userID, int campID) {
		// TODO - implement CampController.delete
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param campID
	 */
	public bool changeCampInCharge(String userID, int campID) {
		// TODO - implement CampController.changeCampInCharge
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campID
	 * @param newName
	 */
	public bool changeName(int campID, String newName) {
		// TODO - implement CampController.changeName
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campID
	 * @param visibility
	 */
	public bool toggleVisibility(int campID, bool visibility) {
		// TODO - implement CampController.toggleVisibility
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campID
	 * @param dates
	 */
	public bool changeDates(int campID, Collection<String> dates) {
		// TODO - implement CampController.changeDates
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campID
	 * @param String
	 */
	public bool changeLocation(int campID, int String) {
		// TODO - implement CampController.changeLocation
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campID
	 * @param String
	 */
	public bool changeDescription(int campID, int String) {
		// TODO - implement CampController.changeDescription
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campID
	 * @param slots
	 */
	public bool changeCampCommSlots(int campID, int slots) {
		// TODO - implement CampController.changeCampCommSlots
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campID
	 * @param slots
	 */
	public void changeCampParticipantSlots(int campID, int slots) {
		// TODO - implement CampController.changeCampParticipantSlots
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campID
	 * @param dates
	 */
	public bool changeEndDate(int campID, string dates) {
		// TODO - implement CampController.changeEndDate
		throw new UnsupportedOperationException();
	}

}