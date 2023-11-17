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
	public Suggestion view(String userID, int suggestionID) {
		// TODO - implement SuggestionController.view
		throw new UnsupportedOperationException();
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
	public ArrayList<Suggestion> getPendingSuggestionByCamp(String campID) {
		// TODO - implement SuggestionController.getPendingSuggestionByCamp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userID
	 */
	public ArrayList<Suggestion> getEnquiryByCampComm(String userID) {
		// TODO - implement SuggestionController.getEnquiryByCampComm
		throw new UnsupportedOperationException();
	}

}