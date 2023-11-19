import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import entity.CampDatabase;
import entity.CampMembershipDatabase;
import entity.Enquiry;
import entity.EnquiryDatabase;
import entity.Student;
import entity.User;
import entity.UserDatabase;


public class EnquiryController {

	private PointController pointCont; 
	/**
	 * 
	 * @param userID
	 * @param enquiryID
	 * @param reply
	 */
	public void reply(String userID, String enquiryID, String reply) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		UserDatabase uDB = new UserDatabase();
		Enquiry enq1 = eDB.getItem(enquiryID);
		User u1 = uDB.getItem(userID);

		enq1.reply(reply);
		if (u1 instanceof Student) {
			pointCont.replyEnquiry(userID);
		}
	}

	/**
	 * 
	 * @param userID
	 * @param text
	 */
	public String create(String userID, String text) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		Enquiry enq1 = new Enquiry(text, userID);
		eDB.add(enq1);

		return enq1.getID();
	}

	/**
	 * 
	 * @param userID
	 * @param enquiryID
	 * @param newText
	 */
	public void edit(String userID, String enquiryID, String newText) throws Exception{
		EnquiryDatabase eDB = new EnquiryDatabase();
		Enquiry enq1 = eDB.getItem(enquiryID);

		if (enq1.isResolved()) {
			throw new Exception("Enquiry is already resolved");
		}
		if (enq1.getAskedBy() == userID) {
			throw new Exception("Enquiry is not created by user");
		}
		enq1.setEnquiry(newText);
	}

	/**
	 * 
	 * @param userID
	 * @param enquiryID
	 */
	public void delete(String userID, String enquiryID) throws Exception{
		EnquiryDatabase eDB = new EnquiryDatabase();
		Enquiry enq1 = eDB.getItem(enquiryID);
	

		if (enq1.isResolved()) {
			throw new Exception("Enquiry is already resolved");
		}
		if (enq1.getAskedBy() == userID) {
			throw new Exception("Enquiry is not created by user");
		}
		eDB.remove(enq1);
	
	}

	/**
	 * 
	 * @param enquiryID
	 */
	public boolean getStatus(String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		Enquiry enq1 = eDB.getItem(enquiryID);
		
		return enq1.isResolved();
		

	}

	/**
	 * 
	 * @param enquiryID
	 */
	public boolean isValidEnquiry(String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		if (eDB.getItem(enquiryID)== null) {
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
	public Collection<String> getPendingEnquiryByCamp(String campID) {
		CampMembershipDatabase cmemberDB = new CampMembershipDatabase();
		CampDatabase cDB = new CampDatabase(cmemberDB);
		Iterator<Enquiry> enqIterator = cDB.getItem(campID).getEnquiryDB().getUnresolvedEnquiries().iterator();
		Collection<String> enquiryIDList = new ArrayList<String>();

		while (enqIterator.hasNext()) {
			Enquiry enq1 = enqIterator.next();
			enquiryIDList.add(enq1.getID());
		}
		
		return enquiryIDList;
	}
	

	/**
	 * 
	 * @param userID
	 */
	public Collection<String> getEnquiryByStudent(String userID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		Iterator<Enquiry> enqIterator = eDB.getUnresolvedEnquiriesBy(userID).iterator();
		Collection<String> enquiryIDList = new ArrayList<String>();;

		while (enqIterator.hasNext()) {
			Enquiry enq1 = enqIterator.next();
			enquiryIDList.add(enq1.getID());
		}
		
		return enquiryIDList;
	}

	/**
	 * 
	 * @param enquiryID
	 */
	public String getEnquiryCreator(String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		return eDB.getItem(enquiryID).getAskedBy();

	}

	/**
	 * 
	 * @param enquiryID
	 */
	public String getEnquiryText(String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		return eDB.getItem(enquiryID).getEnquiry();
	}

	/**
	 * 
	 * @param enquiryID
	 */
	public String getEnquiryReply(String enquiryID) {
		EnquiryDatabase eDB = new EnquiryDatabase();
		return eDB.getItem(enquiryID).getReply();
	}

}