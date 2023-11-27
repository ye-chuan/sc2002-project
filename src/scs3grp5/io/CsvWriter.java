package scs3grp5.io;

import java.util.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

/**
 * A CSV Writer which aid in the writing of simple CSV files.
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class CsvWriter {
    private List<List<String>> rows;
    private int numCols = 0;
    private Path path;

    /**
     * Creates a CSV Writer which will aid in the writing of CSV files.
     *
     * Number of columns will be dynamically infered as rows are added
     *
     * @param filepath Where the CSV file will be save (will override existing)
     */
    public CsvWriter(String filepath) {
        this.path = Paths.get(filepath);
        this.rows = new ArrayList<List<String>>();
    }

    /**
     * Add a row to the buffer of this CsvWriter.
     *
     * Null values will be rendered as an empty string
     *
     * @param strings A vararg, give as many strings as needed in a row
     */
    public void addRow(String... strings) {
        if (strings.length > numCols)
            numCols = strings.length;
        List<String> row = new ArrayList<String>();
        for (String s : strings) {
            row.add(s == null ? "" : s);
        }
        rows.add(row);
    }

    /**
     * Write the buffer into the file.
     *
     * @throws IOException If an I/O error occurs writing to or creating the file, or the text cannot be encoded using the specified charset
     */
    public void write() throws IOException {
        System.out.println(rows);
        List<String> commaSepLines = new ArrayList<String>();
        // Pad the rows such that they all have the max columns
        for (List<String> row : rows) {
            List<String> strInCurLine = new ArrayList<String>();
            for (int i=0; i<numCols; i++) {
                if (i < row.size()) {
                    strInCurLine.add(row.get(i));
                }
                else {
                    strInCurLine.add("");
                }
            }
            commaSepLines.add(String.join(",", strInCurLine));
        }
        Files.write(path, commaSepLines, StandardCharsets.UTF_8);
    }
}
