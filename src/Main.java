
import entity.*;
import entity.Date;
import readerwriter.NewFileInformationAllocator;
import readerwriter.WriterController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, Exception {
        // ReaderController reader = new ReaderController();
        // CampDatabase campDB = reader.getCampDatabase("Camp.ser");
        // CampMembershipDatabase campMBDB = reader.getCampMembershipDatabase("CampMembership.ser");
        // UserDatabase userDB = reader.getUserDatabase("User2.ser");
        // int lastID = reader.getLastID("lastID");
        //UniqueIDGenerator uniqueIDgen = reader.getUniqueIDGenerator("UniqueIDGenerator.ser");
        // System.out.println(uniqueIDgen); 

        //System.out.println(campDB.getCamps(new CampDatabase.Query().onlyOpenToFaculty(Faculty.SCSE).registrationBeforeIncl(new Date(2022, 11, 3))));
        //System.out.println(lastID);
        NewFileInformationAllocator fileAllocator = new NewFileInformationAllocator();
        ArrayList<Staff> listStaffs =  fileAllocator.initialiseStaffFile("C:\\Users\\elain\\Documents\\2)NTU Uni\\2_year2\\y2s1\\2) SC2002 Object Oriented Design and Programming\\sc2002Assignment\\sc2002-project\\database\\staff_list.xlsx");
        ArrayList<Student> listStudent = fileAllocator.initialiseStudentFile("C:\\Users\\elain\\Documents\\2)NTU Uni\\2_year2\\y2s1\\2) SC2002 Object Oriented Design and Programming\\sc2002Assignment\\sc2002-project\\database\\student_list.xlsx");
        for (Staff x :listStaffs) {
            System.out.println(x);
        }        
        for (Student x :listStudent) {
            System.out.println(x);
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
        Collection<Date> dates = new ArrayList<Date>();
        dates.add(new Date(2022,12,3));
        dates.add(new Date(2022,12,2));
        dates.add(new Date(2022,12,4));
        Collection<Faculty> openTo = new ArrayList<Faculty>();
        openTo.add(Faculty.EEE);
        
        Camp camp = new Camp("camp1", "location", dates, new Date(2022,11,3), 100, 10, staff, openTo);
        camp.openToNTU();

        Camp camp2 = new Camp("camp2", "location2", dates, new Date(2022,10,3), 10, 10, staff, openTo);

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
