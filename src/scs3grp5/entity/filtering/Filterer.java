package scs3grp5.entity.filtering;

import java.util.Collection;

/**
 * Used to Filter a Collection of objects given filters
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public abstract class Filterer<T> {

	/**
	 * Add a filter to to used later when {@code .filter()} is called.
     *
	 * @param filter The filter to add
	 */
	public abstract void addFilter(Filter<T> filter);

    /** Remove all filters from this Filterer */
	public abstract void clearFilters();

	/**
     * Returned the items filtered by the added filters so far.
     *
     * Note that this doesn't change the state of the Filterer.
     * @return The resulting collection of items after the filtering
	 */
	public abstract Collection<T> filter();

}
