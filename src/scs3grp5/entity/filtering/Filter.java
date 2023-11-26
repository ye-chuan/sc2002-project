package scs3grp5.entity.filtering;

/**
 * Abstract generic filter class to create filters for objects
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 **/
public abstract class Filter<T> {

    /**
     * @param obj The object to check if passes through this filter
     * @return Whether or not the object passes through filter
     */
	public abstract boolean pass(T obj);

}
