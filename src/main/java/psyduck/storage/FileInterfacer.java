package psyduck.storage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Represents a File and allows editing of specific lines in the file
 */
public class FileInterfacer {
    private final Path path;
    private final String filepath;

    /**
     * Creates a FileInterfacer with the specified filepath
     * @param filepath Directory Path to the file
     */
    public FileInterfacer(String filepath) {
        this.filepath = filepath;
        File f = new File(filepath);
        try {
            f.getParentFile().mkdirs();
            f.createNewFile(); // create tasks file if it does not exist
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("could not create tasks.txt file");
        }
        this.path = Paths.get(filepath);
    }

    /**
     * Gets all lines of text in the file as a list
     * @return List of file lines
     * @throws IOException
     */
    public List<String> getFileLines() throws IOException {
        return Files.readAllLines(path);
    }

    /**
     * Deletes a line at specified lineIndex
     * @param lineIndex Index of Line in fileLines list = Line Number in text file - 1
     * @throws IOException
     */
    public void deleteLine(int lineIndex) throws IOException {
        List<String> fileLines = getFileLines();
        fileLines.remove(lineIndex);
        Files.write(path, fileLines, StandardCharsets.UTF_8);
    }

    /**
     * Writes text to a specific line
     * @param lineIndex Index of Line in fileLines list = Line Number in text file - 1
     * @param text Text to write
     * @throws IOException
     */
    public void writeLine(int lineIndex, String text) throws IOException {
        List<String> fileLines = getFileLines();
        if (lineIndex >= fileLines.size()) {
            fileLines.add(text);
        } else {
            fileLines.set(lineIndex, text);
        }
        writeFile(fileLines);
    }

    /**
     * Rewrites entire file with fileLines
     */
    public void writeFile(List<String> fileLines) throws IOException {
        Files.write(path, fileLines, StandardCharsets.UTF_8);
    }

    public Path getPath() {
        return path;
    }

    public String getFilepath() {
        return filepath;
    }
}
