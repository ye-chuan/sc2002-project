package scs3grp5;

import scs3grp5.entity.*;
import scs3grp5.io.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    private static Path dataPath = Paths.get("database");
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
    private static CampMembershipDatabase memberDB;
    /**
     * The main {@code UniqueIDGenerator} used by the application
     */
    private static UniqueIDGenerator idGenerator;
    
    public static CampDatabase getCampDB() {
        return campDB;
    }

    public static UserDatabase getUserDB() {
        return userDB;
    }
    
    public static CampMembershipDatabase getMemberDB() {
        return memberDB;
    }
    
    public static UniqueIDGenerator getIdGenerator() {
        return idGenerator;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
        Path initialStaffXlsx = dataPath.resolve("staff_list.xlsx");
        Path initialStudentXlsx = dataPath.resolve("student_list.xlsx");

        Path savedUserDBPath = dataPath.resolve("userDB");
        Path savedCampDBPath = dataPath.resolve("campDB");
        Path savedMemberDBPath = dataPath.resolve("memberDB");
        Path savedIdGeneratorPath = dataPath.resolve("idGenerator");

        if (savedUserDBPath.toFile().exists()) {    // Retrieve from saved Database
            ReaderController reader = new ReaderController();
            userDB = reader.getUserDatabase(savedUserDBPath.toString());
            campDB = reader.getCampDatabase(savedCampDBPath.toString());
            memberDB = reader.getCampMembershipDatabase(savedMemberDBPath.toString());
            idGenerator = reader.getUniqueIDGenerator(savedIdGeneratorPath.toString());
        }
        else {                                      // First Time Running / Reset
            NewFileInformationAllocator fileAllocator = new NewFileInformationAllocator();
            ArrayList<Staff> staffList =  fileAllocator.initialiseStaffFile(initialStaffXlsx.toString());
            ArrayList<Student> studentList = fileAllocator.initialiseStudentFile(initialStudentXlsx.toString());

            userDB = new UserDatabase(staffList, studentList);

            memberDB = new CampMembershipDatabase();
            campDB = new CampDatabase(memberDB);
            idGenerator = new UniqueIDGenerator();
        }

        //test_main(args);
    }
    
    // For testing purposes only
    public static void test_main(String[] args) throws FileNotFoundException, IOException {
        UserDatabase userDB = new UserDatabase();
        Staff staff = new Staff("SOME12", "coolpassword", Faculty.SCSE, "SOME12@ntu.edu.sg", "Prof Someone");
        Student student = new Student("STUD1", "coolerpassword", Faculty.SCSE, "STUD1@e.ntu.edu.sg", "Someone");
        userDB.add(student);
        userDB.add(staff);

        CampMembershipDatabase membershipDB = new CampMembershipDatabase();
        CampDatabase campDB = new CampDatabase(membershipDB);
        
        UniqueIDGenerator uniqueIDg = new UniqueIDGenerator();

        // Camp creation
        Collection<Faculty> openTo = new ArrayList<Faculty>();
        openTo.add(Faculty.EEE);
        
        Camp camp = new Camp("camp1", "location", new Date(2022,12,3), new Date(2022,12,4), new Date(2022,11,3), 100, 10, staff, openTo);
        camp.openToNTU();

        Camp camp2 = new Camp("camp2", "location2", new Date(2022,12,3), new Date(2022,12,4), new Date(2022,10,3), 10, 10, staff, openTo);

        campDB.add(camp);
        campDB.add(camp2);

        System.out.println(camp.getOpenTo());
        System.out.println(camp.getDates());

        System.out.println(campDB.getCamps(new CampDatabase.Query().onlyOpenToFaculty(Faculty.SCSE).registrationBeforeIncl(new Date(2022, 11, 3))));

        WriterController writer = new WriterController();
        writer.storeCampDatabase("Camp.ser", campDB);
        writer.storeCampMembershipDatabase("CampMembership.ser", membershipDB);
        writer.storeUserDatabase("User2.ser", userDB);
        writer.storeuniqueIDGeneratorObj("UniqueIDGenerator.ser", uniqueIDg);

        //membershipDB.getParticipantSize();
    }
}
