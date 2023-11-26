package scs3grp5.entity.filtering;

import scs3grp5.entity.*;

import java.util.Collection;
import java.util.ArrayList;

/**
 * Used to Filter a Collection of Camps given CampFilters
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CampFilterer extends Filterer<Camp> {

    /** List of items to start with, this list will not change after filtering */
	private Collection<Camp> initialItems;
    /** Collection of filter to filter by, this collection can be cleared */
    private Collection<Filter<Camp>> filters = new ArrayList<Filter<Camp>>();

	/**
	 * Construct Filterer with the given inital items.
     *
	 * @param initialItems The initial items to filter (will not change throughout the lifetime of the Filterer)
	 */
	public CampFilterer(Collection<Camp> initialItems) {
        this.initialItems = initialItems;
	}

    /** {@inheritDoc} */
    @Override
	public void addFilter(Filter<Camp> filter) {
        filters.add(filter);
	}

    /** {@inheritDoc} */
    @Override
	public void clearFilters() {
        filters.clear();
	}

    /** {@inheritDoc} */
    @Override
	public Collection<Camp> filter() {
        Collection<Camp> filteredCamps = new ArrayList<Camp>();

        for (Camp camp : initialItems) {
            boolean toInclude = true;
            for (Filter<Camp> filter : filters) {
                if (!filter.pass(camp)) {
                    toInclude = false;
                    break;
                }
            }
            if (toInclude)
                filteredCamps.add(camp);
        }
        return filteredCamps;
	}
}
