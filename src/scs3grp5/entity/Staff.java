package scs3grp5.entity;

/**
 * Represents a Staff in school
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class Staff extends User {
    /** Constructs a Staff */
    public Staff(String userID, String password, Faculty faculty, String email, String name) {
        super(userID, password, faculty, email, name);
    }
}
