package entity;
public class Enquiry implements Identifiable {

	private String enquiryID;
	private String enquiry;
	private String reply;
    private String askedBy;

    public Enquiry(String enquiry, String userID) {
        this.enquiryID = UniqueIDGenerator.generate();
        this.enquiry = enquiry;
        this.askedBy = userID;
    }

    public String getEnquiry() {
        return this.enquiry;
    }

	public void setEnquiry(String text) {
	    this.enquiry = text;
    }

	public String getReply() {
		return this.reply;
	}

	public boolean reply(String reply) {
        this.reply = reply;
        return true;
	}

    public String getAskedBy() {
        return this.askedBy;
    }

    public boolean isResolved() {
        return this.reply != null;
    }

    @Override
    public String getID() {
        return enquiryID;
    }

}
