package scs3grp5.entity;

import java.util.*;

/**
 * A database for the Enquiries
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class EnquiryDatabase extends Database<Enquiry> {

    /**
     * @return Collection of unresolved enquiries
     */
	public Collection<Enquiry> getUnresolvedEnquiries() {
        Collection<Enquiry> ret = new ArrayList<Enquiry>();
        for (Enquiry enquiry : items.values()) {
            if (!enquiry.isResolved()) {
                ret.add(enquiry);
            }
        }
        return ret;
	}

    /**
     * @return Collection of resolved enquiries
     */
	public Collection<Enquiry> getResolvedEnquiries() {
        Collection<Enquiry> ret = new ArrayList<Enquiry>();
        for (Enquiry enquiry : items.values()) {
            if (enquiry.isResolved()) {
                ret.add(enquiry);
            }
        }
        return ret;
	}

    /**
     * Get all enquires asked by this user
     * @param userID ID of the asker
     * @return All enquires by this user
     */
	public Collection<Enquiry> getEnquiriesBy(String userID) {
        Collection<Enquiry> ret = new ArrayList<Enquiry>();
        for (Enquiry enquiry : items.values()) {
            if (enquiry.getAskedBy().equals(userID)) {
                ret.add(enquiry);
            }
        }
        return ret;
	}

    /**
     * Get all unresolved enquires asked by this user
     * @param userID ID of the asker
     * @return All unresolved enquires by this user
     */
	public Collection<Enquiry> getUnresolvedEnquiriesBy(String userID) {
        Collection<Enquiry> ret = new ArrayList<Enquiry>();
        for (Enquiry enquiry : getEnquiriesBy(userID)) {
            if (!enquiry.isResolved()) {
                ret.add(enquiry);
            }
        }
        return ret;
    }

    /**
     * Get all resolved enquires asked by this user
     * @param userID ID of the asker
     * @return All resolved enquires by this user
     */
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
