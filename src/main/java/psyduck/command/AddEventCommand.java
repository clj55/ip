package psyduck.command;

import static psyduck.Psyduck.count;

import java.io.IOException;

import psyduck.exception.ExcessArgsException;
import psyduck.exception.InsufficientArgsException;
import psyduck.exception.TaskFieldBlankException;
import psyduck.exception.TaskNameUndefinedException;
import psyduck.parser.Parser;
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
                throw new InsufficientArgsException("Not enough datetimes");
            } else if (splitted.length > 3) {
                throw new ExcessArgsException("Too many datetimes in Event");
            }
            Parser.verifyNotBlank(splitted);
            Event newEvent = new Event(splitted[0], splitted[1], splitted[2]);
            addtoTaskList(newEvent);
            return new CommandResult(count, newEvent);

        } catch (InsufficientArgsException e) {
            //if user did not put start &/or end time
            System.out.println("Psyduck: What time start what time end?");
        } catch (TaskNameUndefinedException e) {
            System.out.println("Psyduck: Nani Event?");
        } catch (ExcessArgsException e) {
            System.out.println("Psyduck: Too many datetimes in Event, only start and end allowed");
        } catch (TaskFieldBlankException e) {
            System.out.println("Psyduck: Task Description, Start Time and End Time cannot be Blank");
        }
        UI.printCommandFormat(AddEventCommand.class);
        return null;
    }
}
