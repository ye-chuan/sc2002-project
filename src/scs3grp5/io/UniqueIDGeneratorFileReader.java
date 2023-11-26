/**
 * Reads UniqueIDGenerator object from a serialised file
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

public class UniqueIDGeneratorFileReader {

	private LongIncrementalIDGenerator UniqueIDGeneratorObj;

	/**
	 * This method deserialises the byte stream in the file given into
	 * a UniqueIDGenerator object and returns this object.
	 * @param filePath refers to the filepath where the file to be read is located
	 * @return UniqueIDGenerator object
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws ClassNotFoundException When class of a serialized object cannot be found.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public LongIncrementalIDGenerator readUniqueIDGeneratorFile(String filePath) throws FileNotFoundException, ClassNotFoundException, IOException {
		FileInputStream fisIDGenerator = new FileInputStream(filePath);
		ObjectInputStream oisIDGenerator = new ObjectInputStream(fisIDGenerator);
		UniqueIDGeneratorObj = (LongIncrementalIDGenerator) oisIDGenerator.readObject();
		oisIDGenerator.close();
		fisIDGenerator.close();
		return UniqueIDGeneratorObj;
	}

}