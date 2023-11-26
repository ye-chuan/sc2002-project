package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

import java.util.*;

/**
 * Camp filter for the Faculty that Camp is opened to
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampFacultyFilter extends CampFilter {

    /** The only camps opened to exactly these faculties will be included */
	private Set<Faculty> exactFaculties = new HashSet<Faculty>();
    /** Only allow exact matches of the faculties to pass (not those subset etc) */
    private Faculty asLongAsOpenedTo = null;

    /**
     * Only include Camps that is open to only this {@code Faculty} (not the whole school)
     *  @param faculty The only faculty to allow through this filter
     *  @return The {@code CampFacultyFilter} with this filter option
     */
    public static CampFacultyFilter onlyOpenedTo(Faculty faculty) {
        CampFacultyFilter filter = new CampFacultyFilter();
        filter.exactFaculties.add(faculty);
        return filter;
    }

    /**
     * Only include Camps that is open to all faculties
     *  @return The {@code CampFacultyFilter} with this filter option
     */
    public static CampFacultyFilter openedToAll() {
        CampFacultyFilter filter = new CampFacultyFilter();
        filter.exactFaculties.addAll(Arrays.asList(Faculty.values()));
        return filter;
    }

    /**
     * Only include Camps that is open to this {@code Faculty} (including those opened to whole school etc.)
     *  @param faculty The faculty to allow through this filter
     *  @return The {@code CampFacultyFilter} with this filter option
     */
    public static CampFacultyFilter openedTo(Faculty faculty) {
        assert faculty != null;
        CampFacultyFilter filter = new CampFacultyFilter();
        filter.asLongAsOpenedTo = faculty;
        return filter;
    }


    /** {@inheritDoc} */
    @Override
    public boolean pass(Camp camp) {
        // Faculty Filtering
        if (asLongAsOpenedTo != null) {
            return camp.getOpenTo().contains(asLongAsOpenedTo);
        }

        Set<Faculty> facultiesInCamp = new HashSet<Faculty>(camp.getOpenTo());
        if (!facultiesInCamp.equals(exactFaculties))
            return false;
        return true;
    }
}
