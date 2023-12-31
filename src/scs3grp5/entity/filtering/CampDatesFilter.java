package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

/**
 * Camp filter for Camp Dates
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampDatesFilter extends CampFilter {

    /** Start of the range of dates to include (inclusive) */
    private Date rangeStart = Date.MIN;
    /** End of the range of dates to include (inclusive) */
    private Date rangeEnd = Date.MAX;

    /**
     * Only include Camps with that occurs entirely within a range (inclusive)
     *  @param startDate The start of the date range (inclusive)
     *  @param endDate The end of the date range (inclusive)
     *  @return The {@code CampDatesFilter} with this filter option
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
     *  @return The {@code CampDatesFilter} with this filter option
     */
    public static CampDatesFilter allBeforeIncl(Date date) {
        CampDatesFilter filter = new CampDatesFilter();
        filter.rangeEnd = date;
        return filter;
    }

    /**
     * Only include Camps with that occurs entirely after (and including) this {@code Date}
     *  @param date The camp dates must all lie after (and including) this date
     *  @return The {@code CampDatesFilter} with this filter option
     */
    public static CampDatesFilter allAfterIncl(Date date) {
        CampDatesFilter filter = new CampDatesFilter();
        filter.rangeStart = date;
        return filter;
    }

    /** {@inheritDoc} */
    @Override
    public boolean pass(Camp camp) {
        // Camp Dates Filtering
        DateRange campDates = camp.getDates();
        if (campDates.getStart().isBefore(rangeStart) || campDates.getEnd().isAfter(rangeEnd))
            return false;
        return true;
    }

}
