package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

/**
 * Camp filter based the visibility of the camp
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampVisiblityFilter extends CampFilter {

    /** The visibility needed to pass this filter */
    private boolean includeOnlyVisibility;

    /**
     * Effectively include only Invisible (hidden) Camps
     * @return The {@code CampVisibilityFilter} with this filter option
     */
    public static CampVisiblityFilter onlyVisible() {
        CampVisiblityFilter filter = new CampVisiblityFilter();
        filter.includeOnlyVisibility = true;
        return filter;
    }

    /**
     * Effectively include only Visible (non-hidden) Camps
     * @return The {@code CampVisibilityFilter} with this filter option
     */
    public static CampVisiblityFilter onlyInvisible() {
        CampVisiblityFilter filter = new CampVisiblityFilter();
        filter.includeOnlyVisibility = false;
        return filter;
    }

    /** {@inheritDoc} */
    @Override
    public boolean pass(Camp camp) {
        // Visibility Filtering
        return includeOnlyVisibility == camp.isVisible();
    }
}
