package no.text.editor.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFile {
    private String fileName;
    private String path;
    private File file;

    public TextFile(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;

        // creating new File object...
        try {
            this.file = new File(this.path + this.fileName);
        } catch (RuntimeException e) {
            System.out.println("File with name: " + this.fileName + ", or filepath " + this.path + ", does not exist!");
            System.exit(1);
        }
    }

    // read file and store them as an array of strings
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



    public File getFile() {
        return this.file;
    }

    public String getPath() {
        return this.path;
    }

    public String getFileName() {
        return this.fileName;
    }
}
