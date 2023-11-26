package scs3grp5.controller;

import java.util.*;

import scs3grp5.Main;
import scs3grp5.entity.*;

/**
 * Manages suggestion list viewing for camps in the system
 * 
 */
public class SuggestionListController {

    private String campID;

    /**
	 * Constructor for {@link SuggestionListController} object
	 * @param campID
	 * 
	 */
	public SuggestionListController(String campID) {
		this.campID = campID;
	}
    
    /**
	 * get pending suggestions based on user Camp Role in camp. <p>
	 * Staff gets a list of pending enquiries in camp <p>
	 * Camp committee gets their pending enquiries in camp
	 * @param userID
	 * @return List of sorted pending suggestions ID by name of suggester
	 */
	public List<String> getPendingSuggestions(String userID) {
		CampDatabase cDB = Main.getCampDB();
		CampController campCont = new CampController();
		Collection<Suggestion> sugList = new ArrayList<Suggestion>();

		Camp c1 = cDB.getItem(campID);
		if (campCont.isCommittee(userID, campID)) {
			sugList = c1.getSuggestionDB().getPendingSuggestions();
		}
		else {
			sugList = c1.getSuggestionDB().getPendingSuggestionsBy(userID);
		}

		return sortByNameIDList(sugList);
	}

	/**
	 * get rejected suggestions based on user Camp Role in camp. <p>
	 * Staff gets a list of rejected enquiries in camp <p>
	 * Camp committee gets their rejected enquiries in camp
	 * @param userID
	 * @return List of sorted rejected suggestions ID by name of suggester
	 */
	public List<String> getRejectedSuggestions(String userID) {
		CampDatabase cDB = Main.getCampDB();
		CampController campCont = new CampController();
		Collection<Suggestion> sugList = new ArrayList<Suggestion>();

		Camp c1 = cDB.getItem(campID);
		if (campCont.isCommittee(userID, campID)) {
			sugList = c1.getSuggestionDB().getRejectedSuggestions();
		}
		else {
			sugList = c1.getSuggestionDB().getRejectedSuggestionsBy(userID);
		}
		
		return sortByNameIDList(sugList);
	}

	/**
	 * get approved suggestions based on user Camp Role in camp. <p>
	 * Staff gets a list of approved enquiries in camp <p>
	 * Camp committee gets their approved enquiries in camp
	 * @param userID
	 * @return List of sorted approved suggestions ID by name of suggester
	 */
	public List<String> getApprovedSuggestion(String userID) {
		CampDatabase cDB = Main.getCampDB();
		CampController campCont = new CampController();
		Collection<Suggestion> sugList = new ArrayList<Suggestion>();

		Camp c1 = cDB.getItem(campID);
		if (campCont.isCommittee(userID, campID)) {
			sugList = c1.getSuggestionDB().getApprovedSuggestions();
		}
		else {
			sugList = c1.getSuggestionDB().getApprovedSuggestionsBy(userID);
		}
		
		return sortByNameIDList(sugList);
	}

	/**
	 * get all suggestions based on user Camp Role in camp. <p>
	 * Staff gets a list of all enquiries in camp <p>
	 * Camp committee gets all their enquiries in camp
	 * @param userID
	 * @return List of sorted suggestions ID by name of suggester
	 */
	public List<String> getAllSuggestion(String userID) {
		CampDatabase cDB = Main.getCampDB();
		Camp c1 = cDB.getItem(campID);
		Collection<Suggestion> sugList = new ArrayList<Suggestion>();
		CampController campCont = new CampController();

		if (campCont.isCommittee(userID, campID)) {
			sugList.addAll(c1.getSuggestionDB().getApprovedSuggestions());
			sugList.addAll(c1.getSuggestionDB().getRejectedSuggestions());
			sugList.addAll(c1.getSuggestionDB().getPendingSuggestions());
		}
		else {
			sugList.addAll(c1.getSuggestionDB().getApprovedSuggestionsBy(userID));
			sugList.addAll(c1.getSuggestionDB().getRejectedSuggestionsBy(userID));
			sugList.addAll(c1.getSuggestionDB().getPendingSuggestionsBy(userID));
		}
		

		return sortByNameIDList(sugList);
	}

    /**
	 * sorts suggestions alphabetically by name of suggester
	 * @param suggestionList
	 * @return List of sorted suggestion IDs
	 */
	private List<String> sortByNameIDList(Collection<Suggestion> sugList) {
		Collections.sort((ArrayList<Suggestion>)sugList, Comparator.comparing(Suggestion::getSuggestedBy));
		List<String> suggestionIDList = new ArrayList<String>();
		for (Suggestion s: sugList)
		{
			suggestionIDList.add(s.getID());
		}
		return suggestionIDList;
	}

}
