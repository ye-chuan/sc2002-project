public class SuggestionController implements RequestController {

	private String suggestionID;
	private Suggestion suggestion;

	/**
	 * 
	 * @param userID
	 * @param suggestionID
	 */
	public bool approve(String userID, int suggestionID) {
		// TODO - implement SuggestionController.approve
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param suggestionID
	 */
	public bool reject(String userID, int suggestionID) {
		// TODO - implement SuggestionController.reject
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param suggestionID
	 */
	public suggestionID view(String userID, int suggestionID) {

	}

	/**
	 * 
	 * @param userID
	 * @param text
	 */
	public suggestionID create(String userID, String text) {
		// TODO - implement SuggestionController.create
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param suggestionID
	 * @param newText
	 */
	public bool edit(String userID, int suggestionID, String newText) {
		// TODO - implement SuggestionController.edit
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 * @param suggestionID
	 */
	public bool delete(String userID, int suggestionID) {
		// TODO - implement SuggestionController.delete
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param suggestionID
	 */
	public SuggestionStatus getStatus(int suggestionID) {
		// TODO - implement SuggestionController.getStatus
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param suggestionID
	 */
	public bool isValidSuggestion(int suggestionID) {
		// TODO - implement SuggestionController.isValidSuggestion
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campID
	 */
	public Collection<suggestionID> getPendingSuggestionByCamp(String campID) {

	}

	/**
	 * 
	 * @param userID
	 */
	public Collection<suggestionID> getEnquiryByCampComm(String userID) {

	}

	/**
	 * 
	 * @param suggestionID
	 */
	public String getSuggestionText(int suggestionID) {
		// TODO - implement SuggestionController.getSuggestionText
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param suggestionID
	 */
	public String getSuggestionCreator(int suggestionID) {
		// TODO - implement SuggestionController.getSuggestionCreator
		throw new UnsupportedOperationException();
	}

}