/**
 * Reads Campdatabase object from a serialised file
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
 * Reads CampDatabase object from file
 */
public class CampDatabaseFileReader {

	private CampDatabase campDatabaseobj;

	/**	
	 * This method deserialises the byte stream in the file given into
	 * a CampDatabase object and returns this object.
	 * @param filePath refers to the filepath where the file to be read is located
	 * @return CampDatabase object
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws ClassNotFoundException When class of a serialized object cannot be found.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public CampDatabase readCampDatabaseFile(String filePath) throws FileNotFoundException, ClassNotFoundException, IOException {
		FileInputStream fisCamp = new FileInputStream(filePath);
		ObjectInputStream oisCamp = new ObjectInputStream(fisCamp);
		campDatabaseobj = (CampDatabase) oisCamp.readObject();
		oisCamp.close();
		fisCamp.close();
		return campDatabaseobj;
	}

}