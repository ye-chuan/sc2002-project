package entity;

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
 */
public class UniqueIDGenerator {

	private static long lastID = 0;

	public static String generate() {
        return String.valueOf(lastID++);
	}

    public static void main(String[] args) {
        for (int i=0; i<10; i++)
            System.out.println(generate());
    }
}
