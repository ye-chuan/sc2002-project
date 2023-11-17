public class EnquiryController implements RequestController {

	private String enquiryID;
	private Enquiry enquiry;

	/**
	 * 
	 * @param userID
	 * @param enquiryID
	 * @param reply
	 */
	public bool reply(String userID, int enquiryID, String reply) {
		// TODO - implement EnquiryController.reply
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param enquiryID
	 */
	public Enquiry view(String userID, int enquiryID) {
		// TODO - implement EnquiryController.view
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param text
	 */
	public enquiryID create(String userID, String text) {
		// TODO - implement EnquiryController.create
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param enquiryID
	 * @param newText
	 */
	public bool edit(String userID, String enquiryID, String newText) {
		// TODO - implement EnquiryController.edit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param enquiryID
	 */
	public bool delete(String userID, String enquiryID) {
		// TODO - implement EnquiryController.delete
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param enquiryID
	 */
	public bool getStatus(int enquiryID) {
		// TODO - implement EnquiryController.getStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param enquiryID
	 */
	public bool isValidEnquiry(String enquiryID) {
		// TODO - implement EnquiryController.isValidEnquiry
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campID
	 */
	public Collection<Enquiry> getPendingEnquiryByCamp(String campID) {
		// TODO - implement EnquiryController.getPendingEnquiryByCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 */
	public Collection<Enquiry> getEnquiryByStudent(String userID) {
		// TODO - implement EnquiryController.getEnquiryByStudent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param enquiryID
	 */
	public String getEnquiryCreator(int enquiryID) {
		// TODO - implement EnquiryController.getEnquiryCreator
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param enquiryID
	 */
	public String getEnquiryText(int enquiryID) {
		// TODO - implement EnquiryController.getEnquiryText
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param enquiryID
	 */
	public String getEnquiryReply(int enquiryID) {
		// TODO - implement EnquiryController.getEnquiryReply
		throw new UnsupportedOperationException();
	}

}