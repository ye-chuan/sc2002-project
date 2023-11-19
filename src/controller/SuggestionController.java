import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


import entity.Camp;
import entity.CampDatabase;
import entity.CampMembershipDatabase;
import entity.Suggestion;
import entity.SuggestionDatabase;
import entity.SuggestionStatus;


public class SuggestionController {

	private UserController userCont;
	private PointController pointCont;

	/**
	 * 
	 * @param userID
	 * @param suggestionID
	 */
	public void approve(String suggestionID) {
		SuggestionDatabase sDB = new SuggestionDatabase();
		Suggestion sug1 = sDB.getItem(suggestionID);

		sug1.approve();
		pointCont.approveSuggestion(sug1.getSuggestedBy());
	}

	/**
	 * 
	 * @param userID
	 * @param suggestionID
	 */
	public void reject(String suggestionID) {
		SuggestionDatabase sDB = new SuggestionDatabase();
		Suggestion sug1 = sDB.getItem(suggestionID);

		sug1.reject();
		pointCont.rejectSuggestion(sug1.getSuggestedBy());
	}

	/**
	 * 
	 * @param userID
	 * @param text
	 */
	public String create(String userID, String text) {
		SuggestionDatabase sDB = new SuggestionDatabase();
		Suggestion sug1 = new Suggestion(text, userID);
		sDB.add(sug1);

		return sug1.getID();
	}

	/**
	 * 
	 * @param userID
	 * @param suggestionID
	 * @param newText
	 */
	public void edit(String userID, String suggestionID, String newText) throws Exception {
		SuggestionDatabase sDB = new SuggestionDatabase();
		Suggestion sug1 = sDB.getItem(suggestionID);

		if (sug1.getSuggestedBy() != userID) {
			throw new Exception("Suggestion is not created by user");
		}
		if (sug1.getStatus() != SuggestionStatus.PENDING) {
			throw new Exception("Suggestion has been {sug1.getStatus()}");
		}
		sug1.setSuggestion(newText);
	}

	/**
	 * 
	 * @param userID
	 * @param suggestionID
	 */
	public void delete(String userID, String suggestionID) throws Exception {
		SuggestionDatabase sDB = new SuggestionDatabase();
		Suggestion sug1 = sDB.getItem(suggestionID);

		if (sug1.getSuggestedBy() != userID) {
			throw new Exception("Suggestion is not created by user");
		}
		if (sug1.getStatus() != SuggestionStatus.PENDING) {
			throw new Exception("Suggestion has been {sug1.getStatus()}");
		}
		sDB.remove(sug1);
	}

	/**
	 * 
	 * @param suggestionID
	 */
	public SuggestionStatus getStatus(String suggestionID) {
		SuggestionDatabase sDB = new SuggestionDatabase();
		Suggestion sug1 = sDB.getItem(suggestionID);

		return sug1.getStatus();
	}

	/**
	 * 
	 * @param suggestionID
	 */
	public boolean isValidSuggestion(String suggestionID) {
		SuggestionDatabase sDB = new SuggestionDatabase();
		if (sDB.getItem(suggestionID)== null) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * 
	 * @param campID
	 */
	public Collection<String> getPendingSuggestionByCamp(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Camp c1 = cDB.getItem(campID);
		Iterator<Suggestion> sugIterator = c1.getSuggestionDB().getPendingSuggestions().iterator();
		Collection<String> suggestionIDList = new ArrayList<String>();

		while (sugIterator.hasNext()) {
			Suggestion sug1 = sugIterator.next();
			suggestionIDList.add(sug1.getID());
		}
		
		return suggestionIDList;
	}

	/**
	 * 
	 * @param userID
	 */
	public Collection<String> getEnquiryByCampComm(String userID) throws Exception{
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		String c1String = userCont.getStudentCommitteeCampID(userID);

		if (c1String==null) {
			throw new Exception("student is not a committee");
		}
		else {
			Camp c1 = cDB.getItem(c1String);
			Iterator<Suggestion> sugIterator = c1.getSuggestionDB().getPendingSuggestions().iterator();
			Collection<String> suggestionIDList = new ArrayList<String>();

			while (sugIterator.hasNext()) {
				Suggestion sug1 = sugIterator.next();
				suggestionIDList.add(sug1.getID());
			}
		
		return suggestionIDList;
		}
		
	}

	/**
	 * 
	 * @param suggestionID
	 */
	public String getSuggestionText(String suggestionID) {
		SuggestionDatabase sDB = new SuggestionDatabase();
		return sDB.getItem(suggestionID).getSuggestion();
	}

	/**
	 * 
	 * @param suggestionID
	 */
	public String getSuggestionCreator(String suggestionID) {
		SuggestionDatabase sDB = new SuggestionDatabase();
		return sDB.getItem(suggestionID).getSuggestedBy();
	}

}