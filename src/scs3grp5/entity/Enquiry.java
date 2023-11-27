package scs3grp5.entity;

import java.io.Serializable;

import scs3grp5.Main;

/**
 * A representation for an enquiry, with the ability to reply 
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class Enquiry implements Identifiable, Serializable {

    /** The unique ID of this enquiry */
	private String enquiryID;
    /** The enquiry text of this enquiry */
	private String enquiry;
    /** The reply to this enquiry */
	private String reply;
    /** UserID of the asker */
    private String askedBy;
    /** UserID of the respondent */
    private String repliedBy;

    /** Constructs an Enquiry
     * @param enquiry The question
     * @param userID The asker's ID
     */
    public Enquiry(String enquiry, String userID) {
        this.enquiryID = Main.getIdGenerator().generate();
        this.enquiry = enquiry;
        this.askedBy = userID;
    }

    /**
     * @return The enquiry text / question
     */
    public String getEnquiry() {
        return this.enquiry;
    }

    /**
     * @param text The enquiry text
     * Set the enquiry text / question
     */
	public void setEnquiry(String text) {
	    this.enquiry = text;
    }

    /**
     * @return The reply to this enquiry
     */
	public String getReply() {
		return this.reply;
	}

    /**
     * Reply this enquiry
     * @param reply The reply string
     * @param respondentID The ID of the respondent
     * @return Whether the replying was successful
     */
	public boolean reply(String reply, String respondentID) {
        this.reply = reply;
        this.repliedBy = respondentID;
        return true;
	}

    /**
     * @return The user ID of the asker of this enquiry
     */
    public String getAskedBy() {
        return this.askedBy;
    }

    /**
     * @return The user ID of the replier of this enquiry
     */
    public String getRepliedBy() {
        return this.repliedBy;
    }

    /**
     * @return If this enquiry has already been responded to
     */
    public boolean isResolved() {
        return this.reply != null;
    }

    @Override
    public String getID() {
        return enquiryID;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Enquiry))
            return false;
        return this.getID().equals(((Enquiry)obj).getID());
    }

    @Override
    public int hashCode() {
        return this.getID().hashCode();
    }

}
