package psyduck.command;

import static psyduck.Psyduck.count;

import java.io.IOException;

import psyduck.exception.ExcessArgsException;
import psyduck.exception.TaskUndefinedException;
import psyduck.task.Event;
import psyduck.ui.UI;

public class AddEventCommand extends AddCommand {
    public AddEventCommand(String details) {
        super(details);
    }

    public CommandResult execute() throws IOException {
        try {
            String[] splitted = parseTask();
            if (splitted.length < 3) {
                throw new ArrayIndexOutOfBoundsException("Not enough datetimes");
            } else if (splitted.length > 3) {
                throw new ExcessArgsException("Too many datetimes in Event");
            }
            Event newEvent = new Event(splitted[0], splitted[1], splitted[2]);
            addtoTaskList(newEvent);
            return new CommandResult(count, newEvent);

        } catch (ArrayIndexOutOfBoundsException e) {
            //if user did not put start &/or end time
            System.out.println("Psyduck: What time start what time end?");
        } catch (TaskUndefinedException e) {
            System.out.println("Psyduck: Nani Event?");
        } catch (ExcessArgsException e) {
            System.out.println("Psyduck: Too many datetimes in Event, only start and end allowed");
        }
        UI.printCommandFormat(AddEventCommand.class);
        return null;
    }
}
