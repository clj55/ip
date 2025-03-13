package psyduck.command;


import java.io.IOException;

/**
 * Abstract class for all Commands
 */
public abstract class Command{

    public abstract CommandResult execute() throws IOException;
}
