package scs3grp5.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


import scs3grp5.Main;
import scs3grp5.entity.*;


public class SuggestionController {

	private PointController pointCont = new PointController();
	private String campID;

	/**
	 * 
	 * @param campID
	 * 
	 */
	public SuggestionController(String campID) {
		this.campID = campID;
	}

	/**
	 * 
	 * @param userID
	 * @param suggestionID
	 */
	public void approve(String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
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
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
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
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
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
	public void edit(String userID, String suggestionID, String newText) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		Suggestion sug1 = sDB.getItem(suggestionID);

		// if (sug1.getSuggestedBy() != userID) {
		// 	throw new Exception("Suggestion is not created by user");
		// }
		// if (sug1.getStatus() != SuggestionStatus.PENDING) {
		// 	throw new Exception(String.format("Suggestion has been {}",sug1.getStatus()));
		// }
		sug1.setSuggestion(newText);
	}

	/**
	 * 
	 * @param userID
	 * @param suggestionID
	 */
	public void delete(String userID, String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		Suggestion sug1 = sDB.getItem(suggestionID);

		// if (sug1.getSuggestedBy() != userID) {
		// 	throw new Exception("Suggestion is not created by user");
		// }
		// if (sug1.getStatus() != SuggestionStatus.PENDING) {
		// 	throw new Exception(String.format("Suggestion has been {}",sug1.getStatus()));
		// }
		sDB.remove(sug1);
	}

	/**
	 * 
	 * @param suggestionID
	 */
	public String getStatus(String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
		Suggestion sug1 = sDB.getItem(suggestionID);

		return sug1.getStatus().name();
	}

	/**
	 * 
	 * @param enquiryID
	 */
	public boolean isOwner(String userID, String suggestionID) {
		return getSuggestionCreator(suggestionID)==userID;

	}

	/**
	 * 
	 * @param suggestionID
	 */
	public boolean isValidSuggestion(String suggestionID) {
		SuggestionDatabase sDB = Main.getCampDB().getItem(campID).getSuggestionDB();
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
	public Collection<String> getPendingSuggestions(String userID) {
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
	 * 
	 * @param campID
	 */
	public Collection<String> getRejectedSuggestions(String userID) {
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
	 * 
	 * @param campID
	 */
	public Collection<String> getApprovedSuggestion(String userID) {
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
	 * 
	 * @param campID
	 */
	public Collection<String> getAllSuggestion(String userID) {
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

	// /**
	//  * 
	//  * @param userID
	//  */
	// public Collection<String> getMyPendingSuggestion(String campID, String userID) throws Exception{
	// 	CampMembershipDatabase cmemberDB = Main.getMemberDB();
	// 	CampDatabase cDB = Main.getCampDB();
	// 	UserDatabase uDB = Main.getUserDB();
	// 	User u1 = uDB.getItem(userID);
	// 	Collection<Suggestion> sugList = new ArrayList<Suggestion>();
	// 	if (u1 instanceof Student) { 
	// 		Student s1 = (Student) u1; 
		
	// 		Camp c1 = cmemberDB.getCampWithCampCommMember(s1, Date.today());
	// 		if (c1.getID() == campID) {
	// 			sugList = c1.getSuggestionDB().getPendingSuggestionsBy(userID);
	// 		}
	// 		else { 
	// 			throw new Exception("Not a camp committee");
	// 		}
	// 	}
	// 	else {
	// 		throw new Exception("Not a camp committee");
	// 	}
		
	// 	return sortByNameIDList(sugList);
	
	// }

	// /**
	//  * 
	//  * @param userID
	//  */
	// public Collection<String> getMyRejectedSuggestion(String campID, String userID) throws Exception{
	// 	CampMembershipDatabase cmemberDB = Main.getMemberDB();
	// 	CampDatabase cDB = Main.getCampDB();
	// 	UserDatabase uDB = Main.getUserDB();
	// 	User u1 = uDB.getItem(userID);
	// 	Collection<Suggestion> sugList = new ArrayList<Suggestion>();
	// 	if (u1 instanceof Student) { 
	// 		Student s1 = (Student) u1; 
		
	// 		Camp c1 = cmemberDB.getCampWithCampCommMember(s1, Date.today());
	// 		if (c1.getID() == campID) {
	// 			sugList = c1.getSuggestionDB().getRejectedSuggestionsBy(userID);
	// 		}
	// 		else { 
	// 			throw new Exception("Not a camp committee");
	// 		}
	// 	}
	// 	else {
	// 		throw new Exception("Not a camp committee");
	// 	}
		
	// 	return sortByNameIDList(sugList);
	
	// }

	// /**
	//  * 
	//  * @param userID
	//  */
	// public Collection<String> getMySuggestion(String campID, String userID) throws Exception{
	// 	CampMembershipDatabase cmemberDB = Main.getMemberDB();
	// 	CampDatabase cDB = Main.getCampDB();
	// 	UserDatabase uDB = Main.getUserDB();
	// 	User u1 = uDB.getItem(userID);
	// 	Collection<Suggestion> sugList = new ArrayList<Suggestion>();
	// 	if (u1 instanceof Student) { 
	// 		Student s1 = (Student) u1; 
		
	// 		Camp c1 = cmemberDB.getCampWithCampCommMember(s1, Date.today());
	// 		if (c1.getID() == campID) {
	// 			sugList = c1.getSuggestionDB().getRejectedSuggestionsBy(userID);
	// 			sugList.addAll(c1.getSuggestionDB().getApprovedSuggestionsBy(userID));
	// 			sugList.addAll(c1.getSuggestionDB().getPendingSuggestionsBy(userID));
	// 		}
	// 		else { 
	// 			throw new Exception("Not a camp committee");
	// 		}
	// 	}
	// 	else {
	// 		throw new Exception("Not a camp committee");
	// 	}
		
	// 	return sortByNameIDList(sugList);
	
	// }

	// /**
	//  * 
	//  * @param userID
	//  */
	// public Collection<String> getMyApprovedSuggestion(String campID, String userID) throws Exception{
	// 	CampMembershipDatabase cmemberDB = Main.getMemberDB();
	// 	CampDatabase cDB = Main.getCampDB();
	// 	UserDatabase uDB = Main.getUserDB();
	// 	User u1 = uDB.getItem(userID);
	// 	Collection<Suggestion> sugList = new ArrayList<Suggestion>();
	// 	if (u1 instanceof Student) { 
	// 		Student s1 = (Student) u1; 
		
	// 		Camp c1 = cmemberDB.getCampWithCampCommMember(s1, Date.today());
	// 		if (c1.getID() == campID) {
	// 			sugList = c1.getSuggestionDB().getApprovedSuggestionsBy(userID);
	// 		}
	// 		else { 
	// 			throw new Exception("Not a camp committee");
	// 		}
	// 	}
	// 	else {
	// 		throw new Exception("Not a camp committee");
	// 	}
		
	// 	return sortByNameIDList(sugList);
	
	// }

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
	 * @param campList
	 */
	private Collection<String> sortByNameIDList(Collection<Suggestion> sugList) {
		Collections.sort((ArrayList<Suggestion>)sugList, Comparator.comparing(Suggestion::getSuggestedBy));
		ArrayList<String> suggestionIDList = new ArrayList<String>();
		for (Suggestion s: sugList)
		{
			suggestionIDList.add(s.getID());
		}
		return suggestionIDList;
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