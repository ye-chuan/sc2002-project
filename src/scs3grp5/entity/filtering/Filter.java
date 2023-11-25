package scs3grp5.entity.filtering;

public abstract class Filter<T> {

    /**
     * @return Whether or not the object passes through filter
     */
	public abstract boolean pass(T obj);

}
