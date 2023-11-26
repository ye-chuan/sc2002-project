package scs3grp5.entity;

import java.io.Serializable;

import scs3grp5.Main;

public class Suggestion implements Identifiable, Serializable {

	private String suggestionID;
	private String suggestion;
	private SuggestionStatus status;
    private String suggestedBy;

    public Suggestion(String suggestion, String userID) {
        this.suggestionID = Main.getIdGenerator().generate();
        this.suggestion = suggestion;
        this.status = SuggestionStatus.PENDING;
        this.suggestedBy = userID;
    }

	public SuggestionStatus getStatus() {
        return status;
	}

	public void setStatus(SuggestionStatus status) {
        this.status = status;
	}

	public boolean approve() {
        status = SuggestionStatus.APPROVED;
        return true;
	}

	public boolean reject() {
        status = SuggestionStatus.REJECTED;
        return true;
	}

    public String getSuggestion() {
        return suggestion;
    }

	public void setSuggestion(String suggestion) {
	    this.suggestion = suggestion;
    }

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
