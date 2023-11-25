package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

public class CampRegistrationFilter extends CampFilter {

    private Date rangeStart = Date.MIN;
    private Date rangeEnd = Date.MAX;

    /**
     * Include only Camps with registration closing on a specific {@code Date}
     * @param date The date that registration closes
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
     */
    public static CampRegistrationFilter beforeIncl(Date date) {
        CampRegistrationFilter filter = new CampRegistrationFilter();
        filter.rangeEnd = date;
        return filter;
    }

    /**
     * Include only Camps with registration closing after (and including) a specific {@code Date}
     * @param date The date that registration has to close after (or on)
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
