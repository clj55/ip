package psyduck.command;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super();
    }

    @Override
    public CommandResult execute() {
        System.out.println("Nani??");
        return null;
    }
}
