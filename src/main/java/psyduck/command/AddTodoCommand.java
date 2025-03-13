package psyduck.command;

import static psyduck.Psyduck.count;

import java.io.IOException;

import psyduck.task.Task;
import psyduck.ui.UI;

public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(String details) {
        super(details);
    }

    public CommandResult execute() throws IOException {
        String details = this.getUserInput().substring(4);
        if (details.isEmpty()) {
            System.out.println("Psyduck: What do you want to do?");
            UI.printCommandFormat(AddTodoCommand.class);
            return null;
        }
        Task task = new Task(details.trim());
        addtoTaskList(task);

        return new CommandResult(count, task);
    }
}
