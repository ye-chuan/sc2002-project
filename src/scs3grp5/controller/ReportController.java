package scs3grp5.controller;

import scs3grp5.Main;
import scs3grp5.entity.*;
import scs3grp5.io.CsvWriter;

import java.util.*;
import java.io.IOException;

/**
 * Manages output of formatted report to csv
 * @author Edmund Ser
 * @version 1.0
 * @since 2023-11-26
 */
public class ReportController {
	private static String campReportPath = Main.getOutputDir().resolve("camp_report.csv").toString();
	private static String performanceReportPath = Main.getOutputDir().resolve("performance_report.csv").toString();
	private static String enquiryReportPath = Main.getOutputDir().resolve("enquiry_report.csv").toString();
	/**
	 * generates a report with all the camp information and list of members in camp and their roles
	 * @param campID The unique ID of the Camp
	 * @param filter The CampRole Filter for the Report
	 * @throws IOException if fails to write to csv file
	 */
	public void generateCampReport(String campID, REPORTFILTER filter) throws IOException {
		CsvWriter writer = new CsvWriter(campReportPath);
		CampDatabase cDB = Main.getCampDB();
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
		Camp c = cDB.getItem(campID);

		
		//campInfo
		writer.addRow("Name",c.getName());
		writer.addRow("Description",c.getDescription());

        // Faculty
        List<String> facultyRow = new ArrayList<String>();
        Collection<Faculty> openTo = c.getOpenTo();
        facultyRow.add("Faculty");
        if (openTo.size() == Faculty.values().length) {
            facultyRow.add("All Faculties");
        }
        else {
            for (Faculty faculty : openTo) {
                facultyRow.add(faculty.toString());
            }
        }
        writer.addRow(facultyRow.toArray(new String[facultyRow.size()]));

		writer.addRow("Location", c.getLocation());
		writer.addRow("Staff-In-Charge", c.getStaffInCharge().getName());
		writer.addRow("Start Date", c.getDates().getStart().toString());
		writer.addRow("End Date", c.getDates().getEnd().toString());
		writer.addRow("Closing Date", c.getClosingDate().toString());
		writer.addRow("Total Participant Slots", String.valueOf(c.getParticipantSlots()));
		writer.addRow("Total Committee Slots", String.valueOf(c.getCampCommSlots()));

        writer.addRow("");
		writer.addRow("STUDENT NAME", "CAMP ROLE");
		if(filter == REPORTFILTER.CAMPCOMM || filter == REPORTFILTER.ALL) {
			for (Student s : cMemberDB.getCampCommMembers(campID)) {
				writer.addRow(s.getName(), "CAMPCOMM");
			}
		}
		if(filter == REPORTFILTER.PARTICIPANTS || filter == REPORTFILTER.ALL) {
			for (Student s : cMemberDB.getParticipants(campID)) {
				writer.addRow(s.getName(), "PARTICIPANT");
			}
		}

        try {
            writer.write();
        }
        catch (IOException e) {
            throw new IOException("Problem writing to " + campReportPath + " ! Make sure directory exists & File not opened");
        }
	}

	/**
	 * generates a performance report with all the points of camp committee in the camp
	 * @param campID The unique ID of the Camp
	 * @throws IOException if fails to write to csv file
	 */
	public void generatePerformanceReport(String campID) throws IOException {
		CsvWriter writer = new CsvWriter(performanceReportPath);
		CampDatabase cDB = Main.getCampDB();
		CampMembershipDatabase cMemberDB = Main.getMemberDB();
		Camp c = cDB.getItem(campID);
		UserController uCont = new UserController();

		writer.addRow("Camp Name",c.getName());
        writer.addRow("");
		writer.addRow("STUDENT NAME","APPROVED SUGGESTION","REJECTED SUGGESTION","ENQUIRY REPLIED","POINTS");
		for (Student s : cMemberDB.getCampCommMembers(campID)) {
			SuggestionDatabase sDB = c.getSuggestionDB();

				int apSug = sDB.getApprovedSuggestionsBy(s.getID()).size();
				int rejSug = sDB.getRejectedSuggestionsBy(s.getID()).size();
				
				int enqRep = 0;
				EnquiryDatabase eDB = c.getEnquiryDB();

				for(Enquiry e : eDB.getResolvedEnquiries()) {
					if(e.getRepliedBy().equals(s.getID())) enqRep++;
				}
			writer.addRow(s.getName(), Integer.toString(apSug), Integer.toString(rejSug),Integer.toString(enqRep),String.valueOf(uCont.getPoints(s.getID())));
		}
        
        try {
            writer.write();
        }
        catch (IOException e) {
            throw new IOException("Problem writing to " + campReportPath + " ! Make sure directory exists & File not opened");
        }
	}

	/**
	 * generates a list of all enquiries in the camp
	 * @param campID The unique ID of the Camp
	 * @throws IOException if fails to write to csv file
	 */
	public void generateEnquiryReport(String campID) throws IOException {
		CsvWriter writer = new CsvWriter(enquiryReportPath);
		CampDatabase cDB = Main.getCampDB();
		Camp c = cDB.getItem(campID);

		writer.addRow("Camp Name",c.getName(),"ID",c.getID());
        writer.addRow("");
		writer.addRow("Asked By","Enquiry","Reply", "Status");
		for (Enquiry e : c.getEnquiryDB().getUnresolvedEnquiries()) {
			writer.addRow(e.getAskedBy(),e.getEnquiry(),"NIL","Unresolved");
		}
		for (Enquiry e : c.getEnquiryDB().getResolvedEnquiries()) {
			writer.addRow(e.getAskedBy(),e.getEnquiry(),e.getReply(),"Resolved");
		}
        
        try {
            writer.write();
        }
        catch (IOException e) {
            throw new IOException("Problem writing to " + campReportPath + " ! Make sure directory exists & File not opened");
        }
	}

}
