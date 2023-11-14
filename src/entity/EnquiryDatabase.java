package entity;

import java.util.*;

public class EnquiryDatabase extends Database<Enquiry> {

	public Collection<Enquiry> getUnresolvedEnquiries() {
        Collection<Enquiry> ret = new ArrayList<Enquiry>();
        for (Enquiry enquiry : items.values()) {
            if (enquiry.getEnquiry() == null) {
                ret.add(enquiry);
            }
        }
        return ret;
	}

	public Collection<Enquiry> getResolvedEnquiries() {
        Collection<Enquiry> ret = new ArrayList<Enquiry>();
        for (Enquiry enquiry : items.values()) {
            if (enquiry.getEnquiry() != null) {
                ret.add(enquiry);
            }
        }
        return ret;
	}

	public Collection<Enquiry> getEnquiriesBy(String userID) {
        Collection<Enquiry> ret = new ArrayList<Enquiry>();
        for (Enquiry enquiry : items.values()) {
            if (enquiry.getAskedBy() == userID) {
                ret.add(enquiry);
            }
        }
        return ret;
	}

	public Collection<Enquiry> getUnresolvedEnquiriesBy(String userID) {
        Collection<Enquiry> ret = new ArrayList<Enquiry>();
        for (Enquiry enquiry : getEnquiriesBy(userID)) {
            if (!enquiry.isResolved()) {
                ret.add(enquiry);
            }
        }
        return ret;
    }

	public Collection<Enquiry> getResolvedEnquiriesBy(String userID) {
        Collection<Enquiry> ret = new ArrayList<Enquiry>();
        for (Enquiry enquiry : getEnquiriesBy(userID)) {
            if (enquiry.isResolved()) {
                ret.add(enquiry);
            }
        }
        return ret;
    }
}
