package psyduck.storage;

import static psyduck.Psyduck.taskList;
import static psyduck.command.AddCommand.addtoTaskList;
import static psyduck.parser.Parser.verifyNotBlank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import psyduck.command.CommandResult;
import psyduck.exception.TaskFieldBlankException;
import psyduck.exception.TaskNameUndefinedException;
import psyduck.task.Deadline;
import psyduck.task.Event;
import psyduck.task.Task;

public class TaskFileInterfacer extends FileInterfacer {

    public TaskFileInterfacer(String filepath) {
        super(filepath);
    }

    public void loadTaskFile() throws IOException {
        File f = new File(this.getFilepath());
        Scanner fileScanner = new Scanner(f);

        while (fileScanner.hasNext()) {
            String fileLine = fileScanner.nextLine();
            if (fileLine.isBlank()) {
                continue;
            }
            try {
                String[] taskDetails = fileLine.split("/");
                verifyNotBlank(taskDetails);

                boolean taskIsDone = verifyIsDone(taskDetails[1]);
                switch (taskDetails[0]) {
                case "T" -> addtoTaskList(new Task(taskDetails[2], taskIsDone));
                case "D" -> addtoTaskList(new Deadline(taskDetails[2], taskIsDone, taskDetails[3]));
                case "E" -> addtoTaskList(new Event(taskDetails[2], taskIsDone, taskDetails[3], taskDetails[4]));
                default -> throw new TaskNameUndefinedException("Type of Task (T/D/E) not specified in Task File");
                }
            } catch (ArrayIndexOutOfBoundsException | TaskNameUndefinedException | TaskFieldBlankException e) {
                System.out.println("This task could not be read and will be removed: " + fileLine);
            }
        }
    }


    private static boolean verifyIsDone(String isDone) throws TaskNameUndefinedException {
        if (!(isDone.equals("1") | isDone.equals("0"))) {
            throw new TaskNameUndefinedException("Task isDone is not 1 or 0");
        }
        return isDone.equals("1");
    }

    /**
     * Rewrites TaskFile with only valid tasks
     * Removes extra lines and white spaces
     *
     * @throws IOException
     */
    public void rewriteTaskFile() throws IOException {
        List<String> newFileLines = new ArrayList<String>();
        for (Task s : taskList) {
            newFileLines.add(s.toFileString());
        }
        writeFile(newFileLines);
    }

    public void savetoTaskFile(CommandResult result) throws IOException {
        if (result.isDelete()) {
            deleteLine(result.getTaskNum());
            return;
        }
        writeLine(result.getTaskNum(), result.getToStore());
    }
}
