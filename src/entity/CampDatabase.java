package entity;

import java.util.*;

public class CampDatabase extends Database<Camp> {
    /**
     * This class describes a specific Query with various filters to be passed to a {@code CampDatabase}.  
     * 
     * <p>Method chaining is expected here, some methods cannot be used concurrently as it will not make sense<br>
     * (1 method for each type of filter is expected - e.g. don't use 2 filters to filter registration date)
     *
     * @throws CampDBInvalidQueryException When multiple confliction filters are specified for the same attribute
     */
    public static class Query {

        private Date registrationRangeStart = Date.MIN;
        private Date registrationRangeEnd = Date.MAX;
        private Date campDatesRangeStart = Date.MIN;
        private Date campDatesRangeEnd = Date.MAX;

        private Faculty onlyOpenToFaculty = null;
        private String onlyStaffID = null;
        private boolean includeVisible = true;
        private boolean includeInvisible = true;

        private int freeParticipantSlotsMin = 0;
        private int freeParticipantSlotsMax = Integer.MAX_VALUE;
        private int freeCampCommSlotsMin = 0;
        private int freeCampCommSlotsMax = Integer.MAX_VALUE;

        // Registration date
        private boolean registrationDone = false;
        /**
         * Include only Camps with registration closing on a specific {@code Date}
         * @param date The date that registration closes
         */
        public void registrationOn(Date date) {
            if (registrationDone)
                throw new CampDBInvalidQueryException("Too many Registration Date filters, choose only 1");
            else
                registrationDone = true;

            registrationRangeStart = date;
            registrationRangeEnd = date;
        }

        /**
         * Include only Camps with registration closing before (and including) a specific {@code Date}
         * @param date The date that registration has to close before (or on)
         */
        public void registrationBeforeIncl(Date date) {
            if (registrationDone)
                throw new CampDBInvalidQueryException("Too many Registration Date filters, choose only 1");
            else
                registrationDone = true;

            registrationRangeEnd = date;
        }

        /**
         * Include only Camps with registration closing after (and including) a specific {@code Date}
         * @param date The date that registration has to close after (or on)
         */
        public void registrationAfterIncl(Date date) {
            if (registrationDone)
                throw new CampDBInvalidQueryException("Too many Registration Date filters, choose only 1");
            else
                registrationDone = true;

            registrationRangeStart = date;
        }

        // Camp dates
        private boolean campDatesDone = false;
        /**
         * Only include Camps with that occurs entirely within a range (inclusive)
         *  @param startDate The start of the date range (inclusive)
         *  @param endDate The end of the date range (inclusive)
         */
        public void campDatesAllWithin(Date startDate, Date endDate) {
            if (campDatesDone)
                throw new CampDBInvalidQueryException("Too many Camp Dates filters, choose only 1");
            else
                campDatesDone = true;

            campDatesRangeStart = startDate;
            campDatesRangeEnd = endDate;
        }

        /**
         * Only include Camps with that occurs entirely before (and including) this {@code Date}
         *  @param date The camp dates must all lie before (and including) this date
         */
        public void campDatesAllBeforeIncl(Date date) {
            if (campDatesDone)
                throw new CampDBInvalidQueryException("Too many Camp Dates filters, choose only 1");
            else
                campDatesDone = true;

            campDatesRangeEnd = date;
        }

        /**
         * Only include Camps with that occurs entirely after (and including) this {@code Date}
         *  @param date The camp dates must all lie after (and including) this date
         */
        public void campDatesAllAfterIncl(Date date) {
            if (campDatesDone)
                throw new CampDBInvalidQueryException("Too many Camp Dates filters, choose only 1");
            else
                campDatesDone = true;

            campDatesRangeStart = date;
        }

        /**
         * Only include Camps that is open to this {@code Faculty} (including those open to the whole school of course)
         *  @param faculty The only faculty to allow through this filter
         */
        public void onlyOpenToFaculty(Faculty faculty) {
            this.onlyOpenToFaculty = faculty;
        }

        /** Effectively include only Invisible (hidden) Camps */
        public void excludeVisible() {
            this.includeVisible = false;
        }

        /** Effectively include only Visible (non-hidden) Camps */
        public void excludeInvisible() {
            this.includeInvisible = false;
        }

        /** Effectively include only Camps with free Participant Slots */
        public void excludeFullParticipantSlot() {
            this.freeParticipantSlotsMin = 1;
        }

        /** Effectively include only Camps with free Camp Committee Slots */
        public void excludeFullCampCommSlots() {
            this.freeCampCommSlotsMin = 1;
        }

        /** Include only Camp with a specific Staff-in-Charge */
        public void onlyCampsBy(Staff staffInCharge) {
            onlyCampsBy(staffInCharge.getID());
        }
        /** Include only Camp with a specific Staff-in-Charge */
        public void onlyCampsBy(String staffInChargeID) {
            this.onlyStaffID = staffInChargeID;
        }

    }

    private CampMembershipDatabase membershipDB;

    public CampDatabase(CampMembershipDatabase membershipDB) {
        this.membershipDB = membershipDB;
    }

	/**
     * Get list of Camps based on the filters specified with {@code CampDatabase.Query}
	 * @param query Detailed description of the filters to apply
     * @return Collection of Camps according to the filter
	 */
	public Collection<Camp> getCamps(Query query) {
        Collection<Camp> filtered = new ArrayList<Camp>();
        for (Camp camp : items.values()) {
            // Filtering will be done by means of continuing the iteration as Camps are filtered out
            // Staff-in-Charge Filtering
            if (query.onlyStaffID != null && !camp.getStaffInCharge().getID().equals(query.onlyStaffID))
                continue;

            // Registration Date Filtering
            if (camp.getClosingDate().isAfter(query.registrationRangeEnd))
                continue;
            if (camp.getClosingDate().isBefore(query.registrationRangeStart))
                continue;

            // Camp Dates Filtering
            for (Date campDate : camp.getDates())
                if (campDate.isBefore(query.campDatesRangeStart) || campDate.isAfter(query.campDatesRangeEnd))
                    continue;

            // Faculty Filtering
            if (query.onlyOpenToFaculty != null)
                if (!camp.getOpenTo().contains(query.onlyOpenToFaculty))
                    continue;

            // Visibility Filtering
            if (!query.includeVisible && camp.isVisible())
                continue;
            if (!query.includeInvisible && !camp.isVisible())
                continue;

            // Pariticipant / CampComm Slots Filtering
            int freeParticipantSlots = camp.getParticipantSlots() - this.membershipDB.getParticipantSize(camp);
            int freeCampCommSlots = camp.getCampCommSlots() - this.membershipDB.getCampCommSize(camp);
            assert freeParticipantSlots >= 0 && freeCampCommSlots >= 0;

            if (freeParticipantSlots < query.freeParticipantSlotsMin || freeParticipantSlots > query.freeParticipantSlotsMax)
                continue;
            if (freeCampCommSlots < query.freeCampCommSlotsMin || freeCampCommSlots > query.freeCampCommSlotsMax)
                continue;

            // If we haven't continued out of this iteration, then we've passed through all filters
            filtered.add(camp);
        }
        return filtered;
	}
}
