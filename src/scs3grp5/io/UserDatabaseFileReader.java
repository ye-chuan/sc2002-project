/**
 * Reads UserDatabase object from a serialised file
 * @author Ong Yi Ren, Elaine
 * @version 1.0
 * @since 2023-11-26
 */

package scs3grp5.io;

import scs3grp5.entity.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
/**
 * Reads UserDatabase objects from a file
 */
public class UserDatabaseFileReader {

	private UserDatabase UserDatabaseObj;

	/**
	 * This method deserialises the byte stream in the file given into
	 * a UserDatabase object and returns this object.
	 * @param filePath refers to the filepath where the file to be read is located
	 * @return UserDatabase Object
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws ClassNotFoundException When class of a serialized object cannot be found.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public UserDatabase readUserDatabaseFile(String filePath) throws FileNotFoundException, ClassNotFoundException, IOException {
		FileInputStream fisUser = new FileInputStream(filePath);
		ObjectInputStream oisUser = new ObjectInputStream(fisUser);
		UserDatabaseObj = (UserDatabase) oisUser.readObject();
		fisUser.close();
		oisUser.close();
		return UserDatabaseObj;
	}

}