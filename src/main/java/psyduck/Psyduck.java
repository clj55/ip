package psyduck;

import java.io.IOException;
import java.util.ArrayList;

import psyduck.command.Command;
import psyduck.command.CommandResult;
import psyduck.parser.Parser;
import psyduck.storage.FileInterfacer;
import psyduck.storage.TaskFileInterfacer;
import psyduck.task.Task;
import psyduck.ui.UI;

public class Psyduck {
    private static final String TASK_LIST_FILEPATH = "./PsyduckData/tasks.txt";
    public static int count = 0;
    public static final ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        UI.printIntro();
        TaskFileInterfacer TaskFile = new TaskFileInterfacer(TASK_LIST_FILEPATH);
        TaskFile.loadTaskFile();

        String userInput = Parser.getUserInput();
        while (!userInput.equals("bye")) {
            Command command = Parser.chooseCommand(userInput);
            CommandResult result = command.execute();
            if (result != null) {
                TaskFile.savetoTaskFile(result);
            }
            userInput = Parser.getUserInput();
        }
        UI.printOutro();
    }
}
