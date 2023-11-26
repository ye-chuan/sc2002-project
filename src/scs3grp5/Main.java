package scs3grp5;

import scs3grp5.entity.*;
import scs3grp5.entity.filtering.*;
import scs3grp5.io.*;
import scs3grp5.ui.UIMain;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    /** Base path for all data files */
    private static Path dataPath = Paths.get("data");

    /** Path for initial staff_list.xlsx data file */
    private static Path initialStaffXlsx = dataPath.resolve("initial/staff_list.xlsx");
    /** Path for initial student_list.xlsx data file */
    private static Path initialStudentXlsx = dataPath.resolve("initial/student_list.xlsx");

    /** Save/Load Path for UserDatabase */
    private static Path savedUserDBPath = dataPath.resolve("userDB");
    /** Save/Load Path for CampDatabase */
    private static Path savedCampDBPath = dataPath.resolve("campDB");
    /** Save/Load Path for MembershipDatabase */
    private static Path savedMembershipDBPath = dataPath.resolve("membershipDB");
    /** Save/Load Path for UniqueIdGenerator */
    private static Path savedIdGeneratorPath = dataPath.resolve("idGenerator");

    /**
     * The main {@code CampDatabase} used by the application
     */
    private static CampDatabase campDB;
    /**
     * The main {@code UserDatabase} used by the application
     */
    private static UserDatabase userDB;
    /**
     * The main {@code CampMembershipDatabase} used by the application
     */
    private static CampMembershipDatabase membershipDB;
    /**
     * The main {@code UniqueIDGenerator} used by the application
     */
    private static UniqueIDGenerator idGenerator;
    
    /**
     * @return The Camp Database for this application
     */
    public static CampDatabase getCampDB() {
        return campDB;
    }

    /**
     * @return The User Database for this application
     */
    public static UserDatabase getUserDB() {
        return userDB;
    }
    
    /**
     * @return The Camp Membership Database for this application
     */
    public static CampMembershipDatabase getMemberDB() {
        return membershipDB;
    }
    
    /**
     * @return The Unique ID Generator for this application
     */
    public static UniqueIDGenerator getIdGenerator() {
        return idGenerator;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, InvalidFormatException {
        if (savedUserDBPath.toFile().exists()) {    // Retrieve from saved Database
            ReaderController reader = new ReaderController();
            userDB = reader.getUserDatabase(savedUserDBPath.toString());
            campDB = reader.getCampDatabase(savedCampDBPath.toString());
            membershipDB = reader.getCampMembershipDatabase(savedMembershipDBPath.toString());
            idGenerator = reader.getUniqueIDGenerator(savedIdGeneratorPath.toString());
        }
        else {                                      // First Time Running / Reset
            NewFileInformationAllocator fileAllocator = new NewFileInformationAllocator();
            ArrayList<Staff> staffList =  fileAllocator.initialiseStaffFile(initialStaffXlsx.toString());
            ArrayList<Student> studentList = fileAllocator.initialiseStudentFile(initialStudentXlsx.toString());

            userDB = new UserDatabase(staffList, studentList);

            membershipDB = new CampMembershipDatabase();
            campDB = new CampDatabase();
            idGenerator = new UniqueIDGenerator();
        }

        new UIMain().runUI();

        WriterController writer = new WriterController();
        writer.storeCampDatabase(savedCampDBPath.toString(), campDB);
        writer.storeCampMembershipDatabase(savedMembershipDBPath.toString(), membershipDB);
        writer.storeUserDatabase(savedUserDBPath.toString(), userDB);
        writer.storeuniqueIDGeneratorObj(savedIdGeneratorPath.toString(), idGenerator);
    }
    
    public static void test(String[] args) throws FileNotFoundException, IOException, Exception {
        if (savedUserDBPath.toFile().exists()) {    // Retrieve from saved Database
            ReaderController reader = new ReaderController();
            userDB = reader.getUserDatabase(savedUserDBPath.toString());
            campDB = reader.getCampDatabase(savedCampDBPath.toString());
            membershipDB = reader.getCampMembershipDatabase(savedMembershipDBPath.toString());
            idGenerator = reader.getUniqueIDGenerator(savedIdGeneratorPath.toString());
        }

        System.out.println(userDB.getAll());
        System.out.println(campDB.getAll());
        System.out.println(idGenerator.generate());
        System.out.println(idGenerator.generate());
        
    }
}
