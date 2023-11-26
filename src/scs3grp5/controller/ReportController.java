package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;
import scs3grp5.io.CsvWriter;

/**
 * Manages output of formatted report to csv
 * 
 */
public class ReportController {

	private static String filepath;
	/**
	 * 
	 * @param campID
	 */
	public void generateCampReport(String campID, REPORTFILTER filter) {
		CsvWriter writer = new CsvWriter(filepath);
		CampDatabase cDB = Main.getCampDB();
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
		Camp c = cDB.getItem(campID);

		
		//campInfo
		writer.addRow("Name:",c.getName());
		writer.addRow("Description:",c.getDescription());
		writer.addRow("Faculty:",c.getOpenTo().iterator().next().toString());
		writer.addRow("Location:",c.getLocation());
		writer.addRow("Staff-In-Charge:",c.getStaffInCharge().getName());
		writer.addRow("Start Date:",c.getDates().getStart().toString());
		writer.addRow("End Date:",c.getDates().getEnd().toString());
		writer.addRow("Closing Date:",c.getClosingDate().toString());
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
		CampDatabase cDB = Main.getCampDB();
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
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
		CampDatabase cDB = Main.getCampDB();
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