package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

public class CampFacultyFilter extends CampFilter {

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

    public boolean pass(Camp camp) {
        // Faculty Filtering
        if (!camp.getOpenTo().contains(onlyFaculty))
            return false;
        return true;
    }
}
