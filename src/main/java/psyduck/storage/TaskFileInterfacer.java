package psyduck.storage;

import static psyduck.command.AddCommand.addtoTaskList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import psyduck.task.Deadline;
import psyduck.task.Event;
import psyduck.task.Task;
import psyduck.command.CommandResult;

public class TaskFileInterfacer extends FileInterfacer{

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
            String[] taskDetails = fileLine.split("/");
            boolean taskIsDone = taskDetails[1].equals("1");
            switch (taskDetails[0]) {
            case "T" -> addtoTaskList(new Task(taskDetails[2], taskIsDone));
            case "D" -> addtoTaskList(new Deadline(taskDetails[2], taskIsDone, taskDetails[3]));
            case "E" -> addtoTaskList(new Event(taskDetails[2], taskIsDone, taskDetails[3], taskDetails[4]));
            }
        }
    }

    public void savetoTaskFile(CommandResult result) throws IOException {
        if (result.isDelete()){
            deleteLine(result.getTaskNum());
            return;
        }
        writeLine(result.getTaskNum(), result.getToStore());
    }
}
