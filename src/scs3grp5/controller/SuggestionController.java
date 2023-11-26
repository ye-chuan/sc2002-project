package scs3grp5.controller;

import java.util.List;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages suggestions in the system
 * 
 */
public class SuggestionController {
	
	private String campID;
	private ApproverController approverCont = new ApproverController(campID);
	private SuggesterController suggesterCont = new SuggesterController(campID);
	private SuggestionListController suggestionListCont = new SuggestionListController(campID);

	/**
	 * Constructor for {@link SuggestionController} object
	 * @param campID
	 * 
	 */
	public SuggestionController(String campID) {
		this.campID = campID;
	}

	/**
	 * 
	 * approves suggestion 
	 * @param suggestionID
	 * @see ApproverController#approve(String)
	 * 
	 */
	public void approve(String suggestionID) {
		approverCont.approve(suggestionID);
	}

	/**
	 * 
	 * rejects suggestion
	 * @param suggestionID
	 * @see ApproverController#reject(String)
	 */
	public void reject(String suggestionID) {
		approverCont.reject(suggestionID);
	}

	/**
	 * creates suggestion
	 * @param userID
	 * @param text
	 * @return created suggestion ID
	 * @see SuggesterController#create(String, String)
	 */
	public String create(String userID, String text) {
		return suggesterCont.create(userID,text);
	}

	/**
	 * edit suggestion
	 * @param userID
	 * @param suggestionID
	 * @param newText
	 * @see SuggesterController#edit(String, String, String)
	 */
	public void edit(String userID, String suggestionID, String newText) {
		suggesterCont.edit(userID, suggestionID, newText);
	}

	/**
	 * delete suggestion from database
	 * @param userID
	 * @param suggestionID
	 * @see SuggesterController#delete(String, String)
	 */
	public void delete(String userID, String suggestionID) {
		suggesterCont.delete(userID, suggestionID);
	}

	/**
	 * get suggestion status whether it is REJECTED, PENDING or APPROVED
	 * @param suggestionID
	 * @return suggestion status
	 */
	public String getStatus(String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		Suggestion sug1 = sDB.getItem(suggestionID);

		return sug1.getStatus().name();
	}

	/**
	 * checks if user is owner of suggestion ID
	 * @param enquiryID
	 * @return true if user created the suggestion
	 */
	public boolean isOwner(String userID, String suggestionID) {
		return getSuggestionCreator(suggestionID)==userID;

	}

	// /**
	//  * 
	//  * @param suggestionID
	//  */
	// public boolean isValidSuggestion(String suggestionID) {
	// 	SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
	// 	if (sDB.getItem(suggestionID)== null) {
	// 		return false;
	// 	}
	// 	else {
	// 		return true;
	// 	}
	// }

	/**
	 * get pending suggestions based on user Camp Role in camp. <p>
	 * Staff gets a list of pending enquiries in camp <p>
	 * Camp committee gets their pending enquiries in camp
	 * @param userID
	 * @return List of sorted pending suggestions ID by name of suggester
	 */
	public List<String> getPendingSuggestions(String userID) {
		return suggestionListCont.getPendingSuggestions(userID);
	}

	/**
	 * get rejected suggestions based on user Camp Role in camp. <p>
	 * Staff gets a list of rejected enquiries in camp <p>
	 * Camp committee gets their rejected enquiries in camp
	 * @param userID
	 * @return List of sorted rejected suggestions ID by name of suggester
	 */
	public List<String> getRejectedSuggestions(String userID) {
		return suggestionListCont.getRejectedSuggestions(userID);
	}

	/**
	 * 
	 * @param campID
	 */
	public List<String> getApprovedSuggestion(String userID) {
		return suggestionListCont.getApprovedSuggestion(userID);
	}

	/**
	 * 
	 * @param campID
	 */
	public List<String> getAllSuggestion(String userID) {
		return suggestionListCont.getAllSuggestion(userID);
	}
	
	/**
	 * 
	 * @param suggestionID
	 */
	public String getSuggestionText(String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		return sDB.getItem(suggestionID).getSuggestion();
	}

	/**
	 * 
	 * @param suggestionID
	 */
	public String getSuggestionCreator(String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		return sDB.getItem(suggestionID).getSuggestedBy();
	}

	/**
	 * 
	 * @param campID
	 */
	public CampRole getUserStatus(String studentID) {
		CampMembershipDatabase cmemberDB = Main.getMemberDB();
		CampDatabase cDB = Main.getCampDB();
		UserDatabase uDB = Main.getUserDB();
		Camp c1 = cDB.getItem(campID);
		Student s1 = (Student) uDB.getItem(studentID);
		CampRole s1Role = cmemberDB.getRoleInCamp(c1,s1);

		if (s1Role != null) {
			return s1Role;
		}
		else return null;

	}
}