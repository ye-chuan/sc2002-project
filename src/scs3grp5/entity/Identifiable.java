package scs3grp5.entity;

/**
 * Databases that rely on a unique primary key will need it's items to implement this
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public interface Identifiable {

    /** The unique ID of this item */
	String getID();

}
