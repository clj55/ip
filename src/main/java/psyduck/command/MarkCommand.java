package psyduck.command;

import psyduck.ui.UI;

public class MarkCommand extends TaskIndexedCommand {
    public MarkCommand(String userInput) {
        super(userInput);
    }

    public CommandResult execute() {
        if (!this.isSuccessParse()) {
            UI.printCommandFormat(MarkCommand.class);
            return null;
        }
        System.out.println("Psyduck is impressed");
        this.task.setDone(true);
        this.task.printTask();
        System.out.println();
        return new CommandResult(this.getIndex(), this.task);
    }
}
