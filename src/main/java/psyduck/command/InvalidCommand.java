package psyduck.command;

import java.io.IOException;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super();
    }

    /**
     * Gives Error Message when User types an Invalid Command
     * @return null: No Command Result
     */
    @Override
    public CommandResult execute() {
        System.out.println("Nani??");
        return null;
    }
}
