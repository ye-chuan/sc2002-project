package readerwriter;
import entity.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CampMembershipDatabaseFileReader {

	private CampMembershipDatabase campMembershipDatabaseobj;
	
	/**
	 * This method deserialises the byte stream in the file given into
	 * a CampMembershipDatabase object and returns this object.
	 * @param filePath refers to the filepath where the file to be read is located
	 * @return CampMembershipDatabase object
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws ClassNotFoundException When class of a serialized object cannot be found.
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public CampMembershipDatabase readCampMembershipDatabaseFile(String filePath) throws FileNotFoundException, ClassNotFoundException, IOException {
		FileInputStream fisCampMembership = new FileInputStream(filePath);
		ObjectInputStream oisCampMembership = new ObjectInputStream(fisCampMembership);
		campMembershipDatabaseobj = (CampMembershipDatabase) oisCampMembership.readObject();
		fisCampMembership.close();
		oisCampMembership.close();
		return campMembershipDatabaseobj;
	}

}