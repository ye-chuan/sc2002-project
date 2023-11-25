package scs3grp5.io;

import scs3grp5.entity.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

public class CampDatabaseFileWriter {

	/**
	 * This method seralises CampDatabase object and stores it into a file.
	 * @param filePath refers to the filepath where the file to be written is located
	 * @param campDatabaseObj is a CampDatabase object that is going to be 
	 * written into path corresonding to the file path given.
	 * @return does not return anything (void)
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void writecampDatabaseFile(String filePath, CampDatabase campDatabaseObj) throws FileNotFoundException, IOException {
		FileOutputStream fosWriteCamp = new FileOutputStream(filePath);
		ObjectOutputStream oosWriteCamp = new ObjectOutputStream(fosWriteCamp);
		oosWriteCamp.writeObject(campDatabaseObj);
		oosWriteCamp.close();
		fosWriteCamp.close();

	}

}