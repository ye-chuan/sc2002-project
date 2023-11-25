package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

/**
 * Camp filter for the Faculty that Camp is opened to
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampFacultyFilter extends CampFilter {

    /** The only faculty to include */
	private Faculty onlyFaculty;

    /**
     * Only include Camps that is open to this {@code Faculty} (including those open to the whole school of course)
     *  @param faculty The only faculty to allow through this filter
     */
    public static CampFacultyFilter onlyOpenedTo(Faculty faculty) {
        CampFacultyFilter filter = new CampFacultyFilter();
        filter.onlyFaculty = faculty;
        return filter;
    }

    /** {@inheritDoc} */
    @Override
    public boolean pass(Camp camp) {
        // Faculty Filtering
        if (!camp.getOpenTo().contains(onlyFaculty))
            return false;
        return true;
    }
}
