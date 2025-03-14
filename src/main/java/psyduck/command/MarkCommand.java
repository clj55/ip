package psyduck.command;

public class MarkCommand extends TaskIndexedCommand {
    public MarkCommand(String userInput) {
        super(userInput);
    }

    /**
     * Marks Task as Done in TaskList
     * @return Command Result to update Task in TaskFile
     */
    public CommandResult execute() {
        if (!this.isSuccessParse()) {
            return null;
        }
        System.out.println("Psyduck is impressed");
        this.task.setDone(true);
        this.task.printTask();
        System.out.println();
        return new CommandResult(this.getIndex(), this.task);
    }
}
