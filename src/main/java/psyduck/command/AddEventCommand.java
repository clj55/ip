package psyduck.command;

import java.io.IOException;

import psyduck.exception.TaskUndefinedException;
import psyduck.task.Event;

public class AddEventCommand extends AddCommand {
    public AddEventCommand(String details) {
        super(details);
    }

    /**
     * Adds Event Task to TaskList
     * @return Command Result with new Event
     * @throws IOException
     */
    public CommandResult execute() throws IOException {
    try {
        String[] splitted = parseTask();
        Event newEvent = new Event(splitted[0], splitted[1], splitted[2]);
        addtoTaskList(newEvent);

    } catch (ArrayIndexOutOfBoundsException e) {
        //if user did not put start &/or end time
        System.out.println("Psyduck: Nanji kara nanji made desuka (What time start what time end)?");
    } catch (TaskUndefinedException e) {
        System.out.println("Psyduck: Nani Event");
    }
        return null;
    }
}
