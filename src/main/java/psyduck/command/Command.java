package psyduck.command;


import java.io.IOException;

public abstract class Command {
    public abstract CommandResult execute() throws IOException;
}
