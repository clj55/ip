package psyduck.command;

import psyduck.ui.UI;

public class UnmarkCommand extends TaskIndexedCommand {
    public UnmarkCommand(String userInput) {
        super(userInput);
    }

    public CommandResult execute() {
        if (!this.isSuccessParse()) {
            UI.printCommandFormat(UnmarkCommand.class);
            return null;
        }
        System.out.println("Psyduck is NOT impressed");
        this.task.setDone(false);
        this.task.printTask();
        System.out.println();
        return new CommandResult(this.getIndex(), this.task);
    }
}
