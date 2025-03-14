package psyduck.command;

import static psyduck.Psyduck.count;
import static psyduck.Psyduck.taskList;

import psyduck.exception.TaskNameUndefinedException;
import psyduck.task.Task;

/**
 * Represents Commands that Add Tasks
 */
public abstract class AddCommand extends Command{
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

    public static void addtoTaskList(Task task) {
        taskList.add(task);
        count++;
        printAddTaskStatement(task);
    }

    protected String[] parseTask() throws TaskNameUndefinedException {
        try {
            String details = userInput.split(" ", 2)[1];
            String[] splitted = details.split("/");
            if (splitted[0].isBlank()) {
                throw new TaskNameUndefinedException("Task Name missing");
            }
            splitted[0] = splitted[0].trim();
            return splitted;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskNameUndefinedException("Task Name missing");
        }
    }
}
