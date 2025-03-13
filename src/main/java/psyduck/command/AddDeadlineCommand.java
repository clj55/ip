package psyduck.command;

import static psyduck.Psyduck.count;

import java.io.IOException;

import psyduck.exception.TaskUndefinedException;
import psyduck.task.Deadline;
import psyduck.ui.UI;

public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(String userInput) {
        super(userInput);
    }

    public CommandResult execute() throws IOException {

        try {
            String[] splitted = parseTask();
            Deadline newDeadline = new Deadline(splitted[0], splitted[1]);
            addtoTaskList(newDeadline);
            return new CommandResult(count, newDeadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Psyduck: When is the deadline?");
        } catch (TaskUndefinedException e) {
            System.out.println("Psyduck: Nani Deadline?");
        }
        UI.printCommandFormat(AddDeadlineCommand.class);
        return null;
    }
}
