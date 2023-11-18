package readerwriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class LastIDFileReader {

	private int givenLastID;

	/**
	 * Reads integer value (value of LastID) from given file
	 * @param filePath is to be written to the file corresponding to the filePath
	 * @return value of LastID previously stored in the file given
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public int readLastID(String filePath) throws FileNotFoundException, IOException {
		BufferedReader LastIDReader = new BufferedReader(new FileReader(filePath));
		givenLastID = Integer.parseInt(LastIDReader.readLine());
		LastIDReader.close();
		return givenLastID;
	}

}