package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

public class CampDatesFilter extends CampFilter {

    private Date rangeStart = Date.MIN;
    private Date rangeEnd = Date.MAX;

    /**
     * Only include Camps with that occurs entirely within a range (inclusive)
     *  @param startDate The start of the date range (inclusive)
     *  @param endDate The end of the date range (inclusive)
     */
    public static CampDatesFilter allWithin(Date startDate, Date endDate) {
        CampDatesFilter filter = new CampDatesFilter();
        filter.rangeStart = startDate;
        filter.rangeEnd = endDate;
        return filter;
    }

    /**
     * Only include Camps with that occurs entirely before (and including) this {@code Date}
     *  @param date The camp dates must all lie before (and including) this date
     */
    public static CampDatesFilter allBeforeIncl(Date date) {
        CampDatesFilter filter = new CampDatesFilter();
        filter.rangeEnd = date;
        return filter;
    }

    /**
     * Only include Camps with that occurs entirely after (and including) this {@code Date}
     *  @param date The camp dates must all lie after (and including) this date
     */
    public static CampDatesFilter allAfterIncl(Date date) {
        CampDatesFilter filter = new CampDatesFilter();
        filter.rangeStart = date;
        return filter;
    }

    @Override
    public boolean pass(Camp camp) {
        // Camp Dates Filtering
        DateRange campDates = camp.getDates();
        if (campDates.getStart().isBefore(rangeStart) || campDates.getEnd().isAfter(rangeEnd))
            return false;
        return true;
    }

}
