package scs3grp5.entity;

import java.io.Serializable;

import scs3grp5.Main;

/**
 * Represents a Suggestion by a Student
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class Suggestion implements Identifiable, Serializable {

    /** The unique ID of this suggestion */
	private String suggestionID;
    /** The text of this suggestion */
	private String suggestion;
    /** Whether the suggestion is pending, approved or rejected */
	private SuggestionStatus status;
    /** The ID of the Student who suggested this */
    private String suggestedBy;

    /**
     * Constructs a suggestion
     * @param suggestion The suggestion text
     * @param userID The ID of the user who suggested
     */
    public Suggestion(String suggestion, String userID) {
        this.suggestionID = Main.getIdGenerator().generate();
        this.suggestion = suggestion;
        this.status = SuggestionStatus.PENDING;
        this.suggestedBy = userID;
    }

    /**
     * @return The status of this suggestion (approved, rejected, pending)
     */
	public SuggestionStatus getStatus() {
        return status;
	}

    /**
     * Set the status of this suggestion (approved, rejected, pending)
     */
	public void setStatus(SuggestionStatus status) {
        this.status = status;
	}

    /**
     * Approve this suggestion
     * @return If approval was successful
     */
	public boolean approve() {
        status = SuggestionStatus.APPROVED;
        return true;
	}

    /**
     * Reject this suggestion
     * @return If rejection was successful
     */
	public boolean reject() {
        status = SuggestionStatus.REJECTED;
        return true;
	}

    /**
     * @return The suggestion text of this suggestion
     */
    public String getSuggestion() {
        return suggestion;
    }

    /**
     * Set the suggestion text of this suggestion
     */
	public void setSuggestion(String suggestion) {
	    this.suggestion = suggestion;
    }

    /**
     * @return The author of this suggestion
     */
    public String getSuggestedBy() {
        return suggestedBy;
    }
    
    @Override
    public String getID() {
        return suggestionID;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Suggestion))
            return false;
        return this.getID().equals(((Suggestion)obj).getID());
    }

    @Override
    public int hashCode() {
        return this.getID().hashCode();
    }


}
