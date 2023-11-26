package scs3grp5.entity;

/**
 * Unique ID Generators will implement this
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public interface IUniqueIDGenerator {
    /** Generates a unique ID, unique to this instance of the generator */
    public abstract String generate();
}
