package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

/**
 * Camp filter based on registration date
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampRegistrationFilter extends CampFilter {

    /** Start of the range of closing registration dates to include */
    private Date rangeStart = Date.MIN;
    /** End of the range of closing registration dates to include */
    private Date rangeEnd = Date.MAX;

    /**
     * Include only Camps with registration closing on a specific {@code Date}
     * @param date The date that registration closes
     * @return The {@code CampRegistrationFilter} with this filter option
     */
    public static CampRegistrationFilter on(Date date) {
        CampRegistrationFilter filter = new CampRegistrationFilter();
        filter.rangeStart = date;
        filter.rangeEnd = date;
        return filter;
    }

    /**
     * Include only Camps with registration closing before (and including) a specific {@code Date}
     * @param date The date that registration has to close before (or on)
     * @return The {@code CampRegistrationFilter} with this filter option
     */
    public static CampRegistrationFilter beforeIncl(Date date) {
        CampRegistrationFilter filter = new CampRegistrationFilter();
        filter.rangeEnd = date;
        return filter;
    }

    /**
     * Include only Camps with registration closing after (and including) a specific {@code Date}
     * @param date The date that registration has to close after (or on)
     * @return The {@code CampRegistrationFilter} with this filter option
     */
    public static CampRegistrationFilter afterIncl(Date date) {
        CampRegistrationFilter filter = new CampRegistrationFilter();
        filter.rangeStart = date;
        return filter;
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean pass(Camp camp) {
        // Registration Date Filtering
        if (camp.getClosingDate().isAfter(rangeEnd))
            return false;
        if (camp.getClosingDate().isBefore(rangeStart))
            return false;
        return true;
    }
}
