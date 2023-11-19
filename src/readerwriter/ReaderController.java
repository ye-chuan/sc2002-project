package readerwriter;
import entity.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReaderController {

	private UserDatabaseFileReader UserDatabaseFileReadObj;
	private CampDatabaseFileReader CampDatabaseFileReaderObj;
	private CampMembershipDatabaseFileReader CampMembershipDatabaseFileReaderObj;
	private LastIDFileReader LastIDFileReaderObj;

	/**
	 * This method reads the file located at the corresponding file Path given
	 * and returns UserDatabase object by using UserDatabaseFileReader object to call
	 * its readUserDatabaseFile method.
	 * This method might catch a FileNotFoundException from the readUserDatabaseFile method
	 * but would throw it back to the method or class who calls it.
	 * @param filePath refers to the filepath where the file to be read is located
	 * @return UserDatabase object
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public UserDatabase getUserDatabase(String filePath) throws FileNotFoundException, ClassNotFoundException, IOException {
		return UserDatabaseFileReadObj.readUserDatabaseFile(filePath);
	}
	/**
	 * This method reads the file located at the corresponding file Path given
	 * and returns campDatabase object by using CampDatabaseFileReader object to call
	 * its readCampDatabaseFile method.
	 * This method might catch a FileNotFoundException from the readCampDatabaseFile method
	 * but would throw it back to the method or class who calls it.
	 * @param filePath refers to the filepath where the file to be read is located
	 * @return CampDatabase object
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public CampDatabase getCampDatabase(String filePath) throws FileNotFoundException, ClassNotFoundException, IOException {
		return CampDatabaseFileReaderObj.readCampDatabaseFile(filePath);
	}

	/**
	 * This method reads the file located at the corresponding file Path given
	 * and returns campDatabase object by using CampMembershipDatabaseFileReader object to call
	 * its readCampMembershipDatabaseFile method.
	 * This method might catch a FileNotFoundException from the readCampMembershipDatabaseFile method
	 * but would throw it back to the method or class who calls it.
	 * @param filePath refers to the filepath where the file to be read is located
	 * @return CampMembershipDatabase object
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public CampMembershipDatabase getCampMembershipDatabase(String filePath) throws FileNotFoundException, ClassNotFoundException, IOException {
		return CampMembershipDatabaseFileReaderObj.readCampMembershipDatabaseFile(filePath);
	}

	/**
	 * This method reads given file and returns the value of LastID stored in the file
	 * @param filePath
	 * @return getLastID
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public int getLastID(String filePath) throws FileNotFoundException, IOException {
		return LastIDFileReaderObj.readLastID(filePath);
	}

}