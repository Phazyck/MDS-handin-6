package util;

import java.io.*;

/**
 * Handles reading file contents as Strings create new neighboring result files.
 */
public class FileOrganizer {

    /**
     * The path where the files will be read/written.
     */
    private String path;
    /**
     * The first part of the file name.
     */
    private String prefix;

    /**
     * Creates a FileManager using the given File to extract the necessary
     * information.
     *
     * @param taskmanagerFile The XML-file containing task manager data.
     */
    public FileOrganizer(File taskmanagerFile) {
        try {
            this.path = taskmanagerFile.getParentFile().getCanonicalPath() + "/";
            String filename = taskmanagerFile.getName();
            this.prefix = filename.substring(0, filename.lastIndexOf('.'));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Read the file name 'prefix+suffix' located at 'path'. Example: path =
     * "./files/example/", prefix = "prescribe-medicine-example", suffix =
     * ".xml". This will read the file
     * "./files/example/prescribe-medicine-example.xml".
     *
     * @param suffix The last part of the File name.
     * @return The contents of the File.
     */
    public String readFile(String suffix) {
        // Prepare a BufferedReader for the given file.
        try (BufferedReader reader = new BufferedReader(new FileReader(path + prefix + suffix))) {
            // Prepare containers for the lines and results.
            String line, results = "";
            // As long as the read line isn't null...
            while ((line = reader.readLine()) != null) {
                // ...add that line to the results.
                results += line + "\n";
            }
            // When there's no more lines to read, return the result.
            return results;
        } catch (Exception ex) {
            // In case an Exception is thrown, throw it as a RuntimeException.
            throw new RuntimeException(ex);
        }
    }

    /**
     * Write the contents to a file named 'prefix+suffix' located at 'path'.
     * Example: path = "./files/example/" prefix = "prescribe-medicine-example"
     * suffix ="-model.dot". This will create the file
     * "./files/example/prescribe-medicine-example-model.dot" and write the
     * contents to it.
     *
     * @param suffix The last part of the File name.
     * @param contents The contents which will be written to the file.
     */
    public void writeFile(String suffix, String contents) {
        // Prepare a Writer in order to write the file.
        try (Writer writer = new BufferedWriter(new FileWriter(new File(path + prefix + suffix)))) {
            // Write the contents to the file.
            writer.write(contents);
        } catch (IOException ex) {
            // In case an Exception is thrown, throw it as a RuntimeException.
            throw new RuntimeException(ex);
        }
    }
}
