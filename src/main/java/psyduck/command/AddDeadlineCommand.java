package psyduck.command;

import static psyduck.Psyduck.count;

import java.io.IOException;

import psyduck.exception.ExcessArgsException;
import psyduck.exception.TaskUndefinedException;
import psyduck.task.Deadline;

public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(String userInput) {
        super(userInput);
    }

    public CommandResult execute() throws IOException {
        try {
            String[] splitted = parseTask();
            if (splitted.length > 2) {
                throw new ExcessArgsException("Too many datetimes in Deadline");
            } else if (splitted.length < 2) {
                throw new ArrayIndexOutOfBoundsException("Too few datetimes in Deadline");
            }
            Deadline newDeadline = new Deadline(splitted[0], splitted[1]);
            addtoTaskList(newDeadline);
            return new CommandResult(count, newDeadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Psyduck: Shimekiri ha itsu desuka? (When is the deadline?)");
        } catch (TaskUndefinedException e) {
            System.out.println("Psyduck: Nani Deadline");
        } catch (ExcessArgsException e) {
            System.out.println("Psyduck: Too many datetimes, only 1 datetime allowed in Deadline");
        }
        return null;
    }
}
