package scs3grp5.entity;

/**
 * Represents a Staff in school
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class Staff extends User {
    /**
     * Constructs a Staff
     * @param userID The unique ID of this student
     * @param password The password of this student
     * @param faculty The Faculty that this student is in
     * @param email The email of this student
     * @param name The name of this student
     */
    public Staff(String userID, String password, Faculty faculty, String email, String name) {
        super(userID, password, faculty, email, name);
    }
}
