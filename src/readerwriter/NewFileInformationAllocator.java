package readerwriter;
import entity.*;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class NewFileInformationAllocator {

	private String defaultpw = "password";
	private ArrayList<User> listOfUsers;
	private Student studentObj;
	private Staff staffObj;

	/**
	 * 
	 * @param filePath
	 */
	public void initialiseStudentFile(String filePath) throws Exception {
			FileInputStream fStudent = new FileInputStream(filePath);
			XSSFWorkbook studentWorkbook = new XSSFWorkbook(fStudent);
			XSSFSheet studentSheet = studentWorkbook.getSheetAt(0);
			//Iterate row of the sheet
			Iterator<Row> rowIt = studentSheet.iterator();

			//Create array list of Users
			listOfUsers = new ArrayList<User>();
			String tempName;
			Faculty tempFaculty;
			String tempUserID;
			String tempEmail;
			Pattern pattern;
			Matcher matcher;

			while(rowIt.hasNext()) {
				Row row = rowIt.next();
				//Since we know that each col 1 = Name, col2 = email, col3 = faculty
				tempName = row.getCell(0).toString();
				//Fauclty is an enum obj, as such we will use valueof() to convert string to enum obj
				tempFaculty = Faculty.valueOf(row.getCell(2).toString());

				tempEmail = row.getCell(1).toString();

				//Using Regular Expression to Extract UserID from email
				//Pattern Class - Defines a pattern (to be used in a search)
				//Matcher Class - Used to search for the pattern
				pattern = Pattern.compile("(\\S+)\\@");
				matcher = pattern.matcher(tempEmail);
				tempUserID = matcher.group(1);
				studentObj = new Student(tempUserID, defaultpw, tempFaculty, tempEmail, tempName);
				listOfUsers.add(studentObj);
				fStudent.close();
				studentWorkbook.close();
			}
	}

	/**
	 * 
	 * @param filePath
	 */
	public void initialiseStaffFile(String filePath) throws Exception {
			FileInputStream fStaff = new FileInputStream(filePath);
			XSSFWorkbook staffWorkbook = new XSSFWorkbook(fStaff);
			XSSFSheet staffSheet = staffWorkbook.getSheetAt(0);
			//Iterate row of the sheet
			Iterator<Row> rowIt = staffSheet.iterator();

			//Create array list of Users
			listOfUsers = new ArrayList<User>();
			String tempName;
			Faculty tempFaculty;
			String tempUserID;
			String tempEmail;
			Pattern pattern;
			Matcher matcher;

			while(rowIt.hasNext()) {
				Row row = rowIt.next();
				//Since we know that each col 1 = Name, col2 = email, col3 = faculty
				tempName = row.getCell(0).toString();
				//Fauclty is an enum obj, as such we will use valueof() to convert string to enum obj
				tempFaculty = Faculty.valueOf(row.getCell(2).toString());
				
				tempEmail = row.getCell(1).toString();

				//Using Regular Expression to Extract UserID from email
				//Pattern Class - Defines a pattern (to be used in a search)
				//Matcher Class - Used to search for the pattern
				pattern = Pattern.compile("(\\S+)\\@");
				matcher = pattern.matcher(tempEmail);
				tempUserID = matcher.group(1);
				staffObj = new Staff(tempUserID, defaultpw, tempFaculty, tempEmail, tempName);
				listOfUsers.add(staffObj);
				fStaff.close();
				staffWorkbook.close();
			}
	}
			


}