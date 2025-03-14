package psyduck.storage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileInterfacer {
    private final Path path;
    private final String filepath;

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

    public List<String> getFileLines() throws IOException {
        return Files.readAllLines(path);
    }

    public void deleteLine(int lineIndex) throws IOException {
        List<String> fileLines = getFileLines();
        fileLines.remove(lineIndex);
        Files.write(path, fileLines, StandardCharsets.UTF_8);
    }

    public void writeLine(int lineIndex, String data) throws IOException {
        List<String> fileLines = getFileLines();
        if (lineIndex >= fileLines.size()) {
            fileLines.add(data);
        } else {
            fileLines.set(lineIndex, data);
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
