package psyduck.command;

import static psyduck.Psyduck.count;
import java.io.IOException;

import psyduck.task.Task;

public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String details) {
        super(details);
    }

    /**
     * Adds Todo to the Tasklist
     * @return Command result containing new task
     * Line number in command result is last line in text file
     * @throws IOException
     */
    public CommandResult execute() throws IOException {
        String details = this.getUserInput().substring(4);
        if (details.isEmpty()) {
            System.out.println("Psyduck dont know what you want to do");
            return null;
        }
        Task task = new Task(details.trim());
        addtoTaskList(task);

        return new CommandResult(count, task);
    }
}
