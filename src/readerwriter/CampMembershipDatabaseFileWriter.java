package readerwriter;
import entity.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

public class CampMembershipDatabaseFileWriter {

	/**
	 * This method seralises campMambershipDatabase object and stores it into a file.
	 * @param filePath refers to the filepath where the file to be written is located
	 * @param campMemberShipDatabaseObj is a campMambershipDatabase object that is going to be 
	 * written into path corresonding to the file path given.
	 * @return does not return anything (void)
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