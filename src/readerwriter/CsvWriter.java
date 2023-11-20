package readerwriter;

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class CsvWriter {
    private List<String> rows;
    private int numCols;
    private Path path;

    /**
     * Creates a CSV Writer which will aid in the writing of CSV files.
     *
     * @param filepath Where the CSV file will be save (will override existing)
     * @param numCols Number of columns in the CSV file
     */
    public CsvWriter(String filepath, int numCols) {
        this.path = Paths.get(filepath);
        this.numCols = numCols;

        this.rows = new ArrayList<String>();
    }

    /**
     * Creates a CSV Writer which will aid in the writing of CSV files.
     *
     * Number of columns will be infered from the first addition of row.
     *
     * @param filepath Where the CSV file will be save (will override existing)
     */
    public CsvWriter(String filepath) {
        this(filepath, -1);
    }

    /**
     * Add a row to the buffer of this CsvWriter.
     *
     * @param strings A vararg, give as many strings as needed in a row
     *
     * @throws IllegalArgumentException When wrong number of strings are given (i.e. wrong number of columns)
     */
    public void addRow(String... strings) {
        if (numCols == -1)
            numCols = strings.length;
        else if (numCols != strings.length)
            throw new IllegalArgumentException("Number of Columns mismatch in CsvWriter");
        rows.add(String.join(",", strings));
    }

    /**
     * Write the buffer into the file.
     *
     * @throws IOException If an I/O error occurs writing to or creating the file, or the text cannot be encoded using the specified charset
     */
    public void write() throws IOException {
        Files.write(path, rows, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        CsvWriter writer = new CsvWriter("test.csv");
        writer.addRow("oneone", "onetwo", "onethree");
        writer.addRow("twoone", "twotwo", "twothree");
        try {
            writer.write();
        }
        catch(IOException e) {
            System.out.println("IO Error");
        }
    }
}
