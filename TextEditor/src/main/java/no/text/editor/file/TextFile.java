package no.text.editor.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * TextFile class when initialized contains reference to where the file is stored
 * and has methods for writing and reading a file.
 */
public class TextFile {
    private final String fileName; // file name
    private final String path; // file path
    private final File file; // File reference

    /**
     * Constructor takes inn file objects and initializes the class variables
     *
     * @param file file object for the file reference
     */
    public TextFile(File file) {
        this.file = file;
        this.fileName = file.getName();
        this.path = file.getPath();
    }

    /**
     * Public method reads file and store them as an array of strings
     * Each string in the list is a line from the file
     *
     * @return an array with strings
     */
    public String[] readFile() {
        int lines = this.getNumberOfFileLines();
        String[] fileData = new String[lines];

        try {
            Scanner fileReader = new Scanner(this.file);
            for (int line = 0; line < lines; line++) {
                fileData[line] = fileReader.nextLine();
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return fileData;
    }

    /**
     * Private method gets number of lines in the file
     *
     * @return number of lines in the file
     */
    private int getNumberOfFileLines() {
        int lines = 0;

        try {
            Scanner lineCounter = new Scanner(this.file);
            while (lineCounter.hasNextLine()) {
                lineCounter.nextLine();
                lines++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    /**
     * Public method takes inn an array of strings and writes all the lines in to the file
     *
     * @param textData array with strings
     */
    public void writeToFile(String[] textData) {
        try {
            FileWriter writer = new FileWriter(this.file);
            int counter = 0;
            for (String line: textData) {
                writer.write(line); counter++;
                if (counter < textData.length)
                    writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the File object
     * @return File object
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Getter for the file path
     * @return string containing file path
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Getter for file name
     * @return String with file name
     */
    public String getFileName() {
        return this.fileName;
    }
}
