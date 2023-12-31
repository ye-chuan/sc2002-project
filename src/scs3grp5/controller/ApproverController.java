package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;


/**
 * Handles the approval and rejection of suggestions by Staff-In-Charge of Camp
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class ApproverController {

    private PointController pointCont = new PointController();
    private String campID;

    /**
	 * 
	 * constructs ApproverController Object with campID
	 * @param campID The unique ID of the student
	 * 
	 */
	public ApproverController(String campID) {
		this.campID = campID;
	}

    /**
	 * 
	 * approves suggestion and add points to the Camp Committee Suggester
	 * @param suggestionID The unique ID of the suggestion
	 * @see PointController#approveSuggestion(String)
	 * 
	 */
	public void approve(String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		Suggestion sug1 = sDB.getItem(suggestionID);

		sug1.approve();
		pointCont.approveSuggestion(sug1.getSuggestedBy());
	}

	/**
	 * 
	 * rejects suggestion and add points to the Camp Committee Suggester
	 * @param suggestionID The unique ID of the suggestion
	 * @see PointController#rejectSuggestion(String)
	 */
	public void reject(String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		Suggestion sug1 = sDB.getItem(suggestionID);

		sug1.reject();
		pointCont.rejectSuggestion(sug1.getSuggestedBy());
	}

}
