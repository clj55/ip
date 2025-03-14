package psyduck.command;

import static psyduck.Psyduck.count;
import static psyduck.Psyduck.taskList;

import psyduck.ui.UI;

public class DeleteCommand extends TaskIndexedCommand {
    public DeleteCommand(String userInput) {
        super(userInput);
    }

    /**
     * Delete Task from TaskList
     * @return CommandResult to be used to delete from TaskFile
     */
    public CommandResult execute() {
        if (!this.isSuccessParse()) {
            UI.printCommandFormat(DeleteCommand.class);
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
