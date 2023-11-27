package scs3grp5.io;

import scs3grp5.entity.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * WriterContoller Class takes charge of writing object given into a serised file.
 */
public class WriterController {

	private UserDatabaseFileWriter UserDatabaseFileWriterObj = new UserDatabaseFileWriter();
	private CampDatabaseFileWriter campDatabaseFileWriterObj = new CampDatabaseFileWriter();
	private CampMembershipDatabaseFileWriter campMembershipDatabaseFileWriterObj = new CampMembershipDatabaseFileWriter();
	private UniqueIDGeneratorFileWrite uniqueIDGeneratorFileWriteObj = new UniqueIDGeneratorFileWrite();

	/**
	 * This method stores userDatabase object into a file by
	 * using the UserDatabaseFileWriter to call its writeToUserDatabase method.
	 * @param filePath refers to the filepath where the file to be written is located.
	 * @param UserDatabaseObj refers to a UserDatabase object that stores information of multiple users.
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
	 * @param CampDatabaseObj refers to campDatabase object that stores information of camps.
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void storeCampDatabase(String filePath, CampDatabase CampDatabaseObj) throws FileNotFoundException, IOException {
		campDatabaseFileWriterObj.writecampDatabaseFile(filePath, CampDatabaseObj);
	}

	/**
	 * This method stores CampMembershipDatabase object into a file by
	 * using the CampMembershipDatabaseFileWriter to call its writeToCampMembershipDatabase method.
	 * @param filePath refers to the filepath where the file to be written is located.
	 * @param campMambershipDatabaseObj refers to campMambershipDatabase object that stores infomation about campmembership.
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void storeCampMembershipDatabase(String filePath, CampMembershipDatabase campMambershipDatabaseObj) throws FileNotFoundException, IOException {
		campMembershipDatabaseFileWriterObj.writeCampMembershipDatabaseFile(filePath, campMambershipDatabaseObj);
	}

	/**
	 * This method stores UniqueIDGenerator object into a file by
	 * using the UniqueIDGeneratorFileWrite to call its writeUniqueIDGeneratorFile method.
	 * @param filePath refers to the filepath where the file to be written is located.
	 * @param uniqueIDGeneratorObj refers to UniqueIDGenerator object.
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void storeuniqueIDGeneratorObj(String filePath, LongIncrementalIDGenerator uniqueIDGeneratorObj) throws FileNotFoundException, IOException {
		uniqueIDGeneratorFileWriteObj.writeUniqueIDGeneratorFile(filePath, uniqueIDGeneratorObj);
	}

}