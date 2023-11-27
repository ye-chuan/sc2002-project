package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages camp comm suggester actions for camp suggestions in the system
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class SuggesterController {

    private String campID;
    /**
	 * Constructor for {@link SuggesterController} object
	 * @param campID The unique ID of the Camp
	 */
	public SuggesterController(String campID) {
		this.campID = campID;
	}


    /**
	 * creates suggestion
	 * @param userID The unique ID of the User
	 * @param text The text for the Suggestion
	 * @return created suggestion ID
	 */
	public String create(String userID, String text) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		Suggestion sug1 = new Suggestion(text, userID);
		sDB.add(sug1);

		return sug1.getID();
	}

	/**
	 * edit suggestion
	 * @param userID The unique ID of the User
	 * @param suggestionID The unique ID of the Suggestion
	 * @param newText The new text for the Suggestion to be edited
	 */
	public void edit(String userID, String suggestionID, String newText) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		Suggestion sug1 = sDB.getItem(suggestionID);

		sug1.setSuggestion(newText);
	}

	/**
	 * delete suggestion from database
	 * @param userID The unique ID of the User
	 * @param suggestionID The unique ID of the Suggestion
	 */
	public void delete(String userID, String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		Suggestion sug1 = sDB.getItem(suggestionID);

		sDB.remove(sug1);
	}
}
