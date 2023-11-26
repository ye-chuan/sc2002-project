package scs3grp5.entity;

import java.util.*;

/**
 * A database for storing Suggestions
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class SuggestionDatabase extends Database<Suggestion> {

    /**
     * @return All pending suggestions in this database
     */
	public Collection<Suggestion> getPendingSuggestions() {
        return filterByStatus(items.values(), SuggestionStatus.PENDING);
    }

    /**
     * @return All approved suggestions in this database
     */
	public Collection<Suggestion> getApprovedSuggestions() {
        return filterByStatus(items.values(), SuggestionStatus.APPROVED);
	}

    /**
     * @return All rejected suggestions in this database
     */
	public Collection<Suggestion> getRejectedSuggestions() {
        return filterByStatus(items.values(), SuggestionStatus.REJECTED);
	}

    /**
     * Get all pending suggestions by a specific user
     * @param The user that suggested
     * @return All pending suggestions by this user
     */
	public Collection<Suggestion> getPendingSuggestionsBy(String userID) {
        Collection<Suggestion> filtered = filterByUserID(items.values(), userID);
        filtered = filterByStatus(filtered, SuggestionStatus.PENDING);
        return filtered;
	}

    /**
     * Get all approved suggestions by a specific user
     * @param The user that suggested
     * @return All approved suggestions by this user
     */
	public Collection<Suggestion> getApprovedSuggestionsBy(String userID) {
        Collection<Suggestion> filtered = filterByUserID(items.values(), userID);
        filtered = filterByStatus(filtered, SuggestionStatus.APPROVED);
        return filtered;
	}

    /**
     * Get all rejected suggestions by a specific user
     * @param The user that suggested
     * @return All rejected suggestions by this user
     */
	public Collection<Suggestion> getRejectedSuggestionsBy(String userID) {
        Collection<Suggestion> filtered = filterByUserID(items.values(), userID);
        filtered = filterByStatus(filtered, SuggestionStatus.REJECTED);
        return filtered;
	}

    /**
     * Helper static method to filter a Collection of suggestions by Status.
     * @param suggestions Collection of Suggestion
     * @param status The status to keep
     * @return Only suggestions with the specified status
     */
    private static Collection<Suggestion> filterByStatus(Collection<Suggestion> suggestions, SuggestionStatus status) {
        Collection<Suggestion> ret = new ArrayList<Suggestion>();
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getStatus() == status) {
                ret.add(suggestion);
            }
        }
        return ret;
    }

    /**
     * Helper static method to filter a Collection of suggestions by the User that suggested.
     * @param suggestions Collection of Suggestion
     * @param userID ID of the User that suggested
     * @return Only suggestions with the specified user
     */
    private static Collection<Suggestion> filterByUserID(Collection<Suggestion> suggestions, String userID) {
        Collection<Suggestion> ret = new ArrayList<Suggestion>();
        for (Suggestion suggestion : suggestions) {
            if (suggestion.getSuggestedBy().equals(userID)) {
                ret.add(suggestion);
            }
        }
        return ret;
    }
}
