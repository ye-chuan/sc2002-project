package readerwriter;
import entity.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

public class UniqueIDGeneratorFileWrite {

	/**
	 * This method seralises uniqueIDGenerator object and stores it into a file.
	 * @param filePath refers to the filepath where the file to be read is located
	 * @param uniqueIDGeneratorObj is a uniqueIDGenerator object that is going to be 
	 * written into path corresonding to the file path given.
	 * @return does not return anything (void)
	 * @throws FileNotFoundException file corresponding to the file path cannot be found. 
	 * In other words, the file path given is invalid
	 * @throws IOException Signals that an I/O exception of some sort has occurred. 
	 * This class is the general class of exceptions produced by failed or interrupted I/O operations.
	 */
	public void writeUniqueIDGeneratorFile(String filePath, UniqueIDGenerator uniqueIDGeneratorObj) throws FileNotFoundException, IOException {
		FileOutputStream fosWriteIDGenerator = new FileOutputStream(filePath);
		ObjectOutputStream oosWriteIDGenerator = new ObjectOutputStream(fosWriteIDGenerator);
		oosWriteIDGenerator.writeObject(uniqueIDGeneratorObj);
		oosWriteIDGenerator.close();
		fosWriteIDGenerator.close();
	}

}