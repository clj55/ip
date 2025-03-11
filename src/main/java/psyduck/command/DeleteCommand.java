package psyduck.command;

import static psyduck.Psyduck.taskList;
import static psyduck.Psyduck.count;

public class DeleteCommand extends TaskIndexedCommand {
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    public CommandResult execute() {
        if (!this.isSuccessParse()) {
            return null;
        }
        System.out.println("Psyduck deleted task: ");
        this.task.printTask();
        System.out.println();
        taskList.remove(this.task);
        count--;
        System.out.println("Now you have " + count + " psyduck tasks");
        return new CommandResult(this.getIndex(), this.getTask(), true);
    }
}
