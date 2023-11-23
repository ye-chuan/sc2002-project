import entity.Camp;
import entity.CampDatabase;
import entity.CampMembershipDatabase;
import entity.Enquiry;
import entity.Faculty;
import entity.Student;
import readerwriter.CsvWriter;

public class ReportController {

	private static String filepath;
	/**
	 * 
	 * @param campID
	 */
	public void generateCampReport(String campID, REPORTFILTER filter) {
		CsvWriter writer = new CsvWriter(filepath);
		CampDatabase cDB = new CampDatabase();
		CampMembershipDatabase cMemberDB;
		Camp c = cDB.getItem(campID);

		
		//campInfo
		writer.addRow("Name:",c.getName());
		writer.addRow("Description:",c.getDescription());
		writer.addRow("Faculty:",c.getOpenTo().iterator().next().toString());
		writer.addRow("Location:",c.getLocation());
		writer.addRow("Staff-In-Charge:",c.getStaffInCharge().getName());
		writer.addRow("Start Date:",String.format("%d-%d-%d",c.getDates().get(1).getDayOfMonth(),c.getDates().get(1).getMonth(),c.getDates().get(1).getYear()));
		writer.addRow("End Date:",String.format("%d-%d-%d",c.getDates().get(1).getDayOfMonth(),c.getDates().get(1).getMonth(),c.getDates().get(1).getYear()));
		writer.addRow("Closing Date:",String.format("&d",c.getClosingDate().getDayOfMonth(),c.getClosingDate().getMonth(),c.getClosingDate().getYear()));
		writer.addRow("Total Participant Slots:",String.format("&d",c.getParticipantSlots()));
		writer.addRow("Total Committee Slots:",String.format("&d",c.getCampCommSlots()));

		writer.addRow("STUDENT NAME","CAMP ROLE");
		if(filter == REPORTFILTER.CAMPCOMM || filter == REPORTFILTER.ALL) {
			for (Student s : cMemberDB.getCampCommMembers(campID)) {
				writer.addRow(s.getName(),"CAMPCOMM");
			}
		}
		if(filter == REPORTFILTER.PARTICIPANTS || filter == REPORTFILTER.ALL) {
			for (Student s : cMemberDB.getParticipants(campID)) {
				writer.addRow(s.getName(),"PARTICIPANT");
			}
		}

	}

	/**
	 * 
	 * @param campID
	 */
	public void generatePerformanceReport(String campID) {
		CsvWriter writer = new CsvWriter(filepath);
		CampDatabase cDB = new CampDatabase();
		CampMembershipDatabase cMemberDB;
		Camp c = cDB.getItem(campID);

		writer.addRow("Camp Name:",c.getName());
		writer.addRow("STUDENT NAME","POINTS");
		for (Student s : cMemberDB.getCampCommMembers(campID)) {
			writer.addRow(s.getName(),String.format("&d",s.getPoints()));
		}

	}

	/**
	 * 
	 * @param campID
	 */
	public void generateEnquiryReport(String campID) {
		CsvWriter writer = new CsvWriter(filepath);
		CampDatabase cDB = new CampDatabase();
		CampMembershipDatabase cMemberDB;
		Camp c = cDB.getItem(campID);

		writer.addRow("Camp Name:",c.getName(),"ID:",c.getID());
		writer.addRow("Asked By","Enquiry","Reply", "Status");
		for (Enquiry e : c.getEnquiryDB().getUnresolvedEnquiries()) {
			writer.addRow(e.getAskedBy(),e.getEnquiry(),"NIL","Unresolved");
		}
		for (Enquiry e : c.getEnquiryDB().getResolvedEnquiries()) {
			writer.addRow(e.getAskedBy(),e.getEnquiry(),e.getReply(),"Resolved");
		}
	}

	public void setReportFilePath(String filepath) {
		ReportController.filepath = filepath;
	}

}