package scs3grp5;

import scs3grp5.entity.*;
import scs3grp5.entity.filtering.*;
import scs3grp5.io.*;
import scs3grp5.ui.UIMain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collection;

public class Main {
    /** Base path for all data files */
    private static Path dataPath = Paths.get("database");

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
    
    public static CampDatabase getCampDB() {
        return campDB;
    }

    public static UserDatabase getUserDB() {
        return userDB;
    }
    
    public static CampMembershipDatabase getMemberDB() {
        return membershipDB;
    }
    
    public static UniqueIDGenerator getIdGenerator() {
        return idGenerator;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
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

        //test_main(args);
        
        //WriterController writer = new WriterController();
        //writer.storeCampDatabase(savedCampDBPath.toString(), campDB);
        //writer.storeCampMembershipDatabase(savedMemberDBPath.toString(), membershipDB);
        //writer.storeUserDatabase(savedUserDBPath.toString(), userDB);
        //writer.storeuniqueIDGeneratorObj(savedIdGeneratorPath.toString(), uniqueIDg);
    }
    
    // For testing purposes only
    public static void main2(String[] args) throws FileNotFoundException, IOException, Exception {
        // First Time Running / Reset
        NewFileInformationAllocator fileAllocator = new NewFileInformationAllocator();
        ArrayList<Staff> staffList =  fileAllocator.initialiseStaffFile(initialStaffXlsx.toString());
        ArrayList<Student> studentList = fileAllocator.initialiseStudentFile(initialStudentXlsx.toString());
        userDB = new UserDatabase(staffList, studentList);

        membershipDB = new CampMembershipDatabase();
        campDB = new CampDatabase();
        idGenerator = new UniqueIDGenerator();

        Staff staff = new Staff("SOME12", "coolpassword", Faculty.SCSE, "SOME12@ntu.edu.sg", "Prof Someone");
        Student student = new Student("STUD1", "coolerpassword", Faculty.SCSE, "STUD1@e.ntu.edu.sg", "Someone");
        userDB.add(student);
        userDB.add(staff);

        // Camp creation
        Collection<Faculty> openTo = new ArrayList<Faculty>();
        openTo.add(Faculty.EEE);

        Camp camp = new Camp(membershipDB, "camp1", "location", new Date(2022,12,3), new Date(2022,12,4), new Date(2022,11,3), 100, 10, staff, openTo);
        camp.openToNTU();

        Camp camp2 = new Camp(membershipDB, "camp2", "location2", new Date(2022,12,3), new Date(2022,12,4), new Date(2022,10,3), 10, 10, staff, openTo);

        campDB.add(camp);
        campDB.add(camp2);

        System.out.println(camp.getOpenTo());
        System.out.println(camp.getDates());

        CampFilterer filterer = new CampFilterer(campDB.getAll());
        filterer.addFilter(CampFacultyFilter.onlyOpenedTo(Faculty.SCSE));
        filterer.addFilter(CampRegistrationFilter.beforeIncl(new Date(2022, 11, 3)));
        System.out.println(filterer.filter());

        WriterController writer = new WriterController();
        writer.storeCampDatabase(savedCampDBPath.toString(), campDB);
        writer.storeCampMembershipDatabase(savedMembershipDBPath.toString(), membershipDB);
        writer.storeUserDatabase(savedUserDBPath.toString(), userDB);
        writer.storeuniqueIDGeneratorObj(savedIdGeneratorPath.toString(), idGenerator);
    }


    public static void main3(String[] args) throws FileNotFoundException, IOException, Exception {
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
