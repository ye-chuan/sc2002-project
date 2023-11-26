package scs3grp5.entity;

import java.io.Serializable;

/**
 * Generates a globally unique string as ID
 * Current implementation is based on simple long incrementation
 * A long has 2^64 different values which is more than enough for
 * generating
 * - 1000 years each with
 *  - 40000 new users and
 *  - 1000 camps, each camp having
 *   - 10000 suggestions
 *   - 10000 enquiries
 * which would require 20,040,000,000 IDs
 * 2^64 = 18,446,744,073,709,551,616
 *
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class LongIncrementalIDGenerator implements IUniqueIDGenerator, Serializable {

    /** Current internal implementation of unqiue id is a simple long incrementation */
	private long lastID = 0;

    /**
     * Generates a globally unique ID as long as the same instance of UniqueIDGenerator is used
     *
     * @return A globally unique ID
     */
	public String generate() {
        return String.valueOf(lastID++);
	}
}
