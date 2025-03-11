package psyduck.storage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import psyduck.task.Deadline;
import psyduck.task.Event;
import psyduck.task.Task;
import static psyduck.command.AddCommand.addtoTaskList;

public class FileInterfacer {
    private final Path path;
    private final String filepath;

    public FileInterfacer(String filepath) {
        this.filepath = filepath;
        this.path = Paths.get(filepath);
    }

    public List<String> getFileLines() throws IOException{
        return Files.readAllLines(path);
    }

    public void deleteLine(int lineIndex) throws IOException{
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
        Files.write(path, fileLines, StandardCharsets.UTF_8);
    }

    public Path getPath() {
        return path;
    }

    public String getFilepath() {
        return filepath;
    }
}
