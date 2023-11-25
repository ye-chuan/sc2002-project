package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

public class CampVisiblityFilter extends CampFilter {

    private boolean includeOnlyVisibility;

    /** Effectively include only Invisible (hidden) Camps */
    public static CampVisiblityFilter onlyVisible() {
        CampVisiblityFilter filter = new CampVisiblityFilter();
        filter.includeOnlyVisibility = true;
        return filter;
    }

    /** Effectively include only Visible (non-hidden) Camps */
    public static CampVisiblityFilter onlyInvisible() {
        CampVisiblityFilter filter = new CampVisiblityFilter();
        filter.includeOnlyVisibility = false;
        return filter;
    }

    @Override
    public boolean pass(Camp camp) {
        // Visibility Filtering
        return includeOnlyVisibility == camp.isVisible();
    }
}
