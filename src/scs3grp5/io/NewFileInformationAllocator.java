/**
 * @author Ong Yi Ren, Elaine
 * @version 1.0
 * @since 2023-11-26
 */

package scs3grp5.io;

import scs3grp5.entity.*;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
/**
 *Reads data from .xlsx file. Depending on the file that was read,
 *it creates a corresonding array of Student or Staff Objects.
 */
public class NewFileInformationAllocator {

	private String defaultpw = "password";
	private Student studentObj;
	private Staff staffObj;

	/**
	 * 
	 * @param filePath
	 * @return ArrayList<Student>
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 * @throws InvalidFormatException Specialized sub-class of JsonMappingException that is used when 
	 * the underlying problem appears to be that of bad formatting of a value to deserialize
	 */
	public ArrayList<Student> initialiseStudentFile(String filePath) throws FileNotFoundException, IOException, InvalidFormatException {
			FileInputStream fStudent = new FileInputStream(filePath);
			XSSFWorkbook studentWorkbook = new XSSFWorkbook(fStudent);
			XSSFSheet studentSheet = studentWorkbook.getSheetAt(0);
			//Iterate row of the sheet
			Iterator<Row> rowIt = studentSheet.iterator();

			//Create array list of Student
			ArrayList<Student> listOfStudent = new ArrayList<Student>();
			String tempName;
			Faculty tempFaculty;
			String tempUserID;
			String tempEmail;
			Pattern pattern;
			Matcher matcher;

			rowIt.next();
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
				matcher.find();
				tempUserID = matcher.group(1);
				studentObj = new Student(tempUserID, defaultpw, tempFaculty, tempEmail, tempName);
				listOfStudent.add(studentObj);
			}
			fStudent.close();
			studentWorkbook.close();
			return listOfStudent;
	}

	/**
	 * 
	 * @param filePath
	 * @return ArrayList<Staff>
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 * @throws InvalidFormatException Specialized sub-class of JsonMappingException that is used when 
	 * the underlying problem appears to be that of bad formatting of a value to deserialize
	 */
	public ArrayList<Staff> initialiseStaffFile(String filePath) throws FileNotFoundException, IOException, InvalidFormatException {
			FileInputStream fStaff = new FileInputStream(filePath);
			XSSFWorkbook staffWorkbook = new XSSFWorkbook(fStaff);
			XSSFSheet staffSheet = staffWorkbook.getSheetAt(0);
			//Iterate row of the sheet
			Iterator<Row> rowIt = staffSheet.iterator();

			//Create array list of Staff
			ArrayList<Staff> listOfStaff = new ArrayList<Staff>();
			String tempName;
			Faculty tempFaculty;
			String tempUserID;
			String tempEmail;
			Pattern pattern;
			Matcher matcher;

			rowIt.next();
			while(rowIt.hasNext()) {
				Row row = rowIt.next();
				//Since we know that each col 1 = Name, col2 = email, col3 = faculty
				tempName = row.getCell(0).toString();

				//Fauclty is an enum obj, as such we will use valueof() to convert string to enum obj
				tempFaculty = Faculty.valueOf(row.getCell(2).toString());
				
				tempEmail = row.getCell(1).toString();

				//Using Regular Expression to Extract UserID from email
				//Pattern Class - Defines a pattern (to be used in a search)
				//Matcher Class - Used to search for the pattern'
				pattern = Pattern.compile("(\\S+)\\@");
				matcher = pattern.matcher(tempEmail);
				matcher.find();
				tempUserID = matcher.group(1);
				staffObj = new Staff(tempUserID, defaultpw, tempFaculty, tempEmail, tempName);
				listOfStaff.add(staffObj);
			}
			fStaff.close();
			staffWorkbook.close();
			return listOfStaff;
	}
			


}