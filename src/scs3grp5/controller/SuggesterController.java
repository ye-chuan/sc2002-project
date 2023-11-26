package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages camp comm suggester actions for camp suggestions in the system
 * 
 */
public class SuggesterController {

    private String campID;
    /**
	 * 
	 * @param campID
	 * 
	 */
	public SuggesterController(String campID) {
		this.campID = campID;
	}


    /**
	 * creates suggestion
	 * @param userID
	 * @param text
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
	 * @param userID
	 * @param suggestionID
	 * @param newText
	 */
	public void edit(String userID, String suggestionID, String newText) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		Suggestion sug1 = sDB.getItem(suggestionID);

		sug1.setSuggestion(newText);
	}

	/**
	 * delete suggestion from database
	 * @param userID
	 * @param suggestionID
	 */
	public void delete(String userID, String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		Suggestion sug1 = sDB.getItem(suggestionID);

		sDB.remove(sug1);
	}
}
