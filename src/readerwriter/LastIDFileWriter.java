package readerwriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class LastIDFileWriter {

	/**
	 * This method writes the given LastID value into the file given 
	 * @param filePath refers to the filepath where the file to be written is located
	 * @param LastID is to be written to the file corresponding to the filePath
	 * @return does not return anything (void)
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void writeLastIDFile(String filePath, int LastID) throws IOException {
		BufferedWriter lastIDWriter = new BufferedWriter(new FileWriter(filePath,false)); //overwrite the file and replace with the new data, constructor invocation = false
		lastIDWriter.write(LastID);
		lastIDWriter.close();
	}

}