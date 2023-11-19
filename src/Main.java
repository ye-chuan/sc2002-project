
import entity.*;
import entity.Date;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        test_main(args);
    }
    
    // For testing purposes only
    public static void test_main(String[] args) {
        UserDatabase userDB = new UserDatabase();
        Staff staff = new Staff("SOME12", "coolpassword", Faculty.SCSE, "er@ntu.edu.sg", "Prof Someone");
        Student student = new Student("STUD1", "coolerpassword", Faculty.SCSE, "hi@e.ntu.edu.sg", "Someone");
        userDB.add(student);
        userDB.add(staff);

        CampMembershipDatabase membershipDB = new CampMembershipDatabase();
        CampDatabase campDB = new CampDatabase(membershipDB);
        
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

        System.out.println(campDB.getCamps(new CampDatabase.Query().onlyOpenToFaculty(Faculty.SCSE).registrationBeforeIncl(new Date(2022, 11, 2))));
        

        //membershipDB.getParticipantSize();
    }
}
