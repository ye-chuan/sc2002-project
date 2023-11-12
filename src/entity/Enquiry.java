public class Enquiry implements Identifiable {

	private String enquiryID;
	private String text;
	private String reply = None;

	/**
	 * 
	 * @param text
	 */
	public boolean setText(String text) {
		// TODO - implement Enquiry.setText
		throw new UnsupportedOperationException();
	}

	public String getReply() {
		return this.reply;
	}

	/**
	 * 
	 * @param reply
	 */
	public boolean reply(String reply) {
		// TODO - implement Enquiry.reply
		throw new UnsupportedOperationException();
	}

}