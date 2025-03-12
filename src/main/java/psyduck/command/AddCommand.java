package psyduck.command;

import static psyduck.Psyduck.count;
import static psyduck.Psyduck.taskList;

import java.io.IOException;

import psyduck.exception.TaskUndefinedException;
import psyduck.task.Task;

public abstract class AddCommand extends Command {
    private String userInput;

    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    protected static void printAddTaskStatement(Task task) {
        System.out.print("Psyduck added: ");
        task.printTask();
        System.out.println();
        System.out.println("You have " + count + " psyduck tasks");
    }

    public static void addtoTaskList(Task task) throws IOException {
        taskList.add(task);
        count++;
        printAddTaskStatement(task);
    }

    protected String[] parseTask() throws TaskUndefinedException, ArrayIndexOutOfBoundsException {
        if (!userInput.contains(" ")) {
            throw new TaskUndefinedException();
        }
        String[] details = userInput.split(" ", 2);
        String[] splitted = details[1].split("/");
        if (splitted.length == 0 || splitted[0].isEmpty()) {
            throw new TaskUndefinedException();
        }
        splitted[0] = splitted[0].trim();
        return splitted;
    }
}
