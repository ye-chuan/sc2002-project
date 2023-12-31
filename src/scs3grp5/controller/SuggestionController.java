package scs3grp5.controller;

import java.util.List;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages suggestions in the system
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class SuggestionController {
	
	private String campID;
	private ApproverController approverCont;
	private SuggesterController suggesterCont;
	private SuggestionListController suggestionListCont;

	/**
	 * Constructor for {@link SuggestionController} object
	 * @param campID The unique ID for the Camp
	 * 
	 */
	public SuggestionController(String campID) {
		this.campID = campID;
		approverCont = new ApproverController(campID);
		suggesterCont = new SuggesterController(campID);
		suggestionListCont = new SuggestionListController(campID);
	}

	/**
	 * approves suggestion 
	 * @param suggestionID The unique ID of the Suggestion
	 * @see ApproverController#approve(String)
	 */
	public void approve(String suggestionID) {
		approverCont.approve(suggestionID);
	}

	/**
	 * 
	 * rejects suggestion
	 * @param suggestionID The unique ID of the Suggestion
	 * @see ApproverController#reject(String)
	 */
	public void reject(String suggestionID) {
		approverCont.reject(suggestionID);
	}

	/**
	 * creates suggestion
	 * @param userID The unique ID of the User
	 * @param text The text for the Suggestion
	 * @return created suggestion ID
	 * @see SuggesterController#create(String, String)
	 */
	public String create(String userID, String text) {
		return suggesterCont.create(userID,text);
	}

	/**
	 * edit suggestion
	 * @param userID The unique ID of the User
	 * @param suggestionID The unique ID of the Suggestion
	 * @param newText The new text for the Suggestion
	 * @see SuggesterController#edit(String, String, String)
	 */
	public void edit(String userID, String suggestionID, String newText) {
		suggesterCont.edit(userID, suggestionID, newText);
	}

	/**
	 * delete suggestion from database
	 * @param userID The unique ID of the User
	 * @param suggestionID The unique ID of the Suggestion
	 * @see SuggesterController#delete(String, String)
	 */
	public void delete(String userID, String suggestionID) {
		suggesterCont.delete(userID, suggestionID);
	}

	/**
	 * get suggestion status whether it is REJECTED, PENDING or APPROVED
	 * @param suggestionID The unique ID of the Suggestion
	 * @return suggestion status
	 */
	public String getStatus(String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		Suggestion sug1 = sDB.getItem(suggestionID);

		return sug1.getStatus().name();
	}

	/**
	 * checks if user is owner of suggestion ID
	 * @param userID The unique ID of the User
	 * @param suggestionID The unique ID of the Suggestion
	 * @return true if user created the suggestion
	 */
	public boolean isOwner(String userID, String suggestionID) {
		return getSuggestionCreator(suggestionID).equals(userID);

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
	 * @param userID The unique ID of the User
	 * @return List of sorted pending suggestions ID by name of suggester
	 * @see SuggestionListController#getPendingSuggestions(String)
	 */
	public List<String> getPendingSuggestions(String userID) {
		return suggestionListCont.getPendingSuggestions(userID);
	}

	/**
	 * get rejected suggestions based on user Camp Role in camp. <p>
	 * Staff gets a list of rejected enquiries in camp <p>
	 * Camp committee gets their rejected enquiries in camp
	 * @param userID The unique ID of the User
	 * @return List of sorted rejected suggestions ID by name of suggester
	 * @see SuggestionListController#getRejectedSuggestions(String)
	 */
	public List<String> getRejectedSuggestions(String userID) {
		return suggestionListCont.getRejectedSuggestions(userID);
	}

	/**
	 * get approved suggestions based on user Camp Role in camp. <p>
	 * Staff gets a list of approved enquiries in camp <p>
	 * Camp committee gets their approved enquiries in camp
	 * @param userID The unique ID of the User
	 * @return List of sorted approved suggestions ID by name of suggester
	 * @see SuggestionListController#getApprovedSuggestion(String)
	 */
	public List<String> getApprovedSuggestion(String userID) {
		return suggestionListCont.getApprovedSuggestion(userID);
	}

	/**
	 * get all suggestions based on user Camp Role in camp. <p>
	 * Staff gets a list of all enquiries in camp <p>
	 * Camp committee gets all their enquiries in camp
	 * @param userID The unique ID of the User
	 * @return List of sorted suggestions ID by name of suggester
	 * @see SuggestionListController#getAllSuggestion(String)
	 */
	public List<String> getAllSuggestion(String userID) {
		return suggestionListCont.getAllSuggestion(userID);
	}
	
	/**
	 * get the suggestion text through suggestionID
	 * @param suggestionID The unique ID of the Suggestion
	 * @return text for the Suggestion
	 */
	public String getSuggestionText(String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		return sDB.getItem(suggestionID).getSuggestion();
	}

	/**
	 * get the suggestion creator through suggestionID
	 * @param suggestionID The unique ID of the Suggestion
	 * @return name of the suggester
	 */
	public String getSuggestionCreator(String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		return sDB.getItem(suggestionID).getSuggestedBy();
	}

	/**
	 * checks whether student is a camp participant or camp committee in camp
	 * @param studentID The unique ID of the Student
	 * @return Camp Role of the student in the camp specified
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