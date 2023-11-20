package entity;

import java.io.Serializable;

public class Suggestion implements Identifiable, Serializable {

	private String suggestionID;
	private String suggestion;
	private SuggestionStatus status;
    private String suggestedBy;

    public Suggestion(String suggestion, String userID) {
        this.suggestionID = UniqueIDGenerator.generate();
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

}
