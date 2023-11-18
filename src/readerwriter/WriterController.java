package readerwriter;
import entity.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WriterController {

	private UserDatabaseFileWriter UserDatabaseFileWriterObj;
	private CampDatabaseFileWriter campDatabaseFileWriterObj;
	private CampMembershipDatabaseFileWriter campMembershipDatabaseFileWriterObj;
	private LastIDFileWriter LastIDFileWriterObj;

	/**
	 * This method stores userDatabase object into a file by
	 * using the UserDatabaseFileWriter to call its writeToUserDatabase method.
	 * @param filePath refers to the filepath where the file to be written is located.
	 * @param UserDatabaseObj is a userDatabase object that is going to be 
	 * written into path corresonding to the file path given.
	 * @return Does not return anything (void)
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void storeUserDatabase(String filePath, UserDatabase UserDatabaseObj) throws FileNotFoundException, IOException {
		UserDatabaseFileWriterObj.writeUserDatabaseFile(filePath, UserDatabaseObj);
	}

	/**
	 * This method stores CampDatabase object into a file by
	 * using the CampDatabaseFileWriter to call its writeToCampDatabase method.
	 * @param filePath refers to the filepath where the file to be written is located.
	 * @param CampDatabaseObj is a CampDatabase object that is going to be 
	 * written into path corresonding to the file path given.
	 * @return Does not return anything (void)
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void storeCampDatabase(String filePath, CampDatabase CampDatabaseObj) throws FileNotFoundException, IOException {
		campDatabaseFileWriterObj.writecampDatabaseFile(filePath, CampDatabaseObj);
	}

	/**
	 * This method stores userDatabase object into a file by
	 * using the CampMembershipDatabaseFileWriter to call its writeToCampMembershipDatabase method.
	 * @param filePath refers to the filepath where the file to be written is located.
	 * @param campMambershipDatabaseObj is a campMambershipDatabase object that is going to be 
	 * written into path corresonding to the file path given.
	 * @return Does not return anything (void)
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void storeCampMembershipDatabase(String filePath, CampMembershipDatabase campMambershipDatabaseObj) throws FileNotFoundException, IOException {
		campMembershipDatabaseFileWriterObj.writeCampMembershipDatabaseFile(filePath, campMambershipDatabaseObj);
	}

	/**
	 * This method stores the given LastId into a file by using the LastIDFileWriter to call the writeLastIDFile method.
	 * @param filePath refers to the filepath where the file to be written is located.
	 * @param givenLastID is the value of LastID to be stored into the given file.
	 */
	public void storeLastID(String filePath, int givenLastID) throws FileNotFoundException, IOException {
		LastIDFileWriterObj.writeLastIDFile(filePath, givenLastID);
	}

}