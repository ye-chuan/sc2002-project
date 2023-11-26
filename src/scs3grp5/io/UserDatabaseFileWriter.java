/**
 * Writes UserDatabase object into a serialised file
 * @author Ong Yi Ren, Elaine
 * @version 1.0
 * @since 2023-11-26
 */
package scs3grp5.io;

import scs3grp5.entity.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
/**
 * Writes UserDatabase object into a file
 */
public class UserDatabaseFileWriter {

	/**
	 * This method seralises userDatabase object and stores it into a file.
	 * @param filePath refers to the filepath where the file to be written is located.
	 * @param UserDatabaseObj is a userDatabase object that is going to be 
	 * written into path corresonding to the file path given.
	 * @return does not return anything (void)
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void writeUserDatabaseFile(String filePath, UserDatabase UserDatabaseObj) throws FileNotFoundException, IOException {
		FileOutputStream fosWriteUser = new FileOutputStream(filePath);
		ObjectOutputStream oosWriteUser = new ObjectOutputStream(fosWriteUser);
		oosWriteUser.writeObject(UserDatabaseObj);
		fosWriteUser.close();
		oosWriteUser.close();
	}

}