/**
 * Reader controller class that is able to read CampDatabase, CampMembershipDatabase,
 * UniqueIDGenerator and UserDatabase from a serialise file
 * @author Ong Yi Ren, Elaine
 * @version 1.0
 * @since 2023-11-26
 */

package scs3grp5.io;

import scs3grp5.entity.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReaderController {

	private UserDatabaseFileReader UserDatabaseFileReadObj = new UserDatabaseFileReader();
	private CampDatabaseFileReader CampDatabaseFileReaderObj = new CampDatabaseFileReader();
	private CampMembershipDatabaseFileReader CampMembershipDatabaseFileReaderObj = new CampMembershipDatabaseFileReader();
	private UniqueIDGeneratorFileReader uniqueIDGeneratorFileReaderObj = new UniqueIDGeneratorFileReader();

	/**
	 * This method reads the file located at the corresponding file Path given
	 * and returns UserDatabase object by using UserDatabaseFileReader object to call
	 * its readUserDatabaseFile method.
	 * This method might catch a FileNotFoundException from the readUserDatabaseFile method
	 * but would throw it back to the method or class who calls it.
	 * @param filePath refers to the filepath where the file to be read is located
	 * @return UserDatabase object
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws ClassNotFoundException When class of a serialized object cannot be found.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
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
	 * and returns campMembershipDatabase object by using CampMembershipDatabaseFileReader object to call
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
	 * This method reads the file located at the corresponding file Path given
	 * and returns UniqueIDGenerator object by using uniqueIDGeneratorFileReader object to call
	 * its readUniqueIDGeneratorFile method.
	 * This method might catch a FileNotFoundException from the readUniqueIDGeneratorFile method
	 * but would throw it back to the method or class who calls it.
	 * @param filePath refers to the filepath where the file to be read is located
	 * @return UniqueIDGenerator object
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public LongIncrementalIDGenerator getUniqueIDGenerator(String filePath) throws FileNotFoundException, ClassNotFoundException, IOException {
		return uniqueIDGeneratorFileReaderObj.readUniqueIDGeneratorFile(filePath);
	}

}