/**
 * Writes CampMembershipDatabase object into a serialised file
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
 * Writes CampMembershipDatabase object into a file
 */
public class CampMembershipDatabaseFileWriter {

	/**
	 * This method seralises campMambershipDatabase object and stores it into a file.
	 * @param filePath refers to the filepath where the file to be written is located
	 * @param campMemberShipDatabaseObj is a campMambershipDatabase object that is going to be 
	 * written into path corresonding to the file path given.
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void writeCampMembershipDatabaseFile(String filePath, CampMembershipDatabase campMemberShipDatabaseObj) throws FileNotFoundException, IOException {
		FileOutputStream fosWriteCampMembership = new FileOutputStream(filePath);
		ObjectOutputStream oosWriteCampMembership = new ObjectOutputStream(fosWriteCampMembership);
		oosWriteCampMembership.writeObject(campMemberShipDatabaseObj);
		fosWriteCampMembership.close();
		oosWriteCampMembership.close();
	}

}