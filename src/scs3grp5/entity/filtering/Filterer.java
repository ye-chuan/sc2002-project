package scs3grp5.entity.filtering;

import java.util.Collection;

/**
 * Used to Filter a Collection of given a Collection of Filters.
 */
public abstract class Filterer<T> {

	/**
	 * Add a filter to to used later when {@code .filter()} is called.
     *
	 * @param filter
	 */
	public abstract void addFilter(Filter<T> filter);

    /** Remove all filters from this Filterer */
	public abstract void clearFilters();

	/**
     * Returned the items filtered by the added filters so far.
     *
     * Note that this doesn't change the state of the Filterer.
	 * 
	 * @param filter
	 */
	public abstract Collection<T> filter();

}
